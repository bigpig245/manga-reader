package com.bigpig.manga.reader;

import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.service.BlogTruyenMangaService;
import com.bigpig.manga.reader.service.MangaService;
import com.google.common.base.CharMatcher;

import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
        MangaService blogTruyenMangaService = new BlogTruyenMangaService();
        List<CategoryDto> categoryDtos = blogTruyenMangaService.getCategories();
        for (CategoryDto categoryDto : categoryDtos) {
            categoryDto.print();
            List<MangaDto> mangaDtos = blogTruyenMangaService.getMangaList(categoryDto);
            for (MangaDto mangaDto : mangaDtos) {
                blogTruyenMangaService.updateManga(mangaDto);
                mangaDto.print();
            }
        }

        String s = "javascript:LoadMangaPage(16)";
        System.out.println(CharMatcher.DIGIT.retainFrom(s));
    }
}
