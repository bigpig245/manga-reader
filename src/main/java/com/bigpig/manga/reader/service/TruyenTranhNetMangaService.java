package com.bigpig.manga.reader.service;

import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.ChapterDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.dto.PageDto;
import com.bigpig.manga.reader.enumaration.ServerName;
import com.bigpig.manga.reader.utils.CommonUtils;
import com.bigpig.manga.reader.utils.JsoupUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TruyenTranhNetMangaService implements MangaService {
    @Override
    public List<CategoryDto> writeCategories() throws Exception {
        Document doc = Jsoup.connect(getServerName().getUrl()).get();

        String cssQuery = "div.category div.col-xs-6 a";
        //contains

        Elements links = doc.select(cssQuery); // a with href
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Element element : links) {
            categoryDtos.add(CategoryDto.builder()
                    .name(element.attr("title"))
                    .path(element.attr("href"))
                    .title(element.attr("title"))
                    .build());
        }

        return categoryDtos;
    }

    @Override
    public List<MangaDto> writeMangaList(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        String pageCssQuery = "a.page";
        Elements pageLinks = doc.select(pageCssQuery);
        int lastPage = pageLinks.size() > 0 ? Integer.valueOf(pageLinks.last().text()) : 1;

        String cssQuery = "div.col-md-6 div[class=media mainpage-manga] a.tooltips";

        List<MangaDto> mangaDtos = new ArrayList<>();
        for (int i = 1; i <= lastPage; i++) {
            String path = String.format("%s?p=%s", url, i);
            doc = Jsoup.connect(path).get();
            Elements links = doc.select(cssQuery);
            for (Element element : links) {
                MangaDto mangaDto = MangaDto.builder()
                        .name(element.attr("title"))
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
        Document doc = Jsoup.connect(mangaDto.getPath()).get();

        // update cover page
        mangaDto.setCoverPage(JsoupUtils.getAttribute(doc,
                "div[class=media manga-detail] div[class=media-left cover-detail] img", "src"));

        // update descriptuon
        mangaDto.setDescription(JsoupUtils.getText(doc, "div[class=manga-content]"));

        // update categories/genres
        String categoryQuery = "a[class=CateName]";
        Elements categoryElements = doc.select(categoryQuery);
        List<String> categories = new ArrayList<>();
        for (Element element : categoryElements) {
            String key = CommonUtils.deAccent(element.attr("title").toLowerCase())
                    .replace(" ", "-");
            categories.add(key);
        }
        mangaDto.setCategories(categories);

        // update chapter
        String chapterQuery = "div[class=total-chapter] section p a";
        Elements chapters = doc.select(chapterQuery);
        List<ChapterDto> chapterDtos = new ArrayList<>();
        for (Element element : chapters) {
            ChapterDto chapterDto = ChapterDto.builder()
                    .name(element.attr("title"))
                    .path(element.attr("href"))
                    .build();
            updatePageList(chapterDto);
            chapterDtos.add(chapterDto);
        }
        mangaDto.setChapterDtos(chapterDtos);

    }

    private void updatePageList(ChapterDto chapterDto) throws Exception {
        Document doc = Jsoup.connect(chapterDto.getPath()).get();

        // update cover page
        String chapterCssQuery = "div[class=each-page] img";
        Elements pageElements = doc.select(chapterCssQuery);
        List<PageDto> pageDtos = new ArrayList<>();
        for (Element element : pageElements) {
            pageDtos.add(PageDto.builder()
                    .path(element.attr("src"))
                    .build());
        }
        chapterDto.setPages(pageDtos);
    }

    @Override
    public ServerName getServerName() {
        return ServerName.TRUYEN_TRANH_NET;
    }
}
