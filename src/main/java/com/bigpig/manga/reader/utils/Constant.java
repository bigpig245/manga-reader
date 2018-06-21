package com.bigpig.manga.reader.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {
    public static final String SEPARATOR = ",";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String DATABASE = "mongodb://localhost:27017";
    public static final String DATABASE_NAME = "mangadb";
    public static final String DATETIME_PATTERN = "dd/MM/yyyy HH:mm";
}
