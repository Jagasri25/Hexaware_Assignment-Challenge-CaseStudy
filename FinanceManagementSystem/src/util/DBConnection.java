package util;

import java.io.FileInputStream;
import java.util.Properties;

public class DBConnection {
    public static String getPropertyString(String filename) {
        String connectionUrl = "";
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(filename);
            props.load(fis);
            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String dbname = props.getProperty("dbname");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbname +
                            "?user=" + username + "&password=" + password;
        } catch (Exception e) {
            System.out.println("Error reading DB properties: " + e.getMessage());
        }

        return connectionUrl;
    }
}
