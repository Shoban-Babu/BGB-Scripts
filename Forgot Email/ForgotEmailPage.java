
package bg.framework.app.functional.page.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.SiebelDataBase;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebElement;

//import org.openqa.selenium.internal.seleniumemulation.GetText;

import com.jniwrapper.win32.ie.Browser;

public class ForgotEmailPage extends BasePage {

    private final static String FILE_NAME = "resources/Slingshot/ForgotEmail.Properties";
    private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
    private final static String FILE_NAME1 = "resources/Slingshot/ForgottenPassword.Properties";
    private static Properties forgottenPasswordpageProperties = new PropertyLoader(FILE_NAME1).load();
    
    private final static String LOGIN_FILE_NAME = "resources/common/LoginPage.Properties";
    private static Properties loginProperties = new PropertyLoader(LOGIN_FILE_NAME).load();
    final String  Pass_Str = "PASS";
    final String  Fail_Str = "FAIL";
    private static String bname="";
    private final static int EMAIL_RETRIEVAL_SUCCESS = 58;  
    
    public ForgotEmailPage() {
        super();
    }

// Verify whether Forgot Email Link exists and if it exists click on the Forgot Email Link and check whether it navigates to the appropriate page    

    public void validateForgotEmail(final UserProfile userProfile) {
        final String EmailAddressText = browser.getTextByXpath(loginProperties.getProperty("LoginPage.RequiredFieldID"));
        if (EmailAddressText.trim().toLowerCase().contains("retrieve your email address")) {
            Report.updateTestLog("Displayed email address text is  :" + EmailAddressText, Pass_Str);
        } else {
            Report.updateTestLog("Expected email address text was not displayed ",Fail_Str);
        }
    }   

   public void VerifyForgotEmailPageFields(){
	   browser.wait(getWaitTime());
		   verifyPageTitle(pageProperties.getProperty("ForgotEmailPage.Title"));
		   //ForgotEmailPage.GetEmailButtonBoxButton
		  
            verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButtonBoxButton"),"Forgot Email Box Button");
		   verifyIsElementVisibleById(pageProperties.getProperty("ForgotEmailPage.ContractAccountNumber"), "Customer reference number");
		   verifyIsElementVisibleById(pageProperties.getProperty("ForgotEmailPage.TitleDropdown"), "Title");
		   verifyIsElementVisibleById(pageProperties.getProperty("ForgotEmailPage.Firstname"), "First name");
		   verifyIsElementVisibleById(pageProperties.getProperty("ForgotEmailPage.Lastname"), "Surname");
		   
		   if (browser.isElementVisibleWithXpath(pageProperties.getProperty("ForgotPasswordPage.BackToLoginButton"))){
			   Report.updateTestLog("Back to login link is displayed in the application", "WARN");
		   }
			   else {
				   
				   Report.updateTestLog("Back to login link is not displayed in the application", "FAIL");
		   }
		  
		   verifyIsElementVisibleWithXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButton"), "Get email address button");
		  
	   }
   
   public String validateForgotEmailaddress(UserProfile userProfile){
	   browser.wait(getWaitTime());	
	  
	   verifyPageTitle("Your account email address");   
	   String generatedemail=null;   
	   
	   try{
    	   browser.wait(getWaitTime());
    	   verifyIsElementVisibleWithXpath(pageProperties.getProperty("ForgotEmailPage.GeneratedEmail")+userProfile.getEmail()+"')]", "Generated Email address");
    	   generatedemail = browser.getTextByXpath(pageProperties.getProperty("ForgotEmailPage.GeneratedEmail")+userProfile.getEmail()+"')]");
    	   System.out.println(generatedemail);
    	   System.out.println(pageProperties.getProperty("ForgotEmailPage.GeneratedEmail")+userProfile.getEmail()+"')]");
    	   if(generatedemail.equalsIgnoreCase(userProfile.getEmail())){
    		   Report.updateTestLog("Generated email address is same as input(xml file)", "Pass");
    	   }else{
    		   Report.updateTestLog("Generated email address:"+generatedemail+"is not same as input(xml file):"+userProfile.getEmail(), "Fail");
    	   }
       }catch(Exception e){
  		 Report.updateTestLog("Some error occurered while getting email address"+e, "Fail");
  	 } 
    return generatedemail;
   }
   
