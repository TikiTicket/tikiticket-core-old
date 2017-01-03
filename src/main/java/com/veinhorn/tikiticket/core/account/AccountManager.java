package com.veinhorn.tikiticket.core.account;

import com.veinhorn.tikiticket.core.BaseManager;
import com.veinhorn.tikiticket.core.IConnector;
import com.veinhorn.tikiticket.core.api.IAccountManager;
import com.veinhorn.tikiticket.core.api.IPersonalData;
import com.veinhorn.tikiticket.core.context.ContextEvent;
import com.veinhorn.tikiticket.core.context.ContextState;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;

import java.io.IOException;

/**
 * Created by veinhorn on 18.12.16.
 */
public class AccountManager extends BaseManager implements IAccountManager {
    private static final String PERSONAL_DATA_URL = "https://poezd.rw.by/wps/myportal/home/rp/private/private1";

    public AccountManager(IConnector connector) {
        super(connector);
    }

    @Override
    public IPersonalData getPersonalData() throws TikiTicketException {
        try {
            String html = connector.doGet(PERSONAL_DATA_URL).getHtml();
            IPersonalData personalData = new PersonalDataParser().parse(html);
            connector.getContextHolder().updateContext(new ContextEvent() {
                @Override
                public ContextState getState() {
                    return ContextState.PERSONAL_ACCOUNT;
                }

                @Override
                public String getLastHtml() {
                    return html;
                }
            });
            return personalData;
        } catch (IOException e) {
            e.printStackTrace();
            throw new TikiTicketException("Cannot parse personal user data", e);
        } catch (TikiTicketException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
