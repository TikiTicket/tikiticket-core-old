package com.veinhorn.tikiticket.core.test.order

import com.veinhorn.tikiticket.core.api.IOrderManager
import com.veinhorn.tikiticket.core.order.OrderManager
import com.veinhorn.tikiticket.core.test.impl.HttpClientConnector
import org.apache.http.impl.client.HttpClients
import org.scalatest.{FlatSpec, Ignore, Matchers}

/**
  * Created by veinhorn on 23.12.16.
  */
class OrderManagerSpec extends FlatSpec with Matchers {
  val connector = new HttpClientConnector(HttpClients.createDefault())

  it should "test current" in {
    val orderManager: IOrderManager = new OrderManager(connector)
    val currentTrips = orderManager.retrieveCurrentTrips()
    val ok = ""
  }

  it should "test completed trips retrieving without specified range" in {
    val orderManager: IOrderManager = new OrderManager(connector)
    val completedTrips = orderManager.retrieveCompletedTrips(null, null)
    completedTrips should have length 20
  }

  it should "test trip details retrieving" in {
    val orderManager: IOrderManager = new OrderManager(connector)
    val completedTrips = orderManager.retrieveCompletedTrips(null, null)
    completedTrips should have length 20
    val tripDetails = orderManager.retrieveTripDetails(completedTrips.get(0))
  }
}
