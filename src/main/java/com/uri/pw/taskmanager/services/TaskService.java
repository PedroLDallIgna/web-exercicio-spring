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

    public Task update(Long id, Task updatedTask) {
        Task currentTask = this.getById(id);
        currentTask.setCreationDate(updatedTask.getCreationDate());
        currentTask.setLimitDate(updatedTask.getLimitDate());
        currentTask.setDescription(updatedTask.getDescription());
        currentTask.setDone(updatedTask.getDone());
        return repository.save(currentTask);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
