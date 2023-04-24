package project.todolist.todotask.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todotask.entity.TodoTask;
import project.todolist.todotask.repository.TodoTaskRepository;


@Repository
@RequiredArgsConstructor
public class TodoTaskDaoImpl implements TodoTaskDao {
    final TodoTaskRepository todoTaskRepository;

    @Override
    public TodoTask saveTodoTask(TodoTask todoTask) {
        return todoTaskRepository.save(todoTask);
    }

    @Override
    public TodoTask getTodoTask(Long id) {
        return todoTaskRepository.findById(id).orElse(null);
    }

    @Override
    public TodoTask deleteTask(TodoTask todoTask) {
        todoTaskRepository.delete(todoTask);
        return todoTask;
    }

    @Override
    public Page<TodoTask> getTodoTasksByTodoList(TodoList todoList, PageRequest pageRequest) {
        return todoTaskRepository.findAllByBelongTo(todoList, pageRequest);
    }
}
