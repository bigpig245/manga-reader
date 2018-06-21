package com.bigpig.manga.reader.utils;

import lombok.experimental.UtilityClass;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.bigpig.manga.reader.utils.CommonUtils.isEmpty;

@UtilityClass
public class JsoupUtils {
    public static String getAttribute(Document doc, String query, String attribute) {
        Elements cateIdElements = doc.select(query);
        return isEmpty(cateIdElements) ? "" : cateIdElements.get(0).attr(attribute);
    }

    public static String getLastAttribute(Document doc, String query, String attribute) {
        Elements cateIdElements = doc.select(query);
        return isEmpty(cateIdElements) ? "" : cateIdElements.last().attr(attribute);
    }

    public static String getText(Document doc, String query) {
        Elements cateIdElements = doc.select(query);
        return isEmpty(cateIdElements) ? "" : cateIdElements.get(0).text();
    }
}
