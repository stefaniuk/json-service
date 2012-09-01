package org.stefaniuk.json.service.example1;

import javax.servlet.http.HttpServletRequest;

import org.stefaniuk.json.service.JsonService;


public class Service {

    @JsonService
    public String echo(HttpServletRequest request, String text) {

        System.out.println("Request URI: " + request.getRequestURI());

        return text;
    }

}
