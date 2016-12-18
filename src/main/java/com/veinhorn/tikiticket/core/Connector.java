package com.veinhorn.tikiticket.core;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 17.12.16.
 */
public interface Connector {
    RequestContext doGet(String url) throws IOException;
    RequestContext doPost(String url, List<Pair> params) throws IOException;
}
