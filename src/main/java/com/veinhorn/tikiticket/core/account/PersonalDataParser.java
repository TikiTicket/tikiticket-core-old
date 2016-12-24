package com.veinhorn.tikiticket.core.account;

import com.veinhorn.tikiticket.core.DataParser;
import com.veinhorn.tikiticket.core.api.IPersonalData;
import com.veinhorn.tikiticket.core.exception.TikiTicketException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by veinhorn on 21.12.16.
 * It can produce exception during parsing
 */
public class PersonalDataParser implements DataParser<IPersonalData> {
    @Override
    public IPersonalData parse(String html) throws TikiTicketException {
        try {
            return parse(Jsoup.parse(html));
        } catch (Exception e) {
            throw new TikiTicketException("Cannot parse personal user data", e);
        }
    }

    private IPersonalData parse(Document document) {
        Element userData = document.getElementById("userReg");
        Elements trElms = userData.getElementsByTag("tr");
        PersonalData personalData = new PersonalData();
        // Fetch data from DOM
        personalData.setLogin(trElms.get(2).getElementsByTag("input").attr("value"));
        personalData.setFirstName(trElms.get(9).getElementsByTag("input").attr("value"));
        personalData.setSecondName(trElms.get(7).getElementsByTag("input").attr("value"));
        personalData.setPatronymic(trElms.get(11).getElementsByTag("input").attr("value"));
        personalData.setPhoneNumber(trElms.get(13).getElementsByTag("input").attr("value"));
        personalData.setCountry(trElms.get(15).getElementsByTag("input").attr("value"));
        personalData.setAddress(trElms.get(16).getElementsByTag("textarea").text());
        personalData.setEmail(trElms.get(17).getElementsByTag("input").attr("value"));
        personalData.setGender(trElms.get(19).getElementsByAttributeValue("selected", "selected").text());
        personalData.setAge(trElms.get(21).getElementsByAttributeValue("selected", "selected").text());

        return personalData;
    }
}
