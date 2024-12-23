package com.app.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.model.Task;
import com.app.todo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todo")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@Valid @RequestBody Task taskDetails) {
        Task task = taskDetails;
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        task.setDueDate(taskDetails.getDueDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(task));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable Long id) {
        Optional<Task> foundTask = taskService.getTaskById(id);
        if (foundTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(foundTask.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        Task updatedTask = taskOptional.get();
        updatedTask.setTitle(taskDetails.getTitle());
        updatedTask.setDescription(taskDetails.getDescription());
        updatedTask.setCompleted(taskDetails.isCompleted());
        updatedTask.setDueDate(taskDetails.getDueDate());

        return ResponseEntity.status(HttpStatus.OK).body(taskService.saveTask(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        boolean taskBoolean = taskService.deleteTask(id);
        return taskBoolean == true ? ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    }
}
