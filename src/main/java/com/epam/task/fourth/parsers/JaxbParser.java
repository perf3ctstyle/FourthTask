package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import com.epam.task.fourth.entities.TouristVouchers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {

    private final static Logger logger = LogManager.getLogger(JaxbParser.class);

    private final static String SCHEMA_XSD = "./src/test/resources/TouristVouchers.xsd";

    public List<AbstractTouristVoucher> parse(String filename) throws ParseException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TouristVouchers.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(SCHEMA_XSD);
            Schema schema = schemaFactory.newSchema(schemaLocation);
            unmarshaller.setSchema(schema);

            File xmlFile = new File(filename);
            TouristVouchers touristVouchers = (TouristVouchers) unmarshaller.unmarshal(xmlFile);

            return touristVouchers.getTouristVouchers();
        } catch (JAXBException | SAXException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }
}
