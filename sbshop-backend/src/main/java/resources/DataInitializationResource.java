package resources;

import jakarta.ws.rs.*;
import data.DataInitializer;
import jakarta.inject.Inject;

@Path("/init-data")
public class DataInitializationResource {

    @Inject
    DataInitializer dataInitializer;

    @POST
    public String initializeData() {
        dataInitializer.initData();
        return "Data initialization completed";
    }
}
