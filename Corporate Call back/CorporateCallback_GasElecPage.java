/**
 * 
 */
package bg.framework.app.functional.page.tetris;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.page.common.TetrisErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

/**
 * @author 292238
 *
 */
public class CorporateCallback_GasElecPage extends BasePage{

	private final static String FILE_NAME = "resources/tetris/Common.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	private final static String FILE_NAME1 = "resources/tetris/CorporateCallBack.properties";
	private static Properties callBackPageProperties = new PropertyLoader(FILE_NAME1).load();

	public void gotoGasAndElecMenu(){
		/*verifyAndClickWithXpath(pageProperties.getProperty("BGB.GotoBusinessLink"), "Go to our Business site");
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("BGB.CorporateLink"),"Corporate");
		browser.wait(getWaitTime());		
		verifyAndClickWithXpath(pageProperties.getProperty("Common.GasAndElecMenu"),"Gas & electricity");
		browser.wait(6000);*/
		browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("BGB.CorporateGasAndElectricityLink"));		
		browser.wait(getWaitTime());
	}
	public void gotoSmartWorkingMegaMenu(){
		//verifyAndClickWithXpath(pageProperties.getProperty("BGB.GotoBusinessLink"), "Go to our Business site");
		//browser.wait(getWaitTime());
		//verifyAndClickWithXpath(pageProperties.getProperty("BGB.CorporateLink"),"Corporate");
		//browser.wait(getWaitTime());	
		browser.open(ApplicationConfig.APP_BG_URL+"/corporate");
		browser.wait(getWaitTime());	
		verifyAndClickWithXpath(pageProperties.getProperty("Common.SmarterWorkingMenu"),"Smarter working");
		browser.wait(6000);
	}
	public void openLink(String strLink){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BGB.FixedPriceLink")+strLink+"')]")){
		verifyAndClickWithXpath(pageProperties.getProperty("BGB.FixedPriceLink")+strLink+"')]", strLink);
		browser.wait(getWaitTime());
		}
		else{
			verifyAndClickWithXpath(pageProperties.getProperty("BGB.Link")+strLink+"')]", strLink);
			browser.wait(getWaitTime());
		}
	}
	public void openSmarterWorkingLink(String strLink,int value){
		switch(value){
		case 0:
		verifyAndClickWithXpath(pageProperties.getProperty("BGB.IndustryInsightLink"), "Industry insight");
		browser.wait(getWaitTime());
		break;
		case 1:
		verifyAndClickWithXpath(pageProperties.getProperty("BGB.CaseStudiesLink"), "Case studies");
		browser.wait(getWaitTime());
		break;
		}
		if(browser.isElementVisibleWithXpath(callBackPageProperties.getProperty("Callback.LeftHandnavigation")+strLink+"')]")){
		verifyAndClickWithXpath(callBackPageProperties.getProperty("Callback.LeftHandnavigation")+strLink+"')]", strLink);
		browser.wait(getWaitTime());
		}
		else{
			verifyAndClickWithXpath(pageProperties.getProperty("BGB.Link")+strLink+"')]", strLink);
			browser.wait(getWaitTime());
		}
	}
	public void verifyFixedPricePage(UserProfile userProfile,String strLink){
		if(browser.isTextPresent(pageProperties.getProperty("FixedPricePage.PageTitle"))){		
			verifyIsTextPresent(pageProperties.getProperty("FixedPricePage.PageTitle"), strLink);
			validateCallBackPage(userProfile,strLink);
			verifyIsTextPresent(pageProperties.getProperty("FixedPricePage.PageTitle"), strLink);	
			verifyAndCloseThankYouOverlay(strLink);			
		}
		else{
			Report.updateTestLog("Fixed price page displayed as expected", "FAIL");
		}
		verifyAndClickWithXpath(pageProperties.getProperty("Common.GasAndElecMenu"),"Gas & electricity");
		browser.wait(6000);
	}
	public void validateCallBackPage(UserProfile userProfile,String strLink){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BGB.CallmebcakLink"))){
		verifyAndClickWithLinkText(pageProperties.getProperty("Callback.CallBackLink"), "Call back");
		browser.wait(getWaitTime());
		verifyAndEnterCallBackPage(userProfile,strLink);			
		verifyAndCloseThankYouOverlay(strLink);}
		else{
			Report.updateTestLog("Call back link is not available", "DONE");
		}
		if(browser.isElementVisibleWithXpath(callBackPageProperties.getProperty("Callback.RequestaCallBackLink"))){
		verifyAndClickWithXpath(callBackPageProperties.getProperty("Callback.RequestaCallBackLink"), "Request a call back");
		browser.wait(getWaitTime());
		verifyAndEnterCallBackPage(userProfile,strLink);
		verifyAndCloseThankYouOverlay(strLink);}
		else{
			Report.updateTestLog("Request call back link is not available", "DONE");
		}
	}
	
	public void verifyAndEnterCallBackPage(UserProfile userProfile,String strLink){
		if(browser.isTextPresent(callBackPageProperties.getProperty("Callback.RequestCallBack"))){
			if(browser.isTextPresent(callBackPageProperties.getProperty("Callback.BGBTitle")+strLink)){
			//verifyIsTextPresent(callBackPageProperties.getProperty("Callback.BGBTitle")+strLink, strLink);
			}
			verifyAndSelectDropDownBox(callBackPageProperties.getProperty("Callback.Title"), "Title", userProfile.getTitle());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.firstname"), "FirstName", userProfile.getFirstName());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.surname"), "SurName", userProfile.getLastName());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.BusinessName"), "BusinessName", userProfile.getCompanyName());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.email"), "Email", userProfile.getEmail());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.telephoneNumber"), "ContactNumber", userProfile.getPhoneNumber());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.postCode"), "PostCode", userProfile.getPostCode());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.annualspend"), "AnnualSpend", userProfile.getAnnualSpend());
			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.companyregistrationnumber"), "CompanyRegistrationNumber", userProfile.getRegisterationNumber());
			verifyAndSelectDropDownBox(callBackPageProperties.getProperty("Callback.Query"), "Query",userProfile.getQuery());
			verifyAndSelectCheckBoxByID(callBackPageProperties.getProperty("Callback.marketingconsent"), "Marketing Consent");
			verifyAndClickWithXpath(callBackPageProperties.getProperty("Callback.SubmitButton"), "Submit button");
			browser.wait(getWaitTime());
			}
		else{
			Report.updateTestLog("Request a callback overlay displayed as expected", "FAIL");
		}
	}
	public void verifyAndCloseThankYouOverlay(String strLink){
		//verifyIsTextPresent(callBackPageProperties.getProperty("Callback.BGBTitle")+strLink, strLink);
		verifyIsTextPresent(callBackPageProperties.getProperty("Callback.ThankYouText"), "Thank you for requesting a callback");
		verifyAndClickWithXpath(callBackPageProperties.getProperty("Callback.CloseButton"), "Close overlay");			
	
	}
	public void verifyFlex247Page(UserProfile userProfile,String strLink){
		if(browser.isTextPresent(callBackPageProperties.getProperty("Callback.FlexpageTitle"))){		
			//verifyIsTextPresent(callBackPageProperties.getProperty("Callback.FlexpageTitle"), "Flex 24/7");
			verifyAndClickWithLinkText(callBackPageProperties.getProperty("Callback.CallBackLink"), "Call back");
			browser.wait(getWaitTime());
			//verifyIsTextPresent(callBackPageProperties.getProperty("Callback.Flex247Title"),"Flex 24/7 page");
			verifyAndEnterCallBackPage(userProfile,strLink);
			verifyAndClickWithXpath(callBackPageProperties.getProperty("Callback.RequestaCallBackLink"), "Request a call back");
			browser.wait(getWaitTime());
			verifyAndEnterCallBackPage(userProfile,strLink);			
		}
		else{
			Report.updateTestLog("Flex 24/7 page displayed as expected", "FAIL");
		}
		verifyAndClickWithXpath(pageProperties.getProperty("Common.GasAndElecMenu"),"Gas & electricity");
		browser.wait(6000);
	}
	public void verifyDB(UserProfile userProfile,String strLink){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();	
		try{
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String leadType[] = dbFunctions.verifyleadDBForLeadType(date,userProfile.getEmail(),"Corporate_Servicecallback_"+strLink);		
		Report.updateTestLog("Lead type is "+leadType[0] +". Hence email sent to both the customer", leadType[0].equals("0")?"PASS":"FAIL");
		strLink = strLink.replaceAll(" ", "_");
		if(leadType[1].equals("Corporate_Servicecallback_"+strLink)){
			Report.updateTestLog("Lead status is "+leadType[1] +"."+"expected: Corporate_Servicecallback_"+strLink, leadType[1].equals("Corporate_Servicecallback_"+strLink)?"PASS":"FAIL");
		}		
		Report.updateTestLog("Title validation is done."+leadType[2], leadType[2].equals(userProfile.getTitle())?"PASS":"FAIL");
		Report.updateTestLog("First name validation is done."+leadType[3], leadType[3].equals(userProfile.getFirstName())?"PASS":"FAIL");
		Report.updateTestLog("Sur name validation is done."+leadType[4], leadType[4].equals(userProfile.getLastName())?"PASS":"FAIL");
		Report.updateTestLog("Company name validation is done."+leadType[5], leadType[5].equals(userProfile.getCompanyName())?"PASS":"FAIL");
		Report.updateTestLog("Contact number validation is done."+leadType[6], leadType[6].equals(userProfile.getPhoneNumber())?"PASS":"FAIL");
		Report.updateTestLog("Post code validation is done."+leadType[7], leadType[7].equals(userProfile.getPostCode())?"PASS":"FAIL");
		Report.updateTestLog("MARKETING_CONSENT validation is done."+leadType[8], leadType[8].equals("Y")?"PASS":"FAIL");
		Report.updateTestLog("TOTAL_ANNUAL_SPEND validation is done."+leadType[9], leadType[9].equals(userProfile.getAnnualSpend())?"PASS":"FAIL");
		Report.updateTestLog("COMMENTS validation is done."+leadType[10], leadType[10].equals(userProfile.getQuery())?"PASS":"FAIL");
		Report.updateTestLog("COMPANY_REGISTRATION_NUMBER validation is done."+leadType[11], leadType[11].equals(userProfile.getRegisterationNumber())?"PASS":"FAIL");
		
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"Corporate_Servicecallback_"+strLink);
		String data = dbFunctions.getAuditType(auditType[0]);	
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equals("SERVICECALLBACK_CUSTOMER_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
		if(auditType[1].contains(userProfile.getEmail())&&auditType[1].contains(userProfile.getPostCode())&&auditType[1].contains(userProfile.getFirstName())){
			Report.updateTestLog("Audit data is made in audit table as expected. Audit entry: "+auditType[1],"PASS");			
		}
		else{
			Report.updateTestLog("Audit data is made in audit table as expected. Audit entry: "+auditType[1],"FAIL");
		}}
		catch(Exception e){
			Report.updateTestLog("Exception occured"+e, "FAIL");
		}
	}
	public void verifyCallBackPage(UserProfile userProfile,String strLink){	
		if(browser.isTextPresent(strLink)){	
			validateCallBackPage(userProfile,strLink);					
		}		
		else{
		//	Report.updateTestLog(strLink+" page displayed as expected", "FAIL");
		}		
	}
	public void clickCallBackPage(UserProfile userProfile){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BGB.CallmebcakLink"))){
		verifyAndClickWithLinkText(pageProperties.getProperty("Callback.CallBackLink"), "Call back");
		browser.wait(getWaitTime());
		validateFirstnameFields(userProfile);	}
		else{
			Report.updateTestLog("Call back link is not available", "DONE");
		}
		
	}
	
	   public void validateFirstnameFields(UserProfile userProfile){
		   String[] strTitle = {"Please select","Please select","Please select","Please select","Please select"};
		   String[] firstName = {"","abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzrererewqwr","Ganthi!@#$%^&*()_+","Ganthi12345mani","F"};
		   String[] lastNmae= {"","abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz","Ganthi!@#$%^&*()_+","Ganthi12345mani","L"};
		   String[] businessName= {"","abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz","Tetris!@#$%^&*()_+","Tetris12345","B"};
		   String[] email = {"","ganthimani.s@cognizant@cognizant.com","ganthimani!s@cts.com","123@123.123","123@123.123"};
		   String[] contactNumber = {"","0987a65432","098!@#$%342","098781312653623576","098781312653623576"};
		   String[] postCode = {"","e11 3po","e11$ 3bh","e11 3bhe11 3bh","e11 3bhe11 3bh"};
		   String[] annualSpend = {"1234abcd","1234!@#$","242367788745","242367788745","242367788745"};
		   String[] registrationNumber = {"1234abcd","1234!@#$","242367788745","242367788745","242367788745"};
	    	//String reTypeEmailAddr = "ganthimani.s@cognizant.com";
	    	for(int i = 0; i<firstName.length;i++){
	    		switch(i){
	    		case 0:
	    			enterCallBackPage(strTitle[i],firstName[i],lastNmae[i],businessName[i],email[i],contactNumber[i],postCode[i],annualSpend[i],registrationNumber[i]);
	    			String errormsg[] = {TetrisErrorMessages.Tetris_EmptyTitle,TetrisErrorMessages.Tetris_EmptyFirstName,TetrisErrorMessages.Tetris_EmptyLastName,TetrisErrorMessages.Tetris_EmptyBusinessName,TetrisErrorMessages.Tetris_EmptyEmailName,TetrisErrorMessages.Tetris_EmptyContactNumber,TetrisErrorMessages.Tetris_EmptyPostCode,TetrisErrorMessages.Tetris_IncorrectAnnualSpend,TetrisErrorMessages.Tetris_IncorrectRegisNumber};
	    			errorMessageComparison(errormsg);	    			
	    			break;
	    		case 1:
	    			enterCallBackPage(strTitle[i],firstName[i],lastNmae[i],businessName[i],email[i],contactNumber[i],postCode[i],annualSpend[i],registrationNumber[i]);
	    			String IncorrectErrormsg[] = {TetrisErrorMessages.Tetris_EmptyTitle,TetrisErrorMessages.Tetris_IncorrectFirstName,TetrisErrorMessages.Tetris_IncorrectLastName,TetrisErrorMessages.Tetris_IncorrectBusinessName,TetrisErrorMessages.Tetris_IncorrectEmailName,TetrisErrorMessages.Tetris_IncorrectContactNumber,TetrisErrorMessages.Tetris_IncorrecPostCode,TetrisErrorMessages.Tetris_IncorrectAnnualSpend,TetrisErrorMessages.Tetris_IncorrectRegisNumber};
	    			errorMessageComparison(IncorrectErrormsg);
	    			break;
	    		case 2:
	    			enterCallBackPage(strTitle[i],firstName[i],lastNmae[i],businessName[i],email[i],contactNumber[i],postCode[i],annualSpend[i],registrationNumber[i]);
	    			String IncorrectErrormsg1[] = {TetrisErrorMessages.Tetris_EmptyTitle,TetrisErrorMessages.Tetris_IncorrectFirstName,TetrisErrorMessages.Tetris_IncorrectLastName,TetrisErrorMessages.Tetris_IncorrectBusinessName,TetrisErrorMessages.Tetris_IncorrectEmailName,TetrisErrorMessages.Tetris_IncorrectContactNumber,TetrisErrorMessages.Tetris_IncorrecPostCode,TetrisErrorMessages.Tetris_IncorrectAnnualSpend,TetrisErrorMessages.Tetris_IncorrectRegisNumber};
	    			errorMessageComparison(IncorrectErrormsg1);
	    			break;
	    		case 3:
	    			enterCallBackPage(strTitle[i],firstName[i],lastNmae[i],businessName[i],email[i],contactNumber[i],postCode[i],annualSpend[i],registrationNumber[i]);
	    			String IncorrectErrormsg2[] = {TetrisErrorMessages.Tetris_EmptyTitle,TetrisErrorMessages.Tetris_IncorrectFirstName,TetrisErrorMessages.Tetris_IncorrectLastName,TetrisErrorMessages.Tetris_IncorrectBusinessName,TetrisErrorMessages.Tetris_IncorrectEmailName,TetrisErrorMessages.Tetris_IncorrectContactNumber,TetrisErrorMessages.Tetris_IncorrecPostCode,TetrisErrorMessages.Tetris_IncorrectAnnualSpend,TetrisErrorMessages.Tetris_IncorrectRegisNumber};
	    			errorMessageComparison(IncorrectErrormsg2);
	    			break;
	    		case 4:
	    			enterCallBackPage(strTitle[i],firstName[i],lastNmae[i],businessName[i],email[i],contactNumber[i],postCode[i],annualSpend[i],registrationNumber[i]);
	    			String IncorrectErrormsg3[] = {TetrisErrorMessages.Tetris_EmptyTitle,TetrisErrorMessages.Tetris_IncorrectFirstName,TetrisErrorMessages.Tetris_IncorrectLastName,TetrisErrorMessages.Tetris_IncorrectBusinessName,TetrisErrorMessages.Tetris_IncorrectEmailName,TetrisErrorMessages.Tetris_IncorrectContactNumber,TetrisErrorMessages.Tetris_IncorrecPostCode,TetrisErrorMessages.Tetris_IncorrectAnnualSpend,TetrisErrorMessages.Tetris_IncorrectRegisNumber};
	    			errorMessageComparison(IncorrectErrormsg3);
	    			break;
	    		}
	    	}
	    }
	//  Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
	    public void errorMessageComparison(final String expectedErrorMsg[]) {
	    	try{
	    		for(int i=0;i<expectedErrorMsg.length; i++){
	    			if(browser.isElementVisibleWithXpath(callBackPageProperties.getProperty("CallBack.ErrorMsgPath"+i))){
			        final String displayedErrorMsg = browser.getTextByXpath(callBackPageProperties.getProperty("CallBack.ErrorMsgPath"+i));
			        verifyErrorMessage(displayedErrorMsg, expectedErrorMsg[i]);
			        }
			       }
	    		}
	    	catch(Exception e){
	    		Report.updateTestLog("Exception occured"+e, "FAIL");
	    	}
	    }
	    

	//  Validation for Inappropriate data for the fields displayed in Forgot Email Compare the error message displayed in the 
	//  application against the Expected data  	 
	     public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
	    	 System.out.println(displayedErrorMsg);
	    	 System.out.println(expectedErrorMsg);
	         if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
	             Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "PASS");
	         } else {
	        	 Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "FAIL");
	         }
	     }
	     public void enterCallBackPage(String strTitle,String strFirstname,String strLastName,String strBusinessName,String strEmail,String strContactNumber,String strPcode,String strAnnualSpend,String strRegNumber){
	 			verifyAndSelectDropDownBox(callBackPageProperties.getProperty("Callback.Title"), "Title", strTitle);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.firstname"), "FirstName", strFirstname);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.surname"), "SurName", strLastName);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.BusinessName"), "BusinessName",strBusinessName);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.email"), "Email", strEmail);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.telephoneNumber"), "ContactNumber", strContactNumber);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.postCode"), "PostCode", strPcode);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.annualspend"), "AnnualSpend", strAnnualSpend);
	 			verifyAndInputByXpath(callBackPageProperties.getProperty("Callback.companyregistrationnumber"), "CompanyRegistrationNumber", strRegNumber);
	 			//verifyAndSelectDropDownBox(callBackPageProperties.getProperty("Callback.Query"), "Query",userProfile.getQuery());
	 			verifyAndSelectCheckBoxByID(callBackPageProperties.getProperty("Callback.marketingconsent"), "Marketing Consent");
	 			verifyAndClickWithXpath(callBackPageProperties.getProperty("Callback.SubmitButton"), "Submit button");
	 			
	 	}
}
