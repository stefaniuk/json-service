package org.stefaniuk.json.service;

/**
 * <p>
 * JSON service error.
 * </p>
 * <p>
 * This class provides definition of JSON-RPC error codes.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.0.0
 * @since 2010/09/20
 */
public enum JsonServiceError {

    PARSE_ERROR(-32700, "Parse error"),
    INTERNAL_ERROR(-32603, "Internal error"),
    INVALID_PARAMS(-32602, "Invalid parameters"),
    METHOD_NOT_FOUND(-32601, "Method not found"),
    INVALID_REQUEST(-32600, "Invalid request"),
    SERVER_ERROR(-32000, "Server error"),
    CUSTOM_ERROR(-1, "Custom error");

    /** Error code. */
    private final int code;

    /** Error message. */
    private String message;

    /**
     * Constructor
     * 
     * @param code Error code
     * @param message Error message
     */
    private JsonServiceError(int code, String message) {

        this.code = code;
        this.message = message;
    }

    /**
     * Returns error code.
     * 
     * @return Returns error code.
     */
    public int getCode() {

        return code;
    }

    /**
     * Returns error message.
     * 
     * @return Returns error message.
     */
    public String getMessage() {

        return message;
    }

    /**
     * Sets error message.
     */
    public void setMessage(String message) {

        this.message = message;
    }

}
