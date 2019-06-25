package utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductPriceForUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductPriceForUrlConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductUrlConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductPriceForUrl;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories.ProductUrlRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductUrlBll {
    @Autowired
    ProductUrlRepository productUrlRepository;
    @Autowired
    private ProductUrlConverter productUrlConverter;
    @Autowired
    ProductBll productBll;
    @Autowired
    ProductConverter productConverter;
    @Autowired
    ProductPriceForUrlBll productPriceForUrlBll;
    @Autowired
    ProductPriceForUrlConverter productPriceForUrlConverter;

    public List<ProductUrlDto> getAllProductUrls() {
        return productUrlConverter.entityListToDtoList(productUrlRepository.findAll());
    }

    public List<ProductUrlDto> getAllProductUrlsByProduct(ProductDto productDto) {
        return productUrlConverter.entityListToDtoList(productUrlRepository.findByProduct(productConverter.dtoToEntity(productDto)).get());
    }

    public List<ProductUrlDto> getAllProductUrlsByDomain(String domain) {
        return productUrlConverter.entityListToDtoList(productUrlRepository.findByDomain(domain).get());
    }

    public String addProductUrl(ProductUrlDto productUrlDto) {
        if (productBll.getProductById(productUrlDto.getProductId()) == null)
            return "PRODUCT URL ADD FAILED: Product with this ID doesn't exist";
        ProductUrl productUrl = productUrlRepository.save(productUrlConverter.dtoToEntity(productUrlDto));

        ProductPriceForUrlDto productPriceForUrlDto = new ProductPriceForUrlDto();
        productPriceForUrlDto.setProductUrlId(productUrl.getId());
        if (productPriceForUrlBll.addProductPriceForUrl(productPriceForUrlDto).contains("SUCCESS")) {
            productUrl.setProductPriceForUrl(productPriceForUrlConverter.dtoToEntity(productPriceForUrlDto));

            return "PRODUCT URL ADD SUCCESS";
        } else
            return "PRODUCT URL ADD WARNING: Wasn't able to add PPFU for this URL";
    }

    public String updateProductUrl(ProductUrlDto productUrlDto) {
        ProductUrl updatedProductUrl;
        String reason = "ProductUrl";
        if (productUrlRepository.findById(productUrlDto.getId()).isPresent()) {
            updatedProductUrl = productUrlRepository.findById(productUrlDto.getId()).get();
            if (productUrlDto.getDomain() != null)
                updatedProductUrl.setDomain(productUrlDto.getDomain());
            if (productUrlDto.getUrl() != null)
                updatedProductUrl.setUrl(productUrlDto.getUrl());
            productUrlRepository.save(updatedProductUrl);
            return "PRODUCT URL UPDATE SUCCESSFUL";
        }
        return "PRODUCT URL UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deleteProductUrl(int id) {
        String reason = "ProductUrl";
        if (productUrlRepository.findById(id).isPresent()) {
            productUrlRepository.deleteById(id);
            return "PRODUCT URL DELETE SUCCESSFUL";
        }
        return "PRODUCT URL DELETE FAILED: " + reason + " with this ID doesn't exist";
    }

    public ProductUrlDto findProductUrlByUrlPart(String urlPart) {
        Optional<List<ProductUrl>> db_result = productUrlRepository.findByUrlContains(urlPart);
        if (db_result.isPresent())
            return productUrlConverter.entityToDto(db_result.get().get(0));
        else
            return new ProductUrlDto();
    }

    public String getUrlWithCheapestPrice(String urlPart) {
        if (!(urlPart.equals("/")))
        {
            Optional<List<ProductUrl>> db_result = productUrlRepository.findByUrlContains(urlPart);
            if (db_result.isPresent()) {
                float smallestPrice = db_result.get().get(0).getProductPriceForUrl().getPrice();
                ProductUrl smallestPriceUrl = db_result.get().get(0);
                for (ProductUrl productUrl : db_result.get().get(0).getProduct().getUrls()) {
                    if (productUrl.getProductPriceForUrl().getPrice() < smallestPrice) {
                        smallestPriceUrl = productUrl;
                        smallestPrice = productUrl.getProductPriceForUrl().getPrice();
                    }
                }
                return smallestPriceUrl.getUrl();
            } else
                return "No similar url found!";
        } else
            return "You're not on a product page (no path)";
    }
}
