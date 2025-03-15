package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import study.crudboard.entity.Article;
import study.crudboard.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor // final 키워드의 생성자 자동 생성
public class ArticleService {
    private final ArticleRepository repository;

    public void create(Article article){
        if(article.getTitle() == null | article.getBody() == null){
            throw new IllegalArgumentException("제목과 내용 작성은 필수입니다.");
        }
        repository.create(article.getId(), article.getTitle(), article.getBody(), article.getHit(), article.getNowDate(), article.getAuthor());
    }
    public void read(int id){
        Optional<Article> optionalArticle = repository.findId(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            System.out.println("게시글 정보:");
            System.out.println("ID: " + article.getId());
            System.out.println("제목: " + article.getTitle());
            System.out.println("내용: " + article.getBody());
            System.out.println("조회수: " + article.getHit());
            System.out.println("작성일: " + article.getNowDate());
            System.out.println("작성자: " + article.getAuthor());
            System.out.println("------------------------------");
        } else {
            System.out.println("해당 게시글이 존재하지 않습니다.");
        }
    }

    public void update(Article article){
        Article articleFine = repository.findId(article.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        repository.update(articleFine.getId(), article);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public Article findId(int id){
        Article articleFind = repository.findId(id).orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        return articleFind;
    }
}
