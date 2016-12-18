package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.auth.ICredentials;

import java.util.Arrays;
import java.util.List;
/**
 * Created by veinhorn on 18.12.16.
 */
public class PairUtil {
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
}
