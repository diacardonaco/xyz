package com.xyz.xyz.controller;
import com.xyz.xyz.model.Client;
import com.xyz.xyz.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/get-clients", method = RequestMethod.GET)
    public ResponseEntity<Client> getAllClients(){
        List<Client> products = clientService.getAllClients();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable long clientId){
        Optional<Client> client = clientService.getClientById(clientId);
        return new ResponseEntity(client, HttpStatus.OK);
    }


    @RequestMapping(value = "/create-client", method = RequestMethod.POST)
    public ResponseEntity<Client> createProduct(@RequestBody Client client){
        Client clientCreated = clientService.createClient(client);
        return new ResponseEntity(clientCreated, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-client")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client updateClient = clientService.updateClient(client);
        return new ResponseEntity(updateClient, HttpStatus.CREATED);
    }


}
