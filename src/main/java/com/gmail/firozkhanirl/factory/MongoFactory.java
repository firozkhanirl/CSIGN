package com.gmail.firozkhanirl.factory;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoFactory {
	private static Logger logger = Logger.getLogger(MongoFactory.class);
	
	private static Mongo mongo;
	
	private MongoFactory() {}
	
	
	@SuppressWarnings("deprecation")
	public static Mongo getMongo(){
		int portNo = 27017;
		String hostname = "localhost";
		
		if(mongo == null){
			try{
				mongo = new Mongo(hostname, portNo);
			}catch(MongoException ex){
				logger.error(ex);
			}
		}
		
		return mongo;
	}
	
	public static DB getDB(String dbName) {
		return getMongo().getDB(dbName);
	}
	
	public static DBCollection getCollection(String dbName, String collectionName){
		return getDB(dbName).getCollection(collectionName);
	}
}