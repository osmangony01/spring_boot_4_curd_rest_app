package org.example.spring_boot_curd_rest_app.controller;

import org.example.spring_boot_curd_rest_app.model.Task;
import org.example.spring_boot_curd_rest_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {

     @Autowired
     private TaskService taskService;


    public Map<String, Object> resFormat(boolean success, String message, Object data, Object error) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", message);
        response.put("data", data);
        response.put("error", error);

        return response;
    }

    // Create Task
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);

        Map<String, Object> response = resFormat(true, "Operation Successful", createdTask, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Tasks
    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        Map<String, Object> response = resFormat(true, "Operation Successful", tasks, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Get Task By ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        //return ResponseEntity.ok(taskService.getTaskById(id));
        Task task = taskService.getTaskById(id);

        Map<String, Object> response = resFormat(true, "Operation Successful", task, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Task
    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task task) {
        //return ResponseEntity.ok(taskService.updateTask(id, task));
        Task upatedTask = taskService.updateTask(id, task);

        Map<String, Object> response = resFormat(true, "Operation Successful", upatedTask, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete Task
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        Map<String, Object> response = resFormat(true, "Operation Successful", null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
