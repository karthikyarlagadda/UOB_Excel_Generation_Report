package com.uob.fp.excel_report_genertaion.delegate;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.uob.fp.excel_report_genertaion.dao.DBOperationsDao;
import com.uob.fp.excel_report_genertaion.dao.impl.DBOperationsDaoImpl;
import com.uob.fp.excel_report_genertaion.exception.UOBExcel_ReportException;



public class UOBExcel_ReportBusinessDelegate {
	private static Logger logger = Logger.getLogger(UOBExcel_ReportBusinessDelegate.class);
	DBOperationsDao dbOperationsDao=null;
	public void getCaseFolder() throws ParseException
	{
		
		
		if (logger.isDebugEnabled()) 
		{
			logger.debug("Entered into getCaseFolder method of KPMGPaymentProcessingBusinessDelegate class");
		}
		
		dbOperationsDao = new DBOperationsDaoImpl();
		try 
		{
			logger.info("New Date () ::: "+new java.util.Date());
			
		    SimpleDateFormat covertDateFomat2=new  SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSS aaa");
		    logger.info("covertDateFomat2 Date is :: "+covertDateFomat2.format(new java.util.Date()));
			
			String str = "2016-05-19 12:03:40.123";
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		    SimpleDateFormat covertDateFomat=new  SimpleDateFormat("dd-MMM-yy");
		    java.util.Date date = df.parse(str);
		    logger.info("Formated Date is :: "+covertDateFomat.format(new java.util.Date()));
		    long epoch = date.getTime();
		    logger.info("EPOCH FORMAT "+epoch); // 1055545912454
			
			logger.info("Convererted Date :: "+new Date(epoch));
			
			dbOperationsDao.getUOBCurrentDateCases(covertDateFomat.format(new java.util.Date()),covertDateFomat2.format(new java.util.Date()));
		}
		catch (UOBExcel_ReportException e) 
		{
			logger.error("Exception occured due to::::::"+e.getMessage());
		}
		if (logger.isDebugEnabled()) 
		{
			logger.debug("End of getCaseFolder method of KPMGPaymentProcessingBusinessDelegate class");
		}
		
		
	}

}
