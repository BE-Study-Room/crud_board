package study.crudboard.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private final int id;
    private String title;
    private String body;
    private int hit = 0;
    private String nowDate;
    private final String author;

    public Article(int id, String title, String body, String nowDate, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.nowDate = nowDate;
        this.author = author;
    }

    public void incrementHit() {
        this.hit++;
    }

}