   //Verify that user is able to successfully retreive the email address in the Forgotten Email Journey  by providing valid user entries
   public void verifyEmailRetrivation(UserProfile userProfile){
	   enterForgotEmailData(userProfile.getAccNumber(), userProfile.getTitle(), userProfile.getFirstName(), userProfile.getLastName());
	   verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButton"), "Click-GetEmailAddress button");
	   getWaitTime();	   
	  // verifyPageTitle("Retrieve email address");
	   String title = "Retrieve email address";
	   if(browser.getTitle().contains(title)){
		 Report.updateTestLog("Expected page title : <b>"+title + "</b> displayed.", "PASS");
		 verifyIsTextPresent("Your account email address");		
		 verifyIsTextPresent("Login", "Verify 'Login' button");
		 verifyIsTextPresent("Cancel", "Verify 'Cancel' button");  		 
	   }else{
  		Report.updateTestLog("Expected page title : <b>"+title + "</b> not displayed. Actual title <b>"+browser.getTitle()+ "</b> ", "FAIL");
	   }
       try{
    	   getWaitTime(); 
    	   String generatedemail = browser.getTextByXpath(pageProperties.getProperty("ForgotEmailPage.GeneratedEmail"));   
    	   if(generatedemail.equalsIgnoreCase(userProfile.getEmail())){
    		   Report.updateTestLog("Generated email address is same as input(xml file)", "Pass");
    	   }else{
    		   Report.updateTestLog("Generated email address:"+generatedemail+"is not same as input(xml file):"+userProfile.getEmail(), "Fail");
    	   }
       }catch(Exception e){
  		 Report.updateTestLog("Submitted information for generated email is inccorect :"+e, "Fail");
  	 } 
   }
   
   // Validating the Error Message by providing different invalid input combinations for Account Number
   public void validateCustomerReferenceNumberError(final UserProfile userProfile) {
	  
	   final String firstName = userProfile.getFirstName();
	   final String lastName = userProfile.getLastName();
	   final String title = userProfile.getTitle(); 
	   
       final String[] customerNum = {"600104694", "600304694123", "", "600&*4694", "600ab4694"};
       for (int i = 0; i < customerNum.length; i++) {
    	   switch (i){
    	   
    	   case 0:enterForgotEmailData(customerNum[i], title, firstName, lastName); 
    	   clickGetEmailAddressButton();
           errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidAccountNumber);
           break;
           
    	   case 1:enterForgotEmailData(customerNum[i], title, firstName, lastName);
    	   clickGetEmailAddressButton();
           errorMessageComparison(SlingshotErrorMessages.SlingShot_Morethan10digitAccountNumber);
    	   break;
    	   
    	   case 2:enterForgotEmailData(customerNum[i], title, firstName, lastName);
    	   clickGetEmailAddressButton();
           errorMessageComparison(SlingshotErrorMessages.SlingShot_AccountNumberEmpty);
           break;
           
    	   case 3:enterForgotEmailData(customerNum[i], title, firstName, lastName);
    	   clickGetEmailAddressButton();
           errorMessageComparison(SlingshotErrorMessages.SlingShot_AccountNumberWithSpecialCharacter);
           break;
           
    	   default:enterForgotEmailData(customerNum[i], title, firstName, lastName);
    	   clickGetEmailAddressButton();
           errorMessageComparison(SlingshotErrorMessages.SlingShot_AccountNumberWithAlphabets);
    	   break;    		   
    	   }
       }
   }

// Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
    public void errorMessageComparison(final String expectedErrorMsg) {
        final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("ForgotEmailPage.ErrorMessageValidationID"));
        verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);
    }
    

