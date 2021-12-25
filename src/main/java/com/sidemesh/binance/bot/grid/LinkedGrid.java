package com.sidemesh.binance.bot.grid;

import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;

/**
 * 当网格数较大时应使用二分法
 */
public class LinkedGrid {

    // 网格间距价格
    private final BigDecimal segment;

    // 头
    private final Node head;
    // 尾
    private final Node tail;

    /**
     * 上一次标记的价格点
     */
    private Node index = null;

    public LinkedGrid(BigDecimal low, BigDecimal high, int grids) {
        if (high.compareTo(low) <= 0) {
            throw new IllegalArgumentException("low price must < high price. low price = " + low + " high price = " + high);
        }

        if (grids <= 1) {
            throw new IllegalArgumentException("grids must > 1. girds = " + grids);
        }

        var segment = this.segment = high.subtract(low).divide(new BigDecimal(grids), RoundingMode.HALF_UP);
        var n = this.head = new Node(low);

        for (var i = 1; i < grids; i++) {
            var node = new Node(n.price.add(segment));
            n.next = node;
            node.pre = n;
            n = node;
        }

        this.tail = n;
    }

    /**
     * 如果网格未进行初始化则进行初始化，并不支持更新 index
     * 如果网格已进行初始化，则按照 index 寻找：
     * 如果价格与当前 index 相等则不返回 callback
     */
    public IndexUpdateCallback update(BigDecimal price) {
        if (null == index) {
            init(price);
            return null;
        }

        var compared = index.price.compareTo(price);
        if (compared == 0) {
            return null;
        }

        // 下跌
        if (compared < 0) {
            // 跌穿网格不进行任何操作
            if (index == head) {
                return null;
            }
            var n = index.pre;
            while (true) {
                // 比较是否
                compared = n.price.compareTo(price);
            }
        }

        // 上涨

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
        while (true) {
            var compared = n.price.compareTo(price);
            if (compared >= 0) {
                break;
            }
            n = n.next;
        }
        index = n;
    }

    public static class IndexUpdateCallback {

        private final Consumer<Node> fn;
        private final Node index;

        private IndexUpdateCallback(Consumer<Node> fn, Node node) {
            this.fn = fn;
            this.index = node;
        }

        public void update() {
            fn.accept(this.index);
        }

    }

    public static class Node {

        public final BigDecimal price;
        public Node pre;
        public Node next;

        public Node(BigDecimal price) {
            this.price = price;
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

}
