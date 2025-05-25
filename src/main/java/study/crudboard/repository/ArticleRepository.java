package study.crudboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.crudboard.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
