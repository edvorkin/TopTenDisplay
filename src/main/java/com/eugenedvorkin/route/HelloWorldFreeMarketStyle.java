package com.eugenedvorkin.route;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: edvorkin
 * Date: 5/16/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldFreeMarketStyle {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarketStyle.class,"/");
        try {
            Template template=configuration.getTemplate("hello.ftl") ;
            StringWriter stringWriter=new StringWriter();
            Map<String, Object> helloMap=new HashMap<String, Object>();
            helloMap.put("name","Hello Freemarker");
            template.process(helloMap,stringWriter);
            System.out.println(stringWriter);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
