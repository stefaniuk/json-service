package org.stefaniuk.json.service.test.bean;

public class User {

    private String name;

    private String password;

    private String fullname;

    private String email;

    public User() {

    }

    public User(String name, String password, String fullname, String email) {

        super();
        this.name = name;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getFullname() {

        return fullname;
    }

    public void setFullname(String fullname) {

        this.fullname = fullname;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

}
