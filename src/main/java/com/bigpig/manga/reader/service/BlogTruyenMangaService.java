package com.bigpig.manga.reader.service;

import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.ChapterDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.dto.ServerName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BlogTruyenMangaService implements MangaService {

    @Override
    public List<CategoryDto> getCategories() throws Exception {
        Document doc = Jsoup.connect(getServerName().getUrl()).get();

        String cssQuery = "ul[class=submenu category list-unstyled] li a";
        //contains

        Elements links = doc.select(cssQuery); // a with href
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Element element : links) {
            categoryDtos.add(CategoryDto.builder()
                    .name(element.text())
                    .path(element.attr("href"))
                    .title(element.attr("title"))
                    .build());
        }

        return categoryDtos;
    }

    @Override
    public List<MangaDto> getMangaList(CategoryDto categoryDto) throws Exception {

        Document doc = Jsoup.connect(getServerName().getUrl() + categoryDto.getPath()).get();

        String cssQuery = "div[class=list] p span a";

        Elements links = doc.select(cssQuery);
        List<MangaDto> mangaDtos = new ArrayList<>();
        for (Element element : links) {
            mangaDtos.add(MangaDto.builder()
                    .name(element.text())
                    .title(element.attr("title"))
                    .path(element.attr("href"))
                    .build());
        }

        return mangaDtos;
    }

    @Override
    public void updateManga(MangaDto mangaDto) throws Exception {
        Document doc = Jsoup.connect(getServerName().getUrl() + mangaDto.getPath()).get();

        // update cover page
        String thumbnailQuery = "div[class=thumbnail] img";
        Elements thumbnail = doc.select(thumbnailQuery);
        if (thumbnail != null && thumbnail.size() > 0) {
            mangaDto.setCoverPage(thumbnail.get(0).attr("src"));
        }

        // update descriptuon
        String descriptionQuery = "div[class=content]";
        Elements description = doc.select(descriptionQuery);
        if (description != null && description.size() > 0) {
            mangaDto.setDescription(description.get(0).text());
        }

        // update categories/genres
        String categoryQuery = "span[class=category]";
        Elements categories = doc.select(categoryQuery);
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Element element : categories) {
            categoryDtos.add(CategoryDto.builder().name(element.text())
                    .title(element.attr("title"))
                    .path(element.attr("href"))
                    .build());
        }
        mangaDto.setCategoryDtos(categoryDtos);

        // update chapter
        String chapterQuery = "div[id=list-chapters] p span a[id]";
        Elements chapters = doc.select(chapterQuery);
        List<ChapterDto> chapterDtos = new ArrayList<>();
        for (Element element : chapters) {
            chapterDtos.add(ChapterDto.builder().name(element.text())
                    .path(element.attr("href"))
                    .build());
        }
        mangaDto.setChapterDtos(chapterDtos);

    }

    @Override
    public ServerName getServerName() {
        return ServerName.BLOG_TRUYEN;
    }
}
