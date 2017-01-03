package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.api.ICredentials;
import com.veinhorn.tikiticket.core.context.ContextHolder;
import com.veinhorn.tikiticket.core.util.Pair;

import java.io.IOException;
import java.util.List;

/**
 * Created by veinhorn on 17.12.16.
 * It's an interlayer between Manager business logic and HTTP layer, you should implement it using
 * some HTTP library
 */
// TODO: Add credentials method, that will provide some credentials
public interface IConnector {
    ResponseContext doGet(String url) throws IOException;

    ResponseContext doPost(String url, List<Pair> params) throws IOException;

    /** Should provide credentials for auth requests */
    ICredentials getCredentials();

    /**
     *
     * @return
     */
    ContextHolder getContextHolder();
}
