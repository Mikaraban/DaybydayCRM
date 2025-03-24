package itu.p16.newapp.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import itu.p16.newapp.models.Client;
import itu.p16.newapp.models.Invoice;
import itu.p16.newapp.models.Offer;
import itu.p16.newapp.models.Payment;
import itu.p16.newapp.models.TotalsDTO;

import reactor.core.publisher.Mono;

@Service
public class LaravelApiService {

    private final WebClient webClient;

    // URL de ton conteneur Nginx accessible sur localhost:80
    private final String LARAVEL_API_BASE_URL = "http://localhost:80";

    public LaravelApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(LARAVEL_API_BASE_URL).build();
    }
    public String getHome() {
        return LARAVEL_API_BASE_URL;  // Retourne l'URL de base
    }
    

    public void redirectToBaseUrl() {
        String baseUrl = getHome();
        System.out.println("Redirecting to: " + baseUrl);
    }
    

    public Mono<TotalsDTO> getTotals() {
        return webClient.get()
                .uri("/dataSpring/getTotals")
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la requête"))
                )
                .bodyToMono(TotalsDTO.class);  // Le client s'attend à recevoir un JSON
    }

    public Mono<Void> deletePayment(Long paymentId) {
        return webClient.delete()
                .uri("/payment/id/{paymentId}", paymentId) // Passer l'ID du paiement dans l'URL
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la suppression du paiement"))
                )
                .bodyToMono(Void.class);  // Aucune réponse attendue si la suppression réussie
    }

    public Mono<List<Client>> getClientsTotals() {
        return webClient.get()
                .uri("/dataSpring/getAllClientsJSON")
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la requête"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<Client>>() {});  // Le client s'attend à recevoir une liste JSON
    }

    public Mono<List<Task>> getTasksTotals() {
        return webClient.get()
                .uri("/dataSpring/getAllTasksJSON")
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la requête"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<Task>>() {});  // Le client s'attend à recevoir une liste JSON
    }

    public Mono<List<Invoice>> getInvoicesTotals() {
        return webClient.get()
                .uri("/dataSpring/getAllInvoicesJSON")
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la requête"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<Invoice>>() {});  // Le client s'attend à recevoir une liste JSON
    }

    public Mono<List<Payment>> getPaymentsTotals() {
        return webClient.get()
                .uri("/dataSpring/getAllPaymentsJSON")
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la requête"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<Payment>>() {});  // Le client s'attend à recevoir une liste JSON
    }

    public Mono<List<Offer>> getOfferTotals() {
        return webClient.get()
                .uri("/dataSpring/getAllOffersJSON")
                .retrieve()
                .onStatus(
                    status -> status.isError(),
                    clientResponse -> Mono.error(new RuntimeException("Erreur lors de la requête"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<Offer>>() {});  // Le client s'attend à recevoir une liste JSON
    }
}
