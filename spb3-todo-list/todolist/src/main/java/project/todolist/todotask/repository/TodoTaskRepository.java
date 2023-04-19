package project.todolist.todotask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.todolist.todotask.entity.TodoTask;

public interface TodoTaskRepository extends JpaRepository<TodoTask, Long>, JpaSpecificationExecutor<TodoTask> {
}
