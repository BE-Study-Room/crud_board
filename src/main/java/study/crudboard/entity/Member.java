package study.crudboard.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    int id;
    public String loginId;
    public String loginPw;
    public String name;

    public Member() {}

    public Member(int id, String loginId, String loginPw, String name){
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

}
