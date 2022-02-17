package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.model.Item;
import pl.edu.wszib.book.store.service.InItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/item_rest")
public class ItemRestContoller {
    @Autowired
    InItemService inItemService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Item> getItemList() {

        return inItemService.getItems();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Item getItem(@PathVariable int id) {

        Optional<Item> carBox = inItemService.getItemById(id);

        if(carBox.isEmpty()) {
            return new Item();
        }

        return carBox.get();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item car) {

        inItemService.addItem(car);

        return car;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Item updateItem(@PathVariable int id, @RequestBody Item item) {


        Optional<Item> itemBox = inItemService.getItemById(id);

        if(itemBox.isEmpty()) {
            return new Item();
        }

        Item itemFromBox = itemBox.get();

        itemFromBox.setName(item.getName());
        itemFromBox.setManufacturer(item.getManufacturer());
        itemFromBox.setPrice(item.getPrice());
        itemFromBox.setDescription(item.getDescription());
        itemFromBox.setQuantity(item.getQuantity());
        itemFromBox.setType(item.getType());

        inItemService.updateItem(itemFromBox);

        return itemFromBox;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable int id) {


        Optional<Item> carBox = inItemService.getItemById(id);

        if(carBox.isEmpty()) {
            return;
        }

        Item carFromBox = carBox.get();

        inItemService.deleteItem(carFromBox);
    }
}
