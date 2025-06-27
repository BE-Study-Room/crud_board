package study.crudboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.crudboard.entity.Article;

public interface ArticleRepositoryCustom {

    Page<Article> findByTitle(String title, Pageable pageable);

    Page<Article> findByAuthorName(String authorName, Pageable pageable);

    Page<Article> findByTitleAndAuthorName(String title, String authorName, Pageable pageable);
}
