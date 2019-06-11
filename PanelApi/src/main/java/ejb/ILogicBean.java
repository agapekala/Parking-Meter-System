package ejb;

import java.util.LinkedList;

public interface ILogicBean {

    void addNewAlert(String alert);

    LinkedList<Alert> getAlerts();

    void clearList();
}
