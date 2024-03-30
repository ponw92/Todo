package com.example.todo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString @Builder
@Table(name="todo")
@SequenceGenerator(name="todo_seq", sequenceName = "todo_seq", initialValue = 1, allocationSize = 1)
public class TodoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq")
    private int id;
    private String title;
    private String content;
    private int level;

}
