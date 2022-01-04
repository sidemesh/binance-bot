package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

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
    private Node index;

    private boolean isInit = false;

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
        // 预初始化，保证初始化算法可以完成
        this.index = head;
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
    public IndexResult findIndex(final BigDecimal price) {
        final var compared = price.compareTo(index.price);

        // 🎯网格没有任何变化
        if (compared == 0) return skip();
        if (compared < 0) {
            // 📉下跌
            // 已为跌穿网格不进行任何操作
            if (index == head) return skip();
            return findIndexForDown(price, index);
        } else {
            // 📈上涨
            // 涨穿网格不进行任何操作
            if (index == tail) return skip();
            return findIndexForRise(price, index);
        }
    }

    /**
     * 递归调用下跌逻辑
     */
    private IndexResult findIndexForDown(final BigDecimal price, final Node n) {
        if (null == n) return newIndexResult(head);
        final var compared = price.compareTo(n.price);
        if (compared > 0) return newIndexResult(n.next);
        if (compared == 0) return newIndexResult(n);
        return findIndexForDown(price, n.pre);
    }

    /**
     * 递归调用上涨逻辑
     */
    private IndexResult findIndexForRise(final BigDecimal price, final Node n) {
        if (null == n) return newIndexResult(tail);
        final var compared =  price.compareTo(n.price);
        if (compared < 0) return newIndexResult(n.pre);
        if (compared == 0) return newIndexResult(n);
        return findIndexForRise(price, n.next);
    }

    /**
     * 更新索引
     * @param node 新的索引
     */
    private void updateIndex(Node node) {
        if (node != null && node != this.index) this.index = node;
    }

    /**
     * 跳过更新，new index 和 index 都为当前 index
     */
    private IndexResult skip() {
        return newIndexResult(index);
    }

    private IndexResult newIndexResult(Node newIndex) {
        return new IndexResult(index, newIndex, this::updateIndex);
    }

    /**
     * 当价格小于等于网格 low price 直接设置为 low price index node
     * 当价格大于等于网格 high price 直接设置为 high price index node
     * 当价格在网格区间，则按照 low level 进行寻找选中。
     */
    public void init(BigDecimal price) {
        if (isInit()) throw new RuntimeException("already init!");
        this.findIndex(price).updateIndex();
        this.isInit = true;
    }

    public boolean isInit() {
        return this.isInit;
    }

    /**
     * 尝试进行初始化
     * 如果没有初始化则进行初始化。
     * @param price 价格
     */
    public void try2Init(BigDecimal price) {
        if (isInit()) return;
        init(price);
    }

    @Slf4j
    public static class IndexResult {
        // 回调函数
        private final Consumer<Node> updateIndexFn;
        // 新的游标
        public final Node newIndex;
        // 当前的游标
        public final Node index;
        // 状态
        public final IndexResultStatus status;

        private boolean isUpdatedIndex;

        private IndexResult(@NotNull Node index,
                            @NotNull Node newIndex,
                            Consumer<Node> fn) {
            this.index = index;
            this.updateIndexFn = fn;
            this.newIndex = newIndex;

            if (index.order == newIndex.order) {
                this.status = IndexResultStatus.REMAIN;
            } else if (index.order > newIndex.order) {
                this.status = IndexResultStatus.DOWN;
            } else {
                this.status = IndexResultStatus.RISE;
            }
            this.isUpdatedIndex = false;
        }

        public void updateIndex() {
            if (null == updateIndexFn) {
                log.warn("update index function is null");
                return;
            }
            updateIndexFn.accept(newIndex);
            isUpdatedIndex = true;
        }

        public boolean isUpdatedIndex() {
            return isUpdatedIndex;
        }

        // 是否为下跌
        public boolean isDown() {
            return IndexResultStatus.DOWN.equals(this.status);
        }

        // 是否为上涨
        public boolean isRise() {
            return IndexResultStatus.RISE.equals(this.status);
        }

        // 是否不变
        public boolean isRemain() {
            return IndexResultStatus.REMAIN.equals(this.status);
        }

        // index 之间的偏移量
        public BigDecimal orderOffset() {
            return new BigDecimal(Math.abs(index.order - newIndex.order));
        }

    }

    public enum IndexResultStatus {
        // 上涨
        RISE,
        // 下跌
        DOWN,
        // 保持
        REMAIN,
    }

    public static class Node implements OrderedGird {

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
