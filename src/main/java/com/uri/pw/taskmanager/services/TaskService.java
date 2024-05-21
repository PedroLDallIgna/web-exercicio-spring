package com.uri.pw.taskmanager.services;

import com.uri.pw.taskmanager.entities.Task;
import com.uri.pw.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        Optional<Task> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new RuntimeException();
    }

    public Task create(Task newTask) {
        return repository.save(newTask);
    }
}
