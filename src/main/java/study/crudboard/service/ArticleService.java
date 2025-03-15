package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import study.crudboard.entity.Article;
import study.crudboard.repository.ArticleRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor // final 키워드의 생성자 자동 생성
public class ArticleService {
    private final ArticleRepository repository;

    public void create(Article article){
        if(article.getTitle() == null | article.getBody() == null){
            throw new IllegalArgumentException("제목과 내용 작성은 필수입니다.");
        }
        repository.create(article.getId(), article.getTitle(), article.getBody(), article.getHit(), article.getNowDate(), article.getAuthor());
    }
    public void read(String id){
        Article articleFine = repository.findId(Integer.parseInt(id))
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        repository.read(articleFine.getId());
    }

    public void update(Article article){
        Article articleFine = repository.findId(article.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        repository.update(articleFine.getId(), article);
    }

    public void delete(String id){
        repository.delete(Integer.parseInt(id));
    }
    public Article findId(int id){
        Article articleFind = repository.findId(id).orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        return articleFind;
    }
}
