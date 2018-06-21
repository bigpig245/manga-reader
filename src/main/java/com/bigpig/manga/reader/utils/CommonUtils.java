package com.bigpig.manga.reader.utils;


import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;


@UtilityClass

public class CommonUtils {

    public static String deAccent(String s) {
        String nfdNormalizedString = Normalizer.normalize(removeDuplicateWhiteSpace(s), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }


    public static String removeDuplicateWhiteSpace(String s) {
        return s.replaceAll("\\s+", " ");
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static LocalDateTime getDate(String date, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, df);
    }

    public static LocalDateTime getDate(String date) {
        return getDate(date, Constant.DATETIME_PATTERN);
    }

}