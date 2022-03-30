package de.huk.seminars.todoapp.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class TodoDto {

  private Long id;
  private String title;
  private String dueDate;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TodoDto)) return false;
    TodoDto todoDto = (TodoDto) o;
    return Objects.equals(getId(), todoDto.getId());
  }


  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
