package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.MangaDto;

public class MangaDao extends BaseDao<MangaDto> {
    @Override
    public MangaDto update(MangaDto entity) {
        return null;
    }

    @Override
    public MangaDto find(MangaDto entity) {
        return null;
    }

    @Override
    public String getCollectionName() {
        return "mangas";
    }

    @Override
    public String getCollectionId() {
        return "manga_id";
    }
}
