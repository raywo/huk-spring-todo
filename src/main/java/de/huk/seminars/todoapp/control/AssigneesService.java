package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.entity.AssigneeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssigneesService {

  private final AssigneeRepository repository;
  private final AssigneeEntityMapper mapper;


  public AssigneesService(AssigneeRepository repository, AssigneeEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }


//  @PostConstruct
//  public void initData() {
//    assignees.put(1L, new Assignee(1L, "Ray"));
//    assignees.put(2L, new Assignee(2L, "Andi"));
//  }


  public Collection<Assignee> allAssignees() {
    return repository.findAll()
        .stream()
        .map(mapper::map)
        .collect(Collectors.toList());
  }


  public Optional<Assignee> singleTodo(Long id) {
    return repository.findById(id)
        .map(mapper::map);
  }


  public Assignee createAssignee(Assignee newAssignee) {
    return mapper.map(repository.save(mapper.map(newAssignee)));
  }


  public Assignee updateAssignee(Assignee updatedAssignee) throws NotFoundException {
    if (!repository.existsById(updatedAssignee.getId())) {
      throw new NotFoundException("Das Todo konnte nicht gefunden werden.");
    }

    return mapper.map(repository.save(mapper.map(updatedAssignee)));
  }


  public void delete(Long id) throws NotFoundException {
    if (!repository.existsById(id)) {
      throw new NotFoundException("Das Todo konnte nicht gefunden werden.");
    }

    repository.deleteById(id);
  }
}
