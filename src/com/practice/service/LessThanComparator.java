package com.practice.service;

public class LessThanComparator implements ComparatorInterface{

	@Override
	public <T extends Comparable> boolean compare(T o1, T o2) {
		return o1.compareTo(o2) < 0;
	}


}
