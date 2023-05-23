package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Item;
import repositories.ItemRepository;

@ApplicationScoped
public class ItemService {

    @Inject
    private ItemRepository itemRepository;

    @Transactional
    public Item createItem(Item item) {
        itemRepository.persist(item);
        return item;
    }
}