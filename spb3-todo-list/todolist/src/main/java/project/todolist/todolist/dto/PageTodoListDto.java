package project.todolist.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageTodoListDto {
    Integer page;
    Integer size;
    Integer totalPage;
    Integer totalElements;
    List<TodoListDto> content;
}
