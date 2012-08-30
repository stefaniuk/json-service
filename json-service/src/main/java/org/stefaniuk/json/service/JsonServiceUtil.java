package org.stefaniuk.json.service;

import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
 * Utility methods.
 * 
 * @author Daniel Stefaniuk
 */
public class JsonServiceUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    private static JsonFactory jsonFactory = new JsonFactory();

    /**
     * @param bos
     * @param response
     */
    public static void setHeadersForServiceMap(BufferedOutputStream bos, HttpServletResponse response) {

        response.setHeader("Content-Type", "application/json; charset=utf-8");
        response.setHeader("Content-Length", Integer.toString(bos.toString().length()));
        response.setStatus(200);
    }

    /**
     * @param bos
     * @param response
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
     * @param bos
     * @param response
     * @param status
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
     * @param bos
     * @return
     */
    public static ResponseEntity<String> getResponseEntityForServiceMap(BufferedOutputStream bos) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Content-Length", Integer.toString(bos.toString().length()));

        return new ResponseEntity<String>(bos.toString(), headers, HttpStatus.OK);
    }

    /**
     * @param bos
     * @return
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
     * @param bos
     * @param status
     * @return
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
     * @param <T>
     * @param jsonAsString
     * @param pojoClass
     * @return
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) throws JsonMappingException,
            JsonParseException, IOException {

        return mapper.readValue(jsonAsString, pojoClass);
    }

    /**
     * @param <T>
     * @param fr
     * @param pojoClass
     * @return
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> Object fromJson(FileReader fr, Class<T> pojoClass) throws JsonParseException, IOException {

        return mapper.readValue(fr, pojoClass);
    }

    /**
     * @param pojo
     * @param prettyPrint
     * @return
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
     * @param pojo
     * @param fw
     * @param prettyPrint
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
