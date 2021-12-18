package com.sidemesh.binance.bot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {

    private Symbol symbol;
    private String orderId;
    //  OCO订单ID，否则为 -1
    private Integer orderListId;
    private String clientOrderId;
    private Long transactTime;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
    private Status status;
    private OrderRequest.TimeInForce timeInForce;
    private OrderRequest.Type type;
    private OrderRequest.Side side;

    public boolean isSuccess() {
        return Status.FILLED == this.status;
    }

    public enum Status {
        // 订单被交易引擎接受
        NEW("NEW"),
        // 部分订单被成交
        PARTIALLY_FILLED("PARTIALLY_FILLED"),
        // 用户撤销了订单
        CANCELED("CANCELED"),
        // 订单完全成交
        FILLED("FILLED"),
        // 撤销中(目前并未使用)
        PENDING_CANCEL("PENDING_CANCEL"),
        // 订单没有被交易引擎接受，也没被处理
        REJECTED("REJECTED"),
        // 订单被交易引擎取消, 比如
        //LIMIT FOK 订单没有成交
        //市价单没有完全成交
        //强平期间被取消的订单
        //交易所维护期间被取消的订单
        EXPIRED("EXPIRED")
        ;

        public final String str;

        Status(String str) {
            this.str = str;
        }

        @JsonValue
        public String jsonValue() {
            return this.str;
        }

        @JsonCreator
        public Status jsonCreator(String input) {
            for (var v : values()) {
                if (v.str.equals(input)) {
                    return v;
                }
            }
            throw new IllegalArgumentException("input: [" + input + "] can't deserialization");
        }
    }

}
