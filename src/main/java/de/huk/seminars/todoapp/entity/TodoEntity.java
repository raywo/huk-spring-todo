package de.huk.seminars.todoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
public class TodoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @NotBlank
  String title;

  @Column(name = "due_date")
  String dueDate;

  String password;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TodoEntity)) return false;
    TodoEntity that = (TodoEntity) o;
    return Objects.equals(getId(), that.getId());
  }


  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
