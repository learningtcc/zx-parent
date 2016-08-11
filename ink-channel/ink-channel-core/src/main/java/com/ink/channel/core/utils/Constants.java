package com.ink.channel.core.utils;

public class Constants {
        //定义从zk获取商户号常量
       public static final String MerchantSuffix="MerchantSuffix";
    /**
     * 渠道商户号后缀，用于拼接在从zk根据渠道商户号获取平台商户号
     */
    public static final String CHANNEL_MERCHANT_SUFFIX = "ChannelMerchantSuffix";
        
        public static String[][] Bank_Code_List = { { "工商银行", "1" }, { "农业银行", "2" }, { "中国银行", "3" }, { "建设银行", "4" },
                        { "招商银行", "5" }, { "兴业银行", "6" }, { "光大银行", "7" }, { "平安银行", "8" }, { "民生银行", "9" }, { "交通银行", "10" },{"北京银行","11"}, {"中信银行","12"}};
        public static String[][] Bank_Short_List = { { "ICBC", "1" }, { "ABC", "2" }, { "BOC", "3" }, { "CBC", "4" },
            { "CMB", "5" }, { "CIB", "6" }, { "CEB", "7" }, { "PAB", "8" }, { "CMBC", "9" }, { "BCM", "10" },{"BOB","11"}, {"CCB","12"}};
        public static String[][] QUICK_BANK_CODE = { { "中国工商银行", "01020000" }, { "中国农业银行", "01030000" },
                        { "中国银行", "01040000" }, { "中国建设银行", "01059999" }, { "招商银行", "03080000" },
                        { "兴业银行", "03090000" }, { "中国光大银行", "03030000" }, { "平安银行", "05105840" },
                        { "中国邮政储蓄银行", "01000000" }, { "中国民生银行", "03050000" }, { "广发银行", "03060000" } };

        public static String[][] REDIS_BANK_CODE = { { "中国工商银行", "ZGGSYH" }, { "中国农业银行", "ZGNYYH" },
                        { "中国银行", "ZGYH" }, { "中国建设银行", "ZGJSYH" }, { "招商银行", "ZSYH" }, { "兴业银行", "XYYH" },
                        { "光大银行", "GDYH" }, { "平安银行", "PAYH" }, { "中国邮政储蓄银行", "ZGYZCX" }, { "民生银行", "MSYH" },
                        { "广发银行", "GDFZYH" } };

    /**翼支付付款到银行卡*/
    /*public static String[][] BEST_PAY_CARD_BANK_LIST = {{ "866600", "CMBC" },
		 	{ "866800", "CGB" },{ "866200", "ICBC" },
            { "866300", "ABC" }, { "866100", "BOC" },
            { "866500", "CCB" },{ "866000", "PSBC" },
            { "866900", "CMB" },{ "867200", "CEB" },
            { "867600", "CIB" }, { "865700", "PAB" }, { "CITIC", "867400" }};*/
    /*public static String[][] CMBC_BANK_NAME_LIST = {{ "中国民生银行", "MSYH" },
		 	{ "广东发展银行", "GFYH" },{ "中国工商银行", "ZGGSYH" },
            { "中国农业银行", "NYYH" }, { "中国银行", "ZGYH" },
            { "中国建设银行", "JSYH" },{ "中国邮政储蓄银行", "YZYH" },
            { "招商银行", "ZSYH" },{ "中国光大银行", "GDYH" },
            { "兴业银行", "XYYH" }, { "平安银行", "PAYH" }, { "中信银行", "ZXYH" }};*/
    /*public static String[][] MAS_PAY_CARD_BANK_LIST = {{ "中国民生银行", "CMBC" },
		 	{ "广东发展银行", "GDB" },{ "中国工商银行", "ICBC" },
            { "中国农业银行", "ABC" }, { "中国银行", "BOC" },
            { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" },
            { "招商银行", "CMB" },{ "中国光大银行", "CEB" },
            { "兴业银行", "CIB" }, { "平安银行", "PAB" }};*/
    
    /**翼支付*/
    public static String[][] BEST_PAY_CARD_BANK_LIST = {{ "866200", "ZGGSYH" },
		 	{ "866300", "NYYH" },{ "866100", "ZGYH" },
            { "866500", "JSYH" }, { "866400", "JTYH" },
            { "866900", "ZSYH" },{ "867200", "GDYH" },
            { "866600", "MSYH" },{ "867600", "XYYH" },
            { "865800", "HXYH" }, { "867400", "ZXYH" },
            { "866000", "YZYH" }, { "865700", "PAYH" },
            { "866800", "GFYH" }, { "867100", "PFYH" }};
    /**招商银行,中国工商银行 ,中国农业银行,中国建设银行,
     * 中国银行,浦发银行,中国交通银行,中国民生银行 ,
     * 广东发展银行,中信银行,华夏银行,兴业银行,
     * 渤海银行,中国光大银行,东亚银行,平安银行,
     * 浙商银行,中国邮政储蓄银行*/
    public static String[][] CMBC_QUICK_BANK_NAME_LIST = {
    		{ "03080000", "ZSYH" },{ "01020000", "ZGGSYH" },{ "01030000", "NYYH" },{ "01059999", "JSYH" },
    		{ "01040000", "ZGYH" },{ "03100000", "PFYH" },{ "03010000", "JTYH" },{ "03050000", "MSYH" },
		 	{ "03060000", "GFYH" }, {"03020000", "ZXYH" },{"03040000", "HXYH" },{ "03090000", "XYYH" },
		 	{ "03170000", "BHYH" },{ "03030000", "GDYH" },{ "26150704", "DYYH" },{ "05105840", "PAYH" },
		 	{ "03160000", "ZJSYYH" },{ "01000000", "YZYH" }};
    
