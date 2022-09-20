package com.example.bkap.android.marketonline.DTO;

import java.util.Date;

public class MyInvoice {
    private int id;
    private int idU;
    private String address;
    private String phone;
    private float totalMoney;
    private Date createDate;
    public MyInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }
    public MyInvoice(int id, int idU, String address, String phone, float totalMoney) {
        super();
        this.id = id;
        this.idU = idU;
        this.address = address;
        this.phone = phone;
        this.totalMoney = totalMoney;
    }


    public MyInvoice(int idU, String address, String phone, float totalMoney) {
        super();
        this.idU = idU;
        this.address = address;
        this.phone = phone;
        this.totalMoney = totalMoney;
    }


    public MyInvoice(int id, int idU, String address, String phone, float totalMoney, Date createDate) {
        super();
        this.id = id;
        this.idU = idU;
        this.address = address;
        this.phone = phone;
        this.totalMoney = totalMoney;
        this.createDate = createDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdU() {
        return idU;
    }
    public void setIdU(int idU) {
        this.idU = idU;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public float getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
