package com.bigpig.manga.reader.dao;

import com.bigpig.manga.reader.dto.SequenceDto;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.bigpig.manga.reader.utils.Constant.*;


public class SequenceDao {
    protected MongoDatabase db;

    public SequenceDao() {
        MongoClient mongo = MongoClients.create(DATABASE);
        db = mongo.getDatabase(DATABASE_NAME);
    }

    public int nextValue(String key) {
        MongoCollection mongoCollection = db.getCollection("sequences");
        Document document = new Document();
        document.put("sequenceName", key);
        int nextValue = 1;
        if (mongoCollection.count(document) != 0) {
            SequenceDto sequenceDto = OBJECT_MAPPER.convertValue(mongoCollection.find(document)
                    .first(), SequenceDto.class);
            nextValue = sequenceDto.getNextValue();
            mongoCollection.findOneAndDelete(document);
        }
        document.put("nextValue", nextValue + 1);
        mongoCollection.insertOne(document);
        return nextValue;
    }
}
