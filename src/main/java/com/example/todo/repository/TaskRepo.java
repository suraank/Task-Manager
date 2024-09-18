package com.example.todo.repository;

import com.example.todo.entity.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long>{
}
