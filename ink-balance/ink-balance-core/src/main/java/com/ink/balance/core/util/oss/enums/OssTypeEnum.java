package com.ink.balance.core.util.oss.enums;

public enum OssTypeEnum {

    TEST(1, "test", "测试用的");

    // 类型
    private int type;

    // 保存路径
    private String path;

    // 描述
    private String description;

    /**
     * 根据类型获取路径
     */
    public static String getPathByType(int type){
        for (OssTypeEnum typeEnum : OssTypeEnum.values()){
            if (typeEnum.getType() == type){
                return typeEnum.getPath();
            }
        }
        return "";
    }

    OssTypeEnum(int type, String path, String description) {
        this.type = type;
        this.path = path;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
