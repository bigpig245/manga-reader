package com.bigpig.manga.reader.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {
    String name;
    String title;
    String path;

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");
        sb.append("Name: " + name);
        sb.append("Title: " + title);
        sb.append("Path: " + path);
        System.out.println(sb.toString());
    }
}
