package com.example.todo.controller;

import com.example.todo.entity.TaskEntity;
import com.example.todo.exception.TaskException;
import com.example.todo.exception.TaskSizeException;
import com.example.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/getTasks")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        return new ResponseEntity<>(taskService.getTaskList(), HttpStatus.OK);
    }

    @PostMapping("/createTask")
    public ResponseEntity createTask(@RequestBody TaskEntity task) throws Exception {

        try {
            taskService.addTask(task);
            return new ResponseEntity("Task added successfully", HttpStatus.OK);
        } catch (TaskException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (TaskSizeException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) throws TaskException {
        try {
            return new ResponseEntity<>(taskService.getTaskForId(id), HttpStatus.OK);
        } catch (TaskException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/updateTask")
    public ResponseEntity updateTask(@RequestBody TaskEntity task) throws TaskException, TaskSizeException {
        taskService.updateTask(task);
        return new ResponseEntity("Task updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity("Task deleted successfully", HttpStatus.OK);
    }
}
