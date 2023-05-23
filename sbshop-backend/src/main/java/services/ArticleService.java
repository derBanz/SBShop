package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Article;
import repositories.ArticleRepository;

@ApplicationScoped
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Transactional
    public Article createArticle(Article article) {
        articleRepository.persist(article);
        return article;
    }
}