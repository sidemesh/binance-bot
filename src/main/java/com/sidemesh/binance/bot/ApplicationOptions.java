package com.sidemesh.binance.bot;

import java.math.BigDecimal;
import java.util.Optional;

public class ApplicationOptions {

    // -enable-local-proxy
    // 是否开启本地代理
    private boolean enableLocalProxy = true;

    // -enable-api-server
    // 是否开启 API 服务器
    private boolean enableApiServer = true;

    // symbol
    // 币种符号
    private Symbol symbol;

    // grids
    // 网格数量
    private Integer grids;

    // grid-open-price
    // 开仓价格
    private BigDecimal gridsOpenPrice;

    // grid-low-price
    // 网格底部
    private BigDecimal gridsLowPrice;

    // grid-high-price
    // 网格底部
    private BigDecimal gridsHighPrice;

    public static ApplicationOptions formEnv() {
        ApplicationOptions opts = new ApplicationOptions();

        var elp = System.getenv("ENABLE-LOCAL-PROXY");
        opts.enableLocalProxy = elp != null && !elp.isEmpty() && Boolean.parseBoolean(elp);
        /*

        var eas = System.getenv("ENABLE-API-SERVER");
        opts.enableApiServer = eas != null && !eas.isEmpty() && Boolean.parseBoolean(eas);

        opts.symbol = Symbol.valueOf(System.getenv("SYMBOL"));
        opts.grids = Integer.valueOf(System.getenv("GRIDS"));
        opts.gridsOpenPrice = new BigDecimal(System.getenv("GRIDS_OPEN_PRICE"));
        opts.gridsLowPrice = new BigDecimal(System.getenv("GRIDS_LOW_PRICE"));
        opts.gridsHighPrice = new BigDecimal(System.getenv("GRIDS_HIGH_PRICE"));

        opts.validate().ifPresent((reason) -> {
            System.out.println(reason);
            System.exit(0);
        });
        */

        return opts;
    }

    /**
     * 验证网格是否配置
     * @return 返回验证结果
     */
    public Optional<String> validate() {
        if (grids == null || grids <= 0) {
            return Optional.of("grid is require. and must > 0");
        }


        if (gridsOpenPrice == null) {
            return Optional.of("grid open price is require. and must > 0");
        }

        if (gridsLowPrice == null) {
            return Optional.of("grid low price is require. and must > 0");
        }

        if (gridsHighPrice == null) {
            return Optional.of("grid high price is require. and must > 0");
        }

        return Optional.empty();
    }

    public boolean isEnableLocalProxy() {
        return enableLocalProxy;
    }

    public boolean isEnableApiServer() {
        return enableApiServer;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Integer getGrids() {
        return grids;
    }

    public BigDecimal getGridsOpenPrice() {
        return gridsOpenPrice;
    }

    public BigDecimal getGridsLowPrice() {
        return gridsLowPrice;
    }

    public BigDecimal getGridsHighPrice() {
        return gridsHighPrice;
    }

}
