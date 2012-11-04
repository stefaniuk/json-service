package org.stefaniuk.json.service.test.service;

import org.stefaniuk.json.service.JsonService;

public class EchoService {

    @JsonService
    public String echo(String text) {

        return "Echo service says: " + text;
    }

}
