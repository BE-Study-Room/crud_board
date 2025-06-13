package study.crudboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.crudboard.entity.Member;
import study.crudboard.exception.BusinessException;
import study.crudboard.exception.ErrorCode;
import study.crudboard.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void join(String loginId, String loginPw, String name) {
        if (memberRepository.existsByLoginId(loginId)) {
            throw new BusinessException(ErrorCode.HAS_EMAIL, "/signup");
        }
        Member member = new Member(loginId, loginPw, name);
        memberRepository.save(member);
    }

    public Member login(String loginId, String loginPw) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, "/login"));

        if (!member.getLoginPw().equals(loginPw)) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD, "/login");
        }

        return member;
    }
}
