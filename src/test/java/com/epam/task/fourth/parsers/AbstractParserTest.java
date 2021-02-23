package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;
import com.epam.task.fourth.entities.ForeignTouristVoucher;
import com.epam.task.fourth.entities.PilgrimageTouristVoucher;
import com.epam.task.fourth.enums.Currency;
import com.epam.task.fourth.enums.Transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {

    protected final static String VALID_XML = "./src/test/resources/TouristVouchers.xml";

    protected final List<AbstractTouristVoucher> expected = new ArrayList<>(Arrays.asList(
            new ForeignTouristVoucher(Transport.CAR, Currency.USD, 200, "v555666", "Saudi Arabia"),
            new ForeignTouristVoucher(Transport.PLANE, Currency.EUR, 1000, "v123321", "Turkey"),
            new PilgrimageTouristVoucher(Transport.TRAIN, Currency.BYN, 500, "v932234", "Israel", false),
            new PilgrimageTouristVoucher(Transport.BOAT, Currency.USD, 1350, "v551346", "India", true)));
}
