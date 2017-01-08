package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.BaseManager;
import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.account.CompletedTripsUrl2Parser;
import com.veinhorn.tikiticket.core.account.CompletedTripsUrlParser;
import com.veinhorn.tikiticket.core.api.IOrder;
import com.veinhorn.tikiticket.core.api.IOrderDetails;
import com.veinhorn.tikiticket.core.api.IOrderManager;
import com.veinhorn.tikiticket.core.context.ContextEvent;
import com.veinhorn.tikiticket.core.context.ContextHolder;
import com.veinhorn.tikiticket.core.context.ContextState;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Pair;
import com.veinhorn.tikiticket.core.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by veinhorn on 22.12.16.
 */
public class OrderManager extends BaseManager implements IOrderManager {
    private static final String PERSONAL_ACCOUNT_URL = "https://poezd.rw.by/wps/myportal/home/rp/private";

    public OrderManager(IConnector connector) {
        super(connector);
    }

    @Override
    public List<IOrder> retrieveCurrentTrips() throws TikiTicketException {
        try {
            String html = connector.doGet(PERSONAL_ACCOUNT_URL).getHtml();
            List<IOrder> currentTrips = new CurrentOrdersParser().parse(html);

            connector.getContextHolder().updateContext(new ContextEvent() {
                @Override public ContextState getState() {
                    return ContextState.CURRENT_TRIPS;
                }

                @Override public String getLastHtml() {
                    return html;
                }
            });

            return currentTrips;
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse current orders", e);
        } catch (TikiTicketException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public IOrderDetails retrieveTripDetails(IOrder order) throws TikiTicketException {
        try {
            ContextHolder ctx = connector.getContextHolder();

            String str;
            // If we already on completed trips page, we can just get this HTML page
            if (ctx.getState() == ContextState.COMPLETED_TRIPS) {
                Document document = Jsoup.parse(ctx.getLastHtml());

                // String lastHtml = ctx.getLastHtml();
                String relativeUrl = document.getElementsByTag("form").get(1).attr("action");
                String url = Util.createUrl(relativeUrl);
                String ids = document.getElementById("com.sun.faces.VIEW").val();

                // TODO: Replace static form values with dynamic
                String detailsHtml = connector.doPost(url, Arrays.asList(
                        new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:cabOrderList1:pagerWeb1__pagerWeb", ""),
                        new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:selForNewOrderId1", ""),
                        new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:selForNewOrderDate1", ""),
                        new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2", "viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2"),
                        new Pair("com.sun.faces.VIEW", ids),
                        new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:_idcl", "viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:cabOrderList1:0:_id71"),
                        new Pair("rownum", "0")
                )).getHtml();
                IOrderDetails tripDetails = new OrderDetailsParser().parse(detailsHtml);
                // Here we should update event
                connector.getContextHolder().updateContext(new ContextEvent() {
                    @Override
                    public ContextState getState() {
                        return ContextState.TRIP_DETAILS;
                    }

                    @Override
                    public String getLastHtml() {
                        return detailsHtml;
                    }
                });

                return tripDetails;
            } else { // TODO: Implement this branch
                str = "none";
                connector.doGet("");
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse detail order info", e);
        }
    }

    @Override
    public List<IOrder> retrieveCompletedTrips(Date start, Date finish) throws TikiTicketException {
        try {
            String personalAccHtml = connector.doGet(PERSONAL_ACCOUNT_URL).getHtml();

            String completedTripsUrl = new CompletedTripsUrlParser().parse(personalAccHtml);
            String completedTripsHtml = connector.doGet(completedTripsUrl).getHtml();

            List<String> parsedData = new CompletedTripsUrl2Parser().parse(completedTripsHtml);
            String fetchedOrdersHtml = connector.doPost(parsedData.get(0), Arrays.asList(
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1:calendar1", ""),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1:calendar1", ""),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1:inputArrivalStationCombo1", ""),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1:InputOrderNumFilter", ""),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1:button1", "Показать"),
                    new Pair("com.sun.faces.VIEW", parsedData.get(1)),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1", "viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form1")
            )).getHtml();

            List<IOrder> orders = new CurrentOrdersParser().parse(fetchedOrdersHtml);

            // Update context state
            connector.getContextHolder().updateContext(new ContextEvent() {
                @Override public ContextState getState() {
                    return ContextState.COMPLETED_TRIPS;
                }

                @Override public String getLastHtml() {
                    return fetchedOrdersHtml;
                }
            });

            return orders;
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot retrieve completed trips", e);
        } catch (TikiTicketException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
