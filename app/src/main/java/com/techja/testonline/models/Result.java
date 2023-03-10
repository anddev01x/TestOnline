package com.techja.testonline.models;

public class Result {
    private int id = 0;
    private String date;
    private String result;
    private String monHoc;

    public Result() {
    }

    public Result(String date, String result, String monHoc) {
        this.date = date;
        this.result = result;
        this.monHoc = monHoc;
    }

    public Result(int id, String date, String result, String monHoc) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.monHoc = monHoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }
}
