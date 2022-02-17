package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Item;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface InItemDAO {
    void addItem(Item item);

    void deleteItem(Item item);

    List<Item> getItems();
    Optional<Item> getItemById(int bookId);
    void updateItem(Item item);

    List<Item> getItemsByType(String type);

    HashSet<Item> getItemsByManufacturer(int mId);

    List<Item> getRandomItems();

    Item getRandomItem();
}
