package bg.framework.app.functional.page.Slingshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import bg.framework.app.functional.action.selfServe.AccountSummaryAction;
import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;
import bg.framework.app.functional.util.RunQTP;
import bg.framework.app.functional.common.ApplicationConfig;

public class AccountSummaryPage extends BasePage{
	DirectDebit directDebit;
	private final static String File_AccPage = "resources/Slingshot/AccountSummary.properties";
    private static Properties pageProperties = new PropertyLoader(File_AccPage).load();
    
    
    String Pass_Str="Pass";
    String Fail_Str="Fail";
    String functionResult;
    public void clickSeeYourStatementLink(){
    	//browser.open("https://10.224.70.83/Your_Account/Update-PbFlag/");
    	browser.wait(2000);
    	String link=browser.getTextByXpath(pageProperties.getProperty("AccountSummary.seeYourStatementLink").replace("NUMBER",""+1));
		verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.seeYourStatementLink").replace("NUMBER", ""+1), link);
		//verifyPageTitle("View bill/statement");
    }
    public void clickViewAllAccounts(){
    	new AccountSummaryAction().allAccountOverviewPageAction();
    }
    public void clickGoPaperLessLink(){
    	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.goPaperLessLink"), "Go Paperless link");
    	verifyPageTitle("Sign up for paperless billing");
    }
    
    public void verifyServiceNotDue(){
    	verifyIsTextPresent("Your annual service is not due");
    }
    
    public void verifyServiceIsDue(){
    	verifyIsTextPresent("Your annual service is due");
    }
        
