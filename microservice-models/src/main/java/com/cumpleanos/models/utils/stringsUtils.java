package com.cumpleanos.models.utils;

public class stringsUtils {

    public static String normalizedItemsPrefix(String item){
        return item.replaceFirst("^(EP|IC)", "");
    }
}
