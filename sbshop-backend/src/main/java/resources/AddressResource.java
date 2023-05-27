package resources;

import io.vertx.core.json.Json;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.Address;
import services.AddressService;

@Path("/address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
    @Inject
    AddressService addressService;

    @GET
    public List<Address> get() {
        return addressService.getAddresses();
    }

    @GET
    @Path("/{addressId}")
    public Address getAddress(@PathParam("addressId") Long addressId) {
        return addressService.getAddress(addressId);
    }

    @POST
    public List<Address> add(Address address) {
        addressService.createAddress(address);
        return addressService.getAddresses();
    }

    @PUT
    @Path("/{addressId}")
    public Address update(@PathParam("addressId") Long addressId, Address address) {
        return addressService.update(addressId, address);
    }

    @DELETE
    @Path("/{addressId}")
    public Response delete(@PathParam("addressId") Long addressId) {
        boolean delete = addressService.deleteAddress(addressId);
        String response = delete ? "Success" : "Failure";
        return Response.ok().entity("{\"value\": \"" + response + "\"}").build();
    }
}
