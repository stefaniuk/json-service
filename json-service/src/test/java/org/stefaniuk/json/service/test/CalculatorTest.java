package org.stefaniuk.json.service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.Test;

public class CalculatorTest extends AbstractTest {

    public CalculatorTest() {

        service = "Calculator";
    }

    @Test
    public void testMethodAdd() throws Exception {

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(2);
        parameters.add(5);

        HttpTester response = tester.callService("/" + service + "/", "add", parameters);
        assertTrue(response.getContent().contains("\"result\":7"));
    }

    @Test
    public void testMethodSubtract() throws Exception {

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(33);
        parameters.add(22);

        HttpTester response = tester.callService("/" + service + "/", "subtract", parameters);
        assertTrue(response.getContent().contains("\"result\":11"));
    }

    @Test
    public void testMethodMultiple() throws Exception {

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(25);
        parameters.add(25);

        HttpTester response = tester.callService("/" + service + "/", "multiple", parameters);
        assertTrue(response.getContent().contains("\"result\":625"));
    }

    @Test
    public void testMethodDivide() throws Exception {

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(999);
        parameters.add(3);

        HttpTester response = tester.callService("/" + service + "/", "divide", parameters);
        assertTrue(response.getContent().contains("\"result\":333"));
    }

}
