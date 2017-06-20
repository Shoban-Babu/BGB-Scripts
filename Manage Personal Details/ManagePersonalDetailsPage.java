package bg.framework.app.functional.page.Slingshot;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;


import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.RunQTP;
import bg.framework.app.functional.util.SiebelDataBase;
import bg.framework.app.functional.util.Report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ManagePersonalDetailsPage extends BasePage {

    private final static String FILE_NAME = "resources/Slingshot/ManagePersonalDetails.properties";
    private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
    private static Properties loginProperties = new PropertyLoader("resources/common/LoginPage.properties").load();
    private final static String BgbFILE_NAME = "resources/Slingshot/HomePage.properties";     
    private static Properties BgbpageProperties = new PropertyLoader(BgbFILE_NAME).load();
    
    //TC_MPD_002
    //To verify the content and layout of "Update your details" page
    public void verifyMPDPageFields(){
    	browser.wait(500);
     	if(browser.getTitle().trim().equalsIgnoreCase(pageProperties.getProperty("ManagePersonalDetails.Title"))){   	
	    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.Header"), "Update your details header");	    	
	    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.firstNameandLastName"), "First name and Last name details");
	    	if(browser.getTextByXpath(pageProperties.getProperty("ManagePersonalDetails.LoginDetails")).equalsIgnoreCase("Log in details")){
	    		verifyIsElementVisibleById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email address");
	    		verifyIsElementVisibleById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "Re-type email address");
	    		verifyIsElementVisibleById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "New password");
	    		verifyIsElementVisibleById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "Re-type new password");    		
	    	}
	    	
	    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.MobileNumberByXpath"), "Mobile Number");	    
	    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.SaveChangesXpath"), "Save changes button");
	    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.CancelLink"), "Cancel button");
	    	}
	    	else{
	    		System.out.println(browser.getTitle() + pageProperties.getProperty("ManagePersonalDetails.Title"));
	    		Report.updateTestLog("MPD page is dsplyed as expected", "FAIL");
	    	}
	    }
	    
	    public void fillValidDataInManagePersonalDetailsPage(String strEmail,String strTempPwd,String mobileNumber) {    	
	           	
	        //Giving empty email for both field since the db not updated with email id
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", strEmail);
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", strEmail);
	        
	        //verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", strEmail);
	        //verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", strEmail);
	        
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "NewPassword Field", strTempPwd);
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "ConfirmNewPassword Field", strTempPwd);
	        //userProfile.setPassword("temp1234");
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.MobileNumber"), "MobileNumber Field", mobileNumber);
	       
	    }
	    
	    public void fillNewEmailField(String strEmail){
	    	
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", strEmail);
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", strEmail);
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "NewPassword Field", "");
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "ConfirmNewPassword Field", "");
	     	   
	        }
	    
	   public void fillNewPasswordField(String strTempPwd){		   
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "NewPassword Field", strTempPwd);
	        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "ConfirmNewPassword Field", strTempPwd);
	     	   }
	    public void ClickSaveChangesButton() {	    
	    	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.SaveChangesXpath"), "Save Changes Button");
	    	browser.wait(getWaitTime());	    	
	    }
	    public void ClickSaveChangesButtonandwithoutAlert() {	  
	    	
	    	//verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.meterreadremindersalert"), "Meter Read reminder alert");
	    	//verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.contractrenewalalert"), "contract renewal alert");
	    	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.Billreadynotificationalert"), "Bill ready notification alert");
	    	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.SaveChangesXpath"), "Save Changes Button");
	    	browser.wait(getWaitTime());	    	
	    }
	    public void Alertverification()
	    {
	    	if(browser.isCheckBoxSelectedWithXpath(pageProperties.getProperty("ManagePersonalDetails.meterreadremindersalert")))
	    	{
	    		
	    	}
	    }
	    
	    public void verifyThankYouPage(){
	    	System.out.println(browser.getTitle());
	    	try{
	    		if(browser.getTitle().trim().contains("Your details have been updated")){ 		
	    	
	    	   	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.ThankYouPageXpath"), "'Thank You' page");
	    	   	Report.updateTestLog("'Your details have been updated' page displayed successfully", "PASS");
	    	}
	    	else {
	    		Report.updateTestLog("'Your details have been updated' page displayed successfully", "FAIL");
	    	}
	    	}
	    	catch(Exception e){
	    		Report.updateTestLog("Exception occured" + e, "FAIL");
	    		
	    	}
	    }
	    public void verifyEmailChangeWithDB(String strEmail) {
	    	
	    	OnlineDBConnector db = new OnlineDBConnector();
	    	System.out.println(strEmail);
	    	db.verifyEmailChange(strEmail);		    	
	    	browser.wait(getWaitTime());
    }
	    
	    public void verifyPasswordChangeWithDB(String strEmail,String strTempPwd){
	    	OnlineDBConnector db = new OnlineDBConnector();
	    	System.out.println(strTempPwd);
	    	db.verifyPasswordChange(strEmail);	    	
	    	browser.wait(getWaitTime());
	    }
	    
	    public void updateTestPassword(UserProfile userProfile){
	    	OnlineDBConnector db = new OnlineDBConnector();
	    	db.updateTestPassword(userProfile.getEmail());
	    }
	    
    public void loginFromMPDChange(String strEmail,String pwd){
    	browser.open(ApplicationConfig.APP_BG_URL);
    	browser.wait(getWaitTime());
    	browser.closeCrmAlert();
    	browser.wait(getWaitTime());
    	verifyAndInputById(loginProperties.getProperty("LoginPage.Email"), "Email Address", strEmail);
    	verifyAndInputById(loginProperties.getProperty("LoginPage.Password"), "Password", pwd);
    	verifyAndClickWithXpath(loginProperties.getProperty("LoginPage.LoginSubmitClass"), "Login");
    	browser.wait(getWaitTime());
    }
	    public void updateEmailInDB(String strEmail,UserProfile userProfile){
	    	OnlineDBConnector db = new OnlineDBConnector();
	    	db.updateDB("update bg_business_ta_customer_reg set email = '"+userProfile.getEmail()+"' where email = '"+strEmail+"'");
	    	
	    }
	    
    public void verifyFillDataWithSiebel(UserProfile userProfile)
    {
    	String sbEmail,sbPhone;
    	//sbEmail = new SiebelDataBase().getEmailAddress(userProfile.getUCRN());
    	sbPhone = new SiebelDataBase().getTelephoneNumber(userProfile.getUCRN());
    		if(sbPhone.equals("+440112232679"))
    		{
    			Report.updateTestLog("Phone number is Updated in Siebel DB :"+sbPhone, "Pass");
    		}
    		else
    		{
    			Report.updateTestLog("Phone number not Updated in Siebel DB,Updated Number :"+sbPhone, "Fail");
    		}
    	
   }
    
    public void VerifyConfirmationOverLayGotToMyAccount() {
    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.ConfirmationMSGHeader"), "Manage Personal Details Confirmation Message Header");
        verifyAndClickWithLinkText(pageProperties.getProperty("ManagePersonalDetails.GoToMyAccountLink"), "GoTo My Account Link");
    }

    public void verifyConfirmationOverLayAndClickLogin() {
    	//verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.ConfirmationMSGHeader"), "Manage Personal Details Confirmation Message Header");
        verifyAndClickWithLinkText(pageProperties.getProperty("ManagePersonalDetails.Login"), "Log in");
    }
    
    public void logout(){
    browser.wait(getWaitTime());	
    verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.Logout"), "Log in");
    
    }  
    
    //TC_MPD_003 : To validate the "Email address" field in MPD journey login detail section.
    public void validateEmailAddressField(){
    	String[] emailAddr= {"","ganthimani.s@ganthimani.s@cognizant.com","chithraqwertyqwertyqwertyqwertyqwertyqweq@bgdigitaltal.co.uk",""};
    	String reTypeEmailAddr = "ganthimani.s@cognizant.com";
    	for(int i = 0; i<emailAddr.length-1;i++){
    		switch(i){
    		case 0:
    			fillEmailAddress(emailAddr[i],reTypeEmailAddr);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_EmptyEmail);
    			break;
    		case 1:
    			fillEmailAddress(emailAddr[i],reTypeEmailAddr);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidEmail);
    			break;
    		case 2:
    			fillEmailAddress(emailAddr[i],reTypeEmailAddr);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidLengthEmail);
    			break;
    		/*case 3:
    			fillEmailAddress(emailAddr[i],reTypeEmailAddr);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_SameEmailAddress);
    			break;*/
    		}
    	}
    }
    
    //TC_MPD_004 :To validate the "Retype Email address" field in MPD journey  business detail section
    public void validateRetypeEmailAddress(){
    	String emailAddr = "ganthimani.s@cognizant.com";
    	String reTypeEmailAddr = "";
    	fillEmailAddress(emailAddr,reTypeEmailAddr);
		errorMessageComparison(SlingshotErrorMessages.SlingShot_EmptyRetypeEmail);
		    }
    
    //TC_MPD_005 : To validate the "New Password" field and its error messages in MPD  journey  user detail section.
    public void validateNewPasswordField(){

    	String[] newPassword= {"","***********************************************","password*!@&12"};

    	String reTypeNewPassword = "password12";
    	for(int i = 0; i<newPassword.length;i++){
    		switch(i){
    		case 0:
    			fillNewPasswordFields(newPassword[i],reTypeNewPassword);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_EmptyPassword);
    			break;
    		case 1:
    			fillNewPasswordFields(newPassword[i],reTypeNewPassword);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_IncorrectPassword);
    			break;
    		case 2:
    			fillNewPasswordFields(newPassword[i],reTypeNewPassword);
    			errorMessageComparison(SlingshotErrorMessages.SlingShot_PasswordWithSplChars);
    			break;    		
    		}
    	}
    }
    
    //TC_MPD_006  :To validate the " Retype New Password" field and its error messages in MPD journey user detail section.
    public void validateRetypeNewPasswordField(){
    	String newPassword = "password13";
    	String reTypeNewPassword= "";
    	fillNewPasswordFields(newPassword,reTypeNewPassword);
		errorMessageComparison(SlingshotErrorMessages.SlingShot_EmptyRetypePwd);
		  }
    
    //TC_MPD_007 :To validate whether password  entered during MPD journey is case sensitive next login.
    public void validatePasswordFieldForCaseSensitve(){
    	fillNewPasswordFields("temp123", "temp123");
    	//checkDBForPwdUpdates();
    	
    }
    
    //TC_MPD_009 : To validate the "Mobile number" field and its error messages in MPD journey your contact  detail section
    public void validateMobileNumberField(){
    	String[] mobileNumber = {"19894183346","989418","989418@#^6"};
    	for(int i = 0; i < mobileNumber.length; i++){
    		fillMobileNumberField(mobileNumber[i]);
    		errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidMobileNumber);
    	}
    }
    
    //TC_MPD_010 : To check with the "your name and address" field in MPD journey         
    public void checkNameAndAddressField(UserProfile userProfile){
    	String strName = browser.getTextByXpath(pageProperties.getProperty("ManagePersonalDetails.NameAndAddressDetails"));
    	if(strName.contains(userProfile.getFirstName())&& strName.contains(userProfile.getLastName())){
    		Report.updateTestLog("Your name and contact details section is displayed with customer name", "PASS");
    	}
    	else{
    		Report.updateTestLog("Your name and contact details section is displayed with customer name", "FAIL");
    	}
    }
    
    //TC_MPD_015 : Verify whether customer is able to change email address alone through MPD journey and its confirmation overlay.
    public void fillEmailChangeData(String strEmail){
        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", strEmail);
        verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", strEmail);
        ClickSaveChangesButton();            
    }
    
    //TC_MPD_016 : Verify whether customer is able to change password  alone through MPD journey and its confirmation overlay.
    public void fillPasswordChangeData(){
    	String password = "temp123";
    	String reTypePassword = "temp123";
    	verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "Password field", password);
    	verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "Re-type new Password field", reTypePassword);
    	ClickSaveChangesButton();
    }
    
    
   public void fillEmailAddress(String email,String reTypeEmail){
	   browser.wait(1000);
	   verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", email);
       verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", reTypeEmail);
       ClickSaveChangesButton();  
       
   }
   
   public void fillNewPasswordFields(String pwd, String retypePwd){
	   browser.wait(1000);
	   verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "New password field", pwd);
	   verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "Re-type password field", retypePwd);
	   ClickSaveChangesButton();
   }
   
