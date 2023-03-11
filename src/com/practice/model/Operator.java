package com.practice.model;

import com.practice.service.ComparatorInterface;
import com.practice.service.EqualToComparator;
import com.practice.service.GreaterThanComparator;
import com.practice.service.LessThanComparator;


public enum Operator {
	EQUAL_TO(new EqualToComparator()),
	LESS_THAN(new LessThanComparator()),
	GREATER_THAN(new GreaterThanComparator());

	ComparatorInterface c;
	Operator(ComparatorInterface c){
		this.c = c;
	}

	public boolean compare(Comparable o1, Comparable o2) {
		return c.compare(o1, o2);
	}


}
