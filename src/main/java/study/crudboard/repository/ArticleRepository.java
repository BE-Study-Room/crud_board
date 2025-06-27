package study.crudboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import study.crudboard.entity.Article;

public interface ArticleRepository
        extends JpaRepository<Article, Integer>, ArticleRepositoryCustom {

    @EntityGraph(attributePaths = "author")
    Page<Article> findAll(Pageable pageable);
}
