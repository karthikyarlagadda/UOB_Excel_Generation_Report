package com.uob.fp.excel_report_genertaion.db.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.uob.fp.excel_report_genertaion.constants.UOBExcel_ReportConstants;
import com.uob.fp.excel_report_genertaion.exception.UOBExcel_ReportException;
import com.uob.fp.excel_report_genertaion.resourceloader.UOBExcel_ReportResourceLoader;
import com.uob.fp.excel_report_genertaion.util.CryptoUtil;

public class DataBaseConnectionProvider {
 private static final Logger logger = Logger.getLogger(DataBaseConnectionProvider.class);
	private  static UOBExcel_ReportResourceLoader cpeProperties = UOBExcel_ReportResourceLoader.getCPEInstance(UOBExcel_ReportConstants.KPMG_PAYMENTPROCESS_CONSTANTS_PROPERTIESFILE);

  /**
  * This method is to create prepared statement for the given query 
  * @param objConnection -- instance of the database connection
  * @param strQuery --- sql query
  * @return --instance of the prepared statement
  * @throws UOBExcel_ReportException
  */
 public PreparedStatement getCreateStatement(Connection connection, String SQLQuery) throws UOBExcel_ReportException {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: getCreateStatement ::  InvoiceAdvisoryDBConnectionProvider");
  }
  PreparedStatement preparedStatement = null;
  try {

   preparedStatement = connection.prepareStatement(SQLQuery);

  } catch (SQLException exception) {
   logger.error("SQLException Message ::" + exception.getMessage(), exception);
   throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
  } catch (Exception exception) {
   logger.error("SQLException Message ::" + exception.getMessage(), exception);
   throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
  }
  if (logger.isDebugEnabled()) {
   logger.debug("End :: getCreateStatement ::  InvoiceAdvisoryDBConnectionProvider");
  }
  return preparedStatement;
 }

 public void close(ResultSet resultSet, Statement statement, Connection connection) throws UOBExcel_ReportException {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: close ::  InvoiceAdvisoryDBConnectionProvider");
  }
  if (null != statement) {
   try {
    statement.close();
   } catch (SQLException exception) {
    logger.error("SQLException Message In close()::" + exception.getMessage(), exception);
    throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
   } catch (Exception exception) {
    logger.error("Exception Message In close()::" + exception.getMessage(), exception);
    throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
   }
  }
  if (null != resultSet) {
   try {
    resultSet.close();
   } catch (SQLException exception) {
    logger.error("SQLException Message In close()::" + exception.getMessage(), exception);
    throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
   } catch (Exception exception) {
    logger.error("Exception Message In close()::" + exception.getMessage(), exception);
    throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
   }
  }
  if (null != connection) {
   try {
    connection.close();
   } catch (SQLException exception) {
    logger.error("SQLException Message In close()::" + exception.getMessage(), exception);
    throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
   } catch (Exception exception) {
    logger.error("Exception Message In close()::" + exception.getMessage(), exception);
    throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
   }
  }
  if (logger.isDebugEnabled()) {
   logger.debug("End :: close ::  InvoiceAdvisoryDBConnectionProvider");
  }
 }

 /**
  * This method is used to getConnection for 
  * SQL database using DriverManager
  * @return Connection
  * @throws UOBExcel_ReportException 
  */
 Connection connection = null;
 public Connection getJDBCConnection() throws UOBExcel_ReportException {
  if (logger.isDebugEnabled()) {
   logger.debug("Start :: getJDBCConnection ::  InvoiceAdvisoryDBConnectionProvider");
  }
  logger.info("Entered into getJDBCConnection class ==> getConnection");
  
  try {
   logger.info("*************Before Driver loaded*********************");
   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   logger.info("*************Driver loaded*********************");
   logger.info("*************JDBC_URL_USERNAME_PASSWORD ********************" + cpeProperties.getProperty(UOBExcel_ReportConstants.JDBC_URL));;
   connection = DriverManager.getConnection(cpeProperties.getProperty(UOBExcel_ReportConstants.JDBC_URL),cpeProperties.getProperty(UOBExcel_ReportConstants.USERNAME),CryptoUtil.decryptPhrase(cpeProperties.getProperty(UOBExcel_ReportConstants.PASSWORD)));
   //connection = DriverManager.getConnection("jdbc:sqlserver://172.16.11.173:1433;database=CUSTDB;username=sa;password=mits123$");
   //logger.info("Connection established ");
  } catch (SQLException e) {
   logger.info("SQL exception is  ********** " + e);
   e.printStackTrace();
  } catch (ClassNotFoundException cnfe) {
   logger.info("ClassNotFoundException  is   *******" + cnfe);
   cnfe.printStackTrace();
  } catch (Exception exception) {
   logger.error("Exception Message ::" + exception.getMessage(), exception);
   throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
  }
  if (logger.isDebugEnabled()) {
   logger.debug("End :: getJDBCConnection ::  InvoiceAdvisoryDBConnectionProvider");
  }
  logger.info("End of  getJDBCConnection method  connection --->" + connection);
  return connection;
 }
 
 /**
  * This method is used to getConnection for 
  * Oracle database using DriverManager
  * @return Connection
  * @throws UOBExcel_ReportException 
  */ 
