package com.example.library.service;

import com.example.library.model.Client;
import com.example.library.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client safeClient(Client client){
        return clientRepository.save(client);
    }

    public Set<Client> findAllClients(){
        return new HashSet<>(clientRepository.findAll());
    }

    public Client findByFirstName(String name){
        return clientRepository.findByFirstName(name)
                .orElseThrow(()-> new NoSuchElementException
                        (String.format("Client %s does not exist", name)));
    }

    public Client findByLastName(String name){
        return clientRepository.findByLastName(name)
                .orElseThrow(()-> new NoSuchElementException
                        (String.format("Client %s does not exist", name)));
    }

    public void deleteClientById (Long id){
        clientRepository.deleteById(id);
    }
}
