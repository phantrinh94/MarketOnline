package com.example.bkap.android.marketonline.DTO;

public class Category {
    private int id;
    private String cateName;
    private String imglink;
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Category(int id, String cateName, String imglink) {
        super();
        this.id = id;
        this.cateName = cateName;
        this.imglink = imglink;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCateName() {
        return cateName;
    }
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    public String getImglink() {
        return imglink;
    }
    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
