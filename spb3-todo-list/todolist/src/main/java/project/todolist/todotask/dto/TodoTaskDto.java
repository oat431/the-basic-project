package project.todolist.todotask.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoTaskDto {
    Long id;
    String name;
    Boolean isDone;
    Date createdDate;
    Date lastModifiedDate;
    String todoListName;
}
