package com.bigpig.manga.reader.enumaration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerName {
    BLOG_TRUYEN("blog truyen", "http://blogtruyen.com/"),
    TRUYEN_TRANH_NET("truyen tranh net", "http://truyentranh.net/"),
    MANGA_FOX("Manga Fox", "http://mangafox.com/");

    String name;
    String url;
}
