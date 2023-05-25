package project.todolist.todotask.controller;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import project.todolist.todolist.dto.TodoListDto;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.service.TodoListService;
import project.todolist.todotask.dto.TodoTaskDto;
import project.todolist.todotask.entity.TodoTask;
import project.todolist.todotask.entity.TodoTaskRequest;
import project.todolist.todotask.service.TodoTaskService;
import project.todolist.utils.AppMapper;

@Controller
@RequiredArgsConstructor
public class TodoTaskGraphQL {
    final TodoTaskService todoTaskService;
    final TodoListService todoListService;
    final CacheManager cacheManager;

    @MutationMapping
    @CacheEvict(value = "todoListId", key = "#todoId")
    public TodoListDto createTodoTask(
            @Argument Long todoId,
            @Argument TodoTaskRequest task
    ) {
        todoTaskService.createTodoTask(todoId, task);
        return AppMapper.INSTANCE.getTodoListDto(
                todoListService.getTodoList(todoId)
        );
    }

    @QueryMapping
    public TodoTaskDto getTodoTaskById(@Argument Long id) {
        return AppMapper.INSTANCE.getTodoTaskDto(
                todoTaskService.getTodoTask(id)
        );
    }

    @MutationMapping
    public TodoListDto updateTodoTask(
            @Argument Long id,
            @Argument TodoTaskRequest task
    ) {
        todoTaskService.updateTodoTask(id, task);
        TodoList result = todoTaskService.getTodoTask(id).getBelongTo();
        Long todoId = result.getId();
        TodoListDto todoListDto = AppMapper.INSTANCE.getTodoListDto(result);
        cacheManager.getCache("todoListId").put(todoId, todoListDto);
        return todoListDto;
    }

    @MutationMapping
    public TodoListDto updateTodoTaskStatus(@Argument Long id) {
        todoTaskService.updateStatus(id);
        TodoList result = todoTaskService.getTodoTask(id).getBelongTo();
        Long todoId = result.getId();
        TodoListDto todoListDto = AppMapper.INSTANCE.getTodoListDto(result);
        cacheManager.getCache("todoListId").put(todoId, todoListDto);
        return todoListDto;
    }

    @MutationMapping
    public TodoListDto deleteTodoTask(@Argument Long id) {
        TodoList todoList = todoTaskService.getTodoTask(id).getBelongTo();
        todoTaskService.deleteTodoTask(id);
        TodoList result = todoListService.updateProgress(todoList);
        Long todoId = result.getId();
        TodoListDto todoListDto = AppMapper.INSTANCE.getTodoListDto(result);
        cacheManager.getCache("todoListId").put(todoId, todoListDto);
        return todoListDto;
    }
}
