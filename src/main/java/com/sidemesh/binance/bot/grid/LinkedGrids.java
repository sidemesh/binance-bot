package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Symbol;
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
        for (var i = 0; i < info.grids; i++) {
            var node = new Node(n.order + 1, n.price.add(info.stepAmount));
            n.next = node;
            node.pre = n;
            n = node;
        }

        this.tail = n;
    }

    LinkedGrids(Symbol symbol, BigDecimal invest, BigDecimal low, BigDecimal high, int grids) {
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

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    /**
     * 如果网格未进行初始化则进行初始化，并不支持更新 index
     * 如果网格已进行初始化，则按照 index 寻找：
     * 如果价格与当前 index 相等则不返回 callback
     */
    public UpdateResult tryUpdate(BigDecimal price) {
        var compared = price.compareTo(index.price);

        // 🎯网格没有任何变化
        if (compared == 0) return skipUpdate();

        // 📉下跌
        if (compared < 0) {
            // 已为跌穿网格不进行任何操作
            if (index == head) return skipUpdate();
            return tryUpdateForDown(price, index);
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

        // 📈上涨
        // 涨穿网格不进行任何操作
        if (index == tail) return skipUpdate();
        return tryUpdateForRise(price, index);
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

    private UpdateResult tryUpdateForDown(BigDecimal price, Node n) {
        if (null == n) return new UpdateResult(index, head, this::updateIndex);
        var compared = price.compareTo(n.price);
        if (compared >= 0) return new UpdateResult(index, n, this::updateIndex);
        return tryUpdateForDown(price, n.pre);
    }

    private UpdateResult tryUpdateForRise(BigDecimal price, Node n) {
        if (null == n) return new UpdateResult(index, tail, this::updateIndex);
        var compared =  price.compareTo(n.price);
        if (compared <= 0) return new UpdateResult(index, n, this::updateIndex);
        return tryUpdateForRise(price, n.next);
    }

    private void updateIndex(Node node) {
        this.index = node;
    }

    private UpdateResult skipUpdate() {
        return new UpdateResult(index, index, null);
    }

    /**
     * 当价格小于等于网格 low price 直接设置为 low price index node
     * 当价格大于等于网格 high price 直接设置为 high price index node
     * 当价格在网格区间，则按照 low level 进行寻找选中。
     */
    public void init(BigDecimal price) {
        if (isInit()) throw new RuntimeException("already init!");

        if (price.compareTo(head.price) <= 0) {
            index = head;
            return;
        }
        
        if (price.compareTo(tail.price) >= 0) {
            index = tail;
            return;
        }
        
        var n = head;
        var selected = n;
        while (n != null) {
            var compared = price.compareTo(n.price);
            if (compared == 0) {
                selected = n;
                break;
            }
            if (compared > 0) {
                selected = n;
                n = n.next;
                continue;
            }
            break;
        }
        index = selected;
    }

    public boolean isInit() {
        return this.index != null;
    }

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

    }

}
