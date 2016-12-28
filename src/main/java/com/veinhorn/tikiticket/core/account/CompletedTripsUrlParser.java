package com.veinhorn.tikiticket.core.account;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by veinhorn on 26.12.16.
 */
public class CompletedTripsUrlParser implements DataParser<String> {
    @Override
    public String parse(String html) throws TikiTicketException {
        try {
            return Util.createUrl(fetchCompletedTripsUrl(Jsoup.parse(html)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse completed trips url", e);
        }
    }

    private String fetchCompletedTripsUrl(Document document) {
        return document.getElementsByClass("tab2").get(0).getElementsByTag("a").get(0).attr("href");

    }
}
