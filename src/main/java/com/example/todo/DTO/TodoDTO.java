package com.example.todo.DTO;

import com.example.todo.Entity.BaseEntity;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TodoDTO {

    private int id;
    private String title;
    private String content;
    private int level;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
