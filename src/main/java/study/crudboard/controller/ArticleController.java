package study.crudboard.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.crudboard.dto.ArticleForm;
import study.crudboard.entity.Article;
import study.crudboard.entity.Member;
import study.crudboard.exception.BusinessException;
import study.crudboard.service.ArticleService;
import study.crudboard.service.Util;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "authorName", required = false) String authorName,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {

        Sort sortObj;

        if (sort != null && !sort.isBlank() && sort.contains(",")) {
            String[] parts = sort.split(",");
            sortObj = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
        } else {
            sortObj = Sort.by("id").descending();
            sort = "id,desc";
        }

        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        Page<Article> articlePage =
                articleService.findByTitleAndAuthor(title, authorName, pageable);

        model.addAttribute("articles", articlePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", articlePage.getTotalPages());
        model.addAttribute("searchedTitle", title);
        model.addAttribute("searchedAuthorName", authorName);
        model.addAttribute("sort", sort);

        model.addAttribute("sort", sort);

        return "articles/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("article", new ArticleForm());
        return "articles/form";
    }

    @PostMapping
    public String create(@ModelAttribute ArticleForm form, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/login";
        }

        String nowDate = Util.getNowDateStr();
        Article article = new Article(form.getTitle(), form.getBody(), nowDate, loginMember);
        articleService.create(article);

        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model,
                         @RequestParam(value = "unauthorized", required = false) String unauthorized)
            throws BusinessException {

        Article article = articleService.findId(id);
        article.incrementHit();
        articleService.update(article);

        model.addAttribute("article", article);
        model.addAttribute("unauthorized", unauthorized);
        return "articles/detail";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Article article = articleService.findId(id);
        model.addAttribute("article", article);
        return "articles/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable int id, @ModelAttribute ArticleForm form){
        Article article = articleService.findId(id);
        article.setTitle(form.getTitle());
        article.setBody(form.getBody());
        articleService.update(article);
        return "redirect:/articles/" + article.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        articleService.delete(id);
        return "redirect:/articles";
    }
}
