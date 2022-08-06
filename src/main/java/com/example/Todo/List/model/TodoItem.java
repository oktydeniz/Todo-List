package com.example.Todo.List.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Table(name = "todo_name")
@AllArgsConstructor
@NoArgsConstructor
public class TodoItem {

     public TodoItem(String desc) {

        this.desc = desc;
        this.complete = false;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Description is required") // validation
    private String desc;

    @Getter
    @Setter
    private Boolean complete;

    @Getter
    @Setter
    private Instant createdDate;

    @Getter
    @Setter
    private Instant modifiedDate;

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", complete=" + complete +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
