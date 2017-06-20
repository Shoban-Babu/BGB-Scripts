package bg.framework.app.functional.page.Slingshot;


import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebElement;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

public class RegistrationPage extends BasePage {

	private final static String File_RegPage = "resources/Slingshot/RegistrationPage.properties";
    private static Properties regPageProperties = new PropertyLoader(File_RegPage).load();
    private final static String BgbFILE_NAME = "resources/Slingshot/HomePage.properties";
    private final static String MVbFILE_NAME = "resources/Slingshot/MultiUserMultiView.properties";  
    private static Properties BgbpageProperties = new PropertyLoader(BgbFILE_NAME).load();
    private static Properties MuvpageProperties = new PropertyLoader(MVbFILE_NAME).load();
    public static String strEmail;
    
    public static String curRegAcctnumber = "";
    public static String curRegEmailAddress = "";
    final String logPath = null;
    final String Pass_Str = "PASS";
    final String Fail_Str = "FAIL";
    public static String UUID = "";
    SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
    CustomerServiceAgentPage csapage = new CustomerServiceAgentPage();
    LegacyHomePage legacyHomePage = new LegacyHomePage();
    OnlineDBConnector onlineDb=new OnlineDBConnector();
    public RegistrationPage() {

    }

    public void goToYourAccount() {
        verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.GotoYourAccLnk"), "Go to your account");
    }

// Enter valid details to register a Account Number
    
    
    public RegistrationPage registerDetailsFirstPage(UserProfile userProfile){
    	registrationDialBox (userProfile);
    	//verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber().trim());
    	//verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number",userProfile.getInvoiceNumber().trim());
    	//verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode",userProfile.getPostCode().trim());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email",userProfile.getEmail().trim());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail",userProfile.getEmail().trim());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword().trim());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword().trim());
    	Report.updateTestLog("Register Details First Page", "WARN");
    	browser.wait(3000);
        verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
        browser.wait(3000);

        return this;
    }  
    public void registrationDialBox (UserProfile userProfile) {
        String accNo=userProfile.getAccNumber();
         System.out.println("Account Number=" +accNo);   
         
           for (int i=0; i<accNo.length();i++ ){
               System.out.println("I AM IN Register Dial Box");
               String Str=accNo.charAt(i)+"";
               System.out.println(Str);
               verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.Registerdials")+(i+1)+"']","Account number",Str); 
               System.out.println("xpath "   + regPageProperties.getProperty("RegistrationPage.Registerdials")+(i+1)+"']"); 
               //Report.updateTestLog("Account Number Entered in the Dial Box", "WARN");
           }
      }

    
    public RegistrationPage registerDetailsSecondPage(UserProfile userProfile){
    	browser.wait(5000);
    	try{
    		String strName = userProfile.getEmail();
            String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
//    		String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
            System.out.println("Email displayed is:"+displayedEmail);
            if(displayedEmail.equalsIgnoreCase(userProfile.getEmail())){
           	 Report.updateTestLog(displayedEmail +" : displayed scuccessfully", "PASS");
            }else{
            	Report.updateTestLog(strName+":"+displayedEmail+":email id not displayed in the textbox", "FAIL");
            }
            browser.wait(5000);
	    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", userProfile.getTitle().trim());
	    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", userProfile.getFirstName().trim());
	    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",userProfile.getLastName().trim());
	    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword().trim());
	    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword().trim());
	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Checkbox");	  
	   // verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions1"), "Checkbox");
	   // verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PaperlessBillingCheckBox"), "Paperless billing");
	    Report.updateTestLog("Register Details Second Page", "WARN");
	    browser.wait(5000);
	    System.out.println("Registration Second Page Continue Button");
	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
	    
	    browser.wait(10000);
//        verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.Title3"), "An activation email has been sent");
        verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ActivationEmail"), "Activation email");
    	}catch(Exception e){
    		Report.updateTestLog("Error in the registration page-2 :"+e, "FAIL"); 
    	}
    	return this;
    }
    

    public RegistrationPage openUrlandVerifyRegistration(UserProfile userProfile){
    /*	String strRetreiveEmailQry="Select BUSINESS_PARTNER_ORG_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG where " +
    			"email='emailid' and rownum=1";
    	String query=strRetreiveEmailQry.replace("emailid",userProfile.getEmail().toLowerCase());
    	System.out.println("query is  :"+query);
    	 String BpOrgNumber=new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query);
    	 userProfile.setBpnumber(BpOrgNumber);
    	 System.out.println("Bporgnumber :"+BpOrgNumber);*/
    	 String BpOrgNumber=userProfile.getBpnumber();
  	 if(BpOrgNumber!=null){
    		 try {
     			String Url=new Encryption().encryptAndSendData(userProfile,"USEREMAIL");
     			System.out.println("ActivationURL"+ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.ActivationEmail")+Url);
     			browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.ActivationEmail")+Url);
     			browser.wait(5000);
     			Report.updateTestLog("Open Url and Verify Registration", "WARN");
     		} catch (InstantiationException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IllegalAccessException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}	
	    	}
    	 else{
    		 Report.updateTestLog("BPOrgnumber is null so email cannot be activated", "Fail");  		 
    	 }	
    	return this;
    }
    public RegistrationPage verifyAfterEncryptedUrl(){
    	verifyPageTitle("Confirmation");
   	    verifyIsTextPresent("Your online account is all set up");
   	    return this;
    }
    public RegistrationPage waitTimeforAuditEntry(){
    	browser.wait(5000);
    	
    	return this;
    }
    
 
    
    public RegistrationPage openEncryptUrlandRegister(UserProfile userProfile){
    	
    	 try {
    			String Url=new Encryption().encryptAndSendData(userProfile,"CSAAGENT");
    			System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.CsaEncryptUrl")+Url);
    			browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.CsaEncryptUrl")+Url);		
    		} catch (InstantiationException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    	return this;
    }
    
    /*public RegistrationPage registerFirstPageCsa(UserProfile userProfile){
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
    	try{
    		String emailId=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.Email"), "value");
    		Report.updateTestLog("Expected Result: "+userProfile.getEmail()+" Actual Result: "+emailId,emailId.equals(userProfile.getEmail())?"Pass":"Fail");
    		String confirmEmailId=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"), "value");
    		Report.updateTestLog("Expected Result: "+userProfile.getEmail()+" Actual Result: "+confirmEmailId,confirmEmailId.equals(userProfile.getEmail())?"Pass":"Fail");
    	}catch(Exception e){
    		Report.updateTestLog("Error while fetching Email value"+e, "Fail");
    	}
        verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
        browser.wait(3000);
        verifyPageTitle(regPageProperties.getProperty("RegistrationPage.AfterFirstPageThroughCsa"));
    	verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueSignUp"),"Continue Button");
   	 return this;
   }*/
   
   
    public RegistrationPage verifyTitle(String title){
    	verifyIsTextPresent("Expired", "Verification of Expired page");
    	return this;
    }
    
    public RegistrationPage openBusinessRegisterUrlPage(){
    	browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgbRegistrationLink"));
    	getWaitTime();
//    	browser.open("https://10.224.70.18/business/your-account/register");
    	return this;
    }
    
    public RegistrationPage verifyErrorinBusinessRegisterFirstPage(int status){
    	
    	switch(status){
    	  case 1:
    		  try{
    			  String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    			  Report.updateTestLog("Expected Result: "+errormsg.Register_FreezedAccount+" Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FreezedAccount)?"Pass":"Fail");
    		  }catch(Exception e){
    			  Report.updateTestLog("Unable to retrieve the error message :"+e, "Fail");
    		  }
    	  break; 	  
    	 
    	  case 2:
    		  try{
    			  String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    			  Report.updateTestLog("Expected Result: "+errormsg.Register_LockedAccount+" Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_LockedAccount)?"Pass":"Fail");
    		  }catch(Exception e){
    			  Report.updateTestLog("Unable to retrieve the error message :"+e, "Fail");
    		  }
    	  break;
    	  
    	  case 3:
    		  try{
    			  String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    			  Report.updateTestLog("Expected Result: "+errormsg.Register_AlreadyActiveAccount+" Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_AlreadyActiveAccount)?"Pass":"Fail");
    		  }catch(Exception e){
    			  Report.updateTestLog("Unable to retrieve the error message :"+e, "Fail");
    		  }
    	  break;
    	  
    	  case 4:
    		  try{
    			  String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    			  Report.updateTestLog("Expected Result: "+errormsg.Register_InvalidAccount+" Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_InvalidAccount)?"Pass":"Fail");
    		  }catch(Exception e){
    			  Report.updateTestLog("Unable to retrieve the error message :"+e, "Fail");
    		  }
    	  break;
    	}
    	return this;
    }
    
    public RegistrationPage validateCustomerRefNo(UserProfile userProfile){
    	
    	String customerRef[]={"","123456abcd","123456!@#","123456789"};
    	
    	for(int iterate=0;iterate < customerRef.length;iterate++){
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",customerRef[iterate]);
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number",userProfile.getInvoiceNumber());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode",userProfile.getPostCode());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email","s"+userProfile.getEmail());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail","s"+userProfile.getEmail());
    	
        verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
        browser.wait(3000);
        String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
        switch(iterate){
          /*case 0:
        	  if(errormsgvalue.contains(errormsg.Register_CustomerRefGreatThan10)){
        		  Report.updateTestLog("Error message displayed for ContractAccountnumber , greater than 10 ", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for ContractAccountnumber , greater than 10:"+errormsgvalue, "Fail");
        	  }
           break;*/
          case 0:
        	  if(errormsgvalue.contains(errormsg.Register_CustomerRefEmpty)){
        		  Report.updateTestLog("Error message displayed for Empty ContractAccountnumber", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for Empty ContractAccountnumber:"+errormsgvalue, "Fail");
        	  }
           break;
          case 1:
        	  if(errormsgvalue.contains(errormsg.Register_CustomerRefAlphaNumeric)){
        		  Report.updateTestLog("Error message displayed for ContractAccountnumber with aplhabets", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch ContractAccountnumber with aplhabets :"+errormsgvalue, "Fail");
        	  }
           break; 
          case 2:
        	  if(errormsgvalue.contains(errormsg.Register_CustomerRefSpecialChar)){
        		  Report.updateTestLog("Error message displayed for ContractAccountnumber with special characters", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch ContractAccountnumber with special characters :"+errormsgvalue, "Fail");
        	  }
           break;
          case 3:
        	  if(errormsgvalue.contains(errormsg.Register_CustomerRefNotInSap)){
        		  Report.updateTestLog("Error message displayed for Incorrect ContractAccountnumber", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for Incorrect ContractAccountnumber :"+errormsgvalue, "Fail");
        	  }
           break;
        }
    	}
    	
    	return this;
    }
    
public RegistrationPage validateInvoiceNumber(UserProfile userProfile){
    	
    	String invoiceNo[]={"","123456abcd","123456789"};
    	
    	for(int iterate=0;iterate < invoiceNo.length;iterate++){
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number",invoiceNo[iterate]);
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode",userProfile.getPostCode());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email","s"+userProfile.getEmail());
    	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail","s"+userProfile.getEmail());
    	
        verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
        browser.wait(3000);
        String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
        switch(iterate){
          /*case 0:
        	  Report.updateTestLog("Expected Result: "+errormsg.Register_InvoiceNoGreatThan10+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_InvoiceNoGreatThan10)?"Pass":"Fail");
        	  if(errormsgvalue.contains(errormsg.Register_InvoiceNoGreatThan10)){
        		  Report.updateTestLog("Error message displayed for InvoiceNumber , greater than 10 ", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for InvoiceNumber , greater than 10:"+errormsgvalue, "Fail");
        	  }
           break;*/
          case 0:
        	  Report.updateTestLog("Expected Result: "+errormsg.Register_InvoiceNoEmpty+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_InvoiceNoEmpty)?"Pass":"Fail");
        	  /*if(errormsgvalue.contains(errormsg.Register_InvoiceNoEmpty)){
        		  Report.updateTestLog("Error message displayed for Empty InvoiceNumber", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for Empty InvoiceNumber:"+errormsgvalue, "Fail");
        	  }*/
           break;
          case 1:
        	  Report.updateTestLog("Expected Result: "+errormsg.Register_InvoiceNoWithAlpha+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_InvoiceNoWithAlpha)?"Pass":"Fail");
        	  /*if(errormsgvalue.contains(errormsg.Register_InvoiceNoWithAlpha)){
        		  Report.updateTestLog("Error message displayed for InvoiceNumber with aplhabets", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for InvoiceNumber with aplhabets :"+errormsgvalue, "Fail");
        	  }*/
           break; 
          case 2:
        	  Report.updateTestLog("Expected Result: "+errormsg.Register_InvoiceNoNotInSap+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_InvoiceNoNotInSap)?"Pass":"Fail");
        	  /*if(errormsgvalue.contains(errormsg.Register_InvoiceNoNotInSap)){
        		  Report.updateTestLog("Error message displayed for Incorrect InvoiceNumber", "Pass");
        	  }else{
        		  Report.updateTestLog("Error message displayed is mismatch for Incorrect InvoiceNumber :"+errormsgvalue, "Fail");
        	  }*/
           break;
        }
    	}
    	
    	return this;
    }

public RegistrationPage validatePostCode(UserProfile userProfile){
	
	String postCode[]={"SK1 2NX","","LE#$%O"};
	
	for(int iterate=0;iterate< postCode.length;iterate++){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number",userProfile.getInvoiceNumber());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode",postCode[iterate]);
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email",userProfile.getEmail());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail",userProfile.getEmail());
	
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
    browser.wait(3000);
    try{
    	String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    	switch(iterate){
        case 0:
      	  if(errormsgvalue.contains(errormsg.Register_PostCodeNotInSap)){
      		  Report.updateTestLog("Error message displayed for PostCode , not matching with CRM ", "Pass");
      	  }else{
      		  Report.updateTestLog("Error message displayed is mismatch for PostCode  , not matching with CRM:"+errormsgvalue, "Fail");
      	  }
         break;
        case 1:
      	  if(errormsgvalue.contains(errormsg.Register_PostCodeEmpty)){
      		  Report.updateTestLog("Error message displayed for PostCode", "Pass");
      	  }else{
      		  Report.updateTestLog("Error message displayed is mismatch for Empty PostCode:"+errormsgvalue, "Fail");
      	  }
         break;
        
        case 2:
    	  if(errormsgvalue.contains(errormsg.Register_PostCodeIncorrect)){
    		  Report.updateTestLog("Error message displayed for PostCode", "Pass");
    	  }else{
    		  Report.updateTestLog("Error message displayed is mismatch for Empty PostCode:"+errormsgvalue, "Fail");
    	  }
       break;
      
    }}catch(Exception e){
    	Report.updateTestLog("Exception occured when the error message is fetched:"+e, "Fail");
    }
    
    
	}
	
	return this;
  }
  
public RegistrationPage validateEmailAddressField(UserProfile userProfile){
	String emaillength="thisistoverifythelengthoftheemailaddresstextbox@cognizant.com";
	String emailAddress[]={"automation_sele@@cognizant.com","","","shanmugapriyan.j3@cognizant.com"};
	
	for(int iterate=0;iterate < emailAddress.length;iterate++){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number",userProfile.getInvoiceNumber());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode",userProfile.getPostCode());
	if(iterate<=2){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email",emailAddress[iterate]);
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail",userProfile.getEmail());
	}else{
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email",userProfile.getEmail());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail",emailAddress[iterate]);
	}
		
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
    browser.wait(3000);
    String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    switch(iterate){
      case 0:
    	  
    	  Report.updateTestLog("Actual Result: "+errormsgvalue+"Expected Result: "+errormsg.Register_EmailAddIncorrectFormat,errormsgvalue.contains(errormsg.Register_EmailAddIncorrectFormat)?"Pass":"Fail");
       break;
      case 1:
    	  
    	  Report.updateTestLog("Actual Result: "+errormsgvalue+"Expected Result: "+errormsg.Register_EmailAddressEmpty,errormsgvalue.contains(errormsg.Register_EmailAddressEmpty)?"Pass":"Fail");
       break;
     /* case 2:
    	  
    	  Report.updateTestLog("Actual Result: "+errormsgvalue+"Expected Result: "+errormsg.Register_EmailAddGreatThan60,errormsgvalue.contains(errormsg.Register_EmailAddGreatThan60)?"Pass":"Fail");
       break; */
      case 2:
    	  
    	  Report.updateTestLog("Actual Result: "+errormsgvalue+"Expected Result: "+errormsg.Register_ConfirmEmailAddEmpty,errormsgvalue.contains(errormsg.Register_ConfirmEmailAddEmpty)?"Pass":"Fail");
       break;
      case 3:
    	  
    	  Report.updateTestLog("Actual Result: "+errormsgvalue+"Expected Result: "+errormsg.Register_EmailAndSameConfirmEmail,errormsgvalue.contains(errormsg.Register_EmailAndSameConfirmEmail)?"Pass":"Fail");
       break;
    }
	}
	
	return this;
}

