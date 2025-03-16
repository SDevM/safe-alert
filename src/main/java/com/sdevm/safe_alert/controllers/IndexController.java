package com.sdevm.safe_alert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public void index() {
        // Handle Docs
    }
}
