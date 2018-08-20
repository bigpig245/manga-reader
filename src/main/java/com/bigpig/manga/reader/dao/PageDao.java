package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.PageDto;

public class PageDao extends BaseDao<PageDto> {
    @Override
    public PageDto update(PageDto entity) {
        return null;
    }

    @Override
    public PageDto find(PageDto entity) {
        return null;
    }

    @Override
    public String getCollectionName() {
        return "pages";
    }

    @Override
    public String getCollectionId() {
        return "page_id";
    }
}
