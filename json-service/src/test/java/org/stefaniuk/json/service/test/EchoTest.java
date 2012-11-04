package org.stefaniuk.json.service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.Test;

public class EchoTest extends AbstractTest {

    public EchoTest() {

        service = "Echo";
    }

    @Test
    public void testEchoMethod() throws Exception {

        String text = "Hello World!";

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(text);

        HttpTester response = tester.callService("/" + service + "/", "echo", parameters);
        assertTrue(response.getContent().contains("Echo service says: " + text));
    }

}
