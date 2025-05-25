package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Article;
import study.crudboard.exception.ArticleValidationException;
import study.crudboard.repository.ArticleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void create(Article article) throws ArticleValidationException {
        validateArticle(article);
        articleRepository.save(article);
    }

    public Article findId(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 없습니다: " + id));
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void update(Article updatedArticle) throws ArticleValidationException {
        validateArticle(updatedArticle);
        articleRepository.save(updatedArticle);
    }

    public void delete(int id) {
        articleRepository.deleteById(id);
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
