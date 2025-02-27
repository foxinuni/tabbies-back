package dev.downloadablefox.tabbies.webserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class TabbiesController {
    @GetMapping
    public String tabbies(){
        return "index";
    }
}
