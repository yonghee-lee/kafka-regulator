package net.fatdog.kafkaregulator.registration;

public class ClientRegistrationRequest {
    private String clientId;
    private String ipAddress;

    // Getters and setters

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}

