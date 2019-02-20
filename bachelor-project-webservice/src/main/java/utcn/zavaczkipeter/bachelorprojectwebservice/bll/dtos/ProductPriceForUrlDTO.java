package utcn.zavaczkipeter.bachelorprojectwebservice.bll.dtos;

public class ProductPriceForUrlDTO {
    private int id;
    private float price;
    private String currency;
    private int productUrlId;

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

    public int getProductUrlId() {
        return productUrlId;
    }

    public void setProductUrlId(int productUrlId) {
        this.productUrlId = productUrlId;
    }
}
