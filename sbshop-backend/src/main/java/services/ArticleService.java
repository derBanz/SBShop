package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Article;
import repositories.ArticleRepository;
import java.util.List;

@ApplicationScoped
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Transactional
    public List<Article> createArticles(List<Article> articles) {
        for (Article article : articles) {
            createArticle(article);
        }
        return articles;
    }

    @Transactional
    public Article createArticle(Article article) {
        articleRepository.persist(article);
        return article;
    }

    public Article getArticle(Long articleId) {
        return articleRepository.findById(articleId);
    }
}