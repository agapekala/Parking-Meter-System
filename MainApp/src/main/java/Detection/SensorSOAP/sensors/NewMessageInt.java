package Detection.SensorSOAP.sensors;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface NewMessageInt {

    @WebMethod
    void sendMessage(@WebParam Event event);

}
