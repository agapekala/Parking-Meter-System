import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.Scanner;


//ta klasa łączy się z restem w MainApp/Detection/ParkingMeterREST i wysyła nowy bilet
//dane o bilecie pobierane z interfejsu tekstowego

public class RestClient {

    //bardzo basic przykład dodania biletu z interfejsu tekstowego
    public static void main(String[] args) {
        System.out.println("Parkometr");
        while (true){
            addTicket();
            //getTicket();
        }

    }
    public static void addTicket(){
        Scanner read = new Scanner(System.in);
        System.out.println("Godzina zakupu: ");
        String payment_time=read.nextLine();
        System.out.println("Czas ważności biletu: ");
        int duration=Integer.parseInt(read.nextLine());
        System.out.println("Miejsce: ");
        int spot_id=Integer.parseInt(read.nextLine());
        System.out.println("Strefa: ");
        int zone_id=Integer.parseInt(read.nextLine());

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/MainApp_war_exploded/rest/tickets");
        Response response = target.request()
                .post(Entity.entity("{\"payment_time\": \""+payment_time +"\", \"duration\":"+duration+"," +
                                " \"spot_id\":"+spot_id+", \"zone_id\":"+zone_id+"}"
                        , MediaType.APPLICATION_JSON));
        int status = response.getStatus();

        System.out.println("Status code: " + status);
        if(status==200){
            System.out.println(response.readEntity(String.class));
        }else{
            System.out.println("Nie udało się dodać biletu");
        }
    }

    public static void getTicket(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        Response response = client.target("http://localhost:8080/MainApp_war_exploded/rest/tickets").request().get();
        int status = response.getStatus();
        System.out.println("Status code: " + status);
    }
}
