package me.test.dist.sql.model;

import java.math.BigDecimal;

public class ESOtherDeviceMsg {
    private BigDecimal odId;

    private String odEquipmentType;

    private String odUpsModel;

    private String odUpsCapacity;

    private String odConversionType;

    private String odCorotatingStatus;

    private BigDecimal basicId;

    private String odUpsStatus;

    private String odCorotatingModel;

    private BigDecimal cmId;

    public BigDecimal getOdId() {
        return odId;
    }

    public void setOdId(BigDecimal odId) {
        this.odId = odId;
    }

    public String getOdEquipmentType() {
        return odEquipmentType;
    }

    public void setOdEquipmentType(String odEquipmentType) {
        this.odEquipmentType = odEquipmentType;
    }

    public String getOdUpsModel() {
        return odUpsModel;
    }

    public void setOdUpsModel(String odUpsModel) {
        this.odUpsModel = odUpsModel;
    }

    public String getOdUpsCapacity() {
        return odUpsCapacity;
    }

    public void setOdUpsCapacity(String odUpsCapacity) {
        this.odUpsCapacity = odUpsCapacity;
    }

    public String getOdConversionType() {
        return odConversionType;
    }

    public void setOdConversionType(String odConversionType) {
        this.odConversionType = odConversionType;
    }

    public String getOdCorotatingStatus() {
        return odCorotatingStatus;
    }

    public void setOdCorotatingStatus(String odCorotatingStatus) {
        this.odCorotatingStatus = odCorotatingStatus;
    }

    public BigDecimal getBasicId() {
        return basicId;
    }

    public void setBasicId(BigDecimal basicId) {
        this.basicId = basicId;
    }

    public String getOdUpsStatus() {
        return odUpsStatus;
    }

    public void setOdUpsStatus(String odUpsStatus) {
        this.odUpsStatus = odUpsStatus;
    }

    public String getOdCorotatingModel() {
        return odCorotatingModel;
    }

    public void setOdCorotatingModel(String odCorotatingModel) {
        this.odCorotatingModel = odCorotatingModel;
    }

    public BigDecimal getCmId() {
        return cmId;
    }

    public void setCmId(BigDecimal cmId) {
        this.cmId = cmId;
    }
}