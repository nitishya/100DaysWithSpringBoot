package com.todo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.app.model.Task;
import com.todo.app.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	//create a task
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}
	
	//get all the task
	public List<Task> getAllTasks(){
		return  taskRepository.findAll();
	}
	
	//get a task by ID
	public Optional<Task> getTaskById(Long id){
		return taskRepository.findById(id);
	}
	
	
	//delete a task by ID
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
}
