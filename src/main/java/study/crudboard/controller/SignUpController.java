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
    public String register(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "articles/signup";
        }

        memberService.join(memberForm.getLoginId(), memberForm.getLoginPw(), memberForm.getName());

        return "redirect:/login";
    }
}
