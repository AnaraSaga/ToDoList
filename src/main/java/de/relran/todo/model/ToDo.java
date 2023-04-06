package de.relran.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {
    @NotNull
    private String id;
    @NotNull
    @NotBlank

    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH-mm", timezone = "Europe/Zagreb")
    private LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH-mm", timezone = "Europe/Zagreb")
    private LocalDateTime modified;

    private boolean completed;

    public ToDo() {
        LocalDateTime date = LocalDateTime.now();
        this.created = date;
        this.modified = date;
        this.id = UUID.randomUUID().toString();

    }

    public ToDo(String description) {
        this();
        this.description = description;
    }

}
