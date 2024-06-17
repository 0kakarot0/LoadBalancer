package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    private static final String userCommandFilePath = "src/test/resources/userCommand.properties";
    private static Properties userCommandProperty;

    public ReadPropertyFile() {
        userCommandProperty = loadPropertyFile(userCommandFilePath);
    }

    private Properties loadPropertyFile(String filePath) {
        Properties tempProperties = new Properties();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            tempProperties.load(bufferedReader);
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
        return tempProperties;
    }

    public String getUserCommand() {
        return userCommandProperty.getProperty("userCommand");
    }
}
