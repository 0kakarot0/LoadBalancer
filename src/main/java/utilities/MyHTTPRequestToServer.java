package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MyHTTPRequestToServer {

    public static String returnResponseFromServer(String server) throws IOException {
        URL url = getUrl(server);
        HttpURLConnection httpURLConnection = getHttpURLConnection(url);
        setRequestMethod(httpURLConnection);
        StringBuilder stringBuffer = getResponseFromServer(getResponseCode(httpURLConnection), httpURLConnection);
        return stringBuffer.toString();
    }

    public static boolean isServerAlive(String url) {
        try {
            HttpURLConnection httpURLConnection = getHttpURLConnection(getUrl(url));
            setRequestMethod(httpURLConnection);
            return getResponseCode(httpURLConnection) == 200;
        } catch (IOException e) {
            return false;
        }

    }

    private static void setRequestMethod(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setRequestMethod("GET");
    }


    private static int getResponseCode(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        return responseCode;
    }

    private static URL getUrl(String server) throws MalformedURLException {
        return new URL(server);
    }

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private static StringBuilder getResponseFromServer(int responseCode, HttpURLConnection httpURLConnection) throws IOException {
        BufferedReader bufferedReader = null;
        if (100 <= responseCode && responseCode <= 399) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        }
        String inputLine;
        StringBuilder stringBuffer = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(inputLine).append("\n");
        }
        bufferedReader.close();
        return stringBuffer;
    }

}
