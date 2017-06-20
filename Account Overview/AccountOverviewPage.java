package bg.framework.app.functional.page.Slingshot;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.AddAdditionalAccounts;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.GlobalErrorMessages;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RunQTP;
import bg.framework.app.functional.util.SiebelDataBase;
import bg.framework.app.functional.util.TestDataHelper;

import org.openqa.selenium.NoSuchElementException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class AccountOverviewPage extends BasePage {

	private final static String File_AccPage = "resources/Slingshot/AccountOverview.properties";
	private static final Properties PageProperties = null;
	private static Properties pageProperties = new PropertyLoader(File_AccPage).load();
	public static String InBanner;
	public static String Welcomemessage;
	public static String Lastlogindetails;
	public static String InBannervalue;
	public static String NewAccount;
	public static String Access;
	public static String AccessType="You have Super User access";


	public AccountOverviewPage() {

	}
	/*
// Verifying the values in the Bread Crumb in Account Overview Page

    public void verifyingForgotEmailBreadCrumb() {
        final String strBreadCrumbText = browser.getTextByXpath(pageProperties.getProperty("AccoOverviewPage.BreadCrumbID"));
        Report.updateTestLog("Displayed Bread Crumb Is  :" + strBreadCrumbText,Pass_Str);
    }

 // Verifying whether appropriate text is displayed in Account Overview Page once after logging with appropriate User Credentials

    public void verifyingMyAccount(UserProfile userProfile) {
    	accountOverviewTitleVerification();
    	//accountOverviewLogInUserValidation(userProfile);
    	//validateAccOverviewContents(userProfile);
//        final String strMyAccText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.MyAccountID"));
//        System.out.println(strMyAccText);
//        if (strMyAccText.trim().equals("Your accounts")){
//        	Report.updateTestLog("Expected Text was displayed : "+strMyAccText, Pass_Str);
//        }
//        else{
//        	Report.updateTestLog("Expected Text was not displayed", Fail_Str);
       // }
    }
    private void accountOverviewTitleVerification(){
    	browser.wait(2000);
    	String accountOverview= browser.getTextByXpath("//title");
    	if(accountOverview.trim().contains("Account overview")){
    		Report.updateTestLog("Account overview page displayed ", Pass_Str);
    	}else{
    		Report.updateTestLog("Account overview page not displayed ", Fail_Str);
    	}
    }
    public void AccountOverViewVerification(UserProfile userProfile){
    	//accountOverviewTitleVerification();
    	verifyPageTitle("accountSummary");
    	loggeduserVerification(userProfile.getUCRN());
    	crosscellVerification();
    }

// Verify whether logged in User's Last Name is displayed in Account Overview Page by validating against the Siebel database 
//  for the appropriate UCRN

    public void accountOverviewLogInUserValidation(UserProfile userProfile){
    	//verifyingForgotEmailBreadCrumb();
    	verifyPageTitle("Account overview");
    	verifyingMyAccount(userProfile);
    	loggeduserVerification(userProfile.getUCRN());    	
   	    String AccNumber = userProfile.getAccNumber();
    	verifyCustomerAddress( AccNumber);
    	validateAccOverviewContents(userProfile);
    	//verifyCQ5();
    }

    public void loggeduserVerification(String email){
    	browser.wait(1000);
    	final String strLastName = new SiebelDataBase().getLastName(email);
    	String loggedInUserName = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.UserId"));
    	String loggedInUserNameArray[] = loggedInUserName.split(" ");
    	if (loggedInUserNameArray[2].equalsIgnoreCase(strLastName)){
    		Report.updateTestLog("Expected Logged In User is displayed and the User Name Is: "+loggedInUserName, Pass_Str);
        }
        else{
        	Report.updateTestLog("Expected Logged In User "+strLastName+" was not displayed", Fail_Str);
        }
    }

// Verify the contents displayed in Account Overview Page 
    private void otherOfferCheck(String type){
    	String link1="";    	
    	browser.wait(2000);
    	if(browser.isElementVisibleWithXpath(AccPageProperties.getProperty("AccoOverviewPage.addAccountLink").replace("TYPE", type))){
    		link1=browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.addAccountLink").replace("TYPE", type));    	
    		if(link1.contains(type)){    		
    		Report.updateTestLog("Link for add "+type+" displayed", "PASS");}
    		else{
			Report.updateTestLog("Link for add "+type+" is not displayed", "FAIL");
		}    	
    	}else{
    		Report.updateTestLog("Cross sell section not displayed", "FAIL");
    	}
    }

    public void crosscellVerification(){
    	String accTypeText=null;
    	Set<String> accountType=new HashSet<String>();
    	int accountCount = browser.getChildElementsCountByXpath(AccPageProperties.getProperty("AccoOverviewPage.accountCount"));  
    	for(int count=1;count<=accountCount;count++){
    		accTypeText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.AccountTypeID").replace("ACC_NUM", ""+count));
    		accountType.add(accTypeText.trim());
    	}
    	System.out.println(accountType);
    	if(accountType.contains("Gas") && accountType.contains("Elect`ricity") && accountType.contains("Insurance & Repair")){
			otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellEnergy"));
		}else
			if(accountType.contains("Gas") && accountType.contains("Electricity")){
    		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellIns"));
    	}else
    		if(accountType.contains("Gas")){    		
    		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellEle"));
    			otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellIns"));
    			System.out.println("FI");
    	}else
    		if(accountType.contains("Electricity")){    	
    		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellGas"));
    		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellIns"));
    			System.out.println("FII");
    	}else 
    		if(accountType.contains("Energy")){    		
        		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellIns"));
        }else
        	if(!accountType.contains("Gas") && !accountType.contains("Electricity")){
        		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellEle"));
        		otherOfferCheck(AccPageProperties.getProperty("AccoOverviewPage.crossCellGas"));
        		System.out.println("FIII");
        }




    }
    public void validateAccOverviewContents(UserProfile userProfile){
    	final String expectedEmailAdd = new OnlineDBConnector().getUserEmail(userProfile.getUCRN());
    	final String accountNum = new SiebelDataBase().getAccountNumber(userProfile.getUCRN());
    	String nectarText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.NectarTextID"));
    	//String accTypeText="";
    	String manageAccText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.ManageAccID"));
    	String optionText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.OptionTextID"));
    	//String readOurText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.ArticleTextID"));
    	//String tariffText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.TariffTextID"));
    	//String offerText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.OfferID"));
    	String energySaverText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.EnergySaverID"));

    	final String[] AccOverviewContents = {nectarText,  manageAccText, energySaverText, 
    										  optionText};
    	    	for (int i=0; i < AccOverviewContents.length ; i++){
    		Report.updateTestLog("Displayed Accont Overview Text Is: "+AccOverviewContents[i], Pass_Str);    		
    	}
    	    	if(nectarText.toLowerCase().contains("Register".toLowerCase())){
        			verifyAndClickWithLinkText(nectarText, "Register link");
        			verifyPageTitle("Your Nectar points - British Gas");
        			browser.browserBack();
        		}
        		if(nectarText.toLowerCase().contains("Your Nectar points".toLowerCase())){
        			verifyAndClickWithLinkText(nectarText, "Your Nectar points");
        			verifyPageTitle("Your Nectar Points -British Gas");
        			browser.browserBack();
        		}
        		if(nectarText.toLowerCase().contains("Collect Nectar points".toLowerCase())){
        			verifyAndClickWithLinkText(nectarText, "Collect Nectar points");
        			verifyPageTitle("Collect");
        			browser.browserBack();
        		}
    	int AccTable = browser.getChildElementsCountByXpath(AccPageProperties.getProperty("AccoOverviewPage.CountID"));
    	System.out.println(AccTable+1);
    	for (int i=1; i < AccTable+1; i++){
    		String AccName = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.AccountName").replace("NUM", ""+i));
    		System.out.println(AccName);
        	Report.updateTestLog("Displayed Account Is: "+AccName, Pass_Str);
    	}
    	verifyAddMissingAccount();
    	//verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.MissingAccID"), "Missing Account Link");
    	//verifyAndInputByXpath(AccPageProperties.getProperty("AccoOverviewPage.CustRefNum"), "FirstName", "admin");
    	//verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.WhereCanIFindID"), "Missing Account Text");
//    	String toolTipText = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.MyAccountID"));
//    	
//    	if (toolTipText.trim() == "customer reference"){
//    		Report.updateTestLog("Expected Text was displayed : "+toolTipText, Pass_Str);
//    	} else {
//    		Report.updateTestLog("Expected Text was not displayed: "+toolTipText, Fail_Str);
//    	}

    	//verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CloseButtonID"), "Missing Account close button");

    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CompleteEnergyID"), "Energy Savers report");
    	 verifyPageTitle("Energy Savers Report - Save Energy - British Gas");

    	//verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.LogOutID"), "Log Out");



    }

    public void verifyCQ5(){
    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CQLoginId"), "Login");
    	verifyAndInputByXpath(AccPageProperties.getProperty("AccoOverviewPage.CQLoginName"), "FirstName", "admin");
        verifyAndInputByXpath(AccPageProperties.getProperty("AccoOverviewPage.CQLoginPwd"), "LastName","admin");
    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CQOKButton"), "OK");
    	browser.wait(30000);
    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CQEtc"), "Etc");
    	browser.wait(1000);
    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CQCentricaTools"), "Centrica Tools");
    	browser.wait(1000);
    	//browser.doubleClick(AccPageProperties.getProperty("AccoOverviewPage.CQErrorMsgMaint"));
    	browser.wait(30000);

    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CQOKButton"), "OK");

    	verifyAndInputByXpath(AccPageProperties.getProperty("AccoOverviewPage.CQUserId"), "FirstName", "admin");
        verifyAndInputByXpath(AccPageProperties.getProperty("AccoOverviewPage.CQPassword"), "LastName","admin");
    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.CQOK"), "OK");
    	int a = browser.getRowCountByXpath("//form[contains(@action,'errormessageoverrides')]//table");
    	int b = browser.getColCountByXpath("//form[contains(@action,'errormessageoverrides')]//table");
    	System.out.println(a);
    	System.out.println(b);
    	for (int i = 1; i < a; i++) {
    		String errorKey = browser.getTextByXpath("//form[contains(@action,'errormessageoverrides')]//table//tbody//tr["+i+"]//td[1]");
    		//System.out.println(errorKey);
    		if (errorKey.trim().equalsIgnoreCase("eshop.middleinitial.invalid")){
    			String errorKey1 = browser.getTextByXpath("//form[contains(@action,'errormessageoverrides')]//table//tbody//tr["+i+"]//td[2]");
    			System.out.println(errorKey1);
    			browser.inputByXpath("//form[contains(@action,'errormessageoverrides')]//table//tbody//tr["+i+"]//td[3]/textarea","thanks"); 
    		}

    }}



    public void verifyCustomerAddress(String accountNumber) {
        SiebelDataBase siebelDatabase = new SiebelDataBase();
        List<String> address = siebelDatabase.getAddress(accountNumber);
        System.out.println(address);
        String[] arrayaddress = (String[]) address.toArray(new String[0]);
        String houseno = arrayaddress[0];
        String addNum = arrayaddress[1];
        String addres = arrayaddress[2];
        String city = arrayaddress[3];
        String zipcode = arrayaddress[4];
        String number = "";
        String fulladdress = "";
        if (!(houseno==null)) {
            number = houseno;
        } else if (!(addNum==null)) {
            number = addNum;
        }

        String dbAddress= number + " " + addNum + " " + addres + " " + city + " " + zipcode;
       // if(ApplicationConfig.BRAND.equalsIgnoreCase("BG")){
        	String addressOneAdd = null;
            String addressTwoAdd = null;
        	int accountCount = browser.getChildElementsCountByXpath(AccPageProperties.getProperty("AccoOverviewPage.accountCount"));        	        	
        	String expectedAccountNum = accountNumber;
        		for (int i = 1; i <= accountCount; i++) {
        			String currentAccountNum ;
        			if(browser.isElementVisibleWithXpath(AccPageProperties.getProperty("AccoOverviewPage.accountNumber").replace("ACC_NUM", "" + i))){
        				currentAccountNum = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.accountNumber").replace("ACC_NUM", "" + i)).trim();
        				if (currentAccountNum.equalsIgnoreCase(expectedAccountNum)) {
        					addressOneAdd = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.addressLineOne").replace("ACC_NUM", "" + i));
        					addressTwoAdd = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.addressLineTwo").replace("ACC_NUM", "" + i));
        					fulladdress = addressOneAdd + addressTwoAdd;        					
        				}
                 }else{
        				currentAccountNum = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.accountNumberClose").replace("ACC_NUM", "" + i)).trim();
        				if (currentAccountNum.equalsIgnoreCase(expectedAccountNum)) {
        					addressOneAdd = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.addressLineOneClose").replace("ACC_NUM", "" + i));
        					addressTwoAdd = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.addressLineTwoClose").replace("ACC_NUM", "" + i));
        					fulladdress = addressOneAdd + addressTwoAdd;        					
                 }
        			}        	        		

        		}
        	if(fulladdress.contains(number) && fulladdress.contains(addres )&& fulladdress.contains(city) && fulladdress.contains(zipcode)){
         	   Report.updateTestLog("Address verification done with database successfull<br>"+
        	"Database address-<b>"+dbAddress+"</b><br>Page address-<b>"+fulladdress+"</b>", "PASS");
            }else{
         	   Report.updateTestLog("Address verification done with database is not successfull<br>"+
        	"Database address-<b>"+dbAddress+"</b><br>Page address-<b>"+fulladdress+"</b>", "FAIL");
            }
      //  }
//        else if(ApplicationConfig.BRAND.equalsIgnoreCase("Fusion")){
//        	fulladdress = browser.getTextByXpath(AccPageProperties.getProperty("AccoOverviewPage.address"));
//        	System.out.println(fulladdress);
//        	if(fulladdress.contains(number) && fulladdress.contains(addres )&& fulladdress.contains(city) && fulladdress.contains(zipcode)){
//         	   Report.updateTestLog("Address verification done with database successfull<br>"+
//        	"Database address-<b>"+dbAddress+"</b><br>Page address-<b>"+fulladdress+"</b>", "PASS");
//            }else{
//         	   Report.updateTestLog("Address verification done with database is not successfull<br>"+
//        	"Database address-<b>"+dbAddress+"</b><br>Page address-<b>"+fulladdress+"</b>", "FAIL");
//            }
       // }
        }

    public void logout() {
    	getWaitTime();
    	if(browser.isElementVisibleWithXpath(AccPageProperties.getProperty("AccoOverviewPage.Logout"))){
    	browser.clickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.Logout"));
    	Report.updateTestLog("User Logged out Successfully","PASS");	
    	}else{
    		Report.updateTestLog("Log out Unsuccessfull","FAIL");
    	}
//    	verifyAndClickWithXpath(AccPageProperties.getProperty("AccountOverviewPage.Logout"), "Log out");
    	verifyPageTitle("Login to Your Account - British Gas");
    }
    public void verifyAddMissingAccount(){
    	browser.clickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.MissingAccID"));
    	browser.wait(3000);
    	if(browser.isElementVisibleWithXpath(AccPageProperties.getProperty("AccoOverviewPage.MissingAccOverlay"))){
    		Report.updateTestLog("Add a Missing Account overlay displayed","PASS");	
    	}else{
    		Report.updateTestLog("Add a Missing Account overlay not displayed","FAIL");
    	}
    	browser.clickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.MissingAccOverlayclose"));    	
    }
    public void verifyBrowserback(){
        browser.browserBack();
      if (browser.isElementVisible(AccPageProperties.getProperty("AccoOverviewPage.MissingAccID"))){
            Report.updateTestLog("Account Overview page exists, Browser back working as expected","PASS");
        }else {
            Report.updateTestLog("Account Overview page not exists, page navigated to back page","FAIL");
      }
    }
    public void registerNectarPage() {
    	browser.clickWithXpath(AccPageProperties.getProperty("AccoOverviewPage.nectarregisterlink"));
    	if (browser.isElementVisibleWithXpath(AccPageProperties.getProperty("AccoOverviewPage.NectarPage"))){
            Report.updateTestLog("Nectar register page exists","PASS");
        }else {
            Report.updateTestLog("Nectar register page not exists","FAIL");
      }
    }

    public void verifyLogin(String lastName, String accNumver) {
        browser.wait(getWaitTime());
        String lastName2 = browser.getText(AccPageProperties.getProperty("AccoOverviewPage.Lastname"));
        if(lastName2.toLowerCase().contains(lastName.toLowerCase())){
          Report.updateTestLog("Lastname is present in application", "PASS");
        }
        else{
          Report.updateTestLog("Lastname not present in application", "FAIL");
        }

        verifyIsTextPresent(accNumver);

    }

	 */
	public void verifyAccountOverviewPage(){
		verifyPageTitle(pageProperties.getProperty("AccountOverviewPage.Title"));
		//verifyIsTextPresent(pageProperties.getProperty("AccountOverviewPage.Header"));

	}
	public void verifySearchFunctionality1(){
		if(browser.isTextPresent(pageProperties.getProperty("AccountOverviewPage.SearchSection"))){    		
			String strNumberOfAccounts = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.NumberOfAccounts"));
			String[] accounts = strNumberOfAccounts.split(" ");
			System.out.println("accounts----------------->"+accounts);
			Report.updateTestLog("The account contains search functionality, Since it has "+accounts[2]+" number of accounts", "PASS");
		}
		else{

		}
	}
	public void getCustomerReferenceNumber(UserProfile userProfile){
		browser.wait(getWaitTime());    	
		//String customerRefNumber = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber"), "p");
		String customerRefNumber1 = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber"));
		String strValue = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"));
		//String strValue1 = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"), "value");
		//String strValue2 = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"), "class='cust-info fleft'");
		//System.out.println("************"+strValue +"************"+strValue1+"************"+strValue2+"************"+customerRefNumber);
		System.out.println("*************"+strValue+"########"+userProfile.getaddr());
		if(strValue.contains(userProfile.getaddr())){
			System.out.println("post code : Pass");
			Report.updateTestLog("Post code: ", "PASS");
		}
		else{
			Report.updateTestLog("Post code: ", "FAIL");
		}
	}
	public void verifyCustomerRefNumber(UserProfile userProfile){
		String customerRefNumber= browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber"));
		System.out.println(customerRefNumber);
		String[] customerReferenceNumber = customerRefNumber.split(":");
		System.out.println(customerReferenceNumber + "******"+customerRefNumber );
		if(customerRefNumber.contains(userProfile.getAccNumber()))
		{
			Report.updateTestLog("Customer reference number displayed successfully: " +customerReferenceNumber[1], "PASS");
			System.out.println("Customer reference number displayed successfully: " +customerReferenceNumber[1]);
		}
		else{
			Report.updateTestLog("Customer reference number displayed successfully: " +customerReferenceNumber[1], "FAIL");
			System.out.println("Customer reference number displayed successfully: " +customerReferenceNumber[1]);
		}
	}

	public void verifySearchFunctionality(){
		if(!browser.isTextPresent(pageProperties.getProperty("AccountOverviewPage.SearchSection"))){
			Report.updateTestLog("Search Functionality is not displayed in Account Summary page", "PASS");
			System.out.println("PASS:Search Functionality is not displayed for this account");
		}
		else{
			Report.updateTestLog("Search Functionality is not displayed in Account Summary page", "FAIL");
			System.out.println("FAIL:Search Functionality is displayed for this account");
		}
	}
	public void verifyPagination(){
		if(!browser.isTextPresent(pageProperties.getProperty("AccountOverviewPage.PaginationLink"))){
			Report.updateTestLog("Pagination is not available for lessthan 5 account customer ", "PASS");
			System.out.println("PASS:Pagination function is not avail for this account");
		}
		else{
			Report.updateTestLog("Pagination is not avail for lessthan 5 account customer ", "FAIL");
			System.out.println("FAIL:Pagination function is not avail for this account");
		}
	}
	public void verifyPaginationForMorethan5Customer(){
		if(browser.isTextPresent(pageProperties.getProperty("AccountOverviewPage.PaginationLink"))){
			Report.updateTestLog("Pagination is available for morethan 5 account customer ", "PASS");
			System.out.println("PASS:Pagination is available for morethan 5 account customer ");
		}
		else{
			Report.updateTestLog("Pagination is available for morethan 5 account customer ", "FAIL");
			System.out.println("FAIL:Pagination is available for morethan 5 account customer ");
		}
	}
	public void verifySearchFunctionalitySection(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.SearchSection"))){
			Report.updateTestLog("Search Functionality is displayed for more than 15 account", "PASS");    		
			System.out.println("PASS:Search Functionality is displayed");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.SearchDropDown"), "Search by drop down");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.Keyword"), "Keyword");
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.SearchButton"), "Search button");
		}
		else{
			Report.updateTestLog("Search Functionality is displayed for more than 15 account", "FAIL");
			System.out.println("FAIL:Search Functionality is displayed");
		}
	}
	public void verifySearchDropDown(){
		String[] searchOptions = {"Please select","Account number","Billing address postcode","Site address postcode"};    								
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
	public void verifyDataInISU(String bpNumber){
		RunQTP qtp = new RunQTP();
		qtp.runQTP("", bpNumber);
	}
	public void verifyPaginationLink(){
		ArrayList<String> link = new ArrayList<String>();
		link.add("AccountOverviewPage.PrevLink");
		link.add("AccountOverviewPage.FirstButtonLink");
		link.add("AccountOverviewPage.SecondButtonLink");
		link.add("AccountOverviewPage.NextLink");
		for (String i : link) {
			System.out.println(i);
			verifyLink(i);
		}    	
	}
	private void verifyLink(String link) {
		String linkName = link.substring(link.indexOf('.') + 1, link.length());
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty(link))) {
			Report.updateTestLog(linkName + " Link Exist", "PASS");
		} else {
			Report.updateTestLog(linkName + " Link not Exist", "FAIL");
		}
	}
	public void clickAndVerifyPagination(){
		try{
			verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.NextLink"),"Next");
			browser.wait(getWaitTime());
			if(!browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.SecondButtonLink"))){
				Report.updateTestLog("Navigates to the next page, Enabling the particular page Number", "PASS");
			}
			else{
				Report.updateTestLog("Navigates to the next page, Enabling the particular page Number", "FAIL");	
			}
			verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.PrevLinkEnable"),"Prev");
			browser.wait(getWaitTime());
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.FirstButtonLink"))){
				Report.updateTestLog("Navigates to the Prevoius page, Enabling the particular page Number", "PASS");
			}
			else{
				Report.updateTestLog("Navigates to the Prevoius page, Enabling the particular page Number", "FAIL");	
			}
			verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.PaginateTransaction"), "Paginate transaction");
		}
		catch(Exception e){
			Report.updateTestLog("Exception occured", "FAIL");
		}
	}
	public void verifyPaginationOnBothSides(){
		Report.updateTestLog("Bottom Pagination exist",browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.PaginationOnBottomSide"))?"PASS":"FAIL");
	}
	public void selectContractAccountAndVerifySearchFunctionality(UserProfile userProfile){
		selectSearchByAndVerify("Account number",userProfile.getAccNumber());
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"))){
			int numberOfAcounts =   browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountOverviewPage.AccountListNumber"));
			System.out.println("Number of account----> "+numberOfAcounts);
			Report.updateTestLog("Number of accounts displayed: "+numberOfAcounts, "PASS");
			if(browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber")).contains(userProfile.getAccNumber()))
{
				Report.updateTestLog("Filter made for corresponding 'Account Number' : "+userProfile.getAccNumber(), "PASS");
			}
			else{
				Report.updateTestLog("Filter made for corresponding 'Account Number' : "+userProfile.getAccNumber(), "FAIL");
			}
		}
	}
	public void searchByBillingAddressAndVerifyAccounts(UserProfile userProfile){
		selectSearchByAndVerify("Billing address postcode",userProfile.getBillAddr());
		verifyBillingAddress(userProfile);

	}
	public void searchBySiteAddressAndVerifyAccounts(UserProfile userProfile){
		selectSearchByAndVerify("Site address postcode",userProfile.getSiteAddr());
		verifySiteAddress(userProfile);
	}
	public void selectSearchByAndVerify(String strSearchByOption,String strKeyword){
		verifyAndSelectDropDownBox(pageProperties.getProperty("AccountOverviewPage.SearchDropDownId"), "Search by", strSearchByOption);
		browser.wait(getWaitTime());
		verifyAndInputByXpath(pageProperties.getProperty("AccountOverviewPage.Keyword"), "Keyword", strKeyword);
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.SearchButton"), "Search button");
		browser.wait(getWaitTime());
		String jquery=("$(document).ready(function(){$('.accordion-head').trigger('click');});");        
		browser.executeScript(jquery);
		getWaitTime();

	}

	public void verifyBillingAddress(UserProfile userProfile){
		int numberOfAcounts =   browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountOverviewPage.AccountListNumber"));
		System.out.println("Number of account----> "+numberOfAcounts);
		int flg = 0;
		Report.updateTestLog("Number of accounts displayed for billing address "+userProfile.getBillAddr()+" is "+ numberOfAcounts, "DONE");
		for(int i=1; i<=numberOfAcounts;i++){	
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.BillingAddress")+i+"]/div[2]/p[2]")){
			String billingAddress  = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.BillingAddress")+i+"]/div[2]/p[2]");
			//String billingAddress  = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.BillingAddress")+i+"]/div[2]/p[2]", "p");
			String[] billingPostCode = billingAddress.split(",");
			System.out.println("billingPostCode0"+billingPostCode[0]);
			System.out.println("billingPostCode1"+billingPostCode[1]);
			System.out.println("billingPostCode2"+billingPostCode[2]);
			
			if(billingPostCode[1].trim().equalsIgnoreCase(userProfile.getBillAddr())){			
				flg = flg +1;
				System.out.println(flg);					
			}
			else{
				if(billingPostCode[2].trim().equalsIgnoreCase(userProfile.getBillAddr())){			
					flg = flg +1;
					System.out.println(flg);					
				}
				System.out.println(flg + "wrong flg");
				//Report.updateTestLog("Billing postcode present for this account", "FAIL");
			}
			
			
			int flg1 = numberOfAcounts/5;
			int flg2 = 0;
			//System.out.println(flg1+ "**********"+(numberOfAcounts%5));

			if(i==(flg1*1)||i==(flg1*2)||i==15||i==20||i==25||(i>25&&i<=numberOfAcounts)){			   		
				Report.updateTestLog("Billing post code "+billingPostCode[2]+" verified for "+numberOfAcounts+" accounts ", "PASS");
				clickNextPagination();
			}

		}
		System.out.println(flg);
		//Report.updateTestLog("Billing post code verification is done for "+numberOfAcounts+" accounts", (flg==numberOfAcounts)?"PASS":"FAIL");
		}
	}
	public void verifySiteAddress(UserProfile userProfile){
		int numberOfAcounts =   browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountOverviewPage.AccountListNumber"));
		System.out.println("Number of account----> "+numberOfAcounts);
		Report.updateTestLog("Number of accounts displayed for site address "+userProfile.getSiteAddr()+" is "+ numberOfAcounts, "DONE");
		if(numberOfAcounts==1){			
			//verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.SiteAddress1"),"SiteAddress");
			System.out.println(pageProperties.getProperty("AccountOverviewPage.AccountList"));
			String fullSiteAddress = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"));
			String[] sitePostCode = fullSiteAddress.split(":");
			System.out.println(sitePostCode[1]);
			String postCode = fullSiteAddress.substring((fullSiteAddress.length()-8), fullSiteAddress.length());			 
			Report.updateTestLog("Site address displayed with "+postCode+" post code", (postCode.equalsIgnoreCase(userProfile.getSiteAddr()))?"PASS":"FAIL");
			System.out.println(postCode);
			browser.wait(getWaitTime());			 
		}
		else{
			//int flg = 0;
			for(int i=1;i<=numberOfAcounts;i++){
				Report.updateTestLog("Number of accounts displayed: "+numberOfAcounts, "DONE");
				String fullSiteAddress = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.SiteAddress2")+i+"]");
				String postCode = fullSiteAddress.substring((fullSiteAddress.length()-8), fullSiteAddress.length());
				Report.updateTestLog("Site address displayed with "+postCode+" post code", (postCode.equalsIgnoreCase(userProfile.getSiteAddr()))?"PASS":"FAIL");

				System.out.println(postCode);
				browser.wait(getWaitTime());
				if(i==5||i==10||i==15||i==20||i==25){			   		
					Report.updateTestLog("Site post code verification is done for "+i+" account ", "PASS");
					clickNextPagination();
				}

			}
		}

	}
	public void getSiteAddress(UserProfile userProfile,int numberOfAccounts,String xpath){
		for(int i=1; i<=numberOfAccounts;i++){	
			String siteAddress  = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.SiteAddressPostCode")+i+"]/div[7]/div/div/p");
			//String billingAddress  = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.BillingAddress")+i+"]/div[2]/p[2]", "p");
			String[] sitePostCode = siteAddress.split(",");
			if(sitePostCode[3].trim().equalsIgnoreCase(userProfile.getSiteAddr())){			
				/*flg = flg +1;
			System.out.println(flg);*/					
			}
			else{
				//System.out.println(flg + "wrong flg");
				Report.updateTestLog("Billing postcode present for this account", "FAIL");
			}
			if(numberOfAccounts>=25){			
				if(i==5||i==10||i==15||i==20||i==25){		   		
					clickNextPagination();
				}}}
	}

	public void clickNextPagination(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.NextLinkXpath"))){
			verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.NextLinkXpath"),"Next pagination link");
			browser.wait(getWaitTime());
		}
		else{
			Report.updateTestLog("Next pagination is not available", "PASS");
		}
	}

	public void verifyAccountDetails(UserProfile userProfile){
		try{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"))){
				if(browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber")).contains(userProfile.getAccNumber())){
					Report.updateTestLog("Customer reference number: "+userProfile.getAccNumber(), "PASS");
				}
				else{
					System.out.println("Customer reference number: "+userProfile.getAccNumber());
					Report.updateTestLog("Customer reference number: "+userProfile.getAccNumber(), "FAIL");
				}
				String strTypeOfAccount = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.TypeOfAccount"));
				System.out.println("strTypeOfAccount"+strTypeOfAccount);
				Report.updateTestLog("Type of customer account : "+strTypeOfAccount, "PASS");/*
				String billingAddr = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerBillingAddress"));
				System.out.println("billingAddr"+billingAddr);
				Report.updateTestLog("The customer billing address: "+billingAddr, "PASS");*/
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AccountBalance"))){
					String accountBalance = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.AccountBalance"));
					String[] balance=accountBalance.split(":");
					System.out.println(accountBalance+"balance"+balance+"accountBalance"+balance[1]);
					Report.updateTestLog("Customer account balance : "+balance[1], "PASS"); 			
				}
				verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLinknew").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
			}
			else{
				Report.updateTestLog("Test data error", "WARN");
			}
		}
		catch(Exception e){
			Report.updateTestLog("Exception occured: "+e, "FAIL");
		}}

	public void accountNumberErrorValidation(){
		String[] contractAccountNumber = {"","600ga46%4","600456789"};
		for(int i=0; i<contractAccountNumber.length;i++){
			String strSearchBy = "Account number";
			switch(i){
			case 0:
				selectSearchByAndVerify(strSearchBy,contractAccountNumber[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_BlankContractAccountNumber);
				break;
			case 1:
				selectSearchByAndVerify(strSearchBy,contractAccountNumber[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_ContractAccountNumberWithAlbhabets);
				break;
			case 2:
				selectSearchByAndVerify(strSearchBy,contractAccountNumber[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_InvalidContractAccountNumber);
				break;
			}
		}

	}

	public void billingPostCodeErrorValidation(){
		String[] billingPostCode = {"","BSDF %AY","DF37 11AY"};
		for(int i=0; i<billingPostCode.length;i++){
			String strSearchBy = "Billing address postcode";
			switch(i){		
			case 0:
				selectSearchByAndVerify(strSearchBy,billingPostCode[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_BlankBillingAddress);
				break;
			case 1:
				selectSearchByAndVerify(strSearchBy,billingPostCode[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_BillingAddressWithAlbhabets);
				break;
			case 2:
				selectSearchByAndVerify(strSearchBy,billingPostCode[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_InvalidBillingAddress);
				break;
			}
		}
	}
	public void SitePostCodeErrorValidation(){
		String[] sitePostCode = {"","DEAC 4%H","DE00 4NH"};
		for(int i=0; i<sitePostCode.length;i++){
			String strSearchBy = "Site address postcode";
			switch(i){		
			case 0:
				selectSearchByAndVerify(strSearchBy,sitePostCode[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_BlankSiteAddress);
				break;
			case 1:
				selectSearchByAndVerify(strSearchBy,sitePostCode[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_SiteAddressWithAlbhabets);
				break;
			case 2:
				selectSearchByAndVerify(strSearchBy,sitePostCode[i]);
				errorMessageComparison(SlingshotErrorMessages.SlingShot_AccOverview_InvalidSiteAddress);
				break;
			}
		}
	}
	public void errorMessageComparison(final String expectedErrorMsg) {
		try{
			final String displayedErrorMsg = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.ErrorMessage"));
			verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);}
		catch(Exception e){
			Report.updateTestLog("Exception occured"+e, "FAIL");
		}
	}
	public void verifyErrorMessage(final String displayedErrorMsg, final String expectedErrorMsg) {
		System.out.println(displayedErrorMsg);
		System.out.println(expectedErrorMsg);
		if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
			Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "PASS");
		} else {
			Report.updateTestLog("Error message validation. Expected message:" +expectedErrorMsg+" Actual message:"+ displayedErrorMsg, "FAIL");
		}
	}
	public void verifyUsefulLinks(){
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.QuickLinks"))){
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.SubmitMeterReadLink"))){
				verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.SubmitMeterReadLink"), "Submit meter read");			
				Report.updateTestLog("Submit meter read page displayed successfully", (browser.isTextPresent("Submit meter read"))?"PASS":"FAIL");	
				browser.browserBack();
				browser.wait(getWaitTime());
			}
			else{
				Report.updateTestLog("Please check submit meter read link", "FAIL");
			}
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.ViewYourBillsLink"))){
				verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ViewYourBillsLink"), "View your bill");			
				Report.updateTestLog("View your bill page displayed successfully", (browser.isTextPresent("View bill"))?"PASS":"FAIL");			
			}
			else{
				Report.updateTestLog("Please check View your bill link", "FAIL");		
			}
		}
	}
	public void verifyBrowserBackFunctionality(){
		browser.browserBack();
		browser.wait(getWaitTime());
		//if(browser.getTitle().equalsIgnoreCase("Account overview")){
		Report.updateTestLog("Browser remains in same page as we expect",(browser.getTitle().equalsIgnoreCase("Account overview"))?"PASS":"FAIL");
	}
	public void verifyDataWithQTP(UserProfile userProfile){
		/*RunQTP runQtp = new RunQTP();
	runQtp.runQTP("\\VFIPFFIL008\\sundarg1\\Desktop\\SlingshotWorkspace\\Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\AccountOverview.vbs", userProfile.getBpnumber());
		 */
		
		System.out.println("RUN QTP");
		RunQTP runQTP = new RunQTP();

		runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\AccountOverView.vbs", userProfile.getBpnumber());

		browser.wait(15000);

		String contractAccountNumber = null;
		String billingAddress = null;
		//String siteAddress =null;         
		String billingPostCode = null;
		String siteAddress = null;
		String sitePostCode = null;
		String balance = null;
		try {
			File file1 = new File("C:\\SAPData\\Sample.txt");        
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			contractAccountNumber = br.readLine();
			billingAddress = br.readLine();
			billingPostCode = br.readLine();
			siteAddress = br.readLine();
			sitePostCode = br.readLine();
			balance = br.readLine();   
			if(browser.getTitle().equalsIgnoreCase("Account overview")||browser.getTitle().equalsIgnoreCase("Account summary")){				
			browser.browserBack();
			browser.wait(getWaitTime());
			String accountNumber = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber"));
			String contAccNumber = accountNumber.substring(accountNumber.length()-9,(accountNumber.length()));
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.CustomerBillingAddress"))){
			String billingAddressFull = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerBillingAddress"));
			String billingPostCode1 = (String) billingAddressFull.subSequence(billingAddressFull.length()-8, (billingAddressFull.length()));
			System.out.println("Billing Address1: "+billingPostCode1);
			System.out.println("Billing Address2: "+billingAddress);
			if(billingAddress.contains(billingAddressFull)){
				Report.updateTestLog("Billing address verification is done.Billing address displayed in application: "+billingAddressFull+" Actual billing address in SAP ISU: "+billingAddress, "PASS");
			}
			else{
				Report.updateTestLog("Billing address verification is done.Billing address displayed in application: "+billingAddressFull+" Actual billing address in SAP ISU: "+billingAddress, "FAIL");      
			}
			}
			else{
				Report.updateTestLog("Billing address is not available for this customer", "DONE");
				}
			String fullSiteAddress = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.SiteAddressPostCode2"));
			String sitePostcode = fullSiteAddress.subSequence(fullSiteAddress.length(), 0).toString();
			System.out.println("Account Number:1 "+accountNumber);
			System.out.println("Account Number:2 "+contractAccountNumber);
			if(contractAccountNumber.contains(contAccNumber)){
				Report.updateTestLog("Verified that details displyed in front end is same as details present backend SAP ISU.Account number displayed in application: "+contAccNumber+" Actual account number in SAP ISU: "+contractAccountNumber, "PASS");
			}
			else{
				Report.updateTestLog("Verified that details displyed in front end is same as details present backend SAP ISU.Account number displayed in application: "+contAccNumber+" Actual account number in SAP ISU: "+contractAccountNumber, "FAIL");
			}			

			System.out.println("Site Address1: "+sitePostcode);
			System.out.println("Site Address2: "+siteAddress);
			if(siteAddress.contains(sitePostcode)){
				Report.updateTestLog("Site address verification is done. Site address displayed in application:  "+sitePostcode+" Actual site address in SAP ISU: "+siteAddress, "PASS");
			}
			else{
				Report.updateTestLog("Site address verification is done. Site address displayed in application:  "+sitePostcode+" Actual site address in SAP ISU: "+siteAddress, "FAIL");      
			}

			System.out.println("Account balance: "+balance);
			String accBal = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.AccountBalance")).replace("$", ".");
			//System.out.println("Account balance : "+accBal);
			System.out.println("accblanace : "+accBal);
			if(accBal.contains(balance)){
				Report.updateTestLog("Account balance verification is done. Account balance displayed in application: "+accBal+" Actual account balance displayed in application: "+balance, "PASS");
			}
			else{
				Report.updateTestLog("Account balance verification is done. Account balance displayed in application: "+accBal+" Actual account balance displayed in application: "+balance, "FAIL");      
			}
			br.close();
			// br1.close();
			// br2.close();
			}
		} catch (IOException e) {
			System.out.println("Exception "+e);
		}/*
    String meterText= browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.meterRead"));
//    int meterCount=browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountSummaryPage.meterRead"));
//    System.out.println(meterCount);
//    if(meterCount==2){
//     meterText = meterText+" "+browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.meterRead")+"[2]");
//    }
    System.out.println(meterText);
    if (meterText.contains(meterdate)) {
        Report.updateTestLog("Meter Date " + meterdate + " is displayed", "PASS");
    } else {
        Report.updateTestLog("Meter Date " + meterdate + " is not displayed", "FAIL");
    }
    meterText=meterText.replaceAll("\n", " ");
    if(ApplicationConfig.BRAND.equalsIgnoreCase("fusion")){
   	 meterText=meterText.replaceAll(" ", "");
    }
    System.out.println(meterText);
    if (meterText.contains(meterread)) {
        Report.updateTestLog("Meter Read " + meterread + " is displayed", "PASS");
    } else {
        Report.updateTestLog("Meter Read " + meterread + " is not displayed", "FAIL");
    }


    if (browser.isTextPresent(balance)) {
        Report.updateTestLog("Balance amount " + balance + " is displayed", "PASS");
    } else {
        Report.updateTestLog("Balance amount " + balance + " is not displayed", "FAIL");
    }
    String paymentType = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.payType1"));

    if (paymentType.contains("Direct Debits")) {
        paymentType = "Direct Debit";
    }
    if (paymentType.contains("Credit/Debit card")) {
        paymentType = "Credit/Debit card";
    }
    System.out.println(paymentType);
    if (browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.payType1")).contains(paymentType)) {
        Report.updateTestLog("Payment type " + paymentType + " is displayed", "PASS");
    } else if (browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.payType1")).contains(paymentType)) {
        Report.updateTestLog("Payment type " + paymentType + " is displayed", "PASS");
    } else {
        Report.updateTestLog("Payment type " + paymentType + " is not displayed", "FAIL");
    }

    String tarrifinPage=browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.tarrif"));
    if(tarrifinPage.trim().contains(tarrif))
    {
   	 Report.updateTestLog("The tarrif  " + tarrif + " is displayed", "PASS"); 
    }else
    {
    Report.updateTestLog("The tarrif  " + tarrif + " is not displayed", "FAIL");
    }*/

	}

	public void clickMPDLink(UserProfile userProfile){
		verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.ManageAccountLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));
		verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.MPDLinkXpath"), "MPD Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Update your details");
	} 
	public void clickManageAccountLink(UserProfile userProfile){
		Report.updateTestLog("account Overview Page", "WARN");
	    verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLinknew").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
		//verifyAndClickWithXpath(".//*[@id='BGBAccountList']/div[6]/div[4]/a","Manage Account");
	   System.out.println("  " +((pageProperties.getProperty("AccountOverviewPage.ManageAccountLinknew").replace("ACCOUNTNUMBER", userProfile.getAccNumber())))) ;
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.PageTitle"));
	} 
	
	public void clickManageAccountLink1(SMRAccountDetails smrAccountDetails){
		Report.updateTestLog("account Overview Page", "WARN");
	    verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLinknew").replace("ACCOUNTNUMBER", smrAccountDetails.getAccountNumber()), "Manage account link");
		//verifyAndClickWithXpath(".//*[@id='BGBAccountList']/div[6]/div[4]/a","Manage Account");
	   System.out.println("  " +((pageProperties.getProperty("AccountOverviewPage.ManageAccountLinknew").replace("ACCOUNTNUMBER", smrAccountDetails.getAccountNumber())))) ;
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.PageTitle"));
	} 
	
	public void getNumberAccountsDisplayed(){
		int numberOfAcounts =   browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountOverviewPage.AccountListNumber"));
		System.out.println("Number of account----> "+numberOfAcounts);
		Report.updateTestLog(numberOfAcounts+" number of account displayed for logged in user.", "DONE");
				}
	public void clickAndVerifyManageAccountLink(SMRAccountDetails smrAccountDetails){
		verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLink").replace("ACCOUNTNUMBER", smrAccountDetails.getAccountNumber()), "Manage account link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewBillLink"), "View bill link");
		verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewYourLastBillLink"), "View your last bill link");
	}
	public void clickAndVerifyManageAccountLink(UserProfile userProfile){
		verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLinknew").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));
		
	}
	public void verifyAccOverview_StdUser(){
		if(browser.isElementVisibleWithXpath("AccountOverviewPage.MessageCenterLink")){
			Report.updateTestLog("user is able to view the your messages section", "PASS");
		}
		else{
			Report.updateTestLog("user is able to view the your messages section", "FAIL");
		}
		
	}
	 public void verifyQuickLinks_stdUser(){
	 		ArrayList<String> list = new ArrayList<String>();
	 		list.add("AccountOverviewPage.SubmitMeterReadLink");
	 		list.add("Sumit meter read");
	 		list.add("AccountOverviewPage.ViewYourBills");
	 		list.add("View bill");	 	
	 		list.add("AccountOverviewPage.AccountReports");
	 		list.add("Account reports");
	 		list.add("AccountOverviewPage.HelpLink");
	 		list.add("Help & advice");
	 		
	 		for (int i=0;i<list.size();i=i+2) {
	 			verifyLinksandTitle(list.get(i),list.get(i+1));
	 		}
	 	}
	 	
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
		}
		public void verifyAccOverview_SuperUser(){
			if(browser.isElementVisibleWithXpath("AccountOverviewPage.MessageCenterLink")){
				Report.updateTestLog("user is able to view the your messages section", "PASS");
			}
			else{
				Report.updateTestLog("user is able to view the your messages section", "FAIL");
			}
			if(browser.isElementVisibleWithXpath("AccountOverviewPage.PaperlessBilling")){
				Report.updateTestLog("Paperless billing POD is displayed for Super user", "PASS");
			}
			else{
				Report.updateTestLog("Paperless billing POD is displayed for Super user", "FAIL");
			}
		}
		 public void verifyQuickLinks_SuperUser(){
		 		ArrayList<String> list = new ArrayList<String>();
		 		list.add("AccountOverviewPage.SubmitMeterReadLink");
		 		list.add("Sumit meter read");
		 		list.add("AccountOverviewPage.ViewYourBills");
		 		list.add("View bill");	 	
		 		list.add("AccountOverviewPage.Manageusers");
		 		list.add("Manage users");
		 		list.add("AccountOverviewPage.HelpLink");
		 		list.add("Help & advice");
		 		
		 		for (int i=0;i<list.size();i=i+2) {
		 			verifyLinksandTitle(list.get(i),list.get(i+1));
		 		}
		 	}
		 public void verifyAccOverview_SuperUserEIDA(){
				if(browser.isElementVisibleWithXpath("AccountOverviewPage.MessageCenterLink")){
					Report.updateTestLog("user is able to view the your messages section", "PASS");
				}
				else{
					Report.updateTestLog("user is able to view the your messages section", "FAIL");
				}
				if(browser.isElementVisibleWithXpath("AccountOverviewPage.PaperlessBilling")){
					Report.updateTestLog("Paperless billing POD is displayed for Super user EIDA customer", "PASS");
				}
				else{
					Report.updateTestLog("Paperless billing POD is displayed for Super user EIDA customer", "FAIL");
				}
			}
		 public void VerifyRenewaldueButton() {
				browser.wait(3000);
				verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Renewalduebutton"),"Renewal Button");
				browser.wait(getWaitTime());		
			}
			 public void verifyQuickLinks_SuperUserEIDA(){
			 		ArrayList<String> list = new ArrayList<String>();
			 		list.add("AccountOverviewPage.SubmitMeterReadLink");
			 		list.add("Sumit meter read");
			 		list.add("AccountOverviewPage.ViewYourBills");
			 		list.add("View bill");	 	
			 		list.add("AccountOverviewPage.Manageusers");
			 		list.add("Manage users");
			 		list.add("AccountOverviewPage.AccountReports");
			 		list.add("Account reports");
			 		list.add("AccountOverviewPage.DashBoard");
			 		list.add("Dash board");
			 		list.add("AccountOverviewPage.HelpLink");
			 		list.add("Help & advice");
			 		
			 		for (int i=0;i<list.size();i=i+2) {
			 			verifyLinksandTitle(list.get(i),list.get(i+1));
			 		}
			 	}
			 public void VerifyCarePlanBanner() {
					//verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Renewalduebutton"),"Renewal Button");
					browser.wait(getWaitTime());
					InBanner = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.BannerAmount"));
					Report.updateTestLog("the CarePlan Value for the gas value is: "+ InBanner, "WARN");
					
				}
			 public void VerifyCarePlanlink() {
				 browser.wait(getWaitTime());
						verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Careplanlink01"), "CarePlan link");
					       Report.updateTestLog("carePlan Link is clicked successfully", "PASS"); 
						}
			 public void VerifyCarePlanpage() {
				 browser.wait(getWaitTime());
				 //browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.CarePlanPage"));
				 verifyIsTextPresent("Get a quote and order Careplan");
			}
			 
			 public void Verifywelcomemessage(){
				 Report.updateTestLog("Account Overview Page " , "WARN");
				 Welcomemessage = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.welcomemessage"));
				 Lastlogindetails = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.Lastlogin"));
				 Report.updateTestLog("Welcome Message is populated successfully" +Welcomemessage ,"PASS");
				 Report.updateTestLog("Login details is populated successfully" +Lastlogindetails ,"PASS");
				 
				 
		}

			 
			 public void VerifyAdditionalrequestLink(){
				Report.updateTestLog("Account overview is loaded successfully", "WARN");
			   	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Additionalaccountrequest"), "Click Additional account request Link");
			   	browser.wait(getWaitTime());
			   	Report.updateTestLog("AddAdditional Account overvlay is displayed successfully", "WARN");
			   	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Additionalaccountrequestoverlay"), "Click Continue button");
			   	browser.wait(getWaitTime());
			   } 
			 
			 public void VerifyAdditionalAccounts(UserProfile userProfile){
					
				 //verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.AddAccountNumber"),"AccountNumber","608489488");
				 verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.AddAccountNumber"),"AccountNumber",userProfile.getaddAccount());
				 verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.SitePostcode"),"Post Code", userProfile.getPostCode());
				 verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.Telephonenumber"),"Telephone Number", userProfile.getMobileNumber());
				 Report.updateTestLog("Add New Accounts is updated successfully", "WARN");
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.AddAccountsubmitButton"), "Click submit button");
				 browser.wait(getWaitTime());
				 Report.updateTestLog("Confirmation page is displayed successfully", "WARN");
				 browser.wait(getWaitTime());
				   } 
			 
			 public void verifyEntry_In_additional_accounts_table(UserProfile userProfile) {
				 
				 String Accountnumber=userProfile.getaddAccount();
				    OnlineDBConnector dbFunctions =new OnlineDBConnector();
				    String Date=dbFunctions.DBsysdateDdmonyyhhmi();
				    System.out.println(" "+Date  );
				    String Result=dbFunctions.verifyAdditionalAccounts(Date,userProfile.getaddAccount());
				    		    
				    if (Accountnumber.equals(Result)){
				     	  Report.updateTestLog("New account Number is Successfully updated in the bgb_ta_add_additional_accounts TABLE", "PASS");
				    }
				    else {
				  	  Report.updateTestLog("New account Number is not updated", "FAIL");
				  	  
				    }
				  				    
				}	
			 public void VerifyAdd_removeAccount(UserProfile userProfile){
					
				 verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.AddAccountNumber"),"AccountNumber",userProfile.getaddAccount());
				 verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.SitePostcode"),"Post Code", userProfile.getPostCode());
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.AddAnotherAccountLink"), "Click Another Account");
				 Report.updateTestLog("Add New Accounts is updated successfully", "WARN");
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.RemoveAccount"), "Click Remove Account");
				 browser.wait(getWaitTime());
				 Report.updateTestLog("Account Removed successfully", "WARN");
				 browser.wait(getWaitTime());
				   } 
			 public void VerifyAdd50AdditionalAccounts(UserProfile userProfile){
				 
				 for(int i=1;i<=51;i++){	
					 
					 AddAdditionalAccounts AddAdditionalAccounts =new TestDataHelper().getAddAdditionalAccounts("Account"+i);
					 
					enterAccountDetails(AddAdditionalAccounts,i);
					 submitAccount(i);
					 //verifyCardAdd(i);
				 }
				 
				 verifyAndInputByXpath(pageProperties.getProperty("AccountSummaryPage.Telephonenumber"),"Telephone Number", userProfile.getMobileNumber());
				 Report.updateTestLog("New Accounts is updated successfully", "WARN");
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.AddAccountsubmitButton"), "Click submit button");
				 browser.wait(getWaitTime());
				 Report.updateTestLog("Confirmation page is displayed successfully", "WARN");
				 browser.wait(getWaitTime());
				   }
		
			 public void enterAccountDetails(AddAdditionalAccounts AddAccounts,int i){
				 
				 browser.inputByXpath(pageProperties.getProperty("AccountSummaryPage.AddAccountNumber").replace("NUM", (i)+""),AddAccounts.getAccountnumber());
				 browser.inputByXpath(pageProperties.getProperty("AccountSummaryPage.SitePostcode").replace("NUM", (i)+""),AddAccounts.getPostcode());
				 browser.wait(1000);

				}
			 public void submitAccount(int i){
				 if(i<51)
				 {
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.AddAnotherAccountLink"), "Click Another Account");
				 }
				 browser.wait(1000);

				}
			 
			 public void verifyCardAdd(int i){
					
					if (browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.NewAddedAccounts").replace("NUM", i+""))){
						Report.updateTestLog("Card Details added successfully", "PASS");	
					}
					else{
						Report.updateTestLog("Card Details not added", "FAIL");	
					}
				}
			 
			 
 public void AccountOverViewSMRQuickNavigation(UserProfile userProfile){
				 
				 System.out.println("im cameeeeeeeeeee");
				 browser.wait(5000);
				 
				 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewSMRQuickLink"))){
					 
					 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr"+" "+"SMR Link is present");
					verifyAndClickWithXpath(pageProperties.getProperty("AccountOverViewSMRQuickLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "SMR Link");
					String S=browser.getTitle();
					String Title="Submit Meter Read";
					if(S.equalsIgnoreCase(Title)){
						System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
						Report.updateTestLog("SMR Link clicked and navigated to SMR Page Sucessfully", "WARN");
					}
					
					browser.wait(2000);
					browser.browserBack();
					 		
				 }
				 else{
					 
					 Report.updateTestLog("SMR Link is not present", "PASS");
				 }
			 }
			 
			 
				 
				 public void AccountOverViewQuickNavigationViewBill(UserProfile userProfile){
					 
					 System.out.println("Imm inn VIew billll ");
					 
					 
					 
					 browser.wait(10000);
                     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewViewBillQuickLink"))){
					 
					 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr"+" "+"View Bill is present");
					verifyAndClickWithXpath(pageProperties.getProperty("AccountOverViewViewBillQuickLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "View Bill Link");
					String S=browser.getTitle();
					String Title="View Bill";
					if(S.equalsIgnoreCase(Title)){
						System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
						Report.updateTestLog("View Bill Link clicked and navigated to View Bill Page Sucessfully", "WARN");
					}
					
					browser.wait(2000);
					browser.browserBack();
					 		
				 }
				 else{
					 
					 Report.updateTestLog("ViewBill Link link not present", "PASS");
				 }
                     
                     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewViewBillQuickLink"))){
                     
                     }
                     
			 }
				 
				 
 public void AccountOverViewQuickNavigationtoViewBill(UserProfile userProfile){
					 
					 System.out.println("Imm inn VIew billll ");
					 
					 
					 
					 browser.wait(10000);
                     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewViewBillQuickLink"))){
					 
					 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr"+" "+"View Bill is present");
					verifyAndClickWithXpath(pageProperties.getProperty("AccountOverViewViewBillQuickLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "View Bill Link");
					String S=browser.getTitle();
					String Title="View Bill";
					if(S.equalsIgnoreCase(Title)){
						System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
						Report.updateTestLog("View Bill Link clicked and navigated to View Bill Page Sucessfully", "WARN");
					}
					
					browser.wait(2000);
				
					 		
				 }
				 else{
					 
					 Report.updateTestLog("ViewBill Link link not present", "PASS");
				 }
                     
                     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewViewBillQuickLink"))){
                     
                     }
                     
			 }
				 
				 
				 
				 
				 
public void AccountOverViewQuickNavigationMAP(UserProfile userProfile){
					 
					 browser.wait(10000);
                    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewMakeAPayment").replace("ACCOUNTNUMBER", userProfile.getAccNumber()))){
                    	
                    	
					 
					 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr"+" "+"MAP Link is present");
					verifyAndClickWithXpath(pageProperties.getProperty("AccountOverViewMakeAPayment").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "MAP Link");
					String S=browser.getTitle();
					String Title="Payment Details";
					if(S.equalsIgnoreCase(Title)){
						System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
						Report.updateTestLog("Make a Payment Link clicked and navigated to MAP Page Sucessfully", "WARN");
					}
					
					browser.wait(2000);
					browser.browserBack();
					 		
				 }
				 else{
					 
					 Report.updateTestLog("MAP Link not present", "PASS");
				 }

           

			 }

