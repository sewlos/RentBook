package pl.edu.wszib.book.store.service;

import pl.edu.wszib.book.store.model.Item;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface InItemService {

    List<Item> getItems();
    Optional<Item> getItemById(int itemId);
    void updateItem(Item item);
    void addItem(Item item);
    void deleteItem(Item item);

    List<Item> getItemsByType(String type);

    HashSet<Item> getItemsByManufacturer(int mId);

    Item hotItem();

    List<Item> actuals();

    List<Item> offer();
}
