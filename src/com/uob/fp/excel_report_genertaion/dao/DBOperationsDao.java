package com.uob.fp.excel_report_genertaion.dao;

import com.ibm.json.java.JSONArray;
import com.uob.fp.excel_report_genertaion.exception.UOBExcel_ReportException;

public interface DBOperationsDao {

	public JSONArray getUOBCurrentDateCases(String startDate,String CurrentTime) throws UOBExcel_ReportException;
	
}
