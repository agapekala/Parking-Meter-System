import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ejb.Stateless;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//wysyła powiadomienia przez rest do urządzeń mobilnych

@Stateless
public class RestSender {

    private final String url="http://localhost:8080/mobile/rest/alert";

    public void sendAlert(String alert){

        Date currentDate = new Date();
        DateFormat dateFormat= new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        String date=dateFormat.format(currentDate);

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(url);
        Response response = target.request()
                .post(Entity.entity("{\"body\":\""+alert+"\"," +
                                " \"time\":\""+date+"\"}"
                        , MediaType.APPLICATION_JSON));
        int status = response.getStatus();

        System.out.println("Status code: " + status);
        if(status==200){
            System.out.println(response.readEntity(String.class));
        }else{
            System.out.println("Nie udało się przesłać powiadomienia");
        }
    }

}
