package org.demo.model;

/**
 * Created by jzchen on 2017/4/15.
 */
public class User extends CoreBase{

    public String username;

    private String password;

    public String truename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }
}
