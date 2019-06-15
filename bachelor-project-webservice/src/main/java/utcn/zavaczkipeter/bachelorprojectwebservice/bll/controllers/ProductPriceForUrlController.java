package utcn.zavaczkipeter.bachelorprojectwebservice.bll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud.ProductPriceForUrlBll;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductPriceForUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.wrappers.IdWrapper;

import java.util.List;

@RestController
@RequestMapping(value = "/productPriceForUrl")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductPriceForUrlController {
    @Autowired
    ProductPriceForUrlBll productPriceForUrlBll;

    @GetMapping(value = "/getAll")
    public List<ProductPriceForUrlDto> getAllProductPriceForUrl() {
        return productPriceForUrlBll.getAllProductPriceForUrl();
    }

    @GetMapping(value = "/get/{id}")
    public ProductPriceForUrlDto getProductPriceForUrlById(@PathVariable("id") int id) {
        return productPriceForUrlBll.getProductPriceForUrlById(id);
    }

    @PostMapping(value = "/add")
    public String addProductPriceForUrl(@RequestBody ProductPriceForUrlDto productPriceForUrl) {
        return productPriceForUrlBll.addProductPriceForUrl(productPriceForUrl);
    }

    @PostMapping(value = "/update")
    public String updateProductPriceForUrl(@RequestBody ProductPriceForUrlDto productPriceForUrl) {
        return productPriceForUrlBll.updateProductPriceForUrl(productPriceForUrl);
    }

    @PostMapping(value = "/delete")
    public String deleteProductPriceForUrl(@RequestBody IdWrapper idWrapper) {
        return productPriceForUrlBll.deleteProductPriceForUrl(idWrapper.getId());
    }
}
