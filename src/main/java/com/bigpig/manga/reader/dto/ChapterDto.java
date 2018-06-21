package com.bigpig.manga.reader.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterDto extends BaseDto {

    String name;
    String path;
    LocalDateTime orgPublishedDate;
    List<PageDto> pages;

}
