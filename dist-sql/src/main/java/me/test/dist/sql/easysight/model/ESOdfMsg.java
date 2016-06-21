package me.test.dist.sql.easysight.model;

import java.math.BigDecimal;

public class ESOdfMsg extends ESOdfMsgKey {
    private BigDecimal fiberCoreSum;

    private Short reelNumber;

    private String frameNum;

    private BigDecimal basicId;

    private BigDecimal cmId;

    public BigDecimal getFiberCoreSum() {
        return fiberCoreSum;
    }

    public void setFiberCoreSum(BigDecimal fiberCoreSum) {
        this.fiberCoreSum = fiberCoreSum;
    }

    public Short getReelNumber() {
        return reelNumber;
    }

    public void setReelNumber(Short reelNumber) {
        this.reelNumber = reelNumber;
    }

    public String getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(String frameNum) {
        this.frameNum = frameNum;
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