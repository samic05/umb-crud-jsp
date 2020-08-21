package com.umb.santiago.galvis.crud.config;

import com.umb.santiago.galvis.crud.config.database.DatabaseProperties;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Configuration {

    private static final Map<String,Map<String,String>> mapOfProperties;
    private static final Map<String,String> databasePropertiesMap;
    private static DatabaseProperties database=null;
    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("application.yml");
        mapOfProperties = yaml.load(inputStream);
        databasePropertiesMap = mapOfProperties.get("database");
    }

    public static DatabaseProperties getDatabaseProperties(){
        database = DatabaseProperties.builder()
                .database(databasePropertiesMap.get("database"))
                .driver(databasePropertiesMap.get("driver"))
                .host(databasePropertiesMap.get("host"))
                .url(databasePropertiesMap.get("url"))
                .port(databasePropertiesMap.get("port"))
                .prefix(databasePropertiesMap.get("prefix"))
                .user(databasePropertiesMap.get("user"))
                .password(databasePropertiesMap.get("password"))
                .build();
        return database;


    }
}
