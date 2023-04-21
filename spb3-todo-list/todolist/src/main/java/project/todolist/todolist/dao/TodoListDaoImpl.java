package project.todolist.todolist.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.repository.TodoListRepository;

@Repository
@RequiredArgsConstructor
public class TodoListDaoImpl implements TodoListDao {

    final TodoListRepository todoListRepository;

    @Override
    public TodoList saveTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList getTodoList(Long id) {
        return todoListRepository.findById(id).orElse(null);
    }

    @Override
    public Page<TodoList> getTodoLists(PageRequest pageRequest) {
        return todoListRepository.findAll(pageRequest);
    }
}
