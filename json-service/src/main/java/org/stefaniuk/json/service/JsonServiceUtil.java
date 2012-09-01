package org.stefaniuk.json.service;

import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * JSON service utility methods.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.0.0
 * @since 2010/09/20
 */
public class JsonServiceUtil {

    /**
     * This object provides functionality for conversion between Java objects
     * and JSON.
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * This object is used to configure and construct JSON reader and writer
     * instances.
     */
    private static JsonFactory jsonFactory = new JsonFactory();

    /**
     * This method sets HTTP response headers adequate for Service Mapping
     * Description.
     * 
     * @param bos Output stream
     * @param response HTTP response object
     */
    public static void setHeadersForServiceMap(BufferedOutputStream bos, HttpServletResponse response) {

        response.setHeader("Content-Type", "application/json; charset=utf-8");
        response.setHeader("Content-Length", Integer.toString(bos.toString().length()));
        response.setStatus(200);
    }

    /**
     * This method sets HTTP response headers adequate for JSON-RPC method call.
     * 
     * @param bos Output stream
     * @param response HTTP response object
     */
    public static void setHeadersForMethodCall(BufferedOutputStream bos, HttpServletResponse response) {

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        response.setHeader("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        response.setHeader("Last-Modified", date);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        response.setHeader("Content-Length", Integer.toString(bos.toString().length()));
        response.setStatus(200);
    }

    /**
     * This method sets HTTP response headers adequate for JSON-RPC method call.
     * 
     * @param bos Output stream
     * @param response HTTP response object
     * @param status HTTP response code
     */
    public static void setHeadersForMethodCall(BufferedOutputStream bos, HttpServletResponse response, int status) {

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        response.setHeader("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        response.setHeader("Last-Modified", date);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        response.setHeader("Content-Length", Integer.toString(bos.toString().length()));
        response.setStatus(status);
    }

    /**
     * This method sets HTTP response headers adequate for Service Mapping
     * Description and returns ResponseEntity to be used by Spring Framework.
     * 
     * @param bos Output stream
     * @return Returns ResponseEntity to be used by Spring Framework.
     */
    public static ResponseEntity<String> getResponseEntityForServiceMap(BufferedOutputStream bos) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Content-Length", Integer.toString(bos.toString().length()));

        return new ResponseEntity<String>(bos.toString(), headers, HttpStatus.OK);
    }

    /**
     * This method sets HTTP response headers adequate for JSON-RPC method call
     * and returns ResponseEntity to be used by Spring Framework.
     * 
     * @param bos Output stream
     * @return Returns ResponseEntity to be used by Spring Framework.
     */
    public static ResponseEntity<String> getResponseEntityForMethodCall(BufferedOutputStream bos) {

        HttpHeaders headers = new HttpHeaders();

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        headers.set("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        headers.set("Last-Modified", date);
        headers.set("Cache-Control", "no-cache");
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Content-Length", Integer.toString(bos.toString().length()));

        return new ResponseEntity<String>(bos.toString(), headers, HttpStatus.OK);
    }

    /**
     * This method sets HTTP response headers adequate for JSON-RPC method call
     * and returns ResponseEntity to be used by Spring Framework.
     * 
     * @param bos Output stream
     * @param status HTTP response code
     * @return Returns ResponseEntity to be used by Spring Framework.
     */
    public static ResponseEntity<String> getResponseEntityForMethodCall(BufferedOutputStream bos, HttpStatus status) {

        HttpHeaders headers = new HttpHeaders();

        String date = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")).format(Calendar.getInstance().getTime());

        headers.set("Expires", "Mon, 01 Jan 2000 00:00:00 GMT");
        headers.set("Last-Modified", date);
        headers.set("Cache-Control", "no-cache");
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Content-Length", Integer.toString(bos.toString().length()));

        return new ResponseEntity<String>(bos.toString(), headers, status);
    }

    /**
     * Handles HTTP request.
     * 
     * @param service Service registry object
     * @param request HTTP request object
     * @param response HTTP response object
     * @param clazz Class
     * @return Returns ResponseEntity to be used by Spring Framework.
     * @throws IOException
     */
    public static ResponseEntity<String> handle(JsonServiceRegistry service, HttpServletRequest request,
            HttpServletResponse response, Class<?> clazz) throws IOException {

        ResponseEntity<String> re = null;

        BufferedOutputStream bos = (BufferedOutputStream) service.handle(request, response, clazz);
        String method = request.getMethod();
        if(method.equals("GET")) {
            re = JsonServiceUtil.getResponseEntityForServiceMap(bos);
        }
        else {
            re = JsonServiceUtil.getResponseEntityForMethodCall(bos);

        }

        return re;
    }

    /**
     * Creates Java POJO object from JSON string.
     * 
     * @param <T>
     * @param jsonAsString JSON string
     * @param pojoClass POJO class
     * @return POJO object
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) throws JsonMappingException,
            JsonParseException, IOException {

        return mapper.readValue(jsonAsString, pojoClass);
    }

    /**
     * Creates Java POJO object from JSON string.
     * 
     * @param <T>
     * @param fr File stream to read JSON string.
     * @param pojoClass POJO class
     * @return POJO object
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> Object fromJson(FileReader fr, Class<T> pojoClass) throws JsonParseException, IOException {

        return mapper.readValue(fr, pojoClass);
    }

    /**
     * Serialises Java POJO object to JSON string.
     * 
     * @param pojo POJO object
     * @param prettyPrint JSON pretty print
     * @return JSON string
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws IOException
     */
    public static String toJson(Object pojo, boolean prettyPrint) throws JsonMappingException, JsonGenerationException,
            IOException {

        StringWriter sw = new StringWriter();
        JsonGenerator jg = jsonFactory.createJsonGenerator(sw);
        if(prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        mapper.writeValue(jg, pojo);

        return sw.toString();
    }

    /**
     * Serialises Java POJO object to JSON string.
     * 
     * @param pojo POJO object
     * @param fw File stream to write JSON string.
     * @param prettyPrint JSON pretty print
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws IOException
     */
    public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) throws JsonMappingException,
            JsonGenerationException, IOException {

        JsonGenerator jg = jsonFactory.createJsonGenerator(fw);
        if(prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        mapper.writeValue(jg, pojo);
    }

}
