package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.util.Pair;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 17.12.16.
 * Stores response context
 */
public interface ResponseContext {
    String getHtml() throws IOException;
    List<Pair> getHeaders();
}
