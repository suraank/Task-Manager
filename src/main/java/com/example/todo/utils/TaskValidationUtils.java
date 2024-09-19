package com.example.todo.utils;

import com.example.todo.entity.TaskEntity;
import io.micrometer.common.util.StringUtils;

import java.util.Objects;

public class TaskValidationUtils {

    public static boolean validateTaskDetails(TaskEntity task) {
        boolean isTaskValidated = !Objects.isNull(task);
        if (Objects.isNull(task.getTitle()) || Objects.isNull(task.getDescription()) || Objects.isNull(task.getPriority()))
            isTaskValidated = false;
        if (StringUtils.isBlank(task.getTitle()) || StringUtils.isBlank(task.getDescription()) || StringUtils.isBlank(task.getPriority())) {
            isTaskValidated = false;
        }
        return isTaskValidated;
    }

    public static boolean validateTaskDetailsLengthLimit(TaskEntity task) {
        return task.getTitle().length() <= 30 && task.getDescription().length() <= 200;
    }
}
