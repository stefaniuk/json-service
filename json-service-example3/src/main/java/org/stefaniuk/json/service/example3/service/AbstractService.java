package org.stefaniuk.json.service.example3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.stefaniuk.json.service.JsonServiceRegistry;

/**
 * This is an intermediate class between a controller and a data access object.
 * 
 * @author Daniel Stefaniuk
 * @version 1.1.0
 * @since 2012/09/02
 * @param <D>
 */
public class AbstractService<D> {

    protected final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    /** Data access object. */
    protected D dao;

    /**
     * This setter should be called automatically by the Spring Framework when
     * bean is created.
     * 
     * @param dao Data access object
     */
    @Autowired
    public void setDao(D dao) {

        this.dao = dao;
    }

    /**
     * This setter should be called automatically by the Spring Framework when
     * bean is created.
     * 
     * @param jsonService JSON service (JSON-RPC)
     */
    @Autowired
    public void setServer(JsonServiceRegistry jsonService) {

        jsonService.register(this);
    }

}
