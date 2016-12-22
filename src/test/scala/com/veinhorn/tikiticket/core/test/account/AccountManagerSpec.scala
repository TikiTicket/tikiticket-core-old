package com.veinhorn.tikiticket.core.test.account

import com.veinhorn.tikiticket.core.account.{AccountManager, IAccountManager}
import com.veinhorn.tikiticket.core.test.impl.HttpClientConnector
import org.apache.http.impl.client.HttpClients
import org.scalatest.FlatSpec

/**
  * Created by veinhorn on 18.12.16.
  */
class AccountManagerSpec extends FlatSpec {
  val customConnector = new HttpClientConnector(HttpClients.createDefault())

  it should "test account manager" in {
    val accountManager: IAccountManager = new AccountManager(customConnector)
    val personalData = accountManager.getPersonalData()
    val ok = "ok"
  }
}
