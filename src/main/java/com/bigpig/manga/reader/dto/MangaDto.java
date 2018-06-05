package com.bigpig.manga.reader.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MangaDto {
    int no;
    String name;
    String title;
    String path;
    String description;
    String coverPage;
    List<ChapterDto> chapterDtos;
    List<CategoryDto> categoryDtos;
    int chapterNumber;
    int views;
    int commentNumber;
    boolean favorite;

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tName: " + name);
        sb.append("\tTitle: " + title);
        sb.append("\tPath: " + path);
        sb.append("\tDescription: " + description);
        sb.append("\tCover Page: " + coverPage);
        sb.append("\tChapters: ");
        for (ChapterDto chapterDto : chapterDtos) {
            sb.append("\t\t" + chapterDto.getName());
        }

        sb.append("\tCategories: ");
        for (CategoryDto categoryDto : categoryDtos) {
            sb.append("\t\t" + categoryDto.getName());
        }
        System.out.println(sb.toString());
    }
}
