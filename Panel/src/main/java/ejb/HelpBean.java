package ejb;


import Entities.Employee;
import Entities.Spot;
import exceptions.WrongPasswordException;
import hash.PasswordConverter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;


//ziarno przejściowe, decyduje którą funkcję wywołać na podstawie tego kto jest zalogowany
@Stateless
public class HelpBean {

    @Resource
    SessionContext ctx;

    @EJB
    private ManagerBean managerBean;

    @EJB
    private EmployeeBean employeeBean;


    public String test(){
        if(ctx.isCallerInRole("Manager")){
            return managerBean.test();
        }
        return employeeBean.test();
    }

    public List<Spot> getSpots(){
        if(ctx.isCallerInRole("Manager")){
            return managerBean.getSpots();
        }
        return employeeBean.getSpots(ctx.getCallerPrincipal().getName());
    }

    public List<Alert> getAlerts(){
        if(ctx.isCallerInRole("Manager")){
            //return managerBean.getAlerts();
            return new ArrayList<>();
        }
        return employeeBean.getAlerts(ctx.getCallerPrincipal().getName());
    }

    public void clearAlerts(){
        if(ctx.isCallerInRole("Manager")){
        }
        employeeBean.clearAlerts(ctx.getCallerPrincipal().getName());
    }

    public boolean isManager(){
        return ctx.isCallerInRole("Manager");
    }

    public void changePassword(String user, String oldPassword, String newPassword) throws WrongPasswordException{
        if(ctx.isCallerInRole("Manager")){
            managerBean.changePassword(user,newPassword);
        }else {
            employeeBean.changePassword(ctx.getCallerPrincipal().getName(), oldPassword, newPassword);
        }
    }

    public List<Employee> getAllEmployees(){
        if(ctx.isCallerInRole("Manager")){
            return managerBean.getEmployees();
        }
        return new ArrayList<>();
    }
}
