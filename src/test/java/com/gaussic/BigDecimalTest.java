package com.gaussic;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(0.33434);
        bigDecimal.setScale(5);
        System.out.println(bigDecimal.floatValue());
    }
}
