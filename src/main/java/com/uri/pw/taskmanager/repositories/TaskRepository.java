package com.uri.pw.taskmanager.repositories;

import com.uri.pw.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> { }
