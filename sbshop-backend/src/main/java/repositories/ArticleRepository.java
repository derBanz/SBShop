package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Article;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<Article> {
}