package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {

  TodoDto map(Todo todo);

  Todo map(TodoDto todoDto);
}
