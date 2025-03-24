package itu.p16.newapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import itu.p16.newapp.models.Payment;

import java.io.IOException;
import java.util.List;

public class PaymentService {

    // Fonction qui prend un tableau JSON en String et retourne une liste de Payments
    public List<Payment> parsePaymentsFromJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper pour la désérialisation
        // Désérialise le JSON en liste d'objets Payment
        List<Payment> payments = objectMapper.readValue(jsonString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Payment.class));
        return payments;
    }
}
