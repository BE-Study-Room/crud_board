package study.crudboard.repository;
import study.crudboard.entity.Article;
import java.util.*;

public interface ArticleRepository {
    void create(int id, String title, String body, int hit, String nowDate, String author);
    ArrayList<Article> read(int id);
    void update(int id, Article article);
    void delete(int id);
    Optional<Article> findId(int id);
}
