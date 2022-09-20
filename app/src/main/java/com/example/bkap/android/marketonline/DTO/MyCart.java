package com.example.bkap.android.marketonline.DTO;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myCart")
public class MyCart {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idP;
    private String nameProduct;
    private String expiryProduct;
    private float priceProduct;
    private int discountProduct;
    private String contentProduct;
    private String linkImg;
    private int quantity;

    public MyCart() {
    }

    public MyCart(int idP, String nameProduct, String expiryProduct, float priceProduct, String contentProduct, String linkImg, int quantity) {
        this.idP = idP;
        this.nameProduct = nameProduct;
        this.expiryProduct = expiryProduct;
        this.priceProduct = priceProduct;
        this.contentProduct = contentProduct;
        this.linkImg = linkImg;
        this.quantity = quantity;
    }

    public MyCart(int idP, String nameProduct, String expiryProduct, float priceProduct, int discountProduct, String contentProduct, String linkImg, int quantity) {
        this.idP = idP;
        this.nameProduct = nameProduct;
        this.expiryProduct = expiryProduct;
        this.priceProduct = priceProduct;
        this.discountProduct = discountProduct;
        this.contentProduct = contentProduct;
        this.linkImg = linkImg;
        this.quantity = quantity;
    }

    public MyCart(int id, int idP, String nameProduct, String expiryProduct, float priceProduct, int discountProduct, String contentProduct, String linkImg, int quantity) {
        this.id = id;
        this.idP = idP;
        this.nameProduct = nameProduct;
        this.expiryProduct = expiryProduct;
        this.priceProduct = priceProduct;
        this.discountProduct = discountProduct;
        this.contentProduct = contentProduct;
        this.linkImg = linkImg;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getExpiryProduct() {
        return expiryProduct;
    }

    public void setExpiryProduct(String expiryProduct) {
        this.expiryProduct = expiryProduct;
    }

    public float getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getDiscountProduct() {
        return discountProduct;
    }

    public void setDiscountProduct(int discountProduct) {
        this.discountProduct = discountProduct;
    }

    public String getContentProduct() {
        return contentProduct;
    }

    public void setContentProduct(String contentProduct) {
        this.contentProduct = contentProduct;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
