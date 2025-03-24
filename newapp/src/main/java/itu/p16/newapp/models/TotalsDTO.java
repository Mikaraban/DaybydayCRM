package itu.p16.newapp.models;


public class TotalsDTO {
    private int totalClients;
    private int totalProjects;
    private int totalTasks;
    private int totalOffers;
    private int totalInvoices;
    private int totalPayments;
    public int getTotalClients() {
        return totalClients;
    }
    public void setTotalClients(int totalClients) {
        this.totalClients = totalClients;
    }
    public int getTotalProjects() {
        return totalProjects;
    }
    public void setTotalProjects(int totalProjects) {
        this.totalProjects = totalProjects;
    }
    public int getTotalTasks() {
        return totalTasks;
    }
    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }
    public int getTotalOffers() {
        return totalOffers;
    }
    public void setTotalOffers(int totalOffers) {
        this.totalOffers = totalOffers;
    }
    public int getTotalInvoices() {
        return totalInvoices;
    }
    public void setTotalInvoices(int totalInvoices) {
        this.totalInvoices = totalInvoices;
    }
    public int getTotalPayments() {
        return totalPayments;
    }
    public void setTotalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
    }
    public TotalsDTO(int totalClients, int totalProjects, int totalTasks, int totalOffers, int totalInvoices,
            int totalPayments) {
        this.totalClients = totalClients;
        this.totalProjects = totalProjects;
        this.totalTasks = totalTasks;
        this.totalOffers = totalOffers;
        this.totalInvoices = totalInvoices;
        this.totalPayments = totalPayments;
    }
    
    public TotalsDTO() {
    }
}

