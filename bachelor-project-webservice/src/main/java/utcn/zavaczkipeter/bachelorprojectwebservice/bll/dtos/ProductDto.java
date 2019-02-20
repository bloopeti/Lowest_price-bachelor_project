package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

import java.util.List;

public class ProductDto {
    private int id;
    private String name;
    private ProductDetailsDto productDetails;
    private List<ProductUrlDto> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDetailsDto getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetailsDto productDetails) {
        this.productDetails = productDetails;
    }

    public List<ProductUrlDto> getUrls() {
        return urls;
    }

    public void setUrls(List<ProductUrlDto> urls) {
        this.urls = urls;
    }
}
