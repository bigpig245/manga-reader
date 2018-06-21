package com.bigpig.manga.reader.service;


import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.enumaration.ServerName;

import java.util.List;

public interface MangaService {
    List<CategoryDto> writeCategories() throws Exception;

    List<MangaDto> writeMangaList(String url) throws Exception;

    void updateManga(MangaDto mangaDto) throws Exception;

    ServerName getServerName();
}
