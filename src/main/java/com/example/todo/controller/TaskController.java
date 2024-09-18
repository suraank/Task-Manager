package com.example.todo.controller;

import com.example.todo.entity.TaskEntity;
import com.example.todo.services.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    @GetMapping("/getTasks")
    public List<TaskEntity> getTasks() {
        return taskService.getTaskList();
    }

    @PostMapping("/createTask")
    public ResponseEntity createTask(@RequestBody TaskEntity task) {

        try {
            taskService.addTask(task);
        } catch (Exception e) {

        }
    }

    @GetMapping("/getTasks")
    public TaskEntity getTaskForId(@PathVariable Long id) {
        return taskService.getTaskForId(id);
    }

    @PutMapping("/updateTask")
    public void updateTask(@RequestBody TaskEntity task) {
        taskService.updateTask(task);
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
