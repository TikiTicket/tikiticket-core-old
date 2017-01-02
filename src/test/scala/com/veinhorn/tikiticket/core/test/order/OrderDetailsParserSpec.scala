package com.veinhorn.tikiticket.core.test.order

import com.veinhorn.tikiticket.core.api.IOrderDetails
import com.veinhorn.tikiticket.core.order.OrderDetailsParser
import com.veinhorn.tikiticket.core.test.ParserFlatSpec

/**
  * Created by veinhorn on 2.1.17.
  */
class OrderDetailsParserSpec extends ParserFlatSpec {
  it should "test order details parser" in {
    val orderDetails: IOrderDetails = new OrderDetailsParser().parse(loadHtml("order-description.html"))
    val ok = "ok"
  }
}
