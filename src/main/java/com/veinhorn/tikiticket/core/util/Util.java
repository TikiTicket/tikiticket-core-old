package com.veinhorn.tikiticket.core.util;

import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.constant.Constants;

import java.util.Arrays;
import java.util.List;
/**
 * Created by veinhorn on 18.12.16.
 * Utility class with useful util methods
 */
public class Util {
    /**
     * Tries to find pair by such key
     * @return pair if it's exist or null
     */
    public static Pair findPairByKey(List<Pair> pairs, String key) {
        for (Pair pair : pairs) {
            if (pair.getKey().equals(key))
                return pair;
        }
        return null;
    }

    /** Converts credentials into a list of Pair's */
    public static List<Pair> toPairs(ICredentials credentials) {
        if (credentials.getLogin() != null && credentials.getPassword() != null) {
            return Arrays.asList(
                    new Pair(Constants.LOGIN, credentials.getLogin()),
                    new Pair(Constants.PASSWORD, credentials.getPassword())
            );
        }
        return null;
    }

    public static String createUrl(String relativeUrl) {
        return Constants.BASE_URL + relativeUrl;
    }

    /** Creates ICredentials from passed login and password */
    public static ICredentials newCredentials(String login, String password) {
        return new ICredentials() {
            @Override
            public String getLogin() {
                return login;
            }

            @Override
            public String getPassword() {
                return password;
            }
        };
    }
}
