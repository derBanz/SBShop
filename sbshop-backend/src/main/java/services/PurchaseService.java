package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Purchase;
import repositories.PurchaseRepository;

import java.util.List;

@ApplicationScoped
public class PurchaseService {

    @Inject
    private PurchaseRepository purchaseRepository;

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
        return purchase;
    }

    public Purchase getPurchase(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }
}