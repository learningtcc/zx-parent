package com.ink.base;

import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdGenerator {
    private static Logger log = LoggerFactory.getLogger(IdGenerator.class);

  //  private static SerialNo no= new SerialNo() ;
    public static  Long genUUIDStr (){
        Long str = UUID.randomUUID().getLeastSignificantBits(); if (str <0 )
            str=-1*str;
        return str;
    }
    public static Long   getPickUpNo  (){
        Long str = UUID.randomUUID().getLeastSignificantBits(); if (str <0 )
            str=-1*str;
        return str;
    }
    

    /**
     *  获取指定长度数值随机数  注意
     *  @param length
     * @return
     */
    public static   Long getFixlengthRandomId(int length){

        //Long id= (long)((1+Math.random())*java.lang.Math.pow(10,length-1));
        
        Long id= null;
        try {
            //log.info("SerialNo.getCNO() ");
          //  id = Long.valueOf(no.getCNO());
        } catch (Exception e) {
        //    log.error("SerialNo.getCNO() error "+ e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return id;
    }


    public static void main(String dsf[]) {
        System.out.println("**************START");
        Long startTime=System.currentTimeMillis();
        for (int i=1; i<=320;i++ )  {
            String rt=String.valueOf( getFixlengthRandomId(9));
                System.out.println(4+rt );
        }
        Long endTime=System.currentTimeMillis();
        System.out.println("**************END,cost time"+(endTime-startTime));
    }



}
