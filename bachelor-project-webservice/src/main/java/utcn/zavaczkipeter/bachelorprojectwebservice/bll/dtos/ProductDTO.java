package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

import java.util.List;

public class ProductDTO {
    private int id;
    private String name;
    private int productDetailsId;
    private List<ProductUrlDTO> urls;

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

    public int getProductDetailsId() {
        return productDetailsId;
    }

    public void setProductDetailsId(int productDetailsId) {
        this.productDetailsId = productDetailsId;
    }

    public List<ProductUrlDTO> getUrls() {
        return urls;
    }

    public void setUrls(List<ProductUrlDTO> urls) {
        this.urls = urls;
    }
}
