package com.example.todo.domain.repository;

import java.util.List;

import com.example.todo.domain.model.Todo;

public interface TodoRepository {
	Todo findOne(int todoId);
	List<Todo> findAll();
	void create(Todo todo);
	void delete(Todo todo);
	void update(Todo todo);
	long countByFinished(boolean finished);
}
