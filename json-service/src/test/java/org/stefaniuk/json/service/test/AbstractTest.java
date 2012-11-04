package org.stefaniuk.json.service.test;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.stefaniuk.json.service.test.util.JsonRpcTester;

public abstract class AbstractTest {

    protected JsonRpcTester tester;

    protected String service;

    @BeforeClass
    public static void setUpClass() throws Exception {

        System.out.println("=========================================================================================");
    }

    @Before
    public void setUp() throws Exception {

        System.out.println();
        tester = new JsonRpcTester("/" + service + "/*");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

        System.out.println();
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
