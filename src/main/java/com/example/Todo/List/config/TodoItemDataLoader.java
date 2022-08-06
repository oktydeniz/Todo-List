package com.example.Todo.List.config;

import com.example.Todo.List.model.TodoItem;
import com.example.Todo.List.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TodoItemDataLoader implements CommandLineRunner {

    private final Logger logger = Logger.getLogger(TodoItemDataLoader.class.getName());

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (todoItemRepository.count() == 0){
            TodoItem todoItem = new TodoItem("Spring 1 ToDo");
            TodoItem todoItem2 = new TodoItem("Spring 2 ToDo");

            todoItemRepository.save(todoItem);
            todoItemRepository.save(todoItem2);
        }
        logger.info("Number of Todo Item: " + todoItemRepository.count());
    }
}
