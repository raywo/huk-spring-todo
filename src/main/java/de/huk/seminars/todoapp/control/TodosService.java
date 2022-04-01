package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.entity.TodoEntity;
import de.huk.seminars.todoapp.entity.TodoRepository;
import de.huk.seminars.todoapp.shared.validation.OnCreate;
import de.huk.seminars.todoapp.shared.validation.OnUpdate;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodosService {

  private final TodoRepository repository;
  private final TodoEntityMapper mapper;


  public TodosService(TodoRepository repository, TodoEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

//
//  @PostConstruct
//  public void initData() {
//    todos.put(1L, new Todo(1L, "erstes Todo", "2022-04-01", "", ""));
//    todos.put(2L, new Todo(2L, "zweites Todo", "2022-04-01", "", ""));
//  }


  public Collection<Todo> allTodos() {
    return repository.findAll()
        .stream()
        .map(mapper::map)
        .collect(Collectors.toList());
  }


  public Optional<Todo> singleTodo(Long id) {
    return repository.findById(id)
        .map(mapper::map);
  }


  @NonNull
  public Todo createTodo(@Validated(OnCreate.class) Todo newTodo) {
    TodoEntity savedTodo = repository.save(mapper.map(newTodo));
    return mapper.map(savedTodo);
  }


  @NonNull
  public Todo updateTodo(@Validated(OnUpdate.class) Todo updatedTodo) throws NotFoundException {
    if (!repository.existsById(updatedTodo.getId())) {
      throw new NotFoundException("Das Todo konnte nicht gefunden werden.");
    }

    TodoEntity savedTodo = repository.save(mapper.map(updatedTodo));

    return mapper.map(savedTodo);
  }


  public void delete(Long id) throws NotFoundException {
    if (!repository.existsById(id)) {
      throw new NotFoundException("Das Todo konnte nicht gefunden werden.");
    }

    repository.deleteById(id);
  }
}
