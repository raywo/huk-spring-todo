package de.huk.seminars.todoapp.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private Long id;

  @NotBlank
  @Size(min = 5)
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
