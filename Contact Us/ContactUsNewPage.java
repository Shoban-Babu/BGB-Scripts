package bg.framework.app.functional.page.Slingshot;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;



public class ContactUsNewPage extends BasePage {

	public static String stremail;
	private final static String FILE_NAME= "resources/Slingshot/ContactUsNew.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	public static int microBusiness = 2;

	public void navigateToContactUsPage (){

		String url="/business/help-and-advice-business/contactus";
		System.out.println("URL=" +ApplicationConfig.APP_BG_URL+url);
		browser.open(ApplicationConfig.APP_BG_URL+url);
		//System.out.println("URL=" +ApplicationConfig.APP_BG_URL+url);
		//verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.ContactUsnavigate"),"Contact Us Link");
		browser.wait(2000);
		Report.updateTestLog("ContactUs Page is Loaded", "WARN");
	}
	public void navigateToContactUsPageLogin (){

		verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.ContactUsLink"),"Contact Us Link");
	}

	public void toggleMicroBusiness(){
		if(microBusiness == 1){
			microBusiness = 2;
		}else
			microBusiness = 1;	 
	}

	public void submitQuerry(UserProfile userProfile, String strType) {

		List<String> enquiry = browser.getFromDropBox("id",pageProperties.getProperty("ContactUsPage.contactCategoryID"));
		System.out.println("ftr"+enquiry);
		int Iteration =1;

		System.out.println("Enquiry" + enquiry.size());
		for (int x=1; x <enquiry.size()  ;x++){
			browser.wait(getWaitTime());
			System.out.println("srty"+enquiry.size());
			System.out.println("Eqnuiry "+x+enquiry.get(x));
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ContactUsPage.contactCategoryXpath"))){
				browser.selectfromDropBox("id", pageProperties.getProperty("ContactUsPage.contactCategoryID"),enquiry.get(x)); 
			}
			String cat =enquiry.get(x);

			Boolean a = browser.isElementVisibleWithXpath(pageProperties.getProperty("ContactUsPage.contactReasonXpath")+x+"']");
			System.out.println("xpath"+(pageProperties.getProperty("ContactUsPage.contactReasonXpath")+x+"']") );
			System.out.println("th"+a);
			if (a) {

				System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJ" + pageProperties.getProperty("ContactUsPage.contactReasonID")+x);
				List<String> category = browser.getFromDropBox("id", pageProperties.getProperty("ContactUsPage.contactReasonID")+x);
				System.out.println("sety"+category);
				for (int y = 1; y < category.size(); y++) {
					System.out.println("free"+category.size());
					browser.wait(getWaitTime());
					browser.selectfromDropBox("id", pageProperties.getProperty("ContactUsPage.contactCategoryID"), enquiry.get(x));
					/* WebElement element = browser.getElementByXpath(pageProperties.getProperty("ContactUsPage.contactReasonXpath"));
	                	element.click();
	                	element = browser.getElementByXpath(pageProperties.getProperty("ContactUsPage.contactReasonXpathOptions").replace("CATEGORYCOUNT", (x+1)+""));
	                	element.click();*/
					//browser.wait(getWaitTime());
					
					browser.wait(3000);
					browser.selectfromDropBox("id", pageProperties.getProperty("ContactUsPage.contactReasonID")+x, category.get(y));
					Report.updateTestLog("*********** Iteration  : '" + Iteration + "' Started ***********", "Done");
					if(cat.contains("Business Energy Complaints")){
						if(microBusiness == 1){
						verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.microBusinessYes"), "Micro Business Yes Radio Button");
						Report.updateTestLog("*********** Micro Businness  : Yes Radio Button ***********", "Done");
						}
						else{
							verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.microBusinessNo"), "Micro Business No Radio Button");
							Report.updateTestLog("*********** Micro Businness  : No Radio Button ***********", "Done");
						}
						toggleMicroBusiness();
					}
					Report.updateTestLog("Category  : " + cat, "Done");
					String reas = category.get(y);
					Report.updateTestLog("Reason    : " + reas, "Done");
					
					if(strType=="Anonymous")
						anonymousSubmitForm(userProfile);
					else{
						LoginSubmitForm(userProfile);	
					}
					//browser.wait(getWaitTime());
			
					System.out.println("im out ");
					
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ContactUsPage.Confirmation"))){

						// Reference Number 
						try {
							String refno = browser.getElementByXpath("//*[@id='content-wrapper-main']/div[2]/div[3]/div/p").getText();
							Report.updateTestLog("Reference Number is sucessfully updated - " + refno, "PASS");
						}catch (Exception e) {
							Report.updateTestLog("Reference Number is NOT present (EMAIL) ", "WARN");
						}
						
						verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.Confirmation"), "Feddback Survey Is Selected");
						Report.updateTestLog("Confirmation Page is Loaded", "WARN");
						System.out.println("im in ");
						verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.FeedbackConfirmation"), "Feddback Submit");
						browser.wait(1000);
						Report.updateTestLog("Feedback is Submitted sucessfully Page", "PASS");

					}
					else {
						Report.updateTestLog("Confirmation Page is not Loaded", "FAIL");
						
					}
				
					browser.wait(5000);
					browser.browserBack();
					//browser.wait(10000);
					Iteration=Iteration+1;	
					browser.wait(5000);
				}
			}else {
				Report.updateTestLog("*********** Iteration  : '" + Iteration + "' Started ***********", "");
				Report.updateTestLog("Category  : " + cat, "Done");
				Iteration=Iteration+1;	 
			}
			browser.wait(getWaitTime()); 
		}	 
	}
	/*************************************************************************************************Anonymous Form***********************************************/

	public void anonymousSubmitForm(UserProfile userProfile) {
		System.out.println("Im in Form");
		browser.clearValue(pageProperties.getProperty("ContactUsPage.contactReasonQuerry"));
		String test ="just testing" ;

		browser.input(pageProperties.getProperty("ContactUsPage.contactReasonQuerry"), test);
		int count = test.length();
		Report.updateTestLog("Tell Us More    ::"+count+" Characters entered", "PASS");
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("ContactUsPage.title"))) {
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("ContactUsPage.title"));
			
		Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("ContactUsPage.title")));
		select.selectByIndex(1);
			
		element.click();
     	browser.wait(2000);
     	element = browser.getElementByXpath(pageProperties.getProperty("ContactUsPage.titleOption"));
     	element.click();
			browser.wait(2000);
			Report.updateTestLog(userProfile.getTitle() + " Title Selected", "DONE");
		} else {
			Report.updateTestLog("Title Not Selected", "FAIL");
		}
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.firstName"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.firstName"));
			browser.input(pageProperties.getProperty("ContactUsPage.firstName"), userProfile.getFirstName());
			Report.updateTestLog(userProfile.getFirstName() + " First Name Entered", "DONE");
		} else {
			Report.updateTestLog("First Name Not Entered", "FAIL");
		}
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.lastName"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.lastName"));
			browser.input(pageProperties.getProperty("ContactUsPage.lastName"), userProfile.getLastName());
			Report.updateTestLog(userProfile.getLastName() + " Last Name Entered", "DONE");
		} else {
			Report.updateTestLog("Last Name Not Entered", "FAIL");
		}
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.busienessName"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.busienessName"));
			browser.input(pageProperties.getProperty("ContactUsPage.busienessName"), userProfile.getbusinessname());
			Report.updateTestLog(userProfile.getbusinessname() + " Business Name Entered", "DONE");
		} else {
			Report.updateTestLog("Business Name Not Entered", "FAIL");
		}
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.AccountNumber"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.AccountNumber"));
			browser.input(pageProperties.getProperty("ContactUsPage.AccountNumber"), userProfile.getAccNumber());
			Report.updateTestLog(userProfile.getAccNumber() + " Account Number Entered", "DONE");
		} else {
			Report.updateTestLog("Account Number Not Entered", "FAIL");
		}
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.postcode"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.postcode"));
			verifyAndInputById(pageProperties.getProperty("ContactUsPage.postcode"),"Postcode", userProfile.getPostCode());
			verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.SearchAddress"),"Find Address");
			browser.WaitForElementWithId(pageProperties.getProperty("ContactUsPage.Addresslist"));
			verifyAndSelectDropDownBoxbyindex_id(pageProperties.getProperty("ContactUsPage.Addresslist"),2);
			verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.AddressconfrimButton"),"Confirm Button");

		} else {
			Report.updateTestLog("PostCode Not Entered", "FAIL");
		}
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.EnterEmailAddress"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.EnterEmailAddress"));
			Random rand =new Random ();
			stremail="automation_digitaltest"+ rand.nextInt(1000)+"@bgdigitaltest.co.uk";

			browser.input(pageProperties.getProperty("ContactUsPage.EnterEmailAddress"),"arunbaskar.g@cognizant.com");
			Report.updateTestLog("arunbaskar.g@cognizant.com - Email Address Entered sucessfully", "DONE");
			//Check ---------------------- Email
		
		} else {
			Report.updateTestLog("Email Address Not Entered", "FAIL");
		}
		
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.EnterTelephoneNumber"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.EnterTelephoneNumber"));
			browser.input(pageProperties.getProperty("ContactUsPage.EnterTelephoneNumber"),userProfile.getPhoneNumber()  );
			Report.updateTestLog(userProfile.getPhoneNumber() + " Phone Number Entered Sucessfully", "DONE");
			//verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.CheckboxCheckin"),"Check Box");

		} else {
			Report.updateTestLog("Telephone number Not Entered", "FAIL");
		}
		
		browser.switchToFrame("//*[@id='html_element']/div/div/iframe");
		
			try {
				browser.wait(2000);
				boolean a = true;
				System.out.println(browser.isElementVisibleWithXpath("//*[@id='recaptcha-anchor-label']"));
				if(a==browser.isElementVisibleWithXpath("//*[@id='recaptcha-anchor-label']")){
					browser.clickWithXpath("//*[@id='recaptcha-anchor']/div[5]");
					Report.updateTestLog("CAPTCHA Checkbox clicked successfully","PASS");	
				}
				
			} catch (Exception e) {
				Report.updateTestLog("CAPTCHA option is NOT displayed","FAIL");
			}
			browser.wait(2000);
			
			browser.swtichToDefaultContent();
			
			browser.switchToFrame("/html/body/div[5]/div[4]/iframe");
			
			try {
				browser.WaitForElementWithId("rc-imageselect");
				Report.updateTestLog("CAPTCHA IMAGE is displayed successfully","PASS");
				browser.wait(2000);
			} catch (Exception e) {
				Report.updateTestLog("CAPTCHA IMAGE option is NOT displayed","FAIL");
			}
			
	
			

		browser.swtichToDefaultContent();
		
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e1) {
			Report.updateTestLog("ROBOT option issue","FAIL");	
		}
		
		try {
			verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.SubmitQuerry"),"Submit Querry");
		} catch (Exception e) {
			Report.updateTestLog("Submit Option is disabled","PASS");
		}

		browser.wait(3000);
		
		String tittle = browser.getTitle();
		if(tittle.equals("Contact us - British Gas Business")){
			Report.updateTestLog("Submit option is disabled","PASS");
		} else
		{
			Report.updateTestLog("Submit option is enabled and it is clicked and navigated to next page successfully","FAIL");
		}
	}
	/*************************************************************************************************Login Form***********************************************/

	public void LoginSubmitForm(UserProfile userProfile) {
		System.out.println("Im in Form");
		browser.clearValue(pageProperties.getProperty("ContactUsPage.contactReasonQuerry"));
		String test ="just testing" ;

		browser.input(pageProperties.getProperty("ContactUsPage.contactReasonQuerry"), test);
		int count = test.length();
		Report.updateTestLog("Tell Us More  ::"+count+" Characters entered", "PASS");

		/*if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.AccountNumber")))
		{
			browser.clearValue(pageProperties.getProperty("ContactUsPage.AccountNumber"));
			browser.input(pageProperties.getProperty("ContactUsPage.AccountNumber"), userProfile.getAccNumber());
			Report.updateTestLog(userProfile.getAccNumber() + " Account Number Entered", "DONE");
		} 
		else {
			Report.updateTestLog("Account Number Not Entered", "FAIL");}*/
		
		if (browser.isElementVisibleWithXpathenable(pageProperties.getProperty("ContactUsPage.AccountNumber")))
				{
			browser.clearValue(pageProperties.getProperty("ContactUsPage.AccountNumber"));
			browser.input(pageProperties.getProperty("ContactUsPage.AccountNumber"), userProfile.getAccNumber());
			Report.updateTestLog(userProfile.getAccNumber() + " Account Number Entered", "DONE");
				}
		else
		{
			Report.updateTestLog(userProfile.getAccNumber()+"Account Number Disabled", "Done");
		}
		
		if (browser.isElementVisible(pageProperties.getProperty("ContactUsPage.postcode"))) {
			browser.clearValue(pageProperties.getProperty("ContactUsPage.postcode"));
			verifyAndInputById(pageProperties.getProperty("ContactUsPage.postcode"),"Postcode", userProfile.getPostCode());
			verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.SearchAddress"),"Find Address");
			browser.WaitForElementWithId(pageProperties.getProperty("ContactUsPage.Addresslist"));
			verifyAndSelectDropDownBoxbyindex_id(pageProperties.getProperty("ContactUsPage.Addresslist"),2);
			verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.AddressconfrimButton"),"Confirm Button");
			
			browser.wait(3000);

		} else {
			Report.updateTestLog("PostCode Not Entered", "FAIL");
		}

		String telephoneNumber = null;
	
			/*browser.input("emailAddress",userProfile.getEmail());
			Report.updateTestLog(userProfile.getPhoneNumber() + " Email Entered", "DONE");*/

			/*telephoneNumber = browser.getAttribute(pageProperties.getProperty("ContactUsPage.EnterTelephoneNumber"), "Value");
			System.out.println("TelephoneNumber = "+telephoneNumber);*/
		
		/*try {
			System.out.println("----------111--------------------");
			browser.input(pageProperties.getProperty("ContactUsPage.EnterTelephoneNumber"),userProfile.getPhoneNumber());
			Report.updateTestLog(userProfile.getPhoneNumber() + " Phone Number Entered", "DONE");
		} catch (NoSuchElementException e) {
			Report.updateTestLog(userProfile.getPhoneNumber() + " Phone Number No Entered - Exception", "DONE");
		}*/
		
		try{
		if(telephoneNumber=="" || telephoneNumber==null){
			System.out.println("------------------------------");
			browser.input(pageProperties.getProperty("ContactUsPage.EnterTelephoneNumber"),userProfile.getPhoneNumber());
			Report.updateTestLog(userProfile.getPhoneNumber() + " Phone Number Entered", "DONE");
		}} catch (Exception e) {
			Report.updateTestLog("Email & Telephone Number is already entered", "DONE");
		}
		
		
		System.out.println("---------------2222---------------");
		// verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.CheckboxCheckin"),"Check Box");
		Report.updateTestLog("Submit Form has been filled sucessfully","PASS");
		//verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.SubmitQuerry"),"Submit Querry");
		verifyAndClickWithCSS(pageProperties.getProperty("ContactUsPage.SubmitQuerry"),"Submit Querry");

		//browser.browserBack();   
	}

		public void verifyLoginLandingPageFunctionality(UserProfile userProfile){
		//verifyAndPerformMouseOver("Your Account Mega Menu");
		verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.MegaMenuLogIn"),"Mega Menu Log in Button");
		verifyLoginLandingPage(userProfile);
		
	}
	
	public void verifyLoginLandingPage(UserProfile userProfile){
		verifyAndInputById(pageProperties.getProperty("ContactUsPage.Email"), "Email Id", userProfile.getEmail());
		verifyAndInputById(pageProperties.getProperty("ContactUsPage.Password"), "Password", userProfile.getPassword());
		verifyAndClickWithXpath(pageProperties.getProperty("ContactUsPage.NewLoginSubmitXpath"), "Submit button");
		browser.wait(5000);
		Report.updateTestLog("Account OverView Page", "WARN");	
	}

}