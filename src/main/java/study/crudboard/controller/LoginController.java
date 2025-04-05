package study.crudboard.controller;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.crudboard.entity.Member;
import study.crudboard.service.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "articles/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpSession session) {
        Member loginMember = memberService.login(loginForm.getLoginId(), loginForm.getLoginPw());
        if (loginMember == null) {
            return "redirect:/login?error";
        }

        session.setAttribute("loginMember", loginMember);
        return "redirect:/articles";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
