package com.veinhorn.tikiticket.core.api;

import com.veinhorn.tikiticket.core.exception.TikiTicketException;

/**
 * Created by veinhorn on 18.12.16.
 */
public interface IAccountManager {
    IPersonalData getPersonalData() throws TikiTicketException;
}
