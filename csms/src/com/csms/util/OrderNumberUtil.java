package com.csms.util;

/**
 * Created by zhuxiaolei on 2017/6/6.
 */
public class OrderNumberUtil {
    private static int num=1;
    public String getInOrderNumber(String id){
        StringBuilder number = new StringBuilder();
        number.append("RK");
        number.append(DATEUtil.getDateNumber());
        number.append(id);
        number.append(num++);
        return number.toString();
    }

    public static String getOutOrderNumber(String id){
        StringBuilder number = new StringBuilder();
        number.append("CK");
        number.append(DATEUtil.getDateNumber());
        number.append(id);
        number.append(num++);
        return number.toString();
    }

    public static void main(String[] args) {
        OrderNumberUtil s = new OrderNumberUtil();
        System.out.println(s.getInOrderNumber("2"));
        OrderNumberUtil a = new OrderNumberUtil();
        System.out.println(s.getInOrderNumber("222"));
        System.out.println(a.getOutOrderNumber("222"));
    }

}