//  Validation for Inappropriate data for the fields displayed in Forgot Email Compare the error message displayed in the 
//  application against the Expected data  	 
     public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
    	 System.out.println(displayedErrorMsg);
    	 System.out.println(expectedErrorMsg);
         if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
             Report.updateTestLog("Error message validation is done. Expected error message: "+ expectedErrorMsg+" Displayed error message: "+ displayedErrorMsg, Pass_Str);
         } else {
        	 Report.updateTestLog("Error message validation is done. Expected error message: "+ expectedErrorMsg+" Displayed error message: "+ displayedErrorMsg, Fail_Str);
         }
     }
     
     //Verifying 'Title' field contents
     public void verifyAndValidateForgotEmailTitleFieldContent(UserProfile userProfile){
    	 int flg = 0;
    	 final String[] titleFieldContents = {"Please select", "Mr", "Mrs", "Ms", "Doctor", "Miss", "Sir", "Reverend", "Dame", "Lady","Professor"};
    	 final ArrayList<String> actualTitleContent =browser.getFromDropBox("id", pageProperties.getProperty("ForgotEmailPage.TitleDropdown"));
    	 for(int i=0;i<titleFieldContents.length; i++){
    		 if(actualTitleContent.get(i).trim().equalsIgnoreCase(titleFieldContents[i].trim())){
    			flg = flg + 1;
    		 }
    		 else {
    			 Report.updateTestLog("Expected 'Title' fileld content does not present", Fail_Str); 
    		 }
    		 if(flg == 12)
    			 Report.updateTestLog("Expected 'Title' field content is present", Pass_Str);  
    	 }
    	 validateForgotEmailTitleField(userProfile.getAccNumber(),actualTitleContent.get(0),userProfile.getFirstName(),userProfile.getLastName());
     }
     
     //Validate 'Title' field content without selecting any value
     public void validateForgotEmailTitleField(String accNumber, String title, String firstName, String lastName){
    	 enterForgotEmailData(accNumber, title,firstName,lastName);
    	 clickGetEmailAddressButton();
    	 errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidTitleSelection);
     }
//   Validating the Error Message by providing different invalid input combinations for Account Number
     public void firstNameErrorMsgValidation(final UserProfile userProfile) {
  	  
  	   final String customerNum = userProfile.getAccNumber();
  	   final String lastName = userProfile.getLastName();
  	   final String title = userProfile.getTitle(); 
  	   
         final String[] firstName = {"ganthimani123", "1234567890", "", "ganth*mani", "ganthimani;"};
         for (int i = 0; i < firstName.length; i++) {
      	   switch (i){
      	   
      	   case 0:enterForgotEmailData(customerNum, title, firstName[i], lastName);  
      	 clickGetEmailAddressButton();
             errorMessageComparison(SlingshotErrorMessages.SlingShot_FirstNameWithNumbers);
             break;
             
      	   case 1:enterForgotEmailData(customerNum, title, firstName[i], lastName);
      	 clickGetEmailAddressButton();
             errorMessageComparison(SlingshotErrorMessages.SlingShot_NumericFirstName);
      	   break;
      	   
      	   case 2:enterForgotEmailData(customerNum, title, firstName[i], lastName);
      	 clickGetEmailAddressButton();
             errorMessageComparison(SlingshotErrorMessages.SlingShot_EmptyFirstName);
             break;
             
      	   case 3:enterForgotEmailData(customerNum, title, firstName[i], lastName);
      	 clickGetEmailAddressButton();
             errorMessageComparison(SlingshotErrorMessages.SlingShot_FirstNameWithStar);
             break;
             
      	   default:enterForgotEmailData(customerNum, title, firstName[i], lastName);
      	 clickGetEmailAddressButton();
             errorMessageComparison(SlingshotErrorMessages.SlingShot_FirstNameWithSemicolon);
      	   break;    		   
      	   }
         }
     }
