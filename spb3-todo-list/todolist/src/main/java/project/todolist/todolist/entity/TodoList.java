package project.todolist.todolist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.todolist.todotask.entity.TodoTask;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "TodoList Entity")
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @NotEmpty
    String title;
    String description;

    @Builder.Default
    Double progress = 0.0;

    @Builder.Default
    Boolean isArchived = false;

    @CreatedDate
    Date createdDate;

    @LastModifiedDate
    Date lastModifiedDate;

    @OneToMany(mappedBy = "belongTo", cascade = CascadeType.ALL)
    List<TodoTask> tasks;
}
