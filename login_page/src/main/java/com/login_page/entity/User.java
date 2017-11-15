package com.login_page.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS", schema = "SYSTEM")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles;
//TODO переделать таблицы. сделать пароли отдельно. а роли в самой таблице. и связать так же
    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @JoinTable(name = "user_pass", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pass_id"))
    private Set<Password> password_t;

    public Set<Password> getPassword_t() {
        return password_t;
    }

    public void setPassword_t(Set<Password> password_t) {
        this.password_t = password_t;
    }

    public String getPasswordFromPass(){
        return this.getPassword_t().iterator().next().getPassword();
    }

    @Override
    public String toString() {
        return "username--"+username+"|password--"+password;
    }
}
