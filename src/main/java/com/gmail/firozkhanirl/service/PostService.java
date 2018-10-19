package com.gmail.firozkhanirl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.firozkhanirl.factory.MongoFactory;
import com.gmail.firozkhanirl.model.Post;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("postService")
@Transactional
public class PostService {
	static String db_name="firoz", collection_name="posts";
	private static Logger logger  = Logger.getLogger(PostService.class);
	
	public List getAll(){
		List postList = new ArrayList();
		DBCollection collection = MongoFactory.getCollection(db_name, collection_name);
		
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			
			Post post = new Post();
			
			post.setId(Integer.parseInt(obj.get("id").toString()));
			post.setTitle(obj.get("title").toString());
			post.setContent(obj.get("content").toString());
			
			postList.add(post);
		}
		
		return postList;
	}
	
	public Boolean add(Post post) {
		Boolean added = false;
		
		Random random = new Random();
		
		try{
			DBCollection collection = MongoFactory.getDB(db_name).getCollection(collection_name);
			
			BasicDBObject newPost = new BasicDBObject();
			newPost.put("id", random.nextInt(Integer.MAX_VALUE));
			newPost.put("title", post.getTitle());
			newPost.put("content", post.getContent());
			
			if(collection.insert(newPost).getN() == 1) {
				added = true;
			}
		}
		catch(Exception ex){
			logger.error("Error occured while adding post");
		}
		
		return added;
	}
	
	public Boolean edit(Post post){
		Boolean edited = false;
		
		try{
			DBCollection collection = MongoFactory.getCollection(db_name, collection_name);
			
			DBObject whereQuery = new BasicDBObject();
			whereQuery.put("id", post.getId());
			
			DBObject update = new BasicDBObject();
			update.put("title", post.getTitle());
			update.put("content", post.getContent());
			
			collection.findAndModify(whereQuery, update);
			edited = true;
		}
		catch(Exception ex){
			logger.error("Problem editing the document");
		}
		
		return edited;
	}
	
	public Boolean delete(int id){
		Boolean deleted = false;
		
		try{
			DBCollection collection = MongoFactory.getCollection(db_name, collection_name);
			
			DBObject whereQuery = new BasicDBObject();
			whereQuery.put("id", id);
						
			collection.findAndRemove(whereQuery);
			deleted = true;
		}
		catch(Exception ex){
			logger.error("Problem editing the document");
		}
		
		return deleted;
	}
	
	private DBObject getDBObject(int id){
		DBCollection collection = MongoFactory.getCollection(db_name, collection_name);
		
		DBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("id", id);
		
		return collection.findOne(whereQuery);
	}
	
	public Post findPostById(int id){
		Post post = new Post();
		
		DBCollection collection = MongoFactory.getCollection(db_name, collection_name);
		
		DBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		
		DBObject obj = collection.findOne(whereQuery);
		
		post.setId(id);
		post.setTitle(obj.get("title").toString());
		post.setContent(obj.get("content").toString());
		
		return post;
	}
}
