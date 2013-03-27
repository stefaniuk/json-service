package org.stefaniuk.json.service.test.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stefaniuk.json.service.JsonServiceRegistry;

public class JsonRpcController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SERVICE_PACKAGE = "org.stefaniuk.json.service.test.service";

    private static final JsonServiceRegistry service = JsonServiceRegistry.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try {
            Class<?> clazz = Class.forName(SERVICE_PACKAGE + "." + getTestName(request) + "Service");
            service.register(clazz);
            service.getServiceMap(clazz, response);
            response.setStatus(200);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        response.setHeader("Last-Modified", date);
        response.setHeader("Cache-Control", "no-cache");

        try {
            Class<?> clazz = Class.forName(SERVICE_PACKAGE + "." + getTestName(request) + "Service");
            service.register(clazz);
            service.handle(request, response, clazz);
            response.setStatus(200);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getTestName(HttpServletRequest request) {

        String name = "";

        Pattern pattern = Pattern.compile("\\bhttp://127.0.0.1/([-a-zA-Z0-9_]*)/[-a-zA-Z0-9_/]*");
        Matcher matcher = pattern.matcher(request.getRequestURL());
        if(matcher.find()) {
            name = matcher.group(1);
        }

        return name;
    }

}
