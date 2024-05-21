package com.uri.pw.taskmanager.controllers;

import com.uri.pw.taskmanager.entities.Task;
import com.uri.pw.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasksList = service.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tasksList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task = service.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(task);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task body) {
        Task task = service.create(body);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(task);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task body) {
        Task updatedTask = service.update(id, body);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedTask);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
