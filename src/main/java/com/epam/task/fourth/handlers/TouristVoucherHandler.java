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
    private final static String COST_VALUE = "costValue";
    private final static String COUNTRY = "country";
    private final static String SECURITY = "security";
    private final static List<String> TOURIST_VOUCHER_SUB_ELEMENTS = Arrays.asList(ID, TRANSPORT, COST, CURRENCY, COST_VALUE, COUNTRY, SECURITY);

    private final static int ID_ATTRIBUTE_NUMBER = 0;
    private final static int SECURITY_ATTRIBUTE_NUMBER = 1;

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
                setIdAttribute(currentTouristVoucher, attributes);
                break;
            case PILGRIMAGE_TOURIST_VOUCHER:
                currentTouristVoucher = new PilgrimageTouristVoucher();
                setIdAttribute(currentTouristVoucher, attributes);
                setSecurityAttribute(currentTouristVoucher, attributes);
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
                    text = text.toUpperCase();
                    Transport transport = Transport.valueOf(text);
                    currentTouristVoucher.setTransport(transport);
                    break;
                case CURRENCY:
                    text = text.toUpperCase();
                    Currency currency = Currency.valueOf(text);
                    currentTouristVoucher.setCurrency(currency);
                    break;
                case COST_VALUE:
                    int value = Integer.parseInt(text);
                    currentTouristVoucher.setCostValue(value);
                    break;
                case COUNTRY:
                    if (currentTouristVoucher.getClass() == ForeignTouristVoucher.class) {
                        ForeignTouristVoucher foreignTouristVoucher = new ForeignTouristVoucher(currentTouristVoucher);
                        foreignTouristVoucher.setCountry(text);
                        currentTouristVoucher = foreignTouristVoucher;
                    } else if (currentTouristVoucher.getClass() == PilgrimageTouristVoucher.class) {
                        PilgrimageTouristVoucher pilgrimageTouristVoucher = (PilgrimageTouristVoucher) currentTouristVoucher;
                        pilgrimageTouristVoucher.setCountry(text);
                        currentTouristVoucher = pilgrimageTouristVoucher;
                    }
                    break;
            }
            currentTouristVoucherSubElement = null;
        }
    }

    public void setIdAttribute(AbstractTouristVoucher touristVoucher, Attributes attributes) {
        String id = attributes.getValue(ID_ATTRIBUTE_NUMBER);
        touristVoucher.setId(id);
    }

    public void setSecurityAttribute(AbstractTouristVoucher touristVoucher, Attributes attributes) {
        PilgrimageTouristVoucher pilgrimageTouristVoucher = new PilgrimageTouristVoucher(touristVoucher);
        String security = attributes.getValue(SECURITY_ATTRIBUTE_NUMBER);
        boolean securityBoolean = Boolean.parseBoolean(security);
        pilgrimageTouristVoucher.setSecurity(securityBoolean);
        currentTouristVoucher = pilgrimageTouristVoucher;
    }
}
