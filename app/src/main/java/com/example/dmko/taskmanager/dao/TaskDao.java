package com.example.dmko.taskmanager.dao;


import com.example.dmko.taskmanager.Entity.Task;

import java.util.List;

public interface TaskDao {
    void addTask(Task task);
    List<Task> getAllTasks();
    List<String> getAllTaskNames();
    void updateTaskById(int id, Task newTask);
    void deleteTaskById(int id);
    int getTaskIdByName(String name);
}
