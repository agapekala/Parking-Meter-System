package Detection.ParkinMeterREST.endpoints;

import Detection.ejb.detection.impl.ReceiveEvent;
import Entities.Ticket;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tickets")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TicketEndpoint {

    @Inject
    private ReceiveEvent receiver;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok("Pobieranie biletu").build();
    }

    //wysyłanie danych o nowym bilecie
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTicket(Ticket ticket){
        try {
            receiver.receiveParkingMeterEvent(ticket);
            return Response.ok("Kupiono bilet "+ticket.getSpot_id()).build();
        }catch(Exception e){
            return Response.status(404).build();
        }

    }
}
