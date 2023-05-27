package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Purchase;

@ApplicationScoped
public class PurchaseRepository implements PanacheRepository<Purchase> {
}