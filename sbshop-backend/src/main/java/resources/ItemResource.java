package resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Item;
import services.ItemService;

import java.util.List;

@Path("/item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    ItemService itemService;

    @POST
    @Transactional
    public List<Item> add(String articles) {
        List<Item> items = itemService.createItems(articles);
        return items;
    }

    @PUT
    @Transactional
    public List<Item> update(String articles) {
        return itemService.updateItems(articles);
    }
}
