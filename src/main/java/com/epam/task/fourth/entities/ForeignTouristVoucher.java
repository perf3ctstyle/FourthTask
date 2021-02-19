package com.epam.task.fourth.entities;

import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;

public class ForeignTouristVoucher extends AbstractTouristVoucher {

    private String country;

    public ForeignTouristVoucher() {}

    public ForeignTouristVoucher(Transport transport, Currency currency, int cost, String id, String country) {
        super(transport, currency, cost, id);
        this.country = country;
    }

    public ForeignTouristVoucher(AbstractTouristVoucher touristVoucher) {
        super(touristVoucher.getTransport(), touristVoucher.getCurrency(), touristVoucher.getCost(), touristVoucher.getId());
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ForeignTouristVoucher that = (ForeignTouristVoucher) o;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ForeignTouristVoucher{" +
                "transport=" + this.getTransport() +
                ", currency=" + this.getCurrency() +
                ", cost=" + this.getCost() +
                ", id='" + this.getId() +
                ", country='" + country + '\'' +
                '}';
    }
}
