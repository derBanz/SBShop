package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Address;
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

    public List<Article> getArticles() { return articleRepository.listAll(); }

    @Transactional
    public Article update(Long articleId, Article article) {
        Article articleEdit = articleRepository.findById(articleId);
        if (articleEdit != null) {
            articleEdit.setName(article.getName());
            articleEdit.setLength(article.getLength());
            articleEdit.setWidth(article.getWidth());
            articleEdit.setHeight(article.getHeight());
            articleEdit.setPrice(article.getPrice());
            articleEdit.setDescription(article.getDescription());
        }
        return articleEdit;
    }

    @Transactional
    public void delete(Long articleId) {
        Article article = articleRepository.findById(articleId);
        article.setItems(null);
        articleRepository.delete(article);
    }
}