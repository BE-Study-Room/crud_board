package study.crud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.crud.repository.MemoryRepository;
import study.crud.service.BoardService;

@Configuration
public class AppConfig {
    @Bean
    public BoardService boardService() {
        return new BoardService(repository());
    }
    @Bean
    public MemoryRepository repository() {
        return new MemoryRepository();
    }
}
