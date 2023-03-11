package com.practice.model;

import java.time.LocalDateTime;

public class Log {
	Integer taskId;
	Action action;

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	LocalDateTime createDate;

	public Log(Integer id, Action action, LocalDateTime createDate) {
		this.taskId = id;
		this.action = action;
		this.createDate = createDate;
	}

	public void displayLog() {
		System.out.println(taskId + "   " + action + "   " + createDate );
	}
}
