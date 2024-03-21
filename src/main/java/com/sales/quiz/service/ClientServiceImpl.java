package com.sales.quiz.service;
import com.sales.quiz.converter.ClientDTO;
import com.sales.quiz.converter.ClientMapper;
import com.sales.quiz.exception.ResourceNotFoundException;
import com.sales.quiz.model.Client;
import com.sales.quiz.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::ClientToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addClient(ClientDTO clientDTO) {
        Client client = clientMapper.ClientDTOToClient(clientDTO);
        clientRepository.saveAndFlush(client);
    }

    @Override
    public void saveClient(ClientDTO clientDTO) {
        Client client = clientRepository
                .findById(clientDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("CLient not found"));
        Client clientToSave = clientMapper.ClientDTOToClient(clientDTO);
        clientRepository.saveAndFlush(clientToSave);
    }
}