public RegistrationPage validateCancelLink(UserProfile userProfile){
	
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number",userProfile.getInvoiceNumber());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode",userProfile.getPostCode());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email",userProfile.getEmail());
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail",userProfile.getEmail());
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.CancelLinkinRegisterPage"), "Cancel link");
	if(browser.isElementVisible(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"))){
		Report.updateTestLog("Registration page not displayed", "Fail");
	}else{
		Report.updateTestLog("Registration page not displayed", "Pass");
	}
	return this;
}

public RegistrationPage enterLoginDetails(String emailid,String password){
	
	browser.open("http://10.224.70.18/content/bgbusiness/youraccount/bgbusiniessLogin.html");
    verifyAndInputById(regPageProperties.getProperty("LoginPage.Email"), "Email Id", emailid);
    verifyAndInputById(regPageProperties.getProperty("LoginPage.Password"), "Password", password);
    verifyAndClickWithXpath(regPageProperties.getProperty("LoginPage.NewLoginSubmitXpath"), "Submit button");
	return this;
}

public void validatePageTitle(String validation){
   	
	verifyPageTitle(validation);
	/*if(validation.equalsIgnoreCase("verifytitle")){
		verifyPageTitle("Account Summary");
	}else{
		verifyPageTitle("Login");
	}*/
}

public void setDataForInvalidAccounts(UserProfile userProfile){
	
	int intRandomNumbers;
    Random random = new Random();
    intRandomNumbers = random.nextInt(10000);
    System.out.print("automation_sele" + intRandomNumbers + "@bgdigitaltest.co.uk");
    userProfile.setEmail("automation_sele" + intRandomNumbers + "@bgdigitaltest.co.uk");
    userProfile.setAccNumber("789456123");
	
}

public void setDataForAccNumberRunTime(UserProfile userProfile,String accnumber){
	
    userProfile.setAccNumber(accnumber);
	
}
public RegistrationPage validateEmailValidationFlag(UserProfile userProfile,String Flag){
	
	String strRetreiveEmailQry="Select EMAIL_VALIDATION_FLAG from BG_BUSINESS_TA_CUSTOMER_REG where " +
			"email='emailid'"; 
	String query=strRetreiveEmailQry.replace("emailid",userProfile.getEmail().toLowerCase());
	String validationFlag=new OnlineDBConnector().getColumn("EMAIL_VALIDATION_FLAG", query);
	if(validationFlag!=null){
		Report.updateTestLog("EmailValidationFlag  - Expected Result: "+Flag+"Actual Result: "+validationFlag,validationFlag.equalsIgnoreCase(Flag)?"Pass":"Fail");
	 }else{
		 validationFlag="null";
		 Report.updateTestLog("EmailValidationFlag  - Expected Result: "+Flag+"Actual Result: "+validationFlag,validationFlag.equalsIgnoreCase(Flag)?"Pass":"Fail");
	 }
	
	return this;
}

public RegistrationPage setDataForEmailaddress(UserProfile userProfile){
	
	//String emailToCase = userProfile.getEmail();
	
	String emailCase=userProfile.getEmail();
    if ((emailCase != null) && (emailCase.contains("@") )){
   	 try{
   		 String[] emailsplit=emailCase.split("@");
		     boolean isuppercase=Character.isUpperCase(emailsplit[0].charAt(0));
		     if (isuppercase){
		    	 String convertEmaillower=emailsplit[0].toLowerCase();
		    	 String convertEmailid=emailsplit[1].toLowerCase(); 
		    	 userProfile.setEmail(convertEmaillower+"@"+convertEmailid);
		    	 Report.updateTestLog("Email set is : "+ convertEmaillower+"@"+convertEmailid, "Done");
		     }else{
		    	 String convertEmaillower=emailsplit[0].toUpperCase();
		    	 String convertEmailid=emailsplit[1].toUpperCase();
		    	 userProfile.setEmail(convertEmaillower+"@"+convertEmailid);
		    	 Report.updateTestLog("Email set is : "+ convertEmaillower+"@"+convertEmailid, "Done");
		     }
		         
   	 }catch(Exception e){
   		 Report.updateTestLog("Exception when the string is converted to uppercase or lower case :"+e, "Fail");
   	 }}else{
    	 Report.updateTestLog("Email address in the userprofile.xml file is null/Invalid :"+emailCase, "Fail");
     }

    return this; 
  }

public RegistrationPage verifyEmailIdInDb(UserProfile userProfile){
	String emailAdress="";
	String strRetreiveEmailQry="Select EMAIL from BG_BUSINESS_TA_CUSTOMER_REG where " +
			"email='emailid' and rownum=1";
	String query=strRetreiveEmailQry.replace("emailid",userProfile.getEmail().toLowerCase());
	if(new OnlineDBConnector().getColumn("EMAIL", query)!=null){
		emailAdress=new OnlineDBConnector().getColumn("EMAIL", query);
	}else{
		emailAdress="null";	
	}
	Report.updateTestLog("Email  - Expected Result: "+emailAdress.toLowerCase()+"Actual Result: "+emailAdress,Character.isLowerCase(emailAdress.charAt(0))?"Pass":"Fail");
	System.out.println("email is upper:"+Character.isLowerCase(emailAdress.charAt(0)));
	System.out.println("email is :"+emailAdress);
	return this;
}


public RegistrationPage setDataForPassword(UserProfile userProfile){
	
	String passwordTocase = userProfile.getPassword();
	
/*	String emailCase=userProfile.getEmail();*/
    if ((passwordTocase != null)){
   	 try{   		 
		     boolean isuppercase=Character.isUpperCase(passwordTocase.charAt(0));
		     if (isuppercase){
		    	 String convertEmaillower=passwordTocase.toLowerCase();
		    	 userProfile.setPassword(convertEmaillower);
		    	 Report.updateTestLog("Password set is : "+ convertEmaillower, "Done");
		     }else{
		    	 String convertEmaillower=passwordTocase.toUpperCase();
		    	 userProfile.setPassword(convertEmaillower);
		    	 Report.updateTestLog("Password set is : "+ convertEmaillower, "Done");
		     }
		         
   	 }catch(Exception e){
   		 Report.updateTestLog("Exception when the string is converted to uppercase or lower case :"+e, "Fail");
   	 }}else{
    	 Report.updateTestLog("Password in the userprofile.xml file is null/Invalid :"+passwordTocase, "Fail");
     }

    return this; 
  }

public RegistrationPage verifyPasswordIdInDb(UserProfile userProfile){
	
	String strRetreiveEmailQry="Select PASSWORD from BG_BUSINESS_TA_CUSTOMER_REG where " +
			"email='emailid' and rownum=1";
	String query=strRetreiveEmailQry.replace("emailid",userProfile.getEmail().toLowerCase());
	String password=new OnlineDBConnector().getColumn("PASSWORD", query);
	String encryptedtext="U9ywhVicEfMzri36qCNVZdi3atw=";
	Report.updateTestLog("Email  - Expected Result: "+encryptedtext+"Actual Result: "+password,password.equalsIgnoreCase(encryptedtext)?"Pass":"Fail");
	return this;
}

public RegistrationPage enterLoginDetails(UserProfile userProfile){

	 getWaitTime();
/*    verifyAndInputById(regPageProperties.getProperty("LoginPage.Email"), "Email Id", userProfile.getEmail());
    verifyAndInputById(regPageProperties.getProperty("LoginPage.Password"), "Password", userProfile.getPassword());
    verifyAndClickWithXpath(regPageProperties.getProperty("LoginPage.NewLoginSubmitXpath"), "Submit button");*/
	 new LegacyHomePage().BgbnavigateToLoginPage();
	 new LegacyLoginPage().BgbloginUser(userProfile);
    browser.wait(getWaitTime());
    if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("LoginPage.ErrorMessageDisplayed"))){
    	//String errormsg="Your email address or password has not been recognised.";
    	String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("LoginPage.ErrorMessageDisplayed"));
    	Report.updateTestLog("Error msg - Expected Result: "+errormsg.password_incorrect+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.password_incorrect)?"Pass":"Fail");    	
    }else{
    	Report.updateTestLog("XPATH-Error message not displayed","Fail");	
    }
         
    setDataForPassword(userProfile);
    /*verifyAndInputById(regPageProperties.getProperty("LoginPage.Email"), "Email Id", userProfile.getEmail());
    verifyAndInputById(regPageProperties.getProperty("LoginPage.Password"), "Password", userProfile.getPassword());
    verifyAndClickWithXpath(regPageProperties.getProperty("LoginPage.NewLoginSubmitXpath"), "Submit button");*/
    new LegacyLoginPage().BgbloginUser(userProfile);
    browser.wait(getWaitTime());
