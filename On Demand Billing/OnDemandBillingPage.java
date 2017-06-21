/**
 * 
 */
package bg.framework.app.functional.page.Slingshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Properties;

import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RunQTP;

/**
 * @author 292238
 *
 */
public class OnDemandBillingPage extends BasePage{
	private final static String FILE_NAME = "resources/Slingshot/OnDemandBilling.properties";	    
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
public void verifyODB(){
	if(browser.isTextPresent("")){
		Report.updateTestLog("On demand billing section is available for this account", "PASS");
		String accountNumber = browser.getTextByXpath(pageProperties.getProperty("ODB.AccountNumber"));
		String billAddr = browser.getTextByXpath(pageProperties.getProperty("ODB.BillingAddress"));
		String previousBalance = browser.getTextByXpath(pageProperties.getProperty("ODB.PreviousBalance"));
		String billAmount = browser.getTextByXpath(pageProperties.getProperty("ODB.BillAmount"));
		String newBalance = browser.getTextByXpath(pageProperties.getProperty("ODB.NewBalance"));
		
	}
	else{
		Report.updateTestLog("On demand billing section is available for this account", "FAIL");
	}
}
public void verifyODBWithISU(SMRAccountDetails smrProfile){
	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ODB.OnDemandBillingSection"))){
	Report.updateTestLog("On demand billing is exist for "+smrProfile.getAccountNumber(), "DONE");
	String accountNumber = browser.getTextByXpath(pageProperties.getProperty("ODB.AccountNumber"));
	String billAddr = browser.getTextByXpath(pageProperties.getProperty("ODB.BillingAddress"));
	String previousBalance = browser.getTextByXpath(pageProperties.getProperty("ODB.PreviousBalance"));
	String billAmount = browser.getTextByXpath(pageProperties.getProperty("ODB.BillAmount"));
	String newBalance = browser.getTextByXpath(pageProperties.getProperty("ODB.NewbalanceAmount"));
	System.out.println("accountNumber:"+accountNumber+"billAddr:"+billAddr+"previousBalance:"+previousBalance
			+"billAmount:"+billAmount+"newBalance:"+newBalance);
	Report.updateTestLog("Customer "+billAddr, "DONE");
	Report.updateTestLog("Account previous balance is"+previousBalance, "DONE");
	Report.updateTestLog("Bill amount is "+billAmount, "DONE");
	Report.updateTestLog("New balance is "+newBalance, "DONE");
	String strRetreiveEmailQry="select BUSINESS_PARTNER_ORG_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
			" where email='emailid'";
	String query=strRetreiveEmailQry.replace("emailid",smrProfile.getEmail().toLowerCase());
	if(new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query).equals(null)){
		Report.updateTestLog("BPORGNUMBER is null for the email"+smrProfile.getEmail(), "Fail");
		return;
	}
	String bpOrgNumber1=new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query);
	RunQTP runQTP = new RunQTP();
	runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\OnDemandBilling.vbs", bpOrgNumber1);
	browser.wait(15000);
	try {
		File file1 = new File("D:\\SAPData\\OnDemandBilling.txt");
		FileReader fr = new FileReader(file1);
		BufferedReader br = new BufferedReader(fr);

		String readdate1 = br.readLine();
		
		String billingAddr = null;
		System.out.println("readdate1:"+readdate1);
	
		
		if(readdate1.contains("Addr line:")){
			String readdate2 = br.readLine();
			System.out.println("readdate2:"+readdate2);
			if(readdate2.contains("Addr line:")){
			String readData1[] = readdate1.split(":");
			String readData2[] = readdate2.split(":");
			billingAddr = readData1[1]+","+readData2[1];
			System.out.println("billingAddr"+billingAddr + "billAddr:"+billAddr);
			if(billAddr.contains(billingAddr)){
			Report.updateTestLog("Address in Application: "+billAddr+"Address in ISU: "+billingAddr, "PASS");
			}
			else{
				if(billAddr.equalsIgnoreCase(billingAddr)){
					Report.updateTestLog("Address in Application: "+billAddr+"Address in ISU: "+billingAddr, "PASS");
				}
				else{
					Report.updateTestLog("Address in Application: "+billAddr+"Address in ISU: "+billingAddr, "FAIL");	
				}
				
			}
			}			
		}
		else{
			Report.updateTestLog("Address not read from ISU" , "FAIL");
		}
		String netBalance = br.readLine();
		String balance[] = netBalance.split("Total balance:");
	
		if(newBalance.equalsIgnoreCase(balance[1])){
			Report.updateTestLog("Net balance in Application: "+newBalance+"New Balance in ISU: "+balance[1], "PASS");

		}
		else{
			Report.updateTestLog("Net balance in Application: "+newBalance+"New Balance in ISU: "+balance[1], "FAIL");
		}
	}
	catch(Exception e){
		Report.updateTestLog("Exception occured : "+e, "FAIL");	
	}}
	else{
		Report.updateTestLog("On demand billing is displayed for account: "+smrProfile.getAccountNumber(), "FAIL");

	}
}

