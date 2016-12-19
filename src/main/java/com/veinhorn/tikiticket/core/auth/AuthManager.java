package com.veinhorn.tikiticket.core.auth;

import com.veinhorn.tikiticket.core.*;
import com.veinhorn.tikiticket.core.util.PairUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by veinhorn on 17.12.16.
 */
public class AuthManager {
    private static final String LOGIN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

    private IConnector connector;

    public AuthManager(IConnector connector) {
        this.connector = connector;
    }

    /** Here we should make 2 requests */
    // TODO: Improve this part, maybe we should not redirect
    public ResponseContext authenticate(ICredentials creds) throws IOException {
        String authUrl = new AuthUrlParser().parse(connector.doGet(LOGIN_PAGE_URL).getHtml());

        ResponseContext context1 = connector.doPost(authUrl, PairUtil.toPairs(creds));
        String redirectionUrl = PairUtil.findPairByKey(context1.getHeaders(), "Location").getValue();
        return connector.doGet(redirectionUrl);
    }

    private class AuthUrlParser implements Parser<String> {
        @Override public String parse(String html) {
            Document document = Jsoup.parse(html);
            return createAuthUrl(document.getElementById("login").attr("action"));
        }

        private String createAuthUrl(String url) {
            return Constants.BASE_URL + url;
        }
    }
}
