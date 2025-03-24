package itu.p16.newapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import itu.p16.newapp.models.Offer;

import java.io.IOException;
import java.util.List;

public class OfferService {

    // Fonction qui prend un tableau JSON en String et retourne une liste d'Offers
    public List<Offer> parseOffersFromJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper pour la désérialisation
        // Désérialise le JSON en liste d'objets Offer
        List<Offer> offers = objectMapper.readValue(jsonString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Offer.class));
        return offers;
    }
}
