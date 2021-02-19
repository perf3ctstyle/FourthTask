package com.epam.task.fourth.entities;

import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;

public abstract class AbstractTouristVoucher {

    private Transport transport;
    private Currency currency;
    private int cost;
    private String id;

    public AbstractTouristVoucher() {}

    public AbstractTouristVoucher(Transport transport, Currency currency, int cost, String id) {
        this.transport = transport;
        this.currency = currency;
        this.cost = cost;
        this.id = id;
    }

    public Transport getTransport() {
        return this.transport;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getCost() {
        return this.cost;
    }

    public String getId() {
        return this.id;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTouristVoucher that = (AbstractTouristVoucher) o;
        return cost == that.cost && transport == that.transport && currency == that.currency && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getCost();
        return result;
    }

    @Override
    public String toString() {
        return "TouristVoucher{" +
                "transport=" + transport +
                ", currency=" + currency +
                ", cost=" + cost +
                ", id='" + id + '\'' +
                '}';
    }
}
