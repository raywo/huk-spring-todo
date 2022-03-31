package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

  TodoDto map(Todo todo) {
    if (todo == null) {
      return null;
    }

    TodoDto result = new TodoDto();

    result.setId(todo.getId());
    result.setTitle(todo.getTitle());
    result.setDueDate(todo.getDueDate());

    return result;
  }


  Todo map(TodoDto todoDto) {
    if (todoDto == null) {
      return null;
    }

    Todo result = new Todo();

    result.setId(todoDto.getId());
    result.setTitle(todoDto.getTitle());
    result.setDueDate(todoDto.getDueDate());

    return result;
  }
}
