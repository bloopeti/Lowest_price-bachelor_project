package utcn.zavaczkipeter.bachelorprojectwebservice.bll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud.ProductBll;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.wrappers.IdWrapper;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    ProductBll productBll;

    @GetMapping(value = "/getAll")
    public List<ProductDto> getAllProducts() {
        return productBll.getAllProducts();
    }

    @GetMapping(value = "/get/{id}")
    public ProductDto getProductById(@PathVariable("id") int id) {
        return productBll.getProductById(id);
    }

    @PostMapping(value = "/add")
    public String addProduct(@RequestBody ProductDto product) {
        return productBll.addProduct(product);
    }

    @PostMapping(value = "/update")
    public String updateProduct(@RequestBody ProductDto product) {
        return productBll.updateProduct(product);
    }

    @PostMapping(value = "/delete")
    public String deleteProduct(@RequestBody IdWrapper idWrapper) {
        return productBll.deleteProduct(idWrapper.getId());
    }
}
