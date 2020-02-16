package com.changw.learn.sync.client.controller;

import com.changw.learn.sync.client.service.WebSocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class MessageController {

    @Autowired
    private WebSocketManager webSocketManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/send")
    public void sendMessage(@RequestParam String message) {
        LOGGER.info("send message:{}", message);
        webSocketManager.sendMessage(message);
    }
}
