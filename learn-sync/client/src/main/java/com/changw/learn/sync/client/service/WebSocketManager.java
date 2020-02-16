package com.changw.learn.sync.client.service;

import com.changw.learn.sync.client.config.WebSocketConfig;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@EnableConfigurationProperties(value = WebSocketConfig.class)
public class WebSocketManager implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketManager.class);

    private volatile Boolean alreadyInited = Boolean.FALSE;

    private WebSocketClient client;

    @Autowired
    private WebSocketConfig webSocketConfig;


    @Override
    public void afterPropertiesSet() throws Exception {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("websocket-connect").setDaemon(true).build());

        try {
            client = new WebSocketClient(new URI(webSocketConfig.getUrl())) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    if (!alreadyInited) {
                        client.send("client open websocket");
                        alreadyInited = Boolean.TRUE;
                    }

                }

                @Override
                public void onMessage(String s) {
                    LOGGER.info("client receive server message:{}", s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    client.close();

                }

                @Override
                public void onError(Exception e) {
                    client.close();
                }
            };
        } catch (Exception e) {
            LOGGER.error("websocket url is error :{}", e);
        }

        boolean sucess = client.connectBlocking();
        if (sucess) {
            LOGGER.info("client connect to Server successfully");
        } else {
            LOGGER.error("client connect to Server failed");
        }
        executor.scheduleAtFixedRate(() -> {
            try {
                if (client != null && client.isClosed()) {
                    boolean success = client.reconnectBlocking();
                    if (success) {
                        LOGGER.info("websocket reconnect is successful.....");
                    } else {
                        LOGGER.info("websocket reconnection is error.....");
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.error("websocket connect is error :{}", e.getMessage());
            }

        }, 10, 30, TimeUnit.SECONDS);

    }

    public void sendMessage(String text) {
        client.send(text);
    }
}
