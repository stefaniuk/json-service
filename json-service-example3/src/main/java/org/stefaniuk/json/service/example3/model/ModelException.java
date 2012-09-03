package org.stefaniuk.json.service.example3.model;

/**
 * <p>
 * MVCS model exception.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.1.0
 * @since 2012/09/03
 */
public class ModelException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ModelException(String msg) {

        super(msg);
    }

}
