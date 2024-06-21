package com.datnsd09.Datnsd09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/403")
    public String accessDenied(){
        return "/admin/pages-error-404/pages-error-404";
    }
}
