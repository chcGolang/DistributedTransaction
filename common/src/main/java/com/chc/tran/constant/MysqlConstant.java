package com.chc.tran.constant;


/**
 * @author chc
 * @create 2019-01-27 12:24
 **/
public class MysqlConstant {
    public final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    public final static String HOST = "192.168.50.115";
    public final static Integer PROT = 3306;
    public final static String DB_USER = "root";
    public final static String DB_PASSWORD = "123456";
    private final static String DB_CONNECTION = "jdbc:mysql://%s:%d/%s?charset=utf8mb4&characterEncoding=utf-8&useSSL=false";

    public static String getConnectionString(String dbName){
        return String.format(DB_CONNECTION,HOST,PROT,dbName);
    }

    public static void main(String[] args) {
        System.out.println(getConnectionString("test"));
    }
}
