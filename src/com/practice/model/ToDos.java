package com.practice.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ToDos {

	List<Task> completedTasks = new ArrayList<>();
	Map<Integer, Task> pendingTasks = new HashMap<>();
	Map<Action, List<Log>> activityLog = new HashMap<>();

	public ToDos() {
		for(Action a : Action.values()) {
			activityLog.put(a, new ArrayList<>());
		}
	}

	public Task addTask(Task task) {
		pendingTasks.put(task.getId(), task);
		activityLog.get(Action.ADDED).add(new Log(task.getId(), Action.ADDED, task.createdDate ));
		return task;

	}

	public Task getTask(Integer id) {
		Task task =  pendingTasks.get(id);
		if(task == null)
			throw new RuntimeException("Task not found, either it is completed or not added");
		return task;
	}

	public void modifyTask(Integer id, Task task) {
		Task t = pendingTasks.get(id);
		if(t == null) {
			throw new RuntimeException("No task found matching given id or it is already completed");
		}
		pendingTasks.put(id, task);
		activityLog.get(Action.MODIFIED).add(new Log(task.getId(), Action.MODIFIED, LocalDateTime.now() ));

	}

	public void removeTask(Integer id) {
		Task t = pendingTasks.get(id);
		if(t == null) {
			throw new RuntimeException("No task found matching given id or it is already completed");
		}
		pendingTasks.remove(id);
		activityLog.get(Action.REMOVED).add(new Log(id, Action.REMOVED, LocalDateTime.now() ));

	}

	public void markComplete(Integer id) {
		Task t = pendingTasks.get(id);
		if(t == null) {
			throw new RuntimeException("No task found matching given id or it is already completed");
		}
		pendingTasks.remove(id);
		t.setCompletedDate(LocalDateTime.now());
		completedTasks.add(t);
		activityLog.get(Action.COMPLETED).add(new Log(id, Action.COMPLETED, t.completedDate));
	}

	public void getActivityLog() {
		for(List<Log> logs :activityLog.values()){
			for (Log l : logs){
				l.displayLog();
			}
		}
	}

	public void getActivityLog(LocalDateTime from, LocalDateTime to) {
		for(List<Log> logs :activityLog.values()){
			for (Log l : logs){
				if(l.getCreateDate().isAfter(from) && l.getCreateDate().isBefore(to))
					l.displayLog();
			}
		}
	}

	public Map<Statistic, Integer> getStatistics(){
		Map<Statistic, Integer> map = new HashMap<>();
		map.put(Statistic.COMPLETED, completedTasks.size());
		int cntAdded = 0;
		int cntSpilled = 0;
		for(Task task: pendingTasks.values()) {
			cntAdded++;
			if(LocalDateTime.now().isAfter(task.deadline))
				cntSpilled++;
		}
		map.put(Statistic.ADDED, cntAdded);
		map.put(Statistic.SPILLED, cntSpilled);
		return map;
	}

	public void displayStatistics(Map<Statistic, Integer> map) {
		for (Statistic statistic : map.keySet()){
			System.out.println(statistic + "    " + map.get(statistic));
		}
	}

	public Map<Statistic, Integer> getStatistics(LocalDateTime from, LocalDateTime to) {
		Map<Statistic, Integer> map = new HashMap<>();
		map.put(Statistic.ADDED, getCnt(Action.ADDED, from, to));
		map.put(Statistic.COMPLETED, getCnt(Action.COMPLETED, from, to));
		int cntSpilled = 0;

		for(Task t : getSpilledTasks()) {
			if(t.createdDate.isAfter(from) && t.createdDate.isBefore(to))
				cntSpilled++;
		}
		map.put(Statistic.SPILLED, cntSpilled);
		return map;
	}

	private Integer getCnt(Action action, LocalDateTime from, LocalDateTime to) {
		int cnt = 0;
		for (Log log: activityLog.get(action)) {
			if(log.getCreateDate().isAfter(from) && log.getCreateDate().isBefore(to))
				cnt++;
		}
		return cnt;
	}

	private List<Task> getSpilledTasks() {
		List<Task> tasks = new ArrayList<>();
		for(Task task: pendingTasks.values()) {
			if(LocalDateTime.now().isAfter(task.deadline))
				tasks.add(task);
		}
		return tasks;
	}


	public List<Task> getList(Field searchField, Operator operator, Object target, Field orderField, Order order) {
		List<Task> tasks = filterByCriteria(searchField, operator, target);
		orderListByGivenFieldAndOrder(tasks, orderField, order);
		displayTaskList(tasks);
		return tasks;
	}

	private void displayTaskList(List<Task> tasks) {
		for(Task t : tasks) {
			System.out.println(t.id + "    " + t.item + "    " + t.deadline + "    " + t.status + "     ");
		}
	}

	private void orderListByGivenFieldAndOrder(List<Task> tasks, Field orderField, Order order) {
		orderField.sort(tasks, order);
	}

	private List<Task> filterByCriteria(Field searchField, Operator operator, Object target) {
		switch (searchField) {
			case ID:
				if(Field.ID.operatorList.contains(operator)) {
					List<Task> tasks = new ArrayList<>();
					for (Task t : pendingTasks.values()) {
						if(operator.compare((int)target,t.id))
							tasks.add(t);
					}
					return tasks;
				} else
					throw new RuntimeException("Invalid operation on given field");
			case DEADLINE:
				if(Field.DEADLINE.operatorList.contains(operator)) {
					List<Task> tasks = new ArrayList<>();
					for (Task t : pendingTasks.values()) {
						if(operator.compare((LocalDateTime)target,t.deadline))
							tasks.add(t);
					}
					return tasks;
				} else
					throw new RuntimeException("Invalid operation on given field");
			case STATUS:
				if(Field.STATUS.operatorList.contains(operator)) {
					List<Task> tasks = new ArrayList<>();
					for (Task t : pendingTasks.values()) {
						if(operator.compare((Status)target,t.status))
							tasks.add(t);
					}
					return tasks;
				} else
					throw new RuntimeException("Invalid operation on given field");

			default:
				throw new RuntimeException("Not a valid field for search criteria");
		}
	}




}
