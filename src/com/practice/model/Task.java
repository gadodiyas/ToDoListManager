package com.practice.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Task {
	public Integer getId() {
		return id;
	}

	Integer id;
	String item;
	Status status;
	LocalDateTime deadline;


	public void setCompletedDate(LocalDateTime completedDate) {
		this.completedDate = completedDate;
	}

	LocalDateTime completedDate;
	LocalDateTime createdDate;
	Set<String> tags;

	public Task(String item, LocalDateTime deadline) {
		this.deadline = deadline;
		this.item = item;
		createdDate =  LocalDateTime.now();
		status = Status.PENDING;

		id =  (int)Math.floor(Math.random() * (100 - 1 + 1) + 1);
	}

	public Task(String item, LocalDateTime deadline, Set<String> tags) {
		this(item, deadline);
		this.tags = tags;
	}
}
