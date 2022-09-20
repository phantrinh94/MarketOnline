package com.example.bkap.android.marketonline.DTO;

public class User {
    private int id;
    private String account;
    private String password;
    private String phone;
    private String fullname;
    private String position;
    private String lvrank;
    private String linkimglv;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String password) {
        this.password = password;
    }

    public User(int id, String account, String password, String phone, String fullname, String position, String lvrank, String linkimglv) {
        super();
        this.id = id;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
        this.position = position;
        this.lvrank = lvrank;
        this.linkimglv = linkimglv;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getLvrank() {
        return lvrank;
    }
    public void setLvrank(String lvrank) {
        this.lvrank = lvrank;
    }
    public String getLinkimglv() {
        return linkimglv;
    }
    public void setLinkimglv(String linkimglv) {
        this.linkimglv = linkimglv;
    }

}
