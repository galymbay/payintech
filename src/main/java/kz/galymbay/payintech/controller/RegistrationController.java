package kz.galymbay.payintech.controller;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final ClientService clientService;

    @PostMapping
    public Client registration(@RequestBody Client client) {
        return clientService.registration(client);
    }
}
