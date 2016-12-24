package com.veinhorn.tikiticket.core.test.order

import com.veinhorn.tikiticket.core.api.IOrderManager
import com.veinhorn.tikiticket.core.order.OrderManager
import com.veinhorn.tikiticket.core.test.impl.HttpClientConnector
import org.apache.http.impl.client.HttpClients
import org.scalatest.FlatSpec

/**
  * Created by veinhorn on 23.12.16.
  */
class OrderManagerSpec extends FlatSpec {
  val customConnector = new HttpClientConnector(HttpClients.createDefault())

  it should "test order manager" in {
    val orderManager: IOrderManager = new OrderManager(customConnector)
    val currentOrdes = orderManager.getCurrentOrders
    val ok = "ok"
  }
}
