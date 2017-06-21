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
public class LargeBusinessElectricityCallBackPage extends BasePage{
	private final static String FILE_NAME = "resources/tetris/LargeBusiness.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	TetrisErrorMessages errormsg = new TetrisErrorMessages();

 public void openLargeBusinessPage(){
	 browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("LargeBusinessElectricity.Url"));
	 browser.wait(getWaitTime());
 }
 
 public void enterValuesInLargeBusinessPage(UserProfile userProfile){
	 verifyAndSelectDropDownBox(pageProperties.getProperty("LargeBusiness.Title"), "Title", userProfile.getTitle());		
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.firstname"), "First name",userProfile.getFirstName());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.surname"), "Sur name",userProfile.getLastName());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.companyname"), "Company name",userProfile.getCompanyName());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.contactnumber"), "Phone number",userProfile.getPhoneNumber());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.email"), "Email address",userProfile.getEmail());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.postcode"), "PostCode", userProfile.getPostCode());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.annualspend"), "AnnualSpend", userProfile.getAnnualSpend());
	 verifyAndInputById(pageProperties.getProperty("LargeBusiness.companyregistrationnumber"), "CompanyRegistrationNumber", userProfile.getRegisterationNumber());		
	 verifyAndClickWithXpath(pageProperties.getProperty("LargeBusiness.submitButton"), "Submit");
	 browser.wait(getWaitTime());
 }
 
 public void verifyThankYouPage(){
	// verifyIsElementVisibleWithXpath(pageProperties.getProperty("LargeBusiness.ThankYou"),"Thank you page");
	 verifyIsTextPresent(pageProperties.getProperty("LargeBusiness.ThankYou"),"Thank you page");
 }
 
 public void verifyDBDetails(UserProfile userProfile){
	 OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String leadType[] = dbFunctions.verifyleadDBForLeadType(date,userProfile.getEmail(),"Large_Business_Electricity");		
		Report.updateTestLog("Lead type is "+leadType[0] +". Hence email sent to customer", leadType[0].equalsIgnoreCase("0")?"PASS":"FAIL");
		Report.updateTestLog("Lead status is "+leadType[1] +".", leadType[1].equalsIgnoreCase("Large_Business_Electricity")?"PASS":"FAIL");
		Report.updateTestLog("Title validation is done."+leadType[2], leadType[2].equals(userProfile.getTitle())?"PASS":"FAIL");
		Report.updateTestLog("First name validation is done."+leadType[3], leadType[3].equals(userProfile.getFirstName())?"PASS":"FAIL");
		Report.updateTestLog("Sur name validation is done."+leadType[4], leadType[4].equals(userProfile.getLastName())?"PASS":"FAIL");
		Report.updateTestLog("Company name validation is done."+leadType[5], leadType[5].equals(userProfile.getCompanyName())?"PASS":"FAIL");
		Report.updateTestLog("Contact number validation is done."+leadType[6], leadType[6].equals(userProfile.getPhoneNumber())?"PASS":"FAIL");
		Report.updateTestLog("Post code validation is done."+leadType[7], leadType[7].equals(userProfile.getPostCode())?"PASS":"FAIL");
		Report.updateTestLog("TOTAL_ANNUAL_SPEND validation is done."+leadType[9], leadType[9].equals(userProfile.getAnnualSpend())?"PASS":"FAIL");
		Report.updateTestLog("COMPANY_REGISTRATION_NUMBER validation is done."+leadType[11], leadType[11].equals(userProfile.getRegisterationNumber())?"PASS":"FAIL");
		Report.updateTestLog("Email address validation is done."+leadType[13], leadType[13].equals(userProfile.getEmail())?"PASS":"FAIL");
		
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"Large_Business_Electricity");
		String data = dbFunctions.getAuditType(auditType[0]);	
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("SERVICECALLBACK_CUSTOMER_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
		
 }
}
