package com.sidemesh.binance.bot;

import com.google.common.collect.Lists;
import com.sidemesh.binance.bot.grid.Grid;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DealGridInfo {

    private final List<DealGrid> dealGridList = Lists.newLinkedList();

    /**
     * 当买入网格
     * @param buyGrid 买入时的网格
     * @param price 买入价格
     * @param quantity 数量
     * @return DealGridInfo
     */
    public DealGrid onBuy(Grid buyGrid, BigDecimal price, BigDecimal quantity) {
        DealGrid dealGrid = new DealGrid(buyGrid, price, quantity);
        dealGridList.add(dealGrid);
        return dealGrid;
    }

    /**
     * 打包可以卖出的持仓
     * @param currGrid 支持判断的当前网格
     * @return 打包的持仓
     */
    public Optional<SellPacked> packCanSells(Grid currGrid) {
        var list = dealGridList.stream()
                .filter(dealGrid -> currGrid.getOrder() > (dealGrid.buyGrid.getOrder() + 1))
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

    @AllArgsConstructor
    @Getter
    public static class DealGrid {
        public final Grid buyGrid;
        public final BigDecimal price;
        public final BigDecimal quantity;

        public BigDecimal totalPrice() {
            return price.multiply(quantity);
        }

    }
}