public void AccountOverViewQuickNavigationtoviewMAP(UserProfile userProfile){
	 
	 browser.wait(10000);
   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverViewMakeAPayment").replace("ACCOUNTNUMBER", userProfile.getAccNumber()))){
   	
   	
	 
	 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr"+" "+"MAP Link is present");
	verifyAndClickWithXpath(pageProperties.getProperty("AccountOverViewMakeAPayment").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "MAP Link");
	String S=browser.getTitle();
	String Title="Payment Details";
	if(S.equalsIgnoreCase(Title)){
		System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		Report.updateTestLog("Make a Payment Link clicked and navigated to MAP Page Sucessfully", "WARN");
	}
	
	browser.wait(2000);

	 		
}
else{
	 
	 Report.updateTestLog("MAP Link not present", "PASS");
}



}


public void AccountOverViewQuickNavigationRenewals(){
	 
			 browser.wait(10000);
			 try{
				 			 
   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.RenewalsoVerviewLinksection"))){
   	   	
	 
	 System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr"+" "+"Renewal Link is present");
	 
	 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.RenewalsoVerviewLinksection"),"Renewal Section");
	 
	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.RenewalsoVerviewLink"), "Renewals Link");
	browser.wait(8000);
	String Sty=browser.getTitle();
	String Title="Renew online today and save";
	if(Sty.equalsIgnoreCase(Title)){
		System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		Report.updateTestLog("Renewals Link clicked and navigated to Renewal Page Sucessfully", "WARN");
		browser.wait(2000);
	}
   }
			 }  	
	
	 		
