package nativeCamp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSendUtil {

    public static void main(String[] args) {

    }

    public  String sendPOST(String url,String cookie,String searchCondition,String userAgent) throws IOException {

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", userAgent);
            con.setRequestProperty("Cookie",cookie);

            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");


            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write((searchCondition).getBytes());
            os.flush();
            os.close();


            int responseCode = con.getResponseCode();


            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

              return response.toString();

            } else {

                System.out.println("POST request not worked");
            }
            return null;

    }
}