//  Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
    public void errorMessageComparison(final String expectedErrorMsg) {
    	try{
        final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("ManagePersonalDetails.ErrorMsg"));
        verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);}
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
     public void fillMobileNumberField(String mobileNumber){
    	 verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.MobileNumber"), "Mobile number field", mobileNumber);
    	 ClickSaveChangesButton();
     }
     public void clickYesInConfirmationOverlay(UserProfile userProfile){
    	 //    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.ConfirmationMSGHeader"), "Manage Personal Details Confirmation Message Header");
         verifyAndClickWithLinkText(pageProperties.getProperty("ManagePersonalDetails.Login"), "Log in");
          }
     public void clickNoInConfirmationOverlay(UserProfile userProfile){
    	 //    	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.ConfirmationMSGHeader"), "Manage Personal Details Confirmation Message Header");
         verifyAndClickWithLinkText(pageProperties.getProperty("ManagePersonalDetails.Login"), "Log in");
          }

 //TC_MPD_019 :Verify whether the proper messages are displayed to the user when the fields "New password" and "Reconfirm New Password" are mismatching.
public void passwordMismatchError(){
	fillNewPasswordFields("password13","password14");
	//ClickSaveChangesButton();
	errorMessageComparison(SlingshotErrorMessages.slingshot_passwordMismatchError);
}

