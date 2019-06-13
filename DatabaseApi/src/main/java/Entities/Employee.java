package Entities;

import hash.PasswordConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Employee implements Serializable{

    @Id
    @GeneratedValue
    private int employee_id;

    private String login;

    @Convert(converter = PasswordConverter.class)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
