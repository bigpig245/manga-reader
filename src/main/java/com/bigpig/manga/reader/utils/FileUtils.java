package com.bigpig.manga.reader.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

import static com.bigpig.manga.reader.utils.Constant.OBJECT_MAPPER;

@UtilityClass
@Slf4j
public class FileUtils {
    public static void writeFile(String filePath, Object object) {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), "UTF-8"))) {
            out.write(OBJECT_MAPPER.writeValueAsString(object));
        } catch (IOException e) {
            log.error("Error when trying to write resource", e);
            e.printStackTrace();
        }

    }

    public static void writeCsvFile(String filePath, List object) {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), "UTF-8"))) {
            for (Object o : object) {
                out.write(o.toString());
                out.write("\r\n");
            }
        } catch (IOException e) {
            log.error("Error when trying to write resource", e);
            e.printStackTrace();
        }

    }
}
