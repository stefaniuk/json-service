package com.code4ge.json.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.code4ge.json.service.JsonServiceObject.ContentType;
import com.code4ge.json.service.JsonServiceObject.Envelope;
import com.code4ge.json.service.JsonServiceObject.Transport;

/**
 * JSON Service annotation.
 * 
 * @author Daniel Stefaniuk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface JsonService {

    Transport transport() default Transport.POST;

    ContentType contentType() default ContentType.APPLICATION_JSON;

    Envelope envelope() default Envelope.JSON_RPC_2_0;

    String target() default "";

    String description() default "";

}
