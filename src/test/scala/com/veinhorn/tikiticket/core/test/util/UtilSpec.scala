package com.veinhorn.tikiticket.core.test.util

import com.veinhorn.tikiticket.core.util.Util
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by veinhorn on 16.1.17.
  */
class UtilSpec extends FlatSpec with Matchers {
  it should "create ICredentials" in {
    val creds = Util.newCredentials("Boris", "Moris")
    creds.getLogin should equal("Boris")
    creds.getPassword should equal("Moris")
  }
}
