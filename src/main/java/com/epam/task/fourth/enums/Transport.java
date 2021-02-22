package com.epam.task.fourth.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Transport {

    @XmlEnumValue("car")
    CAR,
    @XmlEnumValue("plane")
    PLANE,
    @XmlEnumValue("boat")
    BOAT,
    @XmlEnumValue("train")
    TRAIN
}
