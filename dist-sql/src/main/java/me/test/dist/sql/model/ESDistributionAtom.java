package me.test.dist.sql.model;

import java.math.BigDecimal;

public class ESDistributionAtom {
    private BigDecimal daId;

    private BigDecimal dfId;

    private String daPort;

    private String daExtention;

    private String daLandline;

    private String daExplain;

    private BigDecimal dfSeqNum;

    public BigDecimal getDaId() {
        return daId;
    }

    public void setDaId(BigDecimal daId) {
        this.daId = daId;
    }

    public BigDecimal getDfId() {
        return dfId;
    }

    public void setDfId(BigDecimal dfId) {
        this.dfId = dfId;
    }

    public String getDaPort() {
        return daPort;
    }

    public void setDaPort(String daPort) {
        this.daPort = daPort;
    }

    public String getDaExtention() {
        return daExtention;
    }

    public void setDaExtention(String daExtention) {
        this.daExtention = daExtention;
    }

    public String getDaLandline() {
        return daLandline;
    }

    public void setDaLandline(String daLandline) {
        this.daLandline = daLandline;
    }

    public String getDaExplain() {
        return daExplain;
    }

    public void setDaExplain(String daExplain) {
        this.daExplain = daExplain;
    }

    public BigDecimal getDfSeqNum() {
        return dfSeqNum;
    }

    public void setDfSeqNum(BigDecimal dfSeqNum) {
        this.dfSeqNum = dfSeqNum;
    }
}