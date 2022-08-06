package com.example.Todo.List.controllers;

import com.example.Todo.List.model.TodoItem;
import com.example.Todo.List.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.Instant;

@Controller

public class TodoFormController {

    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class.getName());

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateTodoForm(TodoItem todoItem) {
        return "add-todo-item";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("TodoItem id : " + id + "not found."));

        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model){

        if(result.hasErrors()) {
            todoItem.setId(id);
            return "update-todo-item";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem (@PathVariable("id") long id ,Model model){

        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("TodoItem id : " + id + "not found."));

        todoItemRepository.delete(todoItem);

        return "redirect:/";
    }

    @PostMapping("/todo")
    public String createNewTodo(@Valid TodoItem todoItem, BindingResult result, Model model ){

        if (result.hasErrors()) return "add-todo-item";

        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);

        return "redirect:/";
    }

}
