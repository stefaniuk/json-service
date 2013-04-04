package org.stefaniuk.json.service.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.jetty.testing.HttpTester;
import org.junit.Test;
import org.stefaniuk.json.service.JsonServiceError;

public class ErrorTest extends AbstractTest {

    public ErrorTest() {

        service = "Error";
    }

    @Test
    public void testNamedParameters() throws Exception {

        String jsonRpc = "{" +
                "\"jsonrpc\":\"2.0\"," +
                "\"id\":0," +
                "\"method\":\"error\"," +
                "\"params\":{" +
                "\"param1\":1,\"param2\":2,\"param3\":3" +
                "}}";

        HttpTester response = tester.callService("/" + service + "/", jsonRpc);
        assertTrue(response.getContent().contains(JsonServiceError.INVALID_REQUEST.getMessage()));
    }

}
