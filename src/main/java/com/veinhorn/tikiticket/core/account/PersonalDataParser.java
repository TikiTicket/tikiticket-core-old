package com.veinhorn.tikiticket.core.account;

import com.veinhorn.tikiticket.core.DataParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by veinhorn on 21.12.16.
 */
public class PersonalDataParser implements DataParser<IPersonalData> {
    public IPersonalData parse(String html) {
        Document document = Jsoup.parse(html);
        Element userData = document.getElementById("userReg");
        // login.getElementsByTag("input").attr("value")
        Element login = userData.getElementsByTag("td").get(2);
        String test = "ok2";
        String test2 = "ok";
        return new PersonalData("Belarus");
    }
}
