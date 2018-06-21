package com.bigpig.manga.reader.service;

import com.bigpig.manga.reader.dao.CategoryDao;
import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.ChapterDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.enumaration.ServerName;
import com.bigpig.manga.reader.utils.CommonUtils;
import com.bigpig.manga.reader.utils.JsoupUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.bigpig.manga.reader.utils.JsoupUtils.getAttribute;
import static com.bigpig.manga.reader.utils.JsoupUtils.getLastAttribute;

public class BlogTruyenMangaService implements MangaService {
    private static String API_MANGA = "http://blogtruyen.com/ajax/Category/AjaxLoadMangaByCategory?id=%s&orderBy=1&p=%s";
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    public List<CategoryDto> writeCategories() throws Exception {
        Document doc = Jsoup.connect(getServerName().getUrl()).get();

        String cssQuery = "ul[class=submenu category list-unstyled] li a";
        //contains

        Elements links = doc.select(cssQuery); // a with href
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Element element : links) {
            String name = element.text();
            if (StringUtils.isEmpty(name)) {
                continue;
            }
            String key = CommonUtils.deAccent(name.toLowerCase()).replace(" ", "-");
            CategoryDto categoryDto = CategoryDto.builder()
                    .serverName(getServerName())
                    .name(name)
                    .path(element.attr("href"))
                    .title(element.attr("title"))
                    .key(key)
                    .build();
            categoryDao.save(categoryDto);
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }

    @Override
    public List<MangaDto> writeMangaList(String url) throws Exception {

        Document doc = Jsoup.connect(getServerName().getUrl() + url).get();

        String cateId = getAttribute(doc, "input#CateId", "value");
        String totalPageQuery = getLastAttribute(doc, "div.paging a", "href");
        int totalPage = Integer.valueOf(totalPageQuery.replaceAll("[^0-9]", ""));
        List<MangaDto> mangaDtos = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            String apiUrl = String.format(API_MANGA, cateId, i + 1);
            doc = Jsoup.connect(apiUrl).get();
            String cssQuery = "span.tiptip a";
            Elements links = doc.select(cssQuery);
            for (Element element : links) {
                MangaDto mangaDto = MangaDto.builder()
                        .name(element.text())
                        .title(element.attr("title"))
                        .path(element.attr("href"))
                        .build();
                updateManga(mangaDto);
                mangaDtos.add(mangaDto);
            }
        }
        return mangaDtos;
    }

    @Override
    public void updateManga(MangaDto mangaDto) throws Exception {
        Document doc = Jsoup.connect(getServerName().getUrl() + mangaDto.getPath()).get();
        // update cover page
        mangaDto.setCoverPage(JsoupUtils.getAttribute(doc, "div[class=thumbnail] img", "src"));
        // update description
        mangaDto.setDescription(JsoupUtils.getText(doc, "div[class=content]"));
        // author
        mangaDto.setAuthor(JsoupUtils.getText(doc, "a[class=color-green label label-info]"));
        // translation team
        mangaDto.setTranslationTeam(JsoupUtils.getText(doc, "span.translater"));
        // status
        mangaDto.setStatus(JsoupUtils.getText(doc, "span[class=color-red]"));
        // page view
        mangaDto.setViewCount(Integer.valueOf(JsoupUtils.getText(doc, "span#PageViews")));
        // like count
        mangaDto.setFollowCount(Integer.valueOf(JsoupUtils.getText(doc, "span#LikeCount")));
        // update categories/genres
        String categoryQuery = "span[class=category]";
        Elements categoryElements = doc.select(categoryQuery);
        List<String> categories = new ArrayList<>();
        for (Element element : categoryElements) {
            String key = CommonUtils.deAccent(element.attr("title").toLowerCase())
                    .replace(" ", "-");
            categories.add(key);
        }
        mangaDto.setCategories(categories);

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
