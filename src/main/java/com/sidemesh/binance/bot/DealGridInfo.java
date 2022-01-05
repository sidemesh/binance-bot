package com.sidemesh.binance.bot;

import com.google.common.collect.Lists;
import com.sidemesh.binance.bot.grid.OrderedGird;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DealGridInfo {

    @Getter
    private final List<DealGrid> dealGridList;

    public DealGridInfo() {
        dealGridList = Lists.newLinkedList();
    }

    public DealGridInfo(List<DealGrid> dealGridList) {
        this.dealGridList = dealGridList;
    }

    /**
     * 当买入网格
     * @param buyGrid 买入时的网格
     * @param price 买入价格
     * @param quantity 数量
     * @return DealGridInfo
     */
    public DealGrid onBuy(OrderedGird buyGrid, BigDecimal price, BigDecimal quantity) {
        DealGrid dealGrid = new DealGrid(buyGrid, price, quantity);
        dealGridList.add(dealGrid);
        return dealGrid;
    }

    /**
     * 打包可以卖出的持仓
     * @param curr 支持判断的当前网格
     * @return 打包的持仓
     */
    public Optional<SellPacked> packCanSells(OrderedGird curr) {
        var list = dealGridList.stream()
                .filter(it -> curr.order() > it.grid.order())
                .collect(Collectors.toList());
        return list.isEmpty() ? Optional.empty() : Optional.of(new SellPacked(list));
    }

    /**
     * 已卖出打包的持仓
     * @param packed 打包的持仓
     */
    public void onSell(SellPacked packed) {
        for (var g : packed.grids) {
            dealGridList.remove(g);
        }
    }

    public static class SellPacked {

        private final List<DealGrid> grids;
        // private boolean isSell = false;

        public SellPacked(List<DealGrid> grids) {
            this.grids = grids;
        }

        public BigDecimal quantity() {
            return this.grids.stream()
                    .map(DealGrid::getQuantity)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        public BigDecimal totalPrice() {
            return grids.stream().map(DealGrid::totalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

    }

    @Getter
    public static class DealGrid {
        public final OrderedGird grid;
        public final BigDecimal price;
        public final BigDecimal quantity;

        public DealGrid(OrderedGird grid, BigDecimal price, BigDecimal quantity) {
            if (grid == null) throw new IllegalArgumentException("missing grid");
            if (price == null) throw new IllegalArgumentException("missing price");
            if (quantity == null) throw new IllegalArgumentException("missing quantity");
            this.grid = grid;
            this.price = price;
            this.quantity = quantity;
        }

        public BigDecimal totalPrice() {
            return price.multiply(quantity);
        }

    }
}

