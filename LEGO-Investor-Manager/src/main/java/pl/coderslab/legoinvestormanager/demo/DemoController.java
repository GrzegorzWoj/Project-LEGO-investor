package pl.coderslab.legoinvestormanager.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping
    public String hello() {
        return "Hello!";
    }

    // testowanie apki pod adresem: http://localhost:8080/swagger-ui/index.html#/
}
