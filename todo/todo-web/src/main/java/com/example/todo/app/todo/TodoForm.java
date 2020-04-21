package com.example.todo.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoForm implements Serializable {

	private static final long serialVersionUID = 1L;

	public static interface TodoCreate {
    };

    public static interface TodoFinish {
    };

    public static interface TodoDelete {
    };

	@NotNull(groups = {TodoCreate.class})
	@Size(min = 1, max = 30, groups = {TodoCreate.class})
	private String todoTitle;

	@NotNull(groups = {TodoFinish.class, TodoDelete.class})
	private int todoId;

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
}