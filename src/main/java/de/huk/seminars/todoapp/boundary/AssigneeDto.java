package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.shared.validation.OnCreate;
import de.huk.seminars.todoapp.shared.validation.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssigneeDto {

  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private Long id;

  @NotBlank
  @Size(min = 2)
  private String name;
}
