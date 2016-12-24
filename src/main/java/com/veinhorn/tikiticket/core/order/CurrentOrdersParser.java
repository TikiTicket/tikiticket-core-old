package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.api.IOrder;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 23.12.16.
 */
public class CurrentOrdersParser implements DataParser<List<IOrder>> {
    @Override
    public List<IOrder> parse(String html) throws TikiTicketException {
        Document document = Jsoup.parse(html);
        Element orderElm = document.getElementsByClass("information").get(0);

        return new ArrayList<>();
    }
}
