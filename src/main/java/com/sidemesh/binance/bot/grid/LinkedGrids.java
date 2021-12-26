package com.sidemesh.binance.bot.grid;

import com.google.common.base.Objects;
import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.util.GridsUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * 当网格数较大时应使用二分法
 */
public class LinkedGrids {
    // 网格基础信息
    public final GridsInfo info;
    // 头 low price
    private final Node head;
    // 尾 high price
    private final Node tail;
    /*
     * 标记的价格点
     */
    private Node index = null;

    private LinkedGrids(GridsInfo info) {
        this.info = info;
        var n = this.head = new Node(0, info.low);
        for (var i = 1; i < info.grids; i++) {
            var node = new Node(i, n.price.add(info.stepAmount));
            n.next = node;
            node.pre = n;
            n = node;
        }

        this.tail = n;
    }

    private LinkedGrids(Symbol symbol, BigDecimal invest, BigDecimal low, BigDecimal high, int grids) {
        this(new GridsInfo(symbol, invest, low, high, grids));
    }

    public void print() {
        var sb = new StringBuilder();
        var n = head;
        while (n != null) {
            sb.append("-").append(n.price).append("-");
            n = n.next;
        }
        System.out.println(info.toString());
        System.out.println(sb);
    }

    public Node getIndex() {
        return this.index;
    }

    /**
     * 如果网格未进行初始化则进行初始化，并不支持更新 index
     * 如果网格已进行初始化，则按照 index 寻找：
     * 如果价格与当前 index 相等则不返回 callback
     */
    public UpdateResult tryUpdate(BigDecimal price) {
        /*
           🎯网格没有任何变化
         */
        var compared = index.price.compareTo(price);
        if (compared == 0) return skipUpdate();

        /*
           📉下跌
         */
        if (compared < 0) {
            // 跌穿网格不进行任何操作
            if (index == head) return skipUpdate();
            return tryUpdateByDown(price, index);
            /*
            var n = index;
            while (n != null) {
                // price > n.price
                // 当 price 价格大于节点价格时，则不继续向下查询。
                compared = price.compareTo(n.price);
                if (compared >= 0) break;
                // 继续查询下一个节点
                n = n.pre;
            }

            // 跌穿网格
            if (null == n) n = head;
            return new UpdateResult(index, n, this::updateIndex);
             */
        }

        /*
           📈上涨
         */
        // 涨穿网格不进行任何操作
        if (index == tail) return skipUpdate();
        return tryUpdateByRise(price, index);
        /*
        var n = index;
        while (n != null) {
            // 如果当前价格大于节点价格，则继续查询
            compared = price.compareTo(n.price);
            if (compared <= 0) break;
            n = n.next;
        }

        // 涨穿网格
        if (null == n) n = tail;
        return new UpdateResult(index, n, this::updateIndex);
         */
    }

    private UpdateResult tryUpdateByDown(BigDecimal price, Node n) {
        if (null == n) return new UpdateResult(index, head, this::updateIndex);
        var compared = price.compareTo(n.price);
        if (compared >= 0) return new UpdateResult(index, n, this::updateIndex);
        return tryUpdateByDown(price, n.pre);
    }

    private UpdateResult tryUpdateByRise(BigDecimal price, Node n) {
        if (null == n) return new UpdateResult(index, tail, this::updateIndex);
        var compared =  price.compareTo(n.price);
        if (compared <= 0) return new UpdateResult(index, n, this::updateIndex);
        return tryUpdateByRise(price, n.next);
    }

    private void updateIndex(Node node) {
        this.index = node;
    }

    private UpdateResult skipUpdate() {
        return new UpdateResult(index, null, null);
    }

    /**
     * 当价格小于等于网格 low price 直接设置为 low price index node
     * 当价格大于等于网格 high price 直接设置为 high price index node
     * 当价格在网格区间，则按照 low level 进行寻找选中。
     */
    public void init(BigDecimal price) {
        if (isInit()) throw new RuntimeException("already init!");

        if (head.price.compareTo(price) <= 0) {
            index = head;
            return;
        }
        
        if (tail.price.compareTo(price) >= 0) {
            index = tail;
            return;
        }
        
        var n = head;
        while (n != null) {
            var compared = n.price.compareTo(price);
            if (compared >= 0) {
                break;
            }
            n = n.next;
        }
        index = n;
    }

    public boolean isInit() {
        return this.index != null;
    }

    /*
     * new index 永远不会为 null
     */
    @Slf4j
    public static class UpdateResult {
        // 回调函数
        private final Consumer<Node> updateIndexFn;
        // 新的游标
        public final Node newIndex;
        // 当前的游标
        public final Node index;

        private UpdateResult(Node index, Node newIndex, Consumer<Node> fn) {
            this.index = index;
            this.updateIndexFn = fn;
            this.newIndex = newIndex;
        }

        public void updateIndex() {
            if (null == updateIndexFn) {
                log.warn("update index function is null");
                return;
            }
            if (null == newIndex) {
                log.warn("new index is null");
                return;
            }
            updateIndexFn.accept(newIndex);
        }

        // 是否为下跌
        public boolean isDown() {
            if (newIndex == null || index == null) {
                return false;
            }

            return newIndex.order < index.order;
        }

        // 是否为上涨
        public boolean isRise() {
            if (newIndex == null || index == null) {
                return false;
            }

            return newIndex.order > index.order;
        }

        // 是否不变
        public boolean isRemain() {
            if (newIndex == null || index == null) {
                return false;
            }

            return newIndex.order == index.order;
        }

    }

    private static class Node implements OrderedGird {

        public final int order;
        public final BigDecimal price;
        private Node pre;
        private Node next;

        public Node(int order, BigDecimal price) {
            this.order = order;
            this.price = price;
        }

        @Override
        public int order() {
            return order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equal(price, node.price) && Objects.equal(pre, node.pre) && Objects.equal(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(price, pre, next);
        }
    }

    private static class Builder {
        private Symbol symbol;
        // 投资金额
        private BigDecimal invest;
        // 网格底部价格
        private BigDecimal low;
        // 网格顶部价格
        private BigDecimal high;
        // 网格数量
        private int grids;
        // 账户用于计算手续费
        private Account account;

        public BigDecimal getInvest() {
            return invest;
        }

        public Builder setInvest(BigDecimal invest) {
            this.invest = invest;
            return this;
        }

        public BigDecimal getLow() {
            return low;
        }

        public Builder setLow(BigDecimal low) {
            this.low = low;
            return this;
        }

        public BigDecimal getHigh() {
            return high;
        }

        public Builder setHigh(BigDecimal high) {
            this.high = high;
            return this;
        }

        public int getGrids() {
            return grids;
        }

        public Builder setGrids(int grids) {
            this.grids = grids;
            return this;
        }

        public Account getAccount() {
            return account;
        }

        public Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        public void setSymbol(Symbol symbol) {
            this.symbol = symbol;
        }

        public LinkedGrids build() {
            GridsUtil.commonValidate(
                    symbol,
                    invest,
                    low,
                    high,
                    grids,
                    account.serviceChargeRate,
                    account.minimumOrderUSDTAmount
            );
            return new LinkedGrids(symbol, invest, low, high, grids);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

    }

}
