package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerUtils {
    private static final String SERVER_URL_FILE = "src/main/resources/listofserver.txt";

    public static List<String> getListOfServer() {
        List<String> urlList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SERVER_URL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                urlList.add(returnStringURL(line));
            }
            reader.close();
            return urlList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String returnStringURL(String line){
            String[] totalParts = line.split(" = ");
            return totalParts[1].replace("\"","");
    }
}
