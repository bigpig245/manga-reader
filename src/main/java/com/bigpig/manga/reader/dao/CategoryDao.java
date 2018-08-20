package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryDao extends BaseDao<CategoryDto> {
    @Override
    public CategoryDto update(CategoryDto entity) {
        return null;
    }

    @Override
    public CategoryDto find(CategoryDto entity) {
        return null;
    }

    @Override
    public String getCollectionName() {
        return "categories";
    }

    @Override
    public String getCollectionId() {
        return "category_id";
    }
}
