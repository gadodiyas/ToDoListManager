package com.practice.controller;

import com.practice.model.Field;
import com.practice.model.Operator;
import com.practice.model.Order;
import com.practice.model.Statistic;
import com.practice.model.Status;
import com.practice.model.Task;
import com.practice.model.ToDos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ToDoManager {
	ToDos toDos;
	public ToDoManager(ToDos toDos) {
		this.toDos = toDos;
	}

	public Task addTask(Task task) {
		return toDos.addTask(task);
	}


	public Task getTask(Integer id) {
		return toDos.getTask(id);
	}

	public void modifyTask(Task task) {
		if(task.getId() == null) {
			throw new RuntimeException("task id should be present");
		}
		toDos.modifyTask(task.getId(), task);
	}

	public void removeTask(Integer id) {
		toDos.removeTask(id);
	}

	public void markComplete(Integer id) {
		toDos.markComplete(id);
	}

	public void getActivityLog() {
		toDos.getActivityLog();
	}

	public void getActivityLog(LocalDateTime from , LocalDateTime to) {
		toDos.getActivityLog(from, to);
	}

	public void getStatistics() {
		Map<Statistic, Integer> map = toDos.getStatistics();
		toDos.displayStatistics(map);
	}

	public void getStatistics(LocalDateTime from, LocalDateTime to) {
		Map<Statistic, Integer> map = toDos.getStatistics(from, to);
		toDos.displayStatistics(map);
	}

	public List<Task> getList(Field searchField, Operator operator,  Object target, Field orderField, Order order) {
		return toDos.getList(searchField, operator, target, orderField, order);
	}
}
