package study.crudboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.crudboard.entity.Article;
import study.crudboard.entity.Member;
import study.crudboard.service.ArticleService;
import study.crudboard.service.Util;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    // 글 목록 보기
    @GetMapping
    public String list(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "articles/list";
    }

    // 글 작성 폼
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("article", new ArticleForm());
        return "articles/form";
    }

    // 글 저장
    @PostMapping
    public String create(@ModelAttribute ArticleForm form, HttpSession session) {
        // 로그인된 사용자 정보 가져오기
        Member loginMember = (Member) session.getAttribute("loginMember");
        String author = (loginMember != null) ? loginMember.getName() : "login plz";

        int id = articleService.generateId();
        String nowDate = Util.getNowDateStr();

        Article article = new Article(id, form.getTitle(), form.getBody(), nowDate, author);
        articleService.create(article);

        return "redirect:/articles";
    }

    // 글 상세 보기
    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        Article article = articleService.findId(id);

        article.incrementHit();
        articleService.update(article);

        model.addAttribute("article", article);
        return "articles/detail";
    }

    // 글 수정 폼
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Article article = articleService.findId(id);
        model.addAttribute("article", article);
        return "articles/edit";
    }

    // 글 수정 반영
    @PostMapping("/{id}/edit")
    public String update(@PathVariable int id, @ModelAttribute Article article) {
        articleService.update(article);

        return "redirect:/articles/" + article.getId();
    }


    // 글 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        articleService.delete(id);
        return "redirect:/articles";
    }
}
