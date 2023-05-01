package project.todolist.todotask.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.todolist.todolist.dao.TodoListDao;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todotask.dao.TodoTaskDao;
import project.todolist.todotask.entity.TodoTask;
import project.todolist.todotask.entity.TodoTaskRequest;

@Service
@RequiredArgsConstructor
public class TodoTaskServiceImpl implements TodoTaskService {

    final TodoListDao todoListDao;
    final TodoTaskDao todoTaskDao;

    @Override
    @Transactional
    public TodoTask createTodoTask(Long todoListId, TodoTaskRequest todoTaskRequest) {
        TodoList todoList = todoListDao.getTodoList(todoListId);
        TodoTask todoTask = TodoTask.builder()
                .name(todoTaskRequest.getName())
                .build();
        todoTask.setBelongTo(todoList);
        return todoTaskDao.saveTodoTask(todoTask);
    }

    @Override
    public TodoTask updateTodoTask(Long id, TodoTaskRequest todoTaskRequest) {
        TodoTask todoTask = todoTaskDao.getTodoTask(id);
        todoTask.setName(todoTaskRequest.getName());
        return todoTaskDao.saveTodoTask(todoTask);
    }

    @Override
    public TodoTask updateStatus(Long id) {
        TodoTask todoTask = todoTaskDao.getTodoTask(id);
        todoTask.setIsDone(!todoTask.getIsDone());
        return todoTaskDao.saveTodoTask(todoTask);
    }

    @Override
    public TodoTask getTodoTask(Long id) {
        return todoTaskDao.getTodoTask(id);
    }

    @Override
    public Page<TodoTask> getTodoTasksByTodoList(Long todoListId, PageRequest pageRequest) {
        return todoTaskDao.getTodoTasksByTodoList(todoListDao.getTodoList(todoListId), pageRequest);
    }

    @Override
    public TodoTask deleteTodoTask(Long id) {
        return todoTaskDao.deleteTask(todoTaskDao.getTodoTask(id));
    }
}
