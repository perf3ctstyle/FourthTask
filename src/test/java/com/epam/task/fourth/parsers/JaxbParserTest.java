package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JaxbParserTest extends AbstractParserTest {

    private final JaxbParser jaxbParser = new JaxbParser();

    @Test
    public void testShouldParseXmlFileWhenFileIsCorrect() throws ParseException {
        List<AbstractTouristVoucher> actual = jaxbParser.parse(VALID_XML);

        Assert.assertEquals(expected, actual);
    }
}
