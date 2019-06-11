package soap;

import Entities.Spot;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ISpotInfo {

    @WebMethod
    List<Spot> getAllFreeSpots();

    @WebMethod
    List<Spot> getAllOccupiedSpots();

    @WebMethod
    boolean checkIfSpotIsOccupied(@WebParam int id);

    @WebMethod
    List<Spot> getAllOccupiedFromZone(@WebParam int zone);
}
