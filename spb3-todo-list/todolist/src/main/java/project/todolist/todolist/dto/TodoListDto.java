package project.todolist.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import project.todolist.todotask.dto.TodoTaskDto;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "TodoList Response")
public class TodoListDto {

    @Schema(description = "TodoList ID", example = "1")
    Long id;

    @Schema(description = "TodoList Title", example = "TodoList Title")
    String title;

    @Schema(description = "TodoList Description", example = "TodoList Description")
    String description;

    @Schema(description = "TodoList Progress", example = "0.0")
    Double progress;

    @Schema(description = "TodoList Archived", example = "false")
    Boolean isArchived;

    @Schema(description = "TodoList Created Date", example = "2021-08-01T00:00:00.000+00:00")
    Date createdDate;

    @Schema(description = "TodoList Last Modified Date", example = "2021-08-01T00:00:00.000+00:00")
    Date lastModifiedDate;

    @Schema(description = "TodoList Tasks")
    List<TodoTaskDto> tasks;
}
