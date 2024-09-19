package com.example.todo.services;

import com.example.todo.entity.TaskEntity;
import com.example.todo.exception.TaskException;
import com.example.todo.exception.TaskSizeException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    public List<TaskEntity> getTaskList();
    public TaskEntity getTaskForId(Long id) throws TaskException;
    public void addTask(TaskEntity task) throws TaskException, TaskSizeException;
    public void updateTask(TaskEntity task) throws TaskException, TaskSizeException;
    public void deleteTask(Long id);
}
