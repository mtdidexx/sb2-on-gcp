package net.katrinka.sb2ongcp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageRestController {
    @Value("${message:BOO YAH}")
    private String message;
    private MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return this.message + "\n";
    }

    @GetMapping("/foobar")
    public String badHombre() {
        return "2\n";
    }

    @PostMapping("/send/{message}")
    void sendMessage(@PathVariable String message) {
        messageService.sendMessage(message);
    }
}
