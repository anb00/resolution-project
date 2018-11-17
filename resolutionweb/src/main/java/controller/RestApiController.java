package controller;

//import com.iesemilidarder.anicolau.resolutionproject.core.data.Product;
import com.iesemilidarder.anicolau.resolutionproject.ResolutionProjectApplication;
import org.hibernate.type.descriptor.java.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CallMeService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/rest"})
public class RestApiController {
    @Autowired
    CallMeService callService;

    public RestApiController() {
    }

    @RequestMapping({"/greeting"})
    public Product greeting(@RequestParam(value = "name",defaultValue = "World") String name, HttpSession session) {
        this.callService.callMe();
        Restaurant aux = new Restaurant();
        aux.setName("hi");
        return aux;
    }

    @RequestMapping({"/add"})
    public Product addProduct(@RequestParam String name, @RequestParam String description, @RequestParam String img, @RequestParam Double price) {
        Product product = new Restaurant();
        product.setName(name);
        product.setDescription(description);
        product.setImgUri(img);
        product.setPrecio(price);
        DataHelper.addItem(product);
        return product;
    }

    @RequestMapping({"/find"})
    public Product findById(@RequestParam String uuid, Session session) {
        Product product = DataHelper.getItemById(UUID.fromString(uuid));
        return (Product)(product == null ? new Restaurant() : product);
    }

    @RequestMapping({"/getAll"})
    public List<Product> getItems(Model model) {
        return DataHelper.getItems();
    }
}