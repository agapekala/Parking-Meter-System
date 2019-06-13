package mobile.rest.endpoints;

import mobile.rest.objects.Alert;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alert")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AlertEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok("pobieranie alert").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendAlert(Alert alert){
        try{
            System.out.println("MOBILE ALERT:");
            alert.printAlert();
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(404).build();
        }

    }

}
