package project.todolist.todotask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.todolist.todolist.entity.TodoList;
import project.todolist.todotask.entity.TodoTask;

public interface TodoTaskRepository extends JpaRepository<TodoTask, Long>, JpaSpecificationExecutor<TodoTask> {
    Page<TodoTask> findAllByBelongTo(TodoList belongTo, Pageable pageable);
}
