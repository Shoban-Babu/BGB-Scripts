package bg.framework.app.functional.page.Slingshot;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.Properties;
import org.joda.time.DateTime;
import com.gnostice.pdfone.PdfDocument;
import com.gnostice.pdfone.PdfReader;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;

public class ViewBillPage extends BasePage{
	DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal=Calendar.getInstance();
    
    SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyy");
	private final static String FILE_NAME = "resources/Slingshot/ViewBill.properties";	    
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
	
	public void clickViewBillLink(){
		
		verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.Link"), "View Bill Link");	
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("SearchBill.PageTitle"));
		
		verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.ViewbillForDifferentAccount"), "View bill for different account");
		browser.wait(5000);
	}
	public void selectTerm(String Accountnumber){
		
		
		try{
		    verifyAndSelectDropDownBox(pageProperties.getProperty("SearchBill.SearchKey"),"Search Dropdown",Accountnumber);
		}catch(Exception e){
			Report.updateTestLog("Error selecting value in the select term drop down"+e, "Fail");
		}
	}
	public void enterSearchCriteria(String SearchCriteria){
			
		verifyAndInputById(pageProperties.getProperty("SearchBill.SearchCriteria"), "Search Criteria", SearchCriteria);
		}
	public void enterFromDate(int month){

		int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);
		System.out.println("Important");
		System.out.println(days);
		System.out.println("joda day is:"+day);
		
/*		int d=cal.add(Calendar.MONTH,-3);
		if(FromDate.equalsIgnoreCase("3")){
           cal.add(Calendar.MONTH,-3);
		}else{
			
		}*/
		
		verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.FromDate"), "FromDate");
		for(int i=1;i<=month;i++){
			verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.PreviousDatePicker"), "PreviousDatePicker");
			browser.wait(500);
		}
     
		//verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.PreviousDate"), "3 Months date");
		String result=calenderDate(day);
		/*System.out.println("%^$^$%^$%^$%$%$%$%$%$%$%$%");
		System.out.println(result);
		if(result.equals("False")){
		   verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.NextDatePicker"), "NextDatePicker");	
		   calenderDate(day);
		}*/
	}
	public String calenderDate(String day){
		String result="False";
		int rowcount=browser.getRowCountByXpath(pageProperties.getProperty("Searchbill.CalenderTable"));
		int columncount=browser.getColCountByXpath(pageProperties.getProperty("Searchbill.CalenderTable"));	
		
		System.out.println("rowcount:"+rowcount);
		System.out.println("columncount:"+columncount);
		for(int i=1;i<=rowcount;i++){
			for(int j=1;j<=columncount;j++){
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Searchbill.CalenderTable")+"//tr["+i+"]/td["+j+"]/a"+"[contains(@class,'disabled')]"))
				{
					System.out.println("empty xpath is");
					}	
									
				else{
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Searchbill.CalenderTable")+"//tr["+i+"]/td["+j+"]/a")){						
					String dateThreeMonths=browser.getTextByXpath(pageProperties.getProperty("Searchbill.CalenderTable")+"//tr["+i+"]/td["+j+"]/a");
					browser.clickWithXpath(pageProperties.getProperty("Searchbill.CalenderTable")+"//tr["+i+"]/td["+j+"]/a");
					
					System.out.println("dateThreeMonths"+dateThreeMonths);
					System.out.println("&YR^&^$^$%^$%^$%^$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$");
					
					if(dateThreeMonths.equals(day)){
						System.out.println("uhsnhduwecureucgyu");
						System.out.println("dateThreeMonths"+dateThreeMonths);
						System.out.println(day);
						Report.updateTestLog("Day is matched:"+day,"pass");
						browser.clickWithXpath(pageProperties.getProperty("Searchbill.CalenderTable")+"//tr["+i+"]/td["+j+"]/a");
						//verifyAndClickWithXpath(pageProperties.getProperty("Searchbill.CalenderTable")+"/tbody/tr["+i+"]/td["+j+"]/a", "FromDate");
						result="True";
						
						System.out.println(result);
						break;
					}
					}
				}	
				
			}
		}
		
		return result;
				}
	
	public void enterToDate(int month){

		int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);
		System.out.println("Important");
		System.out.println(days);
		System.out.println("joda day is:"+day);
		
