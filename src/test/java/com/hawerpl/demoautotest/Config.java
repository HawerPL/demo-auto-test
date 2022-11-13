package com.hawerpl.demoautotest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    Properties appProps = new Properties();

    public Config() {
        String rootPath = System.getProperty("user.dir");
        String appConfigPath = rootPath + "\\demo-auto-test.properties";
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String property){
        return appProps.getProperty(property);
    }
}
