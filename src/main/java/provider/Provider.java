package provider;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Provider {

    @RequestMapping("/v1/cica")
    public String index(@RequestParam int idx) {
        return "cica";
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Provider.class, args);
    }

}