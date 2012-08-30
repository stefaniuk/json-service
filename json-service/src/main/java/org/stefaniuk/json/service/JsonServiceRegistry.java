package org.stefaniuk.json.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * This is a broker class that passes request to a registered bean or returns
 * service mapping descriptor.
 * 
 * @author Daniel Stefaniuk
 */
public class JsonServiceRegistry {

    private static ObjectMapper mapper = new ObjectMapper();

    private Map<String, JsonServiceTarget> registry =
        Collections.synchronizedMap(new HashMap<String, JsonServiceTarget>());

    /**
     * @param clazz
     * @return
     */
    public JsonServiceRegistry register(Class<?> clazz) {

        String name = clazz.getName();
        if(!registry.containsKey(name)) {
            registry.put(name, new JsonServiceTarget(clazz));
        }

        return this;
    }

    /**
     * @param obj
     * @return
     */
    public JsonServiceRegistry register(Object obj) {

        String name = obj.getClass().getName();
        if(!registry.containsKey(name)) {
            registry.put(name, new JsonServiceTarget(obj));
        }

        return this;
    }

    /**
     * @param clazz
     * @return
     */
    public JsonServiceRegistry unregister(Class<?> clazz) {

        String name = clazz.getName();
        registry.remove(name);

        return this;
    }

    /**
     * @param obj
     * @return
     */
    public JsonServiceRegistry unregister(Object obj) {

        String name = obj.getClass().getName();
        registry.remove(name);

        return this;
    }

    /**
     * @param clazz
     * @return
     */
    private JsonServiceTarget lookup(Class<?> clazz) {

        return registry.get(clazz.getName());
    }

    /**
     * @param clazz
     * @param os
     * @return
     */
    public OutputStream getServiceMap(Class<?> clazz, OutputStream os) {

        try {
            mapper.writeValue(os, lookup(clazz).getServiceMap());
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param is
     * @param os
     * @param clazz
     * @param method
     * @param args
     * @return
     */
    public OutputStream handle(InputStream is, OutputStream os, Class<?> clazz, String method, Object... args) {

        try {
            handleNode(null, os, lookup(clazz), method, args);
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param is
     * @param os
     * @param clazz
     * @return
     */
    public OutputStream handle(InputStream is, OutputStream os, Class<?> clazz) {

        try {
            handleNode(null, mapper.readValue(is, JsonNode.class), os, lookup(clazz));
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param request
     * @param os
     * @param clazz
     * @param method
     * @param args
     * @return
     */
    public OutputStream handle(HttpServletRequest request, OutputStream os, Class<?> clazz, String method,
            Object... args) {

        try {
            handleNode(request, os, lookup(clazz), method, args);
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param request
     * @param os
     * @param clazz
     * @return
     */
    public OutputStream handle(HttpServletRequest request, OutputStream os, Class<?> clazz) {

        try {
            handleNode(request, mapper.readValue(request.getInputStream(), JsonNode.class), os, lookup(clazz));
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param request
     * @param os
     * @param service
     * @param method
     * @param args
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleNode(HttpServletRequest request, OutputStream os, JsonServiceTarget service, String method,
            Object... args)
            throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        handleObject(request, os, service, method, args);
    }

    /**
     * @param request
     * @param requestNode
     * @param os
     * @param service
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleNode(HttpServletRequest request, JsonNode requestNode, OutputStream os, JsonServiceTarget service)
            throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        if(requestNode.isObject()) {
            handleObject(request, ObjectNode.class.cast(requestNode), os, service);
        }
        else if(requestNode.isArray()) {
            handleArray(request, ArrayNode.class.cast(requestNode), os, service);
        }
        else {
            throw new JsonServiceException(JsonServiceError.INVALID_REQUEST);
        }
    }

    /**
     * @param request
     * @param requestNode
     * @param os
     * @param service
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleArray(HttpServletRequest request, ArrayNode requestNode, OutputStream os,
            JsonServiceTarget service)
            throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        for(int i = 0; i < requestNode.size(); i++) {
            handleNode(request, requestNode.get(i), os, service);
        }
    }

    /**
     * @param request
     * @param os
     * @param service
     * @param method
     * @param args
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    private void handleObject(HttpServletRequest request, OutputStream os, JsonServiceTarget service, String method,
            Object... args)
            throws IllegalAccessException, InvocationTargetException, JsonGenerationException, JsonMappingException,
            IOException {

        JsonNode responseNode = service.process(request, method, args);

        mapper.writeValue(os, responseNode);
    }

    /**
     * @param request
     * @param requestNode
     * @param os
     * @param service
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    private void handleObject(HttpServletRequest request, ObjectNode requestNode, OutputStream os,
            JsonServiceTarget service)
            throws IllegalAccessException, InvocationTargetException, JsonGenerationException, JsonMappingException,
            IOException {

        JsonNode responseNode = service.process(request, requestNode);

        mapper.writeValue(os, responseNode);
    }

}
