/**
 * 
 */
package bg.framework.app.functional.page.tetris;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.joda.time.DateTime;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.TetrisErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

/**
 * @author 292238
 *
 */
public class CopyBillRequestPage extends BasePage{
	private final static String FILE_NAME = "resources/tetris/CopyBillRequest.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	TetrisErrorMessages errormsg = new TetrisErrorMessages();

 public void openCopyBillrequestPage(){
	 browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("CopyBillRequest.Url"));
	 browser.wait(getWaitTime());
 }
 
 public void enterValuesInCopyBillRequestPage(UserProfile userProfile){
	 Date date= new Date();
	 //verifyAndSelectDropDownBox(pageProperties.getProperty("CopyBillRequest.Title"), "Title", userProfile.getTitle());		
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.fullname"), "Full name",userProfile.getFirstName());
	// verifyAndInputById(pageProperties.getProperty("CopyBillRequest.surname"), "Sur name",userProfile.getLastName());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.companyname"), "Company name",userProfile.getCompanyName());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.contactnumber"), "Phone number",userProfile.getPhoneNumber());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.email"), "Email address",userProfile.getEmail());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.AccountNumber"), "Account Number", userProfile.getAccNumber());
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.FromDatePicker"), "From date picker");
	 
		int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);
		System.out.println("joda day is:"+day);
		String result=calenderDate(day);
		
		if(result.equals("False")){
		   verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.NextDatePicker"), "Next Date Picker");	
		   calenderDate(day);
		   String strFromdate = browser.getTextByXpath(pageProperties.getProperty("CopyBillRequest.billFromDate"));
		Report.updateTestLog("From date filled with :"+strFromdate, "PASS");
			
		}
		 verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.ToDatePicker"), "To date picker");
		 String result1=calenderDate(day);
			if(result1.equals("False")){
			   verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.NextDatePicker"), "Next Date Picker");	
			   calenderDate(day);
			   String strFromdate = browser.getTextByXpath(pageProperties.getProperty("CopyBillRequest.billToDate"));
			   Report.updateTestLog("To date filled with :"+strFromdate, "PASS");
				
			}	
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.billAmount"), "Bill amount", userProfile.getAnnualSpend());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.billReferenceNumber"), "Bill reference number	", userProfile.getAccNumber()+761);		
	
 }
 public void clickSubmit(){
	 verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.submitButton"), "Submit");
	 browser.wait(getWaitTime());
 }
 public String calenderDate(String day){
		String result="False";
		int rowcount=browser.getRowCountByXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable"));
		int columncount=browser.getColCountByXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable"));
		for(int i=1;i<=rowcount;i++){
			for(int j=1;j<=columncount;j++){
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable")+"//tr["+i+"]/td["+j+"]"+
						"[contains(@class,'disabled')]")){
					browser.clickWithXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable")+"//tr[1]/td[5]/a");
					System.out.println("empty xpath is");
					
				}else{
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable")+"//tr["+i+"]/td["+j+"]/a")){						
					String dateThreeMonths=browser.getTextByXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable")+"//tr["+i+"]/td["+j+"]/a");
					if(dateThreeMonths.equals(day)){
						Report.updateTestLog("Day is matched:"+day,"pass");
						browser.clickWithXpath(pageProperties.getProperty("CopyBillRequest.CalenderTable")+"//tr[1]/td[5]/a");
						result="True";
						break;
					}
				 }
				}
			}
			
		}
		return result;
	}
 
 public void verifyThankYouPage(){
	// verifyIsElementVisibleWithXpath(pageProperties.getProperty("LargeBusiness.ThankYou"),"Thank you page");
	 verifyIsTextPresent(pageProperties.getProperty("CopyBillRequest.ThankYou"),"Thank you page");
 }
 
 public void verifyDBDetails(UserProfile userProfile){
	 OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String leadType[] = dbFunctions.verifyleadDBForLeadType(date,userProfile.getEmail(),"CopyBill");		
		Report.updateTestLog("Lead type is "+leadType[0] +". Hence email sent to customer", leadType[0].equalsIgnoreCase("0")?"PASS":"FAIL");
		Report.updateTestLog("Lead status is "+leadType[1] +".", leadType[1].equalsIgnoreCase("CopyBill")?"PASS":"FAIL");
		//Report.updateTestLog("Title validation is done."+leadType[2], leadType[2].equals(userProfile.getTitle())?"PASS":"FAIL");
		//Report.updateTestLog("First name validation is done."+leadType[3], leadType[3].equals(userProfile.getFirstName())?"PASS":"FAIL");
		Report.updateTestLog("Sur name validation is done."+leadType[4], leadType[4].equals(userProfile.getFirstName())?"PASS":"FAIL");
		Report.updateTestLog("Company name validation is done."+leadType[5], leadType[5].equals(userProfile.getCompanyName())?"PASS":"FAIL");
		Report.updateTestLog("Contact number validation is done."+leadType[6], leadType[6].equals(userProfile.getPhoneNumber())?"PASS":"FAIL");
		Report.updateTestLog("Email address validation is done."+leadType[13], leadType[13].equals(userProfile.getEmail())?"PASS":"FAIL");
		Report.updateTestLog("Account number validation is done."+leadType[16], leadType[16].equals(userProfile.getAccNumber())?"PASS":"FAIL");
		Report.updateTestLog("Bill from date validation is done."+leadType[17], leadType[17].equalsIgnoreCase("null")?"FAIL":"PASS");
		Report.updateTestLog("Bill To date validation is done."+leadType[18],leadType[18].equalsIgnoreCase("null")?"FAIL":"PASS");
		
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"CopyBill");
		String data = dbFunctions.getAuditType(auditType[0]);	
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("COPYBILL_CUSTOMER_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
		
 }
 public void verifyAddAnotherAccount(){
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("CopyBillRequest.AddAnotherAccount"))){
	 verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.AddAnotherAccount"), "Add another account");	 
	 }
 }
 public void verifyRequestedBill(){
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("CopyBillRequest.DisplayDiv1"))){
		 Report.updateTestLog("Copy statement 1 requested ", "PASS");
	 }
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("CopyBillRequest.DisplayDiv2"))){
		 Report.updateTestLog("Copy statement 2 requested ", "PASS");
	 }
 }
 public void validateFieldsInTrainingContactUs(UserProfile userProfile){
	   String[] billRefNumber = {"4564ab7688","4564`,./'<>}{~!@#$%^&*()7688","45 64"};	   
	   String[] billAmount = {"abcdedfdf","4564ab","4564`,./'<>}{~!@#$%^&"};	   
	  	for(int i = 0; i<billRefNumber.length;i++){
		switch(i){
		case 0:
			enterFieldValidationData(userProfile,billRefNumber[i],billAmount[i]);
			clickSubmit();
			String errormsg[] = {TetrisErrorMessages.Tetris_EmptyTown,TetrisErrorMessages.Tetris_EmptyCourse};
			errorMessageComparison(errormsg);	    			
			break;
		case 1:
			enterFieldValidationData(userProfile,billRefNumber[i],billAmount[i]);
			clickSubmit();
			String IncorrectErrormsg[] = {TetrisErrorMessages.Tetris_IncorrectTownName,TetrisErrorMessages.Tetris_IncorrectCourse};
			errorMessageComparison(IncorrectErrormsg);
			break;  		
		
	  	default:
	  		enterFieldValidationData(userProfile,billRefNumber[i],"Functional");
	  		clickSubmit();
				String IncorrectErrormsg1[] = {TetrisErrorMessages.Tetris_IncorrectTownName};
				errorMessageComparison(IncorrectErrormsg1);
				break; 	
			  	
		}
	
	}
}
 public void enterFieldValidationData(UserProfile userProfile,String billNumber, String billAmount){	
	// verifyAndSelectDropDownBox(pageProperties.getProperty("CopyBillRequest.Title"), "Title", userProfile.getTitle());		
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.fullname"), "Full name",userProfile.getFirstName());
	 //verifyAndInputById(pageProperties.getProperty("CopyBillRequest.surname"), "Sur name",userProfile.getLastName());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.companyname"), "Company name",userProfile.getCompanyName());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.contactnumber"), "Phone number",userProfile.getPhoneNumber());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.email"), "Email address",userProfile.getEmail());
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.AccountNumber"), "Account Number", userProfile.getAccNumber());
	 verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.FromDatePicker"), "From date picker");
		int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);		
		String result=calenderDate(day);
		if(result.equals("False")){
		   verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.NextDatePicker"), "Next Date Picker");	
		   calenderDate(day);
		   String strFromdate = browser.getTextByXpath(pageProperties.getProperty("CopyBillRequest.billFromDate"));
		   Report.updateTestLog("From date filled with :"+strFromdate, "PASS");
			
		}
		 verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.ToDatePicker"), "To date picker");
		 String result1=calenderDate(day);
			if(result1.equals("False")){
			   verifyAndClickWithXpath(pageProperties.getProperty("CopyBillRequest.NextDatePicker"), "Next Date Picker");	
			   calenderDate(day);
			   String strFromdate = browser.getTextByXpath(pageProperties.getProperty("CopyBillRequest.billToDate"));
			   Report.updateTestLog("To date filled with :"+strFromdate, "PASS");
				
			}	
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.billAmount"), "Bill amount", billAmount);
	 verifyAndInputById(pageProperties.getProperty("CopyBillRequest.billReferenceNumber"), "Bill reference number	", billNumber);		
	
 }
//Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
public void errorMessageComparison(final String expectedErrorMsg[]) {
	try{
		for(int i=0;i<expectedErrorMsg.length; i++){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("MarketingConsent.ErrorMsgPath"))){
		        final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("MarketingConsent.ErrorMsgPath"));
		        verifyErrorMessage(displayedErrorMsg, expectedErrorMsg[i]);
		        }
			else{
				Report.updateTestLog("Error message is not displayed for this scenario", "FAIL");
			}
		       }
		}
	catch(Exception e){
		//Report.updateTestLog("Exception occured"+e, "FAIL");
	}
}


//Validation for Inappropriate data for the fields displayed in Forgot Email Compare the error message displayed in the application against the Expected data  	 
public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
	
  if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
      Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "PASS");
  } else {
 	 Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "FAIL");
  }
}

}
