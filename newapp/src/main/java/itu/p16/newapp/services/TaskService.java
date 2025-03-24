package itu.p16.newapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import itu.p16.newapp.models.Task;

import java.io.IOException;
import java.util.List;

public class TaskService {

    // Fonction qui prend un tableau JSON en String et retourne une liste de Tasks
    public List<Task> parseTasksFromJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper pour la désérialisation
        // Désérialise le JSON en liste d'objets Task
        List<Task> tasks = objectMapper.readValue(jsonString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class));
        return tasks;
    }
}
