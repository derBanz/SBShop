package resources;

import jakarta.ws.rs.*;
import model.Article;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

@Path("/article")
public interface ArticleResource extends PanacheEntityResource<Article, Long> {

}
