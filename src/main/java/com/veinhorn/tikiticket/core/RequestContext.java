package com.veinhorn.tikiticket.core;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 17.12.16.
 */
public interface RequestContext {
    String getHtml() throws IOException;
    List<Pair> getHeaders();
}
