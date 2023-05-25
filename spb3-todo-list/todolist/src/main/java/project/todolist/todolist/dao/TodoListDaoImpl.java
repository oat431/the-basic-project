package project.todolist.todolist.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todolist.repository.TodoListRepository;
import project.todolist.todotask.entity.TodoTask;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TodoListDaoImpl implements TodoListDao {

    final TodoListRepository todoListRepository;

    @Override
    public TodoList saveTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList updateProgress(TodoList todoList) {
        int allTask = todoList.getTasks().size();
        int doneTask = (int) todoList.getTasks().stream().filter(TodoTask::getIsDone).count();
        if(doneTask == 0) {
            todoList.setProgress(0.0);
            return todoListRepository.save(todoList);
        }
        double progress = (double) doneTask / allTask;
        todoList.setProgress(progress * 100);
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList getTodoList(Long id) {
        return todoListRepository.findById(id).orElse(null);
    }

    @Override
    public Page<TodoList> getTodoLists(PageRequest pageRequest) {
        return todoListRepository.findAllByIsArchivedFalseOrderByCreatedDateAsc(pageRequest);
    }
}
