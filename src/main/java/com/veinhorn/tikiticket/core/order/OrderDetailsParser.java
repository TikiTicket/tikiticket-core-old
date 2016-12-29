package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.DataParser;

/**
 * Created by veinhorn on 30.12.16.
 */
public class OrderDetailsParser implements DataParser<OrderDetails> {
    @Override
    public OrderDetails parse(String html) {
        return new OrderDetails();
    }
}
