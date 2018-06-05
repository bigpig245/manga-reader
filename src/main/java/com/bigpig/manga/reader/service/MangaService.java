package com.bigpig.manga.reader.service;


import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.dto.ServerName;

import java.util.List;

public interface MangaService {
    List<CategoryDto> getCategories() throws Exception;

    List<MangaDto> getMangaList(CategoryDto categoryDto) throws Exception;

    void updateManga(MangaDto mangaDto) throws Exception;

    ServerName getServerName();
}
