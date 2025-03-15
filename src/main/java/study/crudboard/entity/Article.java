package study.crudboard.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private final int id;
    private String title;
    private String body;
    private int hit;
    private String nowDate;
    private final String author;

    public Article(int id, String title, String body, int hit, String nowDate, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.hit = ++hit;
        this.nowDate = nowDate;
        this.author = author;
    }

    @Override
    public String toString() {
        return "게시글 ID: " + id +
                "\n제목: " + title +
                "\n내용: " + body +
                "\n조회수: " + hit +
                "\n작성일: " + nowDate +
                "\n작성자: " + author;
    }
}

