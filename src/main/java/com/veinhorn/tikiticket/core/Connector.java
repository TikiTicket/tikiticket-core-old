package com.veinhorn.tikiticket.core;

import com.veinhorn.tikiticket.core.context.ContextHolder;

/**
 * Created by veinhorn on 29.12.16.
 */
@Deprecated
public abstract class Connector implements IConnector {
    private ContextHolder contextHolder;

    public Connector() {
        contextHolder = new ContextHolder();
    }

    @Override
    public ContextHolder getContextHolder() {
        return contextHolder;
    }
}
