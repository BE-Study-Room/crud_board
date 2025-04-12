package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Article;
import study.crudboard.repository.ArticleRepository;
import study.crudboard.exception.ArticleValidationException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private int nextId = 1;

    public void create(Article article) throws ArticleValidationException {
        validateArticle(article);
        articleRepository.create(article);
    }

    public Article findId(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void update(Article updatedArticle) throws ArticleValidationException {
        validateArticle(updatedArticle);
        articleRepository.update(updatedArticle);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }

    public int generateId() {
        return nextId++;
    }

    private void validateArticle(Article article) throws ArticleValidationException {
        if (article.getTitle() == null || article.getTitle().isBlank()) {
            throw new ArticleValidationException("제목은 필수입니다.");
        }
        if (article.getBody() == null || article.getBody().isBlank()) {
            throw new ArticleValidationException("내용은 필수입니다.");
        }
        if (article.getTitle().length() > 100) {
            throw new ArticleValidationException("제목은 100자 이하이어야 합니다.");
        }
    }
}