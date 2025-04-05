package study.crudboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.crudboard.entity.Member;
import study.crudboard.service.MemberService;
import study.crudboard.controller.MemberForm;

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
    public String register(@ModelAttribute MemberForm memberForm, Model model, HttpSession session) {
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
        session.setAttribute("loginMember", member);
        return "redirect:/articles";
    }
}
