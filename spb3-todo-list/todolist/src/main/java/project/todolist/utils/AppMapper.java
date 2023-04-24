package project.todolist.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import project.todolist.todolist.dto.TodoListDto;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todotask.dto.TodoTaskDto;
import project.todolist.todotask.entity.TodoTask;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface AppMapper {
    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    TodoListDto getTodoListDto(TodoList todoList);
    List<TodoListDto> getTodoListDto(List<TodoList> todoList);

    @Mapping(target = "todoListName", expression = "java(todoTask.getBelongTo().getTitle())")
    TodoTaskDto getTodoTaskDto(TodoTask todoTask);
    List<TodoTaskDto> getTodoTaskDto(List<TodoTask> todoTask);
}
