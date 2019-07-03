package utcn.zavaczkipeter.bachelorprojectwebservice.bll.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.ProductDto;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos.converters.ProductDetailsConverter;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.Product;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductDetails;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities.ProductUrl;
import utcn.zavaczkipeter.bachelorprojectwebservice.dal.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (productDetailsBll.addProductDetails(productDetailsConverter.entityToDto(productDetails)).contains("SUCCESS"))
            return "PRODUCT ADD SUCCESSFUL";
        else
            return "PRODUCT ADD PARTIALLY FAILED: Could not add Product Details";
    }

    public String updateProduct(ProductDto productDto) {
        Product updatedProduct;
        String reason = "Product";
        if (productRepository.findById(productDto.getId()).isPresent()) {
            updatedProduct = productRepository.findById(productDto.getId()).get();
            if (productDto.getName() != null)
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

    public List<ProductDto> getProductsBySearchQuery(String searchQuery) {
        Optional<List<Product>> db_result = productRepository.findByNameContains(searchQuery);
        if(db_result.isPresent())
            return productConverter.entityListToDtoList(db_result.get());
        else
            return new ArrayList<>();
    }
}
