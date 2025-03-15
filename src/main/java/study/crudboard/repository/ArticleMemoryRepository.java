package study.crudboard.repository;
import study.crudboard.entity.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleMemoryRepository implements ArticleRepository{
    Map<Integer,Article> articleList = new HashMap<>();

    @Override
    public void create(int id, String title, String body, int hit,  String nowDate, String author) {
        Article article = new Article(id, title, body, hit, nowDate, author);
        articleList.put(article.getId(), article);
    }

    @Override
    public ArrayList<Article> read(int id) {
        if(articleList.isEmpty()){
            System.out.println("게시글이 존재하지 않습니다.");
            return new ArrayList<>();
        }

        if(id == 0){
            return new ArrayList<>(articleList.values());
        } else {
            ArrayList<Article> filter = articleList.values().stream()
                    .filter(article -> article.getId() == id)
                    .collect(Collectors.toCollection(ArrayList::new));

            if (filter.isEmpty()){
                System.out.println("일치하는 게시글이 없습니다.");
            }
            return filter;
        }
    }

    @Override
    public void update(int id, Article article) {
        Article updateArticle = articleList.get(id);
        updateArticle.setTitle(article.getTitle());
        updateArticle.setBody(article.getBody());

        articleList.put(updateArticle.getId(), article);
    }

    @Override
    public void delete(int id) {
        articleList.remove(id);
    }

    @Override
    public void detail(int id) {

    }

    @Override
    public Optional<Article> findId(int id){
        if(articleList.containsKey(id)){
            return Optional.of(articleList.get(id));
        }
        return Optional.empty();
    }
}
