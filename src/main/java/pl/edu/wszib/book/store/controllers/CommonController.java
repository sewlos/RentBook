package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.model.Manufacturer;
import pl.edu.wszib.book.store.service.IManufacturerService;
import pl.edu.wszib.book.store.service.InItemService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    InItemService inItemService;

    @Autowired
    IManufacturerService iManufacturerService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        if(this.sessionObject.isLogged()){
            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }

        model.addAttribute("hotItem", this.inItemService.hotItem());
        model.addAttribute("offers", this.inItemService.offer());
        model.addAttribute("actuals", this.inItemService.actuals());
        model.addAttribute("authors", this.iManufacturerService.getManufacturerByType("Author"));
        return "main";
    }


    @RequestMapping(value = "/userAccount", method = RequestMethod.GET)
    public String userAccount(Model model) {
        if(!this.sessionObject.isLogged()){
            return "redirect:/login";
        }

        model.addAttribute("user", this.sessionObject.getUser());
        return "userAccount";
    }
}
