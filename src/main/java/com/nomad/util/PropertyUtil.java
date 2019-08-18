package com.nomad.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private volatile static Properties properties;

    public static Properties getProperties() {
        if (properties == null) {
            synchronized (PropertyUtil.class) {
                if (properties == null) {
                    InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("site.properties");
                    properties = new Properties();
                    try {
                        properties.load(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return properties;
    }
}
