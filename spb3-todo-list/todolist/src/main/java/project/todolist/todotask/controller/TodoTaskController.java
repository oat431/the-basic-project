package project.todolist.todotask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.service.TodoListService;
import project.todolist.todotask.entity.TodoTaskRequest;
import project.todolist.todotask.service.TodoTaskService;
import project.todolist.utils.AppMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@Tag(name = "TodoTask API", description = "TodoTask API")
public class TodoTaskController {
    final TodoListService todoListService;
    final TodoTaskService todoTaskService;

    @Operation(summary = "Create Task in TodoList")
    @PostMapping("/create")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create Task in TodoList")
    })
    public ResponseEntity<?> createTodoTask(
            @RequestParam("_todo_id") Long todoId,
            @RequestBody TodoTaskRequest todoTaskRequest
    ) {
        todoTaskService.createTodoTask(todoId, todoTaskRequest);
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoListDto(
                        todoListService.getTodoList(todoId)
                )
        );
    }
}
