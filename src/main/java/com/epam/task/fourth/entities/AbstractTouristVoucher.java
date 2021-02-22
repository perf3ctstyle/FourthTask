package com.epam.task.fourth.entities;

import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public abstract class AbstractTouristVoucher {
    @XmlElement(namespace = "http://www.example.com/TouristVouchers")
    private Transport transport;
    @XmlElement(namespace = "http://www.example.com/TouristVouchers")
    private Currency currency;
    @XmlElement(namespace = "http://www.example.com/TouristVouchers")
    private int costValue;
    @XmlAttribute(required = true)
    @XmlID
    private String id;

    public AbstractTouristVoucher() {}

    public AbstractTouristVoucher(Transport transport, Currency currency, int costValue, String id) {
        this.transport = transport;
        this.currency = currency;
        this.costValue = costValue;
        this.id = id;
    }

    public Transport getTransport() {
        return this.transport;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getCostValue() {
        return this.costValue;
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

    public void setCostValue(int costValue) {
        this.costValue = costValue;
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
        return costValue == that.costValue && transport == that.transport && currency == that.currency && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getCostValue();
        return result;
    }

    @Override
    public String toString() {
        return "TouristVoucher{" +
                "transport=" + transport +
                ", currency=" + currency +
                ", cost=" + costValue +
                ", id='" + id + '\'' +
                '}';
    }
}
