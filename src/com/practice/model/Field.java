package com.practice.model;

import com.practice.service.ComparatorInterface;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Field {
	ID(Arrays.asList(Operator.EQUAL_TO), Comparator.naturalOrder()),
	TAG(Arrays.asList(Operator.EQUAL_TO),Comparator.naturalOrder()),
	DEADLINE(Arrays.asList(Operator.EQUAL_TO, Operator.GREATER_THAN, Operator.LESS_THAN ), Comparator.naturalOrder()),
	STATUS(Arrays.asList(Operator.EQUAL_TO), Comparator.naturalOrder());

	List<Operator> operatorList;
	Comparator c;
	Field(List<Operator> operatorList, Comparator c) {
		this.operatorList = operatorList;
		this.c = c;
	}

	void sort(List<Task> tasks, Order order) {
		if(order == Order.DESC)
			c = Comparator.reverseOrder();
		Collections.sort(tasks, c);
	}


}
