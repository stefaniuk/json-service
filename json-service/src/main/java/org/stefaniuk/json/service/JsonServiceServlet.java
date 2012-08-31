package org.stefaniuk.json.service;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * JSON Service servlet. NOT YET IMPLEMENTED !!!
 * </p>
 * <p>
 * This would be the jabsorb way of handling JSON-RPC request.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.1.0
 * @since 2012/08/31
 */
public class JsonServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(JsonServiceServlet.class);

    private static final String REGISTRY_NAME = "json-service-registry";

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        // get servlet context
        ServletContext sc = getServletContext();

        // create global registry
        sc.setAttribute(REGISTRY_NAME, new JsonServiceRegistry());

        logger.info("Global JSON-RPC registry creted.");

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        logger.debug("Incoming JSON-RPC request at " + request.getRequestURL().toString());

        // get servlet context
        //ServletContext sc = getServletContext();

        // get global registry
        //JsonServiceRegistry registry = (JsonServiceRegistry) sc.getAttribute(REGISTRY_NAME);
    }

}
