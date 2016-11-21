package com.sunshine.rxjavademo.bean;

/**
 * bean
 * Created by Sunshine on 2016/8/30.
 */
public class Contributor {

    private String login;
    private int id;
    private int contributions;

    public Contributor(String login, int id, int contributions) {
        this.login = login;
        this.id = id;
        this.contributions = contributions;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

}
