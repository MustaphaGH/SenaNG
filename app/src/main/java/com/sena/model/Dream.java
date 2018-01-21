package com.sena.model;

import java.io.Serializable;

/**
 * Created by mustapha on 21/01/18.
 */

public class Dream implements Serializable {

    private int idDream;
    private String dreamContent;
    private String postDate;
    private User owner;

    public Dream(int idDream, String dreamContent, String postDate, User owner) {
        this.idDream = idDream;
        this.dreamContent = dreamContent;
        this.postDate = postDate;
        this.owner = owner;
    }

    public Dream(int idDream, String dreamContent, String postDate) {
        this.idDream = idDream;
        this.dreamContent = dreamContent;
        this.postDate = postDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getIdDream() {
        return idDream;
    }

    public void setIdDream(int idDream) {
        this.idDream = idDream;
    }

    public String getDreamContent() {
        return dreamContent;
    }

    public void setDreamContent(String dreamContent) {
        this.dreamContent = dreamContent;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
