package itu.p16.newapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import itu.p16.newapp.models.Invoice;

import java.io.IOException;
import java.util.List;

public class InvoiceService {

    // Fonction qui prend un tableau JSON en String et retourne une liste d'Invoices
    public List<Invoice> parseInvoicesFromJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper pour la désérialisation
        // Désérialise le JSON en liste d'objets Invoice
        List<Invoice> invoices = objectMapper.readValue(jsonString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Invoice.class));
        return invoices;
    }
}

