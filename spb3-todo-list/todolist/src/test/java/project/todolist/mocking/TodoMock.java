package project.todolist.mocking;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.todolist.todolist.entity.TodoListRequest;
import project.todolist.todolist.service.TodoListService;
import project.todolist.todotask.entity.TodoTaskRequest;
import project.todolist.todotask.service.TodoTaskService;

import java.util.Random;

@SpringBootTest
public class TodoMock {
    @Autowired
    TodoListService todoListService;

    @Autowired
    TodoTaskService todoTaskService;

    private final String[] titles = {
            "Make a cup of coffee",
            "Make a cup of tea",
            "Make a cup of chocolate",
            "Make a cup of milk",
            "Make a cup of juice",
            "Make a cup of water",
            "Make a cup of wine",
            "Make a cup of beer",
            "Make a cup of vodka",
            "Make a cup of whiskey"
    };

    private final String[] description = {
            "Make a cup of coffee by using coffee powder",
            "Make a cup of tea by using tea powder",
            "Make a cup of chocolate by using chocolate powder",
            "Make a cup of milk by using milk powder",
            "Make a cup of juice by using juice powder",
            "Make a cup of water by using water powder",
            "Make a cup of wine by using wine powder",
            "Make a cup of beer by using beer powder",
            "Make a cup of vodka by using vodka powder",
            "Make a cup of whiskey by using whiskey powder"
    };

    @Test
    void mock10TodoList() {
        for(int i = 0; i < 10; i++) {
            todoListService.createTodoList(
                    TodoListRequest.builder()
                            .title(titles[i])
                            .description(description[i])
                            .build()
            );
            for(int j=0;j< new Random().nextInt(3) + 1 ; j ++){
                String taskName = RandomStringUtils.randomAlphabetic(10);
                todoTaskService.createTodoTask(
                        i + 1L,
                        TodoTaskRequest.builder().name(taskName).build()
                );
            }
        }
    }
}
