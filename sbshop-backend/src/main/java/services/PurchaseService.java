package services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Address;
import model.Client;
import model.Purchase;
import repositories.AddressRepository;
import repositories.PurchaseRepository;

import java.lang.reflect.Type;
import java.util.List;

@ApplicationScoped
public class PurchaseService {

    @Inject
    private PurchaseRepository purchaseRepository;
    @Inject
    private ClientService clientService;
    @Inject
    private ItemService itemService;
    @Inject
    private AddressRepository addressRepository;

    @Transactional
    public List<Purchase> createPurchases(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            purchaseRepository.persist(purchase);
        }
        return purchases;
    }

    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        purchaseRepository.persist(purchase);
        Client client = clientService.getClient(purchase.getClientId());
        purchase.setClient(client);
        return purchase;
    }

    @Transactional
    public void updatePurchase(Long purchaseId, String purchase) {
        Gson gson = new Gson();
        Type jsonType = new TypeToken<JsonObject>() {}.getType();
        JsonObject json = gson.fromJson(purchase, jsonType);
        Purchase purchaseEdit = purchaseRepository.findById(purchaseId);
        Long addressId = json.get("addressId").getAsLong();
        Address address = addressRepository.findById(addressId);
        purchaseEdit.setPaymentMethod(json.get("paymentMethod").getAsString());
        purchaseEdit.setDeliveryType(json.get("deliveryType").getAsString());
        purchaseEdit.setIsGift(json.get("isGift").getAsBoolean());
        purchaseEdit.setAddressId(addressId);
        purchaseEdit.setAddress(address);
        if (json.get("isConfirmed").getAsBoolean()) {
            purchaseEdit.setIsConfirmed(true);
            purchaseEdit.setTimeOfPurchase();
        }
    }

    public Purchase getPurchase(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId);
        return purchase;
    }

    public List<Purchase> getPurchases() { return purchaseRepository.listAll(); }

    @Transactional
    public boolean deletePurchase(Long purchaseId) {
        return purchaseRepository.deleteById(purchaseId);
    }
}