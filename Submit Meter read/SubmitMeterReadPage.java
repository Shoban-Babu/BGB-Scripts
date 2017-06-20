	package bg.framework.app.functional.page.Slingshot;
	
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
    import java.util.Calendar;
	import java.util.List;
	import java.util.Properties;
	import java.util.Random;
	import org.joda.time.DateTime;
	import org.joda.time.format.DateTimeFormat;
	import org.joda.time.format.DateTimeFormatter;
	import bg.framework.app.functional.page.common.SlingshotErrorMessages;
	import bg.framework.app.functional.common.ApplicationConfig;
	import bg.framework.app.functional.entities.SMRAccountDetails;
	import bg.framework.app.functional.page.common.BasePage;
	import bg.framework.app.functional.util.OnlineDBConnector;
	import bg.framework.app.functional.util.PropertyLoader;
	import bg.framework.app.functional.util.Report;
	import bg.framework.app.functional.util.RobotSendKeys;
	import bg.framework.app.functional.util.RunQTP;
	import bg.framework.app.functional.entities.UserProfile;
	import bg.framework.common.functional.UIDriver;
	import java.util.Date;
	import java.text.ParseException;
import java.text.SimpleDateFormat;
	
	public class SubmitMeterReadPage extends BasePage{
		protected UIDriver uiDriver;
		private final static String FILE_NAME = "resources/Slingshot/SubmitMeterRead.properties";
		private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
		SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
		public static final String TIMESTAMP_FORMATTER= "dd MMMM, yyyy";
		public String currentMeterRead;
	    public static int dialsCount;
	    public static int totalcount;
	
		public void enterValidData_Anonymous(SMRAccountDetails smrProfile){
			if(browser.isTextPresent("Your details")){
				Report.updateTestLog("Your Details page of Submit meter page is loaded", "WARN");
			}
			else {
				Report.updateTestLog("Your Details page of Submit meter page is not loaded", "FAIL");
			}
			
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email address", smrProfile.getEmail());
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", smrProfile.getAccountNumber());
			System.out.println("out of dfklbna");
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Postcode"), "PostCode", smrProfile.getsitePostcode());
			
			System.out.println("zzzzzzzzzzzzzzzzzzz");
			
			System.out.println("   " +smrProfile.getsitePostcode());
			Report.updateTestLog("Values are entered in Your details Page", "WARN");
		
		    verifyIsElementVisibleWithXpath(pageProperties.getProperty("Submitmeterreadpage.AnonymousCancel"), "Cancel");
			verifyAndClickWithXpath(pageProperties.getProperty("Submitmeterreadpage.AnonymousSubmit"), "Submit");
			browser.wait(15000);
			//verifySMRSiteNumber();
	
		}
		public void openSMRPage(String strTypeOfAccount){
			browser.open(ApplicationConfig.APP_BG_URL+"/business/submit-meter-read/your-details?productType="+strTypeOfAccount+"')]");
			Report.updateTestLog("Submit a meter Read Page is loaded", "WARN");
			//verifyAndClickWithXpath(pageProperties.getProperty("Submitmeterreadpage.Overlay"),"Overlay");
			browser.wait(2000);
			
	/*		
			browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("SubmitMeterReadPage.AnonymousSMRUrl"));
			browser.wait(getWaitTime());
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasRead")+strTypeOfAccount+"')]", strTypeOfAccount+" link");
			browser.wait(getWaitTime());*/
		}
		
		public void openSMRPageCR(String fueltype){
			browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("SubmitMeterReadPage.AnonymousSMRUrl"));
			browser.wait(getWaitTime());
			if(fueltype.equals("Gas"))
			{
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Gasfuel"),"Gas link");
			}
			else 
			{
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Elecfuel")," Elec link");
			}
			browser.wait(getWaitTime());
		}
		public void getDialsCountAndSubmitMeterRead(SMRAccountDetails smrProfile){
			
			try{
				if(getMultipleDialCount()>1){
					enterMeterDialForMultipleCount(smrProfile);
			}
				else{
					
					if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
						int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
						System.out.println("dialsCount "+dialsCount);
						Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
						String previousMeterRead = getPreviousMeterRead();
						System.out.println("previousMeterRead"+previousMeterRead);
						Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
						previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";		
						previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
						Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
						System.out.println("previousMeterRead "+previousMeterRead);
						for (int i = 1;i<=dialsCount;i++){
							browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
							browser.wait(500);
						}
						this.currentMeterRead = previousMeterRead;
						System.out.println("currentMeterRead "+currentMeterRead);
						//clickSubmitMeterRead();
						if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
							verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
							getWaitTime();
						}
						Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");
					}
					else {
						Report.updateTestLog("Meter read is already submitted for this account."+smrProfile.getAccountNumber(), "WARN");
					}
				}
			}
			catch(Exception e){
				Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
	
		}
		public String getPreviousMeterRead(){
			System.out.println("im n previs meter");
			String previousMeterRead1 = "";
			String previousMeterRead ="";
				
				System.out.println(""+dialsCount);
				for(int i=1;i<=dialsCount;i++){
					System.out.println("aaa"+dialsCount);
				previousMeterRead1 = browser.getAttributeByXpath(pageProperties.getProperty("SubmitMeterReadPage.PreviousMeterReadGetText").replace("dialsCount", ""+i),"value");
				System.out.println("previousMeterRead1="+previousMeterRead1);
				previousMeterRead=previousMeterRead.concat(previousMeterRead1);
				System.out.println("previousMeterRead   "+previousMeterRead);
				
			}
			return previousMeterRead;
		}
		
		public String getGlobalPreviousMeterElecReaddData(int i){
			System.out.println("im n previs meter");
			String previousMeterRead1 = "";
			String previousMeterRead ="";
			String replace1 ="";
			String replace2 ="";
				System.out.println(""+dialsCount);
				System.out.println("aaaaaaaaaa="+i);
				for(int k=1;k<=dialsCount;k++){
					System.out.println("aaa"+dialsCount);
					//ystem.out.println(",mnn="+k);
					replace2=(pageProperties.getProperty("SubmitMeterReadPage.ElecGlobalPreviousMeterDialsCount").replace("Global",""+i).replace("dialsCount",""+k));
					System.out.println("answer="+replace2);
					//replace1=browser.getAttributeByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalPreviousMeterDialsCount").replace("Global",""+i).replace("dialsCount",""+k),"value");
					//previousMeterRead1=
					//browser.getAttribute("replace2"
							//replace1=browser.getText("replace2");
							//System.out.println(" "+replace1);
					System.out.println("tooolllllllllllllllllllll="+previousMeterRead1);
				previousMeterRead1 = browser.getAttributeByXpath((pageProperties.getProperty("SubmitMeterReadPage.ElecGlobalPreviousMeterDialsCount").replace("Global",""+i).replace("dialsCount",""+k)),"value");
				System.out.println("tooolllllllllllllllllllll22222222="+ previousMeterRead1);
				System.out.println("previousMeterRead1="+previousMeterRead1);
				previousMeterRead=previousMeterRead.concat(previousMeterRead1);
				System.out.println("previousMeterRead   "+previousMeterRead);
			}
			return previousMeterRead;
		}
		public String getGlobalPreviousMeterReaddData(int i){
			System.out.println("im n previs meter");
			String previousMeterRead1 = "";
			String previousMeterRead ="";
			String replace1 ="";
			String replace2 ="";
				System.out.println(""+dialsCount);
				System.out.println("aaaaaaaaaa="+i);
				for(int k=1;k<=dialsCount;k++){
					System.out.println("aaa"+dialsCount);
					//ystem.out.println(",mnn="+k);
					replace2=(pageProperties.getProperty("SubmitMeterReadPage.GlobalPreviousMeterDialsCount").replace("Global",""+i).replace("dialsCount",""+k));
					System.out.println("answer="+replace2);
					//replace1=browser.getAttributeByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalPreviousMeterDialsCount").replace("Global",""+i).replace("dialsCount",""+k),"value");
					//previousMeterRead1=
					//browser.getAttribute("replace2"
							//replace1=browser.getText("replace2");
							//System.out.println(" "+replace1);
					System.out.println("tooolllllllllllllllllllll="+previousMeterRead1);
				previousMeterRead1 = browser.getAttributeByXpath((pageProperties.getProperty("SubmitMeterReadPage.GlobalPreviousMeterDialsCount").replace("Global",""+i).replace("dialsCount",""+k)),"value");
				System.out.println("tooolllllllllllllllllllll22222222="+ previousMeterRead1);
				System.out.println("previousMeterRead1="+previousMeterRead1);
				previousMeterRead=previousMeterRead.concat(previousMeterRead1);
				System.out.println("previousMeterRead   "+previousMeterRead);
				
			}
			return previousMeterRead;
		}
		
		public String getPreviousMeterReadelec(){
			System.out.println("im n previs meter");
			String previousMeterRead1 = "";
			String previousMeterRead ="";
		
		
		
				
				System.out.println(""+dialsCount);
				for(int i=1;i<=dialsCount;i++){
					System.out.println("aaa"+dialsCount);
				previousMeterRead1 = browser.getAttributeByXpath(pageProperties.getProperty("SubmitMeterReadPage4.PreviousMeterReadGetText").replace("dialsCount", ""+i),"value");
				System.out.println("previousMeterRead1="+previousMeterRead1);
				previousMeterRead=previousMeterRead.concat(previousMeterRead1);
				System.out.println("previousMeterRead   "+previousMeterRead);
				
			
				
			}
			return previousMeterRead;
		}
		public String getPreviousMeterReadAnonymous(){
			System.out.println("im n previs meter");
			String previousMeterRead1 = "";
			String previousMeterRead ="";
		
				
				System.out.println(""+dialsCount);
				for(int i=1;i<=dialsCount;i++){
					System.out.println("aaa"+dialsCount);
				previousMeterRead1 = browser.getAttributeByXpath(pageProperties.getProperty("SubmitMeterRead.FetchMeterRead").replace("dialsCount", ""+i),"value");
				System.out.println("previousMeterRead1="+previousMeterRead1);
				previousMeterRead=previousMeterRead.concat(previousMeterRead1);
				System.out.println("previousMeterRead   "+previousMeterRead);
				
			
				
			}
			return previousMeterRead;
		}
		public String getPreviousMeterReadAnonymousgas(){
			System.out.println("im n previs meter");
			String previousMeterRead1 = "";
			String previousMeterRead ="";
				System.out.println(""+dialsCount);
				for(int i=1;i<=dialsCount;i++){
					System.out.println("aaa"+dialsCount);
				previousMeterRead1 = browser.getAttributeByXpath(pageProperties.getProperty("SubmitMeterRead.FetchMeterReadGas").replace("dialsCount", ""+i),"value");
				System.out.println("previousMeterRead1="+previousMeterRead1);
				previousMeterRead=previousMeterRead.concat(previousMeterRead1);
				System.out.println("previousMeterRead   "+previousMeterRead);
				
			
				
			}
			return previousMeterRead;
		}
		/*public String getActualReadDate(){		
			String actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOn"));
			String actualReadOn[] = actualReadDate.split("on");
			System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
			actualReadDate = actualReadOn[1].trim();
			Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
			System.out.println(actualReadOn[1].trim());
			return actualReadDate;
		}*/
		private String padZeros(String previousMeterValue, int dialFieldCount) {
			for (int i = previousMeterValue.length(); i < dialFieldCount; i++) {
				previousMeterValue = "0" + previousMeterValue;
			}
			return previousMeterValue;
		}
			
		public void clickGlobalSmrLink() {	
			//verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterRead.ViewBillLink"), "View bill link");
			
		
			getWaitTime();
			//System.out.println("view bill lik clicked");
			System.out.println("page properties :"+pageProperties.getProperty("SubmitMeterRead.SmrLinkGlobal"));
			if(browser.isElementVisibleWithCss("u")){
				browser.clickWithCss("u");
				System.out.println("Element visible with CSS");
			}else{
				System.out.println("Element not visible with CSS");
			}
		}
		public void searchBy(SMRAccountDetails smrProfile) {	
	
			verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterRead.SearchBy"), "Search By", "contractAccountNumber");
			verifyAndInputById(pageProperties.getProperty("SubmitMeterRead.KeywordToSearch"), "Keyword", smrProfile.getAccountNumber());
			verifyAndClick(pageProperties.getProperty("SubmitMeterRead.SearchButton"), "Search Button");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterRead.SubmitCheck"), "Submit Dial Present");
		}
	
		public void clickSearchinGlobalLink(SMRAccountDetails smrProfile) {	
	
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterRead.SearchButton"), "Search Button");
		}
		public void enterMeterRead(SMRAccountDetails smrProfile) {	
			int bro=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterRead.SearchButton"));
		}
		public void getMultiDialCountAndSubmitMeterRead(SMRAccountDetails smrProfile,int dialcount){
			int dia=dialcount;
			System.out.println("dial count"+dialcount);
			try{
				System.out.println("childitemcount:"+pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsCount").replace("traverse", ""+dialcount));	
				int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsCount").replace("traverse", ""+dialcount));
				System.out.println("dialsCount "+dialsCount);
				Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
				String previousMeterRead = getMultiplePreviousMeterRead(dialcount);
				Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
				previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";	
				System.out.println("PREVIOUS METER READ LENGHT"+previousMeterRead.length()+"DIALS COUNT"+dialsCount);
				previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
				Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
				System.out.println("previousMeterRead "+previousMeterRead);
				for (int i = 1;i<=dialsCount;i++){
					String inpxpath=pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsInputXPath")+i+"')]";
					verifyAndInputByXpath(inpxpath.replace("traverse", ""+dia), "Dial",previousMeterRead.charAt(i - 1)+"");
					System.out.println(inpxpath.replace("traverse", ""+dia)+previousMeterRead.charAt(i - 1));
					browser.wait(500);
				}
				this.currentMeterRead = previousMeterRead;
				System.out.println("currentMeterRead "+currentMeterRead);
				Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");		
			}
			catch(Exception e){
				Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
		}
		public String getMultiplePreviousMeterRead(int dialcount){			
			String previousMeterRead = "";
			try{	
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.MultipleDials").replace("traverse", ""+dialcount));
				System.out.println("previousMeterRead "+previousMeterRead);
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
		public int getMultipleDialCount(){
			int multiDialCount=0;
			try{
				System.out.println("welcome");
				multiDialCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterRead.multipleDialCount"));
				System.out.println("multiDialCount"+multiDialCount);
			}catch(Exception e){
				Report.updateTestLog("Exception occured while retieving MultiDialCount :"+e,"Fail");
			}
			return multiDialCount;
		}
		public int getMultipleDialCountCR(){
			int multiDialCount=0;
			try{
				multiDialCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadCR.multipleDialCount"));
				System.out.println("multiDialCount"+multiDialCount);
			}catch(Exception e){
				Report.updateTestLog("Exception occured while retieving MultiDialCount :"+e,"Fail");
			}
			return multiDialCount;
		}
		/*public void enterMeterDialForMultipleCountforGlobalSmr(SMRAccountDetails smrProfile){
	
			int multicount=getMultipleDialCount();
			int dialsec = 0;
			for(int i=0;i<multicount;i++){
				switch (multicount){
				case 1:
					dialsec=i+2;
					getMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
					break;
				case 2:
					dialsec=i+3;
					getMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
					break;
				case 3:
					dialsec=i+4;
					getMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
					break;
				}	
				System.out.println("case count:"+dialsec);
			}
			clickSubmitMeterRead();
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
				clickSubmitMeterRead();
				getWaitTime();
			}
			verifyGasConfirmation();
		}*/
		
		public void searchBy(String userProfile) {	
	
			verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterRead.SmrGlobalLink"), "Search By", "MPRN");
			verifyAndInputById(pageProperties.getProperty("SubmitMeterRead.SmrGlobalLink"), "Keyword", userProfile);
			verifyAndClick(pageProperties.getProperty("SubmitMeterRead.SearchButton"), "Search Button");
		}
		public void validateAccountNumberField(SMRAccountDetails smrProfile){
			String[] customerRefNumber = {"","600447497ab","600447497*&%","600447400"}; 	  
			final String firstName = smrProfile.getLastName();
			final String lastName = smrProfile.getLastName();
			final String title = smrProfile.getTitle(); 
			final String email = smrProfile.getEmail();
			final String MPRN = smrProfile.getMprn();
			final String MeterSerialNumber = smrProfile.getMeterSerialNumberGas();
			for(int i = 0; i < customerRefNumber.length; i++){
				switch (i){    	 	   
				case 0:enterValidData(title, firstName, lastName, email, customerRefNumber[i], MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_EmptyAccountNumber);
				break;
				case 1:enterValidData(title, firstName, lastName, email, customerRefNumber[i], MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_InvalidAccountNumber);
				break;
				case 2:enterValidData(title, firstName, lastName, email, customerRefNumber[i], MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_InvalidAccountNumber);
				break;
				case 3:enterValidData(title, firstName, lastName, email, customerRefNumber[i], MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_AccountNumberNotBelongsToBP);
				break;
				}
			}
		}
		//Verifying 'Title' field contents
		public void verifyAndValidateSMRTitleFieldContent(SMRAccountDetails smrProfile){
			int flg = 0;
			final String[] titleFieldContents = {"Please select", "Mr", "Mrs", "Miss", "Ms", "Dr", "Sir", "Reverend", "Dame", "Lady","Professor"};
			final ArrayList<String> actualTitleContent =browser.getFromDropBox("id", pageProperties.getProperty("SubmitMeterReadPage.Title"));
			for(int i=0;i<titleFieldContents.length; i++){
				if(actualTitleContent.get(i).trim().equalsIgnoreCase(titleFieldContents[i].trim())){
					flg = flg + 1;
				}
				else {
					Report.updateTestLog("Expected 'Title' fileld content does not present", "FAIL"); 
				}
				if(flg == 12)
					Report.updateTestLog("Expected 'Title' field content is present", "PASS");  
			}
			validateForgotEmailTitleField(actualTitleContent.get(0));
		}
	
		//Validate 'Title' field content without selecting any value
		public void validateForgotEmailTitleField(String title){
			enterTitleField(title);
			clickContinueButton();
			errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidTitleSelection);
		}
	
		//  Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
		public void errorMessageComparison(final String expectedErrorMsg) {
			try{
				final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSmrErrorMessage2"));
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
		public void enterCustomerRefNumberField(String customerRefNumber){
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", customerRefNumber);
			clickContinueButton();
		}
		public void clickContinueButton(){
		
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"))){
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");
				
				browser.wait(5000);
			}
		}
		public void enterTitleField(String title){
			verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", title);
			//clickContinueButton();
		}
		//Validating the Error Message by providing different invalid input combinations for Account Number
		public void firstNameErrorMsgValidation(SMRAccountDetails smrProfile) {
	
			final String contractAccountNumber = smrProfile.getAccountNumber();
			final String surName = smrProfile.getLastName();
			final String title = smrProfile.getTitle(); 
			final String email = smrProfile.getEmail();
			final String MPRN = smrProfile.getMprn();
			final String MeterSerialNumber = smrProfile.getMeterSerialNumberGas();
			final String[] firstName = {"firstname123", "1234567890", "", "first*name", "firstname;"};
			for (int i = 0; i < firstName.length; i++) {
				switch (i){
	
				case 0:enterValidData(title, firstName[i], surName, email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_FirstNameWithNumbers);
				break;
	
				case 1:enterValidData(title, firstName[i], surName, email, contractAccountNumber, MPRN, MeterSerialNumber );   	  clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_NumericFirstName);
				break;
	
				case 2:enterValidData(title, firstName[i], surName, email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_EmptyFirstName);
				break;
	
				case 3:enterValidData(title, firstName[i], surName, email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_FirstNameWithStar);
				break;
	
				default:enterValidData(title, firstName[i], surName, email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_FirstNameWithSemicolon);
				break;    		   
				}
			}
		}
		//Validating the Error Message by providing different invalid input combinations for Account Number
		public void surNameErrorMsgValidation(SMRAccountDetails smrProfile) {
	
			final String contractAccountNumber = smrProfile.getAccountNumber();
			final String firstName = smrProfile.getLastName();
			final String title = smrProfile.getTitle(); 
			final String email = smrProfile.getEmail();
			final String MPRN = smrProfile.getMprn();
			final String MeterSerialNumber = smrProfile.getMeterSerialNumberGas();
			final String[] surName = {"surname123", "1234567890", "", "sur*name", "surname;"};
			for (int i = 0; i < surName.length; i++) {
				switch (i){
	
				case 0:enterValidData(title, firstName, surName[i], email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SurNameWithNumbers);
				break;
	
				case 1:enterValidData(title, firstName, surName[i], email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_NumericSurName);
				break;
	
				case 2:enterValidData(title, firstName, surName[i], email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_EmptySurName);
				break;
	
				case 3:enterValidData(title, firstName, surName[i], email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SurNameWithStar);
				break;
	
				default:enterValidData(title, firstName, surName[i], email, contractAccountNumber, MPRN, MeterSerialNumber );  
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.SlingShot_SurNameWithSemicolon);
				break;    		   
				}
			}
		}
		public void enterValidData(String title, String firstName, String lastName, String email, String contractAccountNumber, String MPRN, String meterSerialNumber ){
			verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.PageTitle"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", title);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", firstName);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", lastName);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email address",email);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", contractAccountNumber);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Mprn"), "MPRN", MPRN);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterSerialNumber"), "Meter Serial Number", meterSerialNumber);
			clickContinueButton();			
		}
		public void validateEmailAddressField(SMRAccountDetails smrProfile){
			String[] emailAddr= {"","ganthimani.s@ganthimani.s@cognizant.com","abcdeftqwertyqwertyqwertyqwertyqwertyqweq@bgdigitaltal.co.uk"};
			final String contractAccountNumber = smrProfile.getAccountNumber();
			final String firstName = smrProfile.getLastName();
			final String title = smrProfile.getTitle(); 
			final String lastName = smrProfile.getLastName();
			final String MPRN = smrProfile.getMprn();
			final String meterSerialNumber = smrProfile.getMeterSerialNumberGas();
			for(int i = 0; i<emailAddr.length-1;i++){
				switch(i){
				case 0:
					enterValidData(title, firstName, lastName, emailAddr[i], contractAccountNumber, MPRN, meterSerialNumber);
					clickContinueButton();
					errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_EmptyEmail);
					break;
				case 1:
					enterValidData(title, firstName, lastName, emailAddr[i], contractAccountNumber, MPRN, meterSerialNumber);
					clickContinueButton();
					errorMessageComparison(SlingshotErrorMessages.SlingShot_SMR_InvalidEmail);
					break;
				case 2:
					enterValidData(title, firstName, lastName, emailAddr[i], contractAccountNumber, MPRN, meterSerialNumber);
					clickContinueButton();
					errorMessageComparison(SlingshotErrorMessages.SlingShot_InvalidLengthEmail);
					break;
				}
			}
		}
		public void validateMSNField(SMRAccountDetails smrProfile){
			String[] meterSerialNumber= {"","2391765abcd","2391765%^&","2391700"};
			final String contractAccountNumber = smrProfile.getAccountNumber();
			final String firstName = smrProfile.getLastName();
			final String title = smrProfile.getTitle(); 
			final String lastName = smrProfile.getLastName();
			final String emailAddr = smrProfile.getEmail();
			final String MPRN = smrProfile.getMprn();
	
			for(int i = 0; i<meterSerialNumber.length-1;i++){
				switch(i){
				case 0:
					enterValidData(title, firstName, lastName, emailAddr, contractAccountNumber, MPRN, meterSerialNumber[i]);
					errorMessageComparison(SlingshotErrorMessages.SlingShot_smr_EmptyMsn);
					break;
				case 1:
					enterValidData(title, firstName, lastName, emailAddr, contractAccountNumber, MPRN, meterSerialNumber[i]);			
					errorMessageComparison(SlingshotErrorMessages.SlingShot_smr_MSNWithAlbhaNumeric);
					break;
				case 2:
					enterValidData(title, firstName, lastName, emailAddr, contractAccountNumber, MPRN, meterSerialNumber[i]);			
					errorMessageComparison(SlingshotErrorMessages.SlingShot_smr_MSNWithSplChar);
					break;
				case 3:
					enterValidData(title, firstName, lastName, emailAddr, contractAccountNumber, MPRN, meterSerialNumber[i]);			
					errorMessageComparison(SlingshotErrorMessages.SlingShot_smr_InvalidMSN);
					break;			
				}
			}
		}
	
		public String verifyServiceDeskCustomerSMR(SMRAccountDetails smrProfile){		
			Random random = new Random();
			//int accountNum = 600764578;
			//int meterSerialNum = 2391765;
			//String accountNumber = Integer.toString(accountNum);
			//String meterSerialNumber = String.valueOf(meterSerialNum);		
			enterValidData(smrProfile.getTitle(), smrProfile.getFirstName(), smrProfile.getLastName(), smrProfile.getEmail(), smrProfile.getAccountNumber(),smrProfile.getMprn(), smrProfile.getMeterSerialNumber());			
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.GasServiceDeskMeterReadPageTitle"));
			
			int randomSiteNumber=60012;
			int meterread = 100;		
			String randomSiteNumber1 = randomSiteNumber +"";
			String meterRead = meterread+"";
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"))){
				Report.updateTestLog("Gas customer is not identified within CRM, hence navigates to SMR second page with site number", "Pass");
			}
			else{
				Report.updateTestLog("Gas customer is not identified within CRM, hence navigates to SMR second page with site number", "Fail");
			}
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", randomSiteNumber1);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterReading"),"Meter Reading",meterRead);
			verifyDontRemindEmailCheckBox();
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Submit button");
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ConfirmationPageTitle"));
			//verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.MeterReadConfirmation"));
			return meterRead;
		}
		public void nonSAPServiceDeskCustomer_Electricity(){
			Random random = new Random();
			int accountNum = 69876;
			int meterSerialNum = 2391;
			String accountNumber = Integer.toString(accountNum & random.nextInt());
			String meterSerialNumber = String.valueOf(meterSerialNum);		
			System.out.println(" accountNumber "+accountNumber+" meterSerialNumber "+meterSerialNumber);
	
		}
		public void enterElectricityData(SMRAccountDetails smrProfile){
			verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.ElectricityPageTitle"));
			verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", smrProfile.getTitle());
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", smrProfile.getFirstName());
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", smrProfile.getLastName());
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email address",smrProfile.getEmail());
			//verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityPageTitle"));
			getAccountAndMeterSerialNumber(smrProfile);
		}
		public void getAccountAndMeterSerialNumber(SMRAccountDetails smrProfile){
			Random random = new Random();
			int accountNum = 600764578;
			int meterSerialNum = 239176;
			accountNum = accountNum & random.nextInt();
			String accountNumber = String.valueOf(accountNum);
			String meterSerialNumber = String.valueOf(meterSerialNum);		
			System.out.println(" accountNumber "+accountNumber+" meterSerialNumber "+meterSerialNumber);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", accountNumber);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterSerialNumber"), "Meter Serial Number", meterSerialNumber);		
			int mpan = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.NumberOfInputMpan"));
			String mpanActual = smrProfile.getMpan();
			System.out.println(mpanActual.length());
			String mpan1 = mpanActual.substring(0, mpanActual.length()-11);
			String mpan2 = mpanActual.substring(mpanActual.length()-11, mpanActual.length()-7);
			String mpan3 = mpanActual.substring(mpanActual.length()-7, mpanActual.length()-3);
			String mpan4 = mpanActual.substring(mpanActual.length()-3, mpanActual.length());
			String[] mpanPretext = {"22","222","222",mpan1,mpan2,mpan3,mpan4};
			System.out.println(mpanPretext);
			for(int i = 0; i<=mpan-1; i++){
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MpanTextBox")+(i+1)+"']", "Mpan-"+i, mpanPretext[i]);
				System.out.println(mpanPretext[i]);
			}
			clickContinueButton();
		}
	
		public String verifyElectricityMeterReads(){
			Random random = new Random();
			int day = 6987;
			int siteNumber = 239176;
			day = day & random.nextInt();
			siteNumber = siteNumber & random.nextInt();
			String siteNumberValue = String.valueOf(siteNumber);
			String dayValue = String.valueOf(day);
			verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.ElectricityMeterReadTitle"));
		verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityMeterReadPageTitle"));
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"))){
				Report.updateTestLog("Electricity customer is not identified within CRM, hence navigates to SMR second page with site number", "Pass");
			}
			else{
				Report.updateTestLog("Electricity customer is not identified within CRM, hence navigates to SMR second page with site number", "Fail");
			}
			verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.ElectricityMeterReadTitle"));
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Day"), "Day", dayValue);
			clickContinueButton();
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElecReadConfirmation"));
			return dayValue;
		}
		public String currentMeterReadImplausible(int dialscount,String previousMeterRead){
			String previousMeterRead1="999";
			switch(dialscount){
			case 2:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+10)+"";
				break;	
			case 3:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+1000)+"";
				break;	
			case 4:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+1000)+"";
				break;
			case 5:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+10000)+"";
				break;
			case 6:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+100000)+"";
				break;
			case 7:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+1000000)+"";
				break;
			case 8:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+10000000)+"";
				break;
			case 9:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+100000000)+"";
				break;
			case 10:
				previousMeterRead1 = (Integer.parseInt(previousMeterRead)+1000000000)+"";
				break;
			}
			return previousMeterRead1;
		}
		public void verifyImplausibleOverlay(){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				String getImplausible=browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ImplausibleText"));
				Report.updateTestLog("Error message validation - Expected Result :"+errormsg.Smr_ImplausibleRead+"ActualResult "+getImplausible,getImplausible.contains(errormsg.Smr_ImplausibleRead)?"Pass":"Fail");
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ImpausibleCloseOverlay"))){
					Report.updateTestLog("Implausible overlay close button is identified","Pass");
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CancelOverlayButton"), "Cancel");
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ImpausibleCloseOverlay"), "Close overlay");
					Report.updateTestLog("Implausible overlay is closed","Pass");
				}else{
					RobotSendKeys.typeEsc();
					Report.updateTestLog("Robot send keys -esc is typed","Pass");
				}
			}else{
				Report.updateTestLog("Implausible overlay is not displayed","Fail");
			}
		}
		public void verifyOverlayAndSubmit(){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
				clickSubmitMeterRead();
				getWaitTime();
			}
		}
	
		public void verifyOverlayAndClose(){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				browser.clickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ImpausibleCloseOverlay"));
				Report.updateTestLog("ImpausibleCloseOverlay is clicked","Pass");
			}
			else{
				Report.updateTestLog("Implausible Overlay is not displayed for implausible value","WARN");
			}
		}
		public void verifyMeterAfterReglogin(SMRAccountDetails smrProfile){
			String previousMeter=getPreviousMeterRead();
			if(previousMeter.equals(smrProfile.getAfterSubmitMeterRead())){
				Report.updateTestLog("Previous meter read is the current meter read"+":Previous:"+smrProfile.getAfterSubmitMeterRead()+"Current:"+previousMeter,"Pass");
			}else{
				Report.updateTestLog("Previous meter read is not the current meter read"+":Previous:"+smrProfile.getAfterSubmitMeterRead()+"Current:"+previousMeter,"Fail");
			}
		}
		public void clickBackLink(){
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BackLinkGlobalSmrPage"), "Back");
		}
		public void verifyAccountOverviewTitle(){
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.AccountOverviewTitle"));
		}
		public void verifySearchByDropdown(){
			try{
				String text = null;
				String indicator="Null";
				String[] verifyText={"MPRN","MPAN (21 digits)"};
				List<String> countOf=browser.getFromDropBox("id", pageProperties.getProperty("SubmitMeterRead.SearchBy"));
				Report.updateTestLog("Number of Items in the listbox :"+countOf.size(),"Pass");
				for(String itera:verifyText){			
					for(int i=2;i<=countOf.size();i++){
						text=browser.getTextByXpath("//*[@id='"+pageProperties.getProperty("SubmitMeterRead.SearchBy")+"']"+"/option["+i+"]");
						if(itera.equals(text)){
							indicator="Pass";	
							break;
						}else{
							indicator="Null"; 	
						}
					}
					Report.updateTestLog("Search By Drop down value: Expected Result: "+itera+"Actual Result: "+text,indicator.contains("Pass")?"Pass":"Fail");
				}}catch(Exception e){
					Report.updateTestLog("Exception occured while retrieving drop down value"+e,"Fail");
				}	
		}
		public void validateMprnNumber(){
			String errormsgvalue="null";
			String mprnnumber[]={"","1234567890","12345678!@","1234567890123456789012","12345","12345678910"};
	
			for(int iterate=0;iterate < mprnnumber.length;iterate++){
				verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterRead.SearchBy"), "Search By", "Meter point reference number");
				verifyAndInputById(pageProperties.getProperty("SubmitMeterRead.KeywordToSearch"), "Keyword",mprnnumber[iterate]);   
				verifyAndClick(pageProperties.getProperty("SubmitMeterRead.SearchButton"), "Search Button");
				browser.wait(3000);
				if(iterate==0){
					errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSmrErrorMessage1"));
				}else{
					errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSmrErrorMessage2"));	
				}
	
				switch(iterate){
				case 0:
					Report.updateTestLog("Expected Result: "+errormsg.Smr_EmptySearchCriteria+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_EmptySearchCriteria)?"Pass":"Fail");
					break;
				case 1:
					Report.updateTestLog("Expected Result: "+errormsg.Smr_InvalidMPrn+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_InvalidMPrn)?"Pass":"Fail");
					break;
				case 2:
					Report.updateTestLog("Expected Result: "+errormsg.Smr_SpecialCharWithAlphaMprn+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_SpecialCharWithAlphaMprn)?"Pass":"Fail");
					break; 
				case 3:
					Report.updateTestLog("Expected Result: "+errormsg.Smr_MoreThan21Charac+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_MoreThan21Charac)?"Pass":"Fail");
					break;
				case 4:
					Report.updateTestLog("Expected Result: "+errormsg.Smr_LessThanSixCharacter+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_LessThanSixCharacter)?"Pass":"Fail");
					break;
				case 5:
					Report.updateTestLog("Expected Result: "+errormsg.Smr_MoreThanSixCharacter+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_MoreThanSixCharacter)?"Pass":"Fail");
					break; 
				}
			}    	
		}
	
		public void verifyDataThroughQTP(SMRAccountDetails smrProfile){
	
			searchBy(smrProfile);
			String readDateFromApp=getActualReadDate();
			String readValueFromApp=getPreviousMeterRead();
			String addressFromApp=getSiteAddress();
			String meterSerialNumberFromApp=getMeterSerialNumber();
			String strRetreiveEmailQry="select BUSINESS_PARTNER_ORG_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
					" where email='emailid'";
			String query=strRetreiveEmailQry.replace("emailid",smrProfile.getEmail().toLowerCase());
			if(new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query).equals(null)){
				Report.updateTestLog("BPORGNUMBER is null for the email"+smrProfile.getEmail(), "Fail");
				return;
			}
			String bpOrgNumber1=new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query);
			System.out.println("RUN QTP"+bpOrgNumber1);
			String strRetreiveEmailQry1="select CONTRACT_ACCOUNT_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
					" where email='emailid'";
			String query1=strRetreiveEmailQry1.replace("emailid",smrProfile.getEmail().toLowerCase());
			if(new OnlineDBConnector().getColumn("CONTRACT_ACCOUNT_NUMBER", query1).equals(null)){
				Report.updateTestLog("CONTRACT_ACCOUNT_NUMBER is null for the email"+smrProfile.getEmail(), "Fail");
				return;
			}
			String accountnumber=new OnlineDBConnector().getColumn("CONTRACT_ACCOUNT_NUMBER", query1);
			System.out.println("Accountnumber is"+accountnumber);  
			String bpOrgNumber=bpOrgNumber1+"-"+accountnumber;
			RunQTP runQTP = new RunQTP();
			runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\SubmitMeterRead_Slingshot.vbs", bpOrgNumber);
	
			browser.wait(15000);
			String bankDetails = null;
			try {
				File file1 = new File("C:\\SAPData\\SubmitMeterReadTest.txt");
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
	
				String readdate = br.readLine();
				if(readdate.contains("LastMeterReadingDate")&& readdate.contains(":") ){
					String[] read=readdate.split(":");
					String readDate1=read[1];
					DateTimeFormatter parser = DateTimeFormat.forPattern("dd.MM.yyyy");
					DateTime readate = parser.parseDateTime(readDate1);
					String readDate=readate.toString(TIMESTAMP_FORMATTER);
	
					if(readDateFromApp.trim().equals(readDate)){
						Report.updateTestLog("LastMeterReadingDate in Application:"+readDateFromApp+"LastMeterReadingDate in ISU"+readDate, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadingDate in Application:"+readDateFromApp+"LastMeterReadingDate in ISU"+readDate, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadingDate is not written in the text file", "Fail");            	
				}
	
				String lastMeterReadValue = br.readLine();
				if(lastMeterReadValue.contains("LastMeterReadValue")&& readdate.contains(":")){
					String[] lastmeterread=lastMeterReadValue.split(":");
					String lastread=lastmeterread[1];
					if(lastread.trim().equals(readValueFromApp.trim())){
						Report.updateTestLog("LastMeterReadValue in Application"+readValueFromApp.trim()+"LastMeterReadValue in ISU :"+lastMeterReadValue, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadValue in Application"+readValueFromApp.trim()+"LastMeterReadValue in ISU :"+lastMeterReadValue, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");            	
				}
				String meterSerialNumber = br.readLine();
				if(meterSerialNumber.contains("MeterSerialNumber")&& readdate.contains(":")){
					String[] meterserial=meterSerialNumber.split(":");
					String meterRea=meterserial[1];
					if(meterRea==meterSerialNumberFromApp){
						Report.updateTestLog("MeterSerialNumber in Application"+meterSerialNumberFromApp.trim()+"MeterSerialNumber in ISU :"+meterRea, "Pass");
					}else{
						Report.updateTestLog("MeterSerialNumber in Application"+meterSerialNumberFromApp.trim()+"MeterSerialNumber in ISU :"+meterRea, "Fail");
					}
				}else{
					Report.updateTestLog("MeterSerialNumber is not written in the text file", "Fail");            	
				}
				String siteAddress = br.readLine();
				if(siteAddress.contains("Site Address")&& readdate.contains(":")){
					String[] site=siteAddress.split(":");
					String siteAdd=site[1];
					if(siteAdd.trim().equals(addressFromApp.trim())){
						Report.updateTestLog("SiteAddress in Application"+addressFromApp.trim()+"SiteAddress in ISU :"+siteAdd, "Pass");
					}else{
						Report.updateTestLog("SiteAddress in Application"+addressFromApp.trim()+"SiteAddress in ISU :"+siteAdd, "Fail");
					}
				}else{
					Report.updateTestLog("SiteAddress is not written in the text file", "Fail");            	
				}
	
				br.close();
			}catch (IOException e) {
				System.out.println("bad"+e);
				Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
			}
	
		}
		public String getMeterSerialNumber(){
	
			String meterSerialNumber = "";
			try{	
				meterSerialNumber = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialMeterSerialNumber"));
				System.out.println("MeterSerialNumber   "+meterSerialNumber);
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return meterSerialNumber.trim();
		}
		public String getSiteAddress(){
	
			String siteAddress = "";
			try{	
				siteAddress = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteAddress"));
				System.out.println("SiteAddress   "+siteAddress);
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return siteAddress;
		}
		public void getImpausibleErrorMsg(){
			getWaitTime();
			String errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSmrErrorMessage1"));
			Report.updateTestLog("Expected Result: "+errormsg.Smr_ImplausibleErrorMsg+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Smr_ImplausibleErrorMsg)?"Pass":"Fail");
	
		}
	
		public void verifyLEADTableForAnonymous(SMRAccountDetails smrProfile,String meterReadValue){
			OnlineDBConnector dbfunctions = new OnlineDBConnector();
			String sysDate = dbfunctions.DBsysdateDdmmyyhhmi(); 
			String[] strContent= dbfunctions.getSMRLogs(sysDate);
			for(int i=0;i<strContent.length;i++){
				if(strContent[i].contains(smrProfile.getEmail())&&strContent[i].contains(smrProfile.getFirstName())&&strContent[i].contains(meterReadValue)){
					System.out.println(strContent[i]);
					Report.updateTestLog("Entry made in online DB as expected for anonymous SMR. The Entry is "+strContent[i], "PASS");
					break;
				}
				else{
					Report.updateTestLog("Entry made in online DB as expected for anonymous SMR. The Entry is "+strContent[i], "FAIL");
				}
			}
		}
		public void verifyBackToYourAccountLink(){
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ConfirmationPageTitle"));
			verifyAndClickWithLinkText(pageProperties.getProperty("SubmitMeterReadPage.BackToYourAccount"), "Back to your account");
			if(browser.isTextPresent("Manage Account"))
			{
				Report.updateTestLog("Customer navigates to 'Manage Account' page as expected while clicking 'Back to your account' link in confirmation page", "PASS");
			}
			else{
				Report.updateTestLog("Customer navigates to 'Manage Account' page as expected", "FAIL");
			}
		}
	
		public void verifySAPForAnonymousSAPCustomer(SMRAccountDetails smrProfile){
	
			String readDateFromApp=getActualReadDate();
			String readValueFromApp=getPreviousMeterRead();
			String strRetreiveEmailQry="select BUSINESS_PARTNER_ORG_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
					" where email='emailid'";
			String query=strRetreiveEmailQry.replace("emailid",smrProfile.getEmail().toLowerCase());
			if(new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query).equals(null)){
				Report.updateTestLog("BPORGNUMBER is null for the email"+smrProfile.getEmail(), "Fail");
				return;
			}
			String bpOrgNumber1=new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query);
			System.out.println("RUN QTP"+bpOrgNumber1);
			String strRetreiveEmailQry1="select CONTRACT_ACCOUNT_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
					" where email='emailid'";
			String query1=strRetreiveEmailQry1.replace("emailid",smrProfile.getEmail().toLowerCase());
			if(new OnlineDBConnector().getColumn("CONTRACT_ACCOUNT_NUMBER", query1).equals(null)){
				Report.updateTestLog("CONTRACT_ACCOUNT_NUMBER is null for the email"+smrProfile.getEmail(), "Fail");
				return;
			}
			String accountnumber=new OnlineDBConnector().getColumn("CONTRACT_ACCOUNT_NUMBER", query1);
			System.out.println("Accountnumber is"+accountnumber);  
			String bpOrgNumber=bpOrgNumber1+"-"+accountnumber;
			RunQTP runQTP = new RunQTP();
			runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\SubmitMeterRead_Slingshot.vbs", bpOrgNumber);
	
			browser.wait(15000);
			try {
				File file1 = new File("C:\\SAPData\\SubmitMeterReadTest.txt");
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
	
				String readdate = br.readLine();
				if(readdate.contains("LastMeterReadingDate")&& readdate.contains(":") ){
					String[] read=readdate.split(":");
					String readDate=read[1];
					if(readDateFromApp.trim().equals(readDate)){
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadingDate is not written in the text file", "Fail");            	
				}
	
				String lastMeterReadValue = br.readLine();
				if(lastMeterReadValue.contains("LastMeterReadValue")&& readdate.contains(":")){
					String[] lastmeterread=lastMeterReadValue.split(":");
					String lastread=lastmeterread[1];
					if(lastread.trim().equals(readValueFromApp.trim())){
						Report.updateTestLog("LastMeterReadValue in Application "+readValueFromApp.trim()+"LastMeterReadValue in ISU : "+lastMeterReadValue, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadValue in Application "+readValueFromApp.trim()+"LastMeterReadValue in ISU : "+lastMeterReadValue, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");            	
				}		
				br.close();
			}catch (IOException e) {
				Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
			}
		}
	
		public void verifyAccountSummaryTitle(){
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.AccountSummaryTitle"));
		}
	
		public void clickCancelLink(){
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CancelButtonGlobalSmrPage"), "Cancel");
		}
		public void clickSMRLink(SMRAccountDetails smrProfile){
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitmeterReadLink").replace("AccountNumber",smrProfile.getAccountNumber()), "Submit meter read");
			browser.wait(getWaitTime());
			if(browser.getTitle().equalsIgnoreCase("Submit meter read")){
				System.out.println("SMR page displayed");
			}
			else{
				System.out.println("SMR page not displayed");
			}
		}
		public void verifySearchBy(SMRAccountDetails smrProfile){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterRead.SearchBy"))){
				verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterRead.SearchBy"), "Search By", "Meter Point Reference Number");
				verifyAndInputById(pageProperties.getProperty("SubmitMeterRead.KeywordToSearch"), "Keyword", smrProfile.getMprn());
				verifyAndClick(pageProperties.getProperty("SubmitMeterRead.SearchButton"), "Search Button");
			}
		}
		public void clickManageAccountLink(SMRAccountDetails smrProfile){
			//verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.manageAccountLink").replace("ACCOUNTNUMBER",smrProfile.getAccountNumber()), "Manage account");
			//if(browser.getTitle().equalsIgnoreCase("Account summary")){
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitmeterReadLinkInLHN"), "Submit meter read");
				browser.wait(getWaitTime());
			//}
			//else{
				//System.out.println("Account summary page not displayed");
			//}
		}
		public void verifyInactiveAccount(){
			Report.updateTestLog("Submit meter read page not displayed for Inactive account", browser.getTitle().equalsIgnoreCase("Submit meter read")?"PASS":"FAIL");
	
		}
	public void openSMRPageUrlPage(){
			browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("SubmitMeterReadPage.AnonymousSMRUrl"));
			browser.wait(getWaitTime());
		  /* verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricityRead"),"Click Electricity read Get Started");
	        browser.wait(getWaitTime());*/
		}
		
		public void clickElectricityGetStartedLink()
		{
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricityRead"),"Click Electricity read Get Started");
		     browser.wait(getWaitTime());
		}
		
		public void verifyElectricityYourDetailsTitle(){
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityReadYourDetilasTitle"));
			System.out.print(browser.getTitle());
		
		}
		public void verifyGasYourDetailsTitle(){
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.GasPageTitle"));
			System.out.print(browser.getTitle());
		
		}
		
		
		//Electricity read page confirmation title
		public void verifyElectricityReadConfirmationTitle(){
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityReadconfirmatioTitle"));
		
		}
		// home meter reading page
		public void verifySubmitMeterReadPageTitle()
		{
			verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.MeterReadPageTitle"));
		}
		public void verifyMeterReadConfirmationTitle(){
		
			verifyPageTitle("Your meter readings are complete");
			browser.wait(6000);
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.logout"), "logout button");
		
		}
		
		
		 public void verifyElectricWhyWeNeedThisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricEmailwhyweneedthis"), "Why we need this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricEmailwhyweneedthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricEmailwhyweneedthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricEmailOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricEmailOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }  
		  	   }
		 
		 public void verifyElectricAcctnoWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoWherecanifindthisContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Where can i find this is displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 public void verifyElectricMeterPointWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterpointWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterpointWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterpointWherecanifindthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterpointOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterpointOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 public void verifyElectricMeterIDWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterserialnoWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterserialnoWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterserialnoWherecanifindthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterserialOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricMeterserialOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 public void verifyGasWhyWeNeedThisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasEmailwhyweneedthis"), "Why we need this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasEmailwhyweneedthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GasEmailwhyweneedthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasEmailOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasEmailOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 
		 public void verifyGasAcctnoWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasAcctnoWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasAcctnoWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GasAcctnoWherecanifindthisContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Where can i find this is displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasAcctnoOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasAcctnoOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 public void verifyGasMeterPointWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterpointWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterpointWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterpointWherecanifindthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterpointOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterpointOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 public void verifyGasMeterIDWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterserialnoWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterserialnoWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterserialnoWherecanifindthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterserialOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterserialOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 public void verifyElectricsitenoWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricSitenoWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricSitenoWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricSitenoWherecanifindthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricSitenoWherecanifindthisOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricSitenoWherecanifindthisOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 
		 public void verifyGassitenoWhereCanIfindthisLink(){	   
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterReadSitenoWherecanifindthis"), "Where can i find this?");
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterReadSitenoWherecanifindthisOverlay"))){
				   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterReadSitenoWherecanifindthisOverlayContent"));
				   System.out.println("overlayContent: "+overlayContent);
				   Report.updateTestLog("Why we need this overlay displayed with below content: "+overlayContent, "PASS");
				   	   }
			   else{
				   Report.updateTestLog("Please check Why we need this link " , "WARN");
			   }
			   
			   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterReadSitenoOverlayClose"))){
			   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasMeterReadSitenoOverlayClose"), "Overlay close button");
			   }
			   else{		 
				   RobotSendKeys.typeenter();
				   browser.wait(getWaitTime());
				   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
				   }
			      
		  
		   }
		 
		 public void ClickBackYourDetailsButton()
		 {
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BackYourDetailsbutton")," Click Electric Your Details Button");
				
		 }
		 
		 public void VerifyElectricityMeterReadingPageTitle()
		 {
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityMeterReadPageTitle"));
			
		 }
		 
		 public void VerifyGMeterReadingPageTitle()
		 {
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.GasMeterReadPageTitle"));
			
		 }
	
		 
		 
		public void enterSAPElelctricCustomerData(SMRAccountDetails smrProfile){
				//verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.ElectricityPageTitle"));
				verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", smrProfile.getTitle());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", smrProfile.getFirstName());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", smrProfile.getLastName());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email address",smrProfile.getEmail());
				//verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityPageTitle"));
				getAccountAndMeterSerialNumberforSAPElelctricCustomer(smrProfile);
			}
		 public void getAccountAndMeterSerialNumberforSAPElelctricCustomer(SMRAccountDetails smrProfile){
				Random random = new Random();
				int accountNum = 601268783;
				String meterSerialNumber = "E12Z053318";
				String accountNumber = String.valueOf(accountNum);
				//String meterSerialNumber = String.valueOf(meterSerialNum);	
				//String meterSerialNumber = "E12Z053318";
				
				System.out.println(" accountNumber "+accountNumber+" meterSerialNumber "+meterSerialNumber);
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", smrProfile.getAccountNumber());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterSerialNumber"), "Meter Serial Number", smrProfile.getMeterSerialNumber());		
				int mpan = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.NumberOfInputMpan"));
				String mpanActual = smrProfile.getMpan();
				System.out.println(mpanActual.length());
				String mpan1 = mpanActual.substring(0, mpanActual.length()-11);
				String mpan2 = mpanActual.substring(mpanActual.length()-11, mpanActual.length()-7);
				String mpan3 = mpanActual.substring(mpanActual.length()-7, mpanActual.length()-3);
				String mpan4 = mpanActual.substring(mpanActual.length()-3, mpanActual.length());
				String[] mpanPretext = {"22","222","222",mpan1,mpan2,mpan3,mpan4};
				System.out.println(mpanPretext);
				for(int i = 0; i<=mpan-1; i++){
					verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MpanTextBox")+(i+1)+"']", "Mpan-"+i, mpanPretext[i]);
					System.out.println(mpanPretext[i]);
				}
				clickContinueButton();
			}
		 public void enterGasValidData_Anonymous(SMRAccountDetails smrProfile){
			 		  
				verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.PageTitle"));
				verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", smrProfile.getTitle());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", smrProfile.getFirstName());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", smrProfile.getLastName());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email address", smrProfile.getEmail());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", smrProfile.getAccountNumber());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Mprn"), "MPRN", smrProfile.getMprn());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterSerialNumber"), "Meter Serial Number", smrProfile.getMeterSerialNumber());
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");
				browser.wait(getWaitTime());		
	
			}
		 
		 public void verifySubmitMeterreadLandingPageNavigationLinks()
		 {	
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AtHomeLink")," Click At Home Link");
			 browser.browserBack();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CorporateLink")," Click Corporate Link");
			 browser.browserBack();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BusinessLink")," Click Business Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.BusinessPageTitle"));
			 browser.browserBack();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.YourAccountLink")," Click YourAccount Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.YourAccountPageTitle"));
			 browser.browserBack();		 
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.HowtoReadYourElecMeter")," Click HowtoReadYourElecMeter");		 
			 browser.swithnewwindow_getTitle();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.HowtoReadYourGasMeter")," Click HowtoReadYourGasMeter");
			 browser.swithnewwindow_getTitle();
		}
		 
		 public void verifyElectricNavigationLinks()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BusinessLink"),"Click Business Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.BusinessPageTitle"));
			 verifybrowsebackandwait();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.YourAccountLink")," Click YourAccount Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.YourAccountPageTitle"));
			  verifybrowsebackandwait();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitMeterReadLinks")," Click Submit Meter Read link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.MeterReadPageTitle"));		 		 
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.howdoireadmymeterLink")," Click How do i read My Meter Link");
			 browser.swithnewwindow_getTitle();
		 }
		 
		 	 
		 public void verifyGasNavigationLinks()
		 
		  {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BusinessLink"),"Click Business Link");
			  verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.BusinessPageTitle"));
			 verifybrowsebackandwait();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.YourAccountLink")," Click YourAccount Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.YourAccountPageTitle"));
			 verifybrowsebackandwait();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitMeterReadLinks")," Click Submit Meter Read link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.MeterReadPageTitle"));
			 verifybrowsebackandwait();		 
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.howdoireadmymeterLink")," Click How do i read My Meter Link");	 
			 browser.swithnewwindow_getTitle();	 		 
		 }
		 public void uploadMeterNavigationlinks()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AtHomeLink")," Click At Home Link");
			 browser.browserBack();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CorporateLink")," Click Corporate Link");
			 browser.browserBack();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BusinessLink")," Click Business Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.BusinessPageTitle"));
			 browser.browserBack();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.YourAccountLink")," Click YourAccount Link");
			 verifyPageTitle("Account overview");
			 browser.browserBack();
		 }
		 public  void verifyGlobalNavigationLink()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BusinessLink"),"Click Business Link");
			  verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.BusinessPageTitle"));
			 verifybrowsebackandwait();
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.YourAccountLink")," Click YourAccount Link");
			 verifyPageTitle("Account overview");
		 }
		 
		 
		 public void verifybrowsebackandwait()
		 {
			 browser.browserBack();
			 browser.wait(4000);
		 }
		 
		
		 public void verifyGasReadConfirmationPageTitle()
		 	 {
			 browser.wait(getWaitTime());
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.GasConfirmationPageTitle"));
				
		    }
		/* public void verifyElectricConfirmations()
		 {
			 browser.wait(getWaitTime());
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricConfirmationPageTitle"));
				
		}*/
		 /*public void verifyMeterReadingEmailConfirmation()
		 {
			
				if(browser.isTextVisible(pageProperties.getProperty("SubmitMeterReadPage.EmailConfirmation1")))
						{
								Report.updateTestLog("Submitted Meter read details are sent to Your Email", "PASS");
						}
					else
						{
								Report.updateTestLog("Submitted Meter read details are not sent to Your Email", "Fail");
						}
			 	
			 	
	   }
		 */
		 // MPN Supply error message validatation
		 
		 public void MPANErrorMsgValidation(SMRAccountDetails smrProfile) 
		 {
	   		      	String mpanNumber[]={"12345678!@222","1234567DDDd11","123451","1245534133204"," "};
					for(int i = 0; i < mpanNumber.length; i++){
						switch (i){    	 	   
						
						case 0:enterElectricCustomerData(smrProfile,mpanNumber[i]);  
						clickContinueButton();
						errorMessageComparisons(SlingshotErrorMessages.SlingShot_SMR_InvalidMpanNumber);
						break;
						case 1:enterElectricCustomerData(smrProfile,mpanNumber[i]);   
						clickContinueButton();
						errorMessageComparisons(SlingshotErrorMessages.SlingShot_SMR_InvalidMpanNumber);
						break;
						case 2:enterElectricCustomerData(smrProfile,mpanNumber[i]);  
						clickContinueButton();
						errorMessageComparisons(SlingshotErrorMessages.SlingShot_SMR_InvalidMpanNumber);
						break;
						case 3:enterElectricCustomerData(smrProfile,mpanNumber[i]);  
						clickContinueButton();
						errorMessageComparisons(SlingshotErrorMessages.SlingShot_SMR_MpanNotBelongsToBP);
						break;
						case 4:enterElectricCustomerData(smrProfile,mpanNumber[i]);  
						clickContinueButton();
						errorMessageComparisons(SlingshotErrorMessages.SlingShot_SMR_EmptyMpanNumber);
						break;
						
						}
					}
	   		      	
					
				}
		//  Capture the error message displayed and verify the actual and error message displayed by making a call to the verifyErrorMessage method
			public void errorMessageComparisons(final String expectedErrorMsg) {
				try{
					final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSmrErrorMessage2"));
					verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);}
				catch(Exception e){
					Report.updateTestLog("Exception occured"+e, "FAIL");
				}
			}
			
			public void enterElectricCustomerData(SMRAccountDetails smrProfile,String mpan1){
				
				verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.ElectricityPageTitle"));
				verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", smrProfile.getTitle());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", smrProfile.getFirstName());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", smrProfile.getLastName());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email address",smrProfile.getEmail());
									
				getAcctAndMeterSerialNumberforSAPElelctricCustomer(mpan1);
				
			}
		 public void getAcctAndMeterSerialNumberforSAPElelctricCustomer(String smrProfile){
				Random random = new Random();
				int accountNum = 601268783;
				String meterSerialNumber = "E12Z053318";
				String Mpan1="12345678!@222";
				String Mpan2="1234567DDDd11";
				String MPan3 ="123451";
				String Mpan4="1245534133204";
				String Mpan5=" ";
				String accountNumber = String.valueOf(accountNum);
				System.out.println(" accountNumber "+accountNumber+" meterSerialNumber "+meterSerialNumber);
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "ContractAccountNumber", accountNumber);
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterSerialNumber"), "Meter Serial Number", meterSerialNumber);		
				int mpan = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.NumberOfInputMpan"));
				String mpanActual = smrProfile;
				System.out.println(mpanActual.length());
				
				 if(mpanActual.trim().contains(Mpan1.trim())|| mpanActual.trim().contains(Mpan2.trim())|| mpanActual.trim().contains(Mpan4.trim()))
				{
					 String mpan1 = mpanActual.substring(0, mpanActual.length()-11);
						String mpan2 = mpanActual.substring(mpanActual.length()-11, mpanActual.length()-7);
						String mpan3 = mpanActual.substring(mpanActual.length()-7, mpanActual.length()-3);
						String mpan4 = mpanActual.substring(mpanActual.length()-3, mpanActual.length());
						String[] mpanPretext = {"22","222","222",mpan1,mpan2,mpan3,mpan4};
						System.out.println(mpanPretext);
						for(int i = 0; i<=mpan-1; i++){
							verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MpanTextBox")+(i+1)+"']", "Mpan-"+i, mpanPretext[i]);
							System.out.println(mpanPretext[i]);
						}
				}
				
				else if(mpanActual.trim().contains(MPan3.trim())) 
				{			
					String mpan1 = mpanActual.substring(0, mpanActual.length()-4);
					String mpan2 = mpanActual.substring(mpanActual.length()-4, mpanActual.length()-0);
					String mpan3 = "";
					String mpan4 = "";
					String[] mpanPretext = {"22","222","222",mpan1,mpan2,mpan3,mpan4};
					System.out.println(mpanPretext);
					for(int i = 0; i<=mpan-1; i++){
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MpanTextBox")+(i+1)+"']", "Mpan-"+i, mpanPretext[i]);
						System.out.println(mpanPretext[i]);
					}
					}
				else if (mpanActual.trim().contains(Mpan5.trim())) 
			{
				String mpan1 = " ";
				String mpan2 = " ";
				String mpan3 = " ";
				String mpan4 = " ";
				String[] mpanPretext = {" "," "," ",mpan1,mpan2,mpan3,mpan4};
				System.out.println(mpanPretext);
				for(int i = 0; i<=mpan-1; i++){
					verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.MpanTextBox")+(i+1)+"']", "Mpan-"+i, mpanPretext[i]);
					System.out.println(mpanPretext[i]);
				}
			} 
						
				
			}
		 
		 public void ValidateDayReadValueforElectricityCustomer()
		 
		 {			
			 
			 Random random = new Random();
				String dayValue[] = {" ","99##$","ddfff2"};
				String siteNumberValue = "239176";
											  
				
				for(int i = 0; i < dayValue.length; i++){
					switch (i){    	 	   
					case 0:
					verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
					verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Day"), "Day", dayValue[i]);  
					clickContinueButton();
					errorMessageComparisondata(SlingshotErrorMessages.Slingshot_SMR_EmptyDayReadNumber);
						
					break;
					case 1:
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Day"), "Day", dayValue[i]);  
						clickContinueButton();
						errorMessageComparisondata(SlingshotErrorMessages.SlingShot_SMR_InvalidDayReadNumber);
					case 2:
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Day"), "Day", dayValue[i]);  
						clickContinueButton();
						errorMessageComparisondata(SlingshotErrorMessages.SlingShot_SMR_InvalidDayReadNumber);
					break;
				
					}
				}
				
			}
		 
	public void validateGasreadingFiledErrormsg(SMRAccountDetails smrProfile)
		 
		 {			
			 enterValidData(smrProfile.getTitle(), smrProfile.getFirstName(), smrProfile.getLastName(), smrProfile.getEmail(), smrProfile.getAccountNumber(),smrProfile.getMprn(),smrProfile.getMeterSerialNumber());
			
			 Random random = new Random();
				String dayValue[] = {" ","99##$","ddfff2"};
				String siteNumberValue = "239176";										  		
				for(int i = 0; i < dayValue.length; i++){
					switch (i){    	 	   
					case 0:
					verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
					verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DayReadGas"), "Day", dayValue[i]);  
					clickContinueButton();
					errorMessageComparisondata(SlingshotErrorMessages.Slingshot_SMR_EmptyReading);
						
					break;
					case 1:
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DayReadGas"), "Day", dayValue[i]);  
						clickContinueButton();
						errorMessageComparisondata(SlingshotErrorMessages.SlingShot_SMR_InvalidReadingNumber);
					case 2:
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"), "Site number", siteNumberValue);
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DayReadGas"), "Day", dayValue[i]);  
						clickContinueButton();
						errorMessageComparisondata(SlingshotErrorMessages.SlingShot_SMR_InvalidReadingNumber);
					break;
				
					}
				}
				
			}
		 public void errorMessageComparisondata(final String expectedErrorMsg) {
				try{
					final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSmrErrorMessage2"));
					verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);}
				catch(Exception e){
					Report.updateTestLog("Exception occured"+e, "FAIL");
				}
			}
		 
		 public void yourdetailsCancelButton()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BackLinkGlobalSmrPage"),"Click the Back Link");
		 }
		 public void clickSubmitMeterReadLink()
		 {
			 browser.wait(1000);
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterRead.SmrLinkGlobal"),"Click Submit Meter Read Link");
		 }
		 
		/* public void clickUploadMeterReadLinks()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageLink"),"Click Upload Meter Read Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageTitle"));
		 }*/
		 
		 
		 public void clickUploadMeterReadLink()
		 {		 
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageLink"),"Click Upload Meter Read Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageTitle"));	
			// verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BrowseButton"),"Click Browser Button");		
			 browser.clickWithXpath(".//*[@id='fileUpload-button']/span/span");
			 browser.wait(2000);
			 browser.clickWithXpath(".//*[@id='input-File']"); 
			 browser.wait(2000);
			 //browser.clickWithXpath("//p/span/span[contains(@class,'show-browse-button-wrapper')]/input");
			 //browser.inputByXpath("//p/span/span[contains(@class,'show-browse-button-wrapper')]/input","asdasd");
			 //	browser.clickbutton("input-File");
			// 	 browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadFileManual"), "D:\\ReadPromptTemplate.xls");	
			   /*browser.wait(6000);
			   RobotSendKeys.type("D:\\ReadPromptTemplate.xls");
		       browser.wait(5000);
		       RobotSendKeys.typeenter();
		       browser.wait(3000);*/
		     //  verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitButtonOfUpload"), "Submit button after browsing file");
		      /* if(browser.isTextPresent("Meter read spreadsheet submitted successfully"))
				{
					Report.updateTestLog("File Uploaded successfully", "Pass");
				}
				else{
					Report.updateTestLog("File Uploaded successfully", "Fail");
				}
			*/
		 }
		 public void uploadcancelbutton()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageLink"),"Click Upload Meter Read Link");			
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BrowseButton"),"Click Browser Button");		
			 
		       verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Cancelbutton"), "Click cancel button");
		 }
		// xpath needs to be verified and confirm with the your account and sumbit meter read needs to be addes
		 public void verifyUploadMeterReadNavigationLinks()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ChattoanAgentOnlineLink"),"Click Chat to an Agent Online Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ChattoanAgentOnlinePageTitle"));
			 browser.browserBack();
			 
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ChattoanAgentOnlineLink"),"Click Browse Link");
		 }
		 
		 public void upLoadCanceLink()
		 {
			 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterCancelReadPageLink"),"Click Upload Meter Cancel Read Link");
			 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageTitle"));
			 
		 }
		 public void verifyDontRemindEmailCheckBox()
		 {
			 verifyAndSelectCheckBoxByXpath("SubmitMeterReadPage.DontRemindEmailCheckbox", "Click Dont Remind Email Checkbox option");
		 }
		 public void verifyLeadTable(SMRAccountDetails smrProfile){
				OnlineDBConnector dbFunctions = new OnlineDBConnector();
				String date=dbFunctions.DBsysdateDdmmyyhhmi();
				String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"success");
				String data = dbFunctions.getAuditType(auditType[0]);	
				Report.updateTestLog("Successfully Email Sent to Customer And Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_ANONYMOUS_POST_SMR_SUCCESS")?"PASS":"FAIL");
				}
		 public void verifyLeadTable1(SMRAccountDetails smrProfile){
				OnlineDBConnector dbFunctions = new OnlineDBConnector();
				String date=dbFunctions.DBsysdateDdmmyyhhmi();
				String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"success");
				String data = dbFunctions.getAuditType(auditType[0]);	
				Report.updateTestLog("Successfully Email Sent to Customer And Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_POST_SMR_SUCCESS")?"PASS":"FAIL");
				}
		 public void verifySearchDropDown(){
				String[] searchOptions = {"Please select","Account number","Supply number","Meter point reference","Site postcode"};    								
				ArrayList<String> lstSearchOptions1 = browser.getFromDropBox("id", pageProperties.getProperty("AccountOverviewPage.SearchDropDownId"));
				int flg = 0;
				for(int i = 0; i<searchOptions.length;i++){    		
					if(lstSearchOptions1.get(i).equalsIgnoreCase(searchOptions[i]))
					{
						flg = flg+1;
						System.out.println(flg);
						/*Report.updateTestLog("Search option "+lstSearchOptions1.get(i), "PASS");
		    			System.out.println(lstSearchOptions1.get(i));*/
					}
					else{
						System.out.println(flg);
						//Report.updateTestLog("Search option is not displayed with the following options: "+lstSearchOptions1.get(i), "FAIL");
						System.out.println(lstSearchOptions1.get(i));
					}
				}    	
				Report.updateTestLog("Search option is displayed with the following options: "+lstSearchOptions1, (flg==4)?"PASS":"FAIL");
			}
		 
		 public void selectContractAccountAndVerifySearchFunctionality(UserProfile userProfile){
				selectSearchByAndVerify("Account number",userProfile.getAccNumber());
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"))){
					int numberOfAcounts =   browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountOverviewPage.AccountListNumber"));
					System.out.println("Number of account----> "+numberOfAcounts);
					Report.updateTestLog("Number of accounts displayed: "+numberOfAcounts, "PASS");
					if(browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber")).contains(userProfile.getAccNumber())){
						Report.updateTestLog("Filter made for corresponding 'Account Number' : "+userProfile.getAccNumber(), "PASS");
					}
					else{
						Report.updateTestLog("Filter made for corresponding 'Account Number' : "+userProfile.getAccNumber(), "FAIL");
					}
				}
			}
			public void selectSearchByAndVerify(String strSearchByOption,String strKeyword){
				verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.SearchDropDownId"), "Search by", strSearchByOption);
				browser.wait(getWaitTime());
				verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Keyword"), "Keyword", strKeyword);
				browser.wait(getWaitTime());
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SearchButton"), "Search button");
				browser.wait(getWaitTime());
				String jquery=("$(document).ready(function(){$('.accordion-head').trigger('click');});");        
				browser.executeScript(jquery);
				getWaitTime();
	
			}
			public void searchByAccountnumber(SMRAccountDetails smrProfile){
				selectSearchByAndVerify("Account number",smrProfile.getsmraccount());	
				System.out.println("555555555556666666666666666666");
			}
			public void searchBySSupplynumber(SMRAccountDetails smrProfile){
				selectSearchByAndVerify("Supply number",smrProfile.getMpan());
				}
			public void searchByMeterpointreference(SMRAccountDetails smrProfile){
				selectSearchByAndVerify("Meter point reference",smrProfile.getMprn());
				}
			public void searchBySitepostcode(SMRAccountDetails smrProfile){
				selectSearchByAndVerify("Site postcode",smrProfile.getsitePostcode());
				}
			
	
			public void searchByInvalidata(){
			//	String SearchBy []={"Account number","Supply number","Meter point reference","Site postcode"};
				String SearchByAcctnoTerm[]={" ","600447497ab","600447497*&%","ab60044111"};
				String SearchBySupplynoTerm[]={" ","600447497ab","600447497*&%","323890081111"};
				String SearchByMprnTerm[]={" ","32389008ab","3238920*&%","323890081111"};
				String SearchBySitepostcodeTerm[]={" ","E12 AAF","E12 111","AF#$$"};
				
			   		      
			   		      		for(int j=0;j<=3;j++)
			   		      		{
			   		      				switch(j)
			   		      				{
			   		      				case 0:
			   		      				 	   selectSearchByAndVerify("Account number",SearchByAcctnoTerm[j]);
			   		      					   errorMsgComparisonSearchTerm(SlingshotErrorMessages.SearchField_Item_EmptyError);
			   		      					   selectSearchByAndVerify("Supply number",SearchBySupplynoTerm[j]);
			   		      					   errorMsgComparisonSearchTerm(SlingshotErrorMessages.SearchField_Item_EmptyError);
			   		      					   selectSearchByAndVerify("Meter point reference",SearchByMprnTerm[j]);
			   		      					   errorMsgComparisonSearchTerm(SlingshotErrorMessages.SearchField_Item_EmptyError);
			   		      					   selectSearchByAndVerify("Site postcode",SearchBySitepostcodeTerm[j]);
											   errorMsgComparisonSearchTerm(SlingshotErrorMessages.SearchField_Item_EmptyError);
											   
			   		      			break;
			   		      			case 1:
			   		      			case 2:
					   		      			selectSearchByAndVerify("Account number",SearchByAcctnoTerm[j]);
			   		      				//	errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_Acctno_InvalidInput);
			   		      					selectSearchByAndVerify("Supply number",SearchBySupplynoTerm[j]);
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
			   		      					selectSearchByAndVerify("Meter point reference",SearchByMprnTerm[j]);
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
			   		      					selectSearchByAndVerify("Site postcode",SearchBySitepostcodeTerm[j]);					
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
	   		      					break;	   		      				
			   		      			
			   		      			case 3:
			   		      					selectSearchByAndVerify("Account number",SearchByAcctnoTerm[j]);
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
			   		      					selectSearchByAndVerify("Supply number",SearchBySupplynoTerm[j]);
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
			   		      					selectSearchByAndVerify("Meter point reference",SearchByMprnTerm[j]);
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
			   		      					selectSearchByAndVerify("Site postcode",SearchBySitepostcodeTerm[j]);					
			   		      					errorMsgComparisonSearchTerm(SlingshotErrorMessages.Slingshot_GSMR_InvalidInput);
			   		      					break;										
			   		      			   }
			   		      				   		      						
			   		      	 }
			}
			
			public void errorMsgComparisonSearchTerm(final String expectedErrorMsg) {
				try{
					final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSMRErrorMessages"));
					System.out.println("displayedErrormsg"+displayedErrorMsg);
					verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);}
				catch(Exception e){
					Report.updateTestLog("Exception occured"+e, "FAIL");
				}
			}
			
			public void verifySearchTitleErrorValidation(){
				
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SearchButton"), "Search Button Clicked");
				
					for(int i = 1; i <=2; i++){
					switch (i){    	 	   
					case 1:
					
					verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.SearchDropDownId"), "Search by", "Account number");
					clickContinueButton();
					String SearchField=browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSMRErrorMessages"));
				  	System.out.println("SearchField"+SearchField);
					errorMsgComparisonSearch(SlingshotErrorMessages.SearchField_EmptyError,SearchField);
					break;
					case 2:
						verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Keyword"), "Keyword", "12324566");
						clickContinueButton();
						String SearchTerm=browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalSMRErrorMessages"));
						System.out.println("SearchTerm"+SearchTerm);
						errorMsgComparisonSearch(SlingshotErrorMessages.SearchField_EmptyError,SearchTerm);
					break;				
					}
				}
		}
			
			public void errorMsgComparisonSearch(final String expectedErrorMsg, String SearchField) 
			{
			 verifyErrorMessage(SearchField, expectedErrorMsg);	
			}
			
			public void OverlayValidation()
			{
				
				AcctOverLay();
				sitePostcodeOverlay();
			}
			
			 public void AcctOverLay(){	   
				 	verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AccountNumberOverlay"), "Account Number Overlay Clicked");
				 	browser.wait(2000);
				    /*if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AccountNumberOverlayTitle"))){
					   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.AccountNumberOverlayTitle"));
					   System.out.println("overlayContent: "+overlayContent);
					   Report.updateTestLog("AccountNumber Overlay content: "+overlayContent, "PASS");
					   	   }
				   else{
					   Report.updateTestLog("Please check Why we need this link " , "WARN");
				   }*/
				   
				   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AccountNumberOverlayClose"))){
				   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AccountNumberOverlayClose"), "Overlay close button");
				   }
				   else{		 
					   RobotSendKeys.typeenter();
					   browser.wait(getWaitTime());
					   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
					   }		  
			     }
	
			 public void sitePostcodeOverlay(){	   
				 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.sitePostcoderOverlay"), "SitePost Code Overlay Clicked");
				    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.sitePostcodeOverlayTitle"))){
					   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.sitePostcodeOverlayTitle"));
					   System.out.println("overlayContent: "+overlayContent);
					   Report.updateTestLog("sitePostcodeOverlay content: "+overlayContent, "PASS");
					   	   }
				   else{
					   Report.updateTestLog("Please check Why we need this link " , "WARN");
				   }
				   
				   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.sitePostcodeOverlayClose"))){
				   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.sitePostcodeOverlayClose"), "Overlay close button");
				   }
				   else{		 
					   RobotSendKeys.typeenter();
					   browser.wait(getWaitTime());
					   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
					   }		  
			     }
			 
			 //have to update his as per new update of QC
			 public void mpnOverlay(){	   
				 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mpanOverlay"), "Mpn Overlay Clicked");
				    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mpnOverlayTitle"))){
					   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.mpnOverlayTitle"));
					   System.out.println("overlayContent: "+overlayContent);
					   Report.updateTestLog("sitePostcodeOverlay content: "+overlayContent, "PASS");
					   	   }
				   else{
					   Report.updateTestLog("Please check Why we need this link " , "WARN");
				   }
				   
				   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mpnOverlayClose"))){
				   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mpnOverlayClose"), "Overlay close button");
				   }
				   else{		 
					   RobotSendKeys.typeenter();
					   browser.wait(getWaitTime());
					   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
					   }		  
			     }
					 
			 public void mprnOverlay(){	   
				 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mprnOverlay"), "Mprn Overlay Clicked");
				    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mprnOverlayTitle"))){
					   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.mprnOverlayTitle"));
					   System.out.println("overlayContent: "+overlayContent);
					   Report.updateTestLog("sitePostcodeOverlay content: "+overlayContent, "PASS");
					   	   }
				   else{
					   Report.updateTestLog("Please check Why we need this link " , "WARN");
				   }
				   
				   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mprnOverlayClose"))){
				   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.mprnOverlayClose"), "Overlay close button");
				   }
				   else{		 
					   RobotSendKeys.typeenter();
					   browser.wait(getWaitTime());
					   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
					   }		  
			     }
			 public void Numberofmetersmorethanmaximum() {
					try{
						final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GloabalSMRerrorMsg"));
						verifyErrorMessage(displayedErrorMsg,SlingshotErrorMessages.Global_SMR_MaximumMeter_Error);}
					catch(Exception e){
						Report.updateTestLog("Exception occured"+e, "FAIL");
					}
				}
			
			 public void test1()
			 {
				    browser.open("https://10.224.70.18//business/meterread/submit-meter-read");
				    verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.switchtonewwindow"), "swithwindowclicked");			   	
				    browser.swithnewwindow_getTitle();
				    System.out.println("back to original"+browser.getTitle());					
				   
			 }
		public void submiMeterRead(SMRAccountDetails smrProfile)
		{
			System.out.println("i am in");
			System.out.println(pageProperties.getProperty("SubmitMeterReadPage.SubmitmeterReadManage").replace("ACCOUNTNUMBER", smrProfile.getAccountNumber()));
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitmeterReadManage").replace("ACCOUNTNUMBER", smrProfile.getAccountNumber()), "SMR link");
			browser.wait(5000);
		}
			 
			 public void downloadPDF()
				{
					   verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.DownBill"), "Download bill");
					   browser.wait(getWaitTime());
					   //String testPath;
					   //testPath = "";
					   RobotSendKeys.altS();
				       browser.wait(3000);
				       RobotSendKeys.typeenter();
				       browser.wait(3000);
				       Report.updateTestLog("Pdf file get downloaded by clicking enter", "PASS");
				  }
			 
			 public void getDialsCountAndSubmitIncorrectRead1(SMRAccountDetails smrProfile,String indicator){
	
					try{
						if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"))){
							int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
							System.out.println("dialsCount "+dialsCount);
							Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
							String previousMeterRead = getPreviousMeterRead();
							Report.updateTestLog("Previous meter read value is "+previousMeterRead, "PASS");
							smrProfile.setlastMeterRead(previousMeterRead);
							if(indicator.equalsIgnoreCase("Exceedvalue")){
								System.out.println("indicator value:"+indicator);
								previousMeterRead = currentMeterReadImplausible(dialsCount,previousMeterRead);
							}
							previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
							smrProfile.setafterSubmitMeterRead(previousMeterRead);
							Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
							System.out.println("previousMeterRead "+previousMeterRead);
	
							for (int i = 1;i<=dialsCount;i++){
								browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
								browser.wait(500);
							}
							this.currentMeterRead = previousMeterRead;
							System.out.println("currentMeterRead "+currentMeterRead);
							clickSubmitMeterRead();
							browser.wait(20000);
							browser.wait(20000);
						}
						else{
							Report.updateTestLog("Meter read already submitted for this account", "WARN");	
						}
					}
					catch(Exception e){
	
					}
				}
			 
			 //********************************************** sundar script********************************************************************************************
				public void clickManageAccountLink(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ManageAccountLink"), "Managae Account");
					browser.wait(getWaitTime());
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.AccountSummaryTitle"));
				}
				public void clickSubmitMeterReadlink(SMRAccountDetails smrProfile){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitMeterReadlink").replace("ACCOUNTNUMBER", smrProfile.getAccountNumber()), "Manage account link");
					browser.wait(getWaitTime());
				}
			   public void verifySmrPage(){
				   verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.PreviousMeterRead."), "Previous meter read");
				   verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualMeterRead"), "Actual meter read");
				   verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.MeterSerialNumber"), "Meter serial number");
				   verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Address"), "Address");   
				   
			   }
			   public void clickManageAccountLinkWithAccNo(SMRAccountDetails smrProfile){
				   browser.wait(5000);
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ManageAccountLinkWithAccNo_Img").replace("ACCOUNTNUMBER", smrProfile.getAccountNumber()), "Manage account link");
					browser.wait(getWaitTime());
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.AccountSummaryTitle"));
					
				}
				public void SubmitMeterRead()
				{
					browser.wait(getWaitTime());
					verifyPageTitle("Meter read confirmation");
			}
				public void  SubmitMeterReadUpto3MetersTitle(){
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.upto3meters"));
					
				}
				public void smrCollectiveBillingAddressComparsion(SMRAccountDetails smrProfile){
					try{
						final String displayedAddress = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.CollectiveBilling"));
						final String expectedaddress =smrProfile.getBillingAddr();  
						verifyAddresText(displayedAddress, expectedaddress);
						}
					catch(Exception e){
						Report.updateTestLog("Exception occured"+e, "FAIL");
					}
				}
				public void verifyAddresText(final String displayedAddress, final String expectedaddress) {
					System.out.println(displayedAddress);
					System.out.println(expectedaddress);
					if (displayedAddress.trim().contains(expectedaddress.trim())) {
						Report.updateTestLog("Address validation. Expected   message:" +expectedaddress+" Actual message:"+ displayedAddress, "PASS");
					} else {
						Report.updateTestLog("Error message validation. Expected message:" +expectedaddress+" Actual message:"+ displayedAddress, "FAIL");
					}
				}
				public void smrNormalSiteAddressComparsion(SMRAccountDetails smrProfile){
					try{
						final String displayedAddress = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.NormalSite"));
						System.out.println("displayedAddress"+displayedAddress);
						final String expectedaddress =smrProfile.getsiteAddress();
						verifyAddresText1(displayedAddress, expectedaddress);
						}
					catch(Exception e){
						Report.updateTestLog("Exception occured"+e, "FAIL");
					}
	
				}
				public void verifyAddresText1(final String displayedAddress, final String expectedaddress) {
					System.out.println(displayedAddress);
					System.out.println(expectedaddress);
					if (displayedAddress.trim().contains(expectedaddress.trim())) {
						Report.updateTestLog("Address validation. Expected   message:" +expectedaddress+" Actual message:"+ displayedAddress, "PASS");
					} else {
						Report.updateTestLog("Error message validation. Expected message:" +expectedaddress+" Actual message:"+ displayedAddress, "FAIL");
					}
				}
			    	public void smrMoreThan3MetersSearchOptions(){
					verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Searchby"),"Search by");
					verifyIsElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Term"), "Search term");
				
			}
				public void smrDownloadLinkVerificationAndTemplateDownload(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DownloadLink"), "Download Template");
					browser.wait(getWaitTime());
					   //String testPath;
					   //testPath = "";
					  // RobotSendKeys.altS();
				      // browser.wait(3000);
				       RobotSendKeys.typeenter();
				       browser.wait(3000);
				       Report.updateTestLog("xlxs file get downloaded by clicking enter", "PASS");
					
				}
				
				public void smrHowToReadMyMeterAndsmrIcannotSeeAllOfMyMetersLink(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.HowToReadMyMeter"),"How to read my meter");
					browser.wait(getWaitTime());
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.HowToReadMyMeterTitle"));
					browser.browserBack();
					browser.wait(getWaitTime());		
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.IcannotSeeAllOfMyMeters"),"I Cannot see all of my meters");
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ICannotSeeAllOfMyMetersOverlay")))
					{
			            String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ICannotSeeAllOfMyMetersOverlay"));
			            System.out.println("overlayContent: "+overlayContent);
			            Report.updateTestLog("I cannot see all of my meters overlay displayed with below content: "+overlayContent, "PASS");
			                   }
			     else{
			            Report.updateTestLog("Problem with I cannot see all of my meters overlay " , "WARN");
			     }
			     
			     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ICannotSeeAllOfMyMetersClose"))){
			     verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ICannotSeeAllOfMyMetersClose"), "Overlay close button");
			     }
			     else{             
			            RobotSendKeys.typeenter();
			            browser.wait(getWaitTime());
			            Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
			            }
			        
	
			}
				public void smrSubmitFunctionalities(SMRAccountDetails smrProfile){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BackLink"), "Back Link");
					browser.wait(getWaitTime());
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.AccountSummaryTitle"));
				      browser.browserBack();
				      browser.wait(getWaitTime());
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CancelLink"), "Cancel Link");
					 browser.wait(getWaitTime());
					 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ViewYourAccountTitle"));
					 submiMeterRead(smrProfile);
					browser.wait(getWaitTime());
				}
				public void smrUploadMeterReadLink(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadLink"),"UploadMeterRead");
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadTitle"));
					browser.browserBack();
				}
				public void smrElectricityWhatsThisLink(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecWhatsThis"),"What's this? for electricity");
					browser.wait(getWaitTime());
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecWhatsThisOverlay"))){
			            String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecWhatsThisOverlayContent"));
			            System.out.println("overlayContent: " +overlayContent);
			            Report.updateTestLog("What's this overlay for electricity displayed with below content: " +overlayContent, "PASS");
			                   }
			     else{
			            Report.updateTestLog("Problem with What's this overlay " , "WARN");
			     }
			     
			     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecWhatsThisOverlayClose"))){
			     verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecWhatsThisOverlayClose"), "Overlay close button");
			     }
			     else{             
			            RobotSendKeys.typeenter();
			            browser.wait(getWaitTime());
			            Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
			            }
	
			}
				public void smrGasWhatsThisLink(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasWhatsThis"),"What's this? for Gas");
					browser.wait(getWaitTime());
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasWhatsThisOverlay"))){
			            String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GasWhatsThisOverlayContent"));
			            System.out.println("overlayContent: " +overlayContent);
			            Report.updateTestLog("What's this overlay for gas displayed with below content: " +overlayContent, "PASS");
			                   }
			     else{
			            Report.updateTestLog("Problem with What's this overlay " , "WARN");
			     }
			     
			     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasWhatsThisOverlayClose"))){
			     verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GasWhatsThisOverlayClose"), "Overlay close button");
			     }
			     else{             
			            RobotSendKeys.typeenter();
			            browser.wait(getWaitTime());
			            Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
			            }
				}
				public void smrAbove3MeterSearchAndLinks(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.HowToReadMyMeter"),"How to read my meter");
					browser.wait(getWaitTime());
					verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.HowToReadMyMeterTitle"));
					browser.browserBack();
					browser.wait(getWaitTime());
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CancelLink"), "Cancel Link");
					 browser.wait(getWaitTime());
					 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ViewYourAccountTitle"));
					 browser.browserBack();
				}
				public void smrSearchBlankClickSearch(){
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.blankSearch"),"Blank Search button click");
					if(browser.isElementVisibleWithXpath("SubmitMeterReadPage.BlankSearchError"));
				}
				
				public void verifySearchByDropdownbutton(){
					try{
						String text = null;
	                    String indicator="Null";
	                    String[] verifyText={"Meter Point Reference Number","Supply number","Site postcode"};
	                    List<String> countOf=browser.getFromDropBox("id", pageProperties.getProperty("SubmitMeterRead.SearchBy"));
	                    Report.updateTestLog("Number of Items in the listbox :"+countOf.size(),"Pass");
	                    for(String itera:verifyText){                   
	                           for(int i=2;i<=countOf.size();i++){
	                                  text=browser.getTextByXpath("//*[@id='"+pageProperties.getProperty("SubmitMeterRead.SearchBy")+"']"+"/option["+i+"]");
	                                  if(itera.equals(text)){
	                                         indicator="Pass";    
	                                         break;
	                                  }else{
	                                         indicator="Null";    
	                                  }
	                           }
	                           Report.updateTestLog("Search By Drop down value: Expected Result: "+itera+"Actual Result: "+text,indicator.contains("Pass")?"Pass":"Fail");
	                    }}catch(Exception e){
	                           Report.updateTestLog("Exception occured while retrieving drop down value"+e,"Fail");
	                    }      
	
				}
				public void moreThanMaximumErrorMessage(){
					if(browser.isElementVisibleWithXpath("SubmitMeterReadPage.Error"));
				
				}
				public void verifyAuditTable(SMRAccountDetails smrProfile){
					OnlineDBConnector dbFunctions = new OnlineDBConnector();
					String date=dbFunctions.DBsysdateDdmmyyhhmi();
					String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail()," Email Sent Status: success");
					String data = dbFunctions.getAuditType(auditType[0]);	
				    Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_SMR_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
				
				}
				public void verifyAuditTable_SMRstatus(SMRAccountDetails smrProfile){
					browser.wait(14000);
					OnlineDBConnector dbFunctions = new OnlineDBConnector();
					String date=dbFunctions.DBsysdateDdmmyyhhmi();
					String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"Business Anonymous Submit Meter Read: success");
					String data = dbFunctions.getAuditType(auditType[0]);	
				    Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_ANONYMOUS_POST_SMR_SUCCESS")?"PASS":"FAIL");
				
				}
				public void verifyAuditTable_SMREmailstatus(SMRAccountDetails smrProfile){
					OnlineDBConnector dbFunctions = new OnlineDBConnector();
					String date=dbFunctions.DBsysdateDdmmyyhhmi();
					String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"Business Anonymous Submit Meter Read Email Sent Status: success");
					String data = dbFunctions.getAuditType(auditType[0]);	
				    Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_ANONYMOUS_SMR_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
				
				}
				public void uploadMeterRead(){
					  verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BrowseButton"), "Browse bill button");
					   browser.wait(getWaitTime());
					   //String testPath;
					   //testPath = "";
					  /* RobotSendKeys.type("D:\\smr.xlxs");
				       browser.wait(3000);
				       RobotSendKeys.typeenter();
				       browser.wait(3000);*/
				       browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadFileManual"), "D:\\Smr.xlxs");
				       verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SubmitButtonOfUpload"), "Submit button after browsing file");
				       if(browser.isTextPresent("Meter read spreadsheet submitted successfully"))
						{
							Report.updateTestLog("File Uploaded successfully", "Pass");
						}
						else{
							Report.updateTestLog("File Uploaded successfully", "Fail");
						}
				}
				public void implausibleMultiDial(SMRAccountDetails smrProfile){
					int multicount=getMultipleDialCount();
					int dialsec = 0;
					for(int i=0;i<multicount;i++){
						switch (multicount){
						case 1:
							dialsec=i+2;
							getImplausiveMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);					
						case 2:
							dialsec=i+2;
							getImplausiveMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
						case 3:
							dialsec=i+3;
							getImplausiveMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
							break;
						}	
						System.out.println("case count:"+dialsec);
					}
					clickSubmitMeterRead();
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
						clickSubmitMeterRead();
						getWaitTime();
					}
					
					//verifyGasConfirmation();
				
				}
				public void getImplausiveMultiDialCountAndSubmitMeterRead(SMRAccountDetails smrProfile,int dialcount){
					int dia=dialcount;
					System.out.println("dial count"+dialcount);
					try{
						System.out.println("childitemcount:"+pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsCount").replace("traverse", ""+dialcount));	
						int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsCount").replace("traverse", ""+dialcount));
						System.out.println("dialsCount "+dialsCount);
						Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
						String previousMeterRead = getMultiplePreviousMeterRead(dialcount);
						Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
						previousMeterRead = currentMeterReadImplausible(dialsCount,previousMeterRead);	
						System.out.println("PREVIOUS METER READ LENGHT"+previousMeterRead.length()+"DIALS COUNT"+dialsCount);
						previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
						Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
						System.out.println("previousMeterRead "+previousMeterRead);
						for (int i = 1;i<=dialsCount;i++){
							String inpxpath=pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsInputXPath")+i+"')]";
							verifyAndInputByXpath(inpxpath.replace("traverse", ""+dia), "input",previousMeterRead.charAt(i - 1)+"");
							System.out.println(inpxpath.replace("traverse", ""+dia)+previousMeterRead.charAt(i - 1));
							browser.wait(500);
						}
						this.currentMeterRead = previousMeterRead;
						System.out.println("currentMeterRead "+currentMeterRead);
						Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");		
					}
					catch(Exception e){
						Report.updateTestLog("Exception occured : "+e, "FAIL");
					}
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						//verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
						getWaitTime();
					}
				}
				/*public void verifyOverlayAndSubmitMultiDial(){
					if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.MultiForceSubmit"), "Force Submit");	
						clickSubmitMeterRead();
						getWaitTime();
					}*/
				
				
				public void getDialsCountAndSubmitMeterRead5(SMRAccountDetails smrProfile,int status){
					int doYouWantToVerifySAPGas =1;
					int doYouWantToVerifySAPElec=2;
					String previousMeterRead = null;
					String ActualReadDate=null;
					String previousMeterReadvalue=null;
		              try{
		                     if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
		                           int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
		                           System.out.println("dialsCount "+dialsCount);
		                           Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
		                           previousMeterRead = getPreviousMeterRead();
		                           previousMeterReadvalue=previousMeterRead;
		                           ActualReadDate=getGlobalActualReadDate();
		                           
		                           System.out.println("now present ActualReadDate"+ActualReadDate);
		                           Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
		                           previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
		                           previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
		                           Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
		                           System.out.println("previousMeterRead "+previousMeterRead);
		                           for (int i = 1;i<=dialsCount;i++){
		                                  browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
		                                  browser.wait(500);
		                           }
		                           this.currentMeterRead = previousMeterRead;
		                           System.out.println("currentMeterRead "+currentMeterRead);
		                           clickSubmitMeterRead();
		                            if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
		                                  //verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
		                                  getWaitTime();
		                           }
		                           Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");
		                     }
		                     else {
		                           Report.updateTestLog("Meter read is already submitted for this account."+smrProfile.getAccountNumber(), "WARN");
		                     }
		                     
		                     if(doYouWantToVerifySAPGas==status)
		         			{
		                    	 System.out.println("i am here oly");
		                    	 System.out.println("previousMeterReadvalue"+previousMeterReadvalue);
		                    	 System.out.println("ActualReadDate"+ActualReadDate);
		                    	 
		                    	 verifySAPForAnonymousSAPCustomerchange(smrProfile,previousMeterReadvalue,ActualReadDate);
		         			}
		         			else if(doYouWantToVerifySAPElec==status)
		         			{
		         				verifyDataThroughQTP(smrProfile,previousMeterReadvalue,ActualReadDate);
		         			}
		              }
	
		              catch(Exception e){
		                     Report.updateTestLog("Exception occured : "+e, "FAIL");
		              }
	
		       }
	
			public void openSMRPage1(){
			browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("SubmitMeterReadPage.AnonymousSMRUrl"));
			browser.wait(getWaitTime());
			}
		public String getGlobalPreviousMeterRead(){
			String previousMeterRead = "";
			try{	
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalPreviousMeterRead"));
				System.out.println("previousMeterRead   "+previousMeterRead);
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
		public String getPreviousMeterRead1(){
			String previousMeterRead = "";
			try{	
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.PreviousMeterReadElec"));
				System.out.println("previousMeterRead   "+previousMeterRead);
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
		public String getPreviousMeterRead2(){
			String previousMeterRead = "";
			try{	
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.PreviousMeterRead1"));
				System.out.println("previousMeterRead   "+previousMeterRead);
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
		public String getActualReadDate(){		
		
			String actualReadDate= null;
				if(browser.isElementVisibleWithName("Actual read on"))
				{
					System.out.println("i am in actual read on");
					 actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOn"));
					String actualReadOn[] = actualReadDate.split("on ");
					System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
					actualReadDate = actualReadOn[1].trim();
					Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
					System.out.println(actualReadOn[1].trim());
					
				}
				else if(browser.isElementVisibleWithName("Estimated Read"))
				{
					System.out.println("Estimated Read");
					 actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOn1"));
					String actualReadOn[] = actualReadDate.split("Read");
					System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
					actualReadDate = actualReadOn[1].trim();
					Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
					System.out.println(actualReadOn[1].trim());
					
				}		
		return actualReadDate;
		}
		public String getGlobalActualReadDate(){		
			String actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalActualReadOn"));
			String actualReadOn[] = actualReadDate.split("on ");
			System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
			actualReadDate = actualReadOn[1].trim();
			Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
			System.out.println(actualReadOn[1].trim());
			return actualReadDate;
		}
		public String getActualReadDate1(){		
			String actualReadDate1 = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOnElec"));
			String actualReadDate=null;
			if(actualReadDate1.contains("on"))
			{
				System.out.println("i am in actual read on");
				 actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOn"));
				String actualReadOn[] = actualReadDate.split("on ");
				System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
				actualReadDate = actualReadOn[1].trim();
				Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
				System.out.println(actualReadOn[1].trim());
			}
			else if(actualReadDate1.contains("Read"))
			{
				System.out.println("i am in estimate read");
				
				 actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOn1"));
				String actualReadOn[] = actualReadDate.split("Read");
				System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
				actualReadDate = actualReadOn[1].trim();
				Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
				System.out.println(actualReadOn[1].trim());
			}
			return actualReadDate;
		}
		public String getActualReadDate2(){		
			String actualReadDate = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ActualReadOn1"));
			String actualReadOn[] = actualReadDate.split("Read");
			System.out.println("actualReadOn[0] "+ actualReadOn[0]+"actualReadOn[1] "+actualReadOn[1]);
			actualReadDate = actualReadOn[1].trim();
			Report.updateTestLog("Actual read date on date is "+actualReadDate, "PASS");
			System.out.println(actualReadOn[1].trim());
			return actualReadDate;
		}
	public void verifyPageTitle()
		{
			browser.wait(5000);
			verifyPageTitle("Meter read confirmation");
		}
	
		public void clickSubmitMeterRead(){
			
		
			//System.out.println("i am in");
			//verifyIsElementVisibleWithXpath(pageProperties.getProperty("Submitmeterreadpage.Anonymousback"), "back button");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("Submitmeterreadpage.Anonymouscancel"), "Cancel button");
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");
			
			browser.wait(getWaitTime());	
			globalIntoleranceOverlay();
			//waitForObjectExistance(pageProperties.getProperty("SubmitMeterReadPage.GasConfirmation"));
			//browser.wait(7000);
			browser.wait(15000);
			
			String S= "Your meter readings are complete";
			
			String Title=browser.getTitle();
		
			if(S.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
				Report.updateTestLog("Confirmation page displayed Sucessfully", "WARN");
			}
			else {
				Report.updateTestLog("Confirmation page not displayed", "FAIL");
			}
			
			
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanBanner"))) {
			
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanBanner"))){
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanBanner"), "Careplan Banner");
		    Report.updateTestLog("Careplan Banner is displayed in the SMR confirmation page", "PASS");
			}
		    else {
		    	Report.updateTestLog("Careplan Banner is not displayed in the SMR confirmation page", "Fail");
		    	
		    }
			
			browser.wait(2000);
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanPageCheck"))){	
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanPageCheck"), "Careplan Applicance");
			    Report.updateTestLog("Careplan Appliance Section is displayed", "PASS");
				}
			    else {
			    	Report.updateTestLog("Careplan Appliance Section is not displayed", "Fail");
			    
			    }
		}
			
			
		}
		
		
		public void anonymousSubmitMeterRead(){
			
			//System.out.println("i am in");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("Submitmeterreadpage.Anonymousback"), "back button");
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");
			browser.wait(getWaitTime());
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.submitbuttonNew"))){	
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.submitbuttonNew"), "Submit button");
			    Report.updateTestLog("Overlay is populated", "PASS");
				}
			
			browser.wait(30000);
			browser.wait(30000);
			verifyIsTextPresent("Confirmation");
			Report.updateTestLog("Cofirmation page is displayed" , "WARN");
			try
			{
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanBanner"))){	
				
				verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanBanner"), "Careplan Banner");
			    Report.updateTestLog("Careplan Banner is displayed in the SMR confirmation page", "PASS");
				}
			else {
		    	Report.updateTestLog("Careplan Banner is not displayed in the SMR confirmation page", "Fail");
		    	
		    }
			}
			catch(Exception e)
			{
				
			}			    
				
				browser.wait(2000);
				try
				{
				if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanPageCheck"))){	
					verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.CarePlanPageCheck"), "Careplan Applicance");
				    Report.updateTestLog("Careplan Appliance Section is displayed", "WARN");
					}
				    else {
				    	Report.updateTestLog("Careplan Appliance Section is not displayed", "Fail");
				    	
				    }
				}
				catch(Exception e)
				{
					
				}
				
			
			
		}
		
		
		public void checkInMeterReadReminder (SMRAccountDetails smrProfile) {
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.AnoymousCheckIn"), "Meter Read Reminder");
			browser.wait(3000);
			verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title", smrProfile.getTitle());
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", smrProfile.getFirstName());
			verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", smrProfile.getLastName());
			Report.updateTestLog("Meter Read Reminder is filled", "WARN");
			browser.wait(10000);
			
			
		}
		public void verifyGasConfirmation(){
			if(browser.isTextPresent(pageProperties.getProperty("SubmitMeterReadPage.GasConfirmation"))){
				verifyIsTextPresent(pageProperties.getProperty("SubmitMeterReadPage.GasConfirmation"));
			}
			else{
				Report.updateTestLog("Meter read is submitted for this account already", "FAIL");
			}
		}
		public void getDialsCountAndSubmitMeterRead(SMRAccountDetails smrProfile,int status){
			int doYouWantToVerifySAPGas =1;
			int doYouWantToVerifySAPElec=2;	
			String previousMeterRead = null;
			String ActualReadDate=null;	
			String previousMeterReadvalue=null;
			try{
				System.out.print("i am in");
				if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
					int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
					System.out.println("dialsCount "+dialsCount);
					Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
					ActualReadDate=getActualReadDate1();
					previousMeterRead = getPreviousMeterRead1();				
					Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
					previousMeterReadvalue=previousMeterRead;
				    previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";		
					previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
					Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
					System.out.println("previousMeterRead "+previousMeterRead);
				//	verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityMeterReadTitle"));
					for (int i = 1;i<=dialsCount;i++){
						browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
						browser.wait(500);
					}
					
				//	this.currentMeterRead = previousMeterRead;
					System.out.println("currentMeterRead "+currentMeterRead);	
					
					//clickSubmitMeterRead();
												
							if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
						getWaitTime();
					}
					Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");
				}
				else {
					Report.updateTestLog("Meter read is already submitted for this account."+smrProfile.getAccountNumber(), "WARN");
				}
				if(doYouWantToVerifySAPGas==status)
				{
				//	verifySAPForAnonymousSAPCustomerchange(smrProfile,previousMeterReadvalue,ActualReadDate);
				}
				else if(doYouWantToVerifySAPElec==status)
				{
				//	verifyDataThroughQTP(smrProfile,previousMeterReadvalue,ActualReadDate);
				}
			}
	
			catch(Exception e){
				Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
					
	
		}
		
		public void getDialsCountAndSubmitMeterRead1(SMRAccountDetails smrProfile,int dialsec){
			
			String previousMeterRead = null;
			String ActualReadDate=null;	
			String previousMeterReadvalue=null;
			
			try{
				System.out.print("i am in");
				if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
					int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
					System.out.println("dialsCount "+dialsCount);
					Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");			
					
					previousMeterRead = getGlobalPreviousMeterRead();
					ActualReadDate=getGlobalActualReadDate();
					previousMeterReadvalue=previousMeterRead;							
					Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
				    previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";		
					previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
					Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
					System.out.println("previousMeterRead "+previousMeterRead);
					for (int i = 1;i<=dialsCount;i++){
						browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
						browser.wait(500);
					}		
				
				
					System.out.println("currentMeterRead "+currentMeterRead);
					clickSubmitMeterRead();
																
							if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
						getWaitTime();
					}					
							
					Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");
				}
				else {
					Report.updateTestLog("Meter read is already submitted for this account."+smrProfile.getAccountNumber(), "WARN");
				}
				
			}
	
			catch(Exception e){
				Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
	
		}
		public void getDialsCountAndSubmitMeterRead12(SMRAccountDetails smrProfile,int status){
			int doYouWantToVerifySAPGas =1;
			int doYouWantToVerifySAPElec=2;
			String previousMeterRead = null;
			String ActualReadDate=null;	
			String previousMeterReadvalue=null;
			
			try{
				System.out.print("i am in");
				if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
					int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
					System.out.println("dialsCount "+dialsCount);
					Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");			
					
					previousMeterRead = getGlobalPreviousMeterRead();
					ActualReadDate=getGlobalActualReadDate();
					previousMeterReadvalue=previousMeterRead;
					/*previousMeterRead = getPreviousMeterRead2();
					ActualReadDate=getActualReadDate2();		*/						
					Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
				    previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";		
					previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
					Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
					System.out.println("previousMeterRead "+previousMeterRead);
				//	verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricityMeterReadTitle"));
					for (int i = 1;i<=dialsCount;i++){
						browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
						browser.wait(500);
					}		
				
				
					System.out.println("currentMeterRead "+currentMeterRead);
					clickSubmitMeterRead();
																
							if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
						getWaitTime();
					}					
							
					Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");
				}
				else {
					Report.updateTestLog("Meter read is already submitted for this account."+smrProfile.getAccountNumber(), "WARN");
				}
				
				if(doYouWantToVerifySAPGas==status)
				{
					System.out.println("i started");
					verifySAPForAnonymousSAPCustomerchange(smrProfile,previousMeterReadvalue,ActualReadDate);
				}
				else if(doYouWantToVerifySAPElec==status)
				{
					verifyDataThroughQTP(smrProfile,previousMeterReadvalue,ActualReadDate);
				}
			}
	
			catch(Exception e){
				Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
	
		}
	public void getMultiDialCountAndSubmitMeterReadpage(SMRAccountDetails smrProfile,int dialcount){
			int dia=dialcount;
			System.out.println("dial count"+dialcount);
			try{
				System.out.println("childitemcount:"+pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsCount1").replace("traverse", ""+dialcount));	
				int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsCount1").replace("traverse", ""+dialcount));
				System.out.println("dialsCount "+dialsCount);
				Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
				String previousMeterRead = getMultiplePreviousMeterRead1(dialcount);
				Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
				previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";	
				System.out.println("PREVIOUS METER READ LENGHT"+previousMeterRead.length()+"DIALS COUNT"+dialsCount);
				previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
				Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
				System.out.println("previousMeterRead "+previousMeterRead);
				for (int i = 1;i<=dialsCount;i++){
					String inpxpath=pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsInputXPath1")+i+"')]";
					verifyAndInputByXpath(inpxpath.replace("traverse", ""+dia), "input",previousMeterRead.charAt(i - 1)+"");
					System.out.println(inpxpath.replace("traverse", ""+dia)+previousMeterRead.charAt(i - 1));
					browser.wait(500);
				}
				this.currentMeterRead = previousMeterRead;
				System.out.println("currentMeterRead "+currentMeterRead);
				Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");		
			}
			catch(Exception e){
				//Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
		}
	
		public String getMultiplePreviousMeterRead1(int dialcount){			
			String previousMeterRead = "";
			try{	
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.MultipleDials1").replace("traverse", ""+dialcount));
				System.out.println("previousMeterRead "+previousMeterRead);
				
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
		public String getMultiplePreviousMeterReadCR(int dialcount){			
			String previousMeterRead = "";
			try{	
				
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPageCR.MultipleDials2").replace("traverse", ""+dialcount));
				System.out.println("previousMeterRead "+previousMeterRead);
				
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
		public String getMultiplePreviousMeterReadCRElec(int dialcount,String position){			
			String previousMeterRead = "";
			try{	
				
				previousMeterRead = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPageCRElec.MultipleDials1").replace("traverse", ""+dialcount).replace("position", ""+position));
				System.out.println("previousMeterRead "+previousMeterRead);
				
			}
			catch(Exception e){
				System.out.println(e);			
			}
			return previousMeterRead;
		}
	public void enterMeterDialForMultipleCount1(SMRAccountDetails smrProfile){
			
				int multicount=getMultipleDialCount();
				int dialsec = 0;int j=0;
				int k=0;
				System.out.println("multicount"+multicount);
				for(int i=0;i<multicount;i++){
					
					switch (multicount){
					
					case 1:
						dialsec=i+1;
						System.out.println("dialsec1"+dialsec);
						getMultiDialCountAndSubmitMeterReadpage(smrProfile,dialsec);	
						break;
					case 2:
						dialsec=i+1;
						System.out.println("dialsec2"+dialsec);
						getMultiDialCountAndSubmitMeterReadpage(smrProfile,dialsec);
						break;
					case 3:
						dialsec=i+1;
						System.out.println("dialsec3"+dialsec);
						getMultiDialCountAndSubmitMeterReadpage(smrProfile,dialsec);	
						break;
					}	
					System.out.println("case count:"+dialsec);
				}
				
			clickSubmitMeterRead();
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
			
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");       
				
				getWaitTime();
			}		
		}
	
	public void getMultiDialCountAndSMRCR(SMRAccountDetails smrProfile,int dialcount){
		int dia=dialcount;
		System.out.println("dial count"+dialcount);
		try{
			System.out.println("childitemcount:"+pageProperties.getProperty("SubmitMeterReadPageCR.MultipleDialsCount1").replace("traverse", ""+dialcount));	
			int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPageCR.MultipleDialsCount1").replace("traverse", ""+dialcount));
			System.out.println("dialsCount "+dialsCount);
			Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
			String previousMeterRead = getMultiplePreviousMeterReadCR(dialcount);
			Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
			previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";	
			System.out.println("PREVIOUS METER READ LENGHT"+previousMeterRead.length()+"DIALS COUNT"+dialsCount);
			previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
			Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
			System.out.println("previousMeterRead "+previousMeterRead);
			for (int i = 1;i<=dialsCount;i++){
				String inpxpath=pageProperties.getProperty("SubmitMeterReadPageCR.MultipleDialsInputXPath1")+i+"')]";
				verifyAndInputByXpath(inpxpath.replace("traverse", ""+dia), "input",previousMeterRead.charAt(i - 1)+"");
				System.out.println(inpxpath.replace("traverse", ""+dia)+previousMeterRead.charAt(i - 1));
				browser.wait(500);
			}
			this.currentMeterRead = previousMeterRead;
			System.out.println("currentMeterRead "+currentMeterRead);
			Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");		
		}
		catch(Exception e){
			//Report.updateTestLog("Exception occured : "+e, "FAIL");
		}
	}
	public void getMultiDialCountAndSMRCRElec(SMRAccountDetails smrProfile,int dialcount,String position){
		int dia=dialcount;
		System.out.println("dial count"+dialcount);
		try{
			
			System.out.println("childitemcount:"+pageProperties.getProperty("SubmitMeterReadPageCRElec.MultipleDialsCount1").replace("traverse", ""+dialcount).replace("position", ""+position));	
					
			int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPageCRElec.MultipleDialsCount1").replace("traverse", ""+dialcount).replace("position", ""+position));
			System.out.println("dialsCount "+dialsCount);
			Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
			String previousMeterRead = getMultiplePreviousMeterReadCRElec(dialcount,position);
			Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
			previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";	
			System.out.println("PREVIOUS METER READ LENGHT"+previousMeterRead.length()+"DIALS COUNT"+dialsCount);
			previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
			Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
			System.out.println("previousMeterRead "+previousMeterRead);
			for (int i = 1;i<=dialsCount;i++){
				String inpxpath=pageProperties.getProperty("SubmitMeterReadPageCRElec.MultipleDialsInputXPath1")+i+"')]";
				verifyAndInputByXpath(inpxpath.replace("traverse", ""+dia).replace("position", ""+position), "input",previousMeterRead.charAt(i - 1)+"");
				System.out.println(inpxpath.replace("traverse", ""+dia).replace("position", ""+position)+previousMeterRead.charAt(i - 1));
				browser.wait(500);
			}
			this.currentMeterRead = previousMeterRead;
			System.out.println("currentMeterRead "+currentMeterRead);
			Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");		
		}
		catch(Exception e){
			//Report.updateTestLog("Exception occured : "+e, "FAIL");
		}
	}
			public void enterMeterDialForMultipleCountforSMRCR(SMRAccountDetails smrProfile,String userstatus){
				
				int multicount=getMultipleDialCountCR();
				int dialsec = 0;int j=0;
				int k=0;
				System.out.println("multicount"+multicount);
				for(int i=0;i<multicount;i++){
					
					switch (multicount){
					
					case 1:
						dialsec=i+2;
						System.out.println("dialsec1"+dialsec);
						getMultiDialCountAndSMRCR(smrProfile,dialsec);	
						break;
					case 2:
						dialsec=i+2;
						System.out.println("dialsec2"+dialsec);
						getMultiDialCountAndSMRCR(smrProfile,dialsec);
						break;
					case 3:
						dialsec=i+2;
						System.out.println("dialsec3"+dialsec);
						getMultiDialCountAndSMRCR(smrProfile,dialsec);	
						break;
					}	
					System.out.println("case count:"+dialsec);
				}
				SMRyourdetails_receivemail(smrProfile,userstatus);	
				continuebutton();
			  if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
			
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");       
				
				getWaitTime();
			}  
			
			}
	public void enterMeterDialForMultipleCountforSMRCR_multiple(SMRAccountDetails smrProfile,String userstatus){
				int multicount=getMultipleDialCountCR();
				browser.wait(2000);
				int dialsec = 0;int j=0;
				int k=0;
				System.out.println("multicount"+multicount);
				for(int i=1;i<=multicount;i++){
					int m=2;
					System.out.println("mypath"+".//*[@id='anonysmr']/fieldset/div[2]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]");
					
					while(browser.isElementVisibleWithXpath(".//*[@id='anonysmr']/fieldset/div[2]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]"))
					{
						if(browser.isElementVisibleWithXpath(".//*[@id='anonysmr']/fieldset/div[2]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]"))
						{
							getMultiDialCountAndSMRCRElec(smrProfile,m,"2");
						}
						m++;
					}
					 m=2;
					while(browser.isElementVisibleWithXpath(".//*[@id='anonysmr']/fieldset/div[5]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]"))
					{
						 if(browser.isElementVisibleWithXpath(".//*[@id='anonysmr']/fieldset/div[5]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]"))
						{
							 getMultiDialCountAndSMRCRElec(smrProfile,m,"5");
						}
						 m++;
					}
					m=2;
					while(browser.isElementVisibleWithXpath(".//*[@id='anonysmr']/fieldset/div[8]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]"))
					{
						if(browser.isElementVisibleWithXpath(".//*[@id='anonysmr']/fieldset/div[8]/div/div["+m+"]/div/div[1]/div[1]//*[contains(@class,'dialInput')]"))
						{
							getMultiDialCountAndSMRCRElec(smrProfile,m,"8");
						}
						m++;
					}
					System.out.println("case count:"+dialsec);
				}
				
					SMRyourdetails_receivemail(smrProfile,userstatus);	
				 	continuebutton();
			  if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
			
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");       
				
				getWaitTime();
			}  
			
			}
		public void enterMeterDialForMultipleCount(SMRAccountDetails smrProfile){	
			int multicount=getMultipleDialCount();
			int dialsec = 0;
			for(int i=0;i<multicount;i++){
				switch (multicount){
				case 1:
					dialsec=i+3;
					getMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);
					break;
				case 2:
					dialsec=i+3;
					getMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
					break;
				case 3:
					dialsec=i+3;
					getMultiDialCountAndSubmitMeterRead(smrProfile,dialsec);	
					break;
				}	
				System.out.println("case count:"+dialsec);
			}
			clickSubmitMeterRead();
			browser.wait(10000);
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
				//clickSubmitMeterRead();
				getWaitTime();
			}
			//verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
		}
		
		public void enterMeterDialForMultipleCountforglobal(SMRAccountDetails smrProfile){
	
			int multicount=getMultipleDialCount();
			int dialsec = 0;
			for(int i=0;i<multicount;i++){
				switch (multicount){
				case 1:
					dialsec=i+2;
					getDialsCountAndSubmitMeterReadvalue(smrProfile,dialsec);					
				case 2:
					dialsec=i+2;
					getDialsCountAndSubmitMeterReadvalue(smrProfile,dialsec);	
				case 3:
					dialsec=i+3;
					getDialsCountAndSubmitMeterReadvalue(smrProfile,dialsec);	
					break;
				}	
				System.out.println("case count:"+dialsec);
			}
			clickSubmitMeterRead();
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
				clickSubmitMeterRead();
				getWaitTime();
			}
		}
		public void getDialsCountAndSubmitIncorrectRead(SMRAccountDetails smrProfile,String indicator){
	
	        try{
	               if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"))){
	                     int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
	                     System.out.println("dialsCount "+dialsCount);
	                     Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                     String previousMeterRead = getGlobalPreviousMeterRead();
	                     Report.updateTestLog("Previous meter read value is "+previousMeterRead, "PASS");
	                     smrProfile.setlastMeterRead(previousMeterRead);
	                     if(indicator.equalsIgnoreCase("Exceedvalue")){
	                            System.out.println("indicator value:"+indicator);
	                            previousMeterRead = currentMeterReadImplausible(dialsCount,previousMeterRead);
	                     }else if(indicator.equalsIgnoreCase("Negativevalue")){
	                            System.out.println("indicator value:"+indicator);
	                            previousMeterRead = (Integer.parseInt(previousMeterRead)-1)+"";      
	                     }else if(indicator.equalsIgnoreCase("Correctvalue")){
	                            System.out.println("indicator value:"+indicator);
	                            previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";
	                            System.out.println("Previous meter read value in case o:"+previousMeterRead);
	                            previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                     }
	                     previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                     smrProfile.setafterSubmitMeterRead(previousMeterRead);
	                     Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                     System.out.println("previousMeterRead "+previousMeterRead);
	
	                     for (int i = 1;i<=dialsCount;i++){
	                            browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
	                            browser.wait(500);
	                     }
	                     this.currentMeterRead = previousMeterRead;
	                     System.out.println("currentMeterRead "+currentMeterRead);
	                     clickSubmitMeterRead();
	                     browser.wait(20000);
	                     browser.wait(20000);
	               }
	               else{
	                     Report.updateTestLog("Meter read already submitted for this account", "WARN");       
	               }
	        }
	 
	        catch(Exception e){
	
	        }
	        forceSubmit();
	 }
	
	public void getMultiDialCountAndSubmitMeterReadvalue(SMRAccountDetails smrProfile,int dialcount)
	{
		int dia=dialcount;
		try{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"))){
				int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
				System.out.println("dialsCount "+dialsCount);
				Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
				String previousMeterRead = getGlobalPreviousMeterRead();
				Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
				previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";	
				System.out.println("PREVIOUS METER READ LENGHT"+previousMeterRead.length()+"DIALS COUNT"+dialsCount);
				previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
				Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
				System.out.println("previousMeterRead "+previousMeterRead);
				for (int i = 1;i<=dialsCount;i++){
					String inpxpath=pageProperties.getProperty("SubmitMeterReadPage.MultipleDialsInputXPath")+i+"')]";
					verifyAndInputByXpath(inpxpath.replace("traverse", ""+dia), "Dial",previousMeterRead.charAt(i - 1)+"");
					System.out.println(inpxpath.replace("traverse", ""+dia)+previousMeterRead.charAt(i - 1));
					browser.wait(500);
				}
				this.currentMeterRead = previousMeterRead;
				System.out.println("currentMeterRead "+currentMeterRead);
				Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");	
			}
			else{
				Report.updateTestLog("Meter read already submitted for this account", "WARN");	
			}
		}
		catch(Exception e){
	
		}
	}
	public void forceSubmit(){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
				Report.updateTestLog("Implausible Overlay Appears", "Pass");	
				verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");	
				getWaitTime();
			}
		}
	public void verifyDataThroughQTP(SMRAccountDetails smrProfile,String previousMeterRead,String ActualReadDate){
	
		String readDateFromApp=Dateformatconvertor(ActualReadDate);
		System.out.println(readDateFromApp);
		String readValueFromApp=previousMeterRead;
		System.out.println(readValueFromApp);		
		System.out.println("hi");
		String Acctno=smrProfile.getAccountNumber();
		System.out.print("Acctno"+Acctno);		
		String bpnumber=smrProfile.getbpnumber();
		System.out.print("bpnumber"+bpnumber);
		String bpOrgNumber =bpnumber.concat("-").concat(Acctno);
		System.out.print("bpOrgNumber"+bpOrgNumber);	
		RunQTP runQTP = new RunQTP();
		System.out.println("i am in");
	    runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\SubmitMeterRead_Slingshot.vbs", bpOrgNumber);
	    System.out.println("i am out");
	
			browser.wait(15000);
			try {
				File file1 = new File("C:\\SAPData\\SubmitMeterReadTest.txt");
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
	
				String readdate = br.readLine();
				if(readdate.contains("LastMeterReadingDate")&& readdate.contains(":") ){
					
					System.out.println("i am started to read");
					String[] read=readdate.split(":");
					String readDate=read[1];
					System.out.println(("my read data"+readDate));
					if(readDateFromApp.trim().equals(readDate)){
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadingDate is not written in the text file", "Fail");            	
				}
	
				String lastMeterReadValue = br.readLine();
				if(lastMeterReadValue.contains("LastMeterReadValue")&& readdate.contains(":")){
					String[] lastmeterread=lastMeterReadValue.split(":");
					String lastread=lastmeterread[1];
					String readvalueapp=readValueFromApp.trim();
					 int Intread1=Integer.parseInt(lastread);
			         int Intread2=Integer.parseInt(readvalueapp);				
					if(Intread1==Intread2){
						Report.updateTestLog("LastMeterReadValue in Application"+readValueFromApp.trim()+"LastMeterReadValue in ISU :"+lastMeterReadValue, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadValue in Application"+readValueFromApp.trim()+"LastMeterReadValue in ISU :"+lastMeterReadValue, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");            	
				}
				
				br.close();
			}catch (IOException e) {
				System.out.println("bad"+e);
				Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
			}
	
		}
		
		public String Dateformatconvertor(String ActualReadDate)
		{
			 try
			    {
			      //create SimpleDateFormat object with source string date format
			      SimpleDateFormat sdfSource = new SimpleDateFormat("dd MMM yyyy");
			      
			      //parse the string into Date object
			      Date date = sdfSource.parse(ActualReadDate);
			      
			      //create SimpleDateFormat object with desired date format
			      SimpleDateFormat sdfDestination = new SimpleDateFormat("dd.MM.yyyy");
			      
			      //parse the date into another format
			      ActualReadDate = sdfDestination.format(date);
			      
			      System.out.println("Date is converted from dd/MM/yy format to dd.MM.yyyy");
			      System.out.println("Converted date is : " + ActualReadDate);
			      
			    }
			    catch(ParseException pe)
			    { 
			      System.out.println("Parse Exception : " + pe);
			    }
			return ActualReadDate;
		}
	public void verifySAPForAnonymousSAPCustomer(SMRAccountDetails smrProfile,String previousMeterRead,String ActualReadDate){
	
			String readDateFromApp=Dateformatconvertor(ActualReadDate);
			System.out.println(readDateFromApp);
			String readValueFromApp=previousMeterRead;
			System.out.println(readValueFromApp);
			System.out.println("hi");
			String bpOrgNumber = "2001435420-601237608";
			OnlineDBConnector dbfunctions = new OnlineDBConnector();
			
		    RunQTP runQTP = new RunQTP();
		    System.out.println("i am in");
		    
		   runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\SubmitMeterRead_Slingshot.vbs", bpOrgNumber);
		   System.out.println("i am out");
	
		   browser.wait(15000);
			try {
				File file1 = new File("C:\\SAPData\\SubmitMeterReadTest.txt");
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
	
				String readdate = br.readLine();
				if(readdate.contains("LastMeterReadingDate")&& readdate.contains(":") ){
					
					System.out.println("i am started to read");
					String[] read=readdate.split(":");
					String readDate=read[1];
					if(readDateFromApp.trim().equals(readDate)){
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadingDate is not written in the text file", "Fail");            	
				}
	
				String lastMeterReadValue = br.readLine();
				if(lastMeterReadValue.contains("LastMeterReadValue")&& readdate.contains(":")){
					String[] lastmeterread=lastMeterReadValue.split(":");
					String lastread=lastmeterread[1];
					if(lastread.trim().equals(readValueFromApp.trim())){
						Report.updateTestLog("LastMeterReadValue in Application "+readValueFromApp.trim()+"LastMeterReadValue in ISU : "+lastMeterReadValue, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadValue in Application "+readValueFromApp.trim()+"LastMeterReadValue in ISU : "+lastMeterReadValue, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");            	
				}		
				br.close();
			}catch (IOException e) {
				Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
			}
	}
		
		public void verifySAPForAnonymousSAPCustomerchange(SMRAccountDetails smrProfile,String previousMeterRead,String ActualReadDate){
			
			String readDateFromApp=Dateformatconvertor(ActualReadDate);
			System.out.println(readDateFromApp);
			String readValueFromApp=previousMeterRead;
			System.out.println(readValueFromApp);		
			System.out.println("hi");
			String Acctno=smrProfile.getAccountNumber();
			System.out.print("Acctno"+Acctno);		
			String bpnumber=smrProfile.getbpnumber();
			System.out.print("bpnumber"+bpnumber);
			String bpOrgNumber =bpnumber.concat("-").concat(Acctno);
			System.out.print("bpOrgNumber"+bpOrgNumber);	
			RunQTP runQTP = new RunQTP();
		   System.out.println("i am in");
		    runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\SubmitMeterRead_Slingshot.vbs", bpOrgNumber);
		   System.out.println("i am out");
	
		   browser.wait(15000);
			try {
				File file1 = new File("C:\\SAPData\\SubmitMeterReadTest.txt");
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
	
				String readdate = br.readLine();
				if(readdate.contains("LastMeterReadingDate")&& readdate.contains(":") ){
					
					System.out.println("i am started to read");
					String[] read=readdate.split(":");
					String readDate=read[1];
					if(readDateFromApp.trim().equals(readDate)){
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadingDate in Application: "+readDateFromApp+"LastMeterReadingDate in ISU "+readDate, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadingDate is not written in the text file", "Fail");            	
				}
	
				String lastMeterReadValue = br.readLine();
				if(lastMeterReadValue.contains("LastMeterReadValue")&& readdate.contains(":")){
					String[] lastmeterread=lastMeterReadValue.split(":");
					String lastread=lastmeterread[1];
					String readvalueapp=readValueFromApp.trim();
					 int Intread1=Integer.parseInt(lastread);
			         int Intread2=Integer.parseInt(readvalueapp);
					
					if(Intread1==Intread2){
						Report.updateTestLog("LastMeterReadValue in Application "+readValueFromApp.trim()+"LastMeterReadValue in ISU : "+lastMeterReadValue, "Pass");
					}else{
						Report.updateTestLog("LastMeterReadValue in Application "+readValueFromApp.trim()+"LastMeterReadValue in ISU : "+lastMeterReadValue, "Fail");
					}
				}else{
					Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");            	
				}		
				br.close();
			}catch (IOException e) {
				Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
			}
	}
		public void verifySMRSiteNumber(){
			if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.SiteNumber"))){
				Report.updateTestLog("Gas customer is identified within CRM, hence navigates to SMR second page without site number", "Pass");
			}
			else{
				Report.updateTestLog("Gas customer is identified within CRM, hence navigates to SMR second page without site number", "Fail");
			}
		}
	public void getDialsCountAndSubmitMeterReadvalue(SMRAccountDetails smrProfile,int dialsec){
			
			String previousMeterRead = null;
			String ActualReadDate=null;	
			String previousMeterReadvalue=null;
			
			try{
				System.out.print("i am in");
				if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
					int dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsCount"));
					System.out.println("dialsCount "+dialsCount);
					Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");			
					
					previousMeterRead = getGlobalPreviousMeterRead();
					ActualReadDate=getGlobalActualReadDate();
					previousMeterReadvalue=previousMeterRead;							
					Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
				    previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";		
					previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
					Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
					System.out.println("previousMeterRead "+previousMeterRead);
					for (int i = 1;i<=dialsCount;i++){
						browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.DialsInputXPath")+i+"']", previousMeterRead.charAt(i - 1)+"");
						browser.wait(500);
					}		
				
				
					System.out.println("currentMeterRead "+currentMeterRead);
					clickSubmitMeterRead();
																
							if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
						verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");
						getWaitTime();
					}					
							
					Report.updateTestLog("Setting reading for account: "+smrProfile.getAccountNumber(), "DONE");
				}
				else {
					Report.updateTestLog("Meter read is already submitted for this account."+smrProfile.getAccountNumber(), "WARN");
				}
				
			}
	
			catch(Exception e){
				Report.updateTestLog("Exception occured : "+e, "FAIL");
			}
	
		}
	public void getDialsCountAndSubmitMeterReadchange(SMRAccountDetails smrProfile,int status){
		int doYouWantToVerifySAPGas =1;
		int doYouWantToVerifySAPElec=2;
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage2.DialsCount"));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getPreviousMeterRead();
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   System.out.println("fffffffffffffffffffffffffffffffffffff");
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   System.out.println("dddddddddddddddddddddddddddddddddd");
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage3.DialsInputXPath"));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	             
	                   for (int i = 1;i<=dialFieldCount;i++){
	                	   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage5.DialsInputXPath").replace("dialFieldCount", i+""), previousMeterRead.charAt(i - 1)+"");
	                          
	                   }
	                   calendarDatePicker();
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);	                
/////////////////////////////////////////////////////////calendar date//////////////////////////////////////////////////////////////////////////////////////////
	                         
	                    
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");
	         
	      }
	      }
	
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	      
	
	
	}
	public void getDialsCountAndGlobalSubmitMeterReadchange(SMRAccountDetails smrProfile,int status){
		int doYouWantToVerifySAPGas =1;
		int doYouWantToVerifySAPElec=2;
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	            	 
	            
	       
	            	 totalcount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.OverallCount"));
	            	 System.out.println("totalcount");
	            	 for (int i = 1; i<=totalcount;i++ ){
	            		System.out.println("globallllllllllllllllllllllllllllllllll");
	            	 
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalDialsCount").replace("Count",""+i));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getGlobalPreviousMeterReaddData(i);
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   System.out.println("fffffffffffffffffffffffffffffffffffff");
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   System.out.println("dddddddddddddddddddddddddddddddddd");
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalInputDialsCount").replace("Count",""+i));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   calendarDatePicker();
	                   for (int j = 1;j<=dialFieldCount;j++){
	                	   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalInputDials").replace("Global",""+i).replace("dialFieldCount", j+""), previousMeterRead.charAt(j - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	                	            
	                calendarDatePicker();
	                    
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");
	            	 }
	      }
	      }
	
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	      
	
	
	}
	public void getDialsCountAndGlobalElecSubmitMeterReadchange(SMRAccountDetails smrProfile,int status){
		int doYouWantToVerifySAPGas =1;
		int doYouWantToVerifySAPElec=2;
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	            	 
	            	 totalcount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecOverallCount"));
	            	 System.out.println("totalcount");
	            	 Report.updateTestLog("*******************************Total number of register= "+totalcount+"***************************", "PASS");
	            	 for (int i = 1; i<=totalcount;i++ ){
	            		System.out.println("globallllllllllllllllllllllllllllllllll");
	            	 
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecGlobalDialsCount").replace("Count",""+i));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getGlobalPreviousMeterElecReaddData(i);
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   System.out.println("fffffffffffffffffffffffffffffffffffff");
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   System.out.println("dddddddddddddddddddddddddddddddddd");
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecGlobalInputDialsCount").replace("Count",""+i));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   calendarDatePicker();
	                   for (int j = 1;j<=dialFieldCount;j++){
	                	   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElecGlobalInputDials").replace("Global",""+i).replace("dialFieldCount", j+""), previousMeterRead.charAt(j - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	                	            
	                calendarDatePicker();
	                    
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");
	            	 }
	      }
	      }
	
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	      
	
	
	}
	
	
	public void meterReadingDue() {
		
		
		verifyIsElementVisibleWithXpath((pageProperties.getProperty("SubmitMeterReadPage.MeteringDue")),"Meter Reading Due overlay");
		
	}
	
	
	public void globalIntoleranceOverlay(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalIntoleranceOverlay"))){
			
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.GlobalIntoleranceOverlay"),"Submit");
			
		}
		
	}
	public void getDialsCountAndSubmitMeterReadchangeElec(SMRAccountDetails smrProfile,int status){
		int doYouWantToVerifySAPGas =1;
		int doYouWantToVerifySAPElec=2;
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage4.DialsCount"));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getPreviousMeterReadelec();
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	              
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	               
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	               
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage3.DialsInputXPath"));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   calendarDatePicker();
	                   for (int i = 1;i<=dialFieldCount;i++){
	        
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage5.DialsInputXPath").replace("dialFieldCount", i+""), previousMeterRead.charAt(i - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	                
/////////////////////////////////////////////////////////calendar date//////////////////////////////////////////////////////////////////////////////////////////
	            
	                calendarDatePicker();
	                    
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");
	         	
	      }
	      }
	
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	      
	
	
	}
	public void getDialsCountAndSubmitMeterReadAnonymous(SMRAccountDetails smrProfile){
	
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage2.AnonymousGasDialsCount"));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getPreviousMeterReadAnonymous();
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   System.out.println("fffffffffffffffffffffffffffffffffffff");
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   System.out.println("dddddddddddddddddddddddddddddddddd");
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage3.DialsInputXPath"));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   calendarDatePicker();
	                   for (int i = 1;i<=dialFieldCount;i++){
	                	   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage5.DialsInputXPath").replace("dialFieldCount", i+""), previousMeterRead.charAt(i - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	                 calendarDatePicker();
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");  	
	      }
	      }
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	}
	public void getDialsCountAndSubmitMeterReadAnonymousElect(SMRAccountDetails smrProfile){
		
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage2.AnonymousElectDialsCount"));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getPreviousMeterReadAnonymous();
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage3.DialsInputXPath"));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   
	                   for (int i = 1;i<=dialFieldCount;i++){
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage5.DialsInputXPath").replace("dialFieldCount", i+""), previousMeterRead.charAt(i - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	                 calendarDatePicker();
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");  	
	      }
	      }
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void getDialsCountAndSubmitMeterReadAnonymousgas(SMRAccountDetails smrProfile){
		
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage2.AnonymousGasDialsCount"));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getPreviousMeterReadAnonymousgas();
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   System.out.println("fffffffffffffffffffffffffffffffffffff");
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   System.out.println("dddddddddddddddddddddddddddddddddd");
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage3.DialsInputXPath"));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   calendarDatePicker();
	                   for (int i = 1;i<=dialFieldCount;i++){
	                	   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage5.DialsInputXPath").replace("dialFieldCount", i+""), previousMeterRead.charAt(i - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	                 calendarDatePicker();
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");  	
	      }
	      }
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	}
	
