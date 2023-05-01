package project.todolist.todolist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TodoListRequest", description = "TodoListRequest")
public class TodoListRequest {
    @Schema(name = "title", description = "title", example = "title", requiredMode = Schema.RequiredMode.REQUIRED)
    String title;

    @Schema(name = "description", description = "description", example = "description", requiredMode = Schema.RequiredMode.REQUIRED)
    String description;
}
