package org.stefaniuk.json.service.example1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stefaniuk.json.service.JsonServiceRegistry;

public class JsonRpcController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static JsonServiceRegistry service = new JsonServiceRegistry();

    static {
        service.register(CalculatorService.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        service.getServiceMap(CalculatorService.class, response);
        response.setStatus(200);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        response.setHeader("Last-Modified", date);
        response.setHeader("Cache-Control", "no-cache");

        service.handle(request, response, CalculatorService.class);
        response.setStatus(200);
    }

}
