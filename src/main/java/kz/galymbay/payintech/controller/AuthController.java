package kz.galymbay.payintech.controller;

import kz.galymbay.payintech.entity.AuthenticationDto;
import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;

    @PostMapping(path = "/registration")
    public String registration(@RequestBody Client client) {
        return clientService.registration(client);
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody AuthenticationDto authenticationDto) {
        return clientService.login(authenticationDto);
    }
}