catch(Exception E)
{
	Report.updateTestLog("Renewal Link not present", "PASS");
}
}



public void pricePlanDetails() {
	
	String Plan=browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.RenewalFixedPlan"));
	
	Report.updateTestLog("Renewal Plan is::"+ Plan +"", "WARN");

	
}





public void AccountOverviewRightPromo(){
	browser.wait(5000);
	

	
/*	
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequest"))){
		 
		 System.out.println(" im in additional request ");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequest"), "Additional Account Request");
		 browser.wait(1000);
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequestOverlay"), "Overlay Displayed");
		 browser.wait(2000);
		 
		 String Sr=browser.getTitle();
			String Title="Add additional accounts";
			if(Sr.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			}
		 
		 		 
			Report.updateTestLog("Additional Account Section is displayed", "WARN");
			browser.wait(2000);
			browser.browserBack();`
	 }
	 
	 else {
		 Report.updateTestLog("Additional Account Section is not displayed", "FAIL");
		 
	 }*/
	 
	
	 
	 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.contactus"))){
		 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.contactus"), "Contact Us link");
		 
		 browser.wait(2000);
		System.out.println(" im nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		 
	
		 browser.wait(2000);
		 String Sri=browser.getTitle();
			String Title="Contact us";
			if(Sri.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
				Report.updateTestLog("Contact Us Section is displayed", "WARN");
			}
		 
		 		 
			
			browser.wait(2000);
			browser.browserBack();
			 browser.wait(2000);
 }
	 
	 else {
		 Report.updateTestLog("Contact Us Section is not displayed", "FAIL");
		 
	 }
 
 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.HelpandAdviseoverview"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.HelpandAdviseoverview"), "Help And Advise link ");
	 
	 browser.wait(2000);
	 
	 String Sri=browser.getTitle();
		String Title="Help & advice";
		if(Sri.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			Report.updateTestLog("Help and Advise page is displayed", "WARN");
		}
	 
	 		 
		
		browser.wait(2000);
		browser.browserBack();
		 browser.wait(2000);
 }
 
 else {
	 Report.updateTestLog("Help and advise page is not displayed", "FAIL");
 }
 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.mpd"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.mpd"), "MPD tool tip link");
	 
	 browser.wait(2000);
	 
	 String Srii=browser.getTitle();
	String Title="Update your details";
		if(Srii.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("MPD Section is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
		 browser.wait(2000);
 }
 
 else {
	 Report.updateTestLog("MPD Section is not displayed", "FAIL");
	 
 }
 
 /*if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.quotegas"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.quotegas"), "GAS Quote tool tip link Displayed");
	 
	 String Sriie=browser.getTitle();
	String Title="Get a business energy quote";
		if(Sriie.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("GAS Quote is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
 }
 
 else {
	 Report.updateTestLog("GAS Quote is not displayed", "FAIL");
	 
 }*/
 
 /*if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.quoteelec"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.quoteelec"), "Electricity Quote tool tip link Displayed");
	 
	 String Sriie=browser.getTitle();
	String Title="Get a business energy quote";
		if(Sriie.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("Electricity Quote is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
 }
 
 else {
	 Report.updateTestLog("Electricity Quote is not displayed", "FAIL");
	 
 }*/

 
 if (AccessType.equalsIgnoreCase(Access)) {
 	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageUsers"), "Manage users link");
	 browser.wait(2000);
	 
	 
	 String Sriiey=browser.getTitle();
	String Title="Manage users";
		if(Sriiey.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			Report.updateTestLog("Managers users link is displayed", "WARN");
		}
	 
		browser.wait(2000);
		browser.browserBack();
		 browser.wait(8000);

 }
 
 else {
	 Report.updateTestLog("Managers user link is not displayed", "FAIL");
	 
 }
 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequest"))){
	 
	 System.out.println(" im in additional request ");
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequest"), "Additional Account Request");
	 browser.wait(1000);
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequestOverlay"), "Overlay Displayed");
	 browser.wait(2000);
	 String Sr=browser.getTitle();
		String Title="Add additional accounts";
		if(Sr.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("Additional Account Section is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
		browser.wait(2000);
 }
 
 else {
	 Report.updateTestLog("Additional Account Section is not displayed", "FAIL");
	 
 }
 browser.wait(5000);
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.careplanBannerinoverview"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.careplanBannerinoverview"), "Careplan banner link");
	 
	 browser.wait(2000);
	 
	 String Sriiey=browser.getTitle();
	String Title="Gas appliance service –Careplan 1";
		if(Sriiey.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			Report.updateTestLog("Careplan Page is displayed", "WARN");
		}
	 
		browser.wait(2000);
		browser.browserBack();
		browser.wait(2000);
 }
 
 else {
	 Report.updateTestLog("Careplan page is not displayed", "FAIL");
	 
 }
 
 browser.wait(8000);
 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.paperlessbillingpromo"))){
	 
	 Report.updateTestLog("Paperless Billing Promo Section is displayed", "PASS");
 }
 
 else {
	 Report.updateTestLog("Paperless Billing Promo Section not displayed", "FAIL");
 }
	 

 
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.helpandadvice"))){
		 
		 
			Report.updateTestLog("Help and Advice complete Section is displayed", "PASS");
	 }
	 
	 else {
		 Report.updateTestLog("Help and advice complete Section is not displayed", "FAIL");
		 
	 }
	 	
}


