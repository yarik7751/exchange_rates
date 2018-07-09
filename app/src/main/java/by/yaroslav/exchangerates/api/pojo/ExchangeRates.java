package by.yaroslav.exchangerates.api.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "DailyExRates", strict = false)
public class ExchangeRates {

    public ExchangeRates() {
        date = "";
        currencies = new ArrayList<>();
    }

    private @Attribute(name = "Date") String date;
    private @ElementList(entry = "Currency", inline = true, required = false) List<Currency> currencies;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
