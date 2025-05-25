package study.crudboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String loginId;

    private String loginPw;
    private String name;

    public Member(String loginId, String loginPw, String name){
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }
}
