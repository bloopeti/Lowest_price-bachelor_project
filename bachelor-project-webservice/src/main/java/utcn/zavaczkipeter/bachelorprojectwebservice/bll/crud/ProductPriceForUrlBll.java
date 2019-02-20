package utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductPriceForUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductPriceForUrlConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductPriceForUrl;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories.ProductPriceForUrlRepository;

import java.util.List;

@Service
public class ProductPriceForUrlBll {
    @Autowired
    ProductPriceForUrlRepository productPriceForUrlRepository;
    @Autowired
    private ProductPriceForUrlConverter productPriceForUrlConverter;

    public List<ProductPriceForUrlDto> getAllProductPriceForUrl() {
        return productPriceForUrlConverter.entityListToDtoList(productPriceForUrlRepository.findAll());
    }

    public ProductPriceForUrlDto getProductPriceForUrlById(int id) {
        if(productPriceForUrlRepository.findById(id).isPresent())
            return productPriceForUrlConverter.entityToDto(productPriceForUrlRepository.findById(id).get());
        else
            return null;
    }

    public String addProductPriceForUrl(ProductPriceForUrlDto productPriceForUrlDto) {
        productPriceForUrlRepository.save(productPriceForUrlConverter.dtoToEntity(productPriceForUrlDto));
        return "PPFU ADD SUCCESSFUL";
    }

    public String updateProductPriceForUrl(ProductPriceForUrlDto productPriceForUrlDto) {
        ProductPriceForUrl updatedProductPriceForUrl;
        String reason = "PPFU";
        if (productPriceForUrlRepository.findById(productPriceForUrlDto.getId()).isPresent()) {
            updatedProductPriceForUrl = productPriceForUrlRepository.findById(productPriceForUrlDto.getId()).get();
            updatedProductPriceForUrl.setCurrency(productPriceForUrlDto.getCurrency());
            updatedProductPriceForUrl.setPrice(productPriceForUrlDto.getPrice());
            productPriceForUrlRepository.save(updatedProductPriceForUrl);
            return "PPFU UPDATE SUCCESSFUL";
        }
        return "PPFU UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deleteProductPriceForUrl(int id) {
        String reason = "PPFU";
        if (productPriceForUrlRepository.findById(id).isPresent()) {
            productPriceForUrlRepository.deleteById(id);
            return "PPFU DELETE SUCCESSFUL";
        }
        return "PPFU DELETE FAILED: " + reason + " with this ID doesn't exist";
    }
}
// TODO merge with prev commit when finished
