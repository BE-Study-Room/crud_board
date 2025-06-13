package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Article;
import study.crudboard.exception.BusinessException;
import study.crudboard.exception.ErrorCode;
import study.crudboard.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void create(Article article) {
        validateArticle(article);
        articleRepository.save(article);
    }

    public Article findId(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, "/articles"));
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void update(Article updatedArticle) {
        validateArticle(updatedArticle);
        articleRepository.save(updatedArticle);
    }

    public void delete(int id) {
        articleRepository.deleteById(id);
    }

    public Page<Article> getPagedArticles(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return articleRepository.findAll(pageable);
    }

    private void validateArticle(Article article) {
        if (article.getTitle() == null || article.getTitle().isBlank()) {
            throw new BusinessException(ErrorCode.ARTICLE_TITLE_REQUIRED, "/articles/form");
        }
        if (article.getBody() == null || article.getBody().isBlank()) {
            throw new BusinessException(ErrorCode.ARTICLE_BODY_REQUIRED, "/articles/form");
        }
        if (article.getTitle().length() > 100) {
            throw new BusinessException(ErrorCode.ARTICLE_TITLE_TOO_LONG, "/articles/form");
        }
    }
}
