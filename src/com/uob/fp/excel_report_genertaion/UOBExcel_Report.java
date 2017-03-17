package com.uob.fp.excel_report_genertaion;

import java.text.ParseException;

import com.uob.fp.excel_report_genertaion.delegate.UOBExcel_ReportBusinessDelegate;

public class UOBExcel_Report {

	public static void main(String[] args)
	{
		
		UOBExcel_ReportBusinessDelegate delegate = new UOBExcel_ReportBusinessDelegate();
		try {
			delegate.getCaseFolder();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
