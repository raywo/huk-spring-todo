package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.boundary.AssigneeDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AssigneesService {

  private final Map<Long, AssigneeDto> assignees = new HashMap<>();


  @PostConstruct
  public void initData() {
    assignees.put(1L, new AssigneeDto(1L, "Ray"));
    assignees.put(2L, new AssigneeDto(2L, "Andi"));
  }


  public Collection<AssigneeDto> allAssignees() {
    return assignees.values();
  }


  public Optional<AssigneeDto> singleTodo(Long id) {
    return Optional.of(assignees.get(id));
  }


  public AssigneeDto createAssignee(AssigneeDto newAssignee) {
    // TODO Was passiert, wenn das Todo schon existiert?
    Long newId = getNextId();

    newAssignee.setId(newId);
    assignees.put(newId, newAssignee);

    return newAssignee;
  }


  public AssigneeDto updateAssignee(AssigneeDto updatedAssignee) {
    // TODO Was passiert, wenn das Todo noch nicht existiert?
    assignees.put(updatedAssignee.getId(), updatedAssignee);

    return updatedAssignee;
  }


  public boolean delete(Long id) {
    AssigneeDto removedAssignee = assignees.remove(id);

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
