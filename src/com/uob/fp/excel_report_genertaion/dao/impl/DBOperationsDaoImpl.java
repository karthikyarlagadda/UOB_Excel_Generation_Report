package com.uob.fp.excel_report_genertaion.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.uob.fp.excel_report_genertaion.constants.UOBExcel_ReportConstants;
import com.uob.fp.excel_report_genertaion.dao.DBOperationsDao;
import com.uob.fp.excel_report_genertaion.db.connection.DataBaseConnectionProvider;
import com.uob.fp.excel_report_genertaion.excel_report_generation.Excel_Report_Generation;
import com.uob.fp.excel_report_genertaion.excel_report_generationImpl.Excel_report_GenerationImpl;
import com.uob.fp.excel_report_genertaion.exception.UOBExcel_ReportException;
import com.uob.fp.excel_report_genertaion.resourceloader.UOBExcel_ReportResourceLoader;

public class DBOperationsDaoImpl implements DBOperationsDao{

	private  static UOBExcel_ReportResourceLoader cpeProperties = UOBExcel_ReportResourceLoader.getCPEInstance(UOBExcel_ReportConstants.KPMG_PAYMENTPROCESS_CONSTANTS_PROPERTIESFILE);
	private static final Logger LOGGER = Logger.getLogger(DBOperationsDaoImpl.class);
	 PreparedStatement preparedStatement;
	 Statement statement;
	 DataBaseConnectionProvider connectionProvider = new DataBaseConnectionProvider();
	 Connection connection;
	 Connection custom_UOB_Connection;
	 ResultSet resultSet;
	 ResultSet custom_UOB_ResultSet;
	 HashMap<Object, Object> paymentProcessDataMapObj;
	 JSONArray finalArray=new JSONArray();
	 

	@Override
	public JSONArray getUOBCurrentDateCases(String currentDate,String currentTime) throws UOBExcel_ReportException {
		
		try {
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Entered into the method getUOBCurrentDateCases Of DBOperationsDAOImpl");}
		
		LOGGER.info("Entered into the method getUOBCurrentDateCases Of DBOperationsDAOImpl");
		connection = connectionProvider.getOracleConnection();
		custom_UOB_Connection = connectionProvider.getUOB_CUSTOM_DB_CONNECTION();
		
		LOGGER.info("currentDate::::::::DB impl:::::"+currentDate);
		
		String SQLQuery = cpeProperties.getProperty(UOBExcel_ReportConstants.EXCEL_REPORT_DETAILS_CONTINER_VIEW_QUERY);
		
		String custom_UOB_SQL_QUERY = cpeProperties.getProperty(UOBExcel_ReportConstants.UOB_CUSTOM_DB_QUERY);
		
		LOGGER.info("SQLQuery:::::::"+SQLQuery);
		
		String searchCriteria="(create_date";
		
		SQLQuery = SQLQuery+" "+searchCriteria+" BETWEEN '"+currentDate+"  12:00:00.000 AM"+"' AND '"+currentTime+"')";
		
		LOGGER.info("SQLQuery:::::::"+SQLQuery);
		
		preparedStatement = connection.prepareStatement(SQLQuery);
		
		PreparedStatement customDBUOBPreparedStatement=custom_UOB_Connection.prepareStatement(custom_UOB_SQL_QUERY);
		
		LOGGER.info("SQLQuery:::::::"+SQLQuery+"\npreparedStatement : "+preparedStatement.toString());
	
			resultSet = preparedStatement.executeQuery();
			LOGGER.info("resultSet size is::::::::::"+resultSet.getFetchSize());
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			
			  
			  
			  JSONArray excelPrepartionArray=null;
			  
			  while(resultSet.next())	 
			  {
				  excelPrepartionArray=new JSONArray();
				  
				  JSONObject excelJSONOBEJCT=new JSONObject();
				  
				  for(int i=0;i<resultSetMetaData.getColumnCount();i++)
				  {
					  
				  LOGGER.info("U9858_CMACMCASEIDENTIFIER :: "+resultSet.getString("U9858_CMACMCASEIDENTIFIER"));
				  
				  LOGGER.info("ColumnName :: "+resultSetMetaData.getColumnName(i+1));
				  
				  excelJSONOBEJCT.put(resultSetMetaData.getColumnName(i+1), resultSet.getObject(resultSetMetaData.getColumnName(i+1)));
				}
				  excelPrepartionArray.add(excelJSONOBEJCT);
				  LOGGER.info("excelPrepartionArray after while loop is::::"+excelPrepartionArray);
				  String caseCoulumnName=resultSet.getString("U9858_CMACMCASEIDENTIFIER");
				  				  
				  customDBUOBPreparedStatement.setString(1, caseCoulumnName);
				  
				  custom_UOB_ResultSet=customDBUOBPreparedStatement.executeQuery();
				  
				  ResultSetMetaData custom_UOB_ResultSetMetaData=custom_UOB_ResultSet.getMetaData();
				  
				  JSONArray customJSONArray=new JSONArray();
				  
				  while(custom_UOB_ResultSet.next())
				  {
					  
					  JSONObject custom_DB_JSONObject=new JSONObject();
					  for(int j=0;j<custom_UOB_ResultSetMetaData.getColumnCount();j++)
					  {
						  custom_DB_JSONObject.put(custom_UOB_ResultSetMetaData.getColumnName(j+1), 
								  custom_UOB_ResultSet.getObject(custom_UOB_ResultSetMetaData.getColumnName(j+1)));
					  }
					  customJSONArray.add(custom_DB_JSONObject);
				  }
				  excelPrepartionArray.add(customJSONArray);
				  finalArray.add(excelPrepartionArray);
			}
			 
		     LOGGER.info("finalArray after while loop is::::"+finalArray);
		     
		     Excel_Report_Generation excel_Report_Generation=new  Excel_report_GenerationImpl();
		     
		     excel_Report_Generation.excel_Report_Generation(finalArray);
			
		} catch (SQLException sql) {
			LOGGER.error("Exception Message In close()::" + sql.getMessage(), sql);
		    throw new UOBExcel_ReportException("Exception Message ::" + sql.getMessage(), sql);
		}
		catch (Exception e) {
			LOGGER.error("Exception Message In close()::" + e.getMessage(), e);
		    throw new UOBExcel_ReportException("Exception Message ::" + e.getMessage(), e);
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Ended the method getUOBCurrentDateCases Of DBOperationsDAOImpl");}
		
		return finalArray;
		
	}

}
