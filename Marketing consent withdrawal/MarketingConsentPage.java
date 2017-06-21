package bg.framework.app.functional.page.tetris;

import java.util.Properties;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.page.common.TetrisErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;


public class MarketingConsentPage extends BasePage{
	private final static String FILE_NAME = "resources/tetris/Common.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	TetrisErrorMessages errormsg = new TetrisErrorMessages();
 public void gotoMarketingConsentpage(){
	 
 }
 
 public void openMarketingConsentPage(){
	 browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("MarketingConsent.Url"));
	 browser.wait(getWaitTime());
 }
 
 public void enterValuesInMarketingConsentPage(UserProfile userProfile){
	 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.firstname"), "First name",userProfile.getFirstName());
	 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.surname"), "Sur name",userProfile.getLastName());
	 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.companyname"), "Company name",userProfile.getCompanyName());
	 verifyAndInputById(pageProperties.getProperty("MarketingConsent.accountNumber"), "Account name",userProfile.getAccNumber());
	 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.email"), "Email address",userProfile.getEmail());
	 verifyAndInputById(pageProperties.getProperty("MarketingConsent.confirmEmail"), "Confirm Email address",userProfile.getEmail());
	 verifyAndClickWithXpath(pageProperties.getProperty("CorporateContactUs.submitButton"), "Submit");
	 browser.wait(getWaitTime());
 }
 
 public void verifyThankYouPage(){
	 verifyIsElementVisibleWithXpath(pageProperties.getProperty("MarketingConsent.ThankYouPageRequest"),"Thank you page");
	 verifyIsTextPresent(TetrisErrorMessages.tetris_MarketingConsentThankYou);
 }
 
 public void verifyDBDetails(UserProfile userProfile){
	 OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String leadType[] = dbFunctions.verifyleadDBForLeadType(date,userProfile.getEmail(),"Marketing_Consent_Withdrawal");		
		Report.updateTestLog("Lead type is "+leadType[0] +". Hence email sent to customer", leadType[0].equalsIgnoreCase("0")?"PASS":"FAIL");
		Report.updateTestLog("Lead status is "+leadType[1] +".", leadType[1].equalsIgnoreCase("Marketing_Consent_Withdrawal")?"PASS":"FAIL");
		Report.updateTestLog("First name validation is done."+leadType[3], leadType[3].equals(userProfile.getFirstName())?"PASS":"FAIL");
		Report.updateTestLog("Sur name validation is done."+leadType[4], leadType[4].equals(userProfile.getLastName())?"PASS":"FAIL");
		Report.updateTestLog("Email address validation is done."+leadType[13], leadType[13].equals(userProfile.getEmail())?"PASS":"FAIL");
		Report.updateTestLog("Company validation is done."+leadType[5], leadType[5].equals(userProfile.getCompanyName())?"PASS":"FAIL");
		Report.updateTestLog("Account number validation is done."+leadType[16], leadType[16].equals(userProfile.getAccNumber())?"PASS":"FAIL");
		
 }
 public void validateFieldsInMisc(UserProfile userProfile){
	   String[] accountNumber = {"","600!@#$364","600abcd456!@#%$^^&","600347823647823541273512","1234567891"};	   
	   String email = "ganthimani.s@cognizant.com";	   
	   String confirmEmail = "ganthimani.s@cognizant@cognizant.com";
  	for(int i = 0; i<accountNumber.length;i++){
  		switch(i){
  		case 0:
  			enterValuesInMarketingConsentPage(userProfile.getFirstName(),userProfile.getLastName(),userProfile.getCompanyName(),accountNumber[i],email,confirmEmail);
  			String errormsg[] = {TetrisErrorMessages.Tetris_EmptyAccountName,TetrisErrorMessages.Tetris_IncorrectConfirmEmailName};
  			errorMessageComparison(errormsg);	    			
  			break;
  		case 1:
  			enterValuesInMarketingConsentPage(userProfile.getFirstName(),userProfile.getLastName(),userProfile.getCompanyName(),accountNumber[i],email,email);
  			String IncorrectErrormsg[] = {TetrisErrorMessages.Tetris_IncorrectAccountName};
  			errorMessageComparison(IncorrectErrormsg);
  			break;  		
  		
	  	case 2:
				enterValuesInMarketingConsentPage(userProfile.getFirstName(),userProfile.getLastName(),userProfile.getCompanyName(),accountNumber[i],email,email);
				String IncorrectErrormsg1[] = {TetrisErrorMessages.Tetris_IncorrectAccountName};
				errorMessageComparison(IncorrectErrormsg1);
				break;  		
			
	  	case 3:
			enterValuesInMarketingConsentPage(userProfile.getFirstName(),userProfile.getLastName(),userProfile.getCompanyName(),accountNumber[i],email,email);
			String IncorrectErrormsg2[] = {TetrisErrorMessages.Tetris_IncorrectAccountName};
			errorMessageComparison(IncorrectErrormsg2);
			break;  		
		
	  	case 4:
			enterValuesInMarketingConsentPage(userProfile.getFirstName(),userProfile.getLastName(),userProfile.getCompanyName(),accountNumber[i],email,email);
			String IncorrectErrormsg3[] = {TetrisErrorMessages.Tetris_IncorrectAccountName};
			errorMessageComparison(IncorrectErrormsg3);
			break;  	
  		}
	
  	}
  }
//  Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
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
  

//  Validation for Inappropriate data for the fields displayed in Forgot Email Compare the error message displayed in the application against the Expected data  	 
   public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
  	
       if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
           Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "PASS");
       } else {
      	 Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "FAIL");
       }
   }
     public void enterValuesInMarketingConsentPage(String strFirstname, String strLastName, String strCompanyname, String strAccNumber, String strEmail, String strConfirmEmail){
    	 if(browser.isElementVisible(pageProperties.getProperty("CorporateContactUs.firstname"))){
			 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.firstname"), "First name",strFirstname);
			 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.surname"), "Sur name",strLastName);
			 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.companyname"), "Company name",strCompanyname);
			 verifyAndInputById(pageProperties.getProperty("MarketingConsent.accountNumber"), "Account name",strAccNumber);
			 verifyAndInputById(pageProperties.getProperty("CorporateContactUs.email"), "Email address",strEmail);
			 verifyAndInputById(pageProperties.getProperty("MarketingConsent.confirmEmail"), "Confirm Email address",strConfirmEmail);
			 verifyAndClickWithXpath(pageProperties.getProperty("CorporateContactUs.submitButton"), "Submit");
			 browser.wait(getWaitTime());
		 }
    	 else{
    		 Report.updateTestLog("Application is not available", "FAIL");
    	 }
     }
}
