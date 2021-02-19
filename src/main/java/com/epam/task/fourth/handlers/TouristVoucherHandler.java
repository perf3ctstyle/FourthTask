package com.epam.task.fourth.handlers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import com.epam.task.fourth.entities.ForeignTouristVoucher;
import com.epam.task.fourth.entities.PilgrimageTouristVoucher;
import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TouristVoucherHandler extends DefaultHandler {

    private final static Logger logger = LogManager.getLogger(TouristVoucherHandler.class);

    private final List<AbstractTouristVoucher> touristVouchers;
    private AbstractTouristVoucher currentTouristVoucher;

    private final static String FOREIGN_TOURIST_VOUCHER = "ForeignTouristVoucher";
    private final static String PILGRIMAGE_TOURIST_VOUCHER = "PilgrimageTouristVoucher";

    private static String currentTouristVoucherSubElement = null;
    private final static String ID = "id";
    private final static String TRANSPORT = "transport";
    private final static String COST = "cost";
    private final static String CURRENCY = "currency";
    private final static String VALUE = "value";
    private final static String COUNTRY = "country";
    private final static String SECURITY = "security";
    private final static List<String> TOURIST_VOUCHER_SUB_ELEMENTS = Arrays.asList(ID, TRANSPORT, COST, CURRENCY, VALUE, COUNTRY, SECURITY);

    public TouristVoucherHandler() {
        this.touristVouchers = new ArrayList<>();
    }

    public List<AbstractTouristVoucher> getTouristVouchers() {
        return touristVouchers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (localName) {
            case FOREIGN_TOURIST_VOUCHER:
                currentTouristVoucher = new ForeignTouristVoucher();
                setAttributes(currentTouristVoucher, attributes);
                break;
            case PILGRIMAGE_TOURIST_VOUCHER:
                currentTouristVoucher = new PilgrimageTouristVoucher();
                setAttributes(currentTouristVoucher, attributes);
                break;
            default:
                if (TOURIST_VOUCHER_SUB_ELEMENTS.contains(localName)) {
                    currentTouristVoucherSubElement = localName;
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (FOREIGN_TOURIST_VOUCHER.equals(localName) || PILGRIMAGE_TOURIST_VOUCHER.equals(localName)) {
            touristVouchers.add(currentTouristVoucher);
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        String text = new String(chars, start, length).trim();
        if (currentTouristVoucherSubElement != null) {
            switch (currentTouristVoucherSubElement) {
                case TRANSPORT:
                    Transport transport = Transport.valueOf(text);
                    currentTouristVoucher.setTransport(transport);
                    break;
                case CURRENCY:
                    Currency currency = Currency.valueOf(text);
                    currentTouristVoucher.setCurrency(currency);
                    break;
                case VALUE:
                    int value = Integer.parseInt(text);
                    currentTouristVoucher.setCost(value);
                    break;
                case COUNTRY:
                    ForeignTouristVoucher foreignTouristVoucher = new ForeignTouristVoucher(currentTouristVoucher);
                    foreignTouristVoucher.setCountry(text);
                    currentTouristVoucher = foreignTouristVoucher;
                    break;
            }
        }
    }

    public void setAttributes(AbstractTouristVoucher touristVoucher, Attributes attributes) {
        String id = attributes.getValue(0);
        touristVoucher.setId(id);
        if (attributes.getLength() == 2) {
            PilgrimageTouristVoucher pilgrimageTouristVoucher = new PilgrimageTouristVoucher(touristVoucher);
            String security = attributes.getValue(1);
            boolean securityBoolean = Boolean.parseBoolean(security);
            pilgrimageTouristVoucher.setSecurity(securityBoolean);
            currentTouristVoucher = pilgrimageTouristVoucher;
        }
    }
}
