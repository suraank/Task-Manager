package com.example.todo.services;

import com.example.todo.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    public List<TaskEntity> getTaskList();
    public TaskEntity getTaskForId(Long id);
    public void addTask(TaskEntity task);
    public void updateTask(TaskEntity task);
    public void deleteTask(Long id);
}
