package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

import java.util.List;

public class UserDTO {
    private int id;
    private String emailAddress;
    private String password;
    private int isAdmin;
    private List<ProductDTO> trackedProduct;

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

    public List<ProductDTO> getTrackedProduct() {
        return trackedProduct;
    }

    public void setTrackedProduct(List<ProductDTO> trackedProduct) {
        this.trackedProduct = trackedProduct;
    }
}
