package com.example.dmko.taskmanager.dao;


import com.example.dmko.taskmanager.entity.Task;

import java.util.List;

public interface TaskDao {
    void addTask(Task task);
    List<Task> getAllTasks();
    void updateTaskById(int id, Task newTask);
    void deleteTaskById(int id);
}
