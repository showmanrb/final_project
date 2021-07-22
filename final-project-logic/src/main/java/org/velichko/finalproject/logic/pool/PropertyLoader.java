package org.velichko.finalproject.logic.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.exception.ConnectionPoolException;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyLoader {
    private static Logger logger = LogManager.getLogger();

    public static Properties loadPropertiesData(URL url) {
        Properties properties = new Properties();
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            logger.log(Level.FATAL, "Error with loading properties from file. ", e);
            throw new RuntimeException("Error with loading properties from file: " + url, e);
        }
        return properties;
    }
}
