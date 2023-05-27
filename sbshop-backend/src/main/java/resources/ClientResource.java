package resources;

import jakarta.ws.rs.*;
import model.Client;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

@Path("/client")
public interface ClientResource extends PanacheEntityResource<Client, Long> {

}
