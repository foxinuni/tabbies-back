package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("")
public class TabbiesController {
    @GetMapping
    @ResponseBody
    public Map<String, String> tabbies(){
        return Map.of("health", "ok");
    }
}
