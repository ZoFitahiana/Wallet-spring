package com.api.wallet.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class CurrencyValue implements Serializable {
    private String currencyValueId;
    private String sourceDeviceId;
    private String deviceDestination;
    private BigDecimal amount;
    private LocalDateTime date ;

    public CurrencyValue(String currencyValueId, String sourceDeviceId, String deviceDestination, BigDecimal amount, LocalDateTime date) {
        this.currencyValueId = currencyValueId;
        this.sourceDeviceId = sourceDeviceId;
        this.deviceDestination = deviceDestination;
        this.amount = amount;
        this.date = date;
    }

    public String getCurrencyValueId() {
        return currencyValueId;
    }

    public void setCurrencyValueId(String currencyValueId) {
        this.currencyValueId = currencyValueId;
    }

    public String getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(String sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    public String getDeviceDestination() {
        return deviceDestination;
    }

    public void setDeviceDestination(String deviceDestination) {
        this.deviceDestination = deviceDestination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CurrencyValue that = (CurrencyValue) object;
        return Objects.equals(currencyValueId, that.currencyValueId) && Objects.equals(sourceDeviceId, that.sourceDeviceId) && Objects.equals(deviceDestination, that.deviceDestination) && Objects.equals(amount, that.amount) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyValueId, sourceDeviceId, deviceDestination, amount, date);
    }

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "currencyValueId='" + currencyValueId + '\'' +
                ", sourceDeviceId='" + sourceDeviceId + '\'' +
                ", deviceDestination='" + deviceDestination + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
