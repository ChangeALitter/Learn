package com.changw.learn.sync.server.configure;

import com.changw.learn.sync.server.service.WebSocketCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfigure {

    @Bean
    public WebSocketCollector getWebSocketCollector() {
        return new WebSocketCollector();
    }

    @Bean
    public ServerEndpointExporter getServerEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
