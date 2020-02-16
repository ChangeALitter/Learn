package com.changw.learn.sync.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sync.websocket")
public class WebSocketConfig {

    private String url;
}
