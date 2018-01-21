package com.sena.model;

import java.io.Serializable;

/**
 * Created by mustapha on 21/01/18.
 */

public class User implements Serializable {

    private int idUser;
    private String name;
    private String lastName;
    private String avatarUrl;


    public User(int idUser, String name, String lastName, String avatarUrl) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
