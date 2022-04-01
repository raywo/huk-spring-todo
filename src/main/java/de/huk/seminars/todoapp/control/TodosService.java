package de.huk.seminars.todoapp.control;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TodosService {
  private Map<Long, Todo> todos = new HashMap<>();


  @PostConstruct
  public void initData() {
    todos.put(1L, new Todo(1L, "erstes Todo", "2022-04-01", "", ""));
    todos.put(2L, new Todo(2L, "zweites Todo", "2022-04-01", "", ""));
  }


  public Collection<Todo> allTodos() {
    return todos.values();
  }


  public Optional<Todo> singleTodo(Long id) {
    return Optional.of(todos.get(id));
  }


  @NonNull
  public Todo createTodo(Todo newTodo) throws CouldNotCreateItemException {
    Long newId = getNextId();

    if (todos.containsKey(newId)) {
      throw new CouldNotCreateItemException("Das Todo konnte nicht erzeugt werden.");
    }

    newTodo.setId(newId);
    todos.put(newId, newTodo);

    return newTodo;
  }


  @NonNull
  public Todo updateTodo(Todo updatedTodo) throws NotFoundException {
    if (!todos.containsKey(updatedTodo.getId())) {
      throw new NotFoundException("Das Todo konnte nicht gefunden werden.");
    }

    todos.put(updatedTodo.getId(), updatedTodo);

    return updatedTodo;
  }


  public void delete(Long id) throws NotFoundException {
    if (todos.remove(id) == null) {
      throw new NotFoundException("Todo konnte nicht gelöscht werden.");
    }
  }


  private Long getNextId() {
    return todos
        .keySet()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(0L) + 1L;
  }
}
