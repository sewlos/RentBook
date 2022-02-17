package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.model.Item;
import pl.edu.wszib.book.store.model.Manufacturer;
import pl.edu.wszib.book.store.service.IManufacturerService;
import pl.edu.wszib.book.store.service.impl.ItemService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    IManufacturerService iManufacturerService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("authors", this.iManufacturerService.getManufacturerByType("Author"));
        return "item_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam Integer author,
                         @RequestParam Integer quantity,
                         @RequestParam Double price,
                         @RequestParam String description,
                         @RequestParam String type) {

        Optional<Manufacturer> manufacturerBox  = iManufacturerService.getManufacturerById(author);

        if (manufacturerBox.isEmpty())
            return "redirect:/create";


        itemService.addItem(new Item(name, manufacturerBox.get(), price, description, quantity, Item.Type.valueOf(type)));

        return "redirect:/main";
    }


    @RequestMapping(value = "/stationery/create", method = RequestMethod.GET)
    public String createStationeryForm(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("manufacturers", this.iManufacturerService.getManufacturerByType("Manufacturer"));
        return "stationery_create";
    }

    @RequestMapping(value = "/stationery/create", method = RequestMethod.POST)
    public String createStationery(@RequestParam String name, @RequestParam Integer manufacturer,
                         @RequestParam Integer quantity,
                         @RequestParam Double price,
                         @RequestParam String description,
                         @RequestParam String type) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        Optional<Manufacturer> manufacturerBox  = iManufacturerService.getManufacturerById(manufacturer);

        if (manufacturerBox.isEmpty())
            return "redirect:/create";


        itemService.addItem(new Item(name, manufacturerBox.get(), price, description, quantity, Item.Type.valueOf(type)));

        return "redirect:/main";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        Optional<Item> itemBox = this.itemService.getItemById(id);

        if (itemBox.isEmpty()){
            return "redirect:/main";
        }

        this.itemService.deleteItem(itemBox.get());

        return "redirect:/main";
    }

    @RequestMapping(value = "/book/show", method = RequestMethod.GET)
    public String create(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("books", this.itemService.getItemsByType("Book"));

        return "book_list";
    }

    @RequestMapping(value = "/stationery/show", method = RequestMethod.GET)
    public String getStationery(Model model) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        model.addAttribute("stationers", this.itemService.getItemsByType("Stationery"));

        return "stationery_list";
    }
}
