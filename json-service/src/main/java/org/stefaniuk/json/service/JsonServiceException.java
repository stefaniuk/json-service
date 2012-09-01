package org.stefaniuk.json.service;

/**
 * <p>
 * JSON service exception.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.0.0
 * @since 2010/09/20
 */
public class JsonServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    /** This is a reference to JSON service error object. */
    private final JsonServiceError error;

    /**
     * Constructor
     * 
     * @param error Error object
     */
    public JsonServiceException(JsonServiceError error) {

        super(error.getMessage());

        this.error = error;
    }

    /**
     * Returns error code.
     * 
     * @return Returns error code.
     */
    public int getCode() {

        return error.getCode();
    }

    /**
     * Returns error object.
     * 
     * @return Returns error object.
     */
    public JsonServiceError getError() {

        return error;
    }

}
