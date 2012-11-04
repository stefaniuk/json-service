package org.stefaniuk.json.service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.Test;
import org.stefaniuk.json.service.test.bean.User;

public class UserTest extends AbstractTest {

    public UserTest() {

        service = "User";
    }

    @Test
    public void testLoginMethod() throws Exception {

        User user = new User("daniel", "53cr3t", "Daniel Stefaniuk", "daniel.stefaniuk@gmail.com");

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(user);

        HttpTester response = tester.callService("/" + service + "/", "login", parameters);
        assertTrue(response.getContent().contains("\"result\":true"));
    }

}
