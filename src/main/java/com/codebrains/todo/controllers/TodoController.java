package com.codebrains.todo.controllers;

import com.codebrains.todo.entities.Todo;
import com.codebrains.todo.entities.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    @GetMapping("/{todoId}")
    public Optional<Todo> getTodo(@PathVariable("todoId") Long todoId){
        var todo = todoRepository.findById(todoId);
        return todo;
    }

    @PostMapping
    public Todo newTodo(@RequestBody Todo todo){
        return this.todoRepository.save(todo);
    }

    @PutMapping("/{todoId}")
    public Optional<Todo> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo updatedTodo){
        return this.todoRepository.findById(todoId)
                .map(oldTodo -> this.todoRepository.save(updatedTodo));
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable("todoId") Long todoId){
        this.todoRepository.deleteById(todoId);
    }
}
