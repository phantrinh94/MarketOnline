package com.example.bkap.android.marketonline.DTO;

public class InvoiceDetail {
    private int idI;
    private int idP;
    private String productName;
    private int quantity;
    private float price;
    private String note;
    public InvoiceDetail() {
        super();
        // TODO Auto-generated constructor stub
    }
    public InvoiceDetail(int idI, int idP, int quantity, float price, String note) {
        super();
        this.idI = idI;
        this.idP = idP;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }

    public InvoiceDetail(int idP, int quantity, float price, String note) {
        super();
        this.idP = idP;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }



    public InvoiceDetail(int idI, int idP, String productName, int quantity, float price, String note) {
        super();
        this.idI = idI;
        this.idP = idP;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }
    public int getIdI() {
        return idI;
    }
    public void setIdI(int idI) {
        this.idI = idI;
    }
    public int getIdP() {
        return idP;
    }
    public void setIdP(int idP) {
        this.idP = idP;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
