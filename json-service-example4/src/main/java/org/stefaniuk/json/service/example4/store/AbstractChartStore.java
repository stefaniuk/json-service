package org.stefaniuk.json.service.example4.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractChartStore<M, D> extends AbstractStore<M, D> {

    protected final Logger logger = LoggerFactory.getLogger(AbstractChartStore.class);

    @Override
    final public Integer update(Integer id, M model) {

        throw new UnsupportedOperationException("update");
    }

    @Override
    final public Integer insert(M model) {

        throw new UnsupportedOperationException("insert");
    }

    @Override
    final public Integer delete(Integer id) {

        throw new UnsupportedOperationException("delete");
    }

}
