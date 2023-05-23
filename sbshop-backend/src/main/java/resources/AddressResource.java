package resources;

import jakarta.ws.rs.*;
import model.Address;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

@Path("/addresses")
public interface AddressResource extends PanacheEntityResource<Address, Long> {

}
