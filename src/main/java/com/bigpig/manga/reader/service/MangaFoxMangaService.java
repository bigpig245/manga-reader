package com.bigpig.manga.reader.service;

import com.bigpig.manga.reader.dto.CategoryDto;
import com.bigpig.manga.reader.dto.MangaDto;
import com.bigpig.manga.reader.enumaration.ServerName;

import java.util.List;

public class MangaFoxMangaService implements MangaService {
    @Override
    public List<CategoryDto> writeCategories() throws Exception {
        return null;
    }

    @Override
    public List<MangaDto> writeMangaList(String utl) throws Exception {
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
