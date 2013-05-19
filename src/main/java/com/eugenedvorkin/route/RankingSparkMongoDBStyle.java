package com.eugenedvorkin.route;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: edvorkin
 * Date: 5/16/13
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class RankingSparkMongoDBStyle {
    public static void main(String[] args) throws FileNotFoundException {
           final Properties prop = new Properties();


           final Configuration configuration = new Configuration();

           configuration.setClassForTemplateLoading(RankingSparkMongoDBStyle.class, "/");
        try {
            prop.load(RankingSparkMongoDBStyle.class.getClassLoader().getResourceAsStream("config.properties"));
            MongoClient mongoClient=new MongoClient(new ServerAddress(prop.getProperty("mongodb")));
            Spark.setPort(Integer.parseInt(prop.getProperty("webport")));
            DB database=mongoClient.getDB("cp");
            final DBCollection collection=database.getCollection("ranking");

        Spark.get(new Route("/ranking") {
               @Override
               public Object handle(Request request, Response response) {
                   StringWriter stringWriter = new StringWriter();
                   try {
                       Template template = configuration.getTemplate("hello.ftl");
                       BasicDBObject query = new BasicDBObject("_id", "globalRanking");
                       DBObject dbObject=collection.findOne(query);
                       String value= dbObject.get("ranking").toString();
                       Map<String,String> ranking=new HashMap<String, String>();
                       ranking.put("ranking",value);
                       return value;
                   } catch (Exception e) {
                       halt(500);
                       e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                   }
                   return stringWriter;
               }
           });
                //main page
                Spark.get(new Route("/") {
                               @Override
                               public Object handle(Request request, Response response) {
                                   StringWriter stringWriter = new StringWriter();
                                   try {
                                       Template template = configuration.getTemplate("hello.ftl");
                                       BasicDBObject query = new BasicDBObject("_id", "globalRanking");
                                       DBObject dbObject=collection.findOne(query);
                                       String value= dbObject.get("ranking").toString();
                                       Map<String,String> ranking=new HashMap<String, String>();
                                       ranking.put("ranking",value);
                                       template.process(ranking, stringWriter);
                                   } catch (Exception e) {
                                       halt(500);
                                       e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                   }
                                   return stringWriter;
                               }
                           }

        );
       }  catch (UnknownHostException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
