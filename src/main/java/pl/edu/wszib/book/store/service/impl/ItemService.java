package pl.edu.wszib.book.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.database.InItemDAO;
import pl.edu.wszib.book.store.model.Item;
import pl.edu.wszib.book.store.service.InItemService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements InItemService {

    @Autowired
    InItemDAO inItemDAO;

    @Override
    public List<Item> getItems() {
        return this.inItemDAO.getItems();
    }

    public Optional<Item> getItemById(int itemId) {
        return this.inItemDAO.getItemById(itemId);
    }

    public void updateItem(Item item) {
        this.inItemDAO.updateItem(item);
    }

    public void addItem(Item item) {
        this.inItemDAO.addItem(item);
    }

    public void deleteItem(Item item) {
        this.inItemDAO.deleteItem(item);
    }

    public List<Item> getItemsByType(String type) {
        return this.inItemDAO.getItemsByType(type);
    }

    @Override
    public HashSet<Item> getItemsByManufacturer(int mId) {
        return this.inItemDAO.getItemsByManufacturer(mId);
    }

    @Override
    public Item hotItem() {
        return this.inItemDAO.getRandomItem();
    }

    @Override
    public List<Item> actuals(){
        return this.inItemDAO.getRandomItems();
    }

    @Override
    public List<Item> offer() {
        return this.inItemDAO.getRandomItems();
    }
}
