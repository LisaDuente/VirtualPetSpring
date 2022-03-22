import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {
    //private HttpURLConnection connection;

    public String sendGetRequest(String request){
        String line = "";
        String response = "";

        try{
            URL url = new URL("http://localhost:8080/"+request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(3000);

            int status = connection.getResponseCode();

            if(status <300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    response += line;
                }
                reader.close();
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
