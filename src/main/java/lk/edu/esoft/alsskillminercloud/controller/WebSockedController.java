package lk.edu.esoft.alsskillminercloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebSockedController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSockedController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void onReceivedMesage(String message) {
        this.template.convertAndSend("/chat", new SimpleDateFormat("hh:mm a").format(new Date()) + " - " + message);
    }
}
