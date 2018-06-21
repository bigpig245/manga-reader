package com.bigpig.manga.reader.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MangaDto extends BaseDto{
    int no;
    String name;
    String title;
    String path;
    String description;
    String coverPage;
    String status;
    String translationTeam;
    String author;
    int chapterCount;
    int viewCount;
    int commentCount;
    int followCount;
    List<ChapterDto> chapterDtos;
    List<String> categories;
}
