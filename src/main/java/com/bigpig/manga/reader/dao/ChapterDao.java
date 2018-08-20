package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.ChapterDto;

public class ChapterDao extends BaseDao<ChapterDto>{
    @Override
    public ChapterDto update(ChapterDto entity) {
        return null;
    }

    @Override
    public ChapterDto find(ChapterDto entity) {
        return null;
    }

    @Override
    public String getCollectionName() {
        return "chapters";
    }

    @Override
    public String getCollectionId() {
        return "chapter_id";
    }
}
