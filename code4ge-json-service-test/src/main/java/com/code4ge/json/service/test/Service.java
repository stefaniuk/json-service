package com.code4ge.json.service.test;

import javax.servlet.http.HttpServletRequest;

import com.code4ge.json.service.JsonService;

public class Service {

    @JsonService
    public String echo(HttpServletRequest request, String text) {

        System.out.println("Request URI: " + request.getRequestURI());

        return text;
    }

}
