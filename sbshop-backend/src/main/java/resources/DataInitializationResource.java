package resources;

import jakarta.ws.rs.*;
import data.DataInitializer;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@Path("/init-data")
public class DataInitializationResource {

    @Inject
    DataInitializer dataInitializer;

    @POST
    public Response initializeData() {
        dataInitializer.initData();
        return Response.ok().entity("{\"value\": \"Data initialized.\"}").build();
    }
}
