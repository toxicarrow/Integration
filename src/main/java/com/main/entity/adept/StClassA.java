package com.main.entity.adept;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stClass")
public class StClassA implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String cno;
    private String sno;
    private Integer grd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Integer getGrd() {
        return grd;
    }

    public void setGrd(Integer grd) {
        this.grd = grd;
    }
}
