package project.todolist.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Application ready to use");
        log.info("using database from: {}", dbUrl);
        log.info("using redis from: {}", redisHost);
    }
}
