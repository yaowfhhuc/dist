package me.test.dist.sql.model;

import java.math.BigDecimal;

public class ESOdfMsgKey {
    private BigDecimal odfId;

    private String opticalCableDirection;

    public BigDecimal getOdfId() {
        return odfId;
    }

    public void setOdfId(BigDecimal odfId) {
        this.odfId = odfId;
    }

    public String getOpticalCableDirection() {
        return opticalCableDirection;
    }

    public void setOpticalCableDirection(String opticalCableDirection) {
        this.opticalCableDirection = opticalCableDirection;
    }
}