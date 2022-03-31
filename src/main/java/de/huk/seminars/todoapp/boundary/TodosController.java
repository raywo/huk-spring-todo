package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.CouldNotCreateItemException;
import de.huk.seminars.todoapp.control.NotFoundException;
import de.huk.seminars.todoapp.control.Todo;
import de.huk.seminars.todoapp.control.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {

  private final TodosService todosService;
  private final TodoMapper todoMapper;


  @Autowired
  public TodosController(TodosService todosService, TodoMapper todoMapper) {
    this.todosService = todosService;
    this.todoMapper = todoMapper;
  }


  @GetMapping
  public Collection<TodoDto> allTodos() {
    return todosService.allTodos()
        .stream()
        // .map(todo -> todoMapper.map(todo)) identisch zur folgenden Zeile
        .map(todoMapper::map)
        .collect(Collectors.toList());
  }


  @GetMapping("/{id}")
  public ResponseEntity<TodoDto> singleTodo(@PathVariable Long id) {
    return todosService
        .singleTodo(id)
        .map(todoMapper::map)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TodoDto createTodo(@RequestBody @Validated(OnCreate.class) TodoDto newTodo)
      throws CouldNotCreateItemException {
    return todoMapper.map(
        todosService.createTodo(todoMapper.map(newTodo))
    );
  }


  @PutMapping("/{id}")
  public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,
                                            @RequestBody @Validated(OnUpdate.class) TodoDto updatedTodo)
      throws NotFoundException {
    if (!id.equals(updatedTodo.getId())) {
      return ResponseEntity.badRequest().build();
    }

    Todo todo = todosService.updateTodo(todoMapper.map(updatedTodo));

    return ResponseEntity.ok(todoMapper.map(todo));
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTodo(@PathVariable Long id) throws NotFoundException {
    todosService.delete(id);
  }
}
