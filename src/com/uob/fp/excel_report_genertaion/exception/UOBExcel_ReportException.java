package com.uob.fp.excel_report_genertaion.exception;




/**
 * this is customized exception which can be thrown by the  
 * STEPDao in exceptional cases.
 *
 */
public class UOBExcel_ReportException extends Exception
{

	
	private static final long serialVersionUID = 1L;
		
	private String message = null;
	
	/**
	 * default Constructor
	 */
    public UOBExcel_ReportException() {
        super();
    }
    /**
     * Contructor with String as arguments to throw the exception message
     * @param message --exceptionmessage
     */
    public UOBExcel_ReportException(String message) {
        super(message);
        this.message = message;
    }
    /**
     * Contructor with Throwable as arguments to throw the exception cause
     * @param message --exceptionmessage
     */
    public UOBExcel_ReportException(String message,Throwable cause) {
        super(cause);
        this.message = message;
    }
   /* public ANZECMException(String message,StackTraceElement stackTraceElement)
    {
    	super(message);
        this.message = message;
    }*/
	/**
     * Overide toString method
     */
    @Override
    public String toString() {
        return message;
    }
	 /**
	  * getter method which returns exception message
	  */
    @Override
    public String getMessage() {
        return message;
    }


}
