package project.todolist.todotask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TodoTaskDto", description = "TodoTaskDto")
public class TodoTaskDto {

    @Schema(name = "id", description = "id", example = "1")
    Long id;

    @Schema(name = "name", description = "name of the task", example = "name")
    String name;

    @Schema(name = "isDone", description = "task is done or not", example = "false")
    Boolean isDone;

    @Schema(name = "createdDate", description = "createdDate", example = "2021-01-01")
    Date createdDate;

    @Schema(name = "lastModifiedDate", description = "lastModifiedDate", example = "2021-01-01")
    Date lastModifiedDate;

    @Schema(name = "todoListName", description = "todoListName", example = "todoListName")
    String todoListName;
}
