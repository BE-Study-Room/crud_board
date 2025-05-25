package study.crudboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String body;
    private int hit = 0;
    private String nowDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;

    public void incrementHit() {
        this.hit++;
    }

    public Article(String title, String body, String nowDate, Member author) {
        this.title = title;
        this.body = body;
        this.nowDate = nowDate;
        this.author = author;
    }
}
