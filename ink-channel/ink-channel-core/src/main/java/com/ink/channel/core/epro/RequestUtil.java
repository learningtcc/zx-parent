package com.ink.channel.core.epro;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RequestUtil {
    /**
     * ��������
     * @return
     */
    public static String getRequestId()
    {
        SimpleDateFormat dateFormat     = new SimpleDateFormat("yyMMdd_HHmmssSSS");
        return "TZTBINDBANKCARD" + dateFormat.format(new Date());
    }

}
