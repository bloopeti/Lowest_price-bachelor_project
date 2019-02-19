package utcn.zavaczkipeter.bachelorprojectwebservice.dal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String emailAddress;
    @Column
    private String password;
    @Column
    private int isAdmin;
    @ManyToMany
    @JoinTable
    private List<Product> trackedProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Product> getTrackedProduct() {
        return trackedProduct;
    }

    public void setTrackedProduct(List<Product> trackedProduct) {
        this.trackedProduct = trackedProduct;
    }
}
