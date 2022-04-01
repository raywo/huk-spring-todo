package de.huk.seminars.todoapp.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

  @Override
  Collection<TodoEntity> findAll();
}
