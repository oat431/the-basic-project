package project.todolist.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.todolist.todolist.entity.TodoListRequest;
import project.todolist.todolist.service.TodoListService;
import project.todolist.utils.AppMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/todolist")
@Tag(name = "TodoList API", description = "TodoList API")
public class TodoListController {
    final TodoListService todoListService;

    @Operation(summary = "Create TodoList")
    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TodoList Created"),
    })
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequest body) {
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoListDto(
                        todoListService.createTodoList(body)
                )
        );
    }
}
