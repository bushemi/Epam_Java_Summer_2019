package com.epam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    public String findWordAfterRegexp(String text, Pattern pattern, String end) {
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        int startOfNeededText = matcher.end();

        String fromOperationTillEnd = text.substring(startOfNeededText);
        return fromOperationTillEnd.trim().substring(0, fromOperationTillEnd.indexOf(end)).trim();
    }
}
