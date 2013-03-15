package org.stefaniuk.json.service.test.service;

import java.util.List;

import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.test.bean.User;

public class ListService {

    @JsonService
    public List<User> echo(List<User> users) {

        for(User user: users) {
            user.setPassword("******");
        }

        return users;
    }

}
