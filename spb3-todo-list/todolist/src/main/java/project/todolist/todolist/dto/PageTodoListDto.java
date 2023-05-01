package project.todolist.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PageTodoListDto", description = "Response of Todo list as pagination")
public class PageTodoListDto {

    @Schema(name = "page", description = "page number", example = "1", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer page;

    @Schema(name = "size", description = "size of the page", example = "10", defaultValue = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer size;

    @Schema(name = "totalPage", description = "total page", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer totalPage;

    @Schema(name = "totalElements", description = "total elements", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer totalElements;

    @Schema(name = "content", description = "content", requiredMode = Schema.RequiredMode.REQUIRED)
    List<TodoListDto> content;
}
