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
public class FirebaseSend extends Component{
    
    HttpURLConnection connection;
    
    OutputStreamWriter streamWriter;
    InputStreamReader inputReader;
    BufferedReader reader;
    BufferedWriter writer;
    
    
    
   /// Run only once
   void start() {
      
       
       send();
   }
   
   /// Repeat every frame
   void repeat() {
       
   }
   
   
   void send(){
       int code;
       
       try{
           
           
           URL url = new URL("https://esport-ae1bb-default-rtdb.firebaseio.com/dataSet/data.json");
            connection =(HttpURLConnection)url.openConnection();
           
           connection.setRequestMethod("PUT");
           connection.setRequestProperty("Content-Type","application/json");
           
           String json = "{\"Name\":\"Shubham Yadav\"}";
           
           
           
           streamWriter = new OutputStreamWriter(connection.getOutputStream());
           writer = new BufferedWriter(streamWriter);
           writer.write(json);
           writer.flush();
           writer.close();
           code = connection.getResponseCode();
           print(code);
           
           connection.disconnect();
           
           get(url,connection);
       }catch(Exception e){}
       
       
       
   }
   
   void get(URL url,HttpURLConnection connection){
      
      try{
          
          connection= (HttpURLConnection) url.openConnection();
          
          inputReader = new InputStreamReader(connection.getInputStream());
          
          reader= new BufferedReader(inputReader);
          
          StringBuilder response = new StringBuilder();
          
          String line;
          
          
          
          while( (line = reader.readLine()) !=null){
              response.append(line);
          }
          reader.close();
          
         print(response.toString());
          
          connection.disconnect();
          
      }catch(Exception e){}
      
      
       
   }
   
   
   
   
}
