package com.yinker.base;//package com.star.payChannel.data.common.base;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.text.DecimalFormat;
//import java.util.*;
//
///**
// * 从发号器项目拷贝而来直接使用.
// * <p/>
// * svn:
// */
//@Service
//public class SerialNo {
//
//    private Logger logger = Logger.getLogger(SerialNo.class);
//
//    /**
//     * 2014-1-1 日期的起始时间 daySort=0
//     */
//    private static long BEGIN_TIME = 1388505600000l;
//
//    private static String day = null;
//    // private static long CUR_TIME_SORT = 0;
//    public static List<Integer> TIME_SORT_RANDOM = new ArrayList<Integer>();
//    // private Set<Integer> USED_RANDOM = new HashSet<Integer>();
//
//    private static String THREE_DEFAULT_STRING = "000";
//    private static String FOUR_DEFAULT_STRING = "0000";
//    private static String FIVE_DEFAULT_STRING = "00000";
//    // private static String SEVEN_DEFAULT_STRING = "0000000";
//
//    private static String localhostIP = IpUtils.getLocalAddress();
//
//    private static Map<String, SerialNoProperty> propertyMap;
//    Map<String, Integer> ipPortMap = new HashMap<String, Integer>();
//
//    {
//        //   ipPortMap.put("10.77.134.16:10046", 1);// 废弃
//        //    ipPortMap.put("10.77.135.215:10046", 1);// 废弃
//
//        //   ipPortMap.put("192.168.2.19:10046", 91); // 废弃
////        ipPortMap.put("192.168.2.75:10046", 91); // 废弃
//        // 廊坊机房
////        ipPortMap.put("10.77.152.185:10073", 2);
////        ipPortMap.put("10.77.152.184:10073", 3);
//        //成都机房
//        ipPortMap.put("10.209.11.64:10073", 1);
//        ipPortMap.put("10.209.11.34:10073", 2);
//        ipPortMap.put("10.209.10.64:10073", 3);
//        ipPortMap.put("10.209.10.34:10073", 4);
//
//        ipPortMap.put("10.209.10.31:10073", 5);
//        ipPortMap.put("10.209.10.61:10073", 7);
//        ipPortMap.put("10.209.11.31:10073", 9);
//        propertyMap = new HashMap<String, SerialNoProperty>();
//        SerialNoProperty serialNoProperty = null;
//        StringBuilder stringBuilder = null;
//        StringBuilder maxNumBuilder = null;
//
//        serialNoProperty = new SerialNoProperty();
//        // 获取随机数长度
//        int length = 9;
//        // 生成format字符串
//        stringBuilder = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            stringBuilder.append("0");
//        }
//        String defaultString = stringBuilder.toString();
//        serialNoProperty.setDefaultString(defaultString);
//        // 根据随机数长度获取随机数最大值
//        maxNumBuilder = new StringBuilder();
//        maxNumBuilder.append(1).append(defaultString);
//        int maxNum = Integer.valueOf(maxNumBuilder.toString());
//        serialNoProperty.setMaxNum(maxNum);
//        // 生成已使用随机数set集合
//        Set<Integer> usedRandom = new HashSet<Integer>();
//        serialNoProperty.setUsedRandom(usedRandom);
//        propertyMap.put("CertificateNo", serialNoProperty);
//    }
//
//    /**
//     * 获取IP序列
//     *
//     * @return
//     */
//    private String getIpSort() {
//        String ip = localhostIP;
//        int port = 10073;
//        String ipAndPort = ip + ":" + port;
//        Integer ipSort = ipPortMap.get(ipAndPort);
//        if (ipSort == null)
//            return "8";
//        return String.valueOf(ipSort);
//    }
//
//    ThreadLocal<DecimalFormat> dateSort = new ThreadLocal<DecimalFormat>() {
//        @Override
//        public DecimalFormat initialValue() {
//            long daySort = (System.currentTimeMillis() - BEGIN_TIME) / 1000 / 60 / 60 / 24;
//            DecimalFormat df = null;
//            if (daySort < 1000) {
//                df = new DecimalFormat(THREE_DEFAULT_STRING);
//            } else if (1000 <= daySort && daySort < 10000) {
//                df = new DecimalFormat(FOUR_DEFAULT_STRING);
//            } else if (10000 <= daySort) {
//                df = new DecimalFormat(FIVE_DEFAULT_STRING);
//            }
//            return df;
//        }
//    };
//
//    /**
//     * 获取距离2014-1-1天数
//     *
//     * @param currentTime
//     * @return
//     */
//    public String getDateSort(long currentTime) {
//        long daySort = (currentTime - BEGIN_TIME) / 1000 / 60 / 60 / 24;
//        return dateSort.get().format(daySort);
//    }
//
//    /**
//     * 获取当前90秒分配的随机数
//     *
//     * @param daySort
//     * @throws java.io.FileNotFoundException
//     */
//    public String getTimeSort(String daySort, long currentTime) throws Exception {
//        if (day == null) {
//            /**
//             * 重启场景 day == 0 需要加载当天已使用的分钟序列
//             */
//            day = daySort;
//            TIME_SORT_RANDOM = new ArrayList<Integer>();
//            File file = new File(String.valueOf(day));
//            if (file.exists()) {
//                FileInputStream fis = new FileInputStream(file);
//                InputStreamReader isr = new InputStreamReader(fis);
//                BufferedReader br = new BufferedReader(isr);
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    TIME_SORT_RANDOM.add(Integer.valueOf(line));
//                }
//                br.close();
//            } else {
//                TIME_SORT_RANDOM = createMiniteSortList();
//            }
//        }
//
//        if (!daySort.equals(day)) {
//            /**
//             * 日期变更场景 日期已变 清空dayUsedRandom
//             */
//            day = daySort;
//
//            /**
//             * 重新生成1.5分钟的序列
//             */
//            TIME_SORT_RANDOM = createMiniteSortList();
//        }
//
//        /**
//         * 获取当前分钟序列
//         */
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(currentTime);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        long baseTimeInMillis = calendar.getTimeInMillis();
//        long second = (currentTime - baseTimeInMillis) / 1000;
//        long index = second % 90;
//        int value = TIME_SORT_RANDOM.get((int) index);
//        DecimalFormat df = new DecimalFormat(THREE_DEFAULT_STRING);
//        return df.format(value);
//    }
//
//    private List<Integer> createMiniteSortList() throws Exception {
//        /**
//         * 重新生成1.5分钟的序列
//         */
//        List<Integer> minuteSortRandom = new ArrayList<Integer>();
//        List<String> tempList = new ArrayList<String>();
//        for (int i = 0; i < 1000; i++) {
//            tempList.add(i + "");
//        }
//        while (tempList.size() != 0) {
//            Random indexRandom = new Random();
//            int index = indexRandom.nextInt(tempList.size());
//            minuteSortRandom.add(Integer.valueOf(tempList.get(index)));
//            tempList.remove(index);
//        }
//        FileOutputStream fos = new FileOutputStream(day);
//        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
//        BufferedWriter bw = new BufferedWriter(osw);
//        for (int key : minuteSortRandom) {
//            bw.write(String.valueOf(key));
//            bw.newLine();
//        }
//        bw.flush();
//        bw.close();
//        return minuteSortRandom;
//    }
//
//    /**
//     * 获取当成的随机数
//     *
//     * @param curTime
//     * @param code
//     * @return
//     */
//    public String getRandomSort(long curTime, String code) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(curTime);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        long baseTimeInMillis = calendar.getTimeInMillis();
//        long second = (curTime - baseTimeInMillis) / 1000;
//        long timeSort = second % 90;
//
//        SerialNoProperty serialNoProperty = propertyMap.get(code);
//
//        long curTimeSort = serialNoProperty.getCurTimeSort();
//        Set<Integer> usedRandom = serialNoProperty.getUsedRandom();
//        String defaultString = serialNoProperty.getDefaultString();
//        int maxNum = serialNoProperty.getMaxNum();
//
//        if (curTimeSort == 0 || curTimeSort != timeSort) {
//            curTimeSort = timeSort;
//            usedRandom = new HashSet<Integer>();
//        }
//
//        int value = 0;
//        Random random = new Random();
//        int nextInt = random.nextInt(maxNum);
//        if (nextInt == 0) {
//            nextInt = 1;
//        }
//        int upInt = nextInt;
//        int downInt = nextInt;
//        while (value == 0) {
//            /**
//             * 上行判断
//             */
//            if (upInt != maxNum && !usedRandom.contains(upInt)) {
//                value = upInt;
//                break;
//            } else if (upInt < maxNum) {
//                upInt++;
//            }
//
//            /**
//             * 下行判断
//             */
//            if (downInt != 0 && !usedRandom.contains(downInt)) {
//                value = downInt;
//                break;
//            } else if (downInt > 0) {
//                downInt--;
//            }
//
//            if (downInt == 0 && upInt == maxNum) {
//                break;
//            }
//        }
//
//        /**
//         * 已经超出最大值
//         */
//        if (value == 0) {
//            throw new RuntimeException("当前时间周期内已经超出取号的最大值");
//        }
//
//        usedRandom.add(value);
//
//        serialNoProperty.setCurTimeSort(curTimeSort);
//        serialNoProperty.setUsedRandom(usedRandom);
//
//        DecimalFormat df = new DecimalFormat(defaultString);
//
//        return df.format(value);
//    }
//
//    public String getSerialNo(String code) throws Exception {
//        long currentTime = System.currentTimeMillis();
//        // 获取ip:port对应value(1位)
//        String ipSort = getIpSort();
//        // System.out.println("ipSort:"+ipSort);
//        // 获取距离201400101天数(3位)
//        //String daySortString = getDateSort(currentTime);
//        //  System.out.println("daySortString:"+daySortString);
//        // 获取距离90秒随机数 (3位)
//        //String timeSort = getTimeSort(daySortString, currentTime);
//        // 获取不重复指定位数随机数 9wei
//        String randomSort = getRandomSort(currentTime, code);
//        // System.out.println("randomSort:"+randomSort);
//        StringBuilder noStringbuilder = new StringBuilder();
//        noStringbuilder.append(ipSort).append(randomSort);
//        String no = noStringbuilder.toString();// SecureHelper.encrypt(noStringbuilder.toString());
//        if (no.charAt(0) == '0') {
//            no = ipSort + no.substring(1);
//        }
//
//        return no;
//    }
//
//    /**
//     * 生成券码
//     *
//     * @return
//     * @throws Exception
//     */
//    public  String getCNO() throws Exception {
//
//        return getSerialNo("CertificateNo");
//    }
//
//    public List<String> createCertificateNos(Integer createType, int count) {
//        List<String> certificateNos = new ArrayList<String>(count);
//        while (certificateNos.size() < count) {
//            try {
//                String cno = getCNO();
//                if (cno != null) {
//                    certificateNos.add(cno);
//                }
//            } catch (Exception e) {
//                logger.error("createCertificateNos error ");
//            }
//        }
//        return certificateNos;
//    }
//
//    public String createCertificateNo(Integer createType) {
//        return createCertificateNos(createType, 1).get(0);
//    }
//
//    public static void main (String asd[]){
//        try {
//            System.out.print(new SerialNo().getCNO());
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }
//
//
//}
