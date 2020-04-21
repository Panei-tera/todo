package com.example.todo.domain.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	private static final long MAX_UNFINISHED_COUNT = 10;
	
	@Inject
	TodoRepository todoRepository;
	
	@Override
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	@Override
	public void create(Todo todo) {
		long unfinished = todoRepository.countByFinished(false);
		if(MAX_UNFINISHED_COUNT <= unfinished) {
			ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E001] The count of un-finished Todo must not be over "
                            + MAX_UNFINISHED_COUNT + "."));
            throw new BusinessException(messages);
		}
		Date createdAt = new Date();
		todo.setFinished(false);
		todo.setCreateAt(createdAt);
		todoRepository.create(todo);
	}

	@Override
	public Todo finish(int todoId) {
		Todo todo = todoRepository.findOne(todoId);
		if(todo.isFinished()) {
			ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E002] The requested Todo is already finished. (id="
                            + todoId + ")"));
            throw new BusinessException(messages);
		}
		todo.setFinished(true);
		todoRepository.update(todo);
		return todo;
	}

	@Override
	public void delete(int todoId) {
		Todo todo = todoRepository.findOne(todoId);
		todoRepository.delete(todo);
	}

}
