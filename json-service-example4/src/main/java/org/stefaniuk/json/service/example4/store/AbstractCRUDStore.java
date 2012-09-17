package org.stefaniuk.json.service.example4.store;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.stefaniuk.json.service.JsonService;
import org.stefaniuk.json.service.JsonServiceRegistry;

public abstract class AbstractCRUDStore<M, D> {

    protected final Logger logger = LoggerFactory.getLogger(AbstractCRUDStore.class);

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
    public void setService(JsonServiceRegistry jsonService) {

        jsonService.register(this);
    }

    @JsonService
    public abstract List<M> select(QueryStore query);

    @JsonService
    public abstract Integer update(Integer id, M model);

    @JsonService
    public abstract Integer insert(M model);

    @JsonService
    public abstract Integer delete(Integer id);

}
