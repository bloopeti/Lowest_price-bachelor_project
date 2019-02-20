package utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities;

import javax.persistence.*;

@Entity
@Table
public class ProductUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String url;
    @Column
    private String domain;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;
    @OneToOne(mappedBy = "productUrl")
    private ProductPriceForUrl productPriceForUrl;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductPriceForUrl getProductPriceForUrl() {
        return productPriceForUrl;
    }

    public void setProductPriceForUrl(ProductPriceForUrl productPriceForUrl) {
        this.productPriceForUrl = productPriceForUrl;
    }
}
