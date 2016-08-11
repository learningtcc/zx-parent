package com.ink.admin.common.constant;

/**
 * 系统映射类
 *
 * @author aiyungui
 * @create 2016-08-02-11:44
 **/
public enum  SysMapping {

    MONITOR("/monitor/",1006),CERT("/cert/",1010),MSGCENTOR("/msgcenter/",1008);

    private String sysName;
    private int sysCode;
    private SysMapping(String sysName, int sysCode) {

        this.sysName = sysName;
        this.sysCode = sysCode;
    }

    public static int getSysCode(String requestUrl){
        for(SysMapping sys : SysMapping.values()){
            if(requestUrl.contains(sys.getSysName())){
                return sys.getSysCode();
            }
        }
        return -1;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public int getSysCode() {
        return sysCode;
    }

    public void setSysCode(int sysCode) {
        this.sysCode = sysCode;
    }
}
