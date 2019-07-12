package com.lym.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyumeng
 */
@RestController
public class TestController {

    @GetMapping("/health/check")
    public String get(){
        return "OK";
    }

}
