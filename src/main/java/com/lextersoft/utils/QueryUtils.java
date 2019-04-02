package com.lextersoft.utils;

public class QueryUtils {
    public static String cleanText(String text) {
        return text.trim()
            .replace(".", "")
            .replace(",", "")
            .replace("\"", "")
            .replace("'", "");
    }
}
