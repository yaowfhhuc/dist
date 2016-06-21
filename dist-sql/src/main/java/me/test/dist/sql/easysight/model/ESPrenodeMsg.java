package me.test.dist.sql.easysight.model;

import java.math.BigDecimal;

public class ESPrenodeMsg {
    private BigDecimal pmId;

    private String pmRouteNum;

    private String pmName;

    private String pmLocation;

    private String pmJumpFiberMsg;

    private BigDecimal basicId;

    private BigDecimal cmId;

    public BigDecimal getPmId() {
        return pmId;
    }

    public void setPmId(BigDecimal pmId) {
        this.pmId = pmId;
    }

    public String getPmRouteNum() {
        return pmRouteNum;
    }

    public void setPmRouteNum(String pmRouteNum) {
        this.pmRouteNum = pmRouteNum;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmLocation() {
        return pmLocation;
    }

    public void setPmLocation(String pmLocation) {
        this.pmLocation = pmLocation;
    }

    public String getPmJumpFiberMsg() {
        return pmJumpFiberMsg;
    }

    public void setPmJumpFiberMsg(String pmJumpFiberMsg) {
        this.pmJumpFiberMsg = pmJumpFiberMsg;
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