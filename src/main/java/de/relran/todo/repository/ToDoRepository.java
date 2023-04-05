package de.relran.todo.repository;

import de.relran.todo.model.ToDo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class ToDoRepository implements CommonRepository<ToDo> {

    private Map<String, ToDo> toDos = new HashMap<>();

    @Override
    public ToDo save(ToDo entity) {
        ToDo result = toDos.get(entity.getId());                //search todo in DB by method get
        if (result != null){
            // means there is entity in toDos -> update entry then update
            result.setModified(LocalDateTime.now());            //update time
            result.setDescription(entity.getDescription());     //update description
            result.setCompleted(entity.isCompleted());          //update status
            entity = result;                                    //put result into entity
        }
        toDos.put(entity.getId(), entity);                      //if no entity -> put into Map NEW todos entity by ID, or update todos
        return toDos.get(entity.getId());
    }

    @Override
    public Iterable<ToDo> saveAll(Collection<ToDo> entities) {
        return null;
    }

    @Override
    public void delete(ToDo entity) {

    }

    @Override
    public ToDo findById(String id) {
        return null;
    }

    @Override
    public Iterable<ToDo> findAll() {
        return null;
    }
}
