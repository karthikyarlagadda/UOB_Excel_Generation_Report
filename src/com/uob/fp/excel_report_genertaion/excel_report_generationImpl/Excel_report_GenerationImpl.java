/**
 * 
 */
package com.uob.fp.excel_report_genertaion.excel_report_generationImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.log4j.Logger;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.uob.fp.excel_report_genertaion.excel_report_generation.Excel_Report_Generation;

/**
 * @author sairam
 *
 */
public class Excel_report_GenerationImpl implements Excel_Report_Generation {

	/* (non-Javadoc)
	 * @see com.uob.fp.excel_report_genertaion.excel_report_generation.Excel_Report_Generation#excel_Report_Generation(com.ibm.json.java.JSONArray)
	 */
	
	private WritableCellFormat times;
	
	private static Logger LOGGER=Logger.getLogger(Excel_report_GenerationImpl.class);
	
	@Override
	public boolean excel_Report_Generation(JSONArray excelReport_Data) throws Exception
	{
		FileOutputStream outputStream=new  FileOutputStream(new File("./Excel_genrration/Excel_Grneration.xls"));

		WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
		
		workbook = write(workbook,excelReport_Data);
		workbook.write();
		workbook.close();
		
		//outputStream.flush();
		//outputStream.close();
		return true;
	}

	private WritableWorkbook write(WritableWorkbook workbook,
			JSONArray excelReport_Data) throws RowsExceededException, WriteException 
	{
		WritableSheet excelSheet = null;
		workbook.createSheet("UOB_Export", 0);
		excelSheet = workbook.getSheet(0);
		excelSheet.setPageSetup(PageOrientation.LANDSCAPE);
		excelSheet.getSettings().setPaperSize(PaperSize.A3);
		// first create header and content for HOD
		String labels="Activity,Names,String";
		createLabel(excelSheet,labels);
		createContent(excelSheet,excelReport_Data);
		return workbook;
	}

	private void createContent(WritableSheet excelSheet,
			JSONArray excelReport_Data) throws RowsExceededException, WriteException 
	{
		for(int i=0;i<excelReport_Data.size();i++)
		{
			JSONArray exportJSONArray=(JSONArray) excelReport_Data.get(i);
			
			for(int j=1;j<exportJSONArray.size();j++)
			{
				JSONObject jsonObject=(JSONObject) exportJSONArray.get(0);
				
				addLabel(excelSheet, i, j, jsonObject.get("NAME"));
				/*if(j==0)
				{
					JSONObject jsonObject=(JSONObject) exportJSONArray.get(0);
					
					addLabel(excelSheet, i, j, jsonObject.get("NAME"));
				}
				else if(j==1)
				{
					JSONArray jsonArray=(JSONArray) exportJSONArray.get(1);
					LOGGER.info("jsonArray :: "+jsonArray);
				}*/
			}
		}
		
	}

	private void addLabel(WritableSheet excelSheet, int i, int j, Object object) throws RowsExceededException, WriteException 
	{
		WritableFont times10pt = new WritableFont(WritableFont.TAHOMA, 11);
		  // Define the cell format
		  times = new WritableCellFormat(times10pt);
		  // Lets automatically wrap the cells
		  times.setWrap(true);
		  times.setAlignment(Alignment.LEFT);
		  times.setVerticalAlignment(VerticalAlignment.TOP);
		  times.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		  times.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		LOGGER.info("excelSheet :: "+excelSheet
				);
		LOGGER.info("i :: "+i);
		LOGGER.info("j :: "+j);
		LOGGER.info("object :: "+object);
		Label label;
		
		label = new Label(i, j, object.toString(), times);
		excelSheet.addCell(label);
	}

	private void createLabel(WritableSheet excelSheet, String labels) throws RowsExceededException, WriteException 
	{
		String lablelsArray[]=labels.split(",");
		Label label;
		for (int i = 0; i < lablelsArray.length; i++) 
		{
			label = new Label(i, 0, lablelsArray[i]);
			LOGGER.info("label :: "+label);
			excelSheet.addCell(label);
		}
		
	}

}
