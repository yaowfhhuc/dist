package me.test.dist.sql.easysight.model;

import java.math.BigDecimal;

public class ESCustomerMsg {
    private BigDecimal basicId;

    private String bmCustName;

    private String bmInsertAddress;

    private String bmLinkman;

    private String bmBusinessType;

    private String bmImg;

    private BigDecimal bmBusnissLevel;

    private BigDecimal bmRouteMsg;

    private String bmMaint;

    public BigDecimal getBasicId() {
        return basicId;
    }

    public void setBasicId(BigDecimal basicId) {
        this.basicId = basicId;
    }

    public String getBmCustName() {
        return bmCustName;
    }

    public void setBmCustName(String bmCustName) {
        this.bmCustName = bmCustName;
    }

    public String getBmInsertAddress() {
        return bmInsertAddress;
    }

    public void setBmInsertAddress(String bmInsertAddress) {
        this.bmInsertAddress = bmInsertAddress;
    }

    public String getBmLinkman() {
        return bmLinkman;
    }

    public void setBmLinkman(String bmLinkman) {
        this.bmLinkman = bmLinkman;
    }

    public String getBmBusinessType() {
        return bmBusinessType;
    }

    public void setBmBusinessType(String bmBusinessType) {
        this.bmBusinessType = bmBusinessType;
    }

    public String getBmImg() {
        return bmImg;
    }

    public void setBmImg(String bmImg) {
        this.bmImg = bmImg;
    }

    public BigDecimal getBmBusnissLevel() {
        return bmBusnissLevel;
    }

    public void setBmBusnissLevel(BigDecimal bmBusnissLevel) {
        this.bmBusnissLevel = bmBusnissLevel;
    }

    public BigDecimal getBmRouteMsg() {
        return bmRouteMsg;
    }

    public void setBmRouteMsg(BigDecimal bmRouteMsg) {
        this.bmRouteMsg = bmRouteMsg;
    }

    public String getBmMaint() {
        return bmMaint;
    }

    public void setBmMaint(String bmMaint) {
        this.bmMaint = bmMaint;
    }
}