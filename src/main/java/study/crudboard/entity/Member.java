package study.crudboard.entity;

public class Member {
    int id;
    public String loginId;
    public String loginPw;
    public String name;

    public Member(int id, String loginId, String loginPw, String name){
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }
}
