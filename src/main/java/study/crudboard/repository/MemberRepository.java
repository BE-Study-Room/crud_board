package study.crudboard.repository;

import org.springframework.stereotype.Repository;
import study.crudboard.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private final List<Member> members = new ArrayList<>();

    public void save(Member member) {
        members.add(member);
    }

    public Member findByLoginId(String loginId) {
        return members.stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst()
                .orElse(null);
    }

    public boolean existsByLoginId(String loginId) {
        return members.stream()
                .anyMatch(m -> m.getLoginId().equals(loginId));
    }

    public int generateId() {
        return members.size() + 1;
    }
}
