package utcn.zavaczkipeter.bachelorprojectwebservice.bll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud.ProductUrlBll;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.wrappers.IdWrapper;

import java.util.List;

@RestController
@RequestMapping(value = "/productUrl")
//@CrossOrigin//(origins = "http://localhost:4200")
public class ProductUrlController {
    @Autowired
    ProductUrlBll productUrlBll;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/getAll")
    public List<ProductUrlDto> getAllProductUrl() {
        return productUrlBll.getAllProductUrls();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/get/{domain}")
    public List<ProductUrlDto> getProductUrlsByDomain(@PathVariable("domain") String domain) {
        return productUrlBll.getAllProductUrlsByDomain(domain);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/add")
    public String addProductUrl(@RequestBody ProductUrlDto productUrl) {
        return productUrlBll.addProductUrl(productUrl);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/update")
    public String updateProductUrl(@RequestBody ProductUrlDto productUrl) {
        return productUrlBll.updateProductUrl(productUrl);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/delete")
    public String deleteProductUrl(@RequestBody IdWrapper idWrapper) {
        return productUrlBll.deleteProductUrl(idWrapper.getId());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/find")
    public ProductUrlDto findProductUrlByUrlPart(@RequestBody String urlPart) {
        return productUrlBll.findProductUrlByUrlPart(urlPart);
    }

    @CrossOrigin//(origins = "http://localhost:4200")
    @PostMapping(value = "/cheapest")
    public String getUrlWithCheapestPrice(@RequestBody String urlPart) {
        return productUrlBll.getUrlWithCheapestPrice(urlPart);
    }
}
