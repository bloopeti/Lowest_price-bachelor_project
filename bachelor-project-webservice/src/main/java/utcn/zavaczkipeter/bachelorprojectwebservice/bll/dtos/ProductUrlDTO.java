package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

public class ProductUrlDTO {
    private int id;
    private String url;
    private String domain;
    private int productId;
    private ProductPriceForUrlDTO productPriceForUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductPriceForUrlDTO getProductPriceForUrl() {
        return productPriceForUrl;
    }

    public void setProductPriceForUrl(ProductPriceForUrlDTO productPriceForUrl) {
        this.productPriceForUrl = productPriceForUrl;
    }
}
