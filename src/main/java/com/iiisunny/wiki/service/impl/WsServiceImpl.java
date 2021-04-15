package com.iiisunny.wiki.service.impl;

import com.iiisunny.wiki.service.WsService;
import com.iiisunny.wiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WsServiceImpl implements WsService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    @Async // 异步化
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);
    }
}
