// 수동 bean 주입 방식

//package study.crudboard;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import study.crudboard.repository.ArticleMemoryRepository;
//import study.crudboard.service.ArticleService;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public ArticleService articleService(){
//        return new ArticleService(repository());
//    }
//    @Bean
//    public ArticleMemoryRepository repository(){
//        return new ArticleMemoryRepository();
//    }
//}
