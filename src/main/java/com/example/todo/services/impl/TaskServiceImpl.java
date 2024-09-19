package com.example.todo.services.impl;

import com.example.todo.entity.TaskEntity;
import com.example.todo.exception.TaskException;
import com.example.todo.exception.TaskSizeException;
import com.example.todo.repository.TaskRepo;
import com.example.todo.services.TaskService;
import com.example.todo.utils.TaskValidationUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<TaskEntity> getTaskList() {
        return taskRepo.findAll();
    }

    public TaskEntity getTaskForId(Long id) throws TaskException {

        Optional<TaskEntity> tasks = taskRepo.findById(id);
        if(!tasks.isPresent()) {
            throw new TaskException("No task found");
        }
        return tasks.get();
    }

    public void addTask(TaskEntity task) throws TaskException, TaskSizeException {
        createOrUpdateTask(task);
    }

    @Override
    public void updateTask(TaskEntity task) throws TaskException, TaskSizeException {
        createOrUpdateTask(task);
    }

    public void createOrUpdateTask(TaskEntity task) throws TaskException, TaskSizeException {
        if(!TaskValidationUtils.validateTaskDetails(task)) {
            throw new TaskException("Task has missing details");
        }
        if(!TaskValidationUtils.validateTaskDetailsLengthLimit(task)) {
            throw new TaskSizeException("Task title or description exceeds the character limit");
        }
        if(Objects.isNull(task.getId())) {
            taskRepo.save(task);
            return;
        }
        Optional<TaskEntity> taskToBeUpdated = taskRepo.findById(task.getId());
        taskToBeUpdated.ifPresentOrElse(taskEntity -> {
            taskEntity.setTitle(StringUtils.isNotBlank(task.getTitle()) ? task.getTitle() : taskEntity.getTitle());
            taskEntity.setDescription(StringUtils.isNotBlank(task.getDescription()) ? task.getDescription() : taskEntity.getDescription());
            taskEntity.setPriority(StringUtils.isNotBlank(task.getPriority()) ? task.getPriority() : taskEntity.getPriority());
            taskRepo.save(taskEntity);
        }, () -> taskRepo.save(task));
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
