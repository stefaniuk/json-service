package org.stefaniuk.json.service.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stefaniuk.json.service.SampleClass;

public class SampleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SampleClass sc = new SampleClass("Hello World!");
        sc.setSampleField("This is a test.");
        response.getOutputStream().print(sc.getSampleField());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        process(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(200);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        process(request, response);

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        response.setHeader("Last-Modified", date);
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(200);
    }

}
