package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.api.IOrderDetails;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by veinhorn on 30.12.16.
 */
public class OrderDetailsParser implements DataParser<IOrderDetails> {
    @Override
    public IOrderDetails parse(String html) throws TikiTicketException {
        try {
            return parse(Jsoup.parse(html));
        } catch (Exception e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse order details", e);
        }
    }

    private IOrderDetails parse(Document document) {
        OrderDetails details = new OrderDetails();
        // Here we fetch several fetches with various data
        Elements tables = document.getElementsByClass("fields");
        Elements trElements = tables.get(0).getElementsByTag("tr");
        // Fetch order details data
        details.setOrderNumber(trElements.get(0).child(1).text());
        details.setOrderDate(trElements.get(1).child(1).text());
        details.setTrainNumber(trElements.get(2).child(1).text());
        details.setDispatchStation(trElements.get(3).child(1).text());
        details.setDestinationStation(trElements.get(4).child(1).text());
        details.setDepartureDate(trElements.get(5).child(1).text());
        details.setArrivalDate(trElements.get(6).child(1).text());
        details.setWagonRoad(trElements.get(7).child(1).text());
        details.setWagonOwner(trElements.get(8).child(1).text());
        details.setWagonNumber(trElements.get(9).child(1).text());
        details.setWagonType(trElements.get(10).child(1).text());
        details.setNumberOfSeats(trElements.get(11).child(1).text());
        details.setSeats(trElements.get(12).child(1).text());
        details.setAdditionalInformation(trElements.get(13).child(1).text());
        details.setPrice(trElements.get(14).child(1).text());
        details.setOrderState(trElements.get(15).child(1).text());
        // Fetch data about ec registration
        Elements ecTrElements = tables.get(1).getElementsByClass("inner_tab").get(0).getElementsByTag("tr");
        details.setEcState(ecTrElements.get(0).child(1).text());
        details.setEcDate(ecTrElements.get(1).child(1).text());
        return details;
    }
}
