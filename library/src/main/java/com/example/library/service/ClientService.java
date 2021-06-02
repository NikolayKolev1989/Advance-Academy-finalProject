package com.example.library.service;

import com.example.library.dto.ClientDto;
import com.example.library.model.Client;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ClientRepository;
import com.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private ClientDto clientDto;

    public ClientService(ClientRepository clientRepository, BookRepository bookRepository, GenreRepository genreRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    public Client safeClient(@NotNull Client client){
        return clientRepository.save(client);
    }

    public Set<Client> findAllClients(){
        return new HashSet<>(clientRepository.findAll());
    }

    public Client findByFirstName(@NotNull String name){
        return clientRepository.findByFirstName(name)
                .orElseThrow(()-> new NoSuchElementException
                        (String.format("Client %s does not exist", name)));
    }

    public Client findByLastName(@NotNull String name){
        return clientRepository.findByLastName(name)
                .orElseThrow(()-> new NoSuchElementException
                        (String.format("Client %s does not exist", name)));
    }

    public void deleteClientById (@NotNull Long id){
        clientRepository.deleteById(id);
    }

    public void takeBook (){

    }
}
