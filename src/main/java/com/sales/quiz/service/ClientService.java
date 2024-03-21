package com.sales.quiz.service;

import com.sales.quiz.converter.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();

    void addClient(ClientDTO clientDTO);

    void saveClient(ClientDTO clientDTO );
}
