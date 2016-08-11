package com.ink.trade.api.enums;

public enum CardType {
    DebitCard("借记卡","0"),/**借记卡**/
    CreditCard("贷记卡","1");/**贷记卡**/
    private String value;
    private String name;
    private CardType(String name,String value){
        this.name=name;
        this.value=value;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
