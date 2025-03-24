package itu.p16.newapp.models;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private String external_id;
    private double amount;
    private String description;
    private String payment_source;
    private LocalDateTime payment_date;
    private String integration_payment_id;
    private String integration_type;
    private int invoice_id;
    private LocalDateTime deleted_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_source() {
        return payment_source;
    }

    public void setPayment_source(String payment_source) {
        this.payment_source = payment_source;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public String getIntegration_payment_id() {
        return integration_payment_id;
    }

    public void setIntegration_payment_id(String integration_payment_id) {
        this.integration_payment_id = integration_payment_id;
    }

    public String getIntegration_type() {
        return integration_type;
    }

    public void setIntegration_type(String integration_type) {
        this.integration_type = integration_type;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
