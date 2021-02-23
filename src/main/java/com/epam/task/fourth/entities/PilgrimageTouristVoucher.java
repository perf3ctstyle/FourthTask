package com.epam.task.fourth.entities;

import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement(name = "TouristVouchers")
public class PilgrimageTouristVoucher extends ForeignTouristVoucher {

    @XmlAttribute(required = false)
    private boolean security;

    public PilgrimageTouristVoucher() {}

    public PilgrimageTouristVoucher(AbstractTouristVoucher touristVoucher) {
        super(touristVoucher.getTransport(), touristVoucher.getCurrency(), touristVoucher.getCostValue(), touristVoucher.getId());
    }

    public PilgrimageTouristVoucher(ForeignTouristVoucher foreignTouristVoucher) {
        super(foreignTouristVoucher.getTransport(), foreignTouristVoucher.getCurrency(),
                foreignTouristVoucher.getCostValue(), foreignTouristVoucher.getId(), foreignTouristVoucher.getCountry());
    }

    public PilgrimageTouristVoucher(Transport transport, Currency currency, int costValue, String id, String country, boolean security) {
        super(transport, currency, costValue, id, country);
        this.security = security;
    }

    public boolean getSecurity() {
        return this.security;
    }

    public void setSecurity(boolean security) {
        this.security = security;
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
        PilgrimageTouristVoucher that = (PilgrimageTouristVoucher) o;
        return security == that.security;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (security ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PilgrimageTouristVoucher{" +
                "transport=" + this.getTransport() +
                ", currency=" + this.getCurrency() +
                ", costValue=" + this.getCostValue() +
                ", id='" + this.getId() +
                "', country='" + this.getCountry() +
                "', security=" + this.getSecurity() +
                '}';
    }
}
