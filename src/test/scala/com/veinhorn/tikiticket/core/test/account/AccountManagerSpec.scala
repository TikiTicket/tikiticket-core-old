package com.veinhorn.tikiticket.core.test.account

import com.veinhorn.tikiticket.core.account.AccountManager
import com.veinhorn.tikiticket.core.api.IAccountManager
import com.veinhorn.tikiticket.core.test.impl.HttpClientConnector
import org.apache.http.impl.client.HttpClients
import org.scalatest.FlatSpec

import scala.collection.mutable

/**
  * Created by veinhorn on 18.12.16.
  */
class AccountManagerSpec extends FlatSpec {
  val customConnector = new HttpClientConnector(HttpClients.createDefault())

  it should "test account manager" in {
    val accountManager: IAccountManager = new AccountManager(customConnector)
    val list = mutable.MutableList(accountManager.getPersonalData())
    (1 to 3).foreach { _ => list += accountManager.getPersonalData }
    val ok = "ok"
  }
}
