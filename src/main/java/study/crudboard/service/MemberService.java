package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Member;
import study.crudboard.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean join(String loginId, String loginPw, String name) {
        if (memberRepository.existsByLoginId(loginId)) {
            return false;
        }
        Member member = new Member(loginId, loginPw, name);
        memberRepository.save(member);
        return true;
    }

    public Member login(String loginId, String loginPw) {
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            if (member.getLoginPw().equals(loginPw)) {
                return member;
            }
        }
        return null;
    }
}
