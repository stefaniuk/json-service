package org.stefaniuk.json.service.example3.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stefaniuk.json.service.JsonServiceRegistry;
import org.stefaniuk.json.service.JsonServiceUtil;

/**
 * <p>
 * MVCS abstract controller.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.1.0
 * @since 2012/09/02
 */
public abstract class AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    /** JSON service (JSON-RPC) object. */
    @Autowired
    private JsonServiceRegistry jsonService;

    /**
     * JSON service (JSON-RPC) call.
     * 
     * @param request HTTP request
     * @param response HTTP response
     * @param service Name of the service.
     * @return ResponseEntity<String>
     * @throws Exception
     */
    @RequestMapping(value = "service/{service}")
    public abstract ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String service) throws Exception;

    /**
     * JSON service (JSON-RPC) call.
     * 
     * @param request HTTP request
     * @param response HTTP response
     * @return ResponseEntity<String>
     * @throws Exception
     */
    @RequestMapping(value = "service")
    public ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return service(request, response, "default");
    }

    /**
     * Handles JSON-RPC communication.
     * 
     * @param request HTTP request
     * @param response HTTP response
     * @param clazz Method from this class will be invoked.
     * @return ResponseEntity<String>
     * @throws IOException
     */
    protected ResponseEntity<String> handleJsonRpc(HttpServletRequest request, HttpServletResponse response,
            Class<?> clazz) throws IOException {

        return JsonServiceUtil.handle(jsonService, request, response, clazz);

    }

}