// Enter the appropriate details in the fields displayed in Forgot Email Page        
    public void enterForgotEmailData(final String CustomerNumber, final String CustomerTitle, final String FirstName, final String SurName) {
    	
    	 browser.wait(getWaitTime());
		   verifyPageTitle(pageProperties.getProperty("ForgotEmailPage.Title"));
		   //verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButtonBoxButton"),"Forgot Email Box Button");
		  /* WebElement element = browser.getElementByXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButtonBoxButton"));
			element.click();*/
		   
		   
		   
		 System.out.println("im out");
    	
		 
    	verifyAndSelectDropDownBox(pageProperties.getProperty("ForgotEmailPage.TitleDropdown"),"Title",CustomerTitle);
    	verifyAndInputById(pageProperties.getProperty("ForgotEmailPage.Firstname"), "Firstname", FirstName);
    	verifyAndInputById(pageProperties.getProperty("ForgotEmailPage.Lastname"), "Lastname",SurName);
    	verifyAndInputById(pageProperties.getProperty("ForgotEmailPage.ContractAccountNumber"), "ContractAccountNumber", CustomerNumber);
    	
     if (browser.isElementVisibleWithXpath(pageProperties.getProperty("ForgotPasswordPage.BackToLoginButton"))){
			   Report.updateTestLog("Back to login link is displayed in the application", "WARN");
		   }
			   else {
				   Report.updateTestLog("Back to login link is not displayed in the application", "FAIL");
		   }
		browser.wait(2000);  
     verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButton"), "Get email address button");
		  
	   }
    	
    	
    
    public void enterForgotEmailDataAndClickCancelButton(final String CustomerNumber, final String CustomerTitle, final String FirstName, final String SurName){
    	verifyAndInputById(pageProperties.getProperty("ForgotEmailPage.ContractAccountNumber"), "ContractAccountNumber", CustomerNumber);
    	verifyAndSelectDropDownBox(pageProperties.getProperty("ForgotEmailPage.TitleDropdown"),"Title",CustomerTitle);
    	verifyAndInputById(pageProperties.getProperty("ForgotEmailPage.Firstname"), "Firstname", FirstName);
    	verifyAndInputById(pageProperties.getProperty("ForgotEmailPage.Lastname"), "Lastname",SurName);
    
    }
    
    public void verifyAuditDetails(final UserProfile userProfile) {     	
    	OnlineDBConnector dbfunctions = new OnlineDBConnector();       
        String sysDate = dbfunctions.DBsysdateDdmmyyhhmi();            
        System.out.println(dbfunctions.getAuditTypeIdMs(userProfile.getEmail(), sysDate,EMAIL_RETRIEVAL_SUCCESS));
        int getAudittype=dbfunctions.getAuditTypeIdMs(userProfile.getEmail(), sysDate, EMAIL_RETRIEVAL_SUCCESS);
        if(getAudittype>=1){
          Report.updateTestLog("Audit entry is made as expected: "+EMAIL_RETRIEVAL_SUCCESS, "PASS");
          
        }else{
        	Report.updateTestLog("Audit entry is made as expected: "+EMAIL_RETRIEVAL_SUCCESS, "FAIL");
        }
        
    }

   public void verifyClickLogintoAccount(UserProfile userProfile){
	   
	   verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.Logintoyouraccount"), "Click-Login to your account button");
	   
   }
   
   //TC015
   //Click and validate 'Cancel' button functionality
   public void clickAndValidateCancelButton(){
	   if(verifyIsTextPresent("Cancel")){ 	   
	   verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.Cancel"), "Click-Cancel button");
	   browser.wait(getWaitTime());
	   verifyPageTitle("Log in to your account");   
	   
	   }
	   else {
		   Report.updateTestLog("Cancel button is not present", "FAIL");
	   }	
   }
   public void clickAndValidateCancelButtonWithValues(UserProfile userProfile){
	   //enterForgotEmailDataAndClickCancelButton(userProfile.getAccNumber(),userProfile.getTitle(),userProfile.getFirstName(),userProfile.getLastName());
	   clickAndValidateCancelButton();
	  
   }
     
   //Verify the functionality of forgotten Email through forgotten password link
   public void clickAndValidateForgottenPasswordLink(UserProfile userProfile){	   
	   getWaitTime();	   
	   if(browser.isTextPresent("Your account")){
		   getWaitTime();
		  verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.ForgottenPasswordLink"), "I've forgotten my password");		  
		 /* if(browser.getTitle().contains("403 Forbidden")){
			  Report.updateTestLog("I've forgotten my password link is not working.So launching URL directly", "PASS");
			  browser.open("http://10.224.70.18/content/bgbusiness/youraccount/bgbusinessForgottenPassword.html");
			  getWaitTime();	 
			 
		  }
		  else{
			  if(browser.getTitle().equalsIgnoreCase("Reset your password")){				 			  	
				  Report.updateTestLog("I've forgotten password link is working fine", "PASS");
				  }
		  }*/
	   }
   }
   
   public void verifyResetPasswordPageFields(){
	  verifyPageTitle(forgottenPasswordpageProperties.getProperty("ForgottenPasswordPage.Title"));
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ForgotPasswordPage.ResetYourPasswordId"), "Reset your password");
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ForgotPasswordPage.EmailFieldByXpath"), "Email Field");
	  verifyIsElementVisibleWithXpath(forgottenPasswordpageProperties.getProperty("ForgottenPasswordPage.ForgottenEmailLink"), "Forgotten email link");
	  verifyIsElementVisibleById(pageProperties.getProperty("ForgotPasswordPage.ContinueButtonId"), "continue");
	  verifyIsElementVisibleWithXpath(pageProperties.getProperty("ForgotPasswordPage.CancelButton"), "Cancel");
	     
   }
   
   public void clickForgottenEmailLink(){
		   verifyAndClickWithXpath(pageProperties.getProperty("ForgotPasswordPage.ForgottenEmailLinkByXpath"), "I've forgotten my email address ");
	 
		 //  Report.updateTestLog("Retrieve email address page displayed successfully", "FAIL");
	   
	  
   }
   
   public void clickLoginButton(){
	   if(browser.isTextPresent(pageProperties.getProperty("ForgotPasswordPage.LoginButton"))){
	   verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.LoginId"), "Login");
	   }
	   else{
		   Report.updateTestLog("Login button is present in 'Retrieve email page'", "FAIL");
	   }
   }
   
   public void loginWithRetrievedEmail(String RetrievEmail,UserProfile userProfile){	   	 
	   verifyAndInputById(loginProperties.getProperty("LoginPage.Email"), "Email", RetrievEmail);
	   verifyAndInputById(loginProperties.getProperty("LoginPage.Password"), "Password", userProfile.getPassword());
	   verifyAndClickWithXpath(loginProperties.getProperty("LoginPage.LoginButtonXpath"), "Login");
	   browser.wait(getWaitTime());		  
	   Report.updateTestLog("User is able login with retrieved email", browser.getTitle().equalsIgnoreCase("Account Overview")?"PASS":"FAIL");	   	   	   
   }
   public void dynamicWait(String property,String propertyname){
   	int count=1;
   	do{
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				getWaitTime();
				e.printStackTrace();
			}
		System.out.println("Count value"+count);	
   		if(propertyname=="xpath"){
   			
				if(browser.isElementVisibleWithXpath(property)){
					System.out.println("xpath-condition");
					break;
				}
   		}else if(propertyname=="id"){
   			System.out.println("id value"+count);
   			if(browser.isElementVisible(property)){
   				System.out.println("id-condition");
   				break;
   			}  			
   		}    		   		
   		
   	}while (count>=10);
   	
   } 
   public void clickGetEmailAddressButton(){
	   verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButton"), "Click-GetEmailAddress button");
		getWaitTime();  
   }
   
   public void validateErrorWhenMorethanOneEmailRetrieves(){
	   String customerNum="600439072";
	   String title = "Mr";
	   String firstName = "michael";
	   String lastName = "s";
	   enterForgotEmailData(customerNum, title, firstName, lastName);  
	   clickGetEmailAddressButton();
       errorMessageComparison(SlingshotErrorMessages.SlingShot_FE_RetrievesMoreThanOneEmailIdError);
   }
   public void clickLoginLink(){
	   verifyAndClickWithXpath(pageProperties.getProperty("ForgotEmailPage.LoginLink"), "Login to your account link");
	   browser.wait(getWaitTime());
	   
	   if (browser.isElementVisibleWithXpath(pageProperties.getProperty("ForgotEmailPage.HomePageLoginLink"))){
		   Report.updateTestLog("Login page is displayed in the application", "WARN");
	   }
		   else {
			   
			   Report.updateTestLog("login page is not displayed in the application", "FAIL");
	   }
	   
	   
   }
}

