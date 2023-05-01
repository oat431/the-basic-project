package project.todolist.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.todolist.todolist.dto.PageTodoListDto;
import project.todolist.todolist.dto.TodoListDto;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.entity.TodoListRequest;
import project.todolist.todolist.service.TodoListService;
import project.todolist.utils.AppMapper;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/todolist")
@Tag(name = "TodoList API", description = "TodoList API")
public class TodoListController {
    final TodoListService todoListService;

    @Operation(summary = "Create TodoList")
    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "TodoList Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListDto.class)
                    )
            ),
    })
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequest body) {
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoListDto(
                        todoListService.createTodoList(body)
                )
        );
    }

    @Operation(summary = "Get TodoList as pagination")
    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "TodoList Retrieved",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageTodoListDto.class)
                    )
            ),
    })
    public ResponseEntity<?> getTodoList(
            @RequestParam(value = "_size", required = false) Integer size,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        size = size == null ? 10 : size;
        page = page == null ? 0 : page;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TodoList> todoList = todoListService.getTodoLists(pageRequest);
        Map result = new HashMap();
        HttpHeaders headers = new HttpHeaders();
        result.put("page", page);
        result.put("size", size);
        result.put("totalPage", todoList.getTotalPages());
        result.put("totalElements", todoList.getTotalElements());
        result.put("content", AppMapper.INSTANCE.getTodoListDto(todoList.getContent()));
        return new ResponseEntity<>(result, headers, org.springframework.http.HttpStatus.OK);
    }

    @Operation(summary = "Get TodoList By ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "TodoList Retrieved",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListDto.class)
                    )
            ),
    })
    public ResponseEntity<?> getTodoListById(@PathVariable Long id) {
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoListDto(
                        todoListService.getTodoList(id)
                )
        );
    }

    @Operation(summary = "Update TodoList")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "TodoList Updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListDto.class)
                    )

            ),
    })
    public ResponseEntity<?> updateTodoList(@PathVariable Long id, @RequestBody TodoListRequest body) {
        return ResponseEntity.ok(
                AppMapper.INSTANCE.getTodoListDto(
                        todoListService.updateTodoList(id, body)
                )
        );
    }

    @Operation(summary = "Delete TodoList")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "TodoList Deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoListDto.class)
                    )
            ),
    })
    public ResponseEntity<?> deleteTodoList(@PathVariable Long id) {
        TodoList arch = todoListService.archiveTodoList(id);
        return ResponseEntity.ok(AppMapper.INSTANCE.getTodoListDto(arch));
    }
}
