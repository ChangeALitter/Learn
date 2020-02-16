package com.changw.learn.sync.server.controller;

import com.changw.learn.sync.server.service.WebSocketCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/send")
    public void sendMessage(@RequestParam String message) {
        LOGGER.info("send message:{}", message);
        WebSocketCollector.send(message);
    }
}
