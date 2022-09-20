package com.example.bkap.android.marketonline.DTO;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int idU;
    private String address;
    private String phone;
    private float totalMoney;
    private List<InvoiceDetail> lstInvoiceDetail = new ArrayList<>();

    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Cart(int idU, String address, String phone, float totalMoney, List<InvoiceDetail> lstInvoiceDetail) {
        super();
        this.idU = idU;
        this.address = address;
        this.phone = phone;
        this.totalMoney = totalMoney;
        this.lstInvoiceDetail = lstInvoiceDetail;
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

    public float getAmount() {
        return totalMoney;
    }

    public void setAmount(float amount) {
        this.totalMoney = amount;
    }

    public List<InvoiceDetail> getLstInvoiceDetail() {
        return lstInvoiceDetail;
    }

    public void setLstInvoiceDetail(List<InvoiceDetail> lstInvoiceDetail) {
        this.lstInvoiceDetail = lstInvoiceDetail;
    }

}