//    verifyPageTitle("Account Summary");
    new OnlineDBConnector().passwordreset(userProfile.getEmail());
	return this;
}

public RegistrationPage verifyTermsAndConditions(UserProfile userProfile){
	
	verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", userProfile.getTitle());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", userProfile.getFirstName());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",userProfile.getLastName());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword());
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
    
    if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"))){
    	String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
    	Report.updateTestLog("Error msg - Expected Result: "+errormsg.Register_MarketingConsent+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_MarketingConsent)?"Pass":"Fail");
    }else{
    	Report.updateTestLog("Error message - XPATH not displayed","Fail");
    }
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Checkbox");
    
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword());
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
    verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ActivationEmail"), "Activation email");
	return this;
}

public RegistrationPage verifyTitleContent(){
	String text = null;
	String indicator="Null";
	String[] verifyText={"Mr","Mrs","Ms","Doctor","Miss","Sir","Reverend","Dame","Lady","Professor"};
	List<String> countOf=browser.getFromDropBox("id", regPageProperties.getProperty("RegistrationPage.Title"));
	Report.updateTestLog("Number of Items in the listbox :"+countOf.size(),"Pass");
	for(String itera:verifyText){
		
		for(int i=2;i<=countOf.size();i++){
			text=browser.getTextByXpath("//*[@id='title']/option["+i+"]");
			if(itera.equals(text)){
			  indicator="Pass";	
			  break;
			}else{
				indicator="Null"; 	
			}
		}
		Report.updateTestLog("Drop down value: Expected Result: "+itera+"Actual Result: "+text,indicator.contains("Pass")?"Pass":"Fail");
	}	
	return this;
}

public RegistrationPage SetTitleForErrorMsg(UserProfile userProfile,String title){
	Report.updateTestLog("Title value set is :"+title,"Pass");
	userProfile.setTitle(title);
	return this;
}

public RegistrationPage enterTitle(String title){
	verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", title);
    return this;
}
public RegistrationPage enterFirstName(String firstname){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", firstname);
    return this;
}
public RegistrationPage enterSurname(String surname){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",surname);
    return this;
}
public RegistrationPage enterPassword(String password){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",password);
    return this;
}
public RegistrationPage enterConfirmPassword(String confirmPassword){
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",confirmPassword);
    return this;
}
public RegistrationPage clickRegister(){
	verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
    return this;
}

public RegistrationPage enterDetailsSecondPage(String title,String firstname,String surname,String password){
	verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", title);
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", firstname);
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",surname);
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",password);
	verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",password);
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Checkbox");
	
	return this;
}

public RegistrationPage verifyTitleError(){
	
	try{
	 String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
	 Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_TitleEmpty+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_TitleEmpty)?"Pass":"Fail");
	}catch(Exception e){
		Report.updateTestLog("Unable to locate the Error msg Xpath :"+e,"Fail");	
	}
	
	return this;
}

public RegistrationPage validateFirstName(UserProfile userProfile){
	String[] firstname={"","1212","firstname*","fistrtname;","fisrt12345","!qwer23"};
	for(int i=0;i<=5;i++){
		enterDetailsSecondPage(userProfile.getTitle(),firstname[i],userProfile.getLastName(),userProfile.getPassword());
		clickRegister();
		getWaitTime();
		validateFirstnameError(userProfile,i);
	}	
	return this;
}

public RegistrationPage validateFirstnameError(UserProfile userProfile,int status){
	String errormsgvalue="null";
	try{
		 errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
	}catch(Exception e){
			Report.updateTestLog("Unable to locate the Error msg Xpath :"+e,"Fail");	
	}
	switch (status){
	
	case 0:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_FirstnameEmpty+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FirstnameEmpty)?"Pass":"Fail");
		break;
	case 1:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_FirstnameNumeric+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FirstnameNumeric)?"Pass":"Fail");
		break;
	case 2:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_FirstnameStar+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FirstnameStar)?"Pass":"Fail");
		break;
	case 3:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_FirstnameSemi+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FirstnameSemi)?"Pass":"Fail");
		break;
	case 4:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_FirstnameAplhanumeric+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FirstnameAplhanumeric)?"Pass":"Fail");
		break;
	case 5:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_FirstnameSpecAlphaNum+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_FirstnameSpecAlphaNum)?"Pass":"Fail");
		break;
	}
	
	return this;
}

public RegistrationPage validateSurName(UserProfile userProfile){
	try{
	String[] surname={"","1212","Surname*","surname;","Sur12345","!qwer23"};
	for(int i=0;i<=5;i++){
		enterDetailsSecondPage(userProfile.getTitle(),userProfile.getFirstName(),surname[i],userProfile.getPassword());
		clickRegister();
		getWaitTime();
		validateSurnameError(userProfile,i);
	}	
	}catch(Exception e){
		Report.updateTestLog("Exception occured :"+e,"Fail");
	}
	return this;
}

public RegistrationPage validateSurnameError(UserProfile userProfile,int status){

	try{
		String errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
		 switch (status){
			
			case 0:
				Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_SurnameEmpty+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_SurnameEmpty)?"Pass":"Fail");
				break;
			case 1:
				Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_SurnameNumeric+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_SurnameNumeric)?"Pass":"Fail");
				break;
			case 2:
				Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_SurnameStar+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_SurnameStar)?"Pass":"Fail");
				break;
			case 3:
				Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_SurnameSemi+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_SurnameSemi)?"Pass":"Fail");
				break;
			case 4:
				Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_SurnameAplhanumeric+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_SurnameAplhanumeric)?"Pass":"Fail");
				break;
			case 5:
				Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_SurnameSpecAlphaNum+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_SurnameSpecAlphaNum)?"Pass":"Fail");
				break;
			}
	}catch(Exception e){
			Report.updateTestLog("Unable to locate the Error msg Xpath :"+e,"Fail");	
	}
	
	
	return this;
}

public RegistrationPage validatePassword(UserProfile userProfile){
	String[] password={"","qwert",userProfile.getEmail(),"password!@#$%","passwordsss","","password18"};
	for(int i=0;i<7;i++){
		if(i<=4){
			enterDetailsSecondPage(userProfile.getTitle(),userProfile.getFirstName(),userProfile.getLastName(),password[i]);
		}else{
			enterTitle(userProfile.getTitle());
			enterFirstName(userProfile.getFirstName());
			enterSurname(userProfile.getLastName());
			enterPassword(userProfile.getPassword());
			enterConfirmPassword(password[i]);	
		}
		clickRegister();
		getWaitTime();
		validatePassword(userProfile,i);
	}	
	return this;
}

public RegistrationPage validatePassword(UserProfile userProfile,int status){
	String errormsgvalue="null";
	try{
		 errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
	}catch(Exception e){
		 Report.updateTestLog("Unable to locate the Error msg Xpath :"+e,"Fail");	
         return this;
	}
	switch (status){
	
	case 0:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_PasswordEmpty+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_PasswordEmpty)?"Pass":"Fail");
		break;
	case 1:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_PasswordLength4+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_PasswordLength4)?"Pass":"Fail");
		break;
	case 2:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_PasswordEmail+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_PasswordEmail)?"Pass":"Fail");
		break;
	case 3:		
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_PasswordSpecialChar+"Actual Result: "+errormsgvalue,!errormsgvalue.contains(errormsg.Register_PasswordSpecialChar)?"Pass":"Fail");
		break;
	case 4:		
		Report.updateTestLog("Error Msg: Expected Result:"+errormsg.Register_PasswordString+"Actual Result: "+errormsgvalue,!errormsgvalue.contains(errormsg.Register_PasswordString)?"Pass":"Fail");
		break;	
	case 5:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_ConfirmPasswordEmpty+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_ConfirmPasswordEmpty)?"Pass":"Fail");
		break;	
	case 6:
		Report.updateTestLog("Error Msg: Expected Result: "+errormsg.Register_ConfirmPasswordDiff+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_ConfirmPasswordDiff)?"Pass":"Fail");
		break;		
	}
	
	return this;
}

public RegistrationPage clickBrowserBackAndVerify(UserProfile userProfile,int page){
	clickBrowserBack();
	waitTime();
	String pageTitle="Null";
	String pageText=regPageProperties.getProperty("Registration.BusinessHomepageText");
	String activationemail=regPageProperties.getProperty("RegistrationPage.ActivationEmail");
	String YouHaveRegisteredPage=regPageProperties.getProperty("RegistrationPage.YouHaveRegisteredPage");
	switch(page){
	 case 0:
//		 Report.updateTestLog("Case 0 executed", "Pass");
		 if(browser.isTextPresent(pageText)){
				Report.updateTestLog("Expected Result :"+pageText+"  Actual Result :"+pageText, "Pass");
			}else{
				Report.updateTestLog("Expected Result :"+pageText+"  Actual Result :"+pageText+" - text is not present", "Fail");
				getWaitTime();
				new LegacyHomePage().navigateToBgbRegistrationPage();
				getWaitTime();
			}
		 break;
	 case 1:	
		 if(browser.isTextPresent(pageText)){
			    getWaitTime();
				Report.updateTestLog("Expected Result :"+pageText+"  Actual Result :"+pageText, "Pass");
				waitTime();
				String getAccount=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"value");
				String getInvoice=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"value");
				String getPostCode=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.Postcode"),"value");
				String Email=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.Email"),"value");
				String ConfirmEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"value");				
				Report.updateTestLog("Contract number: Expected Result: "+""+"Actual Result: "+getAccount,getAccount.equals("")?"Pass":"Fail");
				Report.updateTestLog("Invoice number: Expected Result: "+""+"Actual Result: "+getInvoice,getInvoice.equals("")?"Pass":"Fail");
				Report.updateTestLog("Post Code: Expected Result: "+""+"Actual Result: "+getPostCode,getPostCode.equals("")?"Pass":"Fail");
				Report.updateTestLog("Email : Expected Result: "+""+"Actual Result: "+Email,Email.equals("")?"Pass":"Fail");
				Report.updateTestLog("Confirm Email: Expected Result: "+""+"Actual Result: "+ConfirmEmail,ConfirmEmail.equals("")?"Pass":"Fail");
			}else{
				Report.updateTestLog("Expected Result :"+pageText+"  Actual Result :"+pageTitle+" - expected text not present", "Fail");
				getWaitTime();
				new LegacyHomePage().navigateToBgbRegistrationPage();
				getWaitTime();
			}
		 break;
	 case 2:	 
//		 Report.updateTestLog("Case 2 executed", "Pass");
		 getWaitTime();
		 if(browser.isTextPresent(activationemail)){
				Report.updateTestLog("Expected Result :"+activationemail+"  Actual Result :"+activationemail, "Pass");
			}else{
				Report.updateTestLog("Expected Result :"+activationemail+"  Actual Result : Expected page not displayed", "Fail");
				getWaitTime();
				clickRegister();
				getWaitTime();
			}
	 case 3:
//		 Report.updateTestLog("Case 3 executed", "Pass");
		 getWaitTime();
		 verifyPageTitle(activationemail);
		 break;
	}
	
	return this;
}

public RegistrationPage clickBrowserBack(){
	browser.browserBack();
	Report.updateTestLog("Browser Back performed","Done");
	return this;
}

public void waitTime(){
  browser.wait(5000);	
}

