package org.stefaniuk.json.service.test;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.stefaniuk.json.service.test.util.JsonRpcTester;

public abstract class AbstractTest {

    protected JsonRpcTester tester;

    protected String service;

    @Before
    public void setUp() throws Exception {

        tester = new JsonRpcTester("/" + service + "/*");
    }

    @After
    public void tearDown() throws Exception {

        tester.stop();
    }

    @Test
    public void testSimpleSmd() throws Exception {

        HttpTester response = tester.getSmd("/" + service + "/");
        tester.assertSmdFirstPart(response);
    }

}