/*		int d=cal.add(Calendar.MONTH,-3);
		if(FromDate.equalsIgnoreCase("3")){
           cal.add(Calendar.MONTH,-3);
		}else{
			
		}*/
		
		verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.ToDate"), "FromDate");
		for(int i=1;i<=month;i++){
			verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.PreviousDatePicker"), "PreviousDatePicker");
			browser.wait(500);
		}
     
		//verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.PreviousDate"), "3 Months date");
		String result=calenderDate(day);
		/*System.out.println("%^$^$%^$%^$%$%$%$%$%$%$%$%");
		System.out.println(result);
		if(result.equals("False")){
		   verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.NextDatePicker"), "NextDatePicker");	
		   calenderDate(day);
		}*/
	}
	
			
 /*  public void enterToDate(String ToDate){
	   
		Calendar cal=Calendar.getInstance();
		int currentday=cal.get(Calendar.DATE);	
		System.out.println(currentday);
		verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.ToDate"),"Todate");
//		browser.clickWithXpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[2]/a");
		verifyAndClickWithXpath(pageProperties.getProperty("s")+currentday+"')]", "TodaysDate");		
	}
   */
   
   public void clickSearchBillSearchButton(){
		
		verifyAndClick(pageProperties.getProperty("SearchBill.SearchButton"), "SearchButton");
	}
   public void verifyBillTable(){
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SearchBill.Table"))){
		   Report.updateTestLog("Bill displayed in the table", "Pass");
		   int rowcount=browser.getRowCountByXpath(pageProperties.getProperty("SearchBill.Table"));
		   int columncount=browser.getColCountByXpath(pageProperties.getProperty("SearchBill.Table"));
		   Report.updateTestLog("Number of rows displayed in the table is :"+rowcount+"Number of Columns displayed" +
		   		"in the table :"+columncount, "Done");		   
	   }else{
		   Report.updateTestLog("Bill not displayed in the table", "Fail");
	   }
	   
   }
   public void selectCheckboxDownload(){
	   String checkbox=pageProperties.getProperty("SearchBill.TableData").replace("Row", "1").replace("Column", "7")+"/p/input";
	   verifyAndSelectCheckBoxByXpath(checkbox, "Checkbox");
   }
   public void clickSearchBillDownload(){	   
	   verifyAndClick(pageProperties.getProperty("SearchBill.downloadBtn"), "Download");
	   
   }
   public void verifyDownloadedZipFile(){
	   String fileLocation=System.getProperty("user.home")+File.separator+"My Documents";
	   String path=fileLocation+"\\Downloads";
	   
   }
   public void verifySearchByOptions(){
		String text = null;
		String indicator="Null";
		String[] verifyText={"Please select","Account number","Bill number","Meter point reference number"};
		List<String> countOf=browser.getFromDropBox("id", pageProperties.getProperty("SearchBill.SearchKey"));
		Report.updateTestLog("Number of Items in the listbox :"+countOf.size(),"Pass");
		for(String itera:verifyText){
			
			for(int i=1;i<=countOf.size();i++){
				text=browser.getTextByXpath("//*[@id='searchKey']/option["+i+"]");
				if(itera.equals(text)){
				  indicator="Pass";	
				  break;
				}else{
					indicator="Null"; 	
				}
			}
			Report.updateTestLog("Drop down value: Expected Result: "+itera+"Actual Result: "+text,indicator.contains("Pass")?"Pass":"Fail");
		}	

	}

   public void verifyViewBillPage(){
	   verifyPageTitle(pageProperties.getProperty("ViewBill.PageTitle"));
	   String accountNumber = null;
	   try{
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.AccountNumber"))){
		    accountNumber =  browser.getTextByXpath(pageProperties.getProperty("ViewBill.AccountNumber"));
		    System.out.println("ACCOUNTNUMBER1   "+accountNumber);
	   }}
	   catch(Exception e){
		    accountNumber =  browser.getAttributeByXpath(pageProperties.getProperty("ViewBill.AccountNumber"),"p");
		    System.out.println("ACCOUNTNUMBER2   "+accountNumber);
	   }
	   browser.wait(10000);
	  //accountNumber =  browser.getTextByXpath(pageProperties.getProperty("ViewBill.AccountNumber"));
	  Report.updateTestLog("Cutomer displaying with account number "+accountNumber, "PASS");
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ViewBill.BackToYourAccount"), "Back to your account link");
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ViewBill.InvoiceNumber"), "Invoice number");
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ViewBill.ViewbillForDifferentAccount"), "View bill for different account");
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ViewBill.Duesection"),"View Bill Due section");
	  verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.DownLoadBill"),"DownloadBill");
	  browser.wait(3000);
	  verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.ViewbillForDifferentAccount"), "View bill for different account");
	  browser.wait(3000);
	  Report.updateTestLog("Search Bill", "Pass");
	  browser.browserBack();
	  //verifyIsElementVisibleWithXpath(pageProperties.getProperty("ViewBill.ViewDifferentBill"), "View different bill");
	 //verifyIsElementVisibleWithXpath(pageProperties.getProperty("ViewBill.ViewBillButton"), "View bill button");
	  browser.wait(15000);
	  Report.updateTestLog("View Bill is Displayed", "WARN");
   }

   public void verifyBillTableColumns(){
	   try{
	   int columncount=browser.getColCountByXpath(pageProperties.getProperty("SearchBill.Table"));
	   String[] verifyText={"Billing date","Payment type","Business name","Account number","Bill number","Gross amount"};
//	   for(String itera:verifyText){
		   for(int i=0;i<verifyText.length;i++){
			   int j=i+1;
			 String getRowValues=browser.getTextByXpath((pageProperties.getProperty("SearchBill.Table"))+"//tr/th["+j+"]");
			 System.out.println("Xpath value:"+pageProperties.getProperty("SearchBill.Table")+"//tr/th["+j+"]");
			 if(verifyText[i].equals(getRowValues)){
				 Report.updateTestLog("Expected column value is :"+verifyText[i]+"Actual column value is :"+getRowValues,"Pass");
			 }else{
				 Report.updateTestLog("Expected column value is :"+verifyText[i]+"Actual column value is :"+getRowValues,"Fail");
			 }			   
//		   }
	   }}catch(Exception e){
		   Report.updateTestLog("Exception while trying to fetch the value in application","Fail");
	   } 	   
   }
   public void verifySortingBillTable(){
	   
	   String dateField1="";
	   String dateField2="";	   
	   try{
		   int rowcount=browser.getRowCountByXpath(pageProperties.getProperty("SearchBill.Table"));
		   
		   System.out.println("rowcount:"+rowcount);
		   if(rowcount==1){
			   Report.updateTestLog("View bill table row count is less than 2 , Test data error","Warn");
		   }
		   verifyAndClickWithXpath((pageProperties.getProperty("SearchBill.Table"))+"//tr/th[1]/strong", "Billing date");
		   for(int i=1;i<rowcount;i++){
			   System.out.println("First for loop:"+i);
			   dateField1=browser.getTextByXpath(pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+i+"]/td[1]/p");
			   System.out.println("xpath value is:"+pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+i+"]/td[1]/p");
			   System.out.println("dateField1:"+dateField1);
			   String lastmod=format.format(new Date(dateField1));
			   System.out.println("lastmod:"+lastmod);
			   Date date1=format.parse(lastmod);
			   System.out.println("Before date displayed");
			   System.out.println("date1:"+date1);
			   for(int j=i+1;j<rowcount;j++){
				   System.out.println("Second for loop");
				   dateField2=browser.getTextByXpath(pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+j+"]/td[1]/p");
				   System.out.println("xpath value is:"+pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+j+"]/td[1]/p");
				   System.out.println("dateField2:"+dateField2);
				   String lastmod1=format.format(new Date(dateField2));
				   Date date2=format.parse(lastmod1);
				   System.out.println("date2:"+date2);
				   if(date1.compareTo(date2)>0){
					   //Report.updateTestLog("Date ("+"i"+")value is :"+date1+"Date ("+"j"+") value is :"+date2,"Pass");
				   }else{
					   //Report.updateTestLog("Date ("+"i"+")value is :"+date1+"Date ("+"j"+") value is :"+date2,"Pass");
				   }
			   }   
		   }   
	   }catch(Exception e){
		   Report.updateTestLog("Exception while trying to fetch the value in application:"+e,"Fail");
	   } 	   
   }

   public void verifyBackToAccountLink(){
	   
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.BackToYourAccount"))){
		   verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.BackToYourAccount"), "Back to your account link");
		   browser.wait(getWaitTime());
		   if(browser.getTitle().equalsIgnoreCase(pageProperties.getProperty("ViewBill.AccountSummaryTitle"))){
		   verifyPageTitle(pageProperties.getProperty("ViewBill.AccountSummaryTitle"));
		   Report.updateTestLog("Clicking 'Back to your account' link works as expected", "PASS");
		   }
		   else{
			   Report.updateTestLog("Clicking 'Back to your account' link works as expected", "FAIL");  
		   }
	   }
	   else{
		   Report.updateTestLog("Back to your account link is not available", "FAIL");
	   }
   }
   public void clickAndVerifyViewYourLastBillLink(){
	   verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.ViewYourLastBillLink"), "View your last bill");
	   browser.wait(getWaitTime());
   }
   public void verifyPDFFile() throws ParseException{
	   File folderExisting = new File(pageProperties.getProperty("ViewBill.DownLoadPath")); 
       if (folderExisting.exists()){  
    	   File[] listOfFiles = folderExisting.listFiles();       
    	   for(int i =0; i<listOfFiles.length; i++){
    		   System.out.println("listOfFiles.length "+listOfFiles.length);
    		   if(listOfFiles[i].getName().contains("Bill")){
    			  PdfReader p = null;			   
    			  try{
    				  p = p.fileReader(pageProperties.getProperty("ViewBill.DownLoadPath")+".pdf");
    				  PdfDocument doc = new PdfDocument(p);
    				  List l = doc.extractText(1);
    				  Report.updateTestLog("Pdf file contains data", "PASS");
    				  System.out.println("PDF FILE CONTENT"+l.toString());
    				  if(l.toString().contains("")){
    					  System.out.println("PASS");
    				  }
    			  }
    			  catch(Exception e){
    				  
    			  }
    		   }
    		   else{
    			   Report.updateTestLog("No files exist in the name of Bill.pdf", "FAIL");
    		   }
    	   }          
       }
       else{
    	   Report.updateTestLog("File does not exist in specified folder", "FAIL");
       }
       
   }
   public void deleteFile(){
	   File folderExisting = new File(pageProperties.getProperty("ViewBill.DownLoadPath")); 
       if (folderExisting.exists()){  
    	   File[] listOfFiles = folderExisting.listFiles();       
    	   for(int i =0; i<listOfFiles.length; i++){
    		   System.out.println("listOfFiles.length "+listOfFiles.length);
    		   if(listOfFiles[i].getName().contains("Bill")){
    			   listOfFiles[i].delete();  
    			   System.out.println("Pdf file got deleted");
    			   Report.updateTestLog("Already existing pdf got deleted "+listOfFiles[i].getName(), "PASS");
    		   }
    	   }          
       } 
       else{
    	   Report.updateTestLog("There is no files exist to delete", "FAIL");
       }
   }
   public void downloadPDF(){
	   verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.DownLoadBill"), "Download bill");
	   browser.wait(getWaitTime());
	   //String testPath;
	   //testPath = "";
	   RobotSendKeys.altS();
       browser.wait(3000);
       RobotSendKeys.typeenter();
       browser.wait(3000);
       Report.updateTestLog("Pdf file get downloaded by clicking enter", "PASS");
   }
   public void deleteFolder(){
	   File folderExisting = new File(pageProperties.getProperty("ViewBill.DownLoadFolder")); 
       if (folderExisting.exists()){  
    	   File[] listOfFiles = folderExisting.listFiles();       
    	   for(int i =0; i<listOfFiles.length; i++){
    		   System.out.println("listOfFiles.length "+listOfFiles.length);
    		   if(listOfFiles[i].getName().contains(pageProperties.getProperty("ViewBill.DownLoadZipFile"))){
    			   listOfFiles[i].delete();  
    			   System.out.println("zip file get deleted");
    			   Report.updateTestLog("Already existing zip files get deleted "+listOfFiles[i].getName(), "PASS");
    		   }
    	   }          
       } 
       else{
    	   Report.updateTestLog("There is no zip files exist to delete", "FAIL");
       }
   }
   public void clickBackLink(){
	   
	   verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.BackLink"), "Back to Your account");
	   browser.wait(getWaitTime());
   }
   public void verifyAccountOverviewTitle(){
	   
	   verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.Table"), "Back to Your account");
	   
   }
    

   public void openAndVerifyZipFile() throws ParseException{
	   File folderExisting = new File(pageProperties.getProperty("ViewBill.DownLoadFolder")); 
       if (folderExisting.exists()){ 
    	   Report.updateTestLog("Download folder exist", "DONE");
    	   try{
    	   File[] listOfFiles = folderExisting.listFiles();       
    	   for(int i =0; i<listOfFiles.length; i++){
    		   System.out.println("listOfFiles.length "+listOfFiles.length);
    		   if(listOfFiles[i].getName().contains(pageProperties.getProperty("ViewBill.DownLoadZipFile"))){
    			   System.out.println("Folder contains zip file");
    			   Report.updateTestLog("Download folder contains the "+pageProperties.getProperty("ViewBill.DownLoadZipFile")+" file", "PASS");
    		   }    		   
    	   } 
    	   }
    	   catch(Exception e){
    		   Report.updateTestLog("Exception occured "+e, "FAIL");
    	   }
       }
       else{
    	   Report.updateTestLog("File does not exist in specified folder", "FAIL");
       }
       
   }
   public void verifySortingPaymentTypeBillTable(){
	   verifyAndClickWithXpath((pageProperties.getProperty("SearchBill.Table"))+"//tr/th[2]/strong", "Payment type");
  
	   String dateField1="";
	   String dateField2="";	   
	   try{
		   int rowcount=browser.getRowCountByXpath(pageProperties.getProperty("SearchBill.Table"));
		   
		   System.out.println("rowcount:"+rowcount);
		   if(rowcount==1){
			   Report.updateTestLog("View bill table row count is less than 2 , Test data error","Warn");
		   }
		   
		   for(int i=1;i<rowcount;i++){
			   System.out.println("First for loop:"+i);
			   dateField1=browser.getTextByXpath(pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+i+"]/td[1]/p");
			   System.out.println("xpath value is:"+pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+i+"]/td[1]/p");
			   System.out.println("dateField1:"+dateField1);
			   String lastmod=format.format(new Date(dateField1));
			   System.out.println("lastmod:"+lastmod);
			   Date date1=format.parse(lastmod);
			   System.out.println("Before date displayed");
			   System.out.println("date1:"+date1);
			   for(int j=i+1;j<rowcount;j++){
				   System.out.println("Second for loop");
				   dateField2=browser.getTextByXpath(pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+j+"]/td[1]/p");
				   System.out.println("xpath value is:"+pageProperties.getProperty("SearchBill.Table")+"/tbody/tr["+j+"]/td[1]/p");
				   System.out.println("dateField2:"+dateField2);
				   String lastmod1=format.format(new Date(dateField2));
				   Date date2=format.parse(lastmod1);
				   System.out.println("date2:"+date2);
				   if(date1.compareTo(date2)>0){
					   Report.updateTestLog("Date ("+"i"+")value is :"+date1+"Date ("+"j"+") value is :"+date2,"Pass");
				   }else{
					   Report.updateTestLog("Date ("+"i"+")value is :"+date1+"Date ("+"j"+") value is :"+date2,"Pass");
				   }
			   }   
		   }   
	   }catch(Exception e){
		   Report.updateTestLog("Exception while trying to fetch the value in application:"+e,"Fail");
	   } 	   
   }
   public void verifyFromToDateError(SMRAccountDetails smrProfile,int status){
	   String errormsgvalue="";
	   switch (status){		
		case 0:
			clickSearchBillSearchButton();
			errormsgvalue=verifyErrorMessageViewBill("error");	
			System.out.println("error msg2"+errormsgvalue);
			Report.updateTestLog("Expected Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_EmailAddIncorrectFormat)?"Pass":"Fail");
			break;
		case 1:
			enterFutureFromPastToDate();
			clickSearchBillSearchButton();
			errormsgvalue=verifyErrorMessageViewBill("error1");	
			System.out.println("error msg2"+errormsgvalue);
			Report.updateTestLog("Expected Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.trim().contains(errormsg.Register_EmailAddIncorrectFormat)?"Pass":"Fail");
			break;
	   }
   }
   public String verifyErrorMessageViewBill(String err){
	   
	   String errormessage="";
	   try{	
		    if(err.equals("error")){
		    	errormessage=browser.getTextByXpath(pageProperties.getProperty("SearchBill.ErrorMessage"));
		    }else{
		    	errormessage=browser.getTextByXpath(pageProperties.getProperty("SearchBill.ErrorMessage1"));
		    }			
			System.out.println("error msg1"+errormessage);
	   }catch(Exception e){
		   Report.updateTestLog("Exception while trying to fetch the error message :"+e,"Fail");
	   }	   
	  return errormessage;
   }
   public void enterFutureFromPastToDate(){
	   int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);
		System.out.println("joda day is:"+day);
		verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.FromDate"), "FromDate");
		calenderDate(day);
		verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.ToDate"),"Todate");
		day=Integer.toString(days-1);
		calenderDate(day);
   }
   public void enterSearchCriteria(){
	  	String[] searchCriteria = {"600256400","","6002a*56492"};
	  	for(int i=0;i<searchCriteria.length;i++){
	  		verifyAndInputById(pageProperties.getProperty("SearchBill.SearchCriteria"), "Search Criteria", searchCriteria[i]);	  	
			enterFromDate(3);
			enterToDate(0);
			clickSearchBillSearchButton();
	  		switch(i){	  		
	  		case 0:	  		
	  			errorMessageComparison(SlingshotErrorMessages.SlingShot_VB_InvalidAccountNumber);
	  			break;
	  		case 1:		  		
				errorMessageComparison(SlingshotErrorMessages.SlingShot_VB_EmptyAccountNumber);
				break;
	  		case 2:		  		
				errorMessageComparison(SlingshotErrorMessages.SlingShot_VB_AccountNumberwithSplChar);
				break;
	  		}
	  	}
   }

// Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
    public void errorMessageComparison(final String expectedErrorMsg) {
    	try{
        final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("ViewBill.ErrorList"));
        verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);
    	}
    	catch(Exception e){
    		//Report.updateTestLog("Exception "+e, "FAIL");
    		final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("ViewBill.ErrorPath"));
            verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);
    	}
    }
