package com.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.model.Task;
import com.todo.app.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService taskService;
    
	//create a new task 
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task){
		Task createdTask = taskService.saveTask(task);
		return ResponseEntity.ok(createdTask);
	}
	
	//get all tasks
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(){
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	//get a task by Id
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id){
		return taskService.getTaskById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	//update a task
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task updatedTask){
		return taskService.getTaskById(id).map(existingTask ->{
			existingTask.setTitle(updatedTask.getTitle());
			existingTask.setDescription(updatedTask.getDescription());
			existingTask.setCompleted(updatedTask.isCompleted());
			Task saved = taskService.saveTask(existingTask);
			return ResponseEntity.ok(saved);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
	//delete a task 
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}
