package de.relran.todo.controller;

import de.relran.todo.model.ToDo;
import de.relran.todo.repository.CommonRepository;
import de.relran.todo.validation.ToDoValidationError;
import de.relran.todo.validation.ToDoValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    //this method allows to create and update
    @RequestMapping(value = "/todo", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createTodo(@Valid @RequestBody ToDo toDo, Errors errors) {
        if (errors.hasErrors()){
        return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
       ToDo result = repository.save(toDo);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        ToDo dataBaseToDo = repository.findById(id);
        if (dataBaseToDo == null){
            return ResponseEntity.notFound().build();
        }
        repository.delete(dataBaseToDo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<?> deleteToDo(@RequestBody ToDo toDo){
        ToDo dataBaseToDo = repository.findById(toDo.getId());
        if (dataBaseToDo == null){
            return ResponseEntity.notFound().build();
        }
        repository.delete(dataBaseToDo);
        return ResponseEntity.noContent().build();

    }
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception e) {
        return new ToDoValidationError((e.getMessage()));
    }

}
