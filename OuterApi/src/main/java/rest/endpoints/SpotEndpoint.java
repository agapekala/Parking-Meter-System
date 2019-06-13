package rest.endpoints;

import Entities.Spot;
import dbservice.ISpotService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/spots")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SpotEndpoint {

    @EJB(lookup = "java:global/Database-1.0-SNAPSHOT/SpotService")
    private ISpotService spotService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        try {
            List<Spot> spots = spotService.getAllSpots();
            if (!spots.isEmpty()) {
                return Response.ok(spots).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch(Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id){
        try{
            Spot spot=spotService.getSpotById(id);
            if(spot!=null){
                return Response.ok(spot).build();
            }else return Response.status(Response.Status.NOT_FOUND).build();
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/zone/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByZoneId(@PathParam("id") final int id){
        try{
            List<Spot> spots=spotService.getAllSpots();
            List<Spot> zoneSpots=spots.stream()
                    .filter(spot ->spot.getZone().getZone_id()==id)
                    .collect(Collectors.toList());
            return Response.ok(zoneSpots).build();
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
