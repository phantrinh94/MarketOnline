package com.example.bkap.android.marketonline.DTO;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private int categoryID;
    private String productName;
    private float priceProduct;
    private int discount;
    private String expiry;
    private String content;
    private String imglink;
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Product(int id, int categoryID, String productName, float priceProduct, int discount, String expiry,String content,
                   String imglink) {
        super();
        this.id = id;
        this.categoryID = categoryID;
        this.productName = productName;
        this.priceProduct = priceProduct;
        this.discount = discount;
        this.expiry = expiry;
        this.content = content;
        this.imglink = imglink;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public float getPriceProduct() {
        return priceProduct;
    }
    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String getExpiry() {
        return expiry;
    }
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImglink() {
        return imglink;
    }
    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
