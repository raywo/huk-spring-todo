package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.Assignee;
import org.springframework.stereotype.Component;

@Component
public class AssigneesMapper {

  Assignee map(AssigneeDto assigneeDto) {
    if (assigneeDto == null) {
      return null;
    }

    Assignee result = new Assignee();
    result.setId(assigneeDto.getId());
    result.setName(assigneeDto.getName());

    return result;
  }


  AssigneeDto map(Assignee assignee) {
    if (assignee == null) {
      return null;
    }

    AssigneeDto result = new AssigneeDto();
    result.setId(assignee.getId());
    result.setName(assignee.getName());

    return result;
  }
}
