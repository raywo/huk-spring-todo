package de.huk.seminars.todoapp.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssigneeDto {

  private Long id;
  private String name;
}
