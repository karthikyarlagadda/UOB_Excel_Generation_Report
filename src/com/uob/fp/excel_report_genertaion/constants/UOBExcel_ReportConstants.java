package com.uob.fp.excel_report_genertaion.constants;

public class UOBExcel_ReportConstants {


	

//For read property file from local system and Test Db coonection using JDBC and results
   public static String KPMG_PAYMENTPROCESS_CONSTANTS_PROPERTIESFILE = "config/UOBExcel_Report.properties";

	//To read directly if we make jar or bat file with conical file
	//public static String KPMG_PAYMENTPROCESS_CONSTANTS_PROPERTIESFILE = "config/kpmg.properties";
	
	//For reading the property file by WASS console JVM arguments
	public static String WASCONFIGURED_PROPERTIES_FILENAME = "KPMGProperties";
	//public static String KPMG_CUSTOMCOMPONENET_CONSTANTS_PROPERTIESFILE = "CPE/KPMGCpeConfig.properties";
	
	
	
	//ConversionUtill related Constants
	public static String CE_DATEFORMAT="CE_DATEFORMAT";
    public static String PROPERTIES_SYMBOLICNAME_PREFIX="PROPERTIES_SYMBOLICNAME_PREFIX";
    public static String KPMGREQUEST_DATE_FORMAT="KPMGREQUEST_DATE_FORMAT";
    
    
    //CE Connection related constants
    public static String CE_URI ="CE_URI";
	public static String CE_USER_ID ="CE_USER_ID";
	public static String CE_PASSWORD ="CE_PASSWORD";
	public static String CE_STANZA ="CE_STANZA";
	public static String OS_NAME ="OS_NAME";
	public static String PE_CONNECTION_POINT ="PE_CONNECTION_POINT";
	
	//PEOperaions related constants
	public static String QUEUE_NAME ="QUEUE_NAME";
	
	public static String VOUCHER_NUMBER ="VOUCHER_NUMBER";
	public static String INVOICE_NUMBER ="INVOICE_NUMBER";
	public static String PAYMENT_STATUS ="PAYMENT_STATUS";
	public static String PROCESSTYPE="PROCESSTYPE";
	public static String PAYMENT_STATUS_VALUE ="PAYMENT_STATUS_VALUE";
	public static String CASE_ID ="CASE_ID";
	public static String SQLQUERY = "SQLQUERY";
	public static final String DOMESTICINVOICEPROCESSINGTYPE = "DOMESTICINVOICEPROCESSINGTYPE";
	
	
	//DataSource related constants
	public static String KPMG_CUSTOM_DATA_SOURCE ="KPMG_CUSTOM_DATA_SOURCE";
	public static String  TARGET_DATA_SOURCE="TARGET_DATA_SOURCE";
	public static String  JDBC_URL="JDBC_URL";
	public static String TARGET_OBEJCTSTORE_URL="TARGET_OBEJCTSTORE_URL";
	public static String  USERNAME="USERNAME";
	public static String  PASSWORD="PASSWORD";
	
	
	//DataBase For Standalone
	public static String  ORCL_DB_USERNAME="ORCL_DB_USERNAME";
	public static String  ORCL_DB_PASSWORD="ORCL_DB_PASSWORD";
	public static String  ORCL_DB_URI="ORCL_DB_URI";
	public static String ORCL_UOB_CUTSOM_DB_USERNAME="ORCL_UOB_CUTSOM_DB_USERNAME";
	
	public static String  ORCL_DB_DRIVER="ORCL_DB_DRIVER";
	
	//PAYMENT_DETAILSVIEW_QUERY
	public static String  EXCEL_REPORT_DETAILS_CONTINER_VIEW_QUERY="EXCEL_REPORT_DETAILS_CONTINER_VIEW_QUERY";
	//UOB_CUSTOM_DB_QUERY
	public static String  UOB_CUSTOM_DB_QUERY="UOB_CUSTOM_DB_QUERY";
	//Payment Details ColumnNames
	/*public static String PAYMENT_METHOD_CODE ="PAYMENT_METHOD_CODE";
	public static String PAYMENT_REFERENCE ="PAYMENT_REFERENCE";
	public static String PAYMENT_DATE ="PAYMENT_DATE";
	public static String AMOUNT ="AMOUNT";*/
	public static String EXCEL_REPORT_PROPERTIES ="EXCEL_REPORT_PROPERTIES";
	
	
	
	
	
			
}
