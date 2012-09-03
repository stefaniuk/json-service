package org.stefaniuk.json.service.example3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stefaniuk.json.service.example3.service.AccountService;
import org.stefaniuk.json.service.example3.service.CustomerService;
import org.stefaniuk.json.service.example3.service.StatementService;

@Controller
@RequestMapping(value = "/banking/*")
public class BankingController extends AbstractController {

    @Override
    @RequestMapping("service/{service}")
    public ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String service) throws Exception {

        ResponseEntity<String> re = null;

        if(service.equals("customer")) {
            re = handleJsonRpc(request, response, CustomerService.class);
        }
        else if(service.equals("account")) {
            re = handleJsonRpc(request, response, AccountService.class);
        }
        else if(service.equals("statement")) {
            re = handleJsonRpc(request, response, StatementService.class);
        }

        return re;
    }

}
