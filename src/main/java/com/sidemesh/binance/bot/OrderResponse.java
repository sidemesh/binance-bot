package com.sidemesh.binance.bot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
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

    public enum Status {

        FILLED("FILLED");

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
