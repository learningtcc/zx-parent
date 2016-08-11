package com.ink.trade.api.enums;

public enum TradeCurrency {
    CNY("人民币","CNY");
   private String name;
   private String value;
   private TradeCurrency(String name,String value){
       this.name=name;
       this.value=value;
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
