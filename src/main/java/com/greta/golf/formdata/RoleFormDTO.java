package com.greta.golf.formdata;

import org.jsoup.Jsoup;

public class RoleFormDTO {
    private long id;
    private String name;
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Jsoup.parse(name).text();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Jsoup.parse(role).text();
    }
}
