package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

public class ProductDetailsDTO {
    private int id;
    private ProductDTO product;
    private String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
