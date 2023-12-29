package net.fatdog.kafkaregulator.connection;

import java.io.IOException;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketHandler; 
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        // Handle incoming messages here
        String receivedMessage = (String) message.getPayload();
        try {
        // Process the message and send a response if needed
            session.sendMessage(new TextMessage("Received: " + receivedMessage));
        
        } catch(IOException e) {

        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Perform actions when a new WebSocket connection is established
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Perform actions when a WebSocket connection is closed
    }
}
