package com.main.entity.adept;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "class")
public class ClassA implements Serializable{
    @Id
    private String cno;
    private String cnm;
    private String cpt;//学分
    private String tec;
    private String pla;
    private String share;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCnm() {
        return cnm;
    }

    public void setCnm(String cnm) {
        this.cnm = cnm;
    }

    public String getCpt() {
        return cpt;
    }

    public void setCpt(String cpt) {
        this.cpt = cpt;
    }

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }

    public String getPla() {
        return pla;
    }

    public void setPla(String pla) {
        this.pla = pla;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }
}
