package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.entity.AssigneeEntity;
import org.springframework.stereotype.Component;

@Component
public class AssigneeEntityMapper {

  public Assignee map(AssigneeEntity assignee) {
    if (assignee == null) {
      return null;
    }

    Assignee result = new Assignee();
    result.setId(assignee.getId());
    result.setName(assignee.getName());

    return result;
  }


  public AssigneeEntity map(Assignee assignee) {
    if (assignee == null) {
      return null;
    }

    AssigneeEntity result = new AssigneeEntity();
    result.setId(assignee.getId());
    result.setName(assignee.getName());

    return result;
  }
}
