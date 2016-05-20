package me.test.dist.sql.model;

import java.math.BigDecimal;

public class ESDataTraffic {
    private BigDecimal dtId;

    private BigDecimal dtDeviceType;

    private String dtModel;

    private String dtEquipmentCabinet;

    private String dtManageAddress;

    private String dtUserName;

    private String dtPassword;

    private BigDecimal basicId;

    private BigDecimal cmId;

    public BigDecimal getDtId() {
        return dtId;
    }

    public void setDtId(BigDecimal dtId) {
        this.dtId = dtId;
    }

    public BigDecimal getDtDeviceType() {
        return dtDeviceType;
    }

    public void setDtDeviceType(BigDecimal dtDeviceType) {
        this.dtDeviceType = dtDeviceType;
    }

    public String getDtModel() {
        return dtModel;
    }

    public void setDtModel(String dtModel) {
        this.dtModel = dtModel;
    }

    public String getDtEquipmentCabinet() {
        return dtEquipmentCabinet;
    }

    public void setDtEquipmentCabinet(String dtEquipmentCabinet) {
        this.dtEquipmentCabinet = dtEquipmentCabinet;
    }

    public String getDtManageAddress() {
        return dtManageAddress;
    }

    public void setDtManageAddress(String dtManageAddress) {
        this.dtManageAddress = dtManageAddress;
    }

    public String getDtUserName() {
        return dtUserName;
    }

    public void setDtUserName(String dtUserName) {
        this.dtUserName = dtUserName;
    }

    public String getDtPassword() {
        return dtPassword;
    }

    public void setDtPassword(String dtPassword) {
        this.dtPassword = dtPassword;
    }

    public BigDecimal getBasicId() {
        return basicId;
    }

    public void setBasicId(BigDecimal basicId) {
        this.basicId = basicId;
    }

    public BigDecimal getCmId() {
        return cmId;
    }

    public void setCmId(BigDecimal cmId) {
        this.cmId = cmId;
    }
}