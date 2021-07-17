package com.example.library.library.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Magazine {

    private Long pid;

    private String name;

    private Date dateOfCreate;

    private Long totalOfSheets;

    private Long totalOfMagazines;

    public Magazine(Long pid, String name, Date dateOfCreate, Long totalOfSheets, Long totalOfMagazines) {
        this.pid = pid;
        this.name = name;
        this.dateOfCreate = dateOfCreate;
        this.totalOfSheets = totalOfSheets;
        this.totalOfMagazines = totalOfMagazines;
    }

    public Magazine() {
    }

    public String dateHelper(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(dateOfCreate);
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Long getTotalOfSheets() {
        return totalOfSheets;
    }

    public void setTotalOfSheets(Long totalOfSheets) {
        this.totalOfSheets = totalOfSheets;
    }

    public Long getTotalOfMagazines() {
        return totalOfMagazines;
    }

    public void setTotalOfMagazines(Long totalOfMagazines) {
        this.totalOfMagazines = totalOfMagazines;
    }
}


