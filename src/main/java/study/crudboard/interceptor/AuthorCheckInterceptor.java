package study.crudboard.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import study.crudboard.entity.Article;
import study.crudboard.entity.Member;
import study.crudboard.service.ArticleService;

@RequiredArgsConstructor
public class AuthorCheckInterceptor implements HandlerInterceptor {

    private final ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/login");
            return false;
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            response.sendRedirect("/login");
            return false;
        }

        String requestURI = request.getRequestURI();
        String[] parts = requestURI.split("/");
        if (parts.length < 3) {
            response.sendRedirect("/articles?unauthorized");
            return false;
        }

        try {
            int articleId = Integer.parseInt(parts[2]);
            Article article = articleService.findId(articleId);

            if (!loginMember.getName().equals(article.getAuthor())) {
                response.sendRedirect("/articles/" + articleId + "?unauthorized");
                return false;
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("/articles?unauthorized");
            return false;
        }

        return true;
    }
}
