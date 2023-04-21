package project.todolist.todotask.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.todolist.todolist.entity.TodoList;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @NotEmpty
    String name;

    @Builder.Default
    Boolean isDone = false;

    @CreatedDate
    Date createdDate;
    @LastModifiedDate
    Date lastModifiedDate;

    @ManyToOne
    TodoList belongTo;
}
