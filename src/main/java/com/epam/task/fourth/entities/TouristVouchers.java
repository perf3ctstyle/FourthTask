package com.epam.task.fourth.entities;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TouristVouchers", namespace = "http://www.example.com/TouristVouchers")
public class TouristVouchers {

    @XmlElements({
            @XmlElement(name = "AbstractTouristVoucher", type = AbstractTouristVoucher.class, namespace = "http://www.example.com/TouristVouchers"),
            @XmlElement(name = "ForeignTouristVoucher", type = ForeignTouristVoucher.class, namespace = "http://www.example.com/TouristVouchers"),
            @XmlElement(name = "PilgrimageTouristVoucher", type = PilgrimageTouristVoucher.class, namespace = "http://www.example.com/TouristVouchers")
    })

    private List<AbstractTouristVoucher> touristVouchers = new ArrayList<>();

    public void setTouristVouchers(List<AbstractTouristVoucher> touristVouchers) {
        this.touristVouchers = touristVouchers;
    }

    public List<AbstractTouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }

    @Override
    public String toString() {
        return "TouristVouchers{" +
                "touristVouchers=" + touristVouchers +
                '}';
    }
}
