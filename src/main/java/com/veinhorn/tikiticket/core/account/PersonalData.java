package com.veinhorn.tikiticket.core.account;

/**
 * Created by veinhorn on 18.12.16.
 */
public class PersonalData implements IPersonalData {
    private String country;

    public PersonalData(String country) {
        this.country = country;
    }

    @Override
    public String getCountry() {
        return country;
    }
}
