package org.stefaniuk.json.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * JSON service registry.
 * </p>
 * <p>
 * A registry class that holds all classes provided by a developer that are
 * available to a JSON-RPC client. This is the main class that should be
 * utilised in a user code. Multiple instances can be created or the singleton
 * pattern could be used. The last one is probably more desirable in most use
 * cases.
 * </p>
 * <p>
 * A class can be registered/unregistered by passing a class name as an argument
 * of {@link #register(Class) register}/{@link #unregister(Class) unregister}
 * method. Instance of that class will be created by the service invoker object
 * only once, when a first call is made to any of the exposed methods or when
 * service mapping description is produced.
 * </p>
 * <p>
 * It is user's responsibility to pass incoming HTTP request to the registry
 * object for method to be executed. From inside of a Java servlet this can be
 * achieved by calling
 * {@link #handle(HttpServletRequest, HttpServletResponse, Class) handle} method
 * on the registry object itself:
 * 
 * <pre>
 * registry.handle(request, response, NameOfClass.class);
 * </pre>
 * 
 * From a controller (using Spring Framework) this can be done by calling static
 * method
 * {@link JsonServiceUtil#handle(JsonServiceRegistry, HttpServletRequest, HttpServletResponse, Class)
 * handle} from {@link JsonServiceUtil} class:
 * </p>
 * 
 * <pre>
 * JsonServiceUtil.handle(registry, request, response, NameOfClass.class);
 * </pre>
 * 
 * @author Daniel Stefaniuk
 * @version 1.0.0
 * @since 2010/09/20
 */
public class JsonServiceRegistry {

    private final Logger logger = LoggerFactory.getLogger(JsonServiceRegistry.class);

    /**
     * This object provides functionality for conversion between Java objects
     * and JSON.
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /** Collection of all the registered JSON-RPC classes. */
    private Map<String, JsonServiceInvoker> registry =
        Collections.synchronizedMap(new HashMap<String, JsonServiceInvoker>());

    /**
     * Registers a class to make it available to a JSON-RPC client.
     * 
     * @param clazz Class
     * @return Returns {@link JsonServiceRegistry} object.
     */
    public JsonServiceRegistry register(Class<?> clazz) {

        String name = clazz.getName();
        if(!registry.containsKey(name)) {
            registry.put(name, new JsonServiceInvoker(clazz));
            logger.info("JSON-RPC registered class: " + name);
        }

        return this;
    }

    /**
     * Registers class by passing its instance to make it available to a
     * JSON-RPC client.
     * 
     * @param obj Instance of a class.
     * @return Returns {@link JsonServiceRegistry} object.
     */
    public JsonServiceRegistry register(Object obj) {

        String name = obj.getClass().getName();
        if(!registry.containsKey(name)) {
            registry.put(name, new JsonServiceInvoker(obj));
            logger.info("JSON-RPC registered class: " + name);
        }

        return this;
    }

    /**
     * Unregisters class.
     * 
     * @param clazz Class
     * @return Returns {@link JsonServiceRegistry} object.
     */
    public JsonServiceRegistry unregister(Class<?> clazz) {

        String name = clazz.getName();
        if(registry.containsKey(name)) {
            registry.remove(name);
            logger.info("JSON-RPC unregistered class: " + name);
        }

        return this;
    }

    /**
     * Unregisters class by passing its instance.
     * 
     * @param obj Instance of a class.
     * @return Returns {@link JsonServiceRegistry} object.
     */
    public JsonServiceRegistry unregister(Object obj) {

        String name = obj.getClass().getName();
        if(registry.containsKey(name)) {
            registry.remove(name);
            logger.info("JSON-RPC unregistered class: " + name);
        }

        return this;
    }

    /**
     * Looks up class in registry.
     * 
     * @param clazz Class
     * @return Returns {@link JsonServiceInvoker} object.
     */
    private JsonServiceInvoker lookup(Class<?> clazz) {

        return registry.get(clazz.getName());
    }

    /**
     * Produces Service Mapping Description for a given JSON-RPC class.
     * 
     * @param clazz Class
     * @param os Output stream
     * @return Returns output stream.
     */
    public OutputStream getServiceMap(Class<?> clazz, OutputStream os) {

        try {
            mapper.writeValue(os, lookup(clazz).getServiceMap());
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * Produces Service Mapping Description for a given JSON-RPC class.
     * 
     * @param clazz Class
     * @param response HTTP response
     * @return Returns output stream.
     */
    public OutputStream getServiceMap(Class<?> clazz, HttpServletResponse response) {

        OutputStream os = null;

        try {
            os = getServiceMap(clazz, response.getOutputStream());
        }
        catch(IOException e) {
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * Handles request as an input stream.
     * 
     * @param is Input stream
     * @param os Output stream
     * @param clazz Class
     * @return Returns output stream.
     */
    public OutputStream handle(InputStream is, OutputStream os, Class<?> clazz) {

        try {
            handleNode(null, mapper.readValue(is, JsonNode.class), os, lookup(clazz));
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * Handles request as an input stream.
     * 
     * @param is Input stream
     * @param os Output stream
     * @param clazz Class
     * @param method Method to call.
     * @param args Arguments passed to the method.
     * @return Returns output stream.
     */
    public OutputStream handle(InputStream is, OutputStream os, Class<?> clazz, String method, Object... args) {

        try {
            handleNode(null, os, lookup(clazz), method, args);
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * Handles HTTP request.
     * 
     * @param request HTTP request
     * @param response HTTP response
     * @param clazz Class
     * @return Returns output stream.
     * @throws IOException
     */
    public OutputStream handle(HttpServletRequest request, HttpServletResponse response, Class<?> clazz) {

        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            String method = request.getMethod();
            if(method.equals("GET")) {
                getServiceMap(clazz, bos);
            }
            else {
                handle(request, bos, clazz);
            }
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }

        return bos;
    }

    /**
     * Handles HTTP request.
     * 
     * @param request HTTP request
     * @param os Output stream
     * @param clazz Class
     * @return Returns output stream.
     */
    public OutputStream handle(HttpServletRequest request, OutputStream os, Class<?> clazz) {

        try {
            handleNode(request, mapper.readValue(request.getInputStream(), JsonNode.class), os, lookup(clazz));
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * Handles HTTP request.
     * 
     * @param request HTTP request
     * @param os Output stream
     * @param clazz Class
     * @param method Method to call.
     * @param args Arguments passed to the method.
     * @return Returns output stream.
     */
    public OutputStream handle(HttpServletRequest request, OutputStream os, Class<?> clazz, String method,
            Object... args) {

        try {
            handleNode(request, os, lookup(clazz), method, args);
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * Handles HTTP request.
     * 
     * @param request HTTP request
     * @param os Output stream
     * @param invoker This is the service invoker object.
     * @param method Method to call.
     * @param args Arguments passed to the method.
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleNode(HttpServletRequest request, OutputStream os, JsonServiceInvoker invoker, String method,
            Object... args) throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        handleObject(request, os, invoker, method, args);
    }

    /**
     * Handles HTTP request.
     * 
     * @param request HTTP request
     * @param requestNode HTTP request provided as {@link JsonNode}
     * @param os Output stream
     * @param invoker This is the service invoker object.
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleNode(HttpServletRequest request, JsonNode requestNode, OutputStream os,
            JsonServiceInvoker invoker) throws JsonGenerationException, JsonMappingException, IOException,
            IllegalAccessException, InvocationTargetException, JsonServiceException {

        if(requestNode.isObject()) {
            handleObject(request, ObjectNode.class.cast(requestNode), os, invoker);
        }
        else if(requestNode.isArray()) {
            handleArray(request, ArrayNode.class.cast(requestNode), os, invoker);
        }
        else {
            throw new JsonServiceException(JsonServiceError.INVALID_REQUEST);
        }
    }

    /**
     * Handles HTTP request.
     * 
     * @param request
     * @param requestNode
     * @param os
     * @param invoker
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleArray(HttpServletRequest request, ArrayNode requestNode, OutputStream os,
            JsonServiceInvoker invoker)
            throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        for(int i = 0; i < requestNode.size(); i++) {
            handleNode(request, requestNode.get(i), os, invoker);
        }
    }

    /**
     * Handles HTTP request.
     * 
     * @param request
     * @param os
     * @param invoker
     * @param method
     * @param args
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    private void handleObject(HttpServletRequest request, OutputStream os, JsonServiceInvoker invoker, String method,
            Object... args) throws IllegalAccessException, InvocationTargetException, JsonGenerationException,
            JsonMappingException, IOException {

        JsonNode responseNode = invoker.process(request, method, args);

        mapper.writeValue(os, responseNode);
    }

    /**
     * Handles HTTP request.
     * 
     * @param request
     * @param requestNode
     * @param os
     * @param invoker
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    private void handleObject(HttpServletRequest request, ObjectNode requestNode, OutputStream os,
            JsonServiceInvoker invoker) throws IllegalAccessException, InvocationTargetException,
            JsonGenerationException, JsonMappingException, IOException {

        JsonNode responseNode = invoker.process(request, requestNode);

        mapper.writeValue(os, responseNode);
    }

}
