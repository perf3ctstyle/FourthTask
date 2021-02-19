package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.AbstractTouristVoucher;

import java.util.List;

public interface Parser {

    List<AbstractTouristVoucher> parse(String filename) throws ParseException;
}
