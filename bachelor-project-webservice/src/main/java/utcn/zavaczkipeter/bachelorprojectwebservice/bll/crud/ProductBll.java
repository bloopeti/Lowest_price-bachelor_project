package utcn.zavaczkipeter.bachelorprojectwebservice.bll.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductUrlDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductDetailsConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductDetails;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBll {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    ProductDetailsBll productDetailsBll;
    @Autowired
    ProductDetailsConverter productDetailsConverter;

    public List<ProductDto> getAllProducts() {
        return productConverter.entityListToDtoList(productRepository.findAll());
    }

    public ProductDto getProductById(int id) {
        if (productRepository.findById(id).isPresent())
            return productConverter.entityToDto(productRepository.findById(id).get());
        else
            return null;
    }

    // Whenever we add a product to the database, we automatically add
    // a corresponding ProductDetails too
    public String addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUrls(new ArrayList<ProductUrl>());
        product = productRepository.save(product);

        ProductDetails productDetails = new ProductDetails();
        productDetails.setProduct(product);
        productDetailsBll.addProductDetails(productDetailsConverter.entityToDto(productDetails));
        product.setProductDetails(productDetails);
        productRepository.save(product);
        return "PRODUCT ADD SUCCESSFUL";
    }

    public String updateProduct(ProductDto productDto) {
        Product updatedProduct;
        String reason = "Product";
        if (productRepository.findById(productDto.getId()).isPresent()) {
            updatedProduct = productRepository.findById(productDto.getId()).get();
            updatedProduct.setName(productDto.getName());
            productRepository.save(updatedProduct);
            return "PRODUCT UPDATE SUCCESSFUL";
        }
        return "PRODUCT UPDATE FAILED: " + reason + "with this ID doesn't exist";
    }

    public String addUrlToProduct(ProductUrl productUrl) {
        if (productRepository.findById(productUrl.getProduct().getId()).isPresent()) {
            Product product = productRepository.findById(productUrl.getProduct().getId()).get();
            product.getUrls().add(productUrl);
            productRepository.save(product);
            return "SUCCESSFULLY ADDED PRODUCT URL TO PRODUCT";
        }
        return "FAILED TO ADD PRODUCT URL TO PRODUCT: Product with this ID doesn't exist";
    }

    public String deleteProduct(int id) {
        String reason = "Product";
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return "PRODUCT DELETE SUCCESSFUL";
        }
        return "PRODUCT DELETE FAILED: " + reason + " with this ID doesn't exist";
    }
}