package org.example.spring_boot_curd_rest_app.service;


import lombok.RequiredArgsConstructor;
import org.example.spring_boot_curd_rest_app.repository.TaskRepository;
import org.example.spring_boot_curd_rest_app.model.Task;
import org.example.spring_boot_curd_rest_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    // Create
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get All task
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task By ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Update task
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStartDate(updatedTask.getStartDate());
        existingTask.setEndDate(updatedTask.getEndDate());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }

    // Delete a task
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

}
