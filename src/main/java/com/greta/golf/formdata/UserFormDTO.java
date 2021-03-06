package com.greta.golf.formdata;

import com.greta.golf.models.Role;
import org.jsoup.Jsoup;

import java.util.List;

public class UserFormDTO {
    private long id;
    private boolean active;
    private String mail;
    private String login;
    private String password;
    private List <Role> groupe;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = Jsoup.parse(mail).text();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = Jsoup.parse(login).text();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Jsoup.parse(password).text();
    }
}
