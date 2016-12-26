package com.veinhorn.tikiticket.core.auth;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by veinhorn on 21.12.16.
 */
public class AuthUrlParser implements DataParser<String> {
    @Override
    public String parse(String html) {
        Document document = Jsoup.parse(html);
        return Util.createUrl(document.getElementById("login").attr("action"));
/*
        return createAuthUrl(document.getElementById("login").attr("action"));
*/
    }

    /*private String createAuthUrl(String url) {
        return Constants.BASE_URL + url;
    }*/
}
