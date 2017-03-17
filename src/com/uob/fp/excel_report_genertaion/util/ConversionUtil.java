package com.uob.fp.excel_report_genertaion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.uob.fp.excel_report_genertaion.constants.UOBExcel_ReportConstants;
import com.uob.fp.excel_report_genertaion.exception.UOBExcel_ReportException;
import com.uob.fp.excel_report_genertaion.resourceloader.UOBExcel_ReportResourceLoader;

/**
 * 
 * Utility Class for document related utility methods
 *
 */
public class ConversionUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ConversionUtil.class);
	
	private  static UOBExcel_ReportResourceLoader stepResourceLoader = UOBExcel_ReportResourceLoader.getCPEInstance(UOBExcel_ReportConstants.KPMG_PAYMENTPROCESS_CONSTANTS_PROPERTIESFILE);
	private  static  SimpleDateFormat isDateFormat =  new  SimpleDateFormat();
	
	

	
	/**
	 * The method used to convert String type to float
	 * @param floatProperty
	 * @return double
	 */
	public static Double convertStringToFloat(String floatProperty)
	{
	if(LOGGER.isDebugEnabled()){
		LOGGER.debug("Start :: convertStringToFloat ::  ConversionUtil");
		}
	
			LOGGER.info("Before Formated floatProperty amount  is--->"+floatProperty);
			/*Float f=Float.parseFloat(floatProperty);
			floatProperty=String.format("%.2f", f);
			LOGGER.info("Formated amount is--->"+floatProperty);
			Double doubleValue = new Double(floatProperty.trim());*/
	
			Double doubleValue = Double.valueOf(floatProperty.trim());
			LOGGER.info("Formated amount is--->"+doubleValue);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End  :: convertStringToFloat ::  ConversionUtil");
			}
		return doubleValue;
	}

	/**
	 * The method used to convert String type to Integer
	 * @param inttProperty
	 * @return Integer
	 */
	public Integer convertStringToInteger(String intProperty) 
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToInteger ::   ConversionUtil");
		}
			Integer intValue = Integer.valueOf(intProperty.trim());
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToInteger ::  ConversionUtil");
			}
		return intValue;
	}

	/**
	 * The method used to convert String type to floatList
	 * @param floatListProperty
	 * @return Float64List
	 */