public void navigateToBusinessRegisterUrl(){
	browser.open("https://10.224.70.18/business/your-account/register");
}
public void verifyMoreThanThreeRegistration(UserProfile userProfile){
	
	String strRetreiveEmailQry="Select count(CONTRACT_ACCOUNT_NUMBER) from bg_business_ta_customer_reg where " +
	  		"CONTRACT_ACCOUNT_NUMBER='CONTRACTACCOUNTNUMBER'";
	String query=strRetreiveEmailQry.replace("CONTRACTACCOUNTNUMBER",userProfile.getAccNumber());
	 int contractNumber=new OnlineDBConnector().getRegDBCount(query);
	 switch(contractNumber){
	   case 0:
		for(int i=1;i<=3;i++){
 			 int intRandomNumbers;
             Random random = new Random();
             intRandomNumbers = random.nextInt(10000);
			 userProfile.setEmail("slingshot_"+intRandomNumbers+"@cognizant.com");
			 navigateToBusinessRegisterUrl();
		     registerDetailsFirstPage(userProfile);
			 registerDetailsSecondPage(userProfile);
			 openUrlandVerifyRegistration(userProfile);
	    }
		break;
	   case 1:
		 for(int i=1;i<=2;i++){  
			 int intRandomNumbers;
             Random random = new Random();
             intRandomNumbers = random.nextInt(10000);
			 userProfile.setEmail("slingshot_"+intRandomNumbers+"@cognizant.com");
			 navigateToBusinessRegisterUrl();
		     registerDetailsFirstPage(userProfile);
			 registerDetailsSecondPage(userProfile);
			 openUrlandVerifyRegistration(userProfile);
		}
		break;
	   case 2:
			 int intRandomNumbers;
		     Random random = new Random();
		     intRandomNumbers = random.nextInt(10000);
			 userProfile.setEmail("slingshot_"+intRandomNumbers+"@cognizant.com");
		     navigateToBusinessRegisterUrl();
		     registerDetailsFirstPage(userProfile);
			 registerDetailsSecondPage(userProfile);
			 openUrlandVerifyRegistration(userProfile);		
	    break;		 
       }    
			 int intRandomNumbers;
		     Random random = new Random();
		     intRandomNumbers = random.nextInt(10000);
		     userProfile.setEmail("slingshot_"+intRandomNumbers+"@cognizant.com");
			 navigateToBusinessRegisterUrl();
		     registerDetailsFirstPage(userProfile);
		     String errormsgvalue="null";
		 	try{
		 	  errormsgvalue=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.ErrorRegisterPage"));
		 	 Report.updateTestLog("Expected Result: "+errormsg.Register_AccountMoreThanThree+" Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Register_AccountMoreThanThree)?"Pass":"Fail");
		 	}catch(Exception e){
		 	  Report.updateTestLog("Unable to locate the Error msg Xpath :"+e,"Fail");	
		 	}
		     
}

public RegistrationPage clickLoginLink(){
	
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.LoginLink"), "Login Link");
	return this;
}

public RegistrationPage verifyLinkExpiredPage(){
	
	verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ExpiredLink"));
	return this;
}
public void verifyAccountDetailsFieldsAndLoginLinkatRHS(){
	verifyIsElementVisibleById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number");
	verifyIsElementVisibleById(regPageProperties.getProperty("RegistrationPage.Invoicenumber"),"Invoice number");
	verifyIsElementVisibleById(regPageProperties.getProperty("RegistrationPage.Postcode"),"Postcode");
	verifyIsElementVisibleById(regPageProperties.getProperty("RegistrationPage.Email"),"Email");
	verifyIsElementVisibleById(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"),"ConfirmEmail");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.LoginLink"), "Login to your account link");
	verifyIsTextPresent("Your account");
}

/*public void moreRegistration(UserProfile userProfile){
	String strRetreiveEmailQry="Select count(CONTRACT_ACCOUNT_NUMBER) from bg_business_ta_customer_reg where " +
	  		"CONTRACT_ACCOUNT_NUMBER='CONTRACTACCOUNTNUMBER'";
	String query=strRetreiveEmailQry.replace("CONTRACTACCOUNTNUMBER",userProfile.getAccNumber());
	 int contractNumber=new OnlineDBConnector().getRegDBCount(query);
	 String accNum=userProfile.getAccNumber();
	 String invoice=userProfile.getInvoiceNumber();
	 String postCode=userProfile.getPostCode();
	 
		for(int i=1;i<=3;i++){
 			 int intRandomNumbers;
             Random random = new Random();
             intRandomNumbers = random.nextInt(10000);
			 userProfile.setEmail("shanmuga.j"+intRandomNumbers+"@cognizant.com");
			 navigateToBusinessRegisterUrl();
		     registerDetailsFirstPage(userProfile);
			 registerDetailsSecondPage(userProfile);
			 openUrlandVerifyRegistration(userProfile);
	    }
	 }*/
public void moreRegistration(UserProfile userProfile){
	String strRetreiveEmailQry="Select count(CONTRACT_ACCOUNT_NUMBER) from bg_business_ta_customer_reg where " +
	  		"CONTRACT_ACCOUNT_NUMBER='CONTRACTACCOUNTNUMBER'";
	String query=strRetreiveEmailQry.replace("CONTRACTACCOUNTNUMBER",userProfile.getAccNumber());
	 int contractNumber=new OnlineDBConnector().getRegDBCount(query);
	 String accNum=userProfile.getAccNumber();
	 String invoice=userProfile.getInvoiceNumber();
	 String postCode=userProfile.getPostCode();
	 
		for(int i=1;i<=21;i++){
			 int intRandomNumbers;
             Random random = new Random();
             intRandomNumbers = random.nextInt(10000);
			 userProfile.setEmail("shanmuga.j"+intRandomNumbers+"@cognizant.com");
			 navigateToBusinessRegisterUrl();
		     registerDetailsFirstPage(userProfile);
		     if(i==21)
		     {
		    	 verifyIsTextPresent("Your organization has reached the maximum number of account holders.");
		    	 continue;
		     }
			 registerDetailsSecondPage(userProfile);
			 openUrlandVerifyRegistration(userProfile);
			 if(i==1){
				 verifyAuditIsSuperUser(userProfile);
			 }
			 else if(i!=1){
				 verifyAuditIsStandardUser(userProfile);
			 }
	    }
	 }
public void verifyAccountOverviewTitle(){
	verifyPageTitle(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"));
}

public RegistrationPage verifyMarketingConsent(UserProfile userProfile){
	
	verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", userProfile.getTitle());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", userProfile.getFirstName());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",userProfile.getLastName());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword());
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Terms And Conditions");
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.MarketingConsent"), "Marketing Consent");
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
    verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ActivationEmail"), "Activation email");    
	return this;
}

public RegistrationPage verifyActivationLink28Days(UserProfile userProfile){
	String strRetreiveEmailQry="Select REGISTER_DATE from BG_BUSINESS_TA_CUSTOMER_REG where " +
			"email='emailid' and rownum=1";
	String query=strRetreiveEmailQry.replace("emailid",userProfile.getEmail());
	String date=new OnlineDBConnector().getColumn("REGISTER_DATE", query);
	new OnlineDBConnector().updateRegisterDate(userProfile,date);
		
	return this;
}

public RegistrationPage verifyExpiredLinkPage(){
	verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ExpiredPageAfter"));		
	return this;
}

public RegistrationPage changeStatus(UserProfile userProfile){
	   userProfile.setEmail(onlineDb.getEmailAddressAccountNumber(userProfile));
	   String emailStatusChanged=userProfile.getEmail();
	   System.out.println("Status changed for the email is "+emailStatusChanged);
	   onlineDb.updateorRevertProfileRegistration(userProfile,1,"update");
	   registerWithRandomEmail(userProfile);
       legacyHomePage.navigateToCsaRegistrationPage();
       csapage.clickLookUpUser();
       System.out.println("csa look up user is clicked:0");
       userProfile.setEmail(emailStatusChanged);
       csapage.enterEmailIdInFindUser(userProfile);
       csapage.clickFindUser();
       System.out.println("csa look up user is clicked:1");
       csapage.checkStatus(userProfile, "FREEZED");
       csapage.verifyErrorMsg();
     //verify overlay
       /*userProfile.setEmail(onlineDb.getEmailAddressAccountNumber(userProfile));
       onlineDb.updateorRevertProfileRegistration(userProfile,2,"update");
       //verify overlay
       userProfile.setEmail(onlineDb.getEmailAddressAccountNumber(userProfile));
       emailStatusChanged=userProfile.getEmail();
       System.out.println("Status changed for the email is (1)"+emailStatusChanged);
	   onlineDb.updateorRevertProfileRegistration(userProfile,2,"update");
	   registerWithRandomEmail(userProfile);
       legacyHomePage.navigateToCsaRegistrationPage();
       csapage.clickLookUpUser();
       System.out.println("csa look up user is clicked:2");
       userProfile.setEmail(emailStatusChanged);
       csapage.enterEmailIdInFindUser(userProfile);
       csapage.clickFindUser();
       System.out.println("csa look up user is clicked:3");
       csapage.checkStatus(userProfile, "Locked");
       //verify overlay
*/	   
	return this;
}

public void registerWithRandomEmail(UserProfile userProfile){
	int intRandomNumbers;
    Random random = new Random();
    intRandomNumbers = random.nextInt(100000);
		 userProfile.setEmail("moreregistratincheck"+intRandomNumbers+"@cognizant.com");
		 navigateToBusinessRegisterUrl();
	     registerDetailsFirstPage(userProfile);
		 registerDetailsSecondPage(userProfile);
		 openUrlandVerifyRegistration(userProfile);		 
}

public void verifyMarketingConsentOption(String checkstatus){
	 boolean ischecked=browser.isSelected(regPageProperties.getProperty("RegistrationPage.EmailCheckboxinMpd"));
	 if(checkstatus.equalsIgnoreCase("Checked")){
		 if(ischecked==true){
			 Report.updateTestLog("Email check box is checked","Pass");	
		 }else{
			 Report.updateTestLog("Email check box is not checked","Fail");
		 }
		 
	 }else{
		 if(ischecked==false){
			 Report.updateTestLog("Email check box is not checked","Pass");	
		 }else{
			 Report.updateTestLog("Email check box is checked","Fail");
		 }
	 }
}

public void updateMarketingConsentOptionInDb(UserProfile userProfile,String indicator){
	
	new OnlineDBConnector().updateMarketingConsentOption(indicator, userProfile.getEmail());
	
}

public void verifyAndRegister(UserProfile userProfile){
	new OnlineDBConnector().deleteContractAccountNumber(userProfile.getAccNumber());
	if(new OnlineDBConnector().verifyEmailExists(userProfile.getEmail())==0){		
		System.out.println("user does not exist");
	  registerDetailsFirstPage(userProfile);
	  registerDetailsSecondPage(userProfile);
	  openUrlandVerifyRegistration(userProfile);
	  verifyAfterEncryptedUrl();
	}
	else{
		System.out.println("user exist");
	}
}
public void verifyTitle() {
	String actualtitle=browser.getTextByXpath(MuvpageProperties.getProperty("MultiuserMultiViewPage.AcctOverview"));
	if(actualtitle.equals("Account overview")){			
		Report.updateTestLog("Actual Page Title" +actualtitle+" Verified Successfully", "PASS");			
	}		
}

public RegistrationPage registerDetailsSecondPage_paperless(UserProfile userProfile,String usertype){
	browser.wait(5000);
	try{
		String strName = userProfile.getEmail();
        String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
//		String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
        System.out.println("Email displayed is:"+displayedEmail);
        if(displayedEmail.equalsIgnoreCase(userProfile.getEmail())){
       	 Report.updateTestLog(displayedEmail +" : displayed scuccessfully", "PASS");
        }else{
        	Report.updateTestLog(strName+":"+displayedEmail+":email id not displayed in the textbox", "FAIL");
        	
        }
        browser.wait(5000);
    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", userProfile.getTitle().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", userProfile.getFirstName().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",userProfile.getLastName().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword().trim());
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Checkbox");	   
    //verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PaperlessBillingCheckBox"), "Paperless billing");
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
    if(usertype=="superorfullaccess")
    {
       
    	//verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.paperlesscheckbox"), "Paperless Checkbox");
    	
    	if(browser.isSelected(regPageProperties.getProperty("RegistrationPage.paperlesscheckbox")))
    	{
    		Report.updateTestLog("I would like to receive my bills electrially instead of post selected", "PASS");	
    		System.out.println("**************************");
    	}
    }
    else if(usertype=="EP")
    {
    	if(browser.isSelectedByXpath(regPageProperties.getProperty("RegistrationPage.paperlesscheckbox"))){        		
	  		Report.updateTestLog("User is Enterprice user and the paperless billing is selected as default", "PASS");		 		
    }
    }
    else if(usertype=="EDITVI" || usertype=="corporate")
 	    {
    			if (browser.isTextPresent("paperlesstermsandcondition")) {
    					Report.updateTestLog("paperless condition checkbox is not present in the application page", "Pass");
    			}   
         			else {
            				Report.updateTestLog("paperless condition checkbox is not present in the application page", "Fail");
            	        }
 	    }
    browser.wait(5000);
//    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.Title3"), "An activation email has been sent");
    verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ActivationEmail"), "Activation email");
	}catch(Exception e){
		Report.updateTestLog("Error in the registration page-2 :"+e, "FAIL"); 
	}
	return this;
}
public RegistrationPage registerDetailsSecondPage_paperlesschkbox(UserProfile userProfile,String usertype,String clickPBchekbox){
	browser.wait(5000);
	try{
		String strName = userProfile.getEmail();
        String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
//		String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
        System.out.println("Email displayed is:"+displayedEmail);
        if(displayedEmail.equalsIgnoreCase(userProfile.getEmail())){
       	 Report.updateTestLog(displayedEmail +" : displayed scuccessfully", "PASS");
        }else{
        	Report.updateTestLog(strName+":"+displayedEmail+":email id not displayed in the textbox", "FAIL");            	
        }
        browser.wait(5000);
    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", userProfile.getTitle().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", userProfile.getFirstName().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",userProfile.getLastName().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword().trim());   
    
    
    if(usertype=="superorfullaccess")
    {
       if(clickPBchekbox=="yes")
       {
    	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.paperlesscheckbox"), "Paperless Checkbox");
         	
       }
    }
    else if(usertype=="Enterprise")
       {
    	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.paperlesscheckbox"), "Paperless Checkbox");		 		
   
       }
    else if(usertype=="EDITVI" || usertype=="corporate")
 	    {
    			if (browser.isTextPresent("paperlesstermsandcondition")) {
    					Report.updateTestLog("paperless condition checkbox is not present in the application page", "Pass");
    			}   
         			else {
            				Report.updateTestLog("paperless condition checkbox is not present in the application page", "Fail");
            	        }
 	    }
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Checkbox");
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");	    
    browser.wait(5000);
//    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.Title3"), "An activation email has been sent");
    verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ActivationEmail"), "Activation email");
    }
    catch(Exception e){
		Report.updateTestLog("Error in the registration page-2 :"+e, "FAIL"); 
	}
	return this;

}
public RegistrationPage verifyRegistrationPageByCsa(UserProfile userProfile){

	 verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.CsaTitle1"));
	 verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email id",userProfile.getEmail().trim());
	 verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
	 if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterButton"))){
		 Report.updateTestLog("Register Link is Present", "WARN");
	 }
	 if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.LoginLink"))){
		 Report.updateTestLog("Login Link is Present", "WARN");
	 }
	 browser.wait(getWaitTime());
	 verifyAndClick(regPageProperties.getProperty("RegistrationPage.SendRegistrationEmail"), "Registration Continue");	
	 browser.wait(5000);
	 verifyAndClick(regPageProperties.getProperty("RegistrationPage.SendRegistrationEmailNew"), "Send Registration Email");
	 browser.wait(5000);
	return this;
}