public void AccountOverviewRightPromoStandardUsers(){
	browser.wait(5000);
		
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequest"))){
		 
		 System.out.println(" im in additional request ");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequest"), "Additional Account Request");
		 browser.wait(1000);
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AdditionalAccountRequestOverlay"), "Overlay Displayed");
		 browser.wait(2000);
		 String Sr=browser.getTitle();
			String Title="Add additional accounts";
			if(Sr.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			}
		 
		 		 
			Report.updateTestLog("Additional Account Section is displayed", "WARN");
			browser.wait(2000);
			browser.browserBack();
	 }
	 
	 else {
		 Report.updateTestLog("Additional Account Section is not displayed", "FAIL");
		 
	 }
	 
	
	 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.contactus"))){
		 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.contactus"), "Contact Us link Displayed");
		 
		 String Sri=browser.getTitle();
			String Title="Contact us";
			if(Sri.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			}
		 
		 		 
			Report.updateTestLog("Contact Us Section is displayed", "WARN");
			browser.wait(2000);
			browser.browserBack();
	 }
	 
	 else {
		 Report.updateTestLog("Contact Us Section is not displayed", "FAIL");
		 
	 }
	 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.mpd"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.mpd"), "MPD tool tip link Displayed");
	 
	 String Srii=browser.getTitle();
	String Title="Update your details";
		if(Srii.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("MPD Section is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
 }
 
 else {
	 Report.updateTestLog("MPD Section is not displayed", "FAIL");
	 
 }
 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.quotegas"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.quotegas"), "GAS Quote tool tip link Displayed");
	 
	 String Sriie=browser.getTitle();
	String Title="Get a business energy quote";
		if(Sriie.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("GAS Quote is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
 }
 
 else {
	 Report.updateTestLog("GAS Quote is not displayed", "FAIL");
	 
 }
 
 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.quoteelec"))){
	 
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.quoteelec"), "Electricity Quote tool tip link Displayed");
	 
	 String Sriie=browser.getTitle();
	String Title="Get a business energy quote";
		if(Sriie.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		}
	 
	 		 
		Report.updateTestLog("Electricity Quote is displayed", "WARN");
		browser.wait(2000);
		browser.browserBack();
 }
 
 else {
	 Report.updateTestLog("Electricity Quote is not displayed", "FAIL");
	 
 }
	 
	 

	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.helpandadvice"))){
		 
		 
			Report.updateTestLog("Help and Advice Section is displayed", "WARN");
	 }
	 
	 else {
		 Report.updateTestLog("Help and advice Section is not displayed", "FAIL");
		 
	 }
	 
	
}




