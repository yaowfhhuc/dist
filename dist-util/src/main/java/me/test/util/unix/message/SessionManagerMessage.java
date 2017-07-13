package me.test.util.unix.message;

import java.util.HashMap;


public class SessionManagerMessage extends AbstractMessage {
    private static final long serialVersionUID = 1L;
    private HashMap dataSessionMap;

    public HashMap getDataSessionMap() {
        return dataSessionMap;
    }

    public void setDataSessionMap(HashMap dataSessionMap) {
        this.dataSessionMap = dataSessionMap;
    }
}
