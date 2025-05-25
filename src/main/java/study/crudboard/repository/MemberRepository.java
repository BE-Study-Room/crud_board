package study.crudboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.crudboard.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}
