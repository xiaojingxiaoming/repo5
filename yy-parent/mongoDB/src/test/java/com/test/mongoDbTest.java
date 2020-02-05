package com.test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

public class mongoDbTest {
    @Test
    public void testConnection(){
        MongoClient mongoClient=new MongoClient("localhost",27017);
       // MongoClientURI("mongodb://root:root@localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("student");
        Document myDoc = collection.find().first();
        String json = myDoc.toJson();
        System.out.println(json);
    }
}
