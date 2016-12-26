package com.veinhorn.tikiticket.core.order;

import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.ResponseContext;
import com.veinhorn.tikiticket.core.api.IOrder;
import com.veinhorn.tikiticket.core.api.IOrderManager;
import com.veinhorn.tikiticket.core.auth.AuthManager;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 22.12.16.
 */
public class OrderManager implements IOrderManager {
    // dlya vtorogo zaprosa
    private String body = "sort_hidden_id=sort&sort_hidden_order=sortdescon&sort_hidden_id=sort2&sort_hidden_order=sortdescon&sort_hidden_id=sort3&sort_hidden_order=sortdescon&sort_hidden_id=sort4&sort_hidden_order=sortdescon&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3AcabOrderList1%3A0%3Acm=5345506&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3AcabOrderList1%3A1%3Acm=5345480&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3AcabOrderList1%3ApagerWeb1__pagerWeb=0&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3Alanguage1=ru&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3AselForNewOrderId1=&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3AselForNewOrderDate1=&com.sun.faces.VIEW=_id58643%3A_id58644&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2=viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2&viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3A_idcl=viewns_7_48QFVAUK6PT510AGU3KRAG1004_%3Aform2%3AcabOrderList1%3A1%3A_id71&rownum=1";

    private static final String CURRENT_ORDERS_URL = "https://poezd.rw.by/wps/myportal/home/rp/private";

    private IConnector connector;
    private AuthManager authManager;

    public OrderManager(IConnector connector) {
        this.connector = connector;
        authManager = new AuthManager(connector);
    }

    @Override
    public List<IOrder> getCurrentOrders() throws TikiTicketException {
        try {
            ResponseContext context = authManager.authenticate(connector.getCredentials());
            String html = connector.doGet(CURRENT_ORDERS_URL).getHtml();
            return new CurrentOrdersParser().parse(html);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse current orders", e);
        } catch (TikiTicketException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getOrderDetails() throws TikiTicketException {
        try {
            ResponseContext context = authManager.authenticate(connector.getCredentials());
            String html = connector.doGet(CURRENT_ORDERS_URL).getHtml();
            Document document = Jsoup.parse(html);
            String relativeUrl = document.getElementsByTag("form").get(1).attr("action");
            String detailsUrl = Util.createUrl(relativeUrl);
            /*String res = connector.doPost(detailsUrl, Arrays.asList(
                    new Pair("", ""),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:cabOrderList1:pagerWeb1__pagerWeb", "0"),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2", "viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2"),
                    new Pair("com.sun.faces.VIEW", "id58627:_id58628"),
                    new Pair("viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:_idcl", "viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:cabOrderList1:0:_id71"),
                    new Pair("rownum", "0"))).getHtml();*/
            String res = connector.doPost2(detailsUrl, body).getHtml();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse detail order info", e);
        }
    }
}
