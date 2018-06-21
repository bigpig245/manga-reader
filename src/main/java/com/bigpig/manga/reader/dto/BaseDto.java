package com.bigpig.manga.reader.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseDto {
    int id;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
}
