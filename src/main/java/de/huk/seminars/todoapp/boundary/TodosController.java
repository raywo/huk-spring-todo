package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {

  private final TodosService todosService;


  @Autowired
  public TodosController(TodosService todosService) {
    this.todosService = todosService;
  }


  @GetMapping
  public Collection<TodoDto> allTodos() {
    return todosService.allTodos();
  }


  @GetMapping("/{id}")
  public ResponseEntity<TodoDto> singleTodo(@PathVariable Long id) {
    return todosService
        .singleTodo(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @PostMapping
  public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto newTodo) {
    if (newTodo.getId() != null) {
      return ResponseEntity.badRequest().build();
    }

    TodoDto createdTodo = todosService.createTodo(newTodo);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
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

    return ResponseEntity.ok(todosService.updateTodo(updatedTodo));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    boolean deleted = todosService.delete(id);

    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
