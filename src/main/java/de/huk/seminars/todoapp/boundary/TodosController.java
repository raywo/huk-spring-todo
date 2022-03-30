package de.huk.seminars.todoapp.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {

  private Map<Long, TodoDto> todos = new HashMap<>();

  @PostConstruct
  public void initData() {
    todos.put(1L, new TodoDto(1L, "erstes Todo", "2022-04-01"));
    todos.put(2L, new TodoDto(2L, "zweites Todo", "2022-04-01"));
  }

  @GetMapping
  public Collection<TodoDto> allTodos() {
    return todos.values();
  }


  @GetMapping("/{id}")
  public ResponseEntity<TodoDto> singleTodo(@PathVariable Long id) {
    TodoDto todoDto = todos.get(id);

    if (todoDto == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(todoDto);
    }
  }


  @PostMapping
  public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto newTodo) {
    if (newTodo.getId() != null) {
      return ResponseEntity.badRequest().build();
    }

    Long newId = todos
        .keySet()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(0L) + 1L;

    newTodo.setId(newId);
    todos.put(newId, newTodo);

    return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
  }


  @PutMapping("/{id}")
  public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,
                                            @RequestBody TodoDto updatedTodo) {
    if (updatedTodo.getId() == null) {
      return ResponseEntity.badRequest().build();
    }

    if (!id.equals(updatedTodo.getId())) {
      return ResponseEntity.badRequest().build();
    }

    todos.put(id, updatedTodo);

    return ResponseEntity.ok(updatedTodo);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTodo(@PathVariable Long id) {
    todos.remove(id);
  }
}