//TC_MPD_020 : Verify whether the proper messages are displayed to the user when the fields "Email address" and "Reconfirm Email address" are mismatching.
public void emailMisMatchError(){
	fillEmailAddress("xyz@bgdigital.com", "abc@bgdigital.com");
	ClickSaveChangesButton();
	errorMessageComparison(SlingshotErrorMessages.slingshot_emailMismatchError);
}

//TC_MPD_022 :To check whether Meter read reminders options can be choosen in SMS alerts section and bill notifications options can be choosen in Email alerts section.
public void checkMRAndBNinEmailSection(){	
	verifyAndClick("ManagePersonalDetails.MeterReadRemainderInEmailSection", "Meter read remainder");
	verifyAndClick("ManagePersonalDetails.BillNotificationInEmailSection", "Bill notifications");	
}

//TC_MPD_023 : To check whether Meter read reminders options can be choosen in Email alerts section and bill notifications options can be choosen in SMS alerts section.
public void checkMRAndBNinSMSSection(){	
	verifyAndClick("ManagePersonalDetails.MeterReadRemainderInSMSSection", "Meter read remainder");
	verifyAndClick("ManagePersonalDetails.BillNotificationInSMSSection", "Bill notifications");	
}

public void clickLogout(){
	if(browser.isTextPresent("Log out")){
		verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.Logout"), "Log out");
		browser.wait(getWaitTime());
	}
}
public void checkNameDetails(UserProfile userProfile){
	if(browser.isTextPresent("Your name")){
		System.out.println("your Name details ------------------->"+browser.isTextPresent("ganthimani"));
		Report.updateTestLog("your Name and contact details displayed as expected", "PASS");
		}
	else{
		//String strName = browser.getTextByXpath(pageProperties.getProperty("ManagePersonalDetails.firstNameandLastName"));
		System.out.println("your Name details ------------------->"+browser.isTextPresent("ganthimani"));
		System.out.println();
		Report.updateTestLog("Name and contact details displayed as expected", "FAIL");
	}
}
public void verifyAuditDetails(final UserProfile userProfile,int auditId) {     	
	OnlineDBConnector dbfunctions = new OnlineDBConnector();       
    String sysDate = dbfunctions.DBsysdateDdmmyyhhmi();            
    System.out.println(dbfunctions.getAuditTypeIdMs(userProfile.getEmail(), sysDate, auditId));
    int getAudittype=dbfunctions.getAuditTypeIdMs(userProfile.getEmail(), sysDate, auditId);
   // String auditIdInDB = dbfunctions.getAuditEventID(sysDate, userProfile.getEmail());
    if(getAudittype>=1){
      Report.updateTestLog("Audit entry is made as expected: "+auditId, "PASS");
      
    }else{
    	//Report.updateTestLog("Audit entry is made as expected: "+auditId , "FAIL");
    }
    
}
public void resetEmailField(String strEmail){
	OnlineDBConnector dbfunctions = new OnlineDBConnector();  
	dbfunctions.resetEmailAddress(strEmail);
}
public void fillNewMobileNumberField(UserProfile userProfile){   
  verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "NewPassword Field", "");
  verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "ConfirmNewPassword Field", "");
  verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.MobileNumber"), "MobileNumber Field", "0122226789");

}
public void verifyMobileNumberChangeDataWithSAPCRM(UserProfile userProfile){
	
}

