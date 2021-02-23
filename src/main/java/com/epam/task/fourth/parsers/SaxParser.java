package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import com.epam.task.fourth.handlers.TouristVoucherHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    public List<AbstractTouristVoucher> parse(String xmlFile) throws ParseException {
        List<AbstractTouristVoucher> touristVouchers;
        TouristVoucherHandler touristVoucherHandler = new TouristVoucherHandler();
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(touristVoucherHandler);
            xmlReader.parse(xmlFile);
            touristVouchers = touristVoucherHandler.getTouristVouchers();
            return touristVouchers;
        } catch (IOException | SAXException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }
}
