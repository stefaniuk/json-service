package com.code4ge.json.service;

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

import com.code4ge.json.service.JsonServiceObject.OutputType;

/**
 * This is a broker class that passes a request to a registered bean or returns
 * service mapping descriptor.
 * 
 * @author Daniel Stefaniuk
 */
public class JsonServiceServer {

    private static ObjectMapper mapper = new ObjectMapper();

    private Map<String, JsonServiceObject> registry =
        Collections.synchronizedMap(new HashMap<String, JsonServiceObject>());

    /**
     * @param clazz
     * @return
     */
    public JsonServiceServer register(Class<?> clazz) {

        String name = clazz.getName();
        if(!registry.containsKey(name)) {
            registry.put(name, new JsonServiceObject(clazz));
        }

        return this;
    }

    /**
     * @param obj
     * @return
     */
    public JsonServiceServer register(Object obj) {

        String name = obj.getClass().getName();
        if(!registry.containsKey(name)) {
            registry.put(name, new JsonServiceObject(obj));
        }

        return this;
    }

    /**
     * @param clazz
     * @return
     */
    public JsonServiceServer unregister(Class<?> clazz) {

        String name = clazz.getName();
        registry.remove(name);

        return this;
    }

    /**
     * @param obj
     * @return
     */
    public JsonServiceServer unregister(Object obj) {

        String name = obj.getClass().getName();
        registry.remove(name);

        return this;
    }

    /**
     * @param clazz
     * @return
     */
    private JsonServiceObject lookup(Class<?> clazz) {

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
     * @param clazz
     * @param is
     * @param os
     * @param outputType
     * @return
     */
    public OutputStream handle(Class<?> clazz, InputStream is, OutputStream os, OutputType outputType) {

        try {
            handleNode(lookup(clazz), null, mapper.readValue(is, JsonNode.class), os, outputType);
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param clazz
     * @param is
     * @param os
     * @return
     */
    public OutputStream handle(Class<?> clazz, InputStream is, OutputStream os) {

        return handle(clazz, is, os, OutputType.JSON_RPC_2_0);
    }

    /**
     * @param clazz
     * @param request
     * @param os
     * @param outputType
     * @return
     */
    public OutputStream handle(Class<?> clazz, HttpServletRequest request, OutputStream os, OutputType outputType) {

        try {
            handleNode(
                lookup(clazz),
                request,
                mapper.readValue(request.getInputStream(), JsonNode.class),
                os,
                outputType);
        }
        catch(Exception e) {
            // TODO
            e.printStackTrace(System.err);
        }

        return os;
    }

    /**
     * @param clazz
     * @param request
     * @param os
     * @return
     */
    public OutputStream handle(Class<?> clazz, HttpServletRequest request, OutputStream os) {

        return handle(clazz, request, os, OutputType.JSON_RPC_2_0);
    }

    /**
     * @param service
     * @param request
     * @param requestNode
     * @param os
     * @param outputType
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleNode(JsonServiceObject service, HttpServletRequest request, JsonNode requestNode,
            OutputStream os, OutputType outputType)
            throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        if(requestNode.isObject()) {
            handleObject(service, request, ObjectNode.class.cast(requestNode), os, outputType);
        }
        else if(requestNode.isArray()) {
            handleArray(service, request, ArrayNode.class.cast(requestNode), os, outputType);
        }
        else {
            throw new JsonServiceException(JsonServiceError.INVALID_REQUEST);
        }
    }

    /**
     * @param service
     * @param request
     * @param requestNode
     * @param os
     * @param outputType
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonServiceException
     */
    private void handleArray(JsonServiceObject service, HttpServletRequest request, ArrayNode requestNode,
            OutputStream os, OutputType outputType)
            throws JsonGenerationException, JsonMappingException, IOException, IllegalAccessException,
            InvocationTargetException, JsonServiceException {

        for(int i = 0; i < requestNode.size(); i++) {
            handleNode(service, request, requestNode.get(i), os, outputType);
        }
    }

    /**
     * @param service
     * @param request
     * @param requestNode
     * @param os
     * @param outputType
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    private void handleObject(JsonServiceObject service, HttpServletRequest request, ObjectNode requestNode,
            OutputStream os, OutputType outputType)
            throws IllegalAccessException, InvocationTargetException, JsonGenerationException, JsonMappingException,
            IOException {

        ObjectNode responseNode = service.process(request, requestNode, outputType);

        mapper.writeValue(os, responseNode);
    }

}
