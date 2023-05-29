package services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Article;
import model.Item;
import model.Purchase;
import repositories.ArticleRepository;
import repositories.ItemRepository;
import repositories.PurchaseRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Type;
import java.util.ListIterator;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemService {

    @Inject
    private ItemRepository itemRepository;
    @Inject
    private ArticleRepository articleRepository;
    @Inject
    PurchaseRepository purchaseRepository;

    @Transactional
    public List<Item> createItems(String articles) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonObject>>() {}.getType();
        List<JsonObject> jsonList = gson.fromJson(articles, listType);
        List<Item> items = new ArrayList<>();
        Purchase purchase = purchaseRepository.findById(jsonList.get(0).get("purchaseId").getAsLong());
        for (JsonObject json : jsonList) {
            Article article = articleRepository.findById(json.get("articleId").getAsLong());
            int count = json.get("quantity").getAsInt();
            for (int i = 0; i < count; i++) {
                Item item = new Item(purchase, article);
                createItem(item);
                items.add(item);
            }
        }
        purchase.setPrice(items);
        return items;
    }


    public List<Item> updateItems(String articles) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonObject>>() {}.getType();
        List<JsonObject> jsonList = gson.fromJson(articles, listType);
        List<Item> items = new ArrayList<>();
        Purchase purchase = purchaseRepository.findById(jsonList.get(0).get("purchaseId").getAsLong());
        for(Item item : purchase.getItems()) {
            boolean articleRemoved = jsonList.stream().filter(json -> json.get("articleId").getAsLong() == item.getArticleId()).collect(Collectors.toList()).size() == 0;
            if (articleRemoved){
                item.setPurchase(null);
                item.setPurchaseId(-1);
                item.setArticle(null);
                item.setArticleId(-1);
                itemRepository.delete(item);
            }
        }
        for(JsonObject json : jsonList){
            Article article = articleRepository.findById(json.get("articleId").getAsLong());
            List<Item> purchaseItems = purchase.getArticleItems(article);
            int countNew = json.get("quantity").getAsInt();
            int countOld = purchaseItems.size();
            if(countNew < countOld) {
                int counter = countOld - countNew;
                Iterator<Item> purchaseItemIterator = purchaseItems.iterator();
                while(purchaseItemIterator.hasNext()) {
                    if (counter > 0) {
                        Item item = purchaseItemIterator.next();
                        item.setPurchase(null);
                        item.setPurchaseId(-1);
                        item.setArticle(null);
                        item.setArticleId(-1);
                        itemRepository.delete(item);
                        counter--;
                    }
                    else {
                        Item item = purchaseItemIterator.next();
                        item.setPrice();
                        items.add(item);
                    }
                    System.out.println("Deleting/Updating, new count: " + items.size());
                }
            }
            else {
                for(Item item : purchaseItems) {
                    item.setPrice();
                    items.add(item);
                }
                for(int i = 0; i < countNew - countOld; i++) {
                    Item item = new Item(purchase, article);
                    createItem(item);
                    items.add(item);
                }
            }
        }
        purchase.setPrice(items);
        return items;
    }

    public List<Item> getPurchaseItems(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId);
        return purchase.getItems();
    }

    @Transactional
    public Item createItem(Item item) {
        itemRepository.persist(item);
        return item;
    }
}