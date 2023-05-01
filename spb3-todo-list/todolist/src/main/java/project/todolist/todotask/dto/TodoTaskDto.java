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

    @Schema(name = "id", description = "id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @Schema(name = "name", description = "name of the task", example = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    String name;

    @Schema(name = "isDone", description = "task is done or not", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean isDone;

    @Schema(name = "createdDate", description = "createdDate", example = "2023-03-01T09:20:37.054Z", pattern = "yyyy-MM-ddThh:mm:ss:sssZ", requiredMode = Schema.RequiredMode.REQUIRED)
    Date createdDate;

    @Schema(name = "lastModifiedDate", description = "lastModifiedDate", example = "2023-03-01T09:20:37.054Z", pattern="yyyy-MM-ddThh:mm:ss:sssZ" , requiredMode = Schema.RequiredMode.REQUIRED)
    Date lastModifiedDate;

    @Schema(name = "todoListName", description = "todoListName", example = "todoListName", requiredMode = Schema.RequiredMode.REQUIRED)
    String todoListName;
}
