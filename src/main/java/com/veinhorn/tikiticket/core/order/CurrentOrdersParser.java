package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.api.IOrder;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 23.12.16.
 */
public class CurrentOrdersParser implements DataParser<List<IOrder>> {
    @Override
    public List<IOrder> parse(String html) throws TikiTicketException {
        try {
            return parse(Jsoup.parse(html));
        } catch (Exception e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse current orders", e);
        }
    }

    private List<IOrder> parse(Document document) throws Exception {
        Element ordersTable = document.getElementsByClass("information").get(0);
        Elements rawOrders = fetchRawOrders(ordersTable);

        return parseOrders(rawOrders);
    }

    private List<IOrder> parseOrders(Elements rawOrders) throws Exception {
        List<IOrder> orders = new ArrayList<>();

        for (Element rawOrder : rawOrders) {
            orders.add(parseOrder(rawOrder));
        }

        if (orders.isEmpty()) throw new Exception("Orders table is empty");
        return orders;
    }

    // Parse specific order
    private IOrder parseOrder(Element rawOrder) {
        Elements trElms = rawOrder.getElementsByTag("td");

        Order order = new Order();
        order.setOrderNumber(trElms.get(0).text());
        order.setOrderDate(trElms.get(1).text());
        order.setTripDate(trElms.get(2).text());
        order.setDispatchStation(trElms.get(3).text());
        order.setDestinationStation(trElms.get(4).text());
        order.setTrainNumber(trElms.get(5).text());
        order.setNumberOfSeats(trElms.get(6).text());
        order.setPrice(trElms.get(7).text());
        order.setEc(trElms.get(8).text());

        return order;
    }

    private Elements fetchRawOrders(Element ordersTable) {
        Elements rawOrders = new Elements();
        rawOrders.addAll(ordersTable.getElementsByClass("rowClass1"));
        rawOrders.addAll(ordersTable.getElementsByClass("grey"));
        return rawOrders;
    }
}
