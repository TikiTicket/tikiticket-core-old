package com.veinhorn.tikiticket.core.test.account

import com.veinhorn.tikiticket.core.account.{AccountManager, IAccountManager}
import com.veinhorn.tikiticket.core.test.impl.{HttpClientConnector, PropertyCredentials}
import org.apache.http.impl.client.HttpClients
import org.scalatest.FlatSpec

/**
  * Created by veinhorn on 18.12.16.
  */
class AccountManagerSpec extends FlatSpec {
  val httpClient = HttpClients.createDefault()
  val customConnector = new HttpClientConnector(httpClient)
  val creds = new PropertyCredentials()

  it should "test account manager" in {
    val accountManager: IAccountManager = new AccountManager(customConnector)
    val personalData = accountManager.getPersonalData(creds)
    val ok = "ok"
  }
}
