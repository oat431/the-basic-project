package project.todolist.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import project.todolist.todolist.dto.PageTodoListDto;
import project.todolist.todolist.dto.TodoListDto;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.entity.TodoListRequest;
import project.todolist.todolist.service.TodoListService;
import project.todolist.utils.AppMapper;

@Controller
@RequiredArgsConstructor
public class TodoListGraphQL {
    final TodoListService todoListService;
    final CacheManager cacheManager;

    @QueryMapping
    @Cacheable(value = "todoList", key = "T(java.lang.String).format('%s-%d-%d', #root.method.name, #size, #page)", unless = "#result == null")
    public PageTodoListDto getTodoList(@Argument Integer size, @Argument Integer page) {
        size = size == null ? 10 : size;
        page = page == null ? 1 : page - 1;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TodoList> todoList = todoListService.getTodoLists(pageRequest);
        return PageTodoListDto.builder()
                .page(page)
                .size(size)
                .totalPages(todoList.getTotalPages())
                .totalElements(todoList.getTotalElements())
                .content(AppMapper.INSTANCE.getTodoListDto(todoList.getContent()))
                .build();
    }

    @QueryMapping
    @Cacheable(value = "todoListId", key = "#id",unless = "#result == null")
    public TodoListDto getTodoListById(@Argument Long id) {
        return AppMapper.INSTANCE.getTodoListDto(todoListService.getTodoList(id));
    }

    @MutationMapping
    @CacheEvict(value = "todoList", allEntries = true)
    public TodoListDto createTodoList(@Argument TodoListRequest body) {
        return AppMapper.INSTANCE.getTodoListDto(
            todoListService.createTodoList(body)
        );
    } 

    @MutationMapping
    @CachePut(value = "todoListId", key = "#id")
    public TodoListDto updateTodoList(@Argument Long id, @Argument TodoListRequest body) {
        return AppMapper.INSTANCE.getTodoListDto(
            todoListService.updateTodoList(id, body)
        );
    }

    @MutationMapping
    @CacheEvict(value = "todoList", allEntries = true)
    public TodoListDto deleteTodoList(@Argument Long id) {
        TodoList arch = todoListService.archiveTodoList(id);
        cacheManager.getCache("todoListId").evict(id);
        return AppMapper.INSTANCE.getTodoListDto(arch);
    }
}
