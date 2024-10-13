package me.hsw.springbootdeveloper.dto;

import lombok.Getter;
import lombok.ToString;
import me.hsw.springbootdeveloper.domain.Article;

@Getter
@ToString
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
