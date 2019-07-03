package utcn.zavaczkipeter.bachelorprojectwebservice.bll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.operations.ProductDetailsBll;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDetailsDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.wrappers.IdWrapper;

import java.util.List;

@RestController
@RequestMapping(value = "/productDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductDetailsController {
    @Autowired
    ProductDetailsBll productDetailsBll;

    @GetMapping(value = "/getAll")
    public List<ProductDetailsDto> getAllProductDetails() {
        return productDetailsBll.getAllProductDetails();
    }

    @GetMapping(value = "/get/{id}")
    public ProductDetailsDto getProductDetailsById(@PathVariable("id") int id) {
        return productDetailsBll.getProductDetailsById(id);
    }

    @PostMapping(value = "/add")
    public String addProductDetails(@RequestBody ProductDetailsDto productDetails) {
        return productDetailsBll.addProductDetails(productDetails);
    }

    @PostMapping(value = "/update")
    public String updateProductDetails(@RequestBody ProductDetailsDto productDetails) {
        return productDetailsBll.updateProductDetails(productDetails);
    }

    @PostMapping(value = "/delete")
    public String deleteProductDetails(@RequestBody IdWrapper idWrapper) {
        return productDetailsBll.deleteProductDetails(idWrapper.getId());
    }
}
