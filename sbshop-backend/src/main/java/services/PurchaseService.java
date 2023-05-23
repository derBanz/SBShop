package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Purchase;
import repositories.PurchaseRepository;

@ApplicationScoped
public class PurchaseService {

    @Inject
    private PurchaseRepository purchaseRepository;

    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        purchaseRepository.persist(purchase);
        return purchase;
    }
}