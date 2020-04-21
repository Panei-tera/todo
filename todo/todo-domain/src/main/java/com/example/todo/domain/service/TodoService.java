package com.example.todo.domain.service;

import java.util.List;

import com.example.todo.domain.model.Todo;

public interface TodoService {
	List<Todo> findAll();
	void create(Todo todo);
	Todo finish(int todoId);
	void delete(int todoId);
}
