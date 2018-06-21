package com.bigpig.manga.reader;

import com.bigpig.manga.reader.service.BlogTruyenMangaService;
import com.bigpig.manga.reader.service.MangaService;

public class Application {
    public static void main(String[] args) throws Exception {
        MangaService mangaService = new BlogTruyenMangaService();
        mangaService.writeMangaList("/theloai/16");
    }
}
