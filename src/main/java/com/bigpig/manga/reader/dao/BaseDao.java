package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.BaseDto;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.time.LocalDateTime;

import static com.bigpig.manga.reader.utils.Constant.DATABASE;
import static com.bigpig.manga.reader.utils.Constant.DATABASE_NAME;

public abstract class BaseDao<T extends BaseDto> {
    protected MongoDatabase db;
    private SequenceDao sequenceDao;

    public BaseDao() {
        MongoClient mongo = MongoClients.create(DATABASE);
        db = mongo.getDatabase(DATABASE_NAME);
        sequenceDao = new SequenceDao();
    }

    public abstract void saveItem(T entity);

    public void save(T entity) {
        int nextValue = sequenceDao.nextValue(getCollectionId());
        entity.setId(nextValue);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLastModifiedDate(LocalDateTime.now());
        saveItem(entity);
    }

    public abstract T update(T entity);

    public abstract T find(T entity);

    public boolean isExist(String key, String value) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(key, value);
        return getCollection().count(searchQuery) == 0 ? false : true;
    }

    public void deleteAllByKey(String key, String value) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(key, value);
        getCollection().deleteMany(searchQuery);
    }

    public abstract String getCollectionName();

    public abstract String getCollectionId();

    public MongoCollection getCollection() {
        return db.getCollection(getCollectionName());
    }

}
