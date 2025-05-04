package study.crudboard.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.crudboard.entity.Member;
import study.crudboard.service.MemberService;
import study.crudboard.dto.MemberForm;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final MemberService memberService;

    // 회원가입 폼
    @GetMapping("/signup")
    public String showRegisterForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "articles/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "articles/signup"; // 유효성 검증 오류가 있으면 회원가입 폼으로 다시 반환
        }
        Member member = new Member();
        member.setLoginId(memberForm.getLoginId());
        member.setLoginPw(memberForm.getLoginPw());
        member.setName(memberForm.getName());

        boolean result = memberService.join(member.getLoginId(), member.getLoginPw(), member.getName());

        if (!result) {
            model.addAttribute("error", "Duplicate login ID or invalid input.");
            return "articles/signup";
        }

        // 회원가입 성공 시 로그인 처리
        // session.setAttribute("loginMember", member);
        return "redirect:/login";
    }
}
