package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.AssigneesService;
import de.huk.seminars.todoapp.shared.validation.OnCreate;
import de.huk.seminars.todoapp.shared.validation.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/assignees")
public class AssigneesController {

  private final AssigneesService assigneesService;
  private final AssigneesMapper mapper;


  public AssigneesController(AssigneesService assigneesService, AssigneesMapper mapper) {
    this.assigneesService = assigneesService;
    this.mapper = mapper;
  }


  @GetMapping
  public Collection<AssigneeDto> allAssignees() {
    return assigneesService.allAssignees()
        .stream()
        .map(mapper::map)
        .collect(Collectors.toList());
  }


  @GetMapping("/{id}")
  public ResponseEntity<AssigneeDto> singleAssignee(@PathVariable Long id) {
    return assigneesService
        .singleTodo(id)
        .map(mapper::map)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @PostMapping
  public ResponseEntity<AssigneeDto> createAssignee(@RequestBody @Validated(OnCreate.class) AssigneeDto newAssignee) {
    if (newAssignee.getId() != null) {
      return ResponseEntity.badRequest().build();
    }

    AssigneeDto assignee = mapper.map(
        assigneesService.createAssignee(mapper.map(newAssignee))
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(assignee);
  }


  @PutMapping("/{id}")
  public ResponseEntity<AssigneeDto> updateAssignee(@PathVariable Long id,
                                                    @RequestBody @Validated(OnUpdate.class) AssigneeDto updatedAssignee) {
    if (!id.equals(updatedAssignee.getId())) {
      return ResponseEntity.badRequest().build();
    }

    if (updatedAssignee.getId() == null) {
      return ResponseEntity.badRequest().build();
    }

    AssigneeDto assigneeDto = mapper.map(
        assigneesService.updateAssignee(mapper.map(updatedAssignee))
    );

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
