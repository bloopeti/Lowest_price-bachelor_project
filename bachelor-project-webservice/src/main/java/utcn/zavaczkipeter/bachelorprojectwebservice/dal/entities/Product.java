package utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @OneToOne(mappedBy = "product")
    private ProductDetails productDetails;
    @OneToMany(mappedBy = "product")
    private List<ProductUrl> urls;
    @ManyToMany(mappedBy = "favouriteProducts")
    private List<User> trackingUser;

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

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public List<ProductUrl> getUrls() {
        return urls;
    }

    public void setUrls(List<ProductUrl> urls) {
        this.urls = urls;
    }

    public List<User> getTrackingUser() {
        return trackingUser;
    }

    public void setTrackingUser(List<User> trackingUser) {
        this.trackingUser = trackingUser;
    }
}
