package org.stefaniuk.json.service.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.Test;
import org.stefaniuk.json.service.test.bean.User;

public class ListTest extends AbstractTest {

    public ListTest() {

        service = "List";
    }

    @Test
    public void testEchoMethod() throws Exception {

        ArrayList<Object> parameters = new ArrayList<Object>();
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("daniel", "53cr3t", "Daniel Stefaniuk", "daniel.stefaniuk@gmail.com"));
        users.add(new User("daniel", "53cr3t", "Daniel Stefaniuk", "daniel.stefaniuk@gmail.com"));
        parameters.add(users);

        HttpTester response = tester.callService("/" + service + "/", "echo", parameters);
        String content = response.getContent();
        assertTrue(content.contains("daniel.stefaniuk@gmail.com"));
        assertTrue(content.contains("******"));
    }

}
