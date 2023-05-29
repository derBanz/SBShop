package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Article;
import services.ArticleService;
import java.util.List;

@Path("/article")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    @Inject
    ArticleService articleService;

    @GET
    public List<Article> get() {
        return articleService.getArticles();
    };

    @GET
    @Path("/{articleId}")
    public Article getArticle(@PathParam("articleId") Long articleId) {
        return articleService.getArticle(articleId);
    };

    @POST
    public Article add(Article article) {
        return articleService.createArticle(article);
    };

    @PUT
    @Path("/{articleId}")
    public Article update(@PathParam("articleId") Long articleId, Article article) {
        return articleService.update(articleId, article);
    };

    @DELETE
    @Path("/{articleId}")
    public void delete(@PathParam("articleId") Long articleId) {
        articleService.delete(articleId);
    };

}
