package org.stefaniuk.json.service.store;

import java.util.List;

import org.stefaniuk.json.service.JsonService;

/**
 * Dojo Toolkit store service.
 * 
 * @param <M>
 * @author Daniel Stefaniuk
 * @version 1.2.0
 * @since 2012/11/08
 */
public interface Store<M> {

    @JsonService
    public abstract List<M> select(QueryStore query);

    @JsonService
    public abstract Integer update(Integer id, M model);

    @JsonService
    public abstract Integer insert(M model);

    @JsonService
    public abstract Integer delete(Integer id);

}
