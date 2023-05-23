package resources;

import jakarta.ws.rs.*;
import model.Purchase;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

@Path("/purchases")
public interface PurchaseResource extends PanacheEntityResource<Purchase, Long> {

}