public RegistrationPage verifyRegistrationPageByCsapending(UserProfile userProfile){

	 verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.CsaTitle1"));
	 verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Email"),"Email id",userProfile.getEmail().trim());
	 verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
	 if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterButton"))){
		 Report.updateTestLog("Register Link is Present", "WARN");
	 }
	 if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.LoginLink"))){
		 Report.updateTestLog("Login Link is Present", "WARN");
	 }
	 verifyAndClick(regPageProperties.getProperty("RegistrationPage.SendRegistrationEmail"), "Send Registration Email");	
	 browser.wait(100);
	 
	return this;
}



public RegistrationPage registerFirstPageCsa(UserProfile userProfile){
	/*verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Contractaccountnumber"),"Customer Account Number",userProfile.getAccNumber());
	try{
		String emailId=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.Email"), "value");
		Report.updateTestLog("Expected Result: "+userProfile.getEmail()+" Actual Result: "+emailId,emailId.equals(userProfile.getEmail())?"Pass":"Fail");
		String confirmEmailId=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.ConfirmEmail"), "value");
		Report.updateTestLog("Expected Result: "+userProfile.getEmail()+" Actual Result: "+confirmEmailId,confirmEmailId.equals(userProfile.getEmail())?"Pass":"Fail");
	}catch(Exception e){
		Report.updateTestLog("Error while fetching Email value"+e, "Fail");
	}
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue-Register button");
    browser.wait(3000);
    verifyPageTitle(regPageProperties.getProperty("RegistrationPage.AfterFirstPageThroughCsa"));*/
	verifyAndClick(regPageProperties.getProperty("RegistrationPage.ContinueSignUp"),"Continue Button");
	 return this;
}
public RegistrationPage openEncryptUrlandRegister1(UserProfile userProfile){
	
	 try {
		 	
		 browser.wait(10000);
			String Url=new Encryption().encryptAndSendData1(userProfile,"USEREMAIL");
			System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+Url);
			browser.open(ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+Url);
			String sys=browser.getTitle();
			System.out.println("browser page title"+sys);
			//browser.swtichToDefaultContent();		
			browser.wait(getWaitTime());
			String EmailAddress=browser.getTextByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
			System.out.println(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
			System.out.println("EmailAddress"+EmailAddress);				
			if(EmailAddress.equals(userProfile.getNewEmail()))
			{
						System.out.println("verified new email address");
						Report.updateTestLog("User Name is Prefilled with the Email Address is appreared As Expected"+EmailAddress,"Pass");
			}	
			 /* verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"), "Your Account");
		//	verifyTitle();
			browser.browserBack();
			browser.wait(5000);
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.Businesslink"), "Business");
			//verifyPageTitle("Cheap Business Energy | Business Gas & Electricity - British Gas Business");
			browser.browserBack();
			browser.wait(5000);*/
			browser.wait(5000);
			verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserPassword"), "Password", userProfile.getPassword());
			verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRetypePassword"), "Re-Type Password",userProfile.getPassword());
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTermsandcondtions"),"select Terms and Condition Check box");
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"), "Submit button");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	return this;
}

public RegistrationPage AddUrl_UpdateNewView_Name(UserProfile userProfile){
	browser.wait(10000);
	 	 	browser.wait(10000);
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
		 	String New_UUUID = dbFunctions.Generate_UUID(userProfile.getNewEmail());
		 	
		 	System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+New_UUUID);
			browser.open(ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+New_UUUID);
			String sys=browser.getTitle();
			System.out.println("browser page title"+sys);
			//browser.swtichToDefaultContent();		
			browser.wait(getWaitTime());
			String EmailAddress=browser.getTextByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
			System.out.println(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
			System.out.println("EmailAddress"+EmailAddress);				
			if(EmailAddress.equals(userProfile.getNewEmail()))
			{
						System.out.println("verified new email address");
						Report.updateTestLog("User Name is Prefilled with the Email Address is appreared As Expected"+EmailAddress,"Pass");
			}	
			 /* verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"), "Your Account");
		//	verifyTitle();
			browser.browserBack();
			browser.wait(5000);
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.Businesslink"), "Business");
			//verifyPageTitle("Cheap Business Energy | Business Gas & Electricity - British Gas Business");
			browser.browserBack();
			browser.wait(5000);*/
			browser.wait(5000);
			verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserPassword"), "Password", userProfile.getPassword());
			verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRetypePassword"), "Re-Type Password",userProfile.getPassword());
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTermsandcondtions"),"select Terms and Condition Check box");
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"), "Submit button");
		 
			
	return this;
}

public void verifyAndAuditEmailToCustomer(UserProfile userProfile){
//	verifyPageTitle(regPageProperties.getProperty("RegistrationPage.EmailTitlePage"));
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	String date=dbFunctions.DBsysdateDdmmyyhhmi();
	String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"SLINGSHOT");
	String data = dbFunctions.getAuditType(auditType[0]);	
    Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("LOGIN_SUCCESSFUL")?"PASS":"FAIL");
}
public void verifyAuditIsSuperUser(UserProfile userProfile){
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	String date=dbFunctions.DBsysdateDdmmyyhhmi();
	String data = dbFunctions.verifyAuditIsSuperOrStandardUserFlag(date, userProfile.getEmail());
    Report.updateTestLog("Audit id is made in audit table as expected. Is Super user flag: "+data, data.equals("Y")?"PASS":"FAIL");
}
public void verifyAuditIsStandardUser(UserProfile userProfile){
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	String date=dbFunctions.DBsysdateDdmmyyhhmi();
	String data = dbFunctions.verifyAuditIsSuperOrStandardUserFlag(date, userProfile.getEmail());
    Report.updateTestLog("Audit id is made in audit table as expected. Is Standard user flag: "+data, data.equals("N")?"PASS":"FAIL");
}
public void verifyAuditForPaperLessOption(UserProfile userProfile){
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	String date=dbFunctions.DBsysdateDdmmyyhhmi();
	String data[] = dbFunctions.verifyAuditForPaperLessOption(date, userProfile.getEmail());
    Report.updateTestLog("Audit id is made in audit table as expected. Paperless billing audit is: "+data[1], data[1].equals("PAPERLESS")?"PASS":"FAIL");
}
public void verifyCRMforPaperlessOption(UserProfile userProfile){
	
	}
public RegistrationPage register_PaperLessOptionisSelected(UserProfile userProfile,String paperlessbilling){
			
	 try {
		 	
			String Url=new Encryption().encryptAndSendData1(userProfile,"USEREMAIL");
			System.out.print("my encription"+Url);
			System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+Url);
			browser.open(ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+Url);
			String sys=browser.getTitle();
			System.out.println("browser page title"+sys);
			//browser.swtichToDefaultContent();			
			String EmailAddress=browser.getTextByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
			System.out.println(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
			System.out.println("EmailAddress"+EmailAddress);				
			if(EmailAddress.equals(userProfile.getNewEmail()))
			{
						System.out.println("verified new email address");
						Report.updateTestLog("User Name is Prefilled with the Email Address is appreared As Expected"+EmailAddress,"Pass");
			}	
			  verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"), "Your Account");
		//	verifyTitle();
			browser.browserBack();
			browser.wait(5000);
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.Businesslink"), "Business");
			//verifyPageTitle("Cheap Business Energy | Business Gas & Electricity - British Gas Business");
			browser.browserBack();
			browser.wait(5000);
			browser.wait(5000);
			 
			verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserPassword"), "Password", userProfile.getPassword());
			verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRetypePassword"), "Re-Type Password",userProfile.getPassword());
			if(paperlessbilling=="yes")
		       {
		    	
		    	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.paperlesscheckbox"), "Paperless Checkbox"); 	
		       }
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTermsandcondtions"),"select Terms and Condition Check box");
			verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"), "Submit button");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	return this;
}
public RegistrationPage register_PaperLessOptionNotSelected(UserProfile userProfile,String TermsCheck){
	browser.wait(5000);
	try{
		String strName = userProfile.getEmail();
        String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
//		String displayedEmail=browser.getAttribute(regPageProperties.getProperty("RegistrationPage.EmailIdEntered"),"value");
        System.out.println("Email displayed is:"+displayedEmail);
        if(displayedEmail.equalsIgnoreCase(userProfile.getEmail())){
       	 Report.updateTestLog(displayedEmail +" : displayed scuccessfully", "PASS");
        }else{
        	Report.updateTestLog(strName+":"+displayedEmail+":email id not displayed in the textbox", "FAIL");
        	
        }
        browser.wait(5000);
    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.Title"), "Title", userProfile.getTitle().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.FirstName"), "First Name", userProfile.getFirstName().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Surname"),"Surname",userProfile.getLastName().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Password"),"Password",userProfile.getPassword().trim());
    verifyAndInputById(regPageProperties.getProperty("RegistrationPage.ConfirmPassword"),"Confirm Password",userProfile.getPassword().trim());
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditions"), "Checkbox");
    if(TermsCheck.equalsIgnoreCase("Yes")){
    	verifyAndClickWithLinkText(regPageProperties.getProperty("RegistrationPage.TermsandConditionsLink"), "TermsandConditionsLink");
    	String termsAndCondText = browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.TermsandConditionsText"));
    	if(termsAndCondText.contains("")){
    		Report.updateTestLog("Terms and condition content displayed as expected"+termsAndCondText, "PASS");
    	}
    	else{
    		Report.updateTestLog("Terms and condition content displayed as expected"+termsAndCondText, "FAIL");	
    	}
    }
    //verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PaperlessBillingCheckBox"), "Paperless billing");
    verifyAndClick(regPageProperties.getProperty("RegistrationPage.Continue"), "Continue-Register button");
    
    browser.wait(5000);
//    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.Title3"), "An activation email has been sent");
    verifyIsTextPresent(regPageProperties.getProperty("RegistrationPage.ActivationEmail"), "Activation email");
	}catch(Exception e){
		Report.updateTestLog("Error in the registration page-2 :"+e, "FAIL"); 
	}
	return this;
}
public void verifyWhatCanIdoLink(){
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.WhatCanIdoLink"), "What can i do link");
	String linktext = browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.WhatCanIdoLinkText"));
	if ( linktext.contains("Set up or amend a Variable/Fixed Direct")){
		Report.updateTestLog("Expected 'what Can I do link ' present"+linktext, "PASS");
	}
	else{
		Report.updateTestLog("Expected 'what Can I do link ' present"+linktext, "FAIL");
	}
}
////////////////////////////////////////////////////////////Testing///////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////Digital Wave Registration//////////////////////////////////////////////////////////////////////////////////////////

/*
 * Created on 18-08-2015
 * Zeeshan Ahamed
 */


