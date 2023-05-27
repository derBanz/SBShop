package resources;

import jakarta.ws.rs.*;
import model.Purchase;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

@Path("/purchase")
public interface PurchaseResource extends PanacheEntityResource<Purchase, Long> {

}
