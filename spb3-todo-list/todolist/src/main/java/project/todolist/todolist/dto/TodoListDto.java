package project.todolist.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.todolist.todotask.dto.TodoTaskDto;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "TodoList Response")
public class TodoListDto {

    @Schema(description = "TodoList ID", example = "1" , requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;

    @Schema(description = "TodoList Title", example = "TodoList Title" , requiredMode = Schema.RequiredMode.REQUIRED)
    String title;

    @Schema(description = "TodoList Description", example = "TodoList Description", requiredMode = Schema.RequiredMode.REQUIRED)
    String description;

    @Schema(description = "TodoList Progress", example = "0.0" , requiredMode = Schema.RequiredMode.REQUIRED)
    Double progress;

    @Schema(description = "TodoList Archived", example = "false" , requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean isArchived;

    @Schema(description = "TodoList Created Date", example = "2021-08-01T00:00:00.000Z" , pattern = "yyyy-MM-ddThh:mm:ss:sssZ" , requiredMode = Schema.RequiredMode.REQUIRED)
    Date createdDate;

    @Schema(description = "TodoList Last Modified Date", example = "2021-08-01T00:00:00.000Z", pattern = "yyyy-MM-ddThh:mm:ss:sssZ" , requiredMode = Schema.RequiredMode.REQUIRED)
    Date lastModifiedDate;

    @Schema(description = "TodoList Tasks" , requiredMode = Schema.RequiredMode.REQUIRED)
    List<TodoTaskDto> tasks;
}
