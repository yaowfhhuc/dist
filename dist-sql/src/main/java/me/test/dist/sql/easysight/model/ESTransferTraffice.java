package me.test.dist.sql.easysight.model;

import java.math.BigDecimal;

public class ESTransferTraffice {
    private BigDecimal ttId;

    private String deviceType;

    private String model;

    private String equipmentCabinet;

    private String boardsSlotPosition;

    private String boardsType;

    private String boardsFunction;

    private BigDecimal basicId;

    private BigDecimal cmId;

    public BigDecimal getTtId() {
        return ttId;
    }

    public void setTtId(BigDecimal ttId) {
        this.ttId = ttId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEquipmentCabinet() {
        return equipmentCabinet;
    }

    public void setEquipmentCabinet(String equipmentCabinet) {
        this.equipmentCabinet = equipmentCabinet;
    }

    public String getBoardsSlotPosition() {
        return boardsSlotPosition;
    }

    public void setBoardsSlotPosition(String boardsSlotPosition) {
        this.boardsSlotPosition = boardsSlotPosition;
    }

    public String getBoardsType() {
        return boardsType;
    }

    public void setBoardsType(String boardsType) {
        this.boardsType = boardsType;
    }

    public String getBoardsFunction() {
        return boardsFunction;
    }

    public void setBoardsFunction(String boardsFunction) {
        this.boardsFunction = boardsFunction;
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