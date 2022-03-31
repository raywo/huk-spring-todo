package de.huk.seminars.todoapp.control;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TodosService {
  private Map<Long, Todo> todos = new HashMap<>();


  @PostConstruct
  public void initData() {
    todos.put(1L, new Todo(1L, "erstes Todo", "2022-04-01"));
    todos.put(2L, new Todo(2L, "zweites Todo", "2022-04-01"));
  }


  public Collection<Todo> allTodos() {
    return todos.values();
  }


  public Optional<Todo> singleTodo(Long id) {
    return Optional.of(todos.get(id));
  }


  public Todo createTodo(Todo newTodo) {
    // TODO Was passiert, wenn das Todo schon existiert?
    Long newId = getNextId();

    newTodo.setId(newId);
    todos.put(newId, newTodo);

    return newTodo;
  }


  public Todo updateTodo(Todo updatedTodo) {
    // TODO Was passiert, wenn das Todo noch nicht existiert?
    todos.put(updatedTodo.getId(), updatedTodo);

    return updatedTodo;
  }


  public boolean delete(Long id) {
    Todo removedTodo = todos.remove(id);

    return removedTodo != null;
  }


  private Long getNextId() {
    return todos
        .keySet()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(0L) + 1L;
  }
}
