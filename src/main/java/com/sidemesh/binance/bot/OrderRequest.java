package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.json.JSON;
import com.sidemesh.binance.bot.util.StringEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class OrderRequest extends JSON.ToJson {

    // 默认窗口 5s
    private final static long DEFAULT_RC_WINDOW = 5000L;

    // 唯一ID
    public final String newClientOrderId;
    public final Symbol symbol;
    public final Side side;
    public final Type type;
    public final BigDecimal price;
    public final BigDecimal quantity;
    public final Long recvWindow;
    public final Long timestamp;
    public final RespType newOrderRespType;

    public OrderRequest(String newClientOrderId,
                        Symbol symbol,
                        Side side,
                        Type type,
                        BigDecimal price,
                        BigDecimal quantity,
                        Long recvWindow,
                        Long timestamp) {
        this.newClientOrderId = newClientOrderId;
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.recvWindow = recvWindow;
        this.timestamp = timestamp;
        this.newOrderRespType = RespType.FULL;
    }

    public String toUrlParams() {
        return "newClientOrderId=%s" + this.newClientOrderId + "&"+
                "symbol=" + this.symbol.toUpperCaseStr() + "&" +
                "side=" + this.side.side + "&" +
                // 需要避免科学记数法，注意此处不移除末尾小数 0
                "price=" + this.price.toPlainString() + "&" +
                "quantity=" + this.quantity.toPlainString() + "&" +
                "recvWindow=" + this.recvWindow + "&" +
                "newOrderRespType=" + this.newOrderRespType + "&" +
                "timestamp=" + this.timestamp;
    }

    public static LimitOrderBuilder newLimitOrderBuilder() {
        return new LimitOrderBuilder();
    }

    /**
     * 限价订单请求
     */
    public static class LimitOrderBuilder {

        private String id;
        // 币对
        private Symbol symbol;
        // 购买或卖出
        private Side side;
        // 数量
        private BigDecimal quantity;
        // 价格
        private BigDecimal price;
        // 时间戳
        private Long timestamp;
        // 滑动窗口
        private Long rcwindow;

        public LimitOrderBuilder() {}

        public LimitOrderBuilder symbol(Symbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public LimitOrderBuilder side(Side side) {
            this.side = side;
            return this;
        }

        public LimitOrderBuilder buy() {
            return side(Side.BUY);
        }

        public LimitOrderBuilder sell() {
            return side(Side.SELL);
        }

        public LimitOrderBuilder quantity(BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public LimitOrderBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public LimitOrderBuilder timestamp(long tz) {
            this.timestamp = tz;
            return this;
        }

        public LimitOrderBuilder rcwindow(long rcw) {
            this.rcwindow = rcw;
            return this;
        }

        public LimitOrderBuilder id(String id) {
            this.id = id;
            return this;
        }

        public OrderRequest build() {
            check(symbol, "symbol");
            check(side, "side");
            check(price, "price");
            check(quantity, "quantity");

            return new OrderRequest(
                    // 生成唯一的UUID
                    null == id ? UUID.randomUUID().toString() : id,
                    symbol,
                    side,
                    // 仅支持限价单
                    Type.LIMIT,
                    price,
                    quantity,
                    null == rcwindow ? OrderRequest.DEFAULT_RC_WINDOW : rcwindow,
                    null == timestamp ? new Date().getTime() : timestamp
            );
        }

        private void check(Object o, String field) {
            if (o == null) {
                throw new IllegalArgumentException("missing " + field);
            }
        }

    }

    /**
     * 下单类型
     * 有关于下单类型的介绍 https://www.binance.com/zh-CN/support/faq/360033779452
     */
    public enum Type {
        // 目前仅支持限价单
        LIMIT("LIMIT", "限价单");
        /*
        MARKET("MARKET", "市价单"),
        LIMIT_MAKER("LIMIT_MAKER", "限价只挂单");
         */

        public final String str;
        public final String name;

        Type(String str, String name) {
            this.str = str;
            this.name = name;
        }
    }

    /**
     * 订单多久能够失效
     */
    public enum TimeInForce implements StringEnum<TimeInForce> {
        /*
         * 成交为止，订单会一直有效，直到被成交或者取消。
         */
        GTC("GTC"),

        /*
         * 无法立即成交的部分就撤销，订单在失效前会尽量多的成交。
         */
        IOC("IOC"),

        /*
         * 无法全部立即成交就撤销，如果无法全部成交，订单会失效。
         */
        FOK("FOK");

        public final String str;

        TimeInForce(String str) {
            this.str = str;
        }


        @Override
        public String getStr() {
            return this.str;
        }

        @Override
        public TimeInForce[] getValues() {
           return values();
        }
    }

    /**
     * 购买侧或卖出侧
     */
    public enum Side {
        /**
         * 购买
         */
        BUY("BUY"),

        /**
         * 卖出
         */
        SELL("SELL");

        public final String side;

        Side(String side) {
            this.side = side;
        }
    }

    /**
     * 订单返回类型
     * 不同的类型接口返回的信息不同
     */
    public enum RespType {

        ACK("ACK"),
        RESULT("RESULT"),
        FULL("FULL");

        public final String STR;

        RespType(String str) {
            this.STR= str;
        }
    }

}
