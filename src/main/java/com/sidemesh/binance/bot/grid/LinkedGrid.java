package com.sidemesh.binance.bot.grid;

import com.google.common.base.Objects;
import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.util.GridsUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * 当网格数较大时应使用二分法
 */
public class LinkedGrid {

    // 头
    private final Node head;
    // 尾
    private final Node tail;

    /**
     * 上一次标记的价格点
     */
    private Node index = null;

    private LinkedGrid(BigDecimal low, BigDecimal high, int grids) {
        if (high.compareTo(low) <= 0) {
            throw new IllegalArgumentException("low price must < high price. low price = " + low + " high price = " + high);
        }

        if (grids <= 1) {
            throw new IllegalArgumentException("grids must > 1. girds = " + grids);
        }

        var segment = GridsUtil.computeGirdsSegment(low, high, grids);
        var n = this.head = new Node(0, low);

        for (var i = 1; i < grids; i++) {
            var node = new Node(i, n.price.add(segment));
            n.next = node;
            node.pre = n;
            n = node;
        }

        this.tail = n;
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
        if (null == index) {
            init(price);
            return skipUpdate();
        }

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
            var n = index.pre;
            while (n != null) {
                compared = n.price.compareTo(price);
                if (compared <= 0) {
                    n = n.pre;
                } else {
                    break;
                }
            }
            // 穿过网格
            assert n != null;
            return new UpdateResult(node -> this.index = node, n);
        }

        /*
           📈上涨
         */
        // 涨穿网格不进行任何操作
        if (index == tail) return skipUpdate();
        var n = index.next;
        while (n != null) {
            compared = n.price.compareTo(price);
            if (compared >= 0) {
                n = n.next;
            } else {
                break;
            }
        }
        assert n != null;
        return new UpdateResult(node -> this.index = node, n);
    }

    private UpdateResult skipUpdate() {
        return UpdateResult.skip(index);
    }

    /**
     * 当价格小于等于网格 low price 直接设置为 low price index node
     * 当价格大于等于网格 high price 直接设置为 high price index node
     * 当价格在网格区间，则按照 low level 进行寻找选中。
     */
    private void init(BigDecimal price) {
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

    /*
     * new index 永远不会为 null
     */
    @Slf4j
    public static class UpdateResult {
        // 回调函数
        private final Consumer<Node> updateIndexFn;
        // 新的索引
        public final Node newIndex;

        private UpdateResult(Consumer<Node> fn, @NotNull Node newIndex) {
            this.updateIndexFn = fn;
            this.newIndex = newIndex;
        }

        public void updateIndex() {
            if (null == updateIndexFn) {
                log.warn("update index function is null");
                return;
            }
            updateIndexFn.accept(newIndex);
        }

        private static UpdateResult skip(Node index) {
            return new UpdateResult(null, index);
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

        public LinkedGrid build() {
            GridsUtil.commonValidate(
                    invest,
                    low,
                    high,
                    grids,
                    account.serviceChargeRate,
                    account.minimumOrderUSDTAmount
            );
            return new LinkedGrid(low, high, grids);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

    }

}
