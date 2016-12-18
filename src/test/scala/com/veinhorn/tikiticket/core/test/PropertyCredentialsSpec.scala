package com.veinhorn.tikiticket.core.test

import com.veinhorn.tikiticket.core.impl.PropertyCredentials
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by veinhorn on 18.12.16.
  */
class PropertyCredentialsSpec extends FlatSpec with Matchers {
  it should "test this part of code" in {
    val creds = new PropertyCredentials()
    creds.getLogin should not equal null
    creds.getPassword should not equal null
  }
}
