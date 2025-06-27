package study.crudboard.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import study.crudboard.entity.Article;
import study.crudboard.entity.QArticle;
import study.crudboard.entity.QMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> findByTitle(String title, Pageable pageable) {
        QArticle article = QArticle.article;

        BooleanExpression titleCondition =
                (title != null && !title.isBlank()) ? article.title.contains(title) : null;

        List<Article> content = queryFactory
                .selectFrom(article)
                .where(titleCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(article.count())
                .from(article)
                .where(titleCondition)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    @Override
    public Page<Article> findByAuthorName(String authorName, Pageable pageable) {
        QArticle article = QArticle.article;
        QMember member = QMember.member;

        BooleanExpression authorCondition =
                (authorName != null && !authorName.isBlank()) ? article.author.name.contains(authorName) : null;

        List<Article> content = queryFactory
                .selectFrom(article)
                .leftJoin(article.author, member).fetchJoin()
                .where(authorCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(article, pageable.getSort()))
                .fetch();

        Long total = queryFactory
                .select(article.count())
                .from(article)
                .leftJoin(article.author, member)
                .where(authorCondition)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }

    @Override
    public Page<Article> findByTitleAndAuthorName(String title, String authorName, Pageable pageable) {
        QArticle article = QArticle.article;
        QMember member = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();

        if (title != null && !title.isBlank()) {
            builder.and(article.title.contains(title));
        }

        if (authorName != null && !authorName.isBlank()) {
            builder.and(article.author.name.contains(authorName));
        }

        List<Article> content = queryFactory
                .selectFrom(article)
                .leftJoin(article.author, member).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(article, pageable.getSort()))
                .fetch();

        Long total = queryFactory
                .select(article.count())
                .from(article)
                .leftJoin(article.author, member)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }

    private com.querydsl.core.types.OrderSpecifier<?>[] getOrderSpecifiers(QArticle article, org.springframework.data.domain.Sort sort) {
        return sort.stream()
                .map(order -> {
                    if (order.getProperty().equalsIgnoreCase("title")) {
                        return order.isAscending() ? article.title.asc() : article.title.desc();
                    } else if (order.getProperty().equalsIgnoreCase("hit")) {
                        return order.isAscending() ? article.hit.asc() : article.hit.desc();
                    } else if (order.getProperty().equalsIgnoreCase("id")) {
                        return order.isAscending() ? article.id.asc() : article.id.desc();
                    } else {
                        return article.id.desc(); // default
                    }
                })
                .toArray(com.querydsl.core.types.OrderSpecifier[]::new);
    }

}
