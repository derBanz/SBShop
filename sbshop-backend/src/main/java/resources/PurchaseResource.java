package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Item;
import model.Purchase;
import services.ItemService;
import services.PurchaseService;

import java.util.List;

@Path("/purchase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseResource {
    @Inject
    PurchaseService purchaseService;

    @Inject
    ItemService itemService;

    @GET
    public List<Purchase> get() {
        return purchaseService.getPurchases();
    }

    @GET
    @Path("/{purchaseId}")
    public Purchase getPurchase(@PathParam("purchaseId") Long purchaseId) {
        return purchaseService.getPurchase(purchaseId);
    }
    @GET
    @Path("/{purchaseId}/items")
    public List<Item> getPurchaseItems(@PathParam("purchaseId") Long purchaseId) {
        return itemService.getPurchaseItems(purchaseId);
    }

    @POST
    public Purchase add(Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    @PUT
    @Path("/{purchaseId}")
    public void update(@PathParam("purchaseId") Long purchaseId, String purchase) {
        purchaseService.updatePurchase(purchaseId, purchase);
    }

    @DELETE
    @Path("/{purchaseId}")
    public Response delete(@PathParam("purchaseId") Long purchaseId) {
        boolean delete = purchaseService.deletePurchase(purchaseId);
        String response = delete ? "Success" : "Failure";
        return Response.ok().entity("{\"value\": \"" + response + "\"}").build();
    }
}
