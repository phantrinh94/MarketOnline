package com.example.bkap.android.marketonline.DTO;

public class Messages {
    private int id;
    private String msg;

    public Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Messages(int id, String msg) {
        super();
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
