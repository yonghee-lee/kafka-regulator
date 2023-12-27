package net.fatdog.kafkaregulator.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class ClientRegistrationController {

    @Autowired
    private ClientRegistrationService clientRegistrationService;

    @PostMapping("/register")
    public void registerClient(@RequestBody ClientRegistrationRequest registrationRequest) {
        clientRegistrationService.registerClient(registrationRequest);
    }
}

