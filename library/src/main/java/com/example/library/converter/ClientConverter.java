package com.example.library.converter;

import com.example.library.dto.BookDto;
import com.example.library.dto.ClientDto;
import com.example.library.model.Book;
import com.example.library.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientDto map (Client client){
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .books(client.getBooks())
                .build();
    }
    public Client map (ClientDto clientDto){
        return Client.builder()
                .id(clientDto.getId())
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .books(clientDto.getBooks())
                .build();
    }
}
