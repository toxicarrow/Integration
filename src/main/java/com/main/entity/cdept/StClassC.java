package com.main.entity.cdept;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.PipedReader;

@Entity
@Table(name = "stClass")
public class StClassC {
    @Id
    @GeneratedValue
    private int id;
    private String cno;
    private String sno;
    private Integer grd;

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

    @Override
    public String toString() {
        return "StClassC{" +
                "id=" + id +
                ", cno='" + cno + '\'' +
                ", sno='" + sno + '\'' +
                ", grd=" + grd +
                '}';
    }
}
