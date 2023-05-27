package data;

import jakarta.enterprise.context.ApplicationScoped;
import model.Article;
import services.ArticleService;

import java.util.*;

import jakarta.inject.Inject;

@ApplicationScoped
public class ArticleInitializer {

    @Inject
    ArticleService articleService;

    public void populateArticles() {
        System.out.println("Generating Articles...");
        List<Article> articles = generateArticles();
        System.out.println("Passing Articles to Service...");
        articleService.createArticles(articles);
    }

    private List<Article> generateArticles() {
        List<String> articleNames = List.of("Table", "Chair", "Plate", "Cup", "Glass", "Bed", "Nightstand", "TV", "Home Computer");
        List<Article> articles = new ArrayList<>();

        for (String name : articleNames) {
            double articleHeight = (double)Math.round( (Math.random() * (120 - 60 + 1) + 60) * 10) / 10;
            double articleWidth = (double)Math.round( (Math.random() * (80 - 40 + 1) + 40) * 10 ) / 10;
            double articleLength = (double)Math.round( (Math.random() * (40 - 20 + 1) + 20) * 10 ) / 10;
            double price = (double)Math.round( (Math.random() * (1000 - 0.01 + 1 ) + 0.01) * 100 ) / 100;
            Article article = new Article(name, articleHeight, articleWidth, articleLength, name + " description", price);
            articles.add(article);
        }

        return articles;
    }

}
