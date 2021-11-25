package com.vapasi.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greeting")
public class StringController {
    @GetMapping("/")
    public String getGreetings() {
        return "Welcome Harshada";
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeGreetings() {
        return ResponseEntity.ok().header("Header: Welcome","Harshada").body("Welcome thoughtworks");
    }

}
