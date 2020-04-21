package com.example.todo.app.todo;

import java.util.List;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.todo.app.todo.TodoForm.TodoCreate;
import com.example.todo.app.todo.TodoForm.TodoDelete;
import com.example.todo.app.todo.TodoForm.TodoFinish;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.TodoService;

@Controller
public class TodoController {

	@Inject
	Mapper beanMapper;

	@Inject
	TodoService todoService;

	@ModelAttribute
	public TodoForm setUpForm() {
		TodoForm form = new TodoForm();
		return form;
	}

	@RequestMapping(value = "/")
	public String init(Model model) {
		List<Todo> todos = todoService.findAll();
		model.addAttribute("todos", todos);
		return "todo/list";
	}

	@RequestMapping(value = "create")
	public String createTodo(@Validated({Default.class, TodoCreate.class}) TodoForm form, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return init(model);
		}
		Todo todo = beanMapper.map(form, Todo.class);
		try {
			todoService.create(todo);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return init(model);
		}
		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Created successfully!")));
		return "redirect:/";
	}

	@RequestMapping(value = "finish", method = RequestMethod.POST)
	public String finish(@Validated({ Default.class, TodoFinish.class }) TodoForm form, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return init(model);
		}
		try {
			todoService.finish(form.getTodoId());
		} catch(BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return init(model);
		}
		attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Finished successfully!")));
		return "redirect:/";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteTodo(@Validated({ Default.class, TodoDelete.class }) TodoForm form, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return init(model);
		}
		try {
			todoService.delete(form.getTodoId());
		} catch(BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return init(model);
		}
		attributes.addFlashAttribute(ResultMessages.success().add(
                ResultMessage.fromText("Deleted successfully!")));
		return "redirect:/";
	}
}
