package com.bigpig.manga.reader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerName {
    BLOG_TRUYEN("blog truyen", "http://blogtruyen.com/"),
    MANGA_FOX("Manga Fox", "http://mangafox.com/");

    String name;
    String url;
}
