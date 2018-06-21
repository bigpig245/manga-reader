package com.bigpig.manga.reader.dto;

import com.bigpig.manga.reader.enumaration.ServerName;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto extends BaseDto{
    ServerName serverName;
    String name;
    String title;
    String path;
    String key;
}