/*	public Float64List convertStringToFloatList(JSONArray floatListProperty)
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToFloatList ::  ConversionUtil");
			}
				Float64List propValuesList = Factory.Float64List.createList();
				for (int i = 0; i < floatListProperty.length(); i++)
				{
					String value = floatListProperty.get(i).toString();
					if(value.trim().length()>0)
					{
						propValuesList.add(value);
					}
				}
				
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToFloatList ::  ConversionUtil");
			}
		return propValuesList;
	}

*/	/**
	 * The method used to convert String type to integerList
	 * @param integerListProperty
	 * @return Integer32List
	 */
	/*public Integer32List convertStringToIntegerList(JSONArray integerListProperty)
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToIntegerList ::  ConversionUtil");
			}
		
			Integer32List propValuesList = Factory.Integer32List.createList();
			for (int i = 0; i < integerListProperty.length(); i++)
			{
				String value = integerListProperty.get(i).toString();
				if(value.trim().length()>0)
				{
					propValuesList.add(value);
				}
			}


		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToIntegerList ::  ConversionUtil");
			}
		return propValuesList;
	}*/


	/**
	 * The method used to convert String type to boolean
	 * @param booleanProperty
	 * @return boolean
	 */
	public Boolean convertStringToBooleanAdd(String booleanProperty) 
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToBooleanAdd ::  ConversionUtil");
		}
			Boolean booleanValue=false;
			if( booleanProperty.equalsIgnoreCase("true") || booleanProperty.equalsIgnoreCase("false")
					|| booleanProperty.equals("")) {
				booleanValue = Boolean.valueOf(booleanProperty.trim());
			}
					
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToBooleanAdd ::  ConversionUtil");
		}
		return booleanValue;
	}
	
	
	
	/**
	 * The method used to convert String type to boolean
	 * @param booleanProperty
	 * @return boolean
	 * @throws STEPException 
	 */
	public Boolean convertStringToBoolean(String booleanProperty) throws UOBExcel_ReportException 
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToBoolean ::  ConversionUtil");
		}
			Boolean booleanValue=false;
			if( booleanProperty.equalsIgnoreCase("true") || booleanProperty.equalsIgnoreCase("false")
					|| booleanProperty.equals("")) {
				booleanValue = Boolean.valueOf(booleanProperty.trim());
			}else{
				throw new UOBExcel_ReportException();
			}
					
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToBoolean ::  ConversionUtil");
		}
		return booleanValue;
	}

	/**
	 * The method used to convert String type to Date
	 * @param dateProperty
	 * @param propertyName
	 * @return Date
	 * @throws STEPException 
	 */
	public static  Date convertStringToDate(String dateProperty,String propertyName) throws  UOBExcel_ReportException 
	{
		
		Date dateFormat = null;
		String exceptionMessage="";
		Integer month=null;
		Integer day=null;
		Integer year=null;
		Integer hour=null;
		Integer minutes=null;
		Integer seconds=null;
		String[] date;
		String[] datePropertyArray;
		String[] time;
		String[] timePropertyArray;
		
		try {
		LOGGER.info("In covertStringToDate");
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToDate ::  ConversionUtil");
			}
		// SimpleDateFormat for formatting the date
	 
		isDateFormat.applyPattern(stepResourceLoader.getProperty(UOBExcel_ReportConstants.CE_DATEFORMAT));
	
			LOGGER.info("Date property is--->"+dateProperty);
			isDateFormat.setLenient(false);
			if((null != dateProperty || !dateProperty.trim().equalsIgnoreCase("")))
			{
				LOGGER.info("Given date is "+dateProperty+"\t CE dateproperty ::::::::"+stepResourceLoader.getProperty(UOBExcel_ReportConstants.CE_DATEFORMAT)+	"\t date property length is--->"+dateProperty.length());
				
				
				if (propertyName.contains(stepResourceLoader.getProperty(UOBExcel_ReportConstants.PROPERTIES_SYMBOLICNAME_PREFIX))) {
					propertyName=propertyName.replace(stepResourceLoader.getProperty(UOBExcel_ReportConstants.PROPERTIES_SYMBOLICNAME_PREFIX), "");
				}
				
				exceptionMessage=propertyName+" should be  in the format yyyy/MM/dd ";
				dateProperty=getRequiredFormat(dateProperty, stepResourceLoader.getProperty(UOBExcel_ReportConstants.KPMGREQUEST_DATE_FORMAT), stepResourceLoader.getProperty(UOBExcel_ReportConstants.CE_DATEFORMAT));
				LOGGER.info("Converted date =====>"+dateProperty);
				dateFormat = isDateFormat.parse(dateProperty);					
			
				LOGGER.info("After Parsing =========>>"+dateFormat);
			}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToDate ::  ConversionUtil");
			}
	} catch (Exception e) {
		//exceptionMessage=propertyName+" should be in the format yyyy/MM/dd or yyyy/MM/dd HH:mm:ss ";
		throw new UOBExcel_ReportException(exceptionMessage);
	}
		return dateFormat;
	}

	/**
	 * The method used to convert String type to ID
	 * @param idValue
	 * @return id
	 */
	/*public Id convertStringToId(String idValue) 
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertStringToId ::  ConversionUtil");
			}
				Id guidId= null;
				guidId = new Id(idValue.trim());
				
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertStringToId ::  ConversionUtil");
			}
		
		return guidId;
	}*/

	/**
	 * Converting List To Array
	 * @param propertyValuesList
	 * @return strArray
	 */
	/*public String[] convertListToArray(StringList propertyValuesList)
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertListToArray ::  ConversionUtil");
			}
		String[] strArray = new String[propertyValuesList.size()];
		Iterator<String> itr = propertyValuesList.iterator();
		int count = 0;
		while (itr.hasNext()) {
			String str = itr.next();
			strArray[count++] = str;
		}

		itr = null;
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertListToArray ::  ConversionUtil");
			}
		return strArray;
	}*/

	/**
	 * ConvertArray To String
	 * @param propertyValuesList
	 * @return String
	 */
	public String convertArrayToString(String[] propertyValuesList)
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertArrayToString ::  ConversionUtil");
		}
			String value = "";
			for (int i = 0; i < propertyValuesList.length; i++)
			{
				value += propertyValuesList[i] + ";";
			}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertArrayToString ::  ConversionUtil");
		}
		return value;
	}
	/**
	 * Converting to upperCase
	 * @param string
	 * @return String
	 */
	public static String upperCase(String string)
	{
		if (null != string && !"".equalsIgnoreCase(string))
		{
			return string.trim().toUpperCase(Locale.ENGLISH);
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * The method used to convert String to CE Dateformat
	 * @param sDate
	 * @return String-ceDate
	 */
	 @SuppressWarnings("deprecation")
	public String getCEFormattedDate(String sDate) {
		   
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" Entered into getCEFormattedDate method : Date : "+sDate );
		}
			// To handle dd/mm/yyyy format
			if(sDate.contains("/")){
				String[] dateArr = sDate.split("/");
				sDate = dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
 			}
			
			Calendar cal = Calendar.getInstance();
			 cal.setTime(new Date(sDate));
			 Date date = cal.getTime();
			// SimpleDateFormat CE_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
			 isDateFormat.applyPattern("yyyyMMdd'T'HHmmss'Z'");
			 TimeZone gmtTime = TimeZone.getTimeZone("GMT");
			 isDateFormat.setTimeZone(gmtTime);
			 String ceDate=isDateFormat.format(date);
			
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" End of getCEFormattedDate method : Date : "+sDate );
		}
		
		return ceDate;
	}
	 
	 /**
	 * The method used to convert Date format
	 * @param dateValue
	 * @return String-formatted date
	 
	 */
	public String convertDocDateTimeToString(Date dateValue) {
		
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" Entered into convertDocDateTimeToString method : Conversionutil : ");
		}
		String value = "";
		if( null != dateValue){
		
			//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			isDateFormat.applyPattern("dd-MMM-yyyy HH:mm:ss");
	   	 	Long milliSecs = dateValue.getTime();
	   	 	Calendar calendar = Calendar.getInstance();
	   	 	calendar.setTimeInMillis(milliSecs);
	   	 	value = isDateFormat.format(calendar.getTime());
		}
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" End of convertDocDateTimeToString method : Conversionutil : ");
		}
		return value;
	}
	
	/**
	 * Method to validate String
	 * @param stringVal
	 * @return boolean
	 * */
	public static boolean isValidString(String stringVal)
	{
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" Entered into isValidString method : Conversionutil : ");
		}
			boolean isValid = false;
			if(stringVal != null)
			{
				
				String value = stringVal.trim();
				if(value.length() > 0 && !("".equalsIgnoreCase(value))){
					isValid = true;
				}
			}else {
				isValid = false;
			}
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" End of isValidString method : Conversionutil : ");
		}
		return isValid;
	}
		/**
	 * Converting to lowercase
	 * 
	 * @param string
	 * @return String
	 */
	public static String lowerCase(String string)
	{
		if (null != string && !"".equalsIgnoreCase(string))
		{
			return string.trim().toLowerCase(Locale.ENGLISH);
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * Method to validate List
	 * @param stringVal-StringList
	 * @return boolean
	 * */
	public static boolean isValidList(List<String> stringVal)
	{
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" Entered into isValidList method : Conversionutil : ");
		}
			boolean isValid = true;
			for( int i= 0 ; i<stringVal.size() ; i++){
				 String value = stringVal.get(i);
				 if(! ConversionUtil.isValidString(value)) {
					 isValid =false;
				 }
			 }
		if( LOGGER.isDebugEnabled()){
			LOGGER.debug(" End of isValidList method : Conversionutil : ");
		}
		return isValid;
	}
			
	/**
	 * ConvertList To String
	 * @param propertyValuesList
	 * @return String-value
	 */
	public String convertListToString(List<String> propertyValuesList)
	{
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start :: convertArrayToString ::  ConversionUtil");
		}
			String value = "";
			for (int i = 0; i < propertyValuesList.size(); i++)
			{
				value += propertyValuesList.get(i);
				if( i < propertyValuesList.size()-1){
					value+=",";
				}
			}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End :: convertArrayToString ::  ConversionUtil");
		}
		return value;
	}
	
	public static String getRequiredFormat(String date,String inputformattedDate,String outputFormattedDate) throws UOBExcel_ReportException {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Entered DateUtil class of getRequiredFormat method");
		}
		String requiredFormat="";
		LOGGER.info("Your Date is--> "+date+"input Format is--> "+inputformattedDate+"Out put Format is--> "+outputFormattedDate);
		SimpleDateFormat outputsimpleDateFormat=new SimpleDateFormat(outputFormattedDate);
		SimpleDateFormat inputsimpleDateFormat=new SimpleDateFormat(inputformattedDate);

		try {				
			if((null!= date && (!date.equalsIgnoreCase("null")))){
				if(!date.equalsIgnoreCase("")){
				requiredFormat=outputsimpleDateFormat.format(inputsimpleDateFormat.parse(date));
			}
			}
		} catch (ParseException e) {
			LOGGER.error("DateUtil --- getRequiredFormat() --- Exception :: "+e.getMessage());
			throw new UOBExcel_ReportException(date+ "is not in "+inputformattedDate);

		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Ended ConversionUtil class of getRequiredFormat method---converted date is--->"+requiredFormat);
		}
		return requiredFormat;
	}

}