public void AccountOverViewlink(UserProfile userProfile){
	 
	 browser.wait(10000);
    if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AreYourDetailsUptoDate"))){
	 
    Report.updateTestLog("Account OVerview Page is loaded sucessfully", "WARN");
    	
	Access=browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.AccessType"));
	System.out.println("  "+Access );
	Report.updateTestLog("Access Type Displayed is::"+Access+"" , "PASS");
	
	verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.AreYourDetailsUptoDate"), "Update Personal Detail");
	String S=browser.getTitle();
	String Title="Update your details";
	if(S.equalsIgnoreCase(Title)){
		System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
	}
	Report.updateTestLog("Update Personal Detail link clicked and navigated to Update details Page Sucessfully", "WARN");
	browser.wait(2000);
	browser.browserBack();
	 		
}
else{
	 
	 Report.updateTestLog("Account OVerview Page not displayed", "FAIL");
}



}





   public void Morethan15(String strSearchByOption,String strKeyword){
	UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");  
	String AccountNumber=userProfile.getAccNumber();
	
	verifyAndSelectDropDownBox(pageProperties.getProperty("AccountOverviewPage.SearchDropDownId"), "Search by", strSearchByOption);
	browser.wait(getWaitTime());
	
	System.out.println("eeeeeeeeeeeeeeeeeeegggggggggggggggggggggggggggggggg"+" "+strKeyword);
	System.out.println("eeeeeeeeeeeeeeeeeeegggggggggggggggggggggggggggggggg"+" "+ pageProperties.getProperty("AccountOverviewPage.Keyword"));
	
	verifyAndInputByXpath(pageProperties.getProperty("AccountOverviewPage.Keyword"),"Account Number",userProfile.getAccNumber());
	
	//browser.inputByXpath(".//*[@id='keywordToSearch']", "hihhh");
	/*WebElement element = browser.getElementByXpath(pageProperties.getProperty("AccountOverviewPage.SearchButton"));
	element.sendKeys(strKeyword);*/
	browser.wait(getWaitTime());
	verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.SearchButton"), "Search button");
	
	//browser.wait(getWaitTime());
	browser.wait(10000);
	
}

   public void VerifycustomerReferenceNumber(){
		browser.wait(getWaitTime());
		String Text = "Customer reference number:";
		String strCust = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferencenumber"));
		System.out.println(strCust);
		if (strCust.trim().equals(Text)){
			Report.updateTestLog("Customer reference number Text is displayed successfully: " +strCust, "PASS");
       }
       else{
       	Report.updateTestLog("Customer reference number Text is not displayed successfully", "Fail");
    }
		
		
	}
   
   
