package de.relran.todo.controller;

import de.relran.todo.model.ToDo;
import de.relran.todo.repository.CommonRepository;
import de.relran.todo.validation.ToDoValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private CommonRepository<ToDo> repository;      //refer to Interface

    @Autowired                                      //inject dependency
    public ToDoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    //find and get all ToDos
    @GetMapping("/todo")                     // .../api/todo
    public ResponseEntity<Iterable<ToDo>> getTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception e) {
        return new ToDoValidationError((e.getMessage()));
    }

}
