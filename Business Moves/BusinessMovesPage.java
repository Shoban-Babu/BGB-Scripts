package bg.framework.app.functional.page.Slingshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

import org.joda.time.DateTime;
import org.openqa.selenium.support.ui.Select;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

public class BusinessMovesPage extends BasePage{

	private final static String File_AccPage = "resources/Slingshot/BusinessMoves.properties";
	private static Properties pageProperties = new PropertyLoader(File_AccPage).load();
	 Calendar cal=Calendar.getInstance();
	public void clickBusinessMovesLink(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.BusinessMoveLink"), "Business moves link");
		}
	
	public void clickBusinessMovesLink_Anony(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.BusinessMoveLink"), "Business moves link");
		browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("BusinessMovesPage.AnonymousURL"));
	}
	public void enterMovingOutdate(String FromDate){
		
		int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);
		System.out.println("joda day is:"+day);
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FromDate"), "FromDate");
		/*if(FromDate.equalsIgnoreCase("3")){
           cal.add(Calendar.MONTH,+3);
		}else{
			
		}
		
		for(int i=1;i<4;i++){
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.PreviousDatePicker"), "PreviousDatePicker");
			browser.wait(500);
		}*/
		String result=calenderDate(day);
		if(result.equals("False")){
		   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NextDatePicker"), "NextDatePicker");	
		   calenderDate(day);
		 
		  /// verifyAndInputByXpath(
		   
		   
		}}
	
	public void enterPhoneNumber(UserProfile userProfile){
        browser.wait(3500);
        
        if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.telephoneNumber"))){
                          //String TelephoneNumber=browser.getTextByXpath(pageProperties.getProperty("BusinessMovePageMovingOut.TelephoneNumber"));
                          String TelephoneNumber=browser.getAttributeByXpath(pageProperties.getProperty("BusinessMovesPage.telephoneNumber"), "value");
                          
                          //browser.
                          
                          verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.telephoneNumber"), "Entering Telephone Number",userProfile.getPhoneNumber());
                         
                          
                          // System.out.println("  noooo "+ TelephoneNumber);
                          
                          System.out.println("wwwwwwwwwwwwwwwwwwwwww" + TelephoneNumber);
                          
                      /*   if(TelephoneNumber == null){
                        	 
                        	 System.out.println("wwwwwwwwwwwwwwwwwwwwww");
                        	  
                        	 
                        	 System.out.println("zzzzzzzzzzzz");
                              Report.updateTestLog("Telephone Number entered", "FAIL");
                                //verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ContinueButton"), "Continue");
                                
                          }
                          else{
                        	  System.out.println("   "+ TelephoneNumber);
                              Report.updateTestLog("Telephone Number Already Present", "PASS");
                                
                          }*/
                    }
       
  }

	
	
	public String calenderDate(String day){
		String result="False";
		int rowcount=browser.getRowCountByXpath(pageProperties.getProperty("BusinessMovesPage.CalenderTable"));
		int columncount=browser.getColCountByXpath(pageProperties.getProperty("BusinessMovesPage.CalenderTable"));
		System.out.println("rowcount:"+rowcount);
		System.out.println("columncount:"+columncount);
		for(int i=1;i<=rowcount;i++){
			for(int j=1;j<=columncount;j++){
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.CalenderTable")+"//tr["+i+"]/td["+j+"]"+
						"[contains(@class,'disabled')]")){
					System.out.println("empty xpath is");
				}else{
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.CalenderTable")+"//tr["+i+"]/td["+j+"]/a")){						
					String dateThreeMonths=browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.CalenderTable")+"//tr["+i+"]/td["+j+"]/a");
					if(dateThreeMonths.equals(day)){
						Report.updateTestLog("Day matched is:"+day,"pass");
						browser.clickWithXpath(pageProperties.getProperty("BusinessMovesPage.CalenderTable")+"//tr["+i+"]/td["+j+"]/a");
						result="True";
						break;
					}
				 }
				}
			}
			
		}
		return result;
	}
	
	public void leavingPropertyDetails(UserProfile userProfile){
		//verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.BuildingNumber"), "Building number", "8");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.PostCode"), "Postcode", userProfile.getPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FindAddress"), "FindAddress button");
		browser.wait(10000);
		//if(browser.isElementVisibleWithXpath("BusinessMovesPage.SelectAddress"));
	
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.SelectAddress"))){
			Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("BusinessMovesPage.SelectAddress")));
			select.selectByIndex(1);
			/*Report.updateTestLog("Different billing Address :" +select+ "Selected from Dropdown", "Pass");
		}else {
			Report.updateTestLog("Different billing Address is not Selected from Dropdown", "Fail");	
		}*/
			browser.wait(15000);
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ConfirmAddress"), "Confirm Address button");
		browser.wait(15000);
		browser.wait(15000);
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Submit"), "Continue button");
		browser.wait(15000);
		browser.wait(15000);
	}
	}	
	
  /* public void enterAddressManually(){
	   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddressLink"), "Enter manual address link");
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualBuildingNumber"), "Manual address entry for building number", "19");
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualBuildingName"), "Manual address entry for building name", "Upper");
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress1"), "Manual address entry for Address line1", "Dicconson");
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress2"), "Manual address entry for Address line2", "Third");
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress3"), "Manual address entry for Address line", "Street");
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualLocality"), "Manual address entry for Locality", "City");
	   
	     
	   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualPostCode"), "Manual address entry for Postcode", "CP12 4TR");
	   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Confirm"), "Confirm");
	   
	   
   }*/
	public void clickContinue(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ContinueButton"), "Continue");
		browser.wait(getWaitTime());
	}

	public void verifyBillingAddressPage(){
		verifyIsTextPresent(pageProperties.getProperty("BusinessMovesPage.YourAddressPageTitle"));
		String currentAddr = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.CurrentAddressValue"));
		Report.updateTestLog("Current address displayed as: "+currentAddr, "PASS");
		//verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.CurrentAddress"), "Current address");
		//verifyAndClickWithXpath(pageProperties.getProperty("DifferentAddress"), "Different Address");
	}
	public void selectCurrentAddress(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.CurrentAddress"), "Current address");
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ContinueButton"), "Continue button");
		browser.wait(3000);

	}
	public void enterDifferentAddress(UserProfile userProfile,String addr){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.DifferentAddress"), "Different address");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.BuildingNumber"), "Building name", "8");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.PostingCode"), "Posting code", userProfile.getPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FindAddressButton"), "Find Address Button");
	//	verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.Postcode"),"Postcode",userProfile.getPostCode().trim());
	//	verifyAndClick(pageProperties.getProperty("BusinessMovesPage.FindAddress"), "Find Address Button");
		browser.wait(getWaitTime());
		waitForObjectExistance(pageProperties.getProperty("BusinessMovesPage.EnterAddressManually"),"xpath");
		if(addr.equalsIgnoreCase("Manual")){				
			verifyAndClick(pageProperties.getProperty("BusinessMovesPage.EnterAddressManually"), "Enter Your Address Manually");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.FlatNumber"),"Flat Number","Unit 5");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.Address1"),"Address 1","Greenfield Farm Industrial Estate");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.PostalTown"),"Postal Town","CONGLETON");
			browser.selectfromDropBox("id",pageProperties.getProperty("BusinessMovesPage.County"), "Cheshire");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.PostCode"),"PostCode",userProfile.getPostCode());
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Confirm"), "Confirm");

		}
		else{
			ArrayList<String> contact = browser.getFromDropBox("id", pageProperties.getProperty("BusinessMovesPage.Displayaddr"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("BusinessMovesPage.Displayaddr"), "Preferred Contact ", contact.get(1));
		}
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ConfirmAddress"), "Confirm address");
	}
	public void enterDifferentAddress(UserProfile userProfile){		
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.DifferentAddress"), "Different address");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.BuildingNumber"), "Building name", "8");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.PostingCode"), "Posting code", userProfile.getPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FindAddressButton"), "Find Address Button");
		browser.wait(2000);
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EnterAddressManually"), "Enter address manually link");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualBuildingNumber"), "Manual address entry for building number", "19");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualBuildingName"), "Manual address entry for building name", "Upper");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress1"), "Manual address entry for Address line1", "Dicconson");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress2"), "Manual address entry for Address line2", "Third");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress3"), "Manual address entry for Address line3", "Street");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualLocality"), "Manual address entry for Locality", "City");
		   
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.SelectCountry"))){
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("BusinessMovesPage.SelectCountry")));
				select.selectByIndex(2);
				Report.updateTestLog("Country selection :" +select+ "Selected from Dropdown", "Pass");
			}else {
				Report.updateTestLog("Problem with country selection", "Fail");	
			}
		     
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualPostCode"), "Manual address entry for Postcode", "CP12 4TR");
		   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Confirm"), "Confirm");
		   browser.wait(15000);
		   browser.wait(getWaitTime());
		   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NewAddressconfirm"), "Continue button");
	}
		
	public void clickContinueButton(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ContinueButton"), "Continue button");

	}
	public void verifyErrorPage(){
		verifyIsTextPresent("Unfortunately British Gas doesnt currently supply this address");
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.GetAQuoteLink"))){
			Report.updateTestLog("System displays the error message panel Unfortunately British Gas doesnt currently supply this address with get a quote link", "PASS");
			ArrayList<String> list = new ArrayList<String>();
			list.add("BusinessMovesPage.BackLink");
			list.add("Moving in");			
			list.add("BusinessMovesPage.CancelLinkButton");
			list.add("Moving in");
			list.add("BusinessMovesPage.BreadCrumbLink");
			list.add("Moving in");
			for (int i=0;i<list.size();i=i+2) {
				System.out.println(list.get(i) + "       " + list.get(i+1));
				verifyLinksandTitle(list.get(i),list.get(i+1));
			}
			Report.updateTestLog("User navigates to the get a quote page when the user clicks the get a quote link", browser.getTitle().equalsIgnoreCase("Qet a quote")?"PASS":"FAIL");
		}
		else{
			Report.updateTestLog("System displays the error message panel Unfortunately British Gas doesnt currently supply this address with get a quote link", "FAIL");
		}
		
	}
	public void verifySummaryPage(){
		verifyPageTitle("Summary");
		
		String typeOfAccount = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.typeOfAccount"));
		String accNum = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.accNum"));
		String movingAddress = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.movingAddress"));
		String movingOutDate = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.movingOutDate"));
		String billingAddress = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.billingAddress"));
		Report.updateTestLog("Details:typeOfAccount"+typeOfAccount+"accNum :"+accNum+" movingAddress:"+movingAddress+" movingOutDate:"+movingOutDate+" billingAddress:"+billingAddress, "DONE");
	}
	
	public void verifyAnnonymousSummaryPage(){
		String yourName = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.YourNameVerification"));
		String businessName = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.BusinessNameVerification"));
		String email = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.emailVerification"));
	//	String accountNo = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.accNoVerification"));
		String movingDate = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.MovingDateVerification"));
		String yourAddress = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.YourAddressVerification"));
		String billingAddress = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.BillingAddressVerification"));
		Report.updateTestLog("Details: Your Name "+yourName+"business Name :"+businessName+" email:"+email+" Moving Date:"+movingDate+"Your address :"+yourAddress+"Billing Address :"+billingAddress, "DONE");
		
	}
	public void checkMovingNewPremises(String premises){
		int days=DateTime.now().getDayOfMonth();
		String day=Integer.toString(days);
		if(premises.equalsIgnoreCase("Yes")){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.YesButton"), "Yes button");
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FromDate"), "FromDate");
		for(int i=1;i<4;i++){
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.PreviousDatePicker"), "PreviousDatePicker");
			browser.wait(500);
		}
		String result=calenderDate(day);
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.BuildingNumber"), "Building name", "2");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.PostingCode"), "Posting code", "SW12 0NE");
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FindAddressButton"), "Find Address Button");
		browser.wait(getWaitTime());
			ArrayList<String> contact = browser.getFromDropBox("id", pageProperties.getProperty("BusinessMovesPage.Displayaddr"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("BusinessMovesPage.Displayaddr"), "Preferred Contact ", contact.get(2));
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.ConfirmAddress"), "Confirm address");
		browser.wait(500);
	//	verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Submit"), "Submit button");
	//	browser.wait(getWaitTime());
		if(result.equals("False")){
		   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NextDatePicker"), "NextDatePicker");	
		   calenderDate(day);
		}
		}
		else{
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NoButton"), "No button");
			//verifyIsTextPresent("One of our agents will aim to contact you within the next 48 hours to discuss these and any associated accounts to finalise your move");
	
		}
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.SubmitButton"), "Submit button");
		browser.wait(6000);
	}
	public void verifyConfirmation(){
		browser.wait(getWaitTime());
		//verifyIsTextPresent("Your business move notification has been received");
		verifyIsTextPresent("Back to your account");
		
	}
	public void verifyAuditForConfirmation(UserProfile userProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"Business moves");
		String data = dbFunctions.getAuditType(auditType[0]);	
		//String data1 = dbFunctions.getAuditType(auditType[1]);	
		System.out.println(Arrays.asList(auditType));
		Report.updateTestLog("Audit id is made for Business moves email sent. Audit id: "+auditType[0]+ " Audit event type is: "+data, data.equalsIgnoreCase("BUSINESS_MOVES_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"DONE");

	}
	public void anonymousMovingPremisesHome(){
		browser.open(ApplicationConfig.APP_BG_URL+"/business/moving-premises");
		browser.wait(1000);
		verifyIsTextPresent(pageProperties.getProperty("BusinessMovesPage.MovingPremisesTitle"));
	//	if(browser.isTextPresent(pageProperties.getProperty("BusinessMovesPage.MovingPremisesTitle"))){
			
		//	verifyIsElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingOutLink"), "Moving out link");
		//	verifyIsElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingInLink"), "Moving in link");

		
	}
	
	public void clickMovingOutLink(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingOutLink"))){
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingOutLink"), "Moving out link");
		}
		browser.wait(100);
		verifyIsTextPresent(pageProperties.getProperty("BusinessMovesPage.MovingOutTitle"));
	}
	public void clickMovingInLink(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingInLink"))){
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingInLink"), "Moving in link");
		}
	}
	public void enterYourDetailsMovingOut(UserProfile userProfile){
		verifyAndSelectDropDownBox(pageProperties.getProperty("BusinessMovesPage.Title"), "Title", userProfile.getTitle());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.Firstname"), "FirstName", userProfile.getFirstName());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.surname"), "SurName", userProfile.getLastName());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.BusinessName"), "BusinessName",userProfile.getCompanyName());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.email"), "Email", userProfile.getEmail());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.telephoneNumber"), "ContactNumber", userProfile.getMobileNumber());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.AccountNumber"), "Account number", userProfile.getAccNumber());
 			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Continue"), "Continue button");

	}
	public void enterYourDetailsMovingIn(UserProfile userProfile){
		verifyAndSelectDropDownBox(pageProperties.getProperty("BusinessMovesPage.Title"), "Title", userProfile.getTitle());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.Firstname"), "FirstName", userProfile.getFirstName());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.surname"), "SurName", userProfile.getLastName());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.BusinessName"), "BusinessName",userProfile.getCompanyName());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.email"), "Email", userProfile.getEmail());
			verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.telephoneNumber"), "ContactNumber", userProfile.getMobileNumber());
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Continue"), "Continue button");
	}
	public void enterYourMovingOutAddress(UserProfile userProfile,String addr){

		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.Buildingname"), "Building name", "abcd buliding");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.PostingCode"), "Posting code", userProfile.getPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.FindAddressButton"), "Find Address Button");
		verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.Postcode"),"Postcode",userProfile.getPostCode().trim());
		verifyAndClick(pageProperties.getProperty("BusinessMovesPage.FindAddress"), "Find Address Button");
		browser.wait(getWaitTime());
		waitForObjectExistance(pageProperties.getProperty("BusinessMovesPage.EnterAddressManually"),"xpath");
		if(addr.equalsIgnoreCase("Manual")){				
			verifyAndClick(pageProperties.getProperty("BusinessMovesPage.EnterAddressManually"), "Enter Your Address Manually");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.FlatNumber"),"Flat Number","Unit 5");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.Address1"),"Address 1","Greenfield Farm Industrial Estate");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.PostalTown"),"Postal Town","CONGLETON");
			browser.selectfromDropBox("id",pageProperties.getProperty("BusinessMovesPage.County"), "Cheshire");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.PostCode"),"PostCode",userProfile.getPostCode());
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Confirm"), "Confirm");

		}
		else{
			ArrayList<String> contact = browser.getFromDropBox("id", pageProperties.getProperty("BusinessMovesPage.Displayaddr"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("BusinessMovesPage.Displayaddr"), "Preferred Contact ", contact.get(1));
		}
	
	}
	
	
	public void clickConfirmAddress(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Submit"), "Submit button");
		browser.wait(15000);

	}
	public void checkNewMovingInOption(String strMovingInOption){
		if(strMovingInOption.equalsIgnoreCase("Yes")){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.YesButton"), "Yes button");
		}
		else{
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NoButton"), "No button");

		}
	}
	public void submitNewPropertyDetails(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingInDate"), "Moving in date");
		//Need to add code to move in date 
		
	}
	public void enterMovingInDate(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingInDate"), "Moving in date");
		//Need to add code to move in date 
	}
	public void editMovingInAddress(UserProfile userProfile){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.MovingInEditAddr"), "Edit address link");
		enterYourDetailsMovingIn(userProfile);
	}
	public void clickCacelButton(){
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.CancelButton"), "Cancel");
		browser.wait(getWaitTime());
		verifyIsTextPresent("Your accounts");
	}
	public void verifyAccountDetails(){
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.AccountdetailsSection"), "Account details section");
		browser.wait(getWaitTime());
		String accountNum = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.AccountNumber"));
		String movingOutAddr = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.MovingOutAddress"));
		String movingOutDate = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.MovingOutDate"));
		Report.updateTestLog("Account number is : "+accountNum+".Moving out address is: "+ movingOutAddr+".Moving out date: "+movingOutDate, "DONE");
		
	}
	public void verifyEditAddressLink(UserProfile userProfile){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.EditAddressLink"))){		
		Report.updateTestLog("System displays the selected address along with the edit address link panel in the billing address section", "PASS");
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EditAddressLink"), "Edit address link");
		browser.wait(1000);
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EnterAddressManually"), "Enter address manually link");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualBuildingNumber"), "Manual address entry for building number", "19");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualBuildingName"), "Manual address entry for building name", "Upper");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress1"), "Manual address entry for Address line1", "Dicconson");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress2"), "Manual address entry for Address line2", "Third");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualAddress3"), "Manual address entry for Address line3", "Street");
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualLocality"), "Manual address entry for Locality", "City");
		   
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.SelectCountry"))){
				Select select = new Select(browser.getElementByXpath(pageProperties.getProperty("BusinessMovesPage.SelectCountry")));
				select.selectByIndex(2);
				Report.updateTestLog("Country selection :" +select+ "Selected from Dropdown", "Pass");
			}else {
				Report.updateTestLog("Problem with country selection", "Fail");	
			}
		     
		   verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.ManualPostCode"), "Manual address entry for Postcode", "CP12 4TR");
		   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Confirm"), "Confirm");
		 /*  browser.wait(15000);
		   verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NewAddressconfirm"), "Continue button");*/
		/*verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.FlatNumber"),"Flat Number",userProfile.getHomeNumber());
		verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.Address1"),"Address 1",userProfile.getStreet());
		verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.PostalTown"),"Postal Town",userProfile.getCity());
		browser.selectfromDropBox("id",pageProperties.getProperty("BusinessMovesPage.County"), userProfile.getCountry());
		verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.PostCode"),"PostCode",userProfile.getPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Confirm"), "Confirm");*/
		}
		
	}
	public void verifyAddressInBillingSection(UserProfile userProfile){
	/*	String billingAddr = browser.getTextByXpath(pageProperties.getProperty("BusinessMovesPage.billingAddress"));
		if(billingAddr.contains(userProfile.getHomeNumber())&&billingAddr.contains(userProfile.getCountry())){
			Report.updateTestLog("Billing address displying in billing address section", "PASS");
		}
		else{
			Report.updateTestLog("Billing address displying in billing address section", "FAIL");
		}*/
		browser.wait(1000);
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.ContinueButton"), "Continue Button");
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.BackButton"), "Back Button");
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.CancelButtonInBilling"), "Cancel Button");
	}
	/*public void verifyEditAddressLinkInMovingOutAndBillingAddrSec(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.EditAddressLinkInMovingOut"))){		
		Report.updateTestLog("System displays the selected address along with the edit address link panel in the billing address section", "PASS");
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EditAddressLinkInMovingOut"), "Edit address link in moving out section");		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.EnterBillingAddress"))){	
			Report.updateTestLog("System displays the selected address along with the edit address link panel in the Moving out section", "PASS");
	
		}
		else{
			Report.updateTestLog("System displays the selected address along with the edit address link panel in the Moving out section", "FAIL");

		}
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.CloseOverlay"), "Close overlay");		
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EditAddressLinkInBillingAddrSection"), "Edit address link in moving out section");		

		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.EnterBillingAddress"))){	
			Report.updateTestLog("System displays the selected address along with the edit address link panel in the Moving out section", "PASS");
	
		}
		else{
			Report.updateTestLog("System displays the selected address along with the edit address link panel in the Moving out section", "FAIL");

		}
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.CloseOverlay"), "Close overlay");		

		}
		
	}*/
	public void checkRequestCallBack(){
			//verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NoButton"), "No button");
			//verifyIsTextPresent("One of our agents will aim to contact you within the next 48 hours to discuss these and any associated accounts to finalise your move");
			//verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.RequestACallBack"), "Request a call back");
			browser.wait(getWaitTime());
			verifyPageTitle("Business move callback");
			verifyIsTextPresent("Request a call back");
			verifyIsTextPresent("A British Gas agent will call you back");
			String firstName = browser.getAttribute(pageProperties.getProperty("BusinessMovesPage.FIRSTNAME"),"value");
			String surName = browser.getAttribute(pageProperties.getProperty("BusinessMovesPage.SURNAME"),"value");
			String email = browser.getAttribute(pageProperties.getProperty("BusinessMovesPage.EMAIL"),"value");
			Report.updateTestLog("First name field filled with "+firstName, "DONE");
			Report.updateTestLog("Sur name field filled with "+surName, "DONE");
			Report.updateTestLog("Email field filled with "+email, "DONE");
			String telephoneNumber = null;
			/*try{
			telephoneNumber = browser.getAttribute(pageProperties.getProperty("BusinessMovesPage.TELEPHONENUMBER"),"value");
			Report.updateTestLog("Telephone field filled with "+telephoneNumber, "DONE");
			}
			catch(Exception e){*/
				verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.TELEPHONENUMBER"), "Telephone Number", "0987654321");
				Report.updateTestLog("Telephone field filled with "+telephoneNumber, "DONE");
//			}
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.BUSINESSNAME"), "Business name", "Centrica");
			verifyAndInputById(pageProperties.getProperty("BusinessMovesPage.NOOFSITES"), "No of sites", "99999");
			verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.SubmitButton"), "Submit button");
			}
			
			/*ArrayList<String> list = new ArrayList<String>();
			list.add("BusinessMovesPage.RequestACallBackLink");
			list.add("Request a call back");		
			list.add("BusinessMovesPage.BackButtonLink");
			list.add("Billing address");
			list.add("BusinessMovesPage.CancelLinkButton");
			list.add("Moving out");
			list.add("BusinessMovesPage.SubmitButtonLink");
			list.add("Confirmation");
			for (int i=0;i<list.size();i=i+2) {
				System.out.println(list.get(i) + "       " + list.get(i+1));
				verifyLinksandTitle(list.get(i),list.get(i+1));
			}*/
		
	private void verifyLinksandTitle(String link, String title) {
		int count =1;
		String linkName = link.substring(link.indexOf('.') + 1, link.length());
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty(link))) {
			Report.updateTestLog(linkName + " Link Exist", "PASS");
			browser.clickWithXpath(pageProperties.getProperty(link));
			do{
				getWaitTime();
				if(browser.getTitle().equalsIgnoreCase(title))
				{
					verifyPageTitle(title);
					break;
				}
				count++;
			}while(count<10);
			browser.browserBack();
			
		 } 
		else {
			Report.updateTestLog(linkName + " Link not Exist", "FAIL");
		}
	}
	public void verifyAuditEntry(UserProfile userProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"BUSINESSMOVES");
		String data = dbFunctions.getAuditType(auditType[0]);	
		//String data1 = dbFunctions.getAuditType(auditType[1]);	
		System.out.println(Arrays.asList(auditType));
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is:"+data, data.trim().equalsIgnoreCase("BGBUSINESS_OPPORTUNITY_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"PASS");

	}
	public void verifyAuditEntryMovingOut(UserProfile userProfile){
		
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"QueryingReferenceNumber:success");
		String data = dbFunctions.getAuditType(auditType[0]);	
		String[] auditType2 = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"businessMovesEmailSent:success");
		String data2 = dbFunctions.getAuditType(auditType2[0]);	
		System.out.println(Arrays.asList(auditType));
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is:"+data, data.trim().equalsIgnoreCase("BGBUSINESS_BUSINESSMOVES_REFERENCE_NUMBER_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"PASS");
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType2[0]+ " Audit event type is:"+data2, data2.trim().equalsIgnoreCase("BGBUSINESS_BUSINESSMOVES_EMAIL_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType2[1],"PASS");
		
		
	}
	/*public void verifyAuditEntry_Anony(UserProfile userProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"businessMovesEmailSent:success");
		String data = dbFunctions.getAuditType(auditType[0]);	
		String[] auditType2 = dbFunctions.getAuditEventTypeId(date, userProfile.getEmail(),"QueryingReferenceNumber:success");
		String data2 = dbFunctions.getAuditType(auditType2[0]);	
		//String data1 = dbFunctions.getAuditType(auditType[1]);	
		System.out.println(Arrays.asList(auditType));
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is:"+data, data.trim().equalsIgnoreCase("BGBUSINESS_BUSINESSMOVES_EMAIL_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"PASS");
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType2[0]+ " Audit event type is:"+data2, data2.trim().equalsIgnoreCase("BGBUSINESS_BUSINESSMOVES_REFERENCE_NUMBER_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType2[1],"PASS");

	}*/
	public void verifyPodsInConfirmationPage(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("BusinessMovesPage.ViewYourAccount");
		list.add("Your account");		
		list.add("BusinessMovesPage.ViewYourBill");
		list.add("View Bill");
		list.add("BusinessMovesPage.SMRLink");
		list.add("Submit meter read");
		list.add("BusinessMovesPage.EnergymadeSimple");
		list.add("Energy made simple");
		for (int i=0;i<list.size();i=i+2) {
			System.out.println(list.get(i) + "       " + list.get(i+1));
			verifyLinksandTitle(list.get(i),list.get(i+1));
		}
	}
	public void verifyCallBackThankYouPage(){
		if(browser.isTextPresent("Thank you, one of our agents will give you a call back in 48 hours.")){
			Report.updateTestLog("Thank you page is displayed for request a call back submission.", "PASS");
			if(browser.isTextPresent("Back to your account")){
				verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.BackToYourAccount"), "Back to your account");
				verifyPageTitle("Account overview");
			}
		}
		else{
			Report.updateTestLog("Thank you page is displayed for request a call back submission.", "FAIL");

		}
	}
	
	public void verifyBusinessMoves_AnonymousCallBack(UserProfile userProfile){
		browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("BusinessMovesPage.AnonymousURL"));
		verifyPageTitle("Business move callback");
		verifyIsTextPresent("Request a call back");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.firstname"), "FirstName", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.surname1"), "SurName", userProfile.getLastName());				
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.email"), "Email", userProfile.getEmail());
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.telephoneNumber"), "Telephone Number", "0123456789");
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.businessName"), "Business name", userProfile.getCompanyName());
		verifyAndInputByXpath(pageProperties.getProperty("BusinessMovesPage.NoOfSites"), "No of sites", "99999");
		verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.SubmitButton"), "Submit button");		
		}
	public void verifyCallBackThankYouPage_Anonymous(){
		if(browser.isTextPresent("Thank you, one of our agents will give you a call back in 48 hours.")){
			Report.updateTestLog("Thank you page is displayed for request a call back submission.", "PASS");
			if(browser.isTextPresent("Back to your account")){
				verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.BackToYourAccount"), "Back to your account");
				verifyPageTitle("Your account - British Gas Business");
			}
		}
		else{
			Report.updateTestLog("Thank you page is displayed for request a call back submission.", "FAIL");

		}
	}
	 public void logout(){
	    	verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Logout"), "Log out link");	    	 
	    }
	 public void linkVerificationMovingOut(){
			// Back link verification //
					verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.Back"), "Back link in summary page");
					browser.wait(200);
					verifyIsTextPresent("Your billing address");
					browser.browserBack();
					browser.wait(200);
					verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.NoButton"), "No button");
		}
	 
	 public void verifySummaryPageEditDetails(){
		 // Edit detiails link verification on Moving out date //
		 verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EditMovingOutDate"), "Edit details link of moving out date in summary page");
			browser.wait(1000);
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("BusinessMovesPage.Calender"))){
				Report.updateTestLog("Calender to select moving date present", "PASS");
			}
			else
			{
				Report.updateTestLog("Calender to select moving date absent", "FAIL");		 
	 }
			browser.browserBack();
			browser.wait(200);
			 // Edit detiails link verification on Moving out billing address //
			 verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.EditMovingOutAddress"), "Edit details link of moving out address in summary page");
				browser.wait(1000);
				verifyIsTextPresent("Current address");
				verifyIsTextPresent("Preferred address");
}

	public void browserback() {
		browser.browserBack();
			}

	public void clickRequestCallBack() {
		 verifyAndClickWithXpath(pageProperties.getProperty("BusinessMovesPage.requestcallback"), "request a call back in Summary page");
		
	}
}
