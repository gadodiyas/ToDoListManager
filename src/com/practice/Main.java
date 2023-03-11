package com.practice;

import com.practice.controller.ToDoManager;
import com.practice.model.Field;
import com.practice.model.Operator;
import com.practice.model.Order;
import com.practice.model.Status;
import com.practice.model.Task;
import com.practice.model.ToDos;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ToDos toDos = new ToDos();
        ToDoManager todoManager = new ToDoManager(toDos);
        Task t1 = todoManager.addTask(new Task("Complete Essay", LocalDateTime.of(2023, Month.MARCH, 28, 14, 33)));
        Task t2 = todoManager.addTask(new Task("Complete Paper", LocalDateTime.of(2023, Month.MARCH, 8, 14, 33)));
        Task t3 = todoManager.addTask(new Task("Complete Paper", LocalDateTime.of(2023, Month.MARCH, 9, 14, 33)));

        todoManager.getActivityLog();
        Task task = todoManager.getTask(t1.getId());
        todoManager.modifyTask(t1);
        todoManager.removeTask(t1.getId());
        //assuming we need diff function to mark complete
        todoManager.markComplete(t2.getId());
        todoManager.getActivityLog();
        todoManager.getActivityLog(LocalDateTime.of(2019, Month.MARCH, 7, 0, 0), LocalDateTime.of(2019, Month.MARCH, 10, 22, 59) );
        todoManager.getStatistics();
        todoManager.getStatistics(LocalDateTime.of(2019, Month.MARCH, 7, 0, 0), LocalDateTime.of(2019, Month.MARCH, 10, 22, 59));
        todoManager.getList(Field.STATUS, Operator.EQUAL_TO, Status.PENDING, Field.DEADLINE, Order.ASC);
    }
}