/////////////////////////////////////Account Summary Verification////////////////////////////////////////////////////////////////////////////////////////////////
public void AccountSummaryVerification (UserProfile userProfile) {
	browser.wait(3000);
	/* String Acc=browser.getTitle();
	 String Titles="Account summary";
	 if(Acc.equalsIgnoreCase(Titles)){
	  System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
	  
	  Report.updateTestLog("Account Summary Page is loaded", "WARN");
	  
			}
	 else {
		  Report.updateTestLog("Account Summary Page is not loaded", "FAIL");
		 
	 }*/
	
	summaryPagelink();
	 
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.SMR").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
		 
		System.out.println(  "  got === "+pageProperties.getProperty("AccountSummaryPage.SMR").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ) );
		verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.SMR").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "SMR link");
		
		browser.wait(5000);
		String SMR=browser.getTitle();
		String Title="Submit meter read";
		if(SMR.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			Report.updateTestLog("SMR Page displayed sucessfully", "WARN");
		}
		
		browser.wait(2000);
		browser.browserBack();
		browser.wait(2000);
		 		
	 }
	 else{
		 
		 Report.updateTestLog("SMR page is not displayed", "FAIL");
	 }
	 
	 
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.Billing").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
		 
		 
			verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Billing").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Billing link");
			String Cards=browser.getTitle();
			String Title="Billing";
			if(Cards.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
				Report.updateTestLog("View Bill Page displayed sucessfully", "WARN");
			}
			
			browser.wait(3000);
			//browser.browserBack();
			browser.wait(3000);
		 }
		 else{
			 
			 Report.updateTestLog("View Bill page is not displayed", "FAIL");
		 }
	 
	 
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.StatementOfAccount").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
		 
		 
			verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.StatementOfAccount").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Statement of Account");
			String Cards=browser.getTitle();
			String Title="Statement of account";
			if(Cards.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
				Report.updateTestLog("Statement of Account Page displayed sucessfully", "WARN");
			}
			
			browser.wait(3000);
			browser.browserBack();
			browser.wait(3000);
		 }
		 else{
			 
			 Report.updateTestLog("Statement of Account page is not displayed", "FAIL");
		 }
	 
	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.SDD").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
		 
		 
			verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.SDD").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Direct Debit link");
			
			browser.wait(3000);
			
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.MDDOverlay"))){
				
				Report.updateTestLog("As you already made one change to your direct debit overlay diaplayed", "PASS");
				
				
			}
			
			
			String Cards=browser.getTitle();
			String Title="Set up a Direct Debit";
			if(Cards.equalsIgnoreCase(Title)){
				System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
				Report.updateTestLog("Setup Direct debit Page displayed sucessfully", "WARN");
			}
			
			browser.wait(3000);
			//browser.browserBack();
			browser.wait(3000);
		 }
		 else{
			 
			 Report.updateTestLog("Manage cards page is not displayed", "FAIL");
		 }
	 
	 

