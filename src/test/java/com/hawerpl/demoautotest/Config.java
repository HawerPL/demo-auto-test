package com.hawerpl.demoautotest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    Properties appProps = new Properties();
    String rootPath = System.getProperty("user.dir");
    String appConfigPath = rootPath + "\\demo-auto-test.properties";

    public Config() {
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String property){
        return appProps.getProperty(property);
    }

    public void incrementProperty(String property) {

        String oldValue = appProps.getProperty(property);
        int newValue = Integer.valueOf(oldValue);
        newValue++;
        appProps.setProperty(property, String.valueOf(newValue));

        try {
            appProps.save(new FileOutputStream(appConfigPath), null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
