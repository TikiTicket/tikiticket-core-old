package com.veinhorn.tikiticket.core.test.order

import com.veinhorn.tikiticket.core.api.{IOrder, IOrderManager}
import com.veinhorn.tikiticket.core.order.OrderManager
import com.veinhorn.tikiticket.core.test.impl.HttpClientConnector
import org.apache.http.impl.client.HttpClients
import org.scalatest.FlatSpec

import scala.collection.JavaConversions._

/**
  * Created by veinhorn on 23.12.16.
  */
class OrderManagerSpec extends FlatSpec {
  val customConnector = new HttpClientConnector(HttpClients.createDefault())

  it should "test order manager" in {
    /*val orderManager: IOrderManager = new OrderManager(customConnector)
    val currentOrdes = orderManager.retrieveCurrentTrips
    val ok = "ok"*/

    /*val orderManager: IOrderManager = new OrderManager(customConnector)
    val orderDetails = orderManager.retrieveTripDetails*/

    var orderManager: IOrderManager = new OrderManager(customConnector)

    val orders: List[IOrder] = orderManager.retrieveCompletedTrips(null, null).toList
    val context = customConnector.getContextHolder
    val ok = "ok"

    val order = orderManager.retrieveTripDetails(orders.head)
  }
}
