package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.model.Item;
import pl.edu.wszib.book.store.model.Manufacturer;
import pl.edu.wszib.book.store.service.IManufacturerService;
import pl.edu.wszib.book.store.service.InItemService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;

@Controller
@RequestMapping(value = "/manufacturer")
public class ManufacturerController {

    @Autowired
    IManufacturerService manufacturerService;

    @Autowired
    InItemService inItemService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/get/{manufacturerId}", method = RequestMethod.GET)
    public String getOfficeArticleById(Model model, @PathVariable Integer manufacturerId) {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        Optional<Manufacturer> manufacturerBox = this.manufacturerService.getManufacturerById(manufacturerId);



        if (manufacturerBox.isEmpty())
            return "redirect:/main";

        Manufacturer manufacturer = manufacturerBox.get();

        HashSet<Item> hashSet = this.inItemService.getItemsByManufacturer(manufacturer.getId());

        manufacturer.setItems(hashSet);

        model.addAttribute("manufacturer", manufacturer);

        return "manufacturer_show";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createForm() {

        if (!sessionObject.isLogged())
            return "redirect:/login";

        return "create_manufacturer";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(@RequestParam String name,
                         @RequestParam String type) {

        this.manufacturerService.addManufacturer(new Manufacturer(name, Manufacturer.Type.valueOf(type)));

        return "redirect:/main";
    }



}
