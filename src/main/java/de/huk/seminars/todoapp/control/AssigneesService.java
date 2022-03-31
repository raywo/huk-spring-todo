package de.huk.seminars.todoapp.control;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AssigneesService {

  private final Map<Long, Assignee> assignees = new HashMap<>();


  @PostConstruct
  public void initData() {
    assignees.put(1L, new Assignee(1L, "Ray"));
    assignees.put(2L, new Assignee(2L, "Andi"));
  }


  public Collection<Assignee> allAssignees() {
    return assignees.values();
  }


  public Optional<Assignee> singleTodo(Long id) {
    return Optional.of(assignees.get(id));
  }


  public Assignee createAssignee(Assignee newAssignee) {
    // TODO Was passiert, wenn das Todo schon existiert?
    Long newId = getNextId();

    newAssignee.setId(newId);
    assignees.put(newId, newAssignee);

    return newAssignee;
  }


  public Assignee updateAssignee(Assignee updatedAssignee) {
    // TODO Was passiert, wenn das Todo noch nicht existiert?
    assignees.put(updatedAssignee.getId(), updatedAssignee);

    return updatedAssignee;
  }


  public boolean delete(Long id) {
    Assignee removedAssignee = assignees.remove(id);

    return removedAssignee != null;
  }


  private Long getNextId() {
    return assignees
        .keySet()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(0L) + 1L;
  }
}
