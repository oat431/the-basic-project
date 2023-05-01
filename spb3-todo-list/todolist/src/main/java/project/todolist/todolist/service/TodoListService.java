package project.todolist.todolist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.entity.TodoListRequest;

public interface TodoListService {
    TodoList createTodoList(TodoListRequest todoListRequest);

    TodoList updateTodoList(Long id, TodoListRequest todoListRequest);

    TodoList updateProgress(TodoList todoList);

    TodoList getTodoList(Long id);

    Page<TodoList> getTodoLists(PageRequest pageRequest);

    TodoList archiveTodoList(Long id);
}
