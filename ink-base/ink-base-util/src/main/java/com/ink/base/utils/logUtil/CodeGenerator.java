/**
 *
 */
package com.ink.base.utils.logUtil;

import org.apache.commons.collections4.map.LRUMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author  wangxk
 * @date    2015年3月1日
 * @time    下午4:44:23
 * code  本机器唯一Code：
 * return
 * new       前缀(3位ip) + 当前时间差毫秒数(11位) + 序号(4位,每毫秒从0000开始)   18 位  3 年有效期
 * old       前缀(3位) +    当前时间毫秒数(13位) + 序号(6位,每毫秒从000000开始)  22 位
 *
 */
public class CodeGenerator {
    private static Long oldTime=1454515200000l; //"2016-02-04 00:00:00"
	//默认前缀(999)
	public static final String CODE_PREFIX_DEFAULT = "999";
    private static  CodeGenerator godeGenerator;
    private CodeGenerator(){
    }

    public static CodeGenerator instanceCodeGenerator(){
        if(godeGenerator==null)  godeGenerator=new CodeGenerator();
         return      godeGenerator;
    }

    private static Lock lock =new ReentrantLock();

    private static final LRUMap<String, Long> sequenceMap = new LRUMap<String, Long>(5000);


    /**
     * 根据给定前缀生成CODE码
     * @param prefix   ip位 长度为3
     */
    public  static String generateCode(final String prefix){
        lock.lock();
        try{
            String newPrefix = prefix;
            if(prefix == null || prefix.trim().length() != 3){
                newPrefix = CODE_PREFIX_DEFAULT;
            }else{
                newPrefix = prefix.trim();
            }
         //   currentTime-oldTime =99999999999   11 位  长度能用到 2019-04-06 09:46:39
                long currentTime = System.currentTimeMillis()-oldTime;
                String firstCode = newPrefix + currentTime;
                String code = "";
            if(sequenceMap.containsKey(firstCode)){
                sequenceMap.put(firstCode,  sequenceMap.get(firstCode)+1 );
                code = firstCode + String.format("%04d", sequenceMap.get(firstCode));
            }else{
                code = firstCode + "0000";
                sequenceMap.put(firstCode, 0l);
            }
                return code;
        }  finally {
            lock.unlock();
        }

    }
	public static void main(String[] args) {
       final CodeGenerator dd=CodeGenerator.instanceCodeGenerator();
        for(int i = 0; i < 10; i ++){
			final int ind = i;
			new Thread(){
				@Override
				public void run() {
					for(int j = 0; j < 1000; j ++){
                        long t1 = System.nanoTime();
                        System.out.println(ind+"IP  "+dd.generateCode("880"));
                        System.out.println("nanoTime="+(System.nanoTime()-t1));
					}
				}
			}.start();
		}

        //  ConcurrentHashMap <String, Long> sequenceMap = new ConcurrentHashMap<String, Long>();
        //    System.out.println("GG1450771130673000000".getBytes().length);
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now=      new Date();
//     // System.out.println(  sdf.format(now));
//     //   System.out.println( now.getTime());
//        Long oldTime=1454515200000l; //"2016-02-04 00:00:00"
//        Long nowTime=new Date().getTime();
//        //nowTime-oldTime =99999999999   11 位  长度能用到 2019-04-06 09:46:39
//           // new Date(oldTime);
//        System.out.println(nowTime);
//        System.out.println(oldTime);
//        System.out.println(nowTime-oldTime);
//        //99999999999
//        System.out.println(sdf.format(new Date(nowTime-10000000000l))) ;
//        System.out.println(sdf.format(new Date(oldTime+99999999999l))) ;
//        try {
//            System.out.println(sdf.parse("2016-02-04 00:00:00")) ;
//            System.out.println(sdf.parse("2016-02-04 00:00:00").getTime());
//           // System.out.println(new Date(now.getTime())) ;
//          //  System.out.println(new Date(oldTime)) ;
//         //   System.out.println(sdf.format(new Date(oldTime))) ;
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }




        //  System.out.println("END"+Runtime.getRuntime().freeMemory());
	}
}
