package project.todolist.todotask.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.todolist.todolist.dto.TodoListDto;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.service.TodoListService;
import project.todolist.todotask.dto.TodoTaskDto;
import project.todolist.todotask.entity.TodoTaskRequest;
import project.todolist.todotask.service.TodoTaskService;
import project.todolist.utils.AppMapper;

@Controller
@RequiredArgsConstructor
public class TodoTaskGraphQL {
    final TodoTaskService todoTaskService;
    final TodoListService todoListService;

    @MutationMapping
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
    public TodoTaskDto updateTodoTask(
            @Argument Long id,
            @Argument TodoTaskRequest task
    ) {
        todoTaskService.updateTodoTask(id, task);
        return AppMapper.INSTANCE.getTodoTaskDto(
                todoTaskService.getTodoTask(id)
        );
    }

    @MutationMapping
    public TodoTaskDto updateTodoTaskStatus(@Argument Long id) {
        return AppMapper.INSTANCE.getTodoTaskDto(
                todoTaskService.updateStatus(id)
        );
    }

    @MutationMapping
    public TodoListDto deleteTodoTask(@Argument Long id) {
        TodoList todoList = todoTaskService.getTodoTask(id).getBelongTo();
        todoTaskService.deleteTodoTask(id);
        todoListService.updateProgress(todoList);
        return AppMapper.INSTANCE.getTodoListDto(todoList);
    }
}
