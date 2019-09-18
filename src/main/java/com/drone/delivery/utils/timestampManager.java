package com.drone.delivery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timestampManager {

    /**
     This Method calculates the time difference between order created and order delivered
     */
    public static long findDifference(String ordered, String delivered) throws ParseException {
        long res = 0L;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(ordered);
        Date date2 = format.parse(delivered);
        res = date2.getTime() - date1.getTime();
        return ((res/1000)/60);
    }

    /**
     This Method calculates the delivery start time based on the customer location distance from warehouse
     */

    public static String findStartTime(String ordertime, Double distance) throws ParseException {
        String res ="";
        String d = String.format("%.2f", distance);
        String[] dL =d.split("\\.");
        long a = Long.parseLong((dL[0]));
        float b = Float.parseFloat((dL[1]));
        int x =(int)Math.ceil((b*60)/100);
        String[] ordertimeL =ordertime.split("\\:");
        long h = Long.parseLong((ordertimeL[0]));
        long m = Long.parseLong((ordertimeL[1]));
        long s = Long.parseLong((ordertimeL[2]));
        if((x+s)>=60){
            int temp = (int)((b+s)/60);
            m=m+temp;
            s=s+(((x+s)/60-temp)*60);
        }else{
            s=s+x;
        }
        if((a+m)>=60){
            int tmp = (int)((a+m)/60);
            h=h+tmp;
            m=m+(((a+m)/60-tmp)*60);
        }else{
            m=m+a;
        }
        res= Long.toString(h)+":"+Long.toString(m)+":"+Long.toString(s);
        return res;
    }


}
