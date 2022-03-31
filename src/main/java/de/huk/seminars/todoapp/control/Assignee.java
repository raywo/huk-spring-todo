package de.huk.seminars.todoapp.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignee {

  private Long id;
  private String name;
}
