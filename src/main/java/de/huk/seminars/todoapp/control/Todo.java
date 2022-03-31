package de.huk.seminars.todoapp.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

  private Long id;
  private String title;
  private String dueDate;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Todo)) return false;
    Todo todo = (Todo) o;
    return Objects.equals(getId(), todo.getId());
  }


  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
