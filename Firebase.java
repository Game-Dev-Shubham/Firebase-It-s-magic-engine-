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
import org.json.JSONObject;

/* @Author Shubham Yadav */
public class Firebase extends Component {

  HttpURLConnection connection;
  OutputStreamWriter streamWriter;
  InputStreamReader inputReader;
  BufferedReader reader;
  BufferedWriter writer;
  
  void start() {

    send();
  }

  void repeat() {}

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

  void get(URL url,HttpURLConnection connection) {
    try {
      
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
      
      JSONObject obj = new JSONObject(response.toString());

      String name = obj.getString("Name");
      String age = obj.getString("Age");

      print("Name from JSON: " + name);
      print("Age from JSON: " + age);

    } catch (Exception e) {
      e.printStackTrace();
    }
  } 
  
                                         
