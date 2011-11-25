package com.code4ge.json.service.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code4ge.json.service.JsonServiceServer;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static JsonServiceServer service = new JsonServiceServer();

    static {
        service.register(Service.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try {
            service.getServiceMap(Service.class, response.getOutputStream());
            response.setStatus(200);
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
            response.setStatus(500);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        response.setHeader("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        response.setHeader("Last-Modified", date);
        response.setHeader("Cache-Control", "no-cache");

        try {
            service.handle(Service.class, request, response.getOutputStream());
            response.setStatus(200);
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
            response.setStatus(500);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Content-Type", "text/html");
        response.setStatus(405);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Content-Type", "text/html");
        response.setStatus(405);
    }

}
