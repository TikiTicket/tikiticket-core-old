package com.veinhorn.tikiticket.core.context;

/**
 * Created by veinhorn on 29.12.16.
 * Context holder stores current state of IConnector
 */
public class ContextHolder implements IContextHolder {
    private static final long MAX_TIME = 60;

    /** Holds current context state */
    private ContextState state;

    private String lastHtml;

    /** Stores the time of last context state modification (in ms) */
    private long modified;

    public ContextHolder() {
        state = ContextState.UNKNOWN;
    }

    public ContextState getState() {
        return state;
    }

    @Override
    public void updateContext(ContextEvent event) {
        this.state = event.getState();
        this.lastHtml = event.getLastHtml();
        modified = System.currentTimeMillis();
    }

    @Override
    public boolean isAuthorized() {
        return modified != 0 && ((System.currentTimeMillis() - modified) / 1000L < MAX_TIME);
    }

    @Override
    public String getLastHtml() {
        return lastHtml;
    }
}
