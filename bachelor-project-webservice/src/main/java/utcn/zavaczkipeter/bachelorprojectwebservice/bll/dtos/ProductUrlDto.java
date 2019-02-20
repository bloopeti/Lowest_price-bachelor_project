package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

public class ProductUrlDto {
    private int id;
    private String url;
    private String domain;
    private int productId;
    private ProductPriceForUrlDto productPriceForUrl;

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

    public ProductPriceForUrlDto getProductPriceForUrl() {
        return productPriceForUrl;
    }

    public void setProductPriceForUrl(ProductPriceForUrlDto productPriceForUrl) {
        this.productPriceForUrl = productPriceForUrl;
    }
}
