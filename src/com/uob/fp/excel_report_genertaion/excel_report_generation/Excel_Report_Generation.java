/**
 * 
 */
package com.uob.fp.excel_report_genertaion.excel_report_generation;

import java.io.IOException;

import com.ibm.json.java.JSONArray;

/**
 * @author sairam
 *
 */
public interface Excel_Report_Generation 
{
	public boolean excel_Report_Generation(JSONArray excelReport_Data)throws Exception; 
}
