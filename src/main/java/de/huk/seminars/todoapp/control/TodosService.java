package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.boundary.TodoDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TodosService {
  private Map<Long, TodoDto> todos = new HashMap<>();


  @PostConstruct
  public void initData() {
    todos.put(1L, new TodoDto(1L, "erstes Todo", "2022-04-01"));
    todos.put(2L, new TodoDto(2L, "zweites Todo", "2022-04-01"));
  }


  public Collection<TodoDto> allTodos() {
    return todos.values();
  }


  public Optional<TodoDto> singleTodo(Long id) {
    return Optional.of(todos.get(id));
  }


  public TodoDto createTodo(TodoDto newTodo) {
    // TODO Was passiert, wenn das Todo schon existiert?
    Long newId = getNextId();

    newTodo.setId(newId);
    todos.put(newId, newTodo);

    return newTodo;
  }


  public TodoDto updateTodo(TodoDto updatedTodo) {
    // TODO Was passiert, wenn das Todo noch nicht existiert?
    todos.put(updatedTodo.getId(), updatedTodo);

    return updatedTodo;
  }


  public boolean delete(Long id) {
    TodoDto removedTodo = todos.remove(id);

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
