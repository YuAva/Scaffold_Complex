package cn.edu.bupt.hover.scaffold.controller;


import cn.edu.bupt.hover.scaffold.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/echo")
    public Object test() {
        testService.testRollBack();
        return "{\"message\": \"success\"}";
    }
}
