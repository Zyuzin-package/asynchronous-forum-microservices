package microservice.asynchronousMicroservices.REST.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/"})
    public String get(){
        return "index";
    }
}
