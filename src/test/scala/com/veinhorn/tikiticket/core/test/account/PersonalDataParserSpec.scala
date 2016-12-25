package com.veinhorn.tikiticket.core.test.account

import com.veinhorn.tikiticket.core.account.PersonalDataParser
import com.veinhorn.tikiticket.core.test.ParserFlatSpec

/**
  * Created by veinhorn on 21.12.16.
  */
class PersonalDataParserSpec extends ParserFlatSpec {
  it should "test personal data parser" in {
    val pData = new PersonalDataParser().parse(loadHtml("registration-data.html"))
    val ok = "ok"
  }
}
