package project.todolist.todotask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import project.todolist.todotask.entity.TodoTask;
import project.todolist.todotask.entity.TodoTaskRequest;

public interface TodoTaskService {
    TodoTask createTodoTask(Long todoListId, TodoTaskRequest todoTaskRequest);
    TodoTask updateTodoTask(Long id, TodoTaskRequest todoTaskRequest);
    TodoTask getTodoTask(Long id);
    Page<TodoTask> getTodoTasksByTodoList(Long todoListId, PageRequest pageRequest);
    TodoTask deleteTodoTask(Long id);
}
