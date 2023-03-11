package com.practice.service;

public interface ComparatorInterface {

	<T extends Comparable> boolean compare(T o1, T o2);
}
