package com.simplilearn.mongodb;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;

public class _04_InsertMany {

    public static void main(String[] args) {

        String uri = "mongodb://localhost";

        try (MongoClient mongoClient = MongoClients.create(uri);) { /* Open a Connection with MongoDB */

            MongoDatabase database = mongoClient.getDatabase("blog"); /* Open the database */

            MongoCollection<Document> collection = database.getCollection("posts"); /* Open the collection (table)) */

            List<Document> movieList = Arrays.asList(
						            		new Document()
						            		        .append("_id", new ObjectId())
						                            .append("title", "Post Title 6")
						                            .append("body", "Body of post.")
						                            .append("category", "Technology")
						                            .append("likes", 3)
						                            .append("tags", Arrays.asList("news","events"))
						                            .append("date", new Date().toString()),
						                    new Document()
						                            .append("_id", new ObjectId())
						                            .append("title", "Post Title 7")
						                            .append("body", "Body of post.")
						                            .append("category", "Event")
						                            .append("likes", 7)
						                            .append("tags", Arrays.asList("news","events"))
						                            .append("date", new Date().toString()));
            
            InsertManyResult result = collection.insertMany(movieList);

            System.out.println("Success!, Document ids " + result.getInsertedIds());

        } catch (MongoException me) {
            System.out.println("Error while Inserting!, " + me.getMessage());
        }
    }
}
