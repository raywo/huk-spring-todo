package de.huk.seminars.todoapp.control;

import de.huk.seminars.todoapp.shared.validation.OnCreate;
import de.huk.seminars.todoapp.shared.validation.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

  @NotNull(groups = OnUpdate.class)
  @Null(groups = OnCreate.class)
  private Long id;
  private String title;
  private String dueDate;
  private String password;
  private String rePassword;


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