//  Validation for Inappropriate data for the fields displayed in Forgot Email Compare the error message displayed in the 
//  application against the Expected data  	 
     public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
    	 System.out.println(displayedErrorMsg);
    	 System.out.println(expectedErrorMsg);
         if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
             Report.updateTestLog("Error message validation is done. Expected error message: "+ expectedErrorMsg+" Displayed error message: "+ displayedErrorMsg, "PASS");
         } else {
        	 Report.updateTestLog("Error message validation is done. Expected error message: "+ expectedErrorMsg+" Displayed error message: "+ displayedErrorMsg, "FAIL");
         }
     }
     public void selectMaxNoOfBills(){
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.TablePath"))){
    		 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.AllItemsLink"))){
    			 verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.AllItemsLink"), "All link");
    		 }
    		 int maxNumberofBill = 11;
    		 String recordsInfo = browser.getTextByXpath(pageProperties.getProperty("ViewBill.NoOfrecords"));
    		 System.out.println("Total number of transactions are1 "+recordsInfo);
    		 String[] noOfrecord = recordsInfo.split("of");
    		 System.out.println("Total number of transactions are2 "+noOfrecord[0]+noOfrecord[1]);
    		 String[] recordCount = noOfrecord[1].split("transactions");
    		// int count = Integer.parseInt(recordCount[0]);
    		 Report.updateTestLog("Total number of transactions are "+recordCount[0].trim(), "PASS");
    		 System.out.println("Total number of transactions are "+recordCount[0].trim());
    		 for(int i=1;i<=maxNumberofBill;i++){
    			 verifyAndSelectCheckBoxByXpath(pageProperties.getProperty("ViewBill.CheckBox")+i+"]/td[7]/p/input", "Bill "+i);
    			 System.out.println("Number of bills selected "+i);
    			 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.OverlayXpath"))){
     	    		Report.updateTestLog("Overlay is getting displayed while selecting morethan 10 records", "PASS");
     	    		break;
     	    	}
    		 }
    		 
    	 }
     }
    public void verifyandCloseOverlay(){
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.OverlayXpath"))){
    		Report.updateTestLog("Overlay is getting displayed while selecting morethan 10 records", "PASS");
    		String overlayText = browser.getTextByXpath(pageProperties.getProperty("ViewBill.OverlayContent"));
        	Report.updateTestLog("Overlay displayed with "+overlayText +" text", "DONE");
        	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.OverlayCloseButton"))){
        		verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.OverlayCloseButton"), "Overlay Close button");
        		Report.updateTestLog("Overlay closed as expected", (!browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.OverlayCloseButton")))?"PASS":"FAIL");
        	}
    	}
    	else{
    		Report.updateTestLog("Overlay is getting displayed while selecting morethan 10 records", "FAIL");
    	}    	
    }
    
    public void verifyAndDownLoadFile(){
    	 for(int i=1;i<=10;i++){
			 verifyAndSelectCheckBoxByXpath(pageProperties.getProperty("ViewBill.CheckBox")+i+"]/td[7]/p/input", "Bill "+i);
			 System.out.println("Number of bills selected "+i);
			 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.OverlayXpath"))){
 	    		Report.updateTestLog("Overlay is getting displayed while selecting lessthan 10 records", "FAIL");
 	    		break;
 	    	}}
			 verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.DownloadButton"), "Download button");
			 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SearchBill.ErrorMessage1"))){
				
				 String errorMessage = browser.getTextByXpath(pageProperties.getProperty("ViewBill.ErrorList"));
				 Report.updateTestLog("Error getting displayed while downloading the files. Displayed error: "+errorMessage, "WARN");
			 }
		   browser.wait(getWaitTime());
		   //String testPath;
		   //testPath = "";
		   RobotSendKeys.altS();
	       browser.wait(3000);
	       RobotSendKeys.typeenter();
	       RobotSendKeys.typeenter();
	       browser.wait(3000);
	       Report.updateTestLog("Pdf file get downloaded by clicking enter", "PASS");
		 }
    
    public void verifyDataSortingFields(){
    	String billNumberBefore = browser.getTextByXpath(pageProperties.getProperty("ViewBill.BillingNumber1stColumn"));
    	verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.BillingNumberColumn"), "Billing date column");
    	String billNumberAfter = browser.getTextByXpath(pageProperties.getProperty("ViewBill.BillingNumber1stColumn"));
    	if(billNumberBefore!=billNumberAfter){
    		Report.updateTestLog("Billing number column sorted and values verified. Value before sorting: "+billNumberBefore+"Value after sorting: "+billNumberAfter, "PASS");
    	}
    }
    public void verifyAuditTable(SMRAccountDetails smrProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"SLINGSHOT");
		String data = dbFunctions.getAuditType(auditType[0]);	
	    Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("LOGIN_SUCCESSFUL")?"PASS":"FAIL");
	
	}
    public void verifyAuditTableLoginTimestamp(SMRAccountDetails smrProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String data = dbFunctions.verifyLastLoginTimeStamp(date, smrProfile.getEmail());
	    Report.updateTestLog("Audit id is made in audit table as expected. Last login Timestamp: "+data, data.equalsIgnoreCase("1")?"PASS":"FAIL");
	}
    public void viewYourbillRHS(){
    	verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.ViewYourBilllink"),"view your bill link in RHS");
    	browser.wait(getWaitTime());
    	verifyPageTitle(pageProperties.getProperty("ViewBill.ViewYourBillTitle"));
    }
    public void EnergyMadeSimple(){
    	verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.EnergyMadeSimple"),"Energy Made Simple Title link in view bill page");
    	verifyPageTitle(pageProperties.getProperty("ViewBill.EnergyMadeSimpleTitle"));
    	browser.browserBack();
    }
    
    
    /*******April 20 Release******/
    
    
    
    public void verifyAndClinkBillingLink(){
    	verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.BillingLink"),"Billing link");
    	browser.wait(getWaitTime());
    	verifyPageTitle(pageProperties.getProperty("ViewBill.ViewYourBillTitle"));
    	verifyAndClickWithXpath(pageProperties.getProperty("ViewBill.ViewYourLatestBill"),"Latest Bill link");
    	browser.wait(getWaitTime());
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.ViewYourLatestBillPage"))){
    		Report.updateTestLog("User able to view the View bill page ", "PASS");
    	}
    	else{
    		Report.updateTestLog("User able to view the View bill page ", "FAIL");
    	}
    }
    public void verifyViewBillDue(){
    	browser.wait(10000);
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ViewBill.ViewDue"))){
    		Report.updateTestLog("View bill due is displayed in the View bill page ", "PASS");
    	}
    	else {
    		Report.updateTestLog("View bill due is not displayed in the View bill page ", "FAIL");
    	}   	
    	
    	    }
    
    public void verifyviewbillpagetitlesection(SMRAccountDetails smrProfile)
    {
    	browser.wait(5000);
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.Newtitle")))
    			{
    		System.out.println("Eneter View Bill Page Operation");
    		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    		
    		        verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.Newtitle"), "View Bill Title"); 
    		        browser.wait(2000);
    		        String accno = browser.getTextByXpath(".//*[@class='viewbill-acc-sec']/div/div/div[2]/p[1]/span");
    		    	String existingaccno = String.valueOf(smrProfile.getAccountNumber());
    		    	String accno1 = String.valueOf(accno);
    		    	
    		    	System.out.println(accno);    		    	
    		    	System.out.println("Existing acc:::"+existingaccno);
    		    	browser.wait(2000);
    		    	if(existingaccno.equals(accno1))
    		    	{
    		    		System.out.println("Check in account number verify");
    		    		Report.updateTestLog("Account number Matched with View Bill Account number :" +accno, "Pass");
    		    		verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.newAddresss"), "Address");    		    		
    		    		verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.notoficationduepanel"),"View Bill Due Section");
    		    	}
    			}   	
    }
    
    public void verifyviewbillapybalancesection(SMRAccountDetails smrProfile)
    {
          browser.wait(5000);
    	 String balance = browser.getTextByXpath(".//*[@class='viewBillHead']/p[3]/strong");
    	 String Acctype = browser.getTextByXpath(".//*[@class='viewBillHead']/p[4]/strong");
    	 
    	 String balance1 = String.valueOf(balance);
    	 
    	 System.out.println("&&&*&*& Balance" +balance1);
    	 browser.wait(2000);
    	 
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.NewAmountsection")))
    	{
    		System.out.println("checking balance section");
    		Report.updateTestLog("Available balance is :£" +balance  +Acctype, "PASS");
    	}
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.paymentnewlink").replaceAll("ACCOUNTNUMBER",smrProfile.getAccountNumber())))
		{ 
			verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.paymentnewlink").replaceAll("ACCOUNTNUMBER",smrProfile.getAccountNumber()),"Global Pay Balance");
		    browser.wait(3000);
		    verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.paymentpagetitle"),"Payment Details Page");
		    browser.browserBack();
	        browser.wait(2000);
		}    
    }
    
    public void verifyviewbilltablenewHeader()
    {
    	String header[] = {"Date","Bill type","Bill number","Amount","View bill"};
    	
    	System.out.println("Size of header section"+header.length);
    	
    	for(int i = 1; i<=header.length;i++)
    	{
    		System.out.println("Entered Loop");
    		if(header.equals(pageProperties.getProperty("Viewbill.NewViewBillheadersection").replace("heading", ""+i)))
    			
    		System.out.println("Condition pass");
    		
    		verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.NewViewBillheadersection").replace("heading", ""+i),"Audit Title"+i);
    	}  	
      }
    
    public void verifyviewbilltablenewcontents()
    {
    	int tableSize = browser.getChildElementsCountByXpath(pageProperties.getProperty("Viewbill.Newtablecount"));    	
    	//float rowlenght = Float.valueOf(rowcount.length());    	
    	System.out.println("Audit table Row Count is:" +tableSize);    	
    	Report.updateTestLog("View Bill table Presented Rows are:" +tableSize, "WARN");    	 
    try
    {
    	for(int j=2; j<=tableSize; j++)
    	{
    		 browser.wait(3000);
    		String billno = browser.getTextByXpath(".//*[@id='auditTable']/tbody/tr["+j+"]/td[3]");
    		
    		 verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.NewViewbilltalelink").replaceAll("viewbilllink", ""+j),"View Bill Clicked for ::"+billno);
    		 browser.wait(8000);
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.OverlayHeadersection"),"Overlay Header Section ");
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.OverlayDownloadsection"),"Overlay Download Section");
    		 
    		 Report.updateTestLog("View bill Overlay Displayed success fully for "+billno, "WARN");
    		 browser.wait(5000);
    		 verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.OverlayClose"),"View Bill Closed");
    		
    	}    	
    }
       catch (Exception e)
    {
    	Report.updateTestLog("View Bill Not displayed Succesfully", "FAIL");
    }    	
         }
    
    public void verfiypagenation()
    
    {
    		verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.Pagenavigationsection"),"Page navigation");
    		
    		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.Pagenavigationnextsection")))
    				{
    			        verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.Pagenavigationnextsection"),"Next object clicked successfully");
    			        browser.wait(2000);  
    			        
    			        int tableSize2 = browser.getChildElementsCountByXpath(pageProperties.getProperty("Viewbill.Newtablecount"));  	
    			    	//float rowlenght = Float.valueOf(rowcount.length());    	
    			    	System.out.println("Second Audit table Row Count is:" +tableSize2);    	
    			    	Report.updateTestLog("View Bill table Presented Rows are:" +tableSize2, "WARN");    	 
    			        try
    			        {
    			        	for(int j=1; j<=tableSize2; j++)
    			        	{
    			        		 browser.wait(3000);
    			        		String billno = browser.getTextByXpath(".//*[@id='auditTable']/tbody/tr["+j+"]/td[3]");
    			        		
    			        		 verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.NewViewbilltalelink").replaceAll("viewbilllink", ""+j),"View Bill Clicked for ::"+billno);
    			        		 browser.wait(5000);
    			        		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.OverlayHeadersection"),"Overlay Header Section ");
    			        		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.OverlayDownloadsection"),"Overlay Download Section");
    			        		 
    			        		 Report.updateTestLog("View bill Overlay Displayed success fully for "+billno, "WARN");
    			        		 browser.wait(5000);
    			        		 verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.OverlayClose"),"View Bill Closed");
    			        		
    			        	}    	
    			        }
    			           catch (Exception e)
    			        {
    			        	Report.updateTestLog("View Bill Not displayed Succesfully", "FAIL");
    			        }    	    
    			        
    				}
    		else
    		{
    			Report.updateTestLog("page navigation is not displayed in the application", "Pass");
    		}
    }
    
    public void verifyViewBillnewLinks(SMRAccountDetails smrProfile)
    {
    	browser.wait(2000);
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.pagenavigationrightsection")))
    	{
    		if(browser.isTextPresent("Pay your bill"))
    		{
    			verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.pagenavigationRHN_paylink").replaceAll("ACCOUNTNUMBER",smrProfile.getAccountNumber()),"Pay Your bill");  
    	        browser.wait(3000);
    	        verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.paymentpagetitle"),"Payment Details Page");
    	        Report.updateTestLog("Payment page", "WARN");
    	        browser.browserBack();
    	        browser.wait(2000);
    		}
    		
    		if(browser.isTextPresent("Was your bill estimated?"))
    		{
    			verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.pagenavigationRHN_SMRlink").replaceAll("ACCOUNTNUMBER",smrProfile.getAccountNumber()),"Submit Meter Read");  
    	        browser.wait(3000);
    	        verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.SMRpagetitle"),"Submit meter read Page");
    	        Report.updateTestLog("Submit Meter read Page", "WARN");
    	        browser.browserBack();
    	        browser.wait(2000);
    		}
    		
    		if(browser.isTextPresent("View your Online statement"))
    		{
    			verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.pagenavigationRHN_Statementlink").replaceAll("ACCOUNTNUMBER",smrProfile.getAccountNumber()),"Copy Your Statement");  
    	        browser.wait(3000);
    	        verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.Statementpagetitle"),"Statement of account");
    	        Report.updateTestLog("Statement of account Page", "WARN");
    	        browser.browserBack();
    	        browser.wait(2000);
    		}
    		
    		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.NewSearchbill")))
    		{
    			verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.NewSearchbill"),"Search bill Link");  
    	        browser.wait(3000);
    	        verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.Viewbill_pagetitle"),"Search bill");
    	        Report.updateTestLog("Statement of account Page", "WARN");
    	        browser.browserBack();
    	        browser.wait(2000);
    		}
    	}
    		
    }
    	
    public void verifyGlobalAccountsection_paybalance(SMRAccountDetails smrProfile)
    {   
    	
    	try
    	{
    		        	
        	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Viewbill.NewRACB")))
        	{
        		verifyAndClickWithXpath(pageProperties.getProperty("Viewbill.NewRequestacopybill"),"Request A Copy bill Link");
        		browser.wait(3000); 
        		verifyIsElementVisibleWithXpath(pageProperties.getProperty("Viewbill.RequestaCopybill_pagetitle"),"Request A Copy bill Page");
    	        Report.updateTestLog("Request A Copy bill Link Page", "WARN");
    	        browser.browserBack();
    	        browser.wait(2000);
        	}
        	   				
    		
    	}
    	catch (Exception e)
    	{
    		Report.updateTestLog("Global paybalance button and Request a copy bill is not displayed due to Manage DD", "Pass");
    	}    		
    	
    	
    }
    
}
