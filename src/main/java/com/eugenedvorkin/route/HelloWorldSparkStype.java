package com.eugenedvorkin.route;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created with IntelliJ IDEA.
 * User: edvorkin
 * Date: 5/16/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSparkStype {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello world from spark";  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
