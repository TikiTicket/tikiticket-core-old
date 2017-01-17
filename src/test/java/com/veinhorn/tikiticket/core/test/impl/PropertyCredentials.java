package com.veinhorn.tikiticket.core.test.impl;

import com.veinhorn.tikiticket.core.api.ICredentials;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by veinhorn on 18.12.16.
 */
// TODO: Implement in Scala
public class PropertyCredentials implements ICredentials {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public Properties properties;

    @Override public String getLogin() {
        try {
            loadProperties();
            return properties.getProperty(LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public String getPassword() {
        try {
            loadProperties();
            return properties.getProperty(PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("creds.properties"));
        }
    }
}
