package kz.galymbay.payintech.controller;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
