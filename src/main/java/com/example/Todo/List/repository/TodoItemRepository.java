package com.example.Todo.List.repository;

import com.example.Todo.List.model.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem , Long> {




}
