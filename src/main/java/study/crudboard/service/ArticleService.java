package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Article;
import study.crudboard.repository.ArticleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private int nextId = 1;

    public void create(Article article) {
        articleRepository.create(article);
    }

    public Article findId(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void update(Article updatedArticle) {
        articleRepository.update(updatedArticle);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }

    public int generateId() {
        return nextId++;
    }
}
