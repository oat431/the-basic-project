package project.todolist.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.todolist.todolist.dto.TodoListDto;
import project.todolist.todolist.entity.TodoList;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface AppMapper {
    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    TodoListDto getTodoListDto(TodoList todoList);
    List<TodoListDto> getTodoListDto(List<TodoList> todoList);
}
