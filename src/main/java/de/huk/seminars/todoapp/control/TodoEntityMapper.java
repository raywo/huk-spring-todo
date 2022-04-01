package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.entity.TodoEntity;
import org.springframework.stereotype.Component;

@Component
public class TodoEntityMapper {

  Todo map(TodoEntity todoEntity) {
    if (todoEntity == null) {
      return null;
    }

    Todo result = new Todo();
    result.setId(todoEntity.getId());
    result.setTitle(todoEntity.getTitle());
    result.setDueDate(todoEntity.getDueDate());
    result.setPassword(todoEntity.getPassword());

    return result;
  }


  TodoEntity map(Todo todo) {
    if (todo == null) {
      return null;
    }

    TodoEntity result = new TodoEntity();
    result.setId(todo.getId());
    result.setTitle(todo.getTitle());
    result.setDueDate(todo.getDueDate());
    result.setPassword(todo.getPassword());

    return result;
  }
}
