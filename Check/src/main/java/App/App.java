package App;



import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;
import org.json.JSONObject;

import javassist.tools.rmi.ObjectNotFoundException;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.NoDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gulza on 21/05/2017.
 */
public class App {
    CouchDbClient dbClient;
    public App(){
        CouchDbProperties properties = new CouchDbProperties()
                .setDbName("retail-store")
                .setCreateDbIfNotExist(true)
                .setProtocol("http")
                .setHost("127.0.0.1")
                .setPort(5984)
                .setMaxConnections(100)
                .setConnectionTimeout(0);

        dbClient = new CouchDbClient(properties);

    }
    public  void Register(String name, String password,Integer type) {
        App app= new App();
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");


        Map<String, Object> map = new HashMap<String,Object>();
        System.out.println(password);
        System.out.println(name);

        map.put("_id", name);
        map.put("password", password);
        map.put("type",type);


        dbClient.save(map);
    }
    public Boolean authenticate(String id, String password){
        JSONObject json= new JSONObject();
        System.out.print(id);
        Boolean exists=false;
        try {
           InputStream in = dbClient.find(id);
           int data=0;
           String jsonData="";

           while(data!=-1){
               data=in.read();

               jsonData=jsonData+String.valueOf((char)data);
           }
            json=new JSONObject(jsonData);
           System.out.println(json);
           exists= (json.getString("password")).equals(password);
        }
        catch(NoDocumentException e){
            exists=false;
            System.out.println("object not exists");
        }
        catch(IOException e){}

        return exists;

    }
    public Integer getType(String id){
        JSONObject json= new JSONObject();
        Integer type=0;
        int data=0;
        String jsonData="";
        try {
            InputStream in = dbClient.find(id);
            while(data!=-1){
                data=in.read();
                jsonData=jsonData+((char)data);

            }
            json=new JSONObject(jsonData);
            type=json.getInt("type");
        }
        catch(NoDocumentException e){
            System.out.println("object not exists");
        }
        catch(IOException e){}
        System.out.println(type);
        return type;

    }

}