    /**快钱代收付*/
    public static String[][] MAS_PAY_CARD_BANK_LIST = {{ "民生银行", "MSYH" },
		 	{ "广发银行", "GFYH" },{ "工商银行", "ZGGSYH" },
            { "农业银行", "NYYH" }, { "中国银行", "ZGYH" },
            { "建设银行", "JSYH" },{ "邮政储蓄银行", "YZYH" },
            { "招商银行", "ZSYH" },{ "光大银行", "GDYH" },
            { "兴业银行", "XYYH" }, { "平安银行", "PAYH" }, { "中信银行", "ZXYH" },
            { "交通银行", "JTYH" }, { "华夏银行", "HXYH" }, { "浦发银行", "PFYH" },
            { "齐鲁银行", "QLYH" }, { "潍坊银行", "WFYH" }};
    /**快钱快捷*/
    public static String[][] MAS_QUICK_PAY_CARD_BANK_LIST = {{ "CMBC", "MSYH" },
		 	{ "CGB", "GFYH" },{ "ICBC", "ZGGSYH" },
            { "ABC", "NYYH" }, { "BOC", "ZGYH" },
            { "CCB", "JSYH" },{ "PSBC", "YZYH" },
            { "CMB", "ZSYH" },{ "CEB", "GDYH" },
            { "CIB", "XYYH" }, { "PAB", "PAYH" }, { "CITIC", "ZXYH" },
            { "BOCOM", "JTYH" }, { "HXB", "HXYH" }, { "SPDB", "PFYH" },
            { "QLB", "QLYH" }, { "WFB", "WFYH" }};
    
    
    
    
    
    /**民生*/
    public static String[][] CMBC_BANK_NAME_LIST = {{ "中国民生银行", "CMBC" },
		 	{ "广东发展银行", "CGB" },{ "中国工商银行", "ICBC" },
            { "中国农业银行", "ABC" }, { "中国银行", "BOC" },
            { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" },
            { "招商银行", "CMB" },{ "中国光大银行", "CEB" },
            { "兴业银行", "CIB" }, { "平安银行", "PAB" }, { "中信银行", "CITIC" }};
    /***宝付代付*/
    public static String[][] BF_PAY_CARD_BANK_LIST = {{"BEA东亚银行","BEA"},{ "招商银行", "CMB" },
    		{ "工商银行", "ICBC" },{ "建设银行", "CCB" },
    		{ "浦发银行", "SPDB" },{ "农业银行", "ABC" },
    		{ "民生银行", "CMBC" },{ "兴业银行", "CIB" },
    		{ "交通银行", "BCM" },{ "光大银行", "CEB" },
    		{ "中国银行", "BOC" },{"北京银行","BOB"},
    		{ "渤海银行", "CBHB" },{ "平安银行", "PAB" },
    		{ "广发银行", "CGB" },{ "邮政储蓄银行", "PSBC" },
    		{ "中信银行", "CITIC" },{ "华夏银行", "HXB" },
            { "浙商银行", "CZB" }, {"恒丰银行","EGB"},
            { "齐鲁银行", "QLB" }, { "潍坊银行", "WFCCB" }};
   
    /**宝付代收*/
    public static String[][] BF_PAY_ACCOUNT_BANK_LIST = {
    		{ "BOCOM", "BCM" },{ "SHB", "BOS" }};
    
    /**宝付绑卡*/
    /*public static String[][] BF_BIND_CARD_BANK_LIST = {
    		{ "CMBC", "MSYH" },{ "ICBC", "ZGGSYH" },
            { "ABC", "NYYH" }, { "BOC", "ZGYH" },
            { "CCB", "JSYH" },{ "PSBC", "YZYH" },
            { "CMB", "ZSYH" },{ "CEB", "GDYH" },
            { "CIB", "XYYH" }, { "PAB", "PAYH" }, { "CITIC", "ZXYH" },
            { "BOCOM", "JTYH" },{ "SPDB", "PFYH" }};*/
   
    /**网银在线快捷*/
    /*public static String[][] JD_QUICK_PAY_BANK_LIST = {{ "CMBC", "MSYH" },
		 	{ "CGB", "GFYH" },{ "ICBC", "ZGGSYH" },
            { "ABC", "NYYH" }, { "BOC", "ZGYH" },
            { "CCB", "JSYH" },{ "PSBC", "YZYH" },
            { "CMB", "ZSYH" },{ "CEB", "GDYH" },
            { "CIB", "XYYH" }, { "PAB", "PAYH" }, { "CITIC", "ZXYH" },
            { "BOCOM", "JTYH" }, { "HXB", "HXYH" }, { "SPDB", "PFYH" }};*/
    /*860000 中国人民银行866000 中国邮政储蓄银行866100 中国银行866200 中国工商银行866300 中国农业银行866500 中国建设银行866800 广发银行
	866400 交通银行866600 中国民生银行866900 招商银行867000 广州银行867100 上海浦发银行867200 光大银行867400 中信银行867600 兴业银行
	866700 广州农商865900 北京银行867319 东莞农信865800 华夏银行865600 深圳发展银行865700 平安银行867800 东莞农商867900 东莞银行
	867300 广东农信*/
}