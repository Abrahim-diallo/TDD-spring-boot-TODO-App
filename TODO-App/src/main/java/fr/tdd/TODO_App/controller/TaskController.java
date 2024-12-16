package fr.tdd.TODO_App.controller;

import fr.tdd.TODO_App.model.Task;
import fr.tdd.TODO_App.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        validateTask(task);
        Task createdTask = saveTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    private void validateTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }

    private Task saveTask(Task task) {
        return taskRepository.save(task);
    }
}
