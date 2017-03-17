package com.uob.fp.excel_report_genertaion.resourceloader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.uob.fp.excel_report_genertaion.constants.UOBExcel_ReportConstants;


/**
 * This class is used for load the property file to reading properties from properties file
 * @author MITS
 *
 */
public class UOBExcel_ReportResourceLoader {
	

 static Logger logger = Logger.getLogger(UOBExcel_ReportResourceLoader.class);

 private static UOBExcel_ReportResourceLoader cpePropInstance = null;
 private static UOBExcel_ReportResourceLoader dbPropInstance = null;
 private static UOBExcel_ReportResourceLoader constantPropInstance = null;

 public Properties msgPropObj = new Properties();





 /**
  * Constructor read and loads the file	
  */
 public UOBExcel_ReportResourceLoader(String propertyFileName) {
  try {
   loadProperties(propertyFileName);
  } catch (Exception Exception) {
   logger.error("ExceptionMessage : " + Exception.getMessage(), Exception);
  }
 }


 /**
  * Read the property file via InputStream and load the
  * data into Properties object.
  * @throws IOException 	 
  */
 private void loadProperties(String propertyFileName) throws IOException {
  InputStream inputStream = null;
  try {
	 //For reading from server
	/*System.out.println("WASCONFIGURED_PROPERTIES_FILENAME:::" + KPMGCustomComponentConstants.WASCONFIGURED_PROPERTIES_FILENAME);
      String systemProperty = System.getProperty(KPMGCustomComponentConstants.WASCONFIGURED_PROPERTIES_FILENAME);
      System.out.println("systemProperty::" + systemProperty);*/
      
   try {
	/*  System.out.println("property filename in server:::" + systemProperty + "/" + propertyFileName);
       inputStream = new FileInputStream(new File(systemProperty + "/" + propertyFileName));*/
   
	   
    //To get the complete filename while creating jar or bat file and to run in local
     File file=new File(".");
   // System.out.println("Conical file path is"+file.getCanonicalFile());
    //System.out.println("propertyFileName::::::"+propertyFileName);
    //System.out.println("final path is::::"+file.getCanonicalFile() + "/" + propertyFileName);
	   
	   System.out.println("FinalPathhh:::::"+file.getCanonicalFile()+ "/" + propertyFileName);
	   
	//To read property file with local drive path   
   // inputStream = new FileInputStream(new File(KPMGPaymentProcessConstants.KPMG_PAYMENTPROCESS_CONSTANTS_PROPERTIESFILE));
    inputStream = new FileInputStream(new File(file.getCanonicalFile() + "/" + propertyFileName));

    
   } catch (FileNotFoundException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
   }



   if (inputStream != null) {
    msgPropObj.load(inputStream);
   }
  } catch (IOException ioException) {
   throw ioException;
  } finally {
   if (inputStream != null) {
    try {
     inputStream.close();
    } catch (IOException exception) {
     logger.error("Error while loading the propery file: " + exception.getMessage(), exception);
    }
   }
  }
 }



 /**
  * this method returns ResoureceLoader object for reading properties from properties file
  * @param -propertiesFileName
  * @return-ResourceLoader
  */
 public static UOBExcel_ReportResourceLoader getConstantsPropInstance(String propertiesFileName) {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: ResourceLoader ::   ResourceLoader");
  }
  logger.info("propertiesFileName:" + propertiesFileName);

  try {
   if (constantPropInstance == null) {
    constantPropInstance = new UOBExcel_ReportResourceLoader(propertiesFileName);
   }

  } catch (Exception e) {
   logger.error("Error in getInstance method::- " + e.getMessage(), e);
  }
  if (logger.isDebugEnabled()) {
   logger.debug("End :: ResourceLoader ::  ResourceLoader");
  }
  return constantPropInstance;
 }


 /**
  * this method returns ResoureceLoader object for reading properties from Filenet CEPEproperties file
  * @param -propertiesFileName
  * @return-ResourceLoader
  */
 public static UOBExcel_ReportResourceLoader getCPEInstance(String propertiesFileName) {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: ResourceLoader ::  ResourceLoader");
  }

  try {
   if (cpePropInstance == null) {
    cpePropInstance = new UOBExcel_ReportResourceLoader(propertiesFileName);
   }
  } catch (Exception e) {
   logger.error("Error in getInstance method::- " + e.getMessage(), e);
  }


  if (logger.isDebugEnabled()) {
   logger.debug("End :: ResourceLoader ::  ResourceLoader");
  }
  return cpePropInstance;
 }


 /**
  * this method returns ResoureceLoader object for reading properties from properties file
  * @param -propertiesFileName
  * @return-ResourceLoader
  */
 public static UOBExcel_ReportResourceLoader getDBPropInstance(String propertiesFileName) {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: ResourceLoader ::  ResourceLoader");
  }

  try {
   if (dbPropInstance == null) {
    dbPropInstance = new UOBExcel_ReportResourceLoader(propertiesFileName);
   }
  } catch (Exception e) {
   logger.error("Error in getInstance method::- " + e.getMessage(), e);
  }


  if (logger.isDebugEnabled()) {
   logger.debug("End :: ResourceLoader ::  ResourceLoader");
  }
  return dbPropInstance;
 }

 /**
  * This method is used to get the property
  * @param propertyName
  * @return	String
  */
 public String getProperty(String propertyName) {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: getProperty ::  ResourceLoader");
  }
  String value = "";

  if (msgPropObj != null) {
   value = msgPropObj.getProperty(propertyName);
   if (value != null) {
    value = value.trim();
   } else {
    logger.error(propertyName + " not available in properties file ");
   }
  }
  if (logger.isDebugEnabled()) {
   logger.debug("End :: getProperty ::  ResourceLoader");
  }
  return value;
 }

 /**
  * This method is used to get the property
  * @param strId
  * @param className
  * @return String--statement
  */
 public String getProperty(String strId, String className) {
  if (logger.isDebugEnabled()) {
   logger.debug("End :: getProperty ::  ResourceLoader");
  }
  String statement = "";
  if (msgPropObj != null) {
   statement = msgPropObj.getProperty(strId);
   if (statement != null && !statement.equals("")) {
    statement = statement.replace("{xxxxx}", className);
   }
  }
  if (logger.isDebugEnabled()) {
   logger.debug("End :: getProperty ::  ResourceLoader");
  }
  return statement;
 }
}