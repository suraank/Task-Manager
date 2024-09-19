package com.example.todo.exception;

public class TaskSizeException extends Exception{
    public TaskSizeException(String error) {
        super(error);
    }
}