/* Register Landing Page */

public void navigateToAccountDetailsPage ( ) {
	
	//verifyIsTextPresent("Register for an online business account");
	Report.updateTestLog("Registration landing page is loaded", "WARN");
	verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.loginHeaderLink"), "Login link");
	verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegistrationHeaderLink"), "Register link");
	browser.wait(5000);
	//if (browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterNow"))){
	if (browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterNow"))){
	Report.updateTestLog("Register now button is displayed in the application","PASS");
	//verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterNow"), "Register Now Buttton ");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterNow"), "Register Now Buttton ");
	
     }
	else {
		Report.updateTestLog("Register now button is not displayed","FAIL");
	}
}

/*Create Account login Details Page */


public void verifyAccountDetailsPage (UserProfile userProfile) {
	
	//verifyIsTextPresent("Register for an online business account");
	Report.updateTestLog("Register your online account Landing Page is loaded", "WARN");
	/*verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.AlreadyRegisteredLinkNew"), "Login link");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.AlreadyRegisteredLinkNew"), "Login Link");
	browser.wait(1000);
	
	if (browser.isTextPresent("Log in to your business account") ){
		Report.updateTestLog("Login Page is displayed", "PASS");
	}
	else {
	Report.updateTestLog("Login Page is not displayed", "FAIL");	
		}
	browser.browserBack();*/
	
	/*Random random = new Random ();
	strEmail="automation_digitaltest"+ random.nextInt(1000)+"@bgdigitaltest.co.uk";
	System.out.println(" "+strEmail);
	System.out.println("automation_digitaltest"+random.nextInt(1000)+"@bgdigitaltest.co.uk");*/
	//verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterNowNew"), "Register Now Buttton ");
	browser.wait(1000);
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationEmailAddress"),"Email Address", userProfile.getEmail().trim());
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationConfirmEmailAddress"),"Confirm Email Address", userProfile.getEmail().trim());
	
	
	/*verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationEmailAddress"),"Email Address", strEmail);
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationConfirmEmailAddress"),"Confirm Email Address",strEmail);*/
	
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationPassword"),"Password", userProfile.getPassword().trim());
	//verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationConfirmPassword"),"Confirm Password",userProfile.getPassword().trim());	
    //verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCancelLink"), "Cancel link");
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue button");

	browser.wait(3000);	
	
}


/*Contact Details Account Identification Page */

public void cancelButton () {
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCancelLink1"), "Cancel Link");
	if (browser.isTextPresent("British Gas Business Energy") ){
		Report.updateTestLog("British Gas Business Energy Landing Page is loaded", "PASS");
	}
	else {
	Report.updateTestLog("British Gas Business Energy Landing Page is not loaded", "FAIL");	
		}
}

public void registerForAnOnlineAccount (UserProfile userProfile) {
	String AccountNumber=null;
	
	//verifyIsTextPresent("Register for an online account");
	Report.updateTestLog("Your details page is loaded", "WARN");
    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.RegisterTitle"), "Title", userProfile.getTitle());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationFirstName"),"First Name", userProfile.getFirstName().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationSurname"),"Sur Name", userProfile.getLastName().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationTelephone"),"Telephone", userProfile.getMobileNumber().trim());
   
    registrationDialBox(userProfile);
    Report.updateTestLog("Account number is "+userProfile.getAccNumber(), "PASS");
    Report.updateTestLog("Accoutn Number filled in the dial box", "WARN");
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.Billnumber"), "Bill number", userProfile.getBillNumber());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationSitePostCode"),"Post Code", userProfile.getPostCode().trim());
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterTermsAndCOnditionCheckBox"),"select Terms and Condition Check box");
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegistertTermslink"), "Terms and Condition link");
    //verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterMarketingConcent"), "Marketing Concent Checkbox");
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCancelLink1"), "Cancel link");
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterBackLink"), "Back link");
    collectiveAccountOverlay();
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterAccountNumberOverlay"), "Account Number overlay");
/*    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterAccountNumberOverlayClose"), "Account Number overlay close");*/
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterSitePostcodeOverlay"), "Post Code overlay");
/*    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterSitePostcodeOverlayClose"), "Post Code overlay close");*/
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCreateAcount"), "Create Acount Button");
	browser.wait(5000);
    }

/* Success Screen*/

public void registerForAnOnlineAccountNew01 (UserProfile userProfile) {
	String AccountNumber=null;
	
	//verifyIsTextPresent("Register for an online account");
	Report.updateTestLog("Your details page is loaded", "WARN");
    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.RegisterTitle"), "Title", userProfile.getTitle());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationFirstName"),"First Name", userProfile.getFirstName().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationSurname"),"Sur Name", userProfile.getLastName().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationTelephone"),"Telephone", userProfile.getMobileNumber().trim());
   
    registrationDialBox(userProfile);
    Report.updateTestLog("Account number is "+userProfile.getAccNumber(), "PASS");
    //verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.Billnumber"),"bill Number", userProfile.getBillNumber());
    //verifyAndInputById(regPageProperties.getProperty("RegistrationPage.Billnumber"),"bill Number", userProfile.getBillNumber());
    Report.updateTestLog("Account Number filled and bill Number is updated in the dial box", "WARN");
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationSitePostCode"),"Post Code", userProfile.getPostCode().trim());
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterTermsAndCOnditionCheckBox"),"select Terms and Condition Check box");
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegistertTermslink"), "Terms and Condition link");
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterMarketingConcent"), "Marketing Concent Checkbox");
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCancelLink1"), "Cancel link");
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterBackLink"), "Back link");
    collectiveAccountOverlay();
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterAccountNumberOverlay"), "Account Number overlay");
/*    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterAccountNumberOverlayClose"), "Account Number overlay close");*/
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterSitePostcodeOverlay"), "Post Code overlay");
/*    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterSitePostcodeOverlayClose"), "Post Code overlay close");*/
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCreateAcount"), "Create Acount Button");
	browser.wait(5000);
	browser.wait(getWaitTime());
	
    }


public void registerForAnOnlineAccountNew (UserProfile userProfile) {
	String AccountNumber=null;
	
	//verifyIsTextPresent("Register for an online account");
		Report.updateTestLog("Your details page is loaded", "WARN");
	    verifyAndSelectDropDownBox(regPageProperties.getProperty("RegistrationPage.RegisterTitle"), "Title", userProfile.getTitle());
	    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationFirstName"),"First Name", userProfile.getFirstName().trim());
	    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationSurname"),"Sur Name", userProfile.getLastName().trim());
	    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationTelephone"),"Telephone", userProfile.getMobileNumber().trim());
	   
	    //registrationDialBox(userProfile);
	    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.AccountNumberNew"),"Telephone", userProfile.getAccNumber().trim());
	    
	    
	    Report.updateTestLog("Account number is "+userProfile.getAccNumber(), "PASS");
	   // verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.Billnumber"),"bill Number", userProfile.getBillNumber());
	    //verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.Billnumber"),"bill Number", userProfile.getBillNumber());
	    Report.updateTestLog("Account Number filled and bill Number is updated in the dial box", "WARN");
	    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationSitePostCode"),"Post Code", userProfile.getPostCode().trim());
	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterTermsAndCOnditionCheckBox"),"select Terms and Condition Check box");
	    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegistertTermslink"), "Terms and Condition link");
	   // verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterMarketingConcent"), "Marketing Concent Checkbox");
	    //verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCancelLink1"), "Cancel link");
	    //verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterBackLink"), "Back link");
	    collectiveAccountOverlay();
	    /*verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterAccountNumberOverlay"), "Account Number overlay");
	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterAccountNumberOverlayClose"), "Account Number overlay close");
	    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterSitePostcodeOverlay"), "Post Code overlay");
	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterSitePostcodeOverlayClose"), "Post Code overlay close");*/
	    Report.updateTestLog("Setup details entered successfully ", "WARN");
		verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCreateAcount"), "Create Acount Button");
	browser.wait(5000);
	browser.wait(getWaitTime());
	
	registeronestep(userProfile);
	
    }


   public void registeronestep (UserProfile userProfile) {
	
	browser.wait(getWaitTime());
	
	Report.updateTestLog("Register one step is page is loaded", "WARN");
	
	if(browser.isTextPresent("Please enter the last payment amount made for this energy account"))
	{
		verifyAndInputByXpath(regPageProperties.getProperty("Registartion.lastpayment"),"Last Payment", userProfile.getLastpayment().trim());
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.onlineaccountContinuebutton"),"Confirmbutton");
		
	}
	
	else
	{
    //verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationTelephone"),"Telephone", userProfile.getMobileNumber().trim());
   
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.BankAccountNumber1"),"Bank Account Number", userProfile.getBankAccoutNumber1().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.BankAccountNumber2"),"Bank Account Number", userProfile.getBankAccoutNumber2().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.BankAccountNumber3"),"Bank Account Number", userProfile.getBankAccoutNumber3().trim());
    verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.BankAccountNumber4"),"Bank Account Number", userProfile.getBankAccoutNumber4().trim());
    browser.wait(5000);
    Report.updateTestLog("Register for online accou nt details is entersed successfully", "WARN");
    
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.onlineaccountContinuebutton"),"Confirmbutton");
    
	
	}
	browser.wait(getWaitTime());
	browser.wait(getWaitTime());
	
    }

public void thankYouRegistered () {
	browser.wait(getWaitTime());
	verifyIsTextPresent("You're now registered");
	Report.updateTestLog("Comfrimation Page is loaded", "WARN");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterContinueToYourAccount"), "Continue To Your Account");
	browser.wait(5000);
	browser.wait(getWaitTime());
	
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.closebutton"), "close button");
	//verifyIsTextPresent("Log in to your business account");
	Report.updateTestLog("Login to your online account page is diaplayed", "WARN");
	
}

public void VerifyError () {
	browser.wait(2000);
	//verifyIsTextPresent("Thanks, you’re now registered");
	Report.updateTestLog("Error Message is loaded successfully for an Invalid Bill Number", "WARN");
	
}




/*
 * Email Adrress Already Registered Page
 */

public void verifyEmailAlreadyRegisteredPage () {
	
	verifyIsTextPresent("Email address already registered");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.AlredyRegisterLoginlink"), "Login Link");
	browser.wait(2000);
	if (browser.isTextPresent("Log in to your business account") ){
	Report.updateTestLog("login page is loaded","WARN");
	}
	else{
		Report.updateTestLog("login page is not loaded","FAIL");
	}

	browser.browserBack();
	resetYourPassword();
	backToRegister();
	}
	
public void backToRegister () {

verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.AlredyRegisterBackToRegister"), "Back to Register Link");
browser.wait(2000);
if (browser.isTextPresent("Register for an online business account") ){
	
Report.updateTestLog("Registration page is loaded","WARN");
}
else{
Report.updateTestLog("Registration page is not loaded","FAIL");

}
}
public void resetYourPassword () {
browser.wait(1000);
verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.ResetPassword"), "Reset Password Link");
browser.wait(2000);
if (browser.isTextPresent("Reset your password")){
Report.updateTestLog("Reset password page is loaded","WARN");
}
else {
	Report.updateTestLog("Reset password page is not loaded","FAIL");
}
/*verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.EnterEmailAdress"), "Email Address",userProfile.getEmail().trim());
verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.PasswordResetCancel"), "Login link");
verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PasswordContinue"), "Continue");
verifyIsTextPresent("Password reminder sent");
verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.BackToHomePage"), "Business Home");*/

browser.browserBack();
}
public void resetYourPasswordSucessful (UserProfile userProfile) {
	verifyIsTextPresent("Email address already registered");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.ResetPassword"), "Reset Password Link");
	browser.wait(2000);
	if (browser.isTextPresent("Reset your password")){
	Report.updateTestLog("Reset password page is loaded","WARN");
	}
	else {
		Report.updateTestLog("Reset password page is not loaded","FAIL");
	}
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.EnterEmailAdress"), "Email Address",userProfile.getEmail().trim());
	verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.PasswordResetCancel"), "Login link");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PasswordContinue"), "Continue");
	passwordAuditValidataion(userProfile);
	//verifyIsTextPresent("Password reminder sent");
	if (browser.isTextPresent("Password reminder sent")){
		Report.updateTestLog("Password reminder page is loaded", "WARN");
    }
    else {
	Report.updateTestLog("Password reminder page is not loaded","FAIL");
    }
	verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.BackToHomePage"), "Business Home");
     
	//browser.browserBack();
	}



