package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.auth.AuthUrlParser;
import com.veinhorn.tikiticket.core.context.ContextHolder;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import com.veinhorn.tikiticket.core.util.Pair;
import com.veinhorn.tikiticket.core.util.Util;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 2.1.17.
 */
abstract class BaseManager {
    protected IConnector connector;

    protected BaseManager(IConnector connector) {
        this.connector = connector;
    }

    /** It's like a proxy, using decorator pattern */
    private class AuthConnector implements IConnector {
        private static final String LOGIN_PAGE_URL = "https://poezd.rw.by/wps/portal/home/login_main";

        private IConnector connector;
        private ContextHolder ctx;

        public AuthConnector(IConnector connector) {
            this.connector = connector;
            ctx = new ContextHolder();
        }

        /** It's clever auth */
        private void authenticate() throws IOException {
            if (!ctx.isAuthorized()) authenticate(getCredentials());
        }

        /** It's basic auth */
        private ResponseContext authenticate(ICredentials creds) throws IOException {
            try {
                String authUrl = new AuthUrlParser().parse(connector.doGet(LOGIN_PAGE_URL).getHtml());

                ResponseContext context1 = connector.doPost(authUrl, Util.toPairs(creds));
                String redirectionUrl = Util.findPairByKey(context1.getHeaders(), "Location").getValue();
                return connector.doGet(redirectionUrl);
            } catch (TikiTicketException e) {
                e.printStackTrace();
                throw new IOException();
            }
        }

        @Override
        public ContextHolder getContextHolder() {
            return ctx;
        }

        @Override
        public ResponseContext doGet(String url) throws IOException {
            authenticate();
            return connector.doGet(url);
        }

        @Override
        public ResponseContext doPost(String url, List<Pair> params) throws IOException {
            authenticate();
            return connector.doPost(url, params);
        }

        @Override
        public ICredentials getCredentials() {
            return connector.getCredentials();
        }
    }
}