    public void pbFlagLinkNonExistverification(){
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.goPaperLessLink"))){
    		Report.updateTestLog("Go Paperless link exist in page", "FAIL");
    	}else{
    		Report.updateTestLog("Go Paperless link not exist in page", "PASS");
    	}
    }
    
    public void crossCellVerification(){    	
    	ArrayList<String> accountList = new ArrayList<String>();   
    	Set<String> accountType=new HashSet<String>();
    	if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.differentAccountBox"))){
    		 accountList=(browser.getFromDropBox("id", pageProperties.getProperty("AccountSummary.differentAccountBox"))); 
    	for(String account:accountList){
    	accountType.add(account.substring(0, account.indexOf('-')).trim());
    	}    	
    	}else{    		
    		accountType.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.product")));
    	}
    	    	    
    	System.out.println("MAPS---> "+accountType);
    	if(accountType.contains("Gas") && accountType.contains("Electricity") && accountType.contains("Insurance & Repair")){
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.energy"));    		
    	}else if(accountType.contains("Gas") && accountType.contains("Electricity")){
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.homecare"));    		
    	}else if(accountType.contains("Electricity")){
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.gas"));    		
    	}else if(accountType.contains("Gas")){
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.electricity"));    		
    	}else if(accountType.contains("Energy") ){
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.energy"));
    	}else if(!accountType.contains("Gas") && !accountType.contains("Electricity")){
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.electricity"));
    		otherOfferCheck(pageProperties.getProperty("AccountSummary.gas"));
    	}

    }
    private void otherOfferCheck(String type){    	      	
    	browser.wait(2000);
    	boolean fail=false;   
    	ArrayList<String> crossSell=new ArrayList<String>();
    	if(type.equalsIgnoreCase("Electricity")){
    		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink1"))){
        		crossSell.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink1")));    		
        	}
    	}else if(type.equalsIgnoreCase("Gas")){
    				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink2"))){
    					crossSell.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink2")));    		
    				}
    	}else if(type.equalsIgnoreCase("HomeCare")){
    				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink3"))){
    					crossSell.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink3")));    		
    				}
    	}else if(type.equalsIgnoreCase("Boiler")){
    				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink4"))){
    					crossSell.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink4")));    		
    				}
    	}else{
    		Report.updateTestLog("Cross sell not displayed", "FAIL");
    	}
    	if(!crossSell.isEmpty()){
    		for(String crossSellText:crossSell){
    			if(crossSellText.toLowerCase().contains(type.toLowerCase())){
    				Report.updateTestLog("Cross sell section "+type+" displayed", "PASS"); 
    				fail=false;
    				break;
    				}    		
    		else{
    				fail=true;
    }
    		}
    		if(fail){
    			Report.updateTestLog("Expecteed cross sell section not displayed", "FAIL"); 
    		}
    	}
    
    	/*if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink3"))){
    		crossSell.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink3")));    		
    	}
    	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink3"))){
    		crossSell.add(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink3")));
    	}
    	if(type.equalsIgnoreCase("find out more")){
    		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.crossCellLink3")))
    		System.out.println(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink3")));
    		System.out.println(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.crossCellLink3")).length());
    		if(browser.getTextByXpath(pageProperties.getProperty("AccountSummary.offerSection3")).length()==0 ||
    				browser.getTextByXpath(pageProperties.getProperty("AccountSummary.offerSection3")).toLowerCase().contains("new boiler")){
    			Report.updateTestLog("Customer has all accounts  <br><b>"+
    					browser.getTextByXpath(pageProperties.getProperty("AccountSummary.offerSection3")), "PASS");
    		}else{
    			Report.updateTestLog("customer owned offer displayed <br><b>"+
    					browser.getTextByXpath(pageProperties.getProperty("AccountSummary.offerSection3")), "FAIL");
    		}
    	}*/
    	
    }
    
    public void verifySSOAccount(){
    verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.conFirming"), "Confirming your order link");
    verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.energySuplyDate"), "We confirm your energy supply date");
    verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.meterReading"), "We ask you for your opening meter reading ");
    verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.setupComplete"), "Account set up is complete ");
    }
    
    public void verifyAccountSummaryPage(String accountNumber){
    	AccountSummaryAction accoutnSummaryPage=new AccountSummaryAction();
    	accoutnSummaryPage.verifyAccountSummaryPageAction(accountNumber);
    }
    
    public void logout(){
    	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.Logout"), "Log out link");
    	
    	//verifyAndClickWithXpath(pageProperties.getProperty("LoginPage.Loginagainnew"), "Log in again link");
    	//verifyIsTextPresent("Your account");
    	//verifyIsTextPresent("Log in"); 
    }
    
    public void verifyingLinksAccountSummaryPage(){
    	int count=browser.getChildElementsCountByXpath(pageProperties.getProperty("AccountSummary.accountSummaryLink").replace("[LINK]", ""));
    	System.out.println(count);
    	//browser.clickWithXpath("//div[@class='left-nav oamlefthandnav']/ul/li/ul/ul/li[1]");
    	String linkName[]={"Billing","Payments","Submit a meter reading","Your energy use","Your Nectar points","Moving home",
    	"Your messages","Discover your online account","Manage User Profile"};
    	for(int i=1;i<=count;i++){
    		String link=browser.getTextByXpath(pageProperties.getProperty("AccountSummary.accountSummaryLink").replace("LINK", ""+i));
    		String xpath=pageProperties.getProperty("AccountSummary.accountSummaryLink1").replace("LINK", ""+i);
    		System.out.println("LINK___ >: "+link +" -"+xpath);
    		//browser.clickWithXpath(xpath);
    		verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.accountSummaryLink1").replace("LINK", ""+i), link);
    		verifyIsTextPresent(linkName[i-1]);
    		//verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.linkName").replace("LINK", link), link);
    		browser.browserBack();
    		}
    	
    }
    
    public void loggeduserVerification(UserProfile userprofile){
    	browser.wait(1000);
    	final String strLastName = userprofile.getLastName();
    	//verifyAndInputById(pageProperties.getProperty("LoginPage.Email"), "Email Id", userProfile.getEmail());
    	String loggedInUserName = browser.getTextByXpath(pageProperties.getProperty("AccountSummary.userid"));
    	String loggedInUserNameArray[] = loggedInUserName.split(" ");
    	if (loggedInUserNameArray[2].equalsIgnoreCase(strLastName)){
    		Report.updateTestLog("Expected Logged In User is displayed and the User Name Is: "+loggedInUserName, Pass_Str);
        }
        else{
        	Report.updateTestLog("Expected Logged In User "+strLastName+" was not displayed", Fail_Str);
        }
    }
    
    public void AccountSummaryVerification(UserProfile userProfile){
    	verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.AccountOverviewPageTitle"));   
    	String jquery=("$(document).ready(function(){$('.accordion-head').trigger('click');});");        
        browser.executeScript(jquery);

    }
    
    public void verifyauditdetails(UserProfile userProfile){
//    	accountOverviewTitleVerification();
    	verifyPageTitle(pageProperties.getProperty("AccountSummary.TitleName")); 
    	loggeduserVerification(userProfile);
    	
//    	crosscellVerification();
    }
    
    public void verifyPageafterReopened(){
/*//    	browser.close();
    	Report.updateTestLog("Browser Closed succesfully", "Pass");
//    	Report.updateTestLog("Invoking makedriver function", "Pass");
    	WebDriverProvider.makeDriver();
//    	Report.updateTestLog("Invoking getdriver function", "Pass");
    	WebDriverProvider.getWebDriver();*/
    	browser.openTab(ApplicationConfig.APP_BG_URL);
    	
    }


    public void verifyLoginTryCountInDb(UserProfile userProfile,String recCount){
    	getWaitTime();
    	String loginTryCount = new OnlineDBConnector().verifyLoginTryCount(userProfile.getEmail());
    	/*if(loginTryCount.equals("null")){
    		loginTryCount="null"; 	
    	}*/
     	System.out.println("logincount:"+loginTryCount);
     	
     	if(loginTryCount!=null){
     		Report.updateTestLog("Expected Trycount:"+recCount+"Actual Trycount :"+loginTryCount,loginTryCount.equals(recCount)? "Pass":"Fail");
     	}else{
     		if(recCount.equalsIgnoreCase("null")){
     			Report.updateTestLog("Login trycount is null as expected","Pass");
     		}else{
     			Report.updateTestLog("Login trycount is not as expected","Fail");
     		}
     		
         }
    }
   public void verifyAuditDetailsInDb(UserProfile userProfile,String eventid){
	   try{
	     	OnlineDBConnector onlinedb = new OnlineDBConnector();
	     	final String loginauditdate = onlinedb.DBsysdateDdmmyyhhmi();
	     	String auditeventtype=onlinedb.BgbverifyAuditDetails(loginauditdate, userProfile.getEmail());
	     	
	     	if(auditeventtype!=null){
	     		if(auditeventtype.trim().equals(eventid.trim())){   
	     		  Report.updateTestLog("Audit type displayed is matching : "+ auditeventtype, "Pass");
	     		}else{
	     		  Report.updateTestLog("Audit type displayed is not matching -"+"Expected Audit type is:"
	     		                        +eventid+"Displayed Audit type is:"+auditeventtype, "Fail");		
	     		}
	     	}else{
	     		Report.updateTestLog("Audit type is Null", "Fail");
	     	}}catch(Exception e){
	     		Report.updateTestLog("Error message displayed is:"+e, "FAIL");	
	     	}
	     	
   }
   public void verifyLoginTimeStamp(UserProfile userProfile){
	   try{ 
		   OnlineDBConnector onlinedb = new OnlineDBConnector();
		   final String loginauditd = onlinedb.DBsysdateDdmmyyhhmi();
		   String entryCount=onlinedb.verifyLastLoginTimeStamp(loginauditd, userProfile.getEmail());
		   if(onlinedb.verifyLastLoginTimeStamp(loginauditd, userProfile.getEmail())!=null){
			  Report.updateTestLog("Expected Result : LoginTimestamp displayed","Pass");
		   }else{
			   Report.updateTestLog("Expected Result : LoginTimestamp is not displayed","Fail");
		   }
        }catch(Exception e){
	    		Report.updateTestLog("Error message displayed is:"+e, "FAIL");	
	    }

   }
   public void clickMPDLink(UserProfile userProfile){
	   /*verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.ManageAccountLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
	   browser.wait(getWaitTime());
	   verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryTitle"));*/
    	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.MPDLinkXpath"), "Update your details Link");
    	browser.wait(getWaitTime());
    	verifyPageTitle("Update your details");    	
    	//browser.browserBack();
    } 
    
   public void verifyAuditDetails(final UserProfile userProfile,int auditId) {     	
		OnlineDBConnector dbfunctions = new OnlineDBConnector();       
	    String sysDate = dbfunctions.DBsysdateDdmmyyhhmi();            
	    System.out.println(dbfunctions.getAuditTypeIdMs(userProfile.getEmail(), sysDate, auditId));
	    int getAudittype=dbfunctions.getAuditTypeIdMs(userProfile.getEmail(), sysDate, auditId);
	    if(getAudittype>=1){
	      Report.updateTestLog("Audit entry is made as expected: "+auditId, "PASS");
	      
	    }else{
	    	Report.updateTestLog("Audit entry is made as expected: "+auditId, "FAIL");
	    }
	    
	}
   public void validateActiveAndInactiveCustomer(){
	   Report.updateTestLog("Account Summary page should be updated with active and inactive accounts", "WARN");
   }
   public void verifyBrowserBackFunctionality(){
		browser.browserBack();
		browser.wait(getWaitTime());
		if(browser.getTitle().toString().equalsIgnoreCase("Account summary")){
			Report.updateTestLog("Account summary page remains in same page when user clicks browser back button in account summary page", "PASS");
		}
		else{
			Report.updateTestLog("Account summary page remains in same page when user clicks browser back button in account summary page", "FAIL");
		}
   }
   
   public void verifyandUpdateDetails(UserProfile userProfile){
	   
   }
   
   public void updateStatus(UserProfile userProfile){
	   new OnlineDBConnector().updateorRevertProfileRegistration(userProfile,2,"Revert");
   }
   
   public void verifyMsOnlineCustomer(){
	   verifyPageTitle(pageProperties.getProperty("AccountSummary.MsOnlineCustomerTitle"));
	   verifyIsTextPresent(pageProperties.getProperty("AccountSummary.MsOnlineCustomerTitle"));
   }
   
   public void clickLogoutMsOnlineCustomer(){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.MsOnlineCustomerLogout"),"Logout");
   }
   public void verifyAccountOverviewPage(){
   	verifyPageTitle(pageProperties.getProperty("AccountOverviewPage.Title"));
   	verifyIsTextPresent(pageProperties.getProperty("AccountOverviewPage.Header"));
   }
   public void verifySearchFunctionality(){
   	if(browser.isTextPresent(pageProperties.getProperty("AccountOverviewPage.SearchSection"))){    		
   		String strNumberOfAccounts = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.NumberOfAccounts"));
   		String[] accounts = strNumberOfAccounts.split(" ");
   		System.out.println("accounts----------------->"+accounts);
   		Report.updateTestLog("The account contains search functionality, Since it has "+accounts[2]+" number of accounts", "PASS");
       	}
   	else{
   		
   	}
   }
   public void getCustomerReferenceNumber(){
   	String customerRefNumber = browser.getAttribute(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber"), "value");
   	String strValue = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"), "class");
   	String strValue1 = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"), "value");
   	String strValue2 = browser.getAttributeByXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"), "class='cust-info fleft'");
   	System.out.println("************"+strValue +"************"+strValue1+"************"+strValue2+"************"+customerRefNumber);
   	//System.out.println(customerRefNumber);
   	}
  

   public void selectAccountForDirectDebit(DirectDebit directDebit){
	   String Manageaccount=pageProperties.getProperty("AccountSummary.ManageAccountLink")+directDebit.getAccNumber()+")]";
	   System.out.println("manageaccount link:"+Manageaccount);
	   for(int i=1;i<4;i++){
		   try{			   			   
			   if(browser.isElementVisibleWithXpath(Manageaccount)){
				   verifyAndClickWithXpath(Manageaccount, "ManageAccountLink clicked for this account"+directDebit.getAccNumber());
				   break;
			   }else if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.PaginationNextLink"))){
				   //function to click the pagination
				   if(clickPaginationNextLink(i).contains("false")){
//					   break;
				   }
			   }
		   }catch(Exception e){
			   Report.updateTestLog("Exception occured while clicking the manage Account link for the account:"+Manageaccount , "Fail");
		     }
	   }
	   if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.OptionsToSearch"))){
		   //function to click the drop down and select the account
		   
		   if(selectAccountFromDropDown(directDebit).contains("true")){
			   verifyAndClickWithXpath(Manageaccount, "ManageAccountLink clicked for this account"+directDebit.getAccNumber());
		   }else{
			   Report.updateTestLog("Manage Account link is not found/clicked for the account:"+Manageaccount , "Fail");   
		   }		   
	   }
	   
   }
   
   public String clickPaginationNextLink(int i){
	   
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummary.PaginationNextLink"))){
		   browser.clickWithXpath(pageProperties.getProperty("AccountSummary.PaginationNextLink"));
		   System.out.println("Pagination clicked :"+i);
		   functionResult="true";
	   }else{
		   functionResult="false";
	   }
	   return functionResult;
   }
   
	public String selectAccountFromDropDown(DirectDebit directDebit){
		   if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.OptionsToSearch"))){
			   Report.updateTestLog("Search by drop down displayed", "Pass");   
		    verifyAndSelectDropDownBox(pageProperties.getProperty("AccountSummary.OptionsToSearch"), "Search Accounts", "Account number");
		    verifyAndInputById(pageProperties.getProperty("AccountSummary.ContractAccountNumber"), "ContractAccountNumber", directDebit.getAccNumber());
		    verifyAndClick(pageProperties.getProperty("AccountSummary.SearchAccounts"),"Search-Accounts");
		       functionResult="true";
		   }else{
			   functionResult="false";
		   }
		   return functionResult;
	   }
   
   public void AccountOverviewVerification(){
	   
		 verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));  
		 
   	
   }
   public void verifyEIDAPageAfterLogin(){
	   
   }
   
   //****************Account drilldown page*********************
   public void getCustomerAddressAndAccountNumber(UserProfile userProfile){
	   //verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.AccountSummary"))){
		   String customerAccountNumber = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountNumber"));
		   Report.updateTestLog("Account number validation is done. "+customerAccountNumber,customerAccountNumber.contains(userProfile.getAccNumber())? "PASS":"FAIL");
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress"))){
		   String customerBillingAddress = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress"));	
		   System.out.println("customerAccountNumber "+customerAccountNumber+" customerBillingAddress"+customerBillingAddress);		   
		   Report.updateTestLog("Customer billing address and post code validation is done. "+customerBillingAddress,customerBillingAddress.contains(userProfile.getBillAddr())? "PASS":"FAIL");
		   }			
	   else{
		   Report.updateTestLog("Billing address is not available for this customer","DONE");
		   }
	   	}
	   }
   
   public void getAccountBalanceAndTypeOfAccount(){
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.AccountSummary"))){
		   String accountDetails = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountDetails"));
		   String accountBalance = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountBalance"));
		   
		   if(accountDetails.contains("debit")){
			   String accountType = "debit";
			   Report.updateTestLog("Acount type is "+accountType, "PASS");
		   }
		   else if(accountDetails.contains("credit")){
			   String accountType = "credit";
		   Report.updateTestLog("Acount type is "+accountType, "PASS");}
		   else
			   Report.updateTestLog("Please check the account type", "WARN"); 
			
		   if(accountDetails.contains(accountBalance)){
			   //String accountBalance = "67.55";
			   Report.updateTestLog("Account balance for customer "+accountBalance, "PASS");
		   }
		   else{
			   Report.updateTestLog("Account balance for customer "+accountDetails+"   "+accountBalance , "FAIL");
		   }
	   }else{
		   Report.updateTestLog("Unable to to fetch the account", "FAIL");
	   }
   }
   public void verifyAccountSummaryPageLinks(String bPNumber) {
browser.wait(getWaitTime());

         verifyPageTitle("Account summary");
         try
         {
        	
        if(browser.getTitle().equalsIgnoreCase("Account summary")){
        	ArrayList<String> link = new ArrayList<String>();
        	link.add("AccountSummaryPage.SubmitMeterRead");
        	link.add("AccountSummaryPage.Login");
        	link.add("AccountSummaryPage.Register");
        	link.add("AccountSummaryPage.Accountoverview");
        	link.add("AccountSummaryPage.Billing");
        	link.add("AccountSummaryPage.SetupDirectDebit");
        	link.add("AccountSummaryPage.Whatsthis");
        	link.add("AccountSummaryPage.ManagePersonalDeatilsLink");
        	link.add("AccountSummaryPage.ViewBillLink");
        	link.add("AccountSummaryPage.ManageDirectDebit");
        	
        	String s = link.toString();
        	System.out.println(s);
        	for (String i : link) {
        		System.out.println(i);
        		verifyLink(i);
        	}
        }  
         }
         catch(Exception e)
         {
        	 e.printStackTrace();
         }
        System.out.println("********I am Came verifyAccountSummaryPageLinks************************");
        browser.wait(getWaitTime());
    }
   public void verifyAccountSummaryPageLinksForInactiveAccounts() {
       //verifyPageTitle("Account Summary");
        if(browser.getTitle().equalsIgnoreCase("Account Summary")){
        	ArrayList<String> link = new ArrayList<String>();
        	link.add("AccountSummaryPage.SubmitMeterRead");
        	//link.add("AccountSummaryPage.whatsThisLink");
        	link.add("AccountSummaryPage.Login");
        	link.add("AccountSummaryPage.Register");
        	link.add("AccountSummaryPage.Accountoverview");
        	link.add("AccountSummaryPage.Billing");
        	link.add("AccountSummaryPage.SetupDirectDebit");
        	//link.add("AccountSummaryPage.SetupDirectDebit");
        	//link.add("AccountSummaryPage.FunctionalRefComponents");
        	link.add("AccountSummaryPage.Whatsthis");
        	//link.add("AccountSummaryPage.TestingFolder");
        	link.add("AccountSummaryPage.ManagePersonalDeatilsLink");
        	link.add("AccountSummaryPage.ViewBillLink");
        	String s = link.toString();
        	System.out.println(s);
        	for (String i : link) {
        		System.out.println(i);
        		verifyLink(i);
        	}
        }
            
    }
   public void verifyInactiveAccountLinks(){
	   if(browser.getTitle().equalsIgnoreCase("Account Summary")){
       	ArrayList<String> link = new ArrayList<String>();
       	link.add("AccountSummaryPage.SubmitMeterRead");
	   	link.add("AccountSummaryPage.ManageDirectDebit");
	   	for (String i : link) {
			System.out.println(i);
			verifyLinkForInactiveAccount(i);
		}
	   }
   }
   public void verifySeeyourLastBillLink(){
	   //Need to include "See your last bill" link 
	  
	  verifyIsElementVisibleWithXpath((pageProperties.getProperty("AccountSummaryPage.submitmeter")),"Submit Meter Read");
	  verifyIsElementVisibleWithXpath((pageProperties.getProperty("AccountSummaryPage.energyusage")),"Energy Usgae");
	  verifyIsElementVisibleWithXpath((pageProperties.getProperty("AccountSummaryPage.statementofaccount")),"Statement of account");
	  verifyIsElementVisibleWithXpath((pageProperties.getProperty("AccountSummaryPage.Payment")),"payment");
	  verifyIsElementVisibleWithXpath((pageProperties.getProperty("AccountSummaryPage.paperless")),"Paperless Optin");
	  verifyIsElementVisibleWithXpath((pageProperties.getProperty("AccountSummaryPage.quote")),"Get a Quote");
	  Report.updateTestLog("Account Summary page is displayed", "WARN");
	 /*  browser.clickWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewBillLink"));
	   if(browser.isTextPresent(pageProperties.getProperty("AccountSummaryPage.BillingPageTitle"))){
		   verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.BillingPageTitle"));
		   browser.browserBack();
		   browser.wait(getWaitTime());
	   }
	   else{
		   Report.updateTestLog("View bill page is not avilable", "FAIL");
	   }*/
	  
	  
	  System.out.println("********I am Came verifySeeyourLastBillLink************************");
   }
   public void verifySeeyourLatestStatementLink(){
	   //Need to include "See your latest statement" link after 'View bill' build
	   browser.clickWithXpath(pageProperties.getProperty("AccountSummary.ViewStatementAccount"));
	   if(browser.isTextPresent(pageProperties.getProperty("AccountSummaryPage.BillingPageTitle1"))){
		   verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.BillingPageTitle1"));
		   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.BackToYourAccountLink"), "Back to your account");
		   
	   }
	   else{
		   Report.updateTestLog("Search bill page is not avilable", "FAIL");
	   }
   }
   
   public void verifyWhatsThisLink(){	   
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.Whatsthis"), "What's this?");
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.WhatsThisOverLay"))){
		   String overlayContent = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.WhatsThisOverLayContent"));
		   System.out.println("overlayContent: "+overlayContent);
		   Report.updateTestLog("What's this overlay displayed with below content: "+overlayContent, "PASS");
		   	   }
	   else{
		   Report.updateTestLog("Please check what's this link " , "WARN");
	   }
	   
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.OverlayClose"))){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.OverlayClose"), "Overlay close button");
	   }
	   else{		 
		   RobotSendKeys.typeenter();
		   browser.wait(getWaitTime());
		   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
		   }
	      
   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.OverlayClose"))){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.OverlayClose"), "Overlay close button");
	   }
	   else{		 
		   RobotSendKeys.typeenter();
		   browser.wait(getWaitTime());
		   Report.updateTestLog("Overlay is closed by entering the 'Enter' key", "PASS");
		   }
   }
   public void verifyLink(String link) {
       String linkName = link.substring(link.indexOf('.') + 1, link.length());
       if (browser.isElementVisibleWithXpath(pageProperties.getProperty(link))) {
           Report.updateTestLog(linkName + " Link exist", "PASS");
       } else {
           Report.updateTestLog(linkName + " Link not exist", "FAIL");
       }
   }
   public void verifyLinkForInactiveAccount(String link) {
       String linkName = link.substring(link.indexOf('.') + 1, link.length());
       if (browser.isElementVisibleWithXpath(pageProperties.getProperty(link))) {
           Report.updateTestLog(linkName + " link exist", "FAIL");
       } else {
           Report.updateTestLog(linkName + " link not exist", "PASS");
       }
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
	        br.readLine();
	        br.readLine();
	        br.readLine();
	        br.readLine();
	        balance = br.readLine();
	        System.out.println("Account type : **************"+balance.substring(balance.length()-1));
	        if(balance.substring(balance.length()).equals("-")){
	        	Report.updateTestLog("The displayed account is credit.Since the balnce is "+balance, "PASS");
	        }
	        else{
	        	Report.updateTestLog("The displayed account is debit.Since the balnce is "+balance, "PASS");
	        }	        
	    }
	    catch(Exception e){
	    	Report.updateTestLog("Exception : "+e,"FAIL");
	    	}
	    }
   public void verifyBrowserBackFunctionalityInAccountSummaryPage(){
	   if(browser.getTitle().equalsIgnoreCase(pageProperties.getProperty("AccountSummaryPage.PageTitle"))){
		   browser.browserBack();
		   Report.updateTestLog("Browser back button clicked at Account summary page", "DONE");
		   verifyPageTitle(pageProperties.getProperty("AccountOverviewPage.Title"));
	   }	  
	 Report.updateTestLog("Browser navigates to 'Account overview' page when user clicks browser back button in 'Account summary' page", (browser.getTitle().equalsIgnoreCase(pageProperties.getProperty("AccountSummaryPage.AccountOverviewPageTitle")))?"PASS":"FAIL");
	      }
   public void verifyLoginEIDACustomer(){
	   verifyPageTitle(pageProperties.getProperty("AccountSummary.EIDACustomerTitle"));	  
	   verifyIsTextPresent(pageProperties.getProperty("AccountSummary.EIDACustomerHeader"));
	   RobotSendKeys.typeenter();
	   browser.wait(getWaitTime());
	   verifyPageTitle(pageProperties.getProperty("AccountSummary.EIDACustomerHomePageTitle"));
	   verifyIsTextPresent("Please provide the details below");
	   verifyIsTextPresent("Username");
	   verifyIsTextPresent("Account Executive");
	   verifyIsTextPresent("Submit");
	   
	   }
   public void clickSmrGlobalLink(){
	   
   }
   public void selectSmrForAccount(SMRAccountDetails smrAccountDetails){
	   String Manageaccount=pageProperties.getProperty("AccountSummaryPage.SubmitMeterReadLink")+smrAccountDetails.getAccountNumber()+")]";
	   System.out.println("SubmitMeterRead link:"+Manageaccount);
	   for(int i=1;i<4;i++){
		   try{			   			   
			   if(browser.isElementVisibleWithXpath(Manageaccount)){
				   verifyAndClickWithXpath(Manageaccount, "ManageAccountLink clicked for this account"+smrAccountDetails.getAccountNumber());
				   System.out.println("1st condition:"+Manageaccount);
				   break;
			   }else if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.SubmitMeterReadLink"))){
				   //function to click the pagination
				   if(clickPaginationNextLink(i).contains("false")){
//					   break;
				   }
			   }
		   }catch(Exception e){
			   Report.updateTestLog("Exception occured while clicking the manage Account link for the account:"+Manageaccount , "Fail");
		     }
	   }
	   if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.OptionsToSearch"))){
		   //function to click the drop down and select the account
		   
		   if(selectAccountFromDropDown1(smrAccountDetails.getAccountNumber()).contains("true")){
			   verifyAndClickWithXpath(Manageaccount, "SubmitMeterRead clicked for this account"+smrAccountDetails.getAccountNumber());
		   }else{
			   Report.updateTestLog("SubmitMeterRead link is not found/clicked for the account:"+Manageaccount , "Fail");   
		   }		   
	   }
	   
   }
   public String selectAccountFromDropDown1(String accountnumber){
	   if(browser.isElementVisible(pageProperties.getProperty("AccountSummary.OptionsToSearch"))){
		   Report.updateTestLog("Search by drop down displayed", "Pass");   
	    verifyAndSelectDropDownBox(pageProperties.getProperty("AccountSummary.OptionsToSearch"), "Search Accounts", "Contract Account Number");
	    verifyAndInputById(pageProperties.getProperty("AccountSummary.ContractAccountNumber"), "ContractAccountNumber", accountnumber);
	    verifyAndClick(pageProperties.getProperty("AccountSummary.SearchAccounts"),"Search-Accounts");
	       functionResult="true";
	   }else{
		   functionResult="false";
	   }
	   return functionResult;
   }
   public void AccountSummaryVerification(SMRAccountDetails smrAccountDetails){
   	verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.AccountOverviewPageTitle"));   
   	String jquery=("$(document).ready(function(){$('.accordion-head').trigger('click');});");        
       browser.executeScript(jquery);

   }
   public void getCustomerAddressAndAccountNumber(SMRAccountDetails smrAccountDetails){
	   //verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.AccountDetails"))){
		   String customerAccountNumber = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountNumber"));
		   Report.updateTestLog("Account number validation is done. "+customerAccountNumber,customerAccountNumber.contains(smrAccountDetails.getAccountNumber())? "PASS":"FAIL");
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress"))){
		   String customerBillingAddress = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress"));	
		   System.out.println("customerAccountNumber "+customerAccountNumber+" customerBillingAddress"+customerBillingAddress);		   
		   Report.updateTestLog("Customer "+customerBillingAddress,"DONE");
		   }			
	   else{
		   Report.updateTestLog("Billing address is not available for this customer","DONE");
		   }
	   	}
	   }
   public void verifyAccountDetails(SMRAccountDetails smrAccountDetails){
		try{
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AccountList"))){
				if(browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerReferenceNumber")).contains(smrAccountDetails.getAccountNumber())){
					Report.updateTestLog("Customer reference number: "+smrAccountDetails.getAccountNumber(), "PASS");
				}
				else{
					System.out.println("Customer reference number: "+smrAccountDetails.getAccountNumber());
					Report.updateTestLog("Customer reference number: "+smrAccountDetails.getAccountNumber(), "FAIL");
				}
				//String strTypeOfAccount = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.TypeOfAccount"));
				//System.out.println("strTypeOfAccount"+strTypeOfAccount);
				//Report.updateTestLog("Type of customer account : "+strTypeOfAccount, "PASS");
				/*
				String billingAddr = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.CustomerBillingAddress"));
				System.out.println("billingAddr"+billingAddr);
				Report.updateTestLog("The customer billing address: "+billingAddr, "PASS");*/
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress"))){
					   String customerBillingAddress = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress"));	
					 //  System.out.println("customerAccountNumber "+customerAccountNumber+" customerBillingAddress"+customerBillingAddress);		   
					   Report.updateTestLog("Customer billing address is "+customerBillingAddress,"DONE");
					   }			
				   else{
					   Report.updateTestLog("Billing address is not available for this customer","DONE");
					   }
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.AccountBalance"))){
					String accountBalance = browser.getTextByXpath(pageProperties.getProperty("AccountOverviewPage.AccountBalance"));
					String[] balance=accountBalance.split(":");
					System.out.println(accountBalance+"balance"+balance+"accountBalance"+balance[1]);
					Report.updateTestLog("Customer account balance : "+balance[1], "PASS"); 			
				}
				verifyIsElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLink"), "Manage account link");
			}
			else{
				Report.updateTestLog("Test data error", "WARN");
			}
		}
		catch(Exception e){
			Report.updateTestLog("Exception occured: "+e, "FAIL");
		}}

   public void clickViewBillLink(){
	   
	  if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewBillLink"))){
		  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewBillLink"), "View bill link"); 		  
	  }
   }
   public void clickAreYourDetailsUptoLink(){
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.AreYourDetailsUptoDateLink"))){
		   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.AreYourDetailsUptoDateLink"), "Are your details upto date? link");
		   getWaitTime();
		   //verifyIsTextPresent("Update your details");
	   }
   }
   public void verifyUpdateMyDetailsLink(){
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLink"))){
		   verifyAndClickWithXpath(pageProperties.getProperty("AccountOverviewPage.ManageAccountLink"), "Manage account link");
		   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.UpdateMyPersonalDetailsLink"), "How do I update my personal details?");
		   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.UpdatedeatilsLinkInOverLay"), "Update details link");
		   verifyIsTextPresent("Update your details");
	   }
   }
   public void getCustomerAddressAndAccountNumberInAccountSummary(UserProfile userProfile){
	   //verifyPageTitle(pageProperties.getProperty("AccountSummary.AccountSummaryPageTitle"));
	   
	   browser.wait(getWaitTime());
	   
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.AccountSummary1")))
	   {
		   String customerAccountNumber = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountNumber1"));
		   Report.updateTestLog("Account number validation is done. "+customerAccountNumber,customerAccountNumber.contains(userProfile.getAccNumber())? "PASS":"FAIL");
		   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress1")))
		   {
		System.out.println("222222222222222222222222222222222");
		
		  /* String customerBillingAddress = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.BillingAddress1"));
			System.out.println("111111111111111111111111111111111111111");
		   System.out.println("customerAccountNumber "+customerAccountNumber+" customerBilling Address"+customerBillingAddress);		   
		   Report.updateTestLog("Customer billing address and post code validation is done."+customerBillingAddress,customerBillingAddress.contains(userProfile.getBillAddr())? "PASS":"FAIL");
		   }			
	   else{
		   Report.updateTestLog("Billing address is not available for this customer","DONE");
		   }
	   	}*/
		   }
	   }
	   
	   System.out.println("********I am Came getCustomerAddressAndAccountNumberInAccountSummary************************");
	   }
   
   public void getAccountBalanceAndTypeOfAccountInAccountSummary(){
	   browser.wait(getWaitTime());
	   if(browser.isElementVisibleWithXpath(pageProperties.getProperty("AccountSummaryPage.AccountSummary1"))){
		   String accountDetails = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountDetails1"));
		   String accountBalance = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.AccountBalance"));
		   
		   if(accountDetails.contains("debit")){
			   String accountType = "debit";
			   Report.updateTestLog("Acount type is "+accountType, "PASS");
		   }
		   else if(accountDetails.contains("credit")){
			   String accountType = "credit";
		   Report.updateTestLog("Acount type is "+accountType, "PASS");}
		   else
			  // Report.updateTestLog("Please check the account type", "WARN"); 
			
		   if(accountDetails.contains(accountBalance)){
			   //String accountBalance = "67.55";
			   Report.updateTestLog("Account balance for customer "+accountBalance, "PASS");
		   }
		   else{
			   Report.updateTestLog("Account balance for customer "+accountDetails+"   "+accountBalance , "FAIL");
		   }
	   }else{
		   Report.updateTestLog("Unable to to fetch the account", "FAIL");
	   }
	   System.out.println("********I am Came getAccountBalanceAndTypeOfAccountInAccountSummary************************");
   }
   public void clickEnergyConsumptionGraphLink(UserProfile userProfile){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.EnergyConsumptionLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
	   browser.wait(getWaitTime());
	   Report.updateTestLog("Enery consumption page is displayed",(
			   browser.getTitle().equalsIgnoreCase(pageProperties.getProperty("AccountSummary.EnergyConsumptionTitle")))?"PASS":"FAIL" );
	   
   }
   public void clickMPDLink1(UserProfile userProfile)
   {
	//   	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.ManageAccountLink1"), "Manage your Account");
	   	verifyAndClickWithXpath(pageProperties.getProperty("AccountSummary.ManageAccountLink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Manage account link");
    	browser.wait(getWaitTime());

    }

   public void smrLink(){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.SMRLink"),"Submit meter read link");
	   browser.wait(getWaitTime());
	   verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.VerifySMRTitle"));
	   browser.browserBack();
   }
   public void smrCollectiveBillingAddressComparsion(UserProfile userProfile){
		try{
			//final String displayedAddress = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.CollectiveBillingAddressInAccSummaryPage"));
			final String expectedaddress =userProfile.getBillingAddr();  
		//	verifyAddresText(displayedAddress, expectedaddress);
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
   public void manageAccLink(){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.ManageAccountLink"),"Manage Account link" );
	   browser.wait(getWaitTime());
	   verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.VerifyManageAccTitle"));
   }
   public void smrCollectiveBillingAddressComparsionInAccSummary(UserProfile userProfile){
		try{
			final String displayedAddress = browser.getTextByXpath(pageProperties.getProperty("AccountSummaryPage.CollectiveBillingAddressInAccSummary"));
			final String expectedaddress =userProfile.getBillingAddr();  
			verifyAddresText(displayedAddress, expectedaddress);
			}
		catch(Exception e){
			Report.updateTestLog("Exception occured"+e, "FAIL");
		}
	}
  public void verifyAddresTextInAccSummary(final String displayedAddress, final String expectedaddress) {
		System.out.println(displayedAddress);
		System.out.println(expectedaddress);
		if (displayedAddress.trim().contains(expectedaddress.trim())) {
			Report.updateTestLog("Address validation. Expected   message:" +expectedaddress+" Actual message:"+ displayedAddress, "PASS");
		} else {
			Report.updateTestLog("Error message validation. Expected message:" +expectedaddress+" Actual message:"+ displayedAddress, "FAIL");
		}
	}
  public void viewBillLink(){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewBillLinkInAccSumm"),"View your last bill link");
	   browser.wait(getWaitTime());
	   verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.VerifyVeiwYourLastBillTitle"));
	   browser.browserBack();
  }
  public void payBillLink(){
	   verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.PayBillLink"),"View your last bill link");
	   browser.wait(getWaitTime());
	   verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.VerifyPayYourLastBillTitle"));
	   browser.browserBack();
 }
  public void viewConsumptionLink(){
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewConsumptionLink"),"View Consumption link");
	  browser.wait(getWaitTime());
	  verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.VerifyEnergyConsumptionTitle"));
	   browser.browserBack();
  }
  public void viewStatementOfAccLink(){
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.ViewStatementOfAccLink"),"View Statement of Account link");
	  browser.wait(getWaitTime());
	  verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.VerifyStatementOfAccTitle"));
	   browser.browserBack();
  }
  public void makeAPaymentLink(){
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.makeAPaymentLink"),"Make a payment link");
	  browser.wait(getWaitTime());
	  verifyPageTitle(pageProperties.getProperty("AccountSummaryPage.makeAPaymentLinkTitle"));
  }
  public void accountSummaryPageverification(String Usertype, UserProfile userProfile){
	  if(Usertype=="Super User")
	  {
		  verifyIsTextPresent("Super User");
		  //verifyIsTextPresent("Paperless billing");
	  }
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.QuickLinkSMR").replace("ACCOUNTNUMBER", userProfile.getAccNumber()),"Submit meter read");
	  browser.wait(getWaitTime());
	  verifyPageTitle("Submit meter read");
	  browser.wait(1000);
	  Report.updateTestLog("Submit meter page is displayed", "WARN");
	  browser.browserBack();
	  /*verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.QuickLinkViweBill"),"View your bills");
	  browser.wait(getWaitTime());
	  verifyPageTitle("View bill");
	  browser.browserBack();*/
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.QuickLinkUpdateDetails"),"Update your details");
	  browser.wait(getWaitTime());
	  verifyPageTitle("Update your details");
	  browser.browserBack();
	  browser.clickWithLinkText("How do I submit a meter read?");
	  //verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpSMR"),"Help and advice - How do i submit a meter read link");
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpSMRClose"), "How do i submit a meter read link - Close");
	  verifyIsTextPresent("How do I submit a meter read?");
	  browser.wait(getWaitTime());
	  //verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpReadMeter"),"Help and advice - How do I read my meter?");
	  browser.clickWithLinkText("How do I read my meter?");
	  verifyIsTextPresent("How do I read my meter?");
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpReadMeterClose"),"How do I read my meter? - Close");
	//  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpOnlineAcc"),"Help and advice - What can I do with my online account? ");
	  browser.clickWithLinkText("What can I do with my online account?");
	  browser.wait(getWaitTime());
	  verifyIsTextPresent("What can I do with my online account?");
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpOnlineAccClose"),"What can I do with my online account?  - Close");
	  //verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpcantseeAcc"),"Help and advice - I can’t see all of my accounts? ");
	  browser.clickWithLinkText("I can’t see all of my accounts?");
	  browser.wait(getWaitTime());
	  verifyIsTextPresent("I can’t see all of my accounts?");
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.HelpcantseeAccClose"),"I can’t see all of my accounts?  - Close");
	  }
  public void accountSummaryPageVerificationForSuperUserAndEIDA(String Usertype){
	 if(Usertype =="Super"){
		 verifyAndClickWithXpath(pageProperties.getProperty(""),"Manage Users");
		  browser.wait(getWaitTime());
		  verifyPageTitle(pageProperties.getProperty(""));
		  browser.browserBack();
	 }
	 /*if(Usertype =="EIDA"){
		 verifyAndClickWithXpath(pageProperties.getProperty(""),"Manage Users");
		  browser.wait(getWaitTime());
		  verifyPageTitle(pageProperties.getProperty(""));
		  browser.browserBack();
		  verifyAndClickWithXpath(pageProperties.getProperty(""),"Energy Dashboard");
		  browser.wait(getWaitTime());
		  verifyPageTitle(pageProperties.getProperty(""));
		  browser.browserBack();
	 }*/
}
  public void accountSummaryDDPodVerification(){
	  verifyAndClickWithXpath(pageProperties.getProperty("AccountSummaryPage.ManageAccountLink"),"Manage Account link" );
	  browser.wait(200);
	  if(browser.isElementVisibleWithXpath(pageProperties.getProperty("")))
	  {
	Report.updateTestLog("DD pod is available in account summary page", "PASS");
	  }
	else
     {
	 Report.updateTestLog("DD pod is absent in account summary page", "FAil"); 
	  }
}  
  
  

}
