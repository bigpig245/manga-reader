package com.bigpig.manga.reader.service;

import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.dto.ServerName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MangaFoxMangaService implements MangaService {
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
        return null;
    }

    @Override
    public void updateManga(MangaDto mangaDto) throws Exception {

    }

    @Override
    public ServerName getServerName() {
        return ServerName.MANGA_FOX;
    }
}
