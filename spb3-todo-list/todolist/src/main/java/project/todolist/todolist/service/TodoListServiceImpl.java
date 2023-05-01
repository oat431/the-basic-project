package project.todolist.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import project.todolist.todolist.dao.TodoListDao;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.entity.TodoListRequest;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {
    final TodoListDao todoListDao;

    @Override
    public TodoList createTodoList(TodoListRequest todoListRequest) {
        return todoListDao.saveTodoList(
                TodoList.builder()
                        .title(todoListRequest.getTitle())
                        .description(todoListRequest.getDescription())
                        .build()
        );
    }

    @Override
    public TodoList updateTodoList(Long id, TodoListRequest todoListRequest) {
        TodoList oldTodo = todoListDao.getTodoList(id);
        oldTodo.setTitle(todoListRequest.getTitle());
        oldTodo.setDescription(todoListRequest.getDescription());
        return todoListDao.saveTodoList(oldTodo);
    }

    @Override
    public TodoList updateProgress(TodoList todoList) {
        return todoListDao.updateProgress(todoList);
    }

    @Override
    public TodoList getTodoList(Long id) {
        return todoListDao.getTodoList(id);
    }

    @Override
    public Page<TodoList> getTodoLists(PageRequest pageRequest) {
        return todoListDao.getTodoLists(pageRequest);
    }

    @Override
    public TodoList archiveTodoList(Long id) {
        TodoList todoList = todoListDao.getTodoList(id);
        todoList.setIsArchived(!todoList.getIsArchived());
        return todoListDao.saveTodoList(todoList);
    }
}
