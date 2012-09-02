package org.stefaniuk.json.service.example3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * MVCS abstract model.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.1.0
 * @since 2012/09/02
 */
public abstract class AbstractModel {

    protected final Logger logger = LoggerFactory.getLogger(AbstractModel.class);

    /**
     * Returns identity key of the model.
     * 
     * @return Integer
     */
    public abstract Integer getId();

}
