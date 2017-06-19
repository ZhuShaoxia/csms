package com.csms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhuxiaolei on 2017/6/6.
 */
public class DATEUtil {
    public static String getDateFormatString(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }
    public static String getDateNumber(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }
    public static String getHHmmss(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyHHmmss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }
    public static String getDates(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(date);

    }

}
