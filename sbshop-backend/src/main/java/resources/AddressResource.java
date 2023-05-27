package resources;

import jakarta.ws.rs.*;
import model.Client;
import model.Address;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import java.util.List;

public interface AddressResource extends PanacheEntityResource<Address, Long> {
}
