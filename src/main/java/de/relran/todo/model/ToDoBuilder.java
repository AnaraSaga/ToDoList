package de.relran.todo.model;

public class ToDoBuilder {

    //single tone - class
    private final static ToDoBuilder instance = new ToDoBuilder();      //create object of this class

    private String id = null;
    private String description = " ";

    private ToDoBuilder() {

    }

    public static ToDoBuilder create() {
        return instance;
    }

    public ToDoBuilder withDescription(String description) {
        this.description = description;
        return instance;
    }

    public ToDoBuilder withId(String id) {
        this.id = id;
        return instance;
    }


    public ToDo build() {
        ToDo result = new ToDo(this.description);
        if (id != null) {
            result.setId(id);
        }
        return result;
    }
}
