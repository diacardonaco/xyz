package com.xyz.xyz.service;

import com.xyz.xyz.model.Client;
import com.xyz.xyz.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        Iterable<Client> clients = clientRepository.findAll();
        List<Client> clientList = new ArrayList<Client>();
        clients.forEach(clientList::add);
        return  clientList;
    }

    public Optional<Client> getClientById(long id){
        return clientRepository.findById(id);
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }


}
