package utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDetailsDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductDetailsConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductDetails;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories.ProductDetailsRepository;

import java.util.List;

@Service
public class ProductDetailsBll {
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    private ProductDetailsConverter productDetailsConverter;
    @Autowired
    private ProductConverter productConverter;

    public List<ProductDetailsDto> getAllProductDetails() {
        return productDetailsConverter.entityListToDtoList(productDetailsRepository.findAll());
    }

    public ProductDetailsDto getProductDetailsById(int id) {
        if (productDetailsRepository.findById(id).isPresent())
            return productDetailsConverter.entityToDto(productDetailsRepository.findById(id).get());
        else
            return null;
    }

    public ProductDetailsDto getProductDetailsByProduct(ProductDto productDto) {
        if (productDetailsRepository.findByProduct(productConverter.dtoToEntity(productDto)).isPresent())
            return productDetailsConverter.entityToDto(productDetailsRepository.findByProduct(productConverter.dtoToEntity(productDto)).get());
        else
            return null;
    }

    // this method should not be used except in ProductBll - addProduct
    // Whenever we add a product to the database, we automatically add
    // a corresponding ProductDetails too
    public String addProductDetails(ProductDetailsDto productDetailsDto) {
        productDetailsRepository.save(productDetailsConverter.dtoToEntity(productDetailsDto));
        return "PRODUCT DETAILS ADD SUCCESSFUL";
    }

    // never update the Product field from the ProductDetails
    // it is bound to the one and only product it belongs to from its creation
    public String updateProductDetails(ProductDetailsDto productDetailsDto) {
        ProductDetails updatedProductDetails;
        String reason = "ProductDetails";
        if (productDetailsRepository.findById(productDetailsDto.getId()).isPresent()) {
            updatedProductDetails = productDetailsRepository.findById(productDetailsDto.getId()).get();
            updatedProductDetails.setBrand(productDetailsDto.getBrand());
            return "PRODUCT DETAILS UPDATE SUCCESSFUL";
        }
            return "PRODUCT DETAILS UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deleteProductDetails(int id) {
        String reason = "ProductDetails";
        if (productDetailsRepository.findById(id).isPresent()) {
            productDetailsRepository.deleteById(id);
            return "PRODUCT DETAILS DELETE SUCCESSFUL";
        }
        return "PRODUCT DETAILS DELETE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String fillProductDetails(ProductDetailsDto productDetailsDto) {
        ProductDetails productDetails = productDetailsConverter.dtoToEntity(getProductDetailsById(productDetailsDto.getId()));
        if (productDetails == null)
            return "PRODUCT DETAILS FILL FAILED: ProductDetails with this ID doesn't exist";
        int filledFields = 0;
        if(productDetails.getBrand() == null)
            if(productDetailsDto.getBrand() != null) {
                productDetails.setBrand(productDetailsDto.getBrand());
                filledFields++;
            }
        if (filledFields != 0) {
            productDetailsRepository.save(productDetails);
        }
        return "PRODUCT DETAILS FILL SUCCESSFUL: " + filledFields + " fields filled";
    }
}