public void fillEmailAndPasswordFieldAlone(String strEmail,String strTempPwd){
	verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", strEmail);
    verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", strEmail);
    verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "NewPassword Field", strTempPwd);
    verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "ConfirmNewPassword Field", strTempPwd);   
    
}


public void checkMRinEmailAndBNinEmailSection(){
	verifyAndClick("ManagePersonalDetails.MeterReadRemainderInEmailSection", "Meter read remainder");
	verifyAndClick("ManagePersonalDetails.BillNotificationInSMSSection", "Bill notifications");	
}

public void checkBNinEmailAndMRinSMSSection(){
	verifyAndClick("ManagePersonalDetails.BillNotificationInEmailSection", "Bill notifications");
	verifyAndClick("ManagePersonalDetails.MeterReadRemainderInSMSSection", "Meter read remainder");
}

public void clickLogin(){
	try{		
		if(browser.getTitle().toString().contains("Interaction Center")){
		browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgbLoginLink")); 
		browser.wait(getWaitTime());
		browser.closeAlert();
		browser.wait(getWaitTime());
		//browser.wait(getWaitTime());
		System.out.println(browser.isTextVisible("Leave Page"));
		/*if(browser.getTitle().contains("Login")){
			
		}
		else{
			browser.closeAlert();
			browser.wait(getWaitTime());
		}
		*/
	}
	else{
	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.LoginLink"), "Log in");
	browser.wait(getWaitTime());
	verifyPageTitle("Log in to your business account - British Gas Business");}	}
	catch(Exception e){
		System.out.println(e);
	}
}
public void resetPasswordFields(String strEmail,String strPassword){
	verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", strEmail);
    verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field", strEmail);    
	 verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.NewPassword"), "NewPassword Field", strPassword);
     verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ConfirmNewPassword"), "ConfirmNewPassword Field", strPassword);
     
}
public void verifyMPDUpdatePageAndClickLogout(){
	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.MakeAnotherChange"), "Make another change");
	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.GotoMyAccount"), "Goto my account");
	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.Logout"), "Logout");
	
}

