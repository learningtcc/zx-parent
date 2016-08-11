package com.ink.trade.api.enums;

public enum IdType {
    // 01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他
    IdentificationCard("身份证", "01"), HouseholdCard("户口本", "02"), MilitaryCard("军人身份证", "03"), Passport("护照", "06");
    private String name;
    private String value;

    private IdType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
