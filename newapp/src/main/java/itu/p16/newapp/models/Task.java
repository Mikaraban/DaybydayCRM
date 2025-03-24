package itu.p16.newapp.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private Long id;
    private String externalId;
    private String title;
    private String description;
    private Long statusId;
    private Long userAssignedId;
    private Long userCreatedId;
    private Long clientId;
    private Long projectId;
    private LocalDate deadline;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructeurs
    public Task() {}

    public Task(Long id, String externalId, String title, String description, Long statusId,
                       Long userAssignedId, Long userCreatedId, Long clientId, Long projectId, LocalDate deadline,
                       LocalDateTime deletedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.externalId = externalId;
        this.title = title;
        this.description = description;
        this.statusId = statusId;
        this.userAssignedId = userAssignedId;
        this.userCreatedId = userCreatedId;
        this.clientId = clientId;
        this.projectId = projectId;
        this.deadline = deadline;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getUserAssignedId() {
        return userAssignedId;
    }

    public void setUserAssignedId(Long userAssignedId) {
        this.userAssignedId = userAssignedId;
    }

    public Long getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(Long userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString()
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", statusId=" + statusId +
                ", userAssignedId=" + userAssignedId +
                ", userCreatedId=" + userCreatedId +
                ", clientId=" + clientId +
                ", projectId=" + projectId +
                ", deadline=" + deadline +
                ", deletedAt=" + deletedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

