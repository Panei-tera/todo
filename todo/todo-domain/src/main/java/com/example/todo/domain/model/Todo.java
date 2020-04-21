package com.example.todo.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int todoId;
	public String todoTitle;
	public boolean finished;
	public Date createAt;

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
