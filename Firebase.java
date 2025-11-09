package JAVARuntime;

import java.util.*;
import java.text.*;
import java.net.*;
import java.math.*;
import java.io.*;
import java.nio.*;
import java.nio.*;
import java.util.HashMap;
import java.util.Map;

/* @Author Shubham Yadav */ 
public class Firebase extends Component{

    HttpURLConnection connection;
    OutputStreamWriter streamWriter;
    InputStreamReader inputReader;
    BufferedReader reader;
    BufferedWriter writer;
    

    
    void start() {
        
        send();
    }

    
    void repeat() {

    }

    void send() {
        int code;

        try {
            URL url = new URL("https://esport-ae1bb-default-rtdb.firebaseio.com/dataSet/data.json");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");

            String json = "{\"Name\":\"Shubham Yadav\",\"Age\":\"19\"}";

            streamWriter = new OutputStreamWriter(connection.getOutputStream());
            writer = new BufferedWriter(streamWriter);
            writer.write(json);
            writer.flush();
            writer.close();
            code = connection.getResponseCode();
            print(code);

            connection.disconnect();

            get(url, connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void get(URL url, HttpURLConnection connection) {

        try {
            connection = (HttpURLConnection) url.openConnection();

            inputReader = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(inputReader);

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
                print(line);
            }

            reader.close();
            connection.disconnect();

           
            String name = getValueFromJson(response.toString(), "Name");
            String age = getValueFromJson(response.toString(), "Age");

            print("Name from JSON: " + name);
            print("Age from JSON: " + age);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   
    String getValueFromJson(String json, String key) {
        try {
            
            if (json.contains("\"" + key + "\"")) {
             
                String[] parts = json.split("\"" + key + "\"\\s*:\\s*\""); 
                if (parts.length > 1) {
         
                    return parts[1].split("\"")[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // key not found
    }

}