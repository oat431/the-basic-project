package project.todolist.todotask.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TodoTaskRequest", description = "TodoTaskRequest")
public class TodoTaskRequest {

    @Schema(name = "name", description = "name", example = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    String name;
}
