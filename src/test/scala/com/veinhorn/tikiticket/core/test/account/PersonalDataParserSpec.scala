package com.veinhorn.tikiticket.core.test.account

import java.nio.file.{Files, Paths}

import com.veinhorn.tikiticket.core.account.PersonalDataParser
import org.scalatest.FlatSpec

/**
  * Created by veinhorn on 21.12.16.
  */
class PersonalDataParserSpec extends FlatSpec {
  it should "test personal data parser" in {
    val html = new String(Files.readAllBytes(Paths.get("html/registration-data.html")))
    val pData = new PersonalDataParser().parse(html)
    val ok = "ok"
  }
}
