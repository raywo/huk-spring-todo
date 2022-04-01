package de.huk.seminars.todoapp.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AssigneeRepository extends CrudRepository<AssigneeEntity, Long> {

  @Override
  Collection<AssigneeEntity> findAll();
}