public void verifyODB_CollectiveAccount(SMRAccountDetails smrProfile){
	if(browser.isTextPresent(pageProperties.getProperty("ODB.OnDemandBillingSection"))){
		Report.updateTestLog("On demand billing is not displayed for collective account:"+smrProfile.getAccountNumber(), "PASS");
	}
	else{
		Report.updateTestLog("On demand billing is not displayed for collective account:"+smrProfile.getAccountNumber(), "FAIL");
	}
}
public void validateMprnNumber(){
	browser.switchToPrevFrame();
	String errormsgvalue="null";
	String mprnnumber[]={"","1234567890","12345678!@","1234567890123456789012","12345","12345678910"};

	for(int iterate=0;iterate < mprnnumber.length;iterate++){
		verifyAndSelectDropDownBox(pageProperties.getProperty("SubmitMeterRead.SearchBy"), "Search By", "Meter point reference number");
		verifyAndInputById(pageProperties.getProperty("SubmitMeterRead.KeywordToSearch"), "Keyword",mprnnumber[iterate]);   
		verifyAndClick(pageProperties.getProperty("SubmitMeterRead.SearchButton"), "Search Button");
		browser.wait(3000);
		if(iterate==0){
			errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("ODB.GlobalSmrErrorMessage1"));
		}else{
			errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("ODB.GlobalSmrErrorMessage2"));	
		}

		switch(iterate){
		case 0:
			Report.updateTestLog("Expected Result: "+SlingshotErrorMessages.Smr_EmptySearchCriteria+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(SlingshotErrorMessages.Smr_EmptySearchCriteria)?"Pass":"Fail");
			break;
		case 1:
			Report.updateTestLog("Expected Result: "+SlingshotErrorMessages.Smr_InvalidMPrn+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(SlingshotErrorMessages.Smr_InvalidMPrn)?"Pass":"Fail");
			break;
		case 2:
			Report.updateTestLog("Expected Result: "+SlingshotErrorMessages.Smr_SpecialCharWithAlphaMprn+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(SlingshotErrorMessages.Smr_SpecialCharWithAlphaMprn)?"Pass":"Fail");
			break; 
		case 3:
			Report.updateTestLog("Expected Result: "+SlingshotErrorMessages.Smr_MoreThan21Charac+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(SlingshotErrorMessages.Smr_MoreThan21Charac)?"Pass":"Fail");
			break;
		case 4:
			Report.updateTestLog("Expected Result: "+SlingshotErrorMessages.Smr_LessThanSixCharacter+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(SlingshotErrorMessages.Smr_LessThanSixCharacter)?"Pass":"Fail");
			break;
		case 5:
			Report.updateTestLog("Expected Result: "+SlingshotErrorMessages.Smr_MoreThanSixCharacter+"Actual Result: "+errormsgvalue+"Actual Result: "+errormsgvalue,errormsgvalue.contains(SlingshotErrorMessages.Smr_MoreThanSixCharacter)?"Pass":"Fail");
			break; 
		}
	}    	
}
public void verifyLinkNavigations(SMRAccountDetails smrProfile){
	//verifyAndClickWithXpath(pageProperties.getProperty("ODB.YourAccountBreadCrumb"), "Your account link");
	//Report.updateTestLog("Your account link in breadcrumb clicked and page navigated to "+browser.getTitle(), browser.getTitle().equalsIgnoreCase("Account overview")?"PASS":"FAIL");
	verifyIsElementVisibleWithXpath(pageProperties.getProperty("ODB.BackToYourAccount"), "Back to your account");
	//Report.updateTestLog("Back to your account link object clicked and page navigated to "+browser.getTitle(), browser.getTitle().equalsIgnoreCase("Account over")?"PASS":"FAIL");

}
public void verifyMailSentConfirmation(SMRAccountDetails smrProfile){
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	String date=dbFunctions.DBsysdateDdmmyyhhmi();
	String[] auditType = dbFunctions.getAuditEventTypeId(date, smrProfile.getEmail(),"New Balance");
	String data = dbFunctions.getAuditType(auditType[0]);	
	//String data1 = dbFunctions.getAuditType(auditType[1]);	
	System.out.println(Arrays.asList(auditType));
	Report.updateTestLog("Audit id is made for ODB email sent. Audit id: "+auditType[0]+ " Audit event type is: "+data, data.equalsIgnoreCase("ONDEMAND_BILLING_SUCCESS")?"PASS":"FAIL");
	Report.updateTestLog("Audit data is made in audit table as expected. Audit data: "+auditType[1],"DONE");

}
public void verifyPayNewBalanceLink(){
	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("ODB.NewBalance"))){
		verifyAndClickWithXpath(pageProperties.getProperty("ODB.NewBalance"), "Pay new balance");
		browser.wait(getWaitTime());
		Report.updateTestLog("Payment deatils page displayed as expected", browser.getTitle().equalsIgnoreCase("Payment details")?"PASS":"FAIL");
		browser.browserBack();
		browser.wait(getWaitTime());
			}
}
public void verifyPodLink(){
	verifyAndClickWithXpath(pageProperties.getProperty("View your account"), "View your account");
	browser.browserBack();
	Report.updateTestLog(browser.getTitle()+" page is displayed", "DONE");
	verifyAndClickWithXpath(pageProperties.getProperty("View your account"), "View your bill");
	browser.browserBack();
	Report.updateTestLog(browser.getTitle()+" page is displayed", "DONE");
	verifyAndClickWithXpath(pageProperties.getProperty("View your account"), "Make a payment");
	browser.browserBack();
	Report.updateTestLog(browser.getTitle()+" page is displayed", "DONE");
	verifyAndClickWithXpath(pageProperties.getProperty("View your account"), "View energy consumption");
	browser.browserBack();
	Report.updateTestLog(browser.getTitle()+" page is displayed", "DONE");
}
}
