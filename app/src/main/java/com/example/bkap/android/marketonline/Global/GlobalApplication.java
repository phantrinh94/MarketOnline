package com.example.bkap.android.marketonline.Global;

import android.app.Application;

import com.example.bkap.android.marketonline.DTO.Cart;
import com.example.bkap.android.marketonline.DTO.MyInvoice;
import com.example.bkap.android.marketonline.DTO.InvoiceDetail;
import com.example.bkap.android.marketonline.DTO.User;

public class GlobalApplication extends Application {

    private User gUser;
    private Cart gCart;

    public User getgUser() {
        return gUser;
    }

    public void setgUser(User gUser) {
        this.gUser = gUser;
        // Khới tạo đối tượng giỏ hàng
        gCart = new Cart();
    }


    public void setCustomInfo(String address, String phone,float totalMoney) {
        gCart.setIdU(gUser.getId());// Id người dùng
        gCart.setAddress(address);
        gCart.setPhone(phone);
        gCart.setAmount(totalMoney);
    }

    public Cart getgCart() {
        return gCart;
    }

    public void updateCart(int idPrd, int count, float price) {
        boolean isExits = false;
        for (int i = 0; i < gCart.getLstInvoiceDetail().size(); i++) {
            int idCurrPrd = gCart.getLstInvoiceDetail().get(i).getIdP();
            if (idPrd == idCurrPrd) {
                isExits = true;
                int currQuantity = gCart.getLstInvoiceDetail().get(i).getQuantity();
                gCart.getLstInvoiceDetail().get(i).setQuantity(currQuantity + count);
            }
        }
        if (isExits == false) {
            gCart.getLstInvoiceDetail().add(new InvoiceDetail(idPrd, count, price, ""));
        }
    }
}
