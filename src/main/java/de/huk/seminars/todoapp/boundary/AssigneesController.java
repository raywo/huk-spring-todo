package de.huk.seminars.todoapp.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/assignees")
public class AssigneesController {

  private Map<Long, AssigneeDto> assignees = new HashMap<>();


  @PostConstruct
  public void initData() {
    assignees.put(1L, new AssigneeDto(1L, "Ray"));
    assignees.put(2L, new AssigneeDto(2L, "Andi"));
  }


  @GetMapping
  public Collection<AssigneeDto> allAssignees() {
    return assignees.values();
  }


  @GetMapping("/{id}")
  public ResponseEntity<AssigneeDto> singleAssignee(@PathVariable Long id) {
    AssigneeDto assignee = assignees.get(id);

    if (assignee == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(assignee);
    }
  }


  @PostMapping
  public ResponseEntity<AssigneeDto> createAssignee(@RequestBody AssigneeDto newAssignee) {
    if (newAssignee.getId() != null) {
      return ResponseEntity.badRequest().build();
    }

    Long newId = assignees
        .keySet()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(0L) + 1L;

    newAssignee.setId(newId);
    assignees.put(newId, newAssignee);

    return ResponseEntity.status(HttpStatus.CREATED).body(newAssignee);
  }


  @PutMapping("/{id}")
  public ResponseEntity<AssigneeDto> updateAssignee(@PathVariable Long id,
                                                    @RequestBody AssigneeDto updatedAssignee) {
    if (!id.equals(updatedAssignee.getId())) {
      return ResponseEntity.badRequest().build();
    }

    if (updatedAssignee.getId() == null) {
      return ResponseEntity.badRequest().build();
    }

    assignees.put(id, updatedAssignee);

    return ResponseEntity.ok(updatedAssignee);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAssignee(@PathVariable Long id) {
    assignees.remove(id);
  }
}
