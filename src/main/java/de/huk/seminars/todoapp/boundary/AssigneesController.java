package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.AssigneesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/assignees")
public class AssigneesController {

  final
  AssigneesService assigneesService;


  public AssigneesController(AssigneesService assigneesService) {
    this.assigneesService = assigneesService;
  }


  @GetMapping
  public Collection<AssigneeDto> allAssignees() {
    return assigneesService.allAssignees();
  }


  @GetMapping("/{id}")
  public ResponseEntity<AssigneeDto> singleAssignee(@PathVariable Long id) {
    return assigneesService
        .singleTodo(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @PostMapping
  public ResponseEntity<AssigneeDto> createAssignee(@RequestBody AssigneeDto newAssignee) {
    if (newAssignee.getId() != null) {
      return ResponseEntity.badRequest().build();
    }

    AssigneeDto assignee = assigneesService.createAssignee(newAssignee);

    return ResponseEntity.status(HttpStatus.CREATED).body(assignee);
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

    AssigneeDto assigneeDto = assigneesService.updateAssignee(updatedAssignee);

    return ResponseEntity.ok(assigneeDto);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAssignee(@PathVariable Long id) {
    if (assigneesService.delete(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
