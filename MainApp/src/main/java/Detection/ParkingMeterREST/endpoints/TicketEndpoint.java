package Detection.ParkingMeterREST.endpoints;


import Detection.ParkingMeterREST.entities.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;

@Path("/tickets")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TicketEndpoint {

    //testowa baza danych
    private LinkedList<Ticket> tickets=new LinkedList<>();

    //funkcja tylko testowa
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok("Pobieranie biletu").build();
    }

    //wysyłanie danych o nowym bilecie
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTicket(Ticket ticket){
        tickets.add(ticket);
        //tutaj powinno byc zupdateowanie prawdziwej bazy danych
        //zmiana miejsca na opłacone i ustawienie daty ważności
        return Response.ok("Kupiono bilet "+ticket.getSpot_id()).build();
    }
}
