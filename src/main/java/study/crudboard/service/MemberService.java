package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Member;
import study.crudboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean join(String loginId, String loginPw, String name) {
        if (memberRepository.existsByLoginId(loginId)) {
            return false;
        }
        int id = memberRepository.generateId();
        Member member = new Member(id, loginId, loginPw, name);
        memberRepository.save(member);
        return true;
    }

    public Member login(String loginId, String loginPw) {
        Member member = memberRepository.findByLoginId(loginId);
        if (member != null && member.getLoginPw().equals(loginPw)) {
            return member;
        }
        return null;
    }
}
