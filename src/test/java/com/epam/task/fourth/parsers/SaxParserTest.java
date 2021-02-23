package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SaxParserTest extends AbstractParserTest {

    private final SaxParser saxParser = new SaxParser();

    @Test
    public void testShouldParseXmlWhenXmlFileIsCorrect() throws ParseException {
        List<AbstractTouristVoucher> actual = saxParser.parse(VALID_XML);

        Assert.assertEquals(expected, actual);
    }
}
