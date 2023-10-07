package com.simplilearn.mongodb;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

public class _01_FindOne {

	public static void main(String[] args) {

		String uri = "mongodb://localhost";

		try (MongoClient mongoClient = MongoClients.create(uri);) { /* Open a Connection with MongoDB */

			MongoDatabase database = mongoClient.getDatabase("blog"); /* Open the database */

			MongoCollection<Document> collection = database.getCollection("posts"); /* Open the collection (table)) */
			
			Document doc = collection.find(eq("title", "Post Title 1")) /* Query by title 'eq' Post Title 1 */
					.sort(Sorts.descending("likes")) /* Sort descendingly by number of likes */
					.first(); /* Take the first element of the result */

			if (doc == null) {
				/* If the document is null is because the document was not found. */
				System.out.println("No results found");
			} else {
				System.out.println(doc.toJson()); /* Convert the document to JSON */
			}
		}
		// mongoClient.close();
	}
}
