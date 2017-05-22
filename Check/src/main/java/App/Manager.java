package App;

import org.json.JSONObject;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.NoDocumentException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gulza on 22/05/2017.
 */
public class Manager {
    CouchDbClient dbClient;

    public Manager() {
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
    public void addEmployee(String eid, String password){
        App app= new App();
        app.Register(eid,password,1);

    }
    public void deleteEmployee(String eid){
        InputStream in= dbClient.find(eid);
        JSONObject json= new JSONObject();
        int data=0;
        String dataString="";
        String _rev="";
        try{
            while(data!=-1){

                data= in.read();
                dataString=dataString+String.valueOf((char)data);
            }
            json= new JSONObject(dataString);
            _rev=json.getString("_rev");
        }
        catch(NoDocumentException e){}
        catch (IOException j){}

        dbClient.remove(eid,_rev);
    }
    public void addAdmin(String eid, String password){
        App app= new App();
        app.Register(eid,password,0);
    }

}
