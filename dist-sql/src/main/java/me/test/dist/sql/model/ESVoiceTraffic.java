package me.test.dist.sql.model;

import java.math.BigDecimal;

public class ESVoiceTraffic {
    private BigDecimal vtId;

    private String vtDeviceType;

    private String vtModel;

    private String vtEquipmentCabinet;

    private String vtManageAddress;

    private String vtUserName;

    private String vtPassword;

    private BigDecimal basicId;

    private BigDecimal cmId;

    public BigDecimal getVtId() {
        return vtId;
    }

    public void setVtId(BigDecimal vtId) {
        this.vtId = vtId;
    }

    public String getVtDeviceType() {
        return vtDeviceType;
    }

    public void setVtDeviceType(String vtDeviceType) {
        this.vtDeviceType = vtDeviceType;
    }

    public String getVtModel() {
        return vtModel;
    }

    public void setVtModel(String vtModel) {
        this.vtModel = vtModel;
    }

    public String getVtEquipmentCabinet() {
        return vtEquipmentCabinet;
    }

    public void setVtEquipmentCabinet(String vtEquipmentCabinet) {
        this.vtEquipmentCabinet = vtEquipmentCabinet;
    }

    public String getVtManageAddress() {
        return vtManageAddress;
    }

    public void setVtManageAddress(String vtManageAddress) {
        this.vtManageAddress = vtManageAddress;
    }

    public String getVtUserName() {
        return vtUserName;
    }

    public void setVtUserName(String vtUserName) {
        this.vtUserName = vtUserName;
    }

    public String getVtPassword() {
        return vtPassword;
    }

    public void setVtPassword(String vtPassword) {
        this.vtPassword = vtPassword;
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