package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Map;

import static com.bigpig.manga.reader.utils.Constant.OBJECT_MAPPER;

@Slf4j
public class CategoryDao extends BaseDao<CategoryDto> {
    @Override
    public void saveItem(CategoryDto entity) {
        Map map = OBJECT_MAPPER.convertValue(entity, Map.class);
        getCollection().insertOne(new Document(map));
        System.out.println("Insert successfull: " + entity.getName());
    }

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
