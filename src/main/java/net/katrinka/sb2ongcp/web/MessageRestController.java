package net.katrinka.sb2ongcp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RefreshScope
public class MessageRestController {
    @Value("${message:BOO YAH}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return this.message;
    }

    @GetMapping("/foobar")
    public String badHombre() {
        return new BigDecimal(Double.parseDouble("2")).toString();
    }
}