public void getDialsCountAndSubmitMeterReadAnonymouselect(SMRAccountDetails smrProfile){
		
		String previousMeterRead = null;
		String ActualReadDate=null;
		String previousMeterReadvalue=null;
	      try{
	             if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Submitted"))){
	                   dialsCount = browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage2.AnonymousGasDialsCount"));
	                   System.out.println("dialsCount "+dialsCount);
	                   Report.updateTestLog("Displayed meter dials for "+smrProfile.getAccountNumber()+" is "+dialsCount, "DONE");
	                   previousMeterRead = getPreviousMeterReadAnonymousgas();
	                   previousMeterReadvalue=previousMeterRead;
	                   //ActualReadDate=getGlobalActualReadDate();
	                   System.out.println("fffffffffffffffffffffffffffffffffffff");
	                   
	                   //System.out.println("now present ActualReadDate"+ActualReadDate);
	                   Report.updateTestLog("Previous meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	                   previousMeterRead = (Integer.parseInt(previousMeterRead)+1)+"";            
	                   previousMeterRead = (previousMeterRead.length() == dialsCount)?previousMeterRead:padZeros(previousMeterRead, dialsCount);
	                   System.out.println("dddddddddddddddddddddddddddddddddd");
	                   Report.updateTestLog("Current meter read value is"+previousMeterRead, "PASS");
	                   System.out.println("previousMeterRead="+previousMeterRead);
	                   int dialFieldCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("SubmitMeterReadPage3.DialsInputXPath"));
	                   System.out.println("dialFieldCount "+dialFieldCount);
	                   calendarDatePicker();
	                   browser.wait(5000);
	                   for (int i = 1;i<=dialFieldCount;i++){
	                	   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	                	   //browser.inputByXpath((DialsInputXPath.replace("NUMBER", ""+(i-1)), previousMeterRead.charAt(i - 1) + "");  
	                	   browser.inputByXpath(pageProperties.getProperty("SubmitMeterReadPage5.DialsInputXPath").replace("dialFieldCount", i+""), previousMeterRead.charAt(i - 1)+"");
	                          
	                   }
	                   this.currentMeterRead = previousMeterRead;
	                   System.out.println("currentMeterRead "+currentMeterRead);
	             //    calendarDatePicker();
	             Report.updateTestLog("Meter Read Page is diaplayed", "WARN");  	
	      }
	      }
	      catch(Exception e){
	             Report.updateTestLog("Exception occured : "+e, "FAIL");
	      }
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void calendarDatePicker () {
		int days = DateTime.now().getDayOfMonth();
		System.out.println("day"+days);
		String day= Integer.toString(days);
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Calendarcomponent"))){
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Calendarcomponent"), "Calendar object is");
			Report.updateTestLog("Calendar component is displayed in the application", "PASS");
			
			for(int i=0; i<=1; i++)
			{
			verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadpremontnavigator"), "previous month navigator");
			}
			calenderDate(day);
			browser.wait(5000);
		}
			else {
				Report.updateTestLog("Calendar component is not displayed in the application", "FAIL");
			}
		
		}    
		
	
	
	public String calenderDate(String day) {

		String result = "False";
		System.out.println("day"+day);
		browser.wait(5000);
		System.out.println(pageProperties.getProperty("SubmitMeterReadPage.DatePicker"));
		// int
		// rowcount=browser.getRowCountByXpath(pageProperties.getProperty("GetaQuotePage.CalenderTable"));
		int rowcount = 5;
		System.out.println("rowcount:" + rowcount);
		// int
		// columncount=browser.getColCountByXpath(pageProperties.getProperty("GetaQuotePage.CalenderTable"));
		int columncount = 7;
		System.out.println("columncount:" + columncount);

		for (int i = 1; i <= rowcount; i++) {

			for (int j = 1; j <= columncount; j++) {

				System.out.println(pageProperties.getProperty("SubmitMeterReadPage.DatePicker")+ "/tr["+ i + "]/td[" + j + "]/a");
				if (browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DatePicker")+ "/tr["+ i + "]/td[" + j + "]/a")) {
					String dateThreeMonths = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.DatePicker")+ "/tr[" + i + "]/td[" + j + "]/a");
					
					browser.clickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DatePicker")+ "/tr[" + i + "]/td[" + j + "]/a");
					System.out.print("dateThreeMonths" + dateThreeMonths);
					System.out.print("i" + i);
					System.out.print("j" + j);
					
					browser.wait(5000);
					
					if (dateThreeMonths.equals(day)) {
						Report.updateTestLog("Day is matched:" + day, "pass");
						System.out.print(" matched i" + i);
						System.out.print("matched j" + j);
						System.out.print("date is matched");
						System.out.println(pageProperties.getProperty("SubmitMeterReadPage.DatePicker")+ "/tr[" + i + "]/td[" + j + "]/a");
						browser.wait(5000);
						browser.clickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.DatePicker")+ "//tr[" + i + "]/td[" + j + "]/a");
						browser.wait(5000);
						result = "True";
						break;
						//.//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[3]/a
						  }
					}
							}
		}
		return result;
	}
	
	public void smrMetersOverlay(){	   
		   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.icannotseeallmymeters"), "I cannot see all my meters?");
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.icannotseeallmymeters"))){
			   //String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoWherecanifindthisContent"));
			   //System.out.println("overlayContent: "+overlayContent);
			   Report.updateTestLog("I cannot see all my meters", "PASS");
			   	   }
		   else{
			   Report.updateTestLog(" I cannot see all my meters" , "WARN");
		   }
		   
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoOverlayClose"))){
		   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoOverlayClose"), "Overlay close button");
		   }
		   else{		 
			   RobotSendKeys.typeenter();
			   browser.wait(getWaitTime());
			   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
			   }
		      
	  
	   }
	
	public void howDoIReadMyMeter(){
		verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.howdoreadmymeter"), "How do i read my meter?");
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.howdoreadmymeter"))){
			   //String overlayContent = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.ElectricAcctnoWherecanifindthisContent"));
			   //System.out.println("overlayContent: "+overlayContent);
			   Report.updateTestLog("I cannot see all my meters", "PASS");
			   	   }
		   else{
			   Report.updateTestLog(" I cannot see all my meters" , "WARN");
		   }
		   
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.howdoireadmymeterclose"))){
		   verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.howdoireadmymeterclose"), "Overlay close button");
		   }
		   else{		 
			   RobotSendKeys.typeenter();
			   browser.wait(getWaitTime());
			   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
			   }
		      
	  
	   }
	
	public void submitButton(){
		 clickSubmitMeterRead();
	}
	public void overlayclose()
	{
		 	String jqueryToCloseOverlay=("$('a.ui-dialog-titlebar-close').trigger('click');");
			browser.executeScript(jqueryToCloseOverlay);
		       Report.updateTestLog("Implausible Overlay closed", "Pass");
			browser.wait(getWaitTime());
	}
	public void overlay()
	{
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Implausible"))){
			Report.updateTestLog("Implausible Overlay Appears", "Pass");
			verifyAndClick(pageProperties.getProperty("SubmitMeterReadPage.ForceSubmit"), "Force Submit");					
			clickSubmitMeterRead();
			getWaitTime();
		}
	}
	public void verifyElectricConfirmations()
	{
		 browser.wait(getWaitTime());
		 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.ElectricConfirmationPageTitle"));
			
	}
	public void clickUploadMeterReadLinks()
	{
		 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageLink"),"Click Upload Meter Read Link");
		 verifyPageTitle(pageProperties.getProperty("SubmitMeterReadPage.UploadMeterReadPageTitle"));
	}
	public void cancelbutton()
	{
		 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.Cancelbutton"), "Click cancel button");
	}
	public void yourdetailsCancelButtons()
	{
		 verifyPageTitle("Gas meter read \u2013 Your details");
		 verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.BackLinkGlobalSmrPage"),"Click the Back Link");
	}
	public void verifyLeadTabledata(SMRAccountDetails smrProfile){
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"success");
		String data = dbFunctions.getAuditType(auditType[0]);	
		Report.updateTestLog("Successfully Email Sent to Customer And Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is"+data, data.equalsIgnoreCase("BGBUSINESS_POST_SMR_SUCCESS")?"PASS":"FAIL");
		}
	public void SMRyourdetails(SMRAccountDetails smrProfile)
	{
		verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Email"), "Email",smrProfile.getEmail());
		verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.ContractAccountNumber"), "Acctnumber",smrProfile.getAccountNumber());
		verifyAndInputById(pageProperties.getProperty("SubmitMeterReadPage.postcode"), "Site postcode",smrProfile.getsitePostcode());
		verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");		
		browser.wait(5000);
	}
	public void collective_error()
	{
		String expectedErrorMsg="As you're a large business, you'll need to contact your account manager to discuss your account details.";
			try{
				final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("SubmitMeterReadPage.collectiverror"));
				System.out.println("displayedErrorMsg"+displayedErrorMsg);
				verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);}
			catch(Exception e){
				Report.updateTestLog("Exception occured"+e, "FAIL");
			}
		}
	
	public void SMRyourdetails_receivemail(SMRAccountDetails smrProfile,String Userstatus)
	{
	if(Userstatus.equals("Newuser_with_alretboxchecked"))
	{
		verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.receiveemailcheckbox"), "receive email checkbox");
		verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterReadPage.Title"), "Title","Mr");
		verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.FirstName"), "First name", smrProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("SubmitMeterReadPage.Surname"), "Sur name", smrProfile.getLastName());
		browser.wait(3000);
	}
	else if(Userstatus.equals("Newuser_without_alretboxchecked"))	
	{
		Report.updateTestLog("SMR Alert Checkbox is not selected for this user","Pass");
	}
	else if(Userstatus.equals("Alreadyenrolleduser"))	
	{
		if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("SubmitMeterReadPage.receiveemailcheckbox")))
		{
			Report.updateTestLog("SMR Alert Checkbox is not available for already enrolled Users","Pass");
		}
		else
		{
			Report.updateTestLog("SMR Alert Checkbox is not available for already enrolled Users","Fail");
		}
	}
	}
	public void SMRconfirmationpage()
	{
		browser.wait(10000);
	//	verifyPageTitle("Confirmation");
	}
	public void continuebutton()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");		
	}
	
	public void opensmrpageforemailurl(SMRAccountDetails smrProfile)
	{
		System.out.println(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("SubmitMeterReadPage.AnonymousSMRUrlemail")+"/your-details?email_address="+smrProfile.getEmail()+"&account_number="+smrProfile.getAccountNumber()+"&SitePostcode="+smrProfile.getsitePostcode());
		browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("SubmitMeterReadPage.AnonymousSMRUrlemail")+"/your-details?email_address="+smrProfile.getEmail()+"&account_number="+smrProfile.getAccountNumber()+"&SitePostcode="+smrProfile.getsitePostcode());																	
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("SubmitMeterReadPage.ContinueButton"), "Continue button");
	}
	
	public void verifyAuditEntrySMRAnonymous(SMRAccountDetails smrProfile) 
	{
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date,smrProfile.getEmail(),"Business Anonymous Submit Meter Read: success");
		String data = dbFunctions.getAuditType(auditType[0]);	
		String[] auditType2 = dbFunctions.getAuditEventTypeId(date,smrProfile.getEmail(),"Business Anonymous Submit Meter Read Email Sent Status: success");
		String data2 = dbFunctions.getAuditType(auditType2[0]);	
		System.out.println("Data:"+data);
		System.out.println("Data2:"+data2);
		System.out.println("Audit data is made in audit table as expected. Audit data: " +auditType[0]);
		System.out.println(Arrays.asList(auditType));
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is:"+data, data.trim().equalsIgnoreCase("BGBUSINESS_ANONYMOUS_POST_SMR_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"PASS");
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType2[0]+ " Audit event type is:"+data2, data2.trim().equalsIgnoreCase("BGBUSINESS_ANONYMOUS_SMR_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType2[1],"PASS");
	}
	
	public void verifyAuditEntrySMRLoggedIn(SMRAccountDetails smrProfile) 
	{
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date=dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date,smrProfile.getEmail(),"Business Submit Meter Read: success");
		String data = dbFunctions.getAuditType(auditType[0]);	
		String[] auditType2 = dbFunctions.getAuditEventTypeId(date,smrProfile.getEmail(),"Business Submit Meter Read Email Sent Status: success");
		String data2 = dbFunctions.getAuditType(auditType2[0]);	
		System.out.println("Data:"+data);
		System.out.println("Data2:"+data2);
		System.out.println("Audit data is made in audit table as expected. Audit data: " +auditType[0]);
		System.out.println(Arrays.asList(auditType));
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType[0]+ " Audit event type is:"+data, data.trim().equalsIgnoreCase("BGBUSINESS_POST_SMR_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"PASS");
		Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+auditType2[0]+ " Audit event type is:"+data2, data2.trim().equalsIgnoreCase("BGBUSINESS_SMR_EMAIL_SENT_SUCCESS")?"PASS":"FAIL");
		Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType2[1],"PASS");
	}
	
	}
