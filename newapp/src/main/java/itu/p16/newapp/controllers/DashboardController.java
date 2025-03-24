package itu.p16.newapp.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import itu.p16.newapp.models.Client;
import itu.p16.newapp.models.Invoice;
import itu.p16.newapp.models.Offer;
import itu.p16.newapp.models.Payment;
import itu.p16.newapp.models.TotalsDTO;
import itu.p16.newapp.services.LaravelApiService;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

@RestController
public class DashboardController {

    private final LaravelApiService laravelApiService;

    public DashboardController(LaravelApiService laravelApiService) {
        this.laravelApiService = laravelApiService;
    }

    @GetMapping("/totals")
    public Mono<TotalsDTO> getTotals() {
        return laravelApiService.getTotals();
    }
    @GetMapping("/clients")
    public Mono<List<Client>> getClientsTotals() {
        return laravelApiService.getClientsTotals();
    }
    @GetMapping("/offers")
    public Mono<List<Offer>> getOffersTotals() {
        return laravelApiService.getOfferTotals();
    }
    @GetMapping("/payments")
    public Mono<List<Payment>> getPaymentsTotals() {
        return laravelApiService.getPaymentsTotals();
    }
    @GetMapping("/tasks")
    public Mono<List<Task>> getTasksTotals() {
        return laravelApiService.getTasksTotals();
    }
    @GetMapping("/invoices")
    public Mono<List<Invoice>> getInvoicesTotals() {
        return laravelApiService.getInvoicesTotals();
    }

    @DeleteMapping("/delete-payment/{id}")
    public Mono<Void> deletePayment(@PathVariable Long id) {
        return laravelApiService.deletePayment(id)
                .doOnTerminate(() -> System.out.println("Payment deleted with ID: " + id));
    }

    @GetMapping("/home")
    public void manualRedirect(HttpServletResponse response) throws IOException {
        String baseUrl = laravelApiService.getHome();
        response.sendRedirect(baseUrl);  // Redirige manuellement vers l'URL
    }
    
}
