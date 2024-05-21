package com.uri.pw.taskmanager.services;

import com.uri.pw.taskmanager.entities.Task;
import com.uri.pw.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAll() {
        return repository.findAll();
    }
}
