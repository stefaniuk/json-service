package org.stefaniuk.json.service.test.service;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.test.bean.User;

public class UserService {

    @JsonService
    public Boolean login(User u) {

        boolean valid = true;

        if(!"daniel".equals(u.getName())) {
            valid = false;
        }
        if(!"53cr3t".equals(u.getPassword())) {
            valid = false;
        }
        if(!"Daniel Stefaniuk".equals(u.getFullname())) {
            valid = false;
        }
        if(!"daniel.stefaniuk@gmail.com".equals(u.getEmail())) {
            valid = false;
        }

        return valid;
    }

}
