package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Item;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}