package com.example.todo.services.impl;

import com.example.todo.entity.TaskEntity;
import com.example.todo.repository.TaskRepo;
import com.example.todo.services.TaskService;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepo taskRepo;

    public List<TaskEntity> getTaskList() {
        return taskRepo.findAll();
    }

    public TaskEntity getTaskForId(Long id) {
        return taskRepo.findById;
    }

    public void addTask(TaskEntity task) {
        taskRepo.save(task);
    }

    public void updateTask(TaskEntity task) {
        TaskEntity taskToBeUpdated = taskRepo.findById(task.getId());
        StringUtils.isNotBlank(task.getTitle()) ? taskToBeUpdated.setTitle(task.getTitle()) : "";
        StringUtils.isNotBlank(task.getDescription()) ? taskToBeUpdated.setDescription(task.getDescription()) : "";
        StringUtils.isNotBlank(task.getPriority()) ? taskToBeUpdated.setPriority(task.getPriority()) : "";
        taskRepo.save(taskToBeUpdated);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
