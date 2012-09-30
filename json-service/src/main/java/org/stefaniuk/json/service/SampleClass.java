package org.stefaniuk.json.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleClass {

    private final Logger logger = LoggerFactory.getLogger(SampleClass.class);

    private String sampleField;

    public SampleClass(String sampleField) {

        logger.debug("constructor - sampleField: " + sampleField);

        this.sampleField = sampleField;
    }

    public String getSampleField() {

        logger.info("getter - sampleField: " + sampleField);

        return sampleField;
    }

    public void setSampleField(String sampleField) {

        logger.info("setter - sampleField: " + sampleField);

        this.sampleField = sampleField;
    }

}
