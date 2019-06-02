package sensors;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.ResponseWrapper;
import java.util.concurrent.Future;

@WebService
public interface NewMessageInt {

    @WebMethod
    Event sendMessage();

}
