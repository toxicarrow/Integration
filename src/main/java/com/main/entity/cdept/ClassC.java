package com.main.entity.cdept;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class ClassC {
    @Id
    @GeneratedValue
    private String cno;
    private String cnm;
    private String ctm;
    private String cpt;
    private String tec;
    private String pla;
    private String share;

    @Override
    public String toString() {
        return "ClassC{" +
                "cno='" + cno + '\'' +
                ", cnm='" + cnm + '\'' +
                ", ctm='" + ctm + '\'' +
                ", cpt='" + cpt + '\'' +
                ", tec='" + tec + '\'' +
                ", pla='" + pla + '\'' +
                ", share='" + share + '\'' +
                '}';
    }

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

    public String getCtm() {
        return ctm;
    }

    public void setCtm(String ctm) {
        this.ctm = ctm;
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