if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.SavedCards").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
		 
	browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.SavedCards").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Save Cards link");
		browser.wait(3000);
		String Cards=browser.getTitle();
		String Title="Manage your saved payment cards";
		if(Cards.equalsIgnoreCase(Title)){
			System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
			Report.updateTestLog("Manage Cards Page displayed sucessfully", "WARN");
		}
		
		browser.wait(3000);
		//browser.browserBack();
		browser.wait(3000);
	 }
	 else{
		 
		 Report.updateTestLog("Manage cards page is not displayed", "FAIL");
	 }
	
if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.EnergyUsage").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
	 
	browser.wait(3000);
	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.EnergyUsage").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Energy usage link");
	browser.wait(3000);
	String Cards=browser.getTitle();
	String Title="Your energy consumption";
	if(Cards.equalsIgnoreCase(Title)){
		System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		Report.updateTestLog("Energy Consumption Page displayed sucessfully", "WARN");
	}
	
	browser.wait(3000);
	//browser.browserBack();
	browser.wait(3000);
 }
 else{
	 
	 Report.updateTestLog("Energy Consumption page is not displayed", "FAIL");
 }

if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.PaymentSummary"))){
	 
	 
	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.PaymentSummary"), "Paymentlink");
	String Comaplint=browser.getTitle();
	String Title="Payment details";
	if(Comaplint.equalsIgnoreCase(Title)){
		System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		Report.updateTestLog("Payment Page displayed sucessfully", "WARN");
	}
	
	browser.wait(2000);
	//browser.browserBack();
	browser.wait(3000);
 }


if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.Moving").replace("ACCOUNTNUMBER", userProfile.getAccNumber() ))){
	 
	browser.wait(3000);
	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Moving").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Moving link");
	browser.wait(3000);
	String Moving=browser.getTitle();
	String Title="Moving out";
	if(Moving.equalsIgnoreCase(Title)){
		System.out.println("ssssssssssssssss"+" "+"Page Title Matches");
		Report.updateTestLog("Moving Out Page displayed sucessfully", "WARN");
	}
	
	browser.wait(3000);
	browser.browserBack();
	browser.wait(3000);
	 		
 }
 else{
	 
	 Report.updateTestLog("Moving Out page is not displayed", "FAIL");
 }



if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.MPDsummaryPage"))){
	 
	Report.updateTestLog("Update your details link displayed sucessfully", "PASS");

	browser.wait(3000);
	 		
 }
 else{
	 
	 Report.updateTestLog("Update your details link is not displayed", "FAIL");
 }

if (AccessType.equalsIgnoreCase(Access)) {


	
	verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.ManageUsers"),"Manage Users");

	 
	Report.updateTestLog("Manage Users link displayed sucessfully", "PASS");

	browser.wait(3000);
} 		
 
 else{
	 
	 Report.updateTestLog("Manage users link not displayed", "FAIL");
 }
	



if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.ContactUsPage"))){
	 
	Report.updateTestLog("Contatc us link displayed sucessfully", "PASS");	 		
 }
 else{
	 
	 Report.updateTestLog("Contact us link not displayed", "FAIL");
 }

if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpandAdvisePage"))){
	 
	Report.updateTestLog("Help and Advise link displayed sucessfully", "PASS");	 		
 }
 else{
	 
	 Report.updateTestLog("Help and advise  link not displayed", "FAIL");
 }



	
		
}
   

public void summaryPagelink( ) {
	
	
String Balance=browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.Balance"));
System.out.println("  "+Balance );
Report.updateTestLog("Account Balance Displayed is::"+Balance+"" , "PASS");


	
if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.LastBill"))){
	 
	Report.updateTestLog("View your Last Bill Displayed in the application", "PASS");
	browser.wait(2000);

	 		
 }
 else{
	 
	 Report.updateTestLog("View your Last Bill is not displayed", "FAIL");
 }



if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.Paperless"))){
	 
	Report.updateTestLog("Paperless Billing Section Displayed in the application", "PASS");
	browser.wait(2000);
	
	 		
 }
 else{
	 
	 Report.updateTestLog("Paperless Billing Section is not displayed", "FAIL");
 }



/*if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.GAQ"))){
	 
	Report.updateTestLog("GAQ link is Displayed in the application", "PASS");

	
	 		
 }
 else{
	 
	 Report.updateTestLog("GAQ link is not displayed", "FAIL");
 }*/
