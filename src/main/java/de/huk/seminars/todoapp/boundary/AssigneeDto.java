package de.huk.seminars.todoapp.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssigneeDto {

  private Long id;
  private String name;
}
