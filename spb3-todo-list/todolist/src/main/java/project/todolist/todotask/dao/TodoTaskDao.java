package project.todolist.todotask.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todotask.entity.TodoTask;

public interface TodoTaskDao {
    TodoTask saveTodoTask(TodoTask todoTask);

    TodoTask getTodoTask(Long id);

    TodoTask deleteTask(TodoTask todoTask);

    Page<TodoTask> getTodoTasksByTodoList(TodoList todoList, PageRequest pageRequest);
}
