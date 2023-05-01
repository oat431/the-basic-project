package project.todolist.todotask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/")
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

    @Operation(summary = "Get Task in TodoList")
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get Task in TodoList")
    })
    public ResponseEntity<?> getTodoTask(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoTaskDto(
                        todoTaskService.getTodoTask(id)
                )
        );
    }

    @Operation(summary = "Update Task in TodoList")
    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update Task in TodoList")
    })
    public ResponseEntity<?> updateTodoTask(
            @PathVariable("id") Long id,
            @RequestBody TodoTaskRequest todoTaskRequest
    ) {
        todoTaskService.updateTodoTask(id, todoTaskRequest);
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoTaskDto(
                        todoTaskService.getTodoTask(id)
                )
        );
    }

    @Operation(summary = "update task status in TodoList")
    @PatchMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "update task status in TodoList")
    })
    public ResponseEntity<?> updateTodoTaskStatus(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoTaskDto(
                        todoTaskService.updateStatus(id)
                )
        );
    }

    @Operation(summary = "Delete Task in TodoList")
    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Delete Task in TodoList")
    })
    public ResponseEntity<?> deleteTodoTask(
            @PathVariable("id") Long id
    ) {
        todoTaskService.deleteTodoTask(id);
        return ResponseEntity.ok("Delete Successfully");
    }
}
