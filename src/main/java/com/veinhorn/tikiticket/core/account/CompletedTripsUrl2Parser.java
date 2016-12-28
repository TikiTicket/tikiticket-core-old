package com.veinhorn.tikiticket.core.account;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Util;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 26.12.16.
 */
public class CompletedTripsUrl2Parser implements DataParser<List<String>> {
    @Override
    public List<String> parse(String html) throws TikiTicketException {
        try {
            List<String> list = new ArrayList<>();
            list.add(Util.createUrl(Jsoup.parse(html).getElementsByTag("form").get(0).attr("action")));
            list.add(Jsoup.parse(html).getElementById("com.sun.faces.VIEW").val());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannt parse 2nd trips url", e);
        }
    }
}
