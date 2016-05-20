package me.test.dist.sql.model;

import java.math.BigDecimal;
import java.util.Date;

public class ESDistributionFrame {
    private BigDecimal dfId;

    private Short dfSequenceNumber;

    private BigDecimal basicId;

    private String dfName;

    private Date dfCreateTime;

    private BigDecimal cmId;

    public BigDecimal getDfId() {
        return dfId;
    }

    public void setDfId(BigDecimal dfId) {
        this.dfId = dfId;
    }

    public Short getDfSequenceNumber() {
        return dfSequenceNumber;
    }

    public void setDfSequenceNumber(Short dfSequenceNumber) {
        this.dfSequenceNumber = dfSequenceNumber;
    }

    public BigDecimal getBasicId() {
        return basicId;
    }

    public void setBasicId(BigDecimal basicId) {
        this.basicId = basicId;
    }

    public String getDfName() {
        return dfName;
    }

    public void setDfName(String dfName) {
        this.dfName = dfName;
    }

    public Date getDfCreateTime() {
        return dfCreateTime;
    }

    public void setDfCreateTime(Date dfCreateTime) {
        this.dfCreateTime = dfCreateTime;
    }

    public BigDecimal getCmId() {
        return cmId;
    }

    public void setCmId(BigDecimal cmId) {
        this.cmId = cmId;
    }
}