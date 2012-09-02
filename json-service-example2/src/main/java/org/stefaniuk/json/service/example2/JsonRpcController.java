package org.stefaniuk.json.service.example2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stefaniuk.json.service.JsonServiceRegistry;
import org.stefaniuk.json.service.JsonServiceUtil;

@Controller
@RequestMapping(value = "/jsonrpc/*")
public class JsonRpcController {

    @Autowired
    private JsonServiceRegistry jsonService;

    @RequestMapping(value = "*")
    public ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return JsonServiceUtil.handle(jsonService, request, response, CalculatorService.class);
    }

}
