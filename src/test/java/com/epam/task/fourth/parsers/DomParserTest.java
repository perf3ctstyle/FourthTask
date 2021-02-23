package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DomParserTest extends AbstractParserTest {

    private final DomParser domParser = new DomParser();

    @Test
    public void testShouldParseXmlFileWhenFileIsCorrect() throws ParseException {
        List<AbstractTouristVoucher> actual = domParser.parse(VALID_XML);

        Assert.assertEquals(expected, actual);
    }
}
