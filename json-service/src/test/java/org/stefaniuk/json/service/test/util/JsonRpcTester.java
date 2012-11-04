package org.stefaniuk.json.service.test.util;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.stefaniuk.json.service.JsonServiceUtil;
import org.stefaniuk.json.service.test.controller.JsonRpcController;

public class JsonRpcTester {

    private static ServletTester tester;

    public class JsonRpcRequest {

        private String jsonrpc;

        private String method;

        private List<Object> params;

        private Integer id;

        public JsonRpcRequest(String method, List<Object> params) {

            this.jsonrpc = "2.0";
            this.method = method;
            this.params = params;
            this.id = 0;
        }

        public String getJsonrpc() {

            return jsonrpc;
        }

        public String getMethod() {

            return method;
        }

        public List<Object> getParams() {

            return params;
        }

        public Integer getId() {

            return id;
        }

    }

    private static final String SMD_FIRST_PART = "{\"transport\":\"POST\",\"contentType\":\"application/json\",\"envelope\":\"JSON-RPC-2.0\",\"SMDVersion\":\"2.0\",\"additionalParameters\":false,\"services\":{";

    public JsonRpcTester(String path) throws Exception {

        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(JsonRpcController.class, path);
        tester.start();
    }

    public HttpTester getSmd(String url) throws Exception {

        HttpTester request = new HttpTester();
        request.setMethod("GET");
        request.setVersion("HTTP/1.1");
        request.setHeader("Host", "127.0.0.1");
        request.setURI(url);
        HttpTester response = new HttpTester();
        response.parse(tester.getResponses(request.generate()));

        return response;
    }

    public HttpTester callService(String url, String method, List<Object> parameters) throws Exception {

        JsonRpcRequest jrr = new JsonRpcRequest(method, parameters);
        String json = JsonServiceUtil.toJson(jrr, false);

        HttpTester request = new HttpTester();
        request.setMethod("POST");
        request.setVersion("HTTP/1.1");
        request.setHeader("Host", "127.0.0.1");
        request.setURI(url);
        request.setContent(json);
        HttpTester response = new HttpTester();
        response.parse(tester.getResponses(request.generate()));

        return response;
    }

    public void assertSmdFirstPart(HttpTester response) {

        assertTrue(response.getContent().startsWith(SMD_FIRST_PART));
    }

    public void stop() throws Exception {

        tester.stop();
    }

}