public void clickMakeAnotherChange(){
	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.MakeAnotherChange"), "Make another change");
	browser.wait(getWaitTime());
}
public void validateGotoMyAccountLink(){
	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.GotoMyAccount"), "Goto Your account");
	browser.wait(getWaitTime());
	verifyPageTitle("Account overview");
}
public void logoutPresentOrNot(){
	Report.updateTestLog(" Update your details page", "WARN");
	if(browser.isTextPresent("Log out"))
	{
		Report.updateTestLog("Log out is Absent", "WARN");
}
	else
	{
		Report.updateTestLog("Log out is Present", "Fail");
}
}


public void newEmail(UserProfile userProfile){
	verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.EmailAddress"), "Email Address Field", userProfile.getNewEmail());
    verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.ReEnterEmailAddress"), "ReEnterEmailAddress Field",  userProfile.getNewEmail());  
    verifyAndInputById(pageProperties.getProperty("ManagePersonalDetails.MobileNumber"), "MobileNumber Field", userProfile.getMobileNumber());
    Report.updateTestLog(" Update your details page", "WARN");
    verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.SaveChangesXpath"), "Save Changes Button");
    
    /*verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.reminderAlert"), "Reminder alert option");
    verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.RemainderSubmitbutton"), "Save Changes Button");*/
       
    browser.wait(3000);
}
public void verifyContractRenewalForFAUser(){
	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ManagePersonalDetails.ContractRenewalAlert"))){
    //verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.ContractRenewalAlert"), "Contract Renewal Alert");
	Report.updateTestLog("Superuser full access users is able to view Contract renewal ready option in the Email alerts of Update your details page", "PASS");
	}
	else{
	Report.updateTestLog("Superuser full access users is able to view Contract renewal ready option in the Email alerts of Update your details page", "FAIL");

	}
}

