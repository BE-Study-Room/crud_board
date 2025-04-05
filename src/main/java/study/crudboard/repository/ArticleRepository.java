package study.crudboard.repository;

import org.springframework.stereotype.Repository;
import study.crudboard.entity.Article;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    private final List<Article> articles = new ArrayList<>();


    public void create(Article article) {
        articles.add(article);
    }

    public Article findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public List<Article> findAll() {
        return new ArrayList<>(articles); // 리스트 복사본 반환
    }

    public void update(Article updatedArticle) {
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId() == updatedArticle.getId()) {
                articles.set(i, updatedArticle);
                return;
            }
        }
    }

    public void delete(int id) {
        articles.removeIf(article -> article.getId() == id);
    }
}
