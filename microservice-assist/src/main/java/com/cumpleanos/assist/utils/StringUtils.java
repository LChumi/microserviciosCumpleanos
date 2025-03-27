package com.cumpleanos.assist.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class StringUtils {

    public static String bigDecimalToString(BigDecimal bigDecimal) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US); //Uso de punto en decimales
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols); //Define el formato
        return decimalFormat.format(bigDecimal);
    }

    public static String longToString(Long valor){
        return String.valueOf(valor);
    }

    public static Integer longToInteger(Long valor){
        return Math.toIntExact(valor);
    }
}
