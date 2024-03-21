package com.sales.quiz.controller;

import com.sales.quiz.converter.ClientDTO;
import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clients/")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addClient(@RequestBody ClientDTO user) {
        clientService.addClient(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    void updateClient(@RequestBody ClientDTO clientDTO) {
        clientService.saveClient(clientDTO);
    }
}
