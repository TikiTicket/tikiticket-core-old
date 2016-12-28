package com.veinhorn.tikiticket.core.auth;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by veinhorn on 21.12.16.
 */
public class AuthUrlParser implements DataParser<String> {
    @Override
    public String parse(String html) throws TikiTicketException {
        try {
            Document document = Jsoup.parse(html);
            return Util.createUrl(document.getElementById("login").attr("action"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse auth url", e);
        }
    }
}
