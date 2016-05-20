package me.test.dist.sql.model;

import java.math.BigDecimal;

public class ESTypeFuncPort {
    private BigDecimal tfpId;

    private String tfpName;

    private String tfpFunc;

    private String tfpType;

    private String tfpParentType;

    private BigDecimal tfpParentId;

    public BigDecimal getTfpId() {
        return tfpId;
    }

    public void setTfpId(BigDecimal tfpId) {
        this.tfpId = tfpId;
    }

    public String getTfpName() {
        return tfpName;
    }

    public void setTfpName(String tfpName) {
        this.tfpName = tfpName;
    }

    public String getTfpFunc() {
        return tfpFunc;
    }

    public void setTfpFunc(String tfpFunc) {
        this.tfpFunc = tfpFunc;
    }

    public String getTfpType() {
        return tfpType;
    }

    public void setTfpType(String tfpType) {
        this.tfpType = tfpType;
    }

    public String getTfpParentType() {
        return tfpParentType;
    }

    public void setTfpParentType(String tfpParentType) {
        this.tfpParentType = tfpParentType;
    }

    public BigDecimal getTfpParentId() {
        return tfpParentId;
    }

    public void setTfpParentId(BigDecimal tfpParentId) {
        this.tfpParentId = tfpParentId;
    }
}