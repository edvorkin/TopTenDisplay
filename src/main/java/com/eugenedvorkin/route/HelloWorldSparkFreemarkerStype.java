package com.eugenedvorkin.route;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: edvorkin
 * Date: 5/16/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSparkFreemarkerStype {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStype.class, "/");
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template template = configuration.getTemplate("hello.ftl");

                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Hello Freemarker");
                    template.process(helloMap, stringWriter);
                    System.out.println(stringWriter);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return stringWriter;
            }
        });
    }
}
