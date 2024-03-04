package com.api.wallet.db.entity;

import java.io.Serializable;
import java.util.Objects;

public class Currency implements Serializable {
    private String currencyId;
    private String name ;
    private String code ;

    public Currency(String currencyId, String name, String code) {
        this.currencyId = currencyId;
        this.name = name;
        this.code = code;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Currency currency = (Currency) object;
        return Objects.equals(currencyId, currency.currencyId) && Objects.equals(name, currency.name) && Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyId, name, code);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId='" + currencyId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
