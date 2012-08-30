package org.stefaniuk.json.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.stefaniuk.json.service.JsonServiceTarget.ContentType;
import org.stefaniuk.json.service.JsonServiceTarget.Envelope;
import org.stefaniuk.json.service.JsonServiceTarget.Transport;

/**
 * <p>
 * JSON Service annotation.
 * </p>
 * <p>
 * Each class registered with {@link JsonServiceRegistry} should provide methods
 * to be available to call by a client. It is up to a developer to decide if a
 * public method needs to be exposed to a JSON-RPC client. Only annotated
 * methods with {@link JsonService} declared with the public modifier will be
 * reflected in Service Mapping Description and made visible.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.0.0
 * @since 2010/09/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface JsonService {

    /** The transport property defines the transport mechanism to be used to deliver service calls to server. */
    Transport transport() default Transport.POST;

    /** This is the expected content type of the content returned by a service. */
    ContentType contentType() default ContentType.APPLICATION_JSON;

    /** Envelope defines how a service message string is created from the provided parameters. */
    Envelope envelope() default Envelope.JSON_RPC_2_0;

    /** This should indicate what URL to use for the method call requests. */
    String target() default "";

    /** This is a description of the service. */
    String description() default "";

}