public void VerifySAPCRM_MPDVerification(UserProfile userProfile)
{
	    String Emailstatusapp=null; 
	    String MailStatusapp=null;
	    String SMSstatusapp=null;
	    String TelephoneStatusapp=null;
	    String UpdatedPhoenoapp ="05263415266";	
	    String updatedEmailidapp=userProfile.getNewEmail();
	if(browser.isCheckBoxSelectedWithXpath(pageProperties.getProperty("ManagePersonalDetails.meterreadremindersalert")))
	{
		Emailstatusapp="Opt Out"; 
		System.out.println("Emailstatusapp"+Emailstatusapp);
	}
	else
	{
		Emailstatusapp="Opt In"; 
		System.out.println("Emailstatusapp"+Emailstatusapp);
	}
	if(browser.isCheckBoxSelectedWithXpath(pageProperties.getProperty("ManagePersonalDetails.LetterAlert")))
	{
		MailStatusapp="Opt Out";
		System.out.println("MailStatusapp"+MailStatusapp);
	}
	else
	{
		MailStatusapp="Opt In";
		System.out.println("MailStatusapp"+MailStatusapp);
	}
	if(browser.isCheckBoxSelectedWithXpath(pageProperties.getProperty("ManagePersonalDetails.Telephonealert")))
	{
		TelephoneStatusapp="Opt Out";
		System.out.println("TelephoneStatusapp"+TelephoneStatusapp);
	}
	else
	{
		TelephoneStatusapp="Opt In";
		System.out.println("TelephoneStatusapp"+TelephoneStatusapp);
	}
	if(browser.isCheckBoxSelectedWithXpath(pageProperties.getProperty("ManagePersonalDetails.smsalert")))
	{
		SMSstatusapp="Opt Out";
		System.out.println("SMSstatusapp"+SMSstatusapp);
	}
	else
	{
		SMSstatusapp="Opt In";
		System.out.println("SMSstatusapp"+SMSstatusapp);
	}
	verifyAndClickWithXpath(pageProperties.getProperty("ManagePersonalDetails.SaveChangesXpath"), "Save Changes Button");
	browser.wait(getWaitTime());	    	
	
  	String PBoutput=userProfile.getpbstatus();
  	System.out.println("PBoutput"+PBoutput);
	String Acctno=userProfile.getAccNumber();
	System.out.print("Acctno"+Acctno);		
	String bpnumber=userProfile.getBpnumber();
	System.out.print("bpnumber"+bpnumber);
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	String bpcpnumber=dbFunctions.getBpcpnumber(userProfile.getNewEmail());
	String bpOrgNumber =bpnumber.concat("-").concat(Acctno).concat("-").concat(bpcpnumber);
	System.out.println("bpOrgNumber"+bpOrgNumber);
	RunQTP runQTP = new RunQTP();
    System.out.println("Initiating QTP.............");
    runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\Managedetails.vbs", bpOrgNumber);
    System.out.println("QTP Process Over...........");  
     
   browser.wait(15000);
	try {
		System.out.println("*************************************");
			
		 File file1 = new File("D:\\managedetails\\managedetailstext.txt");
		 System.out.println(file1.getAbsolutePath());
         FileReader fr = new FileReader(file1);            
         BufferedReader br = new BufferedReader(fr);
         
			         String EmailStatus = br.readLine();   
			       System.out.println("EmailStatus"+EmailStatus);     
			     if(EmailStatus.contains("EmailStatus")&& EmailStatus.contains(":")){
			                     String[] EmailStatusvalue=EmailStatus.split(":");
			                     String EmailStatusvaluefinal=EmailStatusvalue[1];
			                     System.out.print("EmailStatusvaluefinal"+EmailStatusvaluefinal);
			                     if(EmailStatusvaluefinal.trim().equals(Emailstatusapp.trim())){
			                                     Report.updateTestLog("Email Status in Application"+Emailstatusapp.trim()+"Email Status in  SAP CRM :"+EmailStatus.trim(), "Pass");
			                     }else{
			                                     Report.updateTestLog("Email Status in Application"+Emailstatusapp.trim()+"Email Status in  SAP CRM :"+EmailStatus.trim(), "Fail");
			                     }
			     }       
			     
			     String MailStatuss = br.readLine();   
			     System.out.println("MailStatus"+MailStatuss);     
			   if(MailStatuss.contains("MailStatus")&& MailStatuss.contains(":")){
			                   String[] MailStatussvalue=MailStatuss.split(":");
			                   String Mailstatusfinal=MailStatussvalue[1];
			                   System.out.print("Mailstatusfinal"+Mailstatusfinal);
			                   if(Mailstatusfinal.trim().equals(MailStatusapp.trim())){
			                                   Report.updateTestLog("Mail Status in Application"+MailStatusapp.trim()+"Mail Status in  SAP CRM :"+Mailstatusfinal.trim(), "Pass");
			                   }else{
			                                   Report.updateTestLog("Mail Status in Application"+MailStatusapp.trim()+"Mail Status in  SAP CRM :"+Mailstatusfinal.trim(), "Fail");
			                   }
			   }
			   
			   String SMSstatus = br.readLine();   
			   System.out.println("SMSstatus"+SMSstatus);     
			 if(SMSstatus.contains("SMSstatus")&& SMSstatus.contains(":")){
			                 String[] SMSstatusvalue=SMSstatus.split(":");
			                 String smsstatusfinal=SMSstatusvalue[1];
			                 System.out.print("smsstatusfinal"+smsstatusfinal);
			                 if(smsstatusfinal.trim().equals(SMSstatusapp.trim())){
			                                 Report.updateTestLog("SMS Status in Application"+smsstatusfinal.trim()+"SMS Status in  SAP CRM :"+smsstatusfinal.trim(), "Pass");
			                 }else{
			                                 Report.updateTestLog("SMS Status in Application"+smsstatusfinal.trim()+"SMS Status in  SAP CRM :"+smsstatusfinal.trim(), "Fail");
			                 }
			 }
			 
			 String telephonestatus = br.readLine();   
			 System.out.println("telephonestatus"+telephonestatus);     
			if(telephonestatus.contains("TelephoneStatus")&& telephonestatus.contains(":")){
			               String[] telephonestatusvalue=telephonestatus.split(":");
			               String telephonestatusfinal=telephonestatusvalue[1];
			               System.out.print("telephonestatusfinal"+telephonestatusfinal);
			               if(telephonestatusfinal.trim().equals(TelephoneStatusapp.trim())){
			                               Report.updateTestLog("Telephone Status in Application"+TelephoneStatusapp.trim()+"Telephone Status in  SAP CRM :"+telephonestatusfinal.trim(),"Pass");
			               }else{
			                               Report.updateTestLog("Telephone Status in Application"+TelephoneStatusapp.trim()+"Telephone Status in  SAP CRM :"+telephonestatusfinal.trim(),"Fail");
			               }
			}
			
			String updatedemail = br.readLine();   
			System.out.println("updatedemail"+updatedemail);     
			if(updatedemail.contains("UpdatedEmailid")&& updatedemail.contains(":")){
			              String[] updatedemailvalue=updatedemail.split(":");
			              String updatedemailvaluefinal=updatedemailvalue[1];
			              System.out.print("updatedemailvaluefinal"+updatedemailvaluefinal);
			              if(updatedemailvaluefinal.trim().equals(updatedEmailidapp.trim())){
			                              Report.updateTestLog("Updated Email Status in Application"+updatedemailvaluefinal.trim()+"Updated Email Status in  SAP CRM :"+updatedEmailidapp, "Pass");
			              }else{
			                              Report.updateTestLog("Updated Email Status in Application"+updatedemailvaluefinal.trim()+"Updated Email Status in  SAP CRM :"+updatedEmailidapp, "Fail");
			              }
			}
			
			String updatedphoeno = br.readLine();   
			System.out.println("updatedphoeno"+updatedphoeno);     
			if(updatedphoeno.contains("UpdatedPhoeno")&& updatedphoeno.contains(":")){
			              String[] updatedphoenovalue=updatedphoeno.split(":");
			              String updatedphoenovaluefinal=updatedphoenovalue[1];
			              System.out.print("updatedphoenovaluefinal"+updatedphoenovaluefinal);
			              if(updatedphoenovaluefinal.trim().equals(UpdatedPhoenoapp.trim())){
			                              Report.updateTestLog("Updated Phone Status in Application"+UpdatedPhoenoapp.trim()+"Updated Phone Status in  SAP CRM :"+UpdatedPhoenoapp, "Pass");
			              }else{
			                              Report.updateTestLog("Updated Phone Status in Application"+UpdatedPhoenoapp.trim()+"Updated Phone Status in  SAP CRM :"+UpdatedPhoenoapp, "Fail");
			              }
			}

			
			br.close();		
					
				  }
				     catch (IOException e) {
					        Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
				}
}
}