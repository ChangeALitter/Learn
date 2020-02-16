package com.changw.learn.sync.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
public class WebSocketCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketCollector.class);

    private static final Set<Session> SESSION_SET = new CopyOnWriteArraySet<>();

    private static Session session;

    @OnOpen
    public void OnOpen(final Session session) {
        LOGGER.info("websocket open successfully!");
        SESSION_SET.add(session);
    }

    @OnClose
    public void OnClose(final Session session) {
        SESSION_SET.remove(session);
        WebSocketCollector.session = null;
        LOGGER.info("websocket close successfully!");
    }

    @OnError
    public void OnError(final Session session, Throwable error) {
        SESSION_SET.remove(session);
        WebSocketCollector.session = null;
        LOGGER.error("websocket collector error: ", error);
    }

    @OnMessage
    public void OnMessage(final String message, final Session session) {
       LOGGER.info("websocket collector receive:{}", message);
       WebSocketCollector.session = session;
    }

    /**
     * send message
     * @param message
     */
    public static void send(String message) {
        for (Session session : SESSION_SET) {
            try {
                session.getBasicRemote().sendText(message);
                LOGGER.info("websocket collector send:{}", message);
            } catch (Exception e) {
                LOGGER.error("websocket send message: {} error:", message, e.getMessage());
            }
        }
    }





}
