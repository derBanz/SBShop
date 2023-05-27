package resources;

import jakarta.ws.rs.*;
import model.Item;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

@Path("/item")
public interface ItemResource extends PanacheEntityResource<Item, Long> {

}
