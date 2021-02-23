package com.epam.task.fourth.validating;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private final Logger LOGGER = LogManager.getLogger(XmlValidator.class);

    public boolean xmlIsValid(String xmlFilename, String xsdFilename) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            File xsdFile = new File(xsdFilename);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator  = schema.newValidator();
            File xmlFile = new File(xmlFilename);
            validator.validate(new StreamSource(xmlFile));
            return true;
        } catch (IOException | SAXException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
