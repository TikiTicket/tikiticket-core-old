package com.veinhorn.tikiticket.core.test.order

import com.veinhorn.tikiticket.core.order.CurrentOrdersParser
import com.veinhorn.tikiticket.core.test.ParserFlatSpec

/**
  * Created by veinhorn on 25.12.16.
  */
class CurrentOrdersParserSpec extends ParserFlatSpec {
  it should "test current orders parser" in {
    val currentOrders = new CurrentOrdersParser().parse(loadHtml("2-orders.html"))
    val ok = "ok"
  }
}
