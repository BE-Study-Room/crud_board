package study.crudboard.repository;

public interface MemberRepository {
    void login(String id, String pw);
    void join(String id, String pw, String pwcheck);
}
