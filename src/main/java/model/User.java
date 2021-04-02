package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;
    private Role role;

    public User(){

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