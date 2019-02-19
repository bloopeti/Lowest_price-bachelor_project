package utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities;

import javax.persistence.*;

@Entity
@Table
public class ProductPriceForURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private float price;
    @Column
    private String currency;
    @OneToOne(optional = false)
    @JoinColumn(name = "product_url_id")
    private ProductURL productURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ProductURL getProductURL() {
        return productURL;
    }

    public void setProductURL(ProductURL productURL) {
        this.productURL = productURL;
    }
}