public Connection  getOracleConnection() throws UOBExcel_ReportException{
	
  if (logger.isDebugEnabled()) {
		   logger.debug("Start :: getOracleConnection ::  InvoiceAdvisoryDBConnectionProvider");
		  }
    try{
    	
	 logger.info("Entered into getOracleConnection class ==> getOracleConnection");
	 
	 String url=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_URI);
	 logger.info("url is..."+url);
	
	 String driver=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_DRIVER);
	 logger.info("driver is..."+driver);
	
	 String username=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_USERNAME);
	 logger.info("username is..."+username);

	 String password=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_PASSWORD);
	 logger.info("DB_Decrypt Password is  ::: "+password);
	 password=CryptoUtil.decryptPhrase(password);
	 logger.info("DB_Encrypt password is..."+password);
	    //registering the driver
	    Class.forName(driver);
	
	   //establishing the connection
		connection = DriverManager.getConnection(url, username,password);
		
     } catch (SQLException e) {
	   logger.info("SQL exception is  ********** " + e);
	   e.printStackTrace();
	  } catch (ClassNotFoundException cnfe) {
	   logger.info("ClassNotFoundException  is   *******" + cnfe);
	   cnfe.printStackTrace();
	  } catch (Exception exception) {
	   logger.error("Exception Message ::" + exception.getMessage(), exception);
	   throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
	  }
	  if (logger.isDebugEnabled()) {
	   logger.debug("End :: getOracleConnection ::  InvoiceAdvisoryDBConnectionProvider");
	  }
	  logger.info("End of  getOracleConnection method  connection --->" + connection);
	  return connection;
 }
/**
 * This method is used to getConnection for 
 * UOB Custom database using DriverManager
 * @return Connection
 * @throws UOBExcel_ReportException 
 */ 
public Connection  getUOB_CUSTOM_DB_CONNECTION() throws UOBExcel_ReportException{
	
 if (logger.isDebugEnabled()) {
		   logger.debug("Start :: getUOB_CUSTOM_DB_CONNECTION ::  InvoiceAdvisoryDBConnectionProvider");
		  }
   try{
   	
	 logger.info("Entered into getUOB_CUSTOM_DB_CONNECTION class ==> getOracleConnection");
	 
	 String url=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_URI);
	 logger.info("url is..."+url);
	
	 String driver=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_DRIVER);
	 logger.info("driver is..."+driver);
	
	 String username=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_UOB_CUTSOM_DB_USERNAME);
	 logger.info("username is..."+username);

	 String password=cpeProperties.getProperty(UOBExcel_ReportConstants.ORCL_DB_PASSWORD);
	 logger.info("DB_Decrypt Password is  ::: "+password);
	 password=CryptoUtil.decryptPhrase(password);
	 logger.info("DB_Encrypt password is..."+password);
	    //registering the driver
	    Class.forName(driver);
	
	   //establishing the connection
		connection = DriverManager.getConnection(url, username,password);
		
    } catch (SQLException e) {
	   logger.info("SQL exception is  ********** " + e);
	   e.printStackTrace();
	  } catch (ClassNotFoundException cnfe) {
	   logger.info("ClassNotFoundException  is   *******" + cnfe);
	   cnfe.printStackTrace();
	  } catch (Exception exception) {
	   logger.error("Exception Message ::" + exception.getMessage(), exception);
	   throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
	  }
	  if (logger.isDebugEnabled()) {
	   logger.debug("End :: getUOB_CUSTOM_DB_CONNECTION ::");
	  }
	  logger.info("End of  getUOB_CUSTOM_DB_CONNECTION method  connection --->" + connection);
	  return connection;
}
/**
 * This method is used to getConnection for 
 * Oracle database using DriverManager
 * @return Connection
 * @throws UOBExcel_ReportException 
 */ 
public Connection  getJDBC_TARGET_ObjectSTore_Connection() throws UOBExcel_ReportException{
	  if (logger.isDebugEnabled()) {
		   logger.debug("Start :: getJDBC_TARGET_ObjectSTore_Connection ::  InvoiceAdvisoryDBConnectionProvider");
		  }
		  logger.info("Entered into getJDBC_TARGET_ObjectSTore_Connection class ==> getConnection");
		  
		  try {
		   logger.info("*************Before Driver loaded*********************");
		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   logger.info("*************Driver loaded*********************");
		   logger.info("*************TARGET_OBEJCTSTORE_URL ********************" + cpeProperties.getProperty(UOBExcel_ReportConstants.TARGET_OBEJCTSTORE_URL));;
		   connection = DriverManager.getConnection(cpeProperties.getProperty(UOBExcel_ReportConstants.TARGET_OBEJCTSTORE_URL),cpeProperties.getProperty(UOBExcel_ReportConstants.USERNAME),CryptoUtil.decryptPhrase(cpeProperties.getProperty(UOBExcel_ReportConstants.PASSWORD)));
		   //connection = DriverManager.getConnection("jdbc:sqlserver://172.16.11.173:1433;database=CUSTDB;username=sa;password=mits123$");
		   //logger.info("Connection established ");
		  } catch (SQLException e) {
		   logger.info("SQL exception is  ********** " + e);
		   e.printStackTrace();
		  } catch (ClassNotFoundException cnfe) {
		   logger.info("ClassNotFoundException  is   *******" + cnfe);
		   cnfe.printStackTrace();
		  } catch (Exception exception) {
		   logger.error("Exception Message ::" + exception.getMessage(), exception);
		   throw new UOBExcel_ReportException("Exception Message ::" + exception.getMessage(), exception);
		  }
		  if (logger.isDebugEnabled()) {
		   logger.debug("End :: getJDBC_TARGET_ObjectSTore_Connection ::  InvoiceAdvisoryDBConnectionProvider");
		  }
		  logger.info("End of  getJDBC_TARGET_ObjectSTore_Connection method  connection --->" + connection);
		  return connection;
		 }
}