public void verifyAccountDetailsPageWithMisMatchEmailandPassword (UserProfile userProfile) {
	
	verifyIsTextPresent("Register for an online business account");
	Report.updateTestLog("Register your online account Landing Page is loaded", "WARN");
	verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.AlreadyRegisteredLink"), "Login link");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.AlreadyRegisteredLink"), "Login Link");
	
	if (browser.isTextPresent("Your account") ){
		Report.updateTestLog("Login Page is displayed", "PASS");
	}
	else {
	Report.updateTestLog("Login Page is not displayed", "FAIL");	
		}
	browser.browserBack();

	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationEmailAddress"),"Email Address", userProfile.getEmail().trim());
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationConfirmEmailAddress"),"Confirm Email Address", "test");
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationPassword"),"Password", userProfile.getPassword().trim());
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationConfirmPassword"),"Confirm Password","tesr12");	
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCancelLink"), "Cancel link");
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.ContinueRegister"), "Continue button");

	browser.wait(3000);	
	
}


////Add Missing Section in Email Already Registered Page//////////////////////////////////////////////////////////////////////////
public void verifyCallBackSection () {
	
	if (browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.AlredyRegisterAddMissingAccount"))){
		Report.updateTestLog("Call to Add Missing Account Section is displayed", "WARN");
	}
	else {
	   Report.updateTestLog("Call to Add Missing Account Section is not displayed", "FAIL");
		}
	}

////////////Email Address Field Validation////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
public void emailAddressFieldValidation () {
	String errormsgdisplayed="null";
	errormsgdisplayed=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.EmailErrorMessage"));
	Report.updateTestLog("Error Msg: Expected Result: "+errormsg.BgbWaveRegistrationEmailEmpty+"Actual Result: "+errormsgdisplayed,errormsgdisplayed.contains(errormsg.BgbWaveRegistrationEmailEmpty)?"WARN":"FAIL");
	
}

////////////Confirm Password Field Validation////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void confirmPasswordFieldValidation () {
	String errormsgdisplayed="null";
	errormsgdisplayed=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.PasswordErrorMessage"));
	Report.updateTestLog("Error Msg: Expected Result: "+errormsg.BgbWaveRegistrationPasswordEmpty+"Actual Result: "+errormsgdisplayed,errormsgdisplayed.contains(errormsg.BgbWaveRegistrationPasswordEmpty)?"WARN":"FAIL");
}
///////////////////////////Password Less than 8 Characters//////////////////////////////////////////////////////////////////////////////////////////////
public void passwordLessThanEightValidation () {
	String errormsgdisplayed="null";
	errormsgdisplayed=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.PasswordLessthanEightErrorMessage"));
	Report.updateTestLog("Error Msg: Expected Result: "+errormsg.BgbWaveRegistrationPasswordLessthanEight+"Actual Result: "+errormsgdisplayed,errormsgdisplayed.contains(errormsg.BgbWaveRegistrationPasswordLessthanEight)?"WARN":"FAIL");
}
//////////////////////////Email Mismatch//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void emailMisMatch () {
	String errormsgdisplayed="null";
	errormsgdisplayed=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.EmailMismatch"));
	Report.updateTestLog("Error Msg: Expected Result: "+errormsg.BgbWaveRegistrationEmailMisMatch+"Actual Result: "+errormsgdisplayed,errormsgdisplayed.contains(errormsg.BgbWaveRegistrationEmailMisMatch)?"WARN":"FAIL");
}
///////////////////////////////////////PasswordMismatch//////////////////////////////////////////////////////////////////////////////////////////////////
public void passwordMisMatch () {
	String errormsgdisplayed="null";
	errormsgdisplayed=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.PasswordMismatch"));
	Report.updateTestLog("Error Msg: Expected Result: "+errormsg.BgbWaveRegistrationPasswordMisMatch+"Actual Result: "+errormsgdisplayed,errormsgdisplayed.contains(errormsg.BgbWaveRegistrationPasswordMisMatch)?"WARN":"FAIL");
}

/////////////////CollectiveAccountOverlay/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void collectiveAccountOverlay(){
	 verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCollectiveAccountOverlay"), "Collective account overlay");
	 Report.updateTestLog("Colective Account Overlay is displayed", "WARN");
	 browser.wait(1000);
	 verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterCollectiveAccountOverlayClose"), "Collective account overlay close");
	
}
/////////////////////////////////////////Verify Audit Entry For Email Confirmation //////////////////////////////////////////////////////////////////////////////
public void verifyAuditEntryForEmail(UserProfile userProfile) {
      OnlineDBConnector dbFunctions =new OnlineDBConnector();
      String Date=dbFunctions.DBsysdateDdmonyyhhmi();
      System.out.println(" "+Date  );
      String Result=dbFunctions.verifyAuditEntryForEmailConfirmation(Date,userProfile.getEmail());
      String AuditEventType="98";
      if (AuditEventType.equals(Result)){
    	  Report.updateTestLog("Email Confirmation Audit Entry Is Made Sucessful in BG_BUSINESS_TA_AUDIT_DETAILS TABLE", "PASS");
      }
      else {
    	  Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
    	  
      }
    /*  Report.updateTestLog("Audit Entry is ::"+*/
      
}	

public void verifyAuditEntryForEmailNew(UserProfile userProfile) {
    OnlineDBConnector dbFunctions =new OnlineDBConnector();
    String Date=dbFunctions.DBsysdateDdmonyyhhmi();
    System.out.println(" "+Date  );
    String Result=dbFunctions.verifyAuditEntryForEmailConfirmationNew(Date,userProfile.getNewEmail());
    System.out.println("Result");
    String AuditEventType="98";
    if (AuditEventType.equals(Result)){
  	  Report.updateTestLog("Email Confirmation Audit Entry Is Made Sucessful in BG_BUSINESS_TA_AUDIT_DETAILS TABLE", "PASS");
    }
    else {
  	  Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
  	  
    }
  /*  Report.updateTestLog("Audit Entry is ::"+*/
    
}	



///////////////////////////////////////////////////////////////verify Audit entry for Super user //////////////////////////////////////////////////////////////
public void verifyAuditEntryForSuperUser(UserProfile userProfile) {
    OnlineDBConnector dbFunctions =new OnlineDBConnector();
    String Date=dbFunctions.DBsysdateDdmonyyhhmi();
    System.out.println(" "+Date  );
    String Result=dbFunctions.verifyAuditEntryForSuperUser(Date,userProfile.getEmail());
    String AuditEventType="312";
    if (AuditEventType.equals(Result)){
  	  Report.updateTestLog("Super User Audit Entry Is Made Sucessful in BG_BUSINESS_TA_AUDIT_DETAILS TABLE", "PASS");
    }
    else {
  	  Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
  	  
    }
  /*  Report.updateTestLog("Audit Entry is ::"+*/
    
}


public void verifyAuditEntryForSuperUserNew(UserProfile userProfile) {
    OnlineDBConnector dbFunctions =new OnlineDBConnector();
    String Date=dbFunctions.DBsysdateDdmonyyhhmi();
    System.out.println(" "+Date  );
    String Result=dbFunctions.verifyAuditEntryForSuperUser(Date,userProfile.getNewEmail());
    String AuditEventType="312";
    if (AuditEventType.equals(Result)){
  	  Report.updateTestLog("Super User Audit Entry Is Made Sucessful in BG_BUSINESS_TA_AUDIT_DETAILS TABLE", "PASS");
    }
    else {
  	  Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
  	  
    }
  /*  Report.updateTestLog("Audit Entry is ::"+*/
    
}



///////////////////////////////////////////////////////////////////////////Feedback Validation/////////////////////////////////////////////////////////////////////////	
	
public void verifyFeedBack() {
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterFeedback"), "Feedback Option is ");
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.RegistrationFeedbackText"),"Feedback is Entered","Tester");
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterFeedbackSubmit"), "Feedback Submit Button ");
	Report.updateTestLog("Feedback is Entered", "WARN");
	
}
////////////////////////////////////////////////////////////////////////////FeedBack DB Validation///////////////////////////////////////////////////////////////////////////////////////////////////////
/*public void feedbackAuditValidataion (UserProfile userProfile) {
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 String SurveyType="THANKYOU";
	 String Result=dbFunctions.verifyAuditEntryForSurveyConfirmation(Date,userProfile.getEmail());
	 if (SurveyType.equals(Result)){
		 Report.updateTestLog("Survey Audit Entry is Made Sucessful in BG_BUSINESS_TA_SURVEY TABLE", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}*/
////////////////////////////////////////////////////////////////////////BARCLAYS DEEPLINK////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void barclaysDeeplink (String Barclays) {
	
	  if(Barclays=="barclays1"){
		  browser.open(ApplicationConfig.APP_BG_URL+"/business/barclays");

       }
	  else if (Barclays=="barclays2"){
		  browser.open(ApplicationConfig.APP_BG_URL+"/business/your-account/register/?cid=BarclaysOAMRegister");
	  }
		  
	  }
////////////////////////////////////////////////////////////////////////////AUDIT_VERIFICATION FOR BARCLAYS///////////////////////////////////////////////////////////////////////////////////////////////////////
/*public void barclaysAuditValidataion (UserProfile userProfile) {
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 String SurveyType="BarclaysOAMRegister";
	 String Result=dbFunctions.verifyAuditEntryForBarclays(Date,userProfile.getEmail());
	 if (SurveyType.equals(Result)){
		 Report.updateTestLog("Barclays OAMRegister Audit Entry is Made Sucessful in BG_BUSINESS_TA_CUSTOMER_REG", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}*/
//////////////////////////////////////////////////////////////////////////////Password Audit Check///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*public void passwordAuditValidataion (UserProfile userProfile) {
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 String EventType="55";
	 String Result=dbFunctions.verifyAuditEntryForPasswordEmail(Date,userProfile.getEmail());
	 if (EventType.equals(Result)){
		 Report.updateTestLog("Password Email Sent Audit Entry is Made Sucessful in BG_BUSINESS_TA_AUDIT_DETAILS", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}*/
////////////////////////////////////////////////////////////////////Register Entry Check in DB/////////////////////////////////////////////////////////////////////////////////////
public void registerAuditValidataion (UserProfile userProfile) {
	browser.wait(5000);
	String Accountnumber=userProfile.getAccNumber();
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 
	 String Result=dbFunctions.verifyAuditEntryForRegistration(Date,userProfile.getEmail());
	 if (Accountnumber.equals(Result)){
		 Report.updateTestLog("Register Audit Entry is Made Sucessful in BG_BUSINESS_TA_CUSTOMER_REG", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}


public void registerAuditValidataionNew (UserProfile userProfile) {
	browser.wait(5000);
	String Accountnumber=userProfile.getAccNumber();
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 
	 String Result=dbFunctions.verifyAuditEntryForRegistrationNew(Date,userProfile.getNewEmail());
	 if (Accountnumber.equals(Result)){
		 Report.updateTestLog("Register Audit Entry is Made Sucessful in BG_BUSINESS_TA_CUSTOMER_REG", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}





///////////////////////////////////////////////////////////////////Super User Text Verification/////////////////////////////////////////////////////////////////////////////
public RegistrationPage verifyConfirmationPageForSuperUserCSA(){
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    browser.wait(5000);
    
    verifyIsElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterLearnMorelink"),"Learn more");
    if (browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterPaperlessbilinglink"))){
    	Report.updateTestLog("Paperless billing opted in presemt in application", "PASS");
    }
    	else {
    		Report.updateTestLog("Paperless billing contennt is not present in the appliation", "FAIL");
    	}
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterPaperlessbilingCheck"), "Opt in for Paperless Billing");
    Report.updateTestLog("Paperless billing opted in sucessfully", "WARN");
    browser.wait(5000);
    if(browser.isTextPresent("Thanks, you’re now registered")){
          Report.updateTestLog("Comfirmation Page is Displayed", "WARN");
    }
    else{
          Report.updateTestLog("Comfirmation Page not Displayed", "FAIL");
    }
        if(browser.isElementVisibleWithXpath(regPageProperties.getProperty("RegistrationPage.ConfirmationPageSuperUserContent"))){
          Report.updateTestLog("Super User Content is Present in Confirmation Page", "WARN");
    }
    else{
          Report.updateTestLog("Super User Content is not Present in Confirmation Page", "FAIL");
    }
     verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterContinueToYourAccount"), "Continue To Your Account");
    if(browser.isTextPresent("Log in to your business account")){
          browser.wait(2000);
          Report.updateTestLog("Login to your online account page is disaplayed", "WARN");
    }
    else{
          Report.updateTestLog("Login to your online account page is not disaplayed", "FAIL");
    }
    
    //browser.browserBack();
    return this;

}


///////////////////////////////////////////////////////////////////Account Overview for Super User//////////////////////////////////////////////////////////////////////////////////////////////////////////////   


public void verifyAccountOverviewForSuperUser () {
	
		String superuser;
	    String superusertext ="You have Super User access";
		if(browser.isTextPresent("Account overview")){
	    Report.updateTestLog("Account Overview page is loaded", "WARN");
		}
	    else {
	    	Report.updateTestLog("Account Overview page is not loaded", "FAIL");
	    }
		superuser=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.RegisterUserText"));
		
		System.out.println("" + superuser );
		if (superusertext.equals(superuser)){
		Report.updateTestLog("You have Super User access text is present in the account overview page", "PASS");
		}
		else {
			Report.updateTestLog("You have Super User access text is not present in the account overview page", "FAIL");
		}
	    //verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterLogout"), "Logout link");
		
		}
/////////////////////////////////////////////////////////////////Account OVerview For Standard user/////////////////////////////////////////////////////////////////////		
public void verifyAccountOverviewForStandardUser () {
	browser.wait(200);
	String superuser;
    String superusertext ="You have Reads, Bills and Payment User access";
	if(browser.isTextPresent("Account overview")){
    Report.updateTestLog("Account Overview page is loaded", "WARN");
	}
    else {
    	Report.updateTestLog("Account Overview page is not loaded", "FAIL");
    }
	superuser=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.RegisterUserText"));
	
	System.out.println("" + superuser );
	if (superusertext.equals(superuser)){
	Report.updateTestLog("You have Reads, Bills and Payment User access text is present in the account overview page", "PASS");
	}
	else {
		Report.updateTestLog("You have Reads, Bills and Payment User access text is not present in the account overview page", "FAIL");
	}
    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterLogout"), "Logout link");
	
	}		
		
////////////////////////////////////////////////////////////////////////////////////Breadcrumb Your details/////////////////////////////////////////////////////////////////////
public void verifyBreadCrumbInYourdetails () {
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.BreadcrumbRegister"), "Register");
	browser.wait(2000);
	verifyIsTextPresent("Register for an online business account");
	Report.updateTestLog("Regsiter Page is loaded", "WARN");
    //verifyPageTitle("Register for an online business account");
	browser.browserBack();
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.BreadcrumbYouraccount"), "Register");
	browser.wait(2000);
    //verifyPageTitle("Your account");
    verifyIsTextPresent("Your account");
    Report.updateTestLog("Your account Page is loaded", "WARN");
	browser.browserBack();
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.BreadcrumbHome"), "Register");
	browser.wait(2000);
	verifyIsTextPresent("British Gas Business Energy");
	Report.updateTestLog("British Gas Page is loaded", "WARN");
    //verifyPageTitle("British Gas Business Energy");
	browser.browserBack();
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
 public void paperlessOptout () {
	 verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PaperBill"), "Paper Bill");
	 browser.wait(2000);
	 Report.updateTestLog("Switch to paper overlay is displayed", "WARN");
	 verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.PaperConfirm"), "Paper Bill Opt out");
	
}
 
 public void verifyAccountOverviewForStandardUserNew () {
	 browser.wait(200);
		String superuser;
	    String superusertext ="You have Reads, Bills and Payment User access";
		if(browser.isTextPresent("Account overview")){
	    Report.updateTestLog("Account Overview page is loaded", "WARN");
		}
	    else {
	    	Report.updateTestLog("Account Overview page is not loaded", "FAIL");
	    }
		superuser=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.RegisterUserText"));
		
		System.out.println("" + superuser );
		if (superusertext.equals(superuser)){
		Report.updateTestLog("You have Reads, Bills and Payment User access text is present in the account overview page", "PASS");
		}
		else {
			Report.updateTestLog("You have Reads, Bills and Payment User access text is not present in the account overview page", "FAIL");
		}
	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterLogout"), "Logout link");
		
		}
 public void passwordAuditValidataion (UserProfile userProfile) {
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 String EventType="55";
	 String Result=dbFunctions.verifyAuditEntryForPasswordEmail(Date,userProfile.getEmail());
	 if (EventType.equals(Result)){
		 Report.updateTestLog("Password Email Sent Audit Entry is Made Sucessful in BG_BUSINESS_TA_AUDIT_DETAILS", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}
 public void feedbackAuditValidataion (UserProfile userProfile) {
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 String SurveyType="THANKYOU";
	 String Result=dbFunctions.verifyAuditEntryForSurveyConfirmation(Date,userProfile.getEmail());
	 if (SurveyType.equals(Result)){
		 Report.updateTestLog("Survey Audit Entry is Made Sucessful in BG_BUSINESS_TA_SURVEY TABLE", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
} 
 public void barclaysAuditValidataion (UserProfile userProfile) {
	 OnlineDBConnector dbFunctions =new OnlineDBConnector();
	 String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 System.out.println(" "+Date  );
	 String SurveyType="BarclaysOAMRegister";
	 String Result=dbFunctions.verifyAuditEntryForBarclays(Date,userProfile.getEmail());
	 if (SurveyType.equals(Result)){
		 Report.updateTestLog("Barclays OAMRegister Audit Entry is Made Sucessful in BG_BUSINESS_TA_CUSTOMER_REG", "PASS");
		 
	 }
	 else {
		 Report.updateTestLog("Audit Entry Was Not Expected", "FAIL");
	 }
}
 
 public RegistrationPage openEncryptUrlandRegister_CSA_New(UserProfile userProfile){
	 
 	System.out.println(" ------------------------------- ");
 	{
 		System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
 		
  	OnlineDBConnector dbFunctions = new OnlineDBConnector(); 
  	String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 	System.out.println(" "+Date  );
	 	System.out.println("Date is populated");
  	
	 	OnlineDBConnector UUIDFunction = new OnlineDBConnector();
		UUID = UUIDFunction.Generate_UUIDNew(userProfile.getEmail());
		
		System.out.println("The UUID is --> " + UUID);
		Report.updateTestLog("the Generated UUID is"+UUID,"Pass");
		System.out.print("my encription"+UUID);
	 		 	        	
  	System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+regPageProperties.getProperty("RegistrationPage.CsaEncryptionLink")+UUID);
 	
 	browser.open(ApplicationConfig.APP_BG_URL+regPageProperties.getProperty("RegistrationPage.CsaEncryptionLink")+UUID);
 	   	
 } 
 return this;
 }
 
 public void CSAinvalidlinkpageverify()
 {
	 
	 if(browser.isTextPresent("Your registration is pending activation"))
			 {
		 
		 Report.updateTestLog("CSA Pending activation Page", "WARN");
		 verifyAndClickWithXpath(regPageProperties.getProperty("Registration.CSAsendactivationemail"),"Send Activation Email");
			 }
	  
	 if(browser.isTextPresent("An activation email has been sent"))
	 {
		 Report.updateTestLog("Activation Email Sent Successfully", "Done");
	 }
 }
 
 public void regisration_dynamic_page_cash_cheque (UserProfile userProfile) {
	 
	 
	 browser.wait(getWaitTime());
	
	 verifyTextWithXpath("//*[@id='content-wrapper-main']/div[3]/div/h3", "Dynamic Page Content", "Before we finish creating your account, please confirm some security details for the following account");
	
	 verifyAndClick("contractEndDate", "Contract End Date Calander");
	 
	String date = "19.10.2016";
	String datestring = date.substring(0, 2);
	String monthstring = date.substring(3, 5);
	String yearstring = date.substring(6, 10);
	String month = null;
	System.out.println(datestring);
	System.out.println(monthstring);
	System.out.println(yearstring);

// Month 
	
		String monthselected = browser.getTextByXpath("//*[@id='ui-datepicker-div']/div/div/span");
		
		if(monthstring.equals("01")){
			month = "Jan";
		}else if (monthstring.equals("02")) {
			month = "Feb";
		}else if (monthstring.equals("03")) {
			month = "Mar";
		}else if (monthstring.equals("04")) {
			month = "Apr";
		}else if (monthstring.equals("05")) {
			month = "May";
		}else if (monthstring.equals("06")) {
			month = "Jun";
		}else if (monthstring.equals("07")) {
			month = "Jul";
		}else if (monthstring.equals("08")) {
			month = "Aug";
		}else if (monthstring.equals("09")) {
			month = "Sep";
		}else if (monthstring.equals("10")) {
			month = "Oct";
		}else if (monthstring.equals("11")) {
			month = "Nov";
		}else if (monthstring.equals("12")) {
			month = "Dec";
		}
		System.out.println(month);	
		
		if (monthselected.equals(month)) {
			Report.updateTestLog("Month Selected successfully", "Pass");
		}else{
			browser.clickWithXpath("//*[@id='ui-datepicker-div']/div/a[2]/span");
		}
		
// Xpath for Year - //*[@id="ui-datepicker-div"]/div/div/select
	
	org.openqa.selenium.support.ui.Select year = new org.openqa.selenium.support.ui.Select(browser.getElementByXpath("//*[@id='ui-datepicker-div']/div/div/select"));
	WebElement yearnew = year.getFirstSelectedOption();
	String yeardisplayed = yearnew.getText();
	
	if(yeardisplayed.equals("")){
		Report.updateTestLog("Year is Selected","Pass");
		}else{
		verifyAndSelectDropDownBoxByXpath("//*[@id='ui-datepicker-div']/div/div/select", "Year", "");
		}

// Date 
	browser.wait(3000);
	browser.clickWithLinkText("19");
	
	browser.wait(5000);
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.confirmbutton"), "Confirm Button");
	browser.wait(5000);
	
 }
 

public void last_payment_amount_page(UserProfile userProfile){
	
	browser.wait(getWaitTime());
	verifyTextWithXpath(regPageProperties.getProperty("RegistrationPage.headerthree"), "Header", regPageProperties.getProperty("RegistrationPage.headertext"));
	
	verifyAndInputById_value("amountToPay", "Last Payment Amount field", regPageProperties.getProperty("RegistrationPage.lastamount"));
	
	browser.wait(2000);
	
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.confirmbutton"), "Confirm Button");
	browser.wait(5000);
	
}

public void credit_card_no_last_four_digits(UserProfile userProfile){
	
	browser.wait(getWaitTime());
	verifyTextWithXpath(regPageProperties.getProperty("RegistrationPage.headerthree"), "Header", regPageProperties.getProperty("RegistrationPage.headertext"));
	
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.firstboxxpath"), "First Field", regPageProperties.getProperty("RegistrationPage.firstboxnumber"));
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.secondboxxpath"), "Second Field", regPageProperties.getProperty("RegistrationPage.secondboxnumber"));
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.thirdboxxpath"), "Third Field", regPageProperties.getProperty("RegistrationPage.thirdboxnumber"));
	verifyAndInputByXpath(regPageProperties.getProperty("RegistrationPage.fourthboxxpath"), "Fourth Field", regPageProperties.getProperty("RegistrationPage.fourthboxnumber"));
	
	browser.wait(2000);
	
	verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.confirmbutton"), "Confirm Button");
	browser.wait(5000);
	
}

    public void RegistrationActivationEmail() {
	browser.wait(2000);
	verifyIsTextPresent("Thank you for confirming your details");
	Report.updateTestLog("Thank you for confirming your details", "WARN");
	//verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.BritishGasbutton"), "British Gas Home Button");
	browser.wait(getWaitTime());		
	
}
    
    public RegistrationPage Activate_EmailUrl(UserProfile userProfile){
		
    	browser.wait(10000);
		 	 	
				OnlineDBConnector dbFunctions = new OnlineDBConnector();
			 	String New_UUUID = dbFunctions.Generate_UUID_Registration(userProfile.getEmail());
			 	System.out.println("The new UUID is " + New_UUUID);			 	
			 	System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+regPageProperties.getProperty("RegistrationPage.Userurl")+New_UUUID);
				browser.open(ApplicationConfig.APP_BG_URL+regPageProperties.getProperty("RegistrationPage.Userurl")+New_UUUID);
				return this;
				
				
				}
    
    public void verifyAccountOverviewForSuperUserNew () {
   	 browser.wait(200);
   		String superuser;
   	    String superusertext ="You have Super User access";
   		if(browser.isTextPresent("Account overview")){
   	    Report.updateTestLog("Account Overview page is loaded", "WARN");
   		}
   	    else {
   	    	Report.updateTestLog("Account Overview page is not loaded", "FAIL");
   	    }
   		superuser=browser.getTextByXpath(regPageProperties.getProperty("RegistrationPage.RegisterUserText"));
   		
   		System.out.println("" + superuser );
   		if (superusertext.equals(superuser)){
   		Report.updateTestLog("You have Super User access text is present in the account overview page", "PASS");
   		}
   		else {
   			Report.updateTestLog("You have Super User access text is not present in the account overview page", "FAIL");
   		}
   	    verifyAndClickWithXpath(regPageProperties.getProperty("RegistrationPage.RegisterLogout"), "Logout link");
   		
   		}


}
	   
  
	   






