package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Item;
import repositories.ItemRepository;

import java.util.List;

@ApplicationScoped
public class ItemService {

    @Inject
    private ItemRepository itemRepository;

    @Transactional
    public List<Item> createAddresses(List<Item> items) {
        for (Item item : items) {
            createItem(item);
        }
        return items;
    }

    @Transactional
    public Item createItem(Item item) {
        itemRepository.persist(item);
        return item;
    }

    public Item getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }
}