import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {
    private HttpURLConnection connection;

    public String sendGetRequest(String request){
        String line = "";
        String response = "";

        try{
            URL url = new URL("http://localhost:8080/"+request);
            this.connection = (HttpURLConnection) url.openConnection();

            this.connection.setRequestMethod("GET");
            this.connection.setConnectTimeout(5000);
            this.connection.setReadTimeout(3000);

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
