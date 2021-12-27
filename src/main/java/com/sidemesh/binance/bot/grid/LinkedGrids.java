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
        if (compared < 0) {
            // 📉下跌
            // 已为跌穿网格不进行任何操作
            if (index == head) return skipUpdate();
            return tryUpdateForDown(price, index);
        } else {
            // 📈上涨
            // 涨穿网格不进行任何操作
            if (index == tail) return skipUpdate();
            return tryUpdateForRise(price, index);
        }
    }

    /**
     * 递归调用下跌逻辑
     */
    private UpdateResult tryUpdateForDown(BigDecimal price, Node n) {
        if (null == n) return new UpdateResult(index, head, this::updateIndex);
        var compared = price.compareTo(n.price);
        if (compared >= 0) return new UpdateResult(index, n.next, this::updateIndex);
        return tryUpdateForDown(price, n.pre);
    }

    /**
     * 递归调用上涨逻辑
     */
    private UpdateResult tryUpdateForRise(BigDecimal price, Node n) {
        if (null == n) return new UpdateResult(index, tail, this::updateIndex);
        var compared =  price.compareTo(n.price);
        if (compared <= 0) return new UpdateResult(index, n.pre, this::updateIndex);
        return tryUpdateForRise(price, n.next);
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
        // 状态
        public final UpdateResultStatus status;

        private UpdateResult(@NotNull Node index,
                             @NotNull Node newIndex,
                             Consumer<Node> fn) {
            this.index = index;
            this.updateIndexFn = fn;
            this.newIndex = newIndex;

            if (index.order == newIndex.order) {
                this.status = UpdateResultStatus.REMAIN;
            } else if (index.order > newIndex.order) {
                this.status = UpdateResultStatus.DOWN;
            } else {
                this.status = UpdateResultStatus.RISE;
            }
        }

        public void updateIndex() {
            if (null == updateIndexFn) {
                log.warn("update index function is null");
                return;
            }
            updateIndexFn.accept(newIndex);
        }

        // 是否为下跌
        public boolean isDown() {
            return UpdateResultStatus.DOWN.equals(this.status);
        }

        // 是否为上涨
        public boolean isRise() {
            return UpdateResultStatus.RISE.equals(this.status);
        }

        // 是否不变
        public boolean isRemain() {
            return UpdateResultStatus.REMAIN.equals(this.status);
        }

    }

    public enum UpdateResultStatus {
        // 上涨
        RISE,
        // 下跌
        DOWN,
        // 保持
        REMAIN,
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
