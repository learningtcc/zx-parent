package com.ink.base.util;

import java.text.DecimalFormat;

/**
 * User: king
 * Date: 15-7-14
 * Time: 下午5:03
 *
 */
public class TableUtil {
    /**
     *
     * @param id  分表主键ID
     * @param tableCount  分表个数
     * @return  获取分表后缀
     * @throws NumberFormatException
     */
    public static String getTableSuffix(Object id,int tableCount) throws Exception {
        int suffixValue;
        int idValue=id.hashCode() ;
        try {
            suffixValue= idValue % tableCount ;
        } catch (Exception e) {
       //     logger.error( "The suffix of id is not number OR tableCount is not number " + id, e);
            throw e;
        }
       // return formatTableSuffix(suffixValue)   ;  //0000  格式 固定长度。
        return String.valueOf(suffixValue) ;
    }
    /**
     * 格式化表分区后缀 0000    最大支持9999 张表
     * @param suffixValue 分表值
     * @return
     */
    public static String formatTableSuffix(Long suffixValue) {
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(suffixValue);

    }

    public static void main (String dsdf[]){
        try {
            System.out.print(TableUtil.getTableSuffix(157,8));
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }



}
