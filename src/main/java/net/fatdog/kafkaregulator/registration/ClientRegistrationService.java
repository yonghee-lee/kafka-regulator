package net.fatdog.kafkaregulator.registration;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientRegistrationService {

    private final Map<String, String> clientMap = new HashMap<>();

    public void registerClient(ClientRegistrationRequest registrationRequest) {
        String clientId = registrationRequest.getClientId();
        String ipAddress = registrationRequest.getIpAddress();
        
        // Perform validation or additional logic if needed
        
        // Register the client ID and IP address
        clientMap.put(clientId, ipAddress);
    }
}
