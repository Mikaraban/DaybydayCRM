package itu.p16.newapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import itu.p16.newapp.models.Client;

import java.io.IOException;
import java.util.List;

public class ClientService {

    // Fonction qui prend un tableau JSON en String et retourne une liste de Clients
    public List<Client> parseClientsFromJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper pour la désérialisation
        // Désérialise le JSON en liste d'objets Client
        List<Client> clients = objectMapper.readValue(jsonString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Client.class));
        return clients;
    }
}

