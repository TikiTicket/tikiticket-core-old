package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.auth.AuthManager;
import com.veinhorn.tikiticket.core.impl.CustomConnector;
import com.veinhorn.tikiticket.core.impl.PropertyCredentials;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by veinhorn on 17.12.16.
 */
public class Boot {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Connector customConnector = new CustomConnector(httpClient);

        try {
            AuthManager manager = new AuthManager(customConnector);
            RequestContext requestContext = manager.authenticate(new PropertyCredentials());
            String test = "ok";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
