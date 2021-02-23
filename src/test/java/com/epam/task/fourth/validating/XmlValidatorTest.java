package com.epam.task.fourth.validating;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    private final XmlValidator xmlValidator = new XmlValidator();

    private final static String SCHEMA_XSD = "./src/test/resources/TouristVouchers.xsd";
    private final static String VALID_XML = "./src/test/resources/TouristVouchers.xml";
    private final static String NOT_VALID_XML = "./src/test/resources/TouristVouchersNotValid.xml";
    private final static String NOT_WELL_FORMED_XML = "./src/test/resources/TouristVouchersNotWellFormed.xml";

    @Test
    public void testShouldValidateXmlWhenXmlIsValid() {
        boolean actual = xmlValidator.xmlIsValid(VALID_XML, SCHEMA_XSD);

        Assert.assertTrue(actual);
    }

    @Test
    public void testShouldValidateXmlWhenXmlIsNotValid() {
        boolean actual = xmlValidator.xmlIsValid(NOT_VALID_XML, SCHEMA_XSD);

        Assert.assertFalse(actual);
    }

    @Test
    public void testShouldValidateXmlWhenXmlIsNotWellFormed() {
        boolean actual = xmlValidator.xmlIsValid(NOT_WELL_FORMED_XML, SCHEMA_XSD);

        Assert.assertFalse(actual);
    }
}
