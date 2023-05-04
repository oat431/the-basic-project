package project.todolist.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import project.todolist.todolist.dto.PageTodoListDto;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.service.TodoListService;
import project.todolist.utils.AppMapper;

@Controller
@RequiredArgsConstructor
public class TodoListGraphQL {
    final TodoListService todoListService;

    @QueryMapping
    public PageTodoListDto getTodoList(@Argument Integer size, @Argument Integer page) {
        size = size == null ? 10 : size;
        page = page == null ? 1 : page - 1;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TodoList> todoList = todoListService.getTodoLists(pageRequest);
        return PageTodoListDto.builder()
                .page(page)
                .size(size)
                .totalPage(todoList.getTotalPages())
                .totalElements(todoList.getTotalElements())
                .content(AppMapper.INSTANCE.getTodoListDto(todoList.getContent()))
                .build();
    }
}