if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.EstimatedSMR"))){
	 
	Report.updateTestLog("Estimated SMR link Displayed in the application", "PASS");

	 		
 }
 else{
	 
	 Report.updateTestLog("Estimated SMR is not displayed", "FAIL");
 }	

	
}
     public void VerifyAccountOverviewDashboardpage(){
    	  
    	  browser.wait(2000);
    	  
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection")))
    	 {
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.DDBreadCrumb"),"BreadCrumb");
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.DDname"),"Account Holder name displayed");
    		 verifyIsTextPresent("What would you like to do today?");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDYourAccount"),"Your Account section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDMeterread"),"Submit Meter read section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDBilling"),"Billing section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDPayments"),"payments section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDmoving"),"Moving Home section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDGAQ"),"GAQ section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDmanageusers"),"Manage Users section");
    		 verifyIsElementVisibleById(pageProperties.getProperty("AccountOverview.DDMPD"),"Update Deatils section"); 
    		 Report.updateTestLog("Account Overview DashBoard page is Displayed", "WARN");
    		
    	 }    
    	 
    	 else
    	 {
    		 Report.updateTestLog("Account Overview new dashboard page is not displayed", "FAIL");
    	 }	
}
			
    public void NavigatetoBillingLandingpage()
    {
    	browser.wait(2000);
   	 
   	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.Viewyourbillsection")))
   	 {
   		 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.Viewyourbillsection"),"Biling pod");
   		 browser.wait(2000);
   		 Report.updateTestLog("Billing landing page is Displayed", "WARN");
   		VerifyAccountOverviewDashboardBilling();
    }
   	 else
	 {
		 Report.updateTestLog("Billing Pod is not displayed", "FAIL");
	 }
   	
   }    
    
	public void VerifyAccountOverviewDashboardBilling()
     {
    	 
    		 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Billingheading")))
    		 {
    			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.Viewyourbillsectpod"),"View Your bills");
    			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.Viewyourstatementpod"),"View Your Statement");
    			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
    			 VerifyAccountOverviewDashboardBilling_ViewYorBill();
    			 VerifyAccountOverviewDashboardBilling_ViewYorStatement();
       		 }
    	 
    	 else
    	 {
    		 Report.updateTestLog("Billing Dashboard Landing page is not displayed", "FAIL");
    	 }
     }
     
     public void VerifyAccountOverviewDashboardBilling_ViewYorBill()
     {
    	 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.Viewyourbillsectpod"),"View Bill Statment");
		 browser.wait(5000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.VerifyVeiwYourLastBillTitle"),"View Bill");
		 Report.updateTestLog("View Bill Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Billingheading"),"Returned Back To Billing Landing Page");
		 browser.wait(2000);
     }
     
     public void VerifyAccountOverviewDashboardBilling_ViewYorStatement()
     {
    	 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.Viewyourstatementpod"),"View Your Statment");
		 browser.wait(5000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.VerifyStatementOfAccTitle"),"Statement Page Title");
		 Report.updateTestLog("Statement of Account Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Billingheading"),"Returned Back To Billing Landing Page");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(2000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Dashboard page Displayed Succeesfully");
		 browser.wait(2000);
     }
     
     public void NavigatetoSMRLandingpage()
     {
     	browser.wait(2000);
    	 
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.SMRSection")))
    	 {
    		 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.SMRSection"),"SMR Pod");
    		 browser.wait(2000);
    		 Report.updateTestLog("Submit Meter read landing page is Displayed", "WARN");
    		VerifyAccountOverviewDashboardSMR();
     }
    	 else
 	 {
 		 Report.updateTestLog("Submit Meter Read Pod is not displayed", "FAIL");
 	 }
    	
    }    
     
     private void VerifyAccountOverviewDashboardSMR() {
    	 
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.SMRheading")))
		 {
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.SMReadingPOD"),"Submit Meter read");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.SMEnergyUsagePOD"),"Energy Usage");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
			 VerifyAccountOverviewDashboardBilling_SMR();
			 VerifyAccountOverviewDashboardBilling_EnergyUsage();
   		 }
	 
	 else
	 {
		 Report.updateTestLog("Submit Meter Read Dashboard Landing page is not displayed", "FAIL");
	 }
 }
     
	private void VerifyAccountOverviewDashboardBilling_SMR() {
	
    	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.SMReadingPOD"),"Submit Meter Read");
		 browser.wait(5000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.VerifySMRTitle"),"Submit Meter Read Title");
		 Report.updateTestLog("Submit Meter Read Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.SMRheading"),"Returned Back To SMR Landing Page");
		 browser.wait(2000);
		
	}
	
	private void VerifyAccountOverviewDashboardBilling_EnergyUsage() {
		
	   	     verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.SMEnergyUsagePOD"),"Energy Usage");
			 browser.wait(5000);
			 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.VerifyEnergyConsumptionTitle"),"Energy Usage Title");
			 Report.updateTestLog("Energy Usage Page", "WARN");
			 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
			 browser.wait(5000);
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.SMRheading"),"Returned Back To SMR Landing Page");
			 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
			 browser.wait(2000);
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Dashboard page Displayed Succeesfully");
			 browser.wait(2000);
		
	}
     
	public void NavigatetoUpdateYourDetailsLandingpage()
     {
     	browser.wait(2000);
    	 
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.UpdateyourDetailssection")))
    	 {
    		 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.UpdateyourDetailssection"),"Update Your Details pod");
    		 browser.wait(2000);
    		 Report.updateTestLog("Update Your Details landing page is Displayed", "WARN");
    		VerifyAccountOverviewDashboardUpdateYourDetails();
     }
    	 else
 	 {
 		 Report.updateTestLog("Update Your Details Pod is not displayed", "FAIL");
 	 }   	
   }
     
     public void VerifyAccountOverviewDashboardUpdateYourDetails()
     {
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.UpdateYourDeatilstitle")))
    			
    	 {
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.UYDpod"),"Update Your Details");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.UYDalertspod"),"Alerts Details");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
			 
			 VerifyAccountOverviewDashboardUpdateYourDetails_firstPOD();
    			 }    
    	
    		 else
    	 	 {
    	 		 Report.updateTestLog("Update Your Details landing page is not displayed", "FAIL");
    	 	 }   	
    	 
     }
     public void VerifyAccountOverviewDashboardUpdateYourDetails_firstPOD()
     {
    	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.UYDpod"),"Update Your Details");
		 browser.wait(3000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.UpdateDetailsTitile"),"Update your Details");
		 Report.updateTestLog("Update your Details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.UpdateYourDeatilstitle"),"Returned Back To Update bank Deatils Landing Page");
		 browser.wait(2000);   
		 VerifyAccountOverviewDashboardUpdateYourDetails_SecondPOD();
     }
     
     public void VerifyAccountOverviewDashboardUpdateYourDetails_SecondPOD()
     {
     verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.UYDalertspod"),"Update Your Details Alerts Section");
	 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.UpdateDetailsTitile"),"Update your Details");
	 Report.updateTestLog("Update your Details Page For Alerts", "WARN");
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
	 browser.wait(5000);
	 verifyIsElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.UpdateYourDeatilstitle"),"Returned Back To Update bank Deatils Landing Page");
	 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
	 browser.wait(2000);
	 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Dashboard Page");
	 browser.wait(2000);
     }
     
     public void NavigatetoMovingHomeLandingPage()
     {
    	 
    	 browser.wait(2000);
    	 
    	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.MovingHomesection")))
    	 {
    		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.MovingHomesection"),"Moving Home Details");
    		 browser.wait(2000);
    		 Report.updateTestLog("Moving Home page is Displayed", "WARN");
    		 VerifyAccountOverviewDashboardMovingHome();
     }
    	 else
 	 {
 		 Report.updateTestLog("Moving Home Pod is not displayed", "FAIL");
 	 }   	
    	 
     }
     
     public void VerifyAccountOverviewDashboardMovingHome()
     {
		 browser.wait(3000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountOverview.MovingHomeOutpagetitle"),"Moving Out");
		 Report.updateTestLog("Moving Out Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
		 Report.updateTestLog("Dashboard page displayed", "WARN");
		 browser.wait(2000);   
     }
     
     public void NavigatetoManageUsers()
     {
     browser.wait(2000);
    	 
         try
        {
    	 if(browser.isElementVisibleWithXpathenable(pageProperties.getProperty("AccountOverview.ManageUsersdiable")))
    	 {
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"DashBoard Page");
    		 Report.updateTestLog("DashBoard page is displayed", "WARN");
    		 Report.updateTestLog("Manage Users section is not displayed due to not Super User", "Done");
         }
    	 else{
    		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.ManageUsers"),"Manage Users");
    		 browser.wait(2000);
    		 Report.updateTestLog("Manage Users page is displayed", "WARN");
    		 VerifyAccountOverviewManageusers();
    	 }
   }
    	 catch(Exception e) 	 { 		 
 		 
 	 }   
    	 
   }
	public void VerifyAccountOverviewManageusers() {
		
		 browser.wait(3000);
		 verifyAndClickWithXpath(pageProperties.getProperty("AcountOverview.ManageUsersOverlay"),"Overlay Closed");
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.ManageUserpagetitle"),"Manage Users Title");
		 Report.updateTestLog("Manage Users Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
		 Report.updateTestLog("Dashboard page displayed", "WARN");
		 browser.wait(2000);
		
	}
	
	
	 public void NavigatetoPayments()
     {
     browser.wait(2000);
    	 
   try
 {
    	 if(browser.isElementVisibleWithXpathenable(pageProperties.getProperty("AccountOverview.PaymentsDisable")))
    	 {
    		 Report.updateTestLog("DashBoard page is displayed", "WARN");
    		 Report.updateTestLog("Payment POD is disabled due to not Super User", "Done");
         }
    	 else{
    		 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.PaymentSection"),"Payment");
    		 browser.wait(2000);
    		 Report.updateTestLog("Manage Your Payments Landing Page", "WARN");
    		 VerifyAccountOverviewPaymentLandingPage();
    	 }
   }
    	 catch(Exception e) 	 { 		 
 		 
 	 }   
    	 
   }
	public void VerifyAccountOverviewPaymentLandingPage() {
		browser.wait(2000);
		
		 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.PaymentHeading")))
    	 {
    		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.MAPpod"),"Make A payment");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.ManageCardsPod"),"Manage Saved cards");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.ManageDDPod"),"Manage Direct Debit");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");			 
			 VerifyAccountOverviewPaymentLandingPage_makeApayment();    
			 VerifyAccountOverviewDashboardUpdateYourDetails_manageYourcards();
			 VerifyAccountOverviewDashboardUpdateYourDetails_manageDD();
			 }    
    	
    		 else
    	 	 {
    	 		 Report.updateTestLog("Update Your Details landing page is not displayed", "FAIL");
    	 	 }  	
		
	}
	
	
	/****need to check****/
	public void VerifyAccountOverviewPaymentLandingPage_makeApayment() {
		try
		{
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.MAPpod")))
		{
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.MAPpod"),"Make A Payment");
		 browser.wait(3000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountSummaryPage.makeAPaymentLinkTitle"),"Payment Details");
		 Report.updateTestLog("Payment details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.PaymentHeading"),"Returned Back To Manage payment Landing Page");
		 browser.wait(2000); 		 
		}
		else
		{
			Report.updateTestLog("Payment section not displayed due to Direct Debit customer", "WARN");
		}
		
	}
		catch(Exception e)
		{
			
		}
	}
	
	public void VerifyAccountOverviewDashboardUpdateYourDetails_manageYourcards() {
		
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.ManageCardsPod"),"Manage Your Cards");
		 browser.wait(3000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountOverview.ManageDDcardetails"),"Manage Your Card Details");
		 Report.updateTestLog("Manage Your Card details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.PaymentHeading"),"Returned Back To Manage payment Landing Page");
		 browser.wait(2000);   
		
	}
	
	public void VerifyAccountOverviewDashboardUpdateYourDetails_manageDD() {
	
		try
		{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.managesetupDD")))
			{
		
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.ManageDDPod"),"Setup Direct Debit");
		 browser.wait(3000);
		 verifyIsTextPresent(pageProperties.getProperty("AccountOverview.ManagesetupDDtitle"),"Manage Your Card Details");
		 Report.updateTestLog("Setup Direct Debit details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.PaymentHeading"),"Returned Back To Manage payment Landing Page");
		 browser.wait(2000); 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(2000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
		 Report.updateTestLog("Dashboard page displayed", "WARN");
		 browser.wait(2000);
		 
		}
			else
			{
				verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.ManageDDPod"),"Manage Direct Debit");
				 browser.wait(3000);
				 verifyIsTextPresent(pageProperties.getProperty("AccountOverview.ManageDDtitle"),"Manage Direct Debit Details");
				 Report.updateTestLog("Manage Direct Debit details Page", "WARN");
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
				 browser.wait(5000);
				 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.PaymentHeading"),"Returned Back To Manage payment Landing Page");
				 browser.wait(2000);  
				 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
				 browser.wait(2000);
				 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
				 Report.updateTestLog("Dashboard page displayed", "WARN");
				 browser.wait(2000);
			}
	}
		catch(Exception e)
		{
			
		}
	}
	
	public void NavigatetoGetAQuotePage()
    {
    	browser.wait(2000);
   	 
   	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Accountoverview.GAQSection")))
   	 {
   		 verifyAndClickWithXpath(pageProperties.getProperty("Accountoverview.GAQSection"),"GAQ Pod");
   		 browser.wait(2000);
   		 Report.updateTestLog("Get A Quote landing page is Displayed", "WARN");
   		VerifyAccountOverviewDashboardGetAQuote();
    }
   	 else
	 {
		 Report.updateTestLog("Get A Quote Pod is not displayed", "FAIL");
	 }
   	
   }
	
	public void VerifyAccountOverviewDashboardGetAQuote() {
	
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQheading")))
   	 {
   		      verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQenergyquotePod"),"Get A Energy Quote");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQboilerquotePod"),"Get A Boiler Quote");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQservicequotePod"),"Get A Service Quote");
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");			 
			 VerifyAccountOverviewPaymentLandingPage_getaEnergyQuote();    
			 VerifyAccountOverviewPaymentLandingPage_getaBoilerQuote();
			 VerifyAccountOverviewPaymentLandingPage_getaserviceQuote();
	}    
   	
   		 else
   	 	 {
   	 		 Report.updateTestLog("Update Your Details landing page is not displayed", "FAIL");
   	 	 }  	
		
	}
	public void VerifyAccountOverviewPaymentLandingPage_getaEnergyQuote() {
	 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.GAQenergyquotePod"),"Get A Energy Quote");
		 browser.wait(3000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQenergyquoteTitle"),"Get A Energy Quote");
		 Report.updateTestLog("Get A Energy Quote details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQheading"),"Returned Back To Get A Qoute Landing Page");
		 browser.wait(2000);   
	} 
	
	public void VerifyAccountOverviewPaymentLandingPage_getaBoilerQuote() {
		 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.GAQboilerquotePod"),"Get A Boiler Quote");
		 browser.wait(3000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQBoilerquoteTitle"),"Get A Boiler Quote Title");
		 Report.updateTestLog("Get A Boiler Quote details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQheading"),"Returned Back To Get A Qoute Landing Page");
		 browser.wait(2000);   
	}   
	
	public void VerifyAccountOverviewPaymentLandingPage_getaserviceQuote() {
		 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.GAQservicequotePod"),"Get A Service Quote");
		 browser.wait(3000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQServicequoteTitle"),"Get A Service Quote Title");
		 Report.updateTestLog("Get A Service Quote details Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.GAQheading"),"Returned Back To Get A Qoute Landing Page");
		 browser.wait(2000);   
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(2000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
		 Report.updateTestLog("Dashboard page displayed", "WARN");
		 browser.wait(2000);
	}  
	
	public void VerfiNavigatetoAccountSummarypage()
    {
    	browser.wait(2000);
   	 try
   	 {
   	 if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Accsummaryid")))
   	 {
   		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.Accsummaryid"),"Your Accounts Section");
   		 browser.wait(2000);
   		 Report.updateTestLog("Account summary page is Displayed", "WARN");
   		 VerifyAccountOverviewsummarypage();
    } 
   	 else if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Accsummaryidrenewals")))
	 {
   		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.Accsummaryidrenewals"),"Your Accounts Renewals Section");
  		 browser.wait(2000);
  		 Report.updateTestLog("Account summary Landing Page is Displayed", "WARN");
  		 VerifyAccountOverviewlandingpage();
	 }
   	
   }
   	 catch(Exception e)
   	 {
   		 
   	 }
    }
	
	private void VerifyAccountOverviewsummarypage() {
		
		 browser.wait(3000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.PageTitlenew"),"Account Summary");
		 Report.updateTestLog("Account Summary Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
		 Report.updateTestLog("Dashboard page displayed", "WARN");
		 browser.wait(2000); 
	}
	
	private void VerifyAccountOverviewlandingpage() {
		
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.landingpageheading")))
	   	 {
	   		     verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.landingpageaccount"),"Your Account");
				 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.landingpagerenewals"),"Renewals");
				 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");			 
				 VerifyAccountOverviewsummarypage_landingpage();    
				 VerifyAccountOverviewRenealspage_landingpage();			
		}    
	   	
	   		 else
	   	 	 {
	   	 		 Report.updateTestLog("Update Your Details landing page is not displayed", "FAIL");
	   	 	 }  	
			
		}
	
	  
	private void VerifyAccountOverviewsummarypage_landingpage() {
		   
		     verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.landingpageaccount"),"Your Acount");
			 browser.wait(3000);
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.PageTitlenew"),"Account Summary");
			 Report.updateTestLog("Account Summary Page", "WARN");
			 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
			 browser.wait(5000);
			 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.landingpageheading"),"Returned Back To Account Summary Landing Page");
			 browser.wait(2000); 
	}
	
	 private void VerifyAccountOverviewRenealspage_landingpage() {
		 
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.landingpagerenewals"),"Renewals");
		 browser.wait(3000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.landingRenewalsheading"),"Renewals Heading");
		 Report.updateTestLog("Renewals Page", "WARN");
		 verifyAndClickWithXpath(pageProperties.getProperty("AccountOverview.navigation"),"Toggle");
		 browser.wait(5000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.landingpageheading"),"Returned Back To Account Summary Landing Page");
		 browser.wait(2000);
		 verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverview.Dashboardsection"),"Returned Back To DashBoard Page");
		 Report.updateTestLog("Dashboard page displayed", "WARN");
		 browser.wait(2000);
		 
			
		}
	
}
