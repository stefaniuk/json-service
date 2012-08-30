package org.stefaniuk.json.service;

/**
 * JSON Service exception.
 * 
 * @author Daniel Stefaniuk
 */
public class JsonServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    /** JSON service error attached to this exception. */
    private final JsonServiceError error;

    /**
     * Constructor
     * 
     * @param error
     */
    public JsonServiceException(JsonServiceError error) {

        super(error.getMessage());

        this.error = error;
    }

    /**
     * Returns exception code.
     * 
     * @return
     */
    public int getCode() {

        return error.getCode();
    }

    /**
     * Returns error object.
     * 
     * @return
     */
    public JsonServiceError getError() {

        return error;
    }

}
