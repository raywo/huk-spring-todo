package de.huk.seminars.todoapp;

import de.huk.seminars.todoapp.control.Todo;
import de.huk.seminars.todoapp.control.TodosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

@Configuration
@Profile("development")
@Log4j2
public class OnStartupListener {

  private final TodosService todosService;


  public OnStartupListener(TodosService todosService) {
    this.todosService = todosService;
  }


  @EventListener(ApplicationReadyEvent.class)
  public void initDatabase() {
    log.info("App ist gestartet, wir schauen ob wir die DB füllen müssen.");

    if (todosService.count() == 0) {
      Todo todo = new Todo();
      todo.setTitle("erstes Todo");

      todosService.createTodo(todo);
    }
  }
}
