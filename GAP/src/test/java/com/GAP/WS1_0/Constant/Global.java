package com.GAP.WS1_0.Constant;

import java.io.File;

public class Global {

    /**
     * Local conf
     */
	public static String LocalPath = System.getProperty("user.dir") +  File.separator+ "data" + File.separator ;
    public static String LocalTempPath = System.getProperty("user.dir") +   File.separator+ "data" + File.separator ;    
    public static String dataInputFileName = "input.csv";    
    public static String dataInputFileName1 = "input1.csv";        
    public static String dataInputFileName2 = "input2.csv";
    public static String dataInputFileName3 = "input3.csv";
    public static String sampleFile = LocalTempPath + "temp" + File.separator+  "sample.pdf";
    public static String masterFilePath = LocalTempPath + "master" + File.separator;
    public static String adminUNM = "shaswat.kar@girikon.com.ge.uat";
    public static String adminPass = "girikon@123";
    public static String salesUNM = "jain@girikon.com.ge.uat";
    public static String salesPass = "girikon#1234";    
    public static String sscUNM = "zuheab.jamil@girikon.com.ge.ssc.uat";
    public static String sscPass = "task$1234";
    public static String brazilUNM = "piyush.jain.brazil@girikon.org.uat";
    public static String brazilPass = "time$12345";
    public static String CCOpAdminUNM = "toc.operations@gmail.com.ge.uat";
    public static String CCOpAdminPass = "Girikon#2019#";
    public static String CCExhibitorUNM = "sushant.binakar@girikon.com.community.uat";
    public static String CCExhibitorPass = "Girikon!2022";
    
    /**
     * web/global conf
     */
    public static String siteUrl = "https://informage--uat.lightning.force.com/lightning/page/home";
    public static String siteUrlCCOpAdmin = "https://informage--uat.lightning.force.com/lightning/page/home";
    public static String siteUrlCC = "https://uat-globalexhibitions.cs70.force.com/CustomerCenter";
    public static String baseUrl = "https://informage--uat.lightning.force.com";
    public static String homeUrl = baseUrl + "/lightning/page/home";
    public static String logoutUrl = baseUrl + "/secur/logout.jsp";

}
