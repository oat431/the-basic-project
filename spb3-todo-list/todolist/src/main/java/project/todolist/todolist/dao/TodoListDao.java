package project.todolist.todolist.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import project.todolist.todolist.entity.TodoList;

public interface TodoListDao {
    TodoList saveTodoList(TodoList todoList);

    TodoList getTodoList(Long id);

    Page<TodoList> getTodoLists(PageRequest pageRequest);
}
