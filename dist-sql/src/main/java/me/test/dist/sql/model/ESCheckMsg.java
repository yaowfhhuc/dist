package me.test.dist.sql.model;

import java.math.BigDecimal;
import java.util.Date;

public class ESCheckMsg {
    private BigDecimal cmId;

    private BigDecimal basicId;

    private BigDecimal cmStatus;

    private String cmPerson;

    private Date cmSubmitTime;

    private Date cmPassTime;

    public BigDecimal getCmId() {
        return cmId;
    }

    public void setCmId(BigDecimal cmId) {
        this.cmId = cmId;
    }

    public BigDecimal getBasicId() {
        return basicId;
    }

    public void setBasicId(BigDecimal basicId) {
        this.basicId = basicId;
    }

    public BigDecimal getCmStatus() {
        return cmStatus;
    }

    public void setCmStatus(BigDecimal cmStatus) {
        this.cmStatus = cmStatus;
    }

    public String getCmPerson() {
        return cmPerson;
    }

    public void setCmPerson(String cmPerson) {
        this.cmPerson = cmPerson;
    }

    public Date getCmSubmitTime() {
        return cmSubmitTime;
    }

    public void setCmSubmitTime(Date cmSubmitTime) {
        this.cmSubmitTime = cmSubmitTime;
    }

    public Date getCmPassTime() {
        return cmPassTime;
    }

    public void setCmPassTime(Date cmPassTime) {
        this.cmPassTime = cmPassTime;
    }
}