package com.veinhorn.tikiticket.core.test.auth

import com.veinhorn.tikiticket.core.api.IAuthManager
import com.veinhorn.tikiticket.core.auth.AuthManager
import com.veinhorn.tikiticket.core.test.impl.{HttpClientConnector, PropertyCredentials}
import com.veinhorn.tikiticket.core.util.Util
import org.apache.http.impl.client.HttpClients
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by veinhorn on 17.1.17.
  */
class AuthManagerSpec extends FlatSpec with Matchers {

  it should "validate user credentials" in {
    val connector = new HttpClientConnector(HttpClients.createDefault())
    val authManger: IAuthManager = new AuthManager(connector)
    authManger.isValidCredentials(new PropertyCredentials) should equal(true)
  }

  it should "fail during user credentials validation" in {
    val connector = new HttpClientConnector(HttpClients.createDefault())
    val authManager: IAuthManager = new AuthManager(connector)
    val creds = Util.newCredentials("Vasiliy", "228")
    authManager.isValidCredentials(creds) should equal(false)
  }
}
