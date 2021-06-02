package com.example.library.controller;

import com.example.library.converter.ClientConverter;
import com.example.library.dto.ClientDto;
import com.example.library.model.Client;
import com.example.library.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
public class ClientController {
    private final ClientService clientService;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientController(ClientService clientService, ClientConverter clientConverter) {
        this.clientService = clientService;
        this.clientConverter = clientConverter;
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient (@RequestBody ClientDto clientDto){
        Client client = clientService.safeClient(clientConverter.map(clientDto));
        return ResponseEntity.ok(clientConverter.map(client));
    }

    @GetMapping
    public ResponseEntity<HashSet<Client>> findAllClients(){
        return ResponseEntity.ok(new HashSet<>(clientService.findAllClients()));
    }

    @GetMapping (value = "/{clientFirstName}")
    public ResponseEntity<ClientDto> findClientByFirstName (@PathVariable String clientFirstName){
        Client findClient = clientService.findByFirstName(clientFirstName);
        return ResponseEntity.ok(clientConverter.map(findClient));
    }

    @GetMapping (value = "/{clientLastName}")
    public ResponseEntity<ClientDto> findClientByLastName (@PathVariable String clientLastName){
        Client findClient = clientService.findByFirstName(clientLastName);
        return ResponseEntity.ok(clientConverter.map(findClient));
    }

}
