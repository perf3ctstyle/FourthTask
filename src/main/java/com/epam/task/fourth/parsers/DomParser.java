package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.ForeignTouristVoucher;
import com.epam.task.fourth.entities.PilgrimageTouristVoucher;
import com.epam.task.fourth.entities.AbstractTouristVoucher;
import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private final static Logger logger = LogManager.getLogger(DomParser.class);

    private final static String FOREIGN_TOURIST_VOUCHER  = "ForeignTouristVoucher";
    private final static String PILGRIMAGE_TOURIST_VOUCHER  = "PilgrimageTouristVoucher";

    private final static String ID = "id";
    private final static String TRANSPORT = "transport";
    private final static String COST = "cost";
    private final static String CURRENCY = "currency";
    private final static String VALUE = "value";
    private final static String COUNTRY = "country";
    private final static String SECURITY = "security";

    @Override
    public List<AbstractTouristVoucher> parse(String filename) throws ParseException {
        List<AbstractTouristVoucher> touristVouchers = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            Element rootElement = document.getDocumentElement();

            NodeList foreignTouristVouchersList = rootElement.getElementsByTagName(FOREIGN_TOURIST_VOUCHER);
            for (int i=0; i< foreignTouristVouchersList.getLength(); i++) {
                Element touristVoucherElement = (Element) foreignTouristVouchersList.item(i);
                ForeignTouristVoucher foreignTouristVoucher = buildForeignTouristVoucher(touristVoucherElement);
                touristVouchers.add(foreignTouristVoucher);
            }

            NodeList pilgrimageTouristVouchersList = rootElement.getElementsByTagName(PILGRIMAGE_TOURIST_VOUCHER);
            for (int i=0; i<pilgrimageTouristVouchersList.getLength(); i++) {
                Element touristVoucherElement = (Element) pilgrimageTouristVouchersList.item(i);
                PilgrimageTouristVoucher pilgrimageTouristVoucher = buildPilgrimageTouristVoucher(touristVoucherElement);
                touristVouchers.add(pilgrimageTouristVoucher);
            }
            return touristVouchers;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    private ForeignTouristVoucher buildForeignTouristVoucher(Element foreignTouristVoucherElement) {
        ForeignTouristVoucher foreignTouristVoucher = new ForeignTouristVoucher();

        String id = foreignTouristVoucherElement.getAttribute(ID);
        foreignTouristVoucher.setId(id);

        String transportString = getElementTextContent(foreignTouristVoucherElement, TRANSPORT);
        Transport transportEnum = Transport.valueOf(transportString);
        foreignTouristVoucher.setTransport(transportEnum);

        Element costElement = (Element) foreignTouristVoucherElement.getElementsByTagName(COST).item(0);
        String currencyString = getElementTextContent(costElement, CURRENCY);
        Currency currencyEnum = Currency.valueOf(currencyString);
        foreignTouristVoucher.setCurrency(currencyEnum);

        int cost = Integer.parseInt(getElementTextContent(costElement, VALUE));
        foreignTouristVoucher.setCost(cost);

        String country = getElementTextContent(foreignTouristVoucherElement, COUNTRY);
        foreignTouristVoucher.setCountry(country);

        return foreignTouristVoucher;
    }

    private PilgrimageTouristVoucher buildPilgrimageTouristVoucher(Element pilgrimageTouristVoucherElement) {
        PilgrimageTouristVoucher pilgrimageTouristVoucher = new PilgrimageTouristVoucher(buildForeignTouristVoucher(pilgrimageTouristVoucherElement));

        String securityString = pilgrimageTouristVoucherElement.getAttribute(SECURITY);
        boolean securityBoolean = Boolean.parseBoolean(securityString);
        pilgrimageTouristVoucher.setSecurity(securityBoolean);

        return pilgrimageTouristVoucher;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
