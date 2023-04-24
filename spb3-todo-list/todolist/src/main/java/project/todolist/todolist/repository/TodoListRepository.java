package project.todolist.todolist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.todolist.todolist.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long>, JpaSpecificationExecutor<TodoList> {
    Page<TodoList> findAllByIsArchivedFalseOrderByCreatedDateAsc(Pageable pageable);
}
