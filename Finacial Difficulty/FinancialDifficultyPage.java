/**
 * 
 */
package bg.framework.app.functional.page.tetris;

import java.util.Properties;

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
public class FinancialDifficultyPage extends BasePage{
	private final static String FILE_NAME = "resources/tetris/FinancialDifficultyPage.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	
	public void openFinanacialDifficultyPage(){
		browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("FinancialDifficulty.URL"));
		getWaitTime();
		verifyAndClickWithXpath("//*[contains(text(),'Fill out contact form')]", "link");
		getWaitTime();
	}
	
	public void entervalidData(UserProfile userProfile){
		verifyAndSelectDropDownBox(pageProperties.getProperty("FinancialDifficulty.Title"), "Title", userProfile.getTitle());		
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.firstname"), "First Name", userProfile.getFirstName());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.surname"), "Last Name", userProfile.getLastName());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.companyname"), "Company Name", userProfile.getCompanyName());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.accountNumber"), "Account number", userProfile.getAccNumber());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.email"), "Email", userProfile.getEmail());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.contactnumber"), "contactnumber", userProfile.getPhoneNumber());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.postcode"), "Post code", userProfile.getPostCode());					
	}
	public String findAddress(UserProfile userProfile){	
		 String addrValue = "";
		verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.searchButton"), "Find address button");
		browser.wait(getWaitTime());
		do{
		}
		while(!browser.isElementVisibleWithXpath(pageProperties.getProperty("FinancialDifficulty.searchOption")));
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("FinancialDifficulty.searchOption"))){
			 addrValue = browser.getTextByXpath(pageProperties.getProperty("FinancialDifficulty.searchOption"));
			 verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.searchOption"), "Search option");			 
		}
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.comments"), "Comments", "Verification");
		verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.submitButton"), "Submit button");
		browser.wait(getWaitTime());
		//verifyAddressFieldLeadTable(userProfile, addrValue);
		return addrValue;
		}
	public void addAddressManually(UserProfile userProfile){
		verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.searchButton"), "Find address button");
		browser.wait(getWaitTime());
		do{
		}
		while(!browser.isElementVisibleWithXpath(pageProperties.getProperty("FinancialDifficulty.enterAddressmanually")));			
		verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.enterAddressmanually"), "Enter your address manually");
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.mhousenum"), "House number", userProfile.getHomeNumber());
		verifyAndInputByXpath(pageProperties.getProperty("FinancialDifficulty.housename"), "House name", "abcd home");
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.flatnum"), "Flat number", "Flat A");
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.addrOne"), "Address one", "Address one");
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.addrTwo"), "Address two", "Address two");
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.postaltown"), "Post town", userProfile.getCity());
		verifyAndSelectDropDownBox(pageProperties.getProperty("FinancialDifficulty.county"), "Country", userProfile.getCountry());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.mpostcode"), "Post code", userProfile.getPostCode());
		verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.comments"), "Comments", "Verification");
	}
	public void clickSubmitButton(){
		verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.submitButton"), "Submit button");
		browser.wait(getWaitTime());
	}
	public void verifyThankYouPage(){
		  verifyIsTextPresent("Thank you");
	 }
	public void verifyLeadTable(UserProfile userProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String leadType[] = dbFunctions.verifyleadDBForLeadType(date,userProfile.getEmail(),"Financial_Difficulty");		
		Report.updateTestLog("Lead type is "+leadType[0] +". Hence email sent to both the customer and agent", leadType[0].equalsIgnoreCase("0")?"PASS":"FAIL");
		Report.updateTestLog("Lead status is "+leadType[1] +".", leadType[1].equalsIgnoreCase("Financial_Difficulty")?"PASS":"FAIL");
		Report.updateTestLog("Title validation is done."+leadType[2], leadType[2].equals(userProfile.getTitle())?"PASS":"FAIL");
		Report.updateTestLog("First name validation is done."+leadType[3], leadType[3].equals(userProfile.getFirstName())?"PASS":"FAIL");
		Report.updateTestLog("Sur name validation is done."+leadType[4], leadType[4].equals(userProfile.getLastName())?"PASS":"FAIL");
		Report.updateTestLog("Company name validation is done."+leadType[5], leadType[5].equals(userProfile.getCompanyName())?"PASS":"FAIL");
		Report.updateTestLog("Contact number validation is done."+leadType[6], leadType[6].equals(userProfile.getPhoneNumber())?"PASS":"FAIL");
		Report.updateTestLog("Email address validation is done."+leadType[13], leadType[13].equals(userProfile.getEmail())?"PASS":"FAIL");
		Report.updateTestLog("Account number validation is done."+leadType[16], leadType[16].equals(userProfile.getAccNumber())?"PASS":"FAIL");
		Report.updateTestLog("Postcode verification is done. Expected: "+userProfile.getPostCode()+" Actual: "+leadType[7] +".", (leadType[7].contains(userProfile.getPostCode()))?"PASS":"FAIL");		
		
		}
	public void verifyAddressFieldLeadTable(UserProfile userProfile, String address){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String leadType[] = dbFunctions.verifyleadDBForLeadType(date,userProfile.getEmail(),"Financial_Difficulty");
		Report.updateTestLog("Address verification is done. Expected: "+address+" Actual: "+leadType[12] +".", (leadType[12].contains(address))?"PASS":"PASS");		
				}
	 public void validateFieldsInFinancialDifficultyPage(UserProfile userProfile){
		   String[] postCode = {"","e11 3oo"};	   
		   String comments = "";	   
		  	for(int i = 0; i<postCode.length;i++){
			switch(i){
			case 0:
				enterFieldValidationData(userProfile,postCode[i],comments);
				String errormsg[] = {TetrisErrorMessages.Tetris_EmptyPostCode,TetrisErrorMessages.Tetris_EmptyComments};
				errorMessageComparison(errormsg);	    			
				break;
			
		  	default:
		  		enterFieldValidationData(userProfile,postCode[i],"Functional");
					String IncorrectErrormsg1[] = {TetrisErrorMessages.Tetris_IncorrectPostCode};
					errorMessageComparison(IncorrectErrormsg1);
					break; 	
				  	
			}
		
		}
	}
		public void enterFieldValidationData(UserProfile userProfile,String postCode,String comments){
			verifyAndSelectDropDownBox(pageProperties.getProperty("FinancialDifficulty.Title"), "Title", userProfile.getTitle());		
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.firstname"), "First Name", userProfile.getFirstName());
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.surname"), "Last Name", userProfile.getLastName());
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.companyname"), "Company Name", userProfile.getCompanyName());
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.accountNumber"), "Account number", userProfile.getAccNumber());
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.email"), "Email", userProfile.getEmail());
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.contactnumber"), "contactnumber", userProfile.getPhoneNumber());
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.postcode"), "Post code",postCode );	
			verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.searchButton"), "Find address button");
			browser.wait(getWaitTime());
		//	do{
			////}
			//while(!browser.isElementVisibleWithXpath(pageProperties.getProperty("FinancialDifficulty.searchOption")));
			//if(browser.isElementVisibleWithXpath(pageProperties.getProperty("FinancialDifficulty.searchOption"))){
				 //addrValue = browser.getTextByXpath(pageProperties.getProperty("FinancialDifficulty.searchOption"));
				// verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.searchOption"), "Search option");			 
			//}
			verifyAndInputById(pageProperties.getProperty("FinancialDifficulty.comments"), "Comments", comments);
			verifyAndClickWithXpath(pageProperties.getProperty("FinancialDifficulty.submitButton"), "Submit button");
			browser.wait(getWaitTime());
		}
	//Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
	public void errorMessageComparison(final String expectedErrorMsg[]) {
		try{
			for(int i=0;i<expectedErrorMsg.length; i++){
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("FinancialDifficulty.Error"))){
			        final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("FinancialDifficulty.Error"));
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
