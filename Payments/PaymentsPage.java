/**
 * 
 */
package bg.framework.app.functional.page.Slingshot;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.MakeAPaymentCardDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import bg.framework.common.functional.UIDriver;
import bg.framework.common.functional.WebDriverProvider;
/**
 * @author 208070
 *
 */
public class PaymentsPage extends BasePage{
	private final static String FILE_NAME = "resources/Slingshot/Payments.Properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
	public static String BalanceAmount;
	public static String BalanceAmountnumbers;
	public static String string_form;
	UIDriver driver = WebDriverProvider.getCurrentDriver();
	private static String errormsgvalue = "null", errormsgvalue1="null",errormsgvalue2="null",errormsgvalue3="null",errormsgvalue4="null",errormsgvalue5="null",Balance="null";

	public void navigatetoPayments(UserProfile userProfile) {
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.paymentslink").replace("ACCOUNTNUMBER", userProfile.getAccNumber()), "Payments Link");
		browser.wait(4000);
		verifyPageTitle("Payment details");
		
	}
	public void navigatetoMakeAPayment() {
		browser.wait(getWaitTime());
		//verifyAndClickWithXpath(pageProperties.getProperty("Payments.MakeAPayment"), "Make A Payment Link");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.MakeaPaymentNew"), "Make A Payment Link");
		browser.wait(4000);
		verifyPageTitle("Payment details");
	}
	public void verifyLinks() {
		verifyIsTextPresent("Back to Your account");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.PaymentsTitle")));
		verifyIsTextPresent("Make a payment for your energy usage");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.MakeAPayment"), "Make A Payment Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Payment details");
		browser.browserBack();
		browser.wait(getWaitTime());
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.AmendDirectDebit")))
		{
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.AmendDirectDebit"), "Amend Direct Debit");
			browser.wait(getWaitTime());
			if(browser.getTitle().equals("Amend Direct Debit"))
			{
				Report.updateTestLog("Amend Direct Debit - Page Title is Displayed", "Pass");
				browser.browserBack();
			}
			else
			{
				browser.wait(15000);
				verifyIsTextPresent("Direct Debit");
				verifyAndClickWithXpath(pageProperties.getProperty("Payments.AmendDirectDebitClose"), "Close Link");
				browser.browserBack();
			}
		}
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.SetupDirectDebit")))
		{
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.SetupDirectDebit"), "Set up Direct Debit");
			browser.wait(getWaitTime());
			verifyPageTitle("Set up Direct Debit");
			browser.browserBack();
		}
		browser.wait(getWaitTime());
		verifyIsTextPresent("We’re here to help…");
	
		verifyIsTextPresent("Chat to an agent online");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.OnlineBanking"), "Online Banking");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.OnlineBankingcontent")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.BACS"), "BACS");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.BACScontent")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Cash/Cheque"), "Cash/Cheque");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.Cash/Chequecontent")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.PostOffice"), "Post Office");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.PostOfficecontent")).trim());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Telephone"), "Telephone");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.Telephonecontent")));
	}

	public void paymentDetails(UserProfile userProfile) {
		String CardType[]={"Visa Debit", "Visa Delta", "Visa credit card", "MasterCard"};
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Cancel"), "Cancel Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Account overview");
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Back"), "Back Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Account summary");
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "4");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.LessAmount")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.LessAmountClose"), "Close Link");
		Balance = browser.getTextByXpath(pageProperties.getProperty("Payments.Balance"));
		String Balance1=Balance.substring(1,(Balance.indexOf('.')));
		int BalanceInt = Integer.parseInt(Balance1)+1;
		Balance1=""+BalanceInt;
		verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", Balance1);
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		String s= browser.getTextByXpath(pageProperties.getProperty("Payments.MoreAmount"));
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.MoreAmount")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.MoreAmountClose"), "Close Link");
		List<String> CardTypes = browser.getFromDropBox("id", pageProperties.getProperty("Payments.CardTypeDropdown"));
		for(int search=0;search<CardType.length;search++)
		{
			if(CardTypes.get(search+1).equals(CardType[search])){
				Report.updateTestLog(CardTypes.get(search+1) + " is available in the Query DropDown", "PASS");
			}
			else
			{
				Report.updateTestLog(CardTypes.get(search+1) + " is not available in the Query DropDown", "FAIL");
			}
		}
		verifyIsTextPresent("Ways you can pay");
		verifyIsTextPresent("Your payment is safe and protected by 3D secure");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.morethanonepayment"), "Can I make more than one payment? Link");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.morethanonepaymentOverlay")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.morethanonepaymentOverlayClose"), "Can I make more than one payment? Close Link");
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.HelpandAdvice"), "Help & Advice Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Help & advice - British Gas Business");
		browser.browserBack();browser.wait(getWaitTime());
		verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", userProfile.getAmountToPay());
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.CardTypeDropdown"), userProfile.getCardtype());
		verifyAndInputById(pageProperties.getProperty("Payments.CardHolderName"), "Card Holder Name", userProfile.getCardHolderName());
		verifyAndInputById(pageProperties.getProperty("Payments.CardNumber"), "Card Number", userProfile.getCardNumber());
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.CardNumWhatsThis"), "Card Number - What's this? Link");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.CardNumWhatsThisContent")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.CardNumWhatsThisContentClose"), "Card Number - What's this? Close Link");
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.ExpiryMonth"), userProfile.getCardExpiryMonth());
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.ExpiryYear"), userProfile.getCardExpiryYear());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.CVVWhatsThis"), "CVV - What's this? Link");
		verifyIsTextPresent(browser.getTextByXpath(pageProperties.getProperty("Payments.CVVWhatsThisContent")));
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.CVVWhatsThisContentClose"), "CVV - What's this? Close Link");
		verifyAndInputById(pageProperties.getProperty("Payments.CVV"), "CVV", userProfile.getCardCVV());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Confirm payment");
		verifyIsTextPresent("Our payment process includes the additional security of Verified by Visa and MasterCard SecureCode");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.PayNow"), "PayNow Button");
		browser.wait(20000);
//		verifyPageTitle("Card security check");
//		verifyIsTextPresent("Our payment process includes the additional security of Verified by Visa and MasterCard SecureCode");
//		browser.wait(getWaitTime());
//		driver.switchTo().frame(driver.findElement(By.xpath(pageProperties.getProperty("Payments.Submit"))));
		//verifyAndClickWithXpath(pageProperties.getProperty("Payments.Submit"), "Submit Button");
		verifyPageTitle("Confirmation");
		verifyIsTextPresent("Thank you for your payment");
		verifyIsTextPresent("We will email you confirmation that your payment has gone through");
		verifyIsTextPresent("Your payment for account " +userProfile.getAccNumber()+ " has been successful.");
		verifyIsTextPresent("Transaction reference");
		verifyIsTextPresent("Your previous balance");
		String PrevBalance = browser.getTextByXpath(pageProperties.getProperty("Payments.previousBalance"));
		if(Balance.trim().equals(PrevBalance.trim()))
		{
			Report.updateTestLog("Previous Balance is displayed correctly - " + PrevBalance, "PASS");
		}
		else
		{
			Report.updateTestLog("Previous Balance is not displayed correctly - " + PrevBalance, "FAIL");
		}
		verifyIsTextPresent("Amount Paid");
		String Amtpaid = browser.getTextByXpath(pageProperties.getProperty("Payments.AmtPaid"));
		int AmtPaidInt = Integer.parseInt(Amtpaid.substring(1));
		System.out.println("Amt paid"+AmtPaidInt);
		verifyIsTextPresent("Your new balance");
		String NewBalance = browser.getTextByXpath(pageProperties.getProperty("Payments.NewBalance"));
		int NewBalanceInt = Integer.parseInt(NewBalance.substring(1,(NewBalance.indexOf('.'))));
		System.out.println("New Balance"+NewBalanceInt);
		int PrevBalanceInt = Integer.parseInt(PrevBalance.substring(1,(PrevBalance.indexOf('.'))));
		System.out.println("Previous Balance"+PrevBalanceInt);
		if((PrevBalanceInt-AmtPaidInt)==NewBalanceInt)
		{
			Report.updateTestLog("New Balance is displayed correctly - " + NewBalance, "PASS");
		}
		else
		{
			Report.updateTestLog("New Balance is not displayed correctly - " + NewBalance, "FAIL");
		}
		verifyAndClickWithLinkText("Submit meter read", "Submit meter read");
		browser.wait(getWaitTime());
		verifyPageTitle("Submit meter read");
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithLinkText("View your account", "View your account");
		browser.wait(getWaitTime());
		verifyPageTitle("Account overview");
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithLinkText("View your bills", "View your bills");
		browser.wait(getWaitTime());
		verifyPageTitle("Search bill");
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithLinkText("Energy made simple", "Energy made simple");
		browser.wait(getWaitTime());
		verifyIsTextPresent("Energy Made Simple");
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithLinkText("Back to your account", "Back to your account");
		browser.wait(getWaitTime());
		verifyPageTitle("Account overview");
//		String AcctBalance = browser.getTextByXpath(pageProperties.getProperty("Payments.AcctBalance"));
//		if(AcctBalance.trim().equals(NewBalance.trim()))
//		{
//			Report.updateTestLog("Account Balance is displayed correctly - " + AcctBalance, "PASS");
//		}
//		else
//		{
//			Report.updateTestLog("Account Balance is not displayed correctly - " + AcctBalance, "FAIL");
//		}
//	       String strRetreiveEmailQry="select BUSINESS_PARTNER_ORG_NUMBER from BG_BUSINESS_TA_CUSTOMER_REG" +
//                   " where email='emailid'";
//	       String query=strRetreiveEmailQry.replace("emailid",userProfile.getEmail().toLowerCase());
//	       if(new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query).equals(null)){
//            Report.updateTestLog("BPORGNUMBER is null for the email"+userProfile.getEmail(), "Fail");
//            return;
//	       }
//	       String bpOrgNumber1=new OnlineDBConnector().getColumn("BUSINESS_PARTNER_ORG_NUMBER", query);
//	       RunQTP runQTP = new RunQTP();
//	       runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\OnDemandBilling.vbs", bpOrgNumber1);
//	       browser.wait(15000);
//	       try {
//	    	   File file1 = new File("D:\\SAPData\\OnDemandBilling.txt");
//	    	   FileReader fr = new FileReader(file1);
//	    	   BufferedReader br = new BufferedReader(fr);
//	    	   br.readLine();
//	    	   br.readLine();
//	    	   br.readLine();
//	    	   String netBalance = br.readLine();
//	    	   String balance[] = netBalance.split("Total balance:");
//	    	   NewBalance=(NewBalance.substring(1));
//	    	   balance[1]= balance[1].replace(',', '.');
//	              if(NewBalance.equalsIgnoreCase(balance[1])){
//	                     Report.updateTestLog("Net balance in Application: "+NewBalance+"New Balance in ISU: "+balance[1], "PASS");
//
//	              }
//	              else{
//	                     Report.updateTestLog("Net balance in Application: "+NewBalance+"New Balance in ISU: "+balance[1], "FAIL");
//	              }
//	       }
//	       catch(Exception e){
//	              Report.updateTestLog("Exception occured : "+e, "FAIL");       
//	       }
		}

	public void errorValidation(UserProfile userProfile) {
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.CardTypeDropdown"), "Select");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		browser.wait(getWaitTime());
		errormsgvalue=browser.getTextByXpath(pageProperties.getProperty("Payments.CardTypeError"));
		Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_CardType+"Actual Result: "+errormsgvalue,errormsgvalue.contains(errormsg.Payments_CardType)?"Pass":"Fail");
		String[] CardNumber={"","Saadha!@34","545645","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"};
		String[] CVV={"ABC","!@1","","A5"};
		for(int i=0;i<CardNumber.length;i++){
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.CardTypeDropdown"), userProfile.getCardtype());
		verifyAndInputById(pageProperties.getProperty("Payments.CardHolderName"), "Card Holder Name",  userProfile.getCardHolderName());
		verifyAndInputById(pageProperties.getProperty("Payments.CardNumber"), "Card Number", CardNumber[i]);
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.StartMonth"), "3");
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.StartYear"), "2015");
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.ExpiryMonth"), "3");
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.ExpiryYear"), "2014");
		verifyAndInputById(pageProperties.getProperty("Payments.IssueNumber"), "Issue Number", "AB");
		verifyAndInputById(pageProperties.getProperty("Payments.CVV"), "CVV", CVV[i]);
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		verifyIsTextPresent("Sorry, we need you to look at the following areas of the form again");
		verifyIsTextPresent("We're missing or don't recognise some of the information in:");
		errormsgvalue1=browser.getTextByXpath(pageProperties.getProperty("Payments.CardNoError"));
		errormsgvalue2=browser.getTextByXpath(pageProperties.getProperty("Payments.CardStartDateError"));
		errormsgvalue3=browser.getTextByXpath(pageProperties.getProperty("Payments.CardExpiryDateError"));
		errormsgvalue4=browser.getTextByXpath(pageProperties.getProperty("Payments.CardSecurityCodeError"));
		errormsgvalue5=browser.getTextByXpath(pageProperties.getProperty("Payments.IssueNumberError"));
		Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_CardNumber+"Actual Result: "+errormsgvalue1,errormsgvalue1.contains(errormsg.Payments_CardNumber)?"Pass":"Fail");
		Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_CardStartDate+"Actual Result: "+errormsgvalue2,errormsgvalue2.contains(errormsg.Payments_CardStartDate)?"Pass":"Fail");
		//Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_CardExpiryDate+"Actual Result: "+errormsgvalue3,errormsgvalue3.contains(errormsg.Payments_CardExpiryDate)?"Pass":"Fail");
		if(CVV[i]==""){
		Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_CVVBlank+"Actual Result: "+errormsgvalue4,errormsgvalue4.contains(errormsg.Payments_CVVBlank)?"Pass":"Fail");
		}
		else{
		Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_CVV+"Actual Result: "+errormsgvalue4,errormsgvalue4.contains(errormsg.Payments_CVV)?"Pass":"Fail");
		}
		
		Report.updateTestLog("Error Msg in Table: Expected Result: "+errormsg.Payments_IssueNumber+"Actual Result: "+errormsgvalue5,errormsgvalue5.contains(errormsg.Payments_IssueNumber)?"Pass":"Fail");
	}	
	}
	
	public void logOut() {
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Logout"), "Logout Link");
	}
	
		
	public void TwoCardlimit(int increment, UserProfile userProfile) {
		browser.wait(getWaitTime());
		
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.radio"), "Confirm Link");
		
		verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay",userProfile.getAmountToPay() );
		//browser.selectfromDropBox("id",pageProperties.getProperty("Payments.CardTypeDropdown"), userProfile.getCardtype());
		verifyAndInputById(pageProperties.getProperty("Payments.CardHolderName"), "Card Holder Name", userProfile.getCardHolderName());
		browser.wait(1000);
		verifyAndInputByXpath(pageProperties.getProperty("Payments.CardNumber"), "Card Number", userProfile.getCardNumber());
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.ExpiryMonth"), userProfile.getCardExpiryMonth());
		browser.selectfromDropBox("id",pageProperties.getProperty("Payments.ExpiryYear"), userProfile.getCardExpiryYear());
		verifyAndInputByXpath(pageProperties.getProperty("Payments.CVV"), "CVV", userProfile.getCardCVV());
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		browser.wait(getWaitTime());
		verifyPageTitle("Confirm payment");
		//verifyIsTextPresent("Our payment process includes the additional security of Verified by Visa and MasterCard SecureCode");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.PayNow"), "PayNow Button");
		browser.wait(5000);
//		verifyPageTitle("Card security check");
//		verifyIsTextPresent("Our payment process includes the additional security of Verified by Visa and MasterCard SecureCode");
		verifyPageTitle("Confirmation");
		verifyIsTextPresent("Thank you for your payment");
		//verifyIsTextPresent("We will email you confirmation that your payment has gone through");
		verifyAndClickWithLinkText("Your account", "your account");
		browser.wait(getWaitTime());
		verifyPageTitle("Account overview");
		}
	
	public void navigatetoMakeAPaymentError() {
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.MakeAPayment"), "Make A Payment Link");
		browser.wait(6000);
		verifyIsTextPresent("Please call us");
		verifyIsTextPresent("Sorry, we have been unable to process your payment");
		verifyIsTextPresent("We were unable to take your payment. Please contact us on 0800 316 2010");
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.NoPayment"), "Unable to make a Payment - Close button");
	}
	
	public void navigateToManageYourPaymentsCards(){
		browser.wait(2000);
		//if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ManagePaymentCardsLink"))){
			verifyAndClickWithLinkText(pageProperties.getProperty("Payments.ManagePaymentCardsLink"), "Manage Payment Cards Link");
			//verifyAndClickWithXpath(pageProperties.getProperty("Payments.ManagePaymentCardsLink"), "Manage Payment Cards Link");
			Report.updateTestLog("Manage your Payment cards link clicked successfully", "PASS");
		//}
		//else{
		//	Report.updateTestLog("Manage your Payment cards link is not present in the application", "FAIL");
		//}
			
	}
	
	public void verifyAndDeleteCardsInManageTable(){
		int tableSize = browser.getChildElementsCountByXpath(pageProperties.getProperty("Payments.TableSizeSection"));
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + tableSize);
		if(tableSize > 1){
		for(int i = 1 ; i < tableSize; i++){
			System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttttttt" + pageProperties.getProperty("Payments.delectLink").replace("ROW", i+""));
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.delectLink"), "Delet Link in " + i + "Row");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.delectCardButton"), "Delete Payment Card Button");
			String deleteConfPage = browser.getTextByXpath(pageProperties.getProperty("Payments.deleteConfPage"));
			if(deleteConfPage.contains("We've deleted your card")){
				Report.updateTestLog("Card has been deleted successfully", "WARN");
			}
			else{
				Report.updateTestLog("Card delete is not happened successfully", "FAIL");
			}
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.backToManageCardSection"), "Manage Card Section");
		}
		}
	}
	
	public void verifyAndEditCardsInManageTable(UserProfile userProfile){
		int tableSize = browser.getChildElementsCountByXpath(pageProperties.getProperty("Payments.TableSizeSection"));
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + tableSize);
		if(tableSize > 1){
		for(int i = 1 ; i <= tableSize; i++){
			System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttttttt" + pageProperties.getProperty("Payments.delectLink").replace("ROW", i+""));
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.EditLink").replace("NUM", i+""), "Edit Link in " + i + "Row");
			browser.wait(2000);
			verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Payments.EndMonth"),"End Month","6");
			verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Payments.EndYear"),"End Year","2022");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.editCardSaveButton"), "Edit Card Save Button");
			String editedConfPage = browser.getTextByXpath(pageProperties.getProperty("Payments.editedConfPage"));
			if(editedConfPage.contains("We’ve updated the details of your payment card")){
				Report.updateTestLog("Card has been edited successfully", "WARN");
				auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.EditCardDBMessage"));
			}
			else{
				Report.updateTestLog("Card edit is not happened successfully", "FAIL");
			}
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.backToManageCardSection"), "Manage Card Section");
		
			
		}
		}
	}
	
	public void cardStorageFunctionality(UserProfile userProfile){
		browser.wait(1000);
		for(int i=1;i<=10;i++){
		   MakeAPaymentCardDetails makeAPaymentCardDetails =new TestDataHelper().getMakeAPaymentCardDetails("Card"+i);
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.addANewPaymentCard"), "Add A New Payment Card");
			enterCardDetails(makeAPaymentCardDetails);	
			//selectDefaultCardCheckBox();
			submitCard();
			verifyAddedPaymentCardSection();
		    verifyPaymentCardSection(i);
		    auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.AddCardDBMessage"));

		}
	}
		public void enterCardDetails(MakeAPaymentCardDetails makeAPaymentCardDetails){
			//verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Payments.CardType"), "Card Type",makeAPaymentCardDetails.getCardType());
			verifyAndInputByXpath(pageProperties.getProperty("Payments.CardName"),"Card Name", makeAPaymentCardDetails.getNameOnCard());
			browser.wait(3000);
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CardNumber"));
			element.sendKeys(makeAPaymentCardDetails.getCardNumber());
			browser.wait(3000);
			verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Payments.EndMonth"),"End Month",makeAPaymentCardDetails.getExpiryMonth());
			verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Payments.EndYear"),"End Year",makeAPaymentCardDetails.getExpiryYear());
			//verifyIsElementVisibleWithLinkText(pageProperties.getProperty("Payments.backlink"),"Back Link");
		}
		
		public void selectDefaultCardCheckBox(){
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.DefaultCardCheckBox"),"Default Card Check Box");
		}

		
		public void verifyAddedPaymentCardSection(){
			String AddConfPage = browser.getTextByXpath(pageProperties.getProperty("Payments.deleteConfPage"));
			if(AddConfPage.contains("We've added your payment card")){
				Report.updateTestLog("Card Details Added Successfully", "WARN");	
			}
			else{
				Report.updateTestLog("Card Details is not added Successfully", "FAIL");	
			}
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.backToManageCardSection"), "Manage Card Section");
		}
		
		public void submitCard(){
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.submitCard"), "Submit Card");
		}
		
		public void verifyPaymentCardSection(int i){
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.RowsAdded").replace("NUM", i+""))){
				Report.updateTestLog("Card Details Updated", "WARN");	
			}
			else{
				Report.updateTestLog("Card Details not Updated", "FAIL");	
			}
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.RowsAddedwithDefault").replace("NUM", "1"))){
				Report.updateTestLog("Card Details Updated with Default", "PASS");	
			}
			else{
				Report.updateTestLog("Card Details not Updated with Default", "FAIL");	
			}
		}
		
		public void paymentViaSavedCard(){
			verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "2");
			verifyAndInputById(pageProperties.getProperty("Payments.CVV"), "CVV", "123");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.Confirm"), "Confirm Link");
		}
		
		public void paymentViaNewCard(UserProfile userProfile,String SaveCard){
			browser.wait(3000);
			verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "5");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.NewCardRadio"),"New Card Radio");
			MakeAPaymentCardDetails makeAPaymentCardDetails =new TestDataHelper().getMakeAPaymentCardDetails("Card7");
			//verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "8");
			enterCardDetails(makeAPaymentCardDetails);
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			element.sendKeys(makeAPaymentCardDetails.getSecurityCode());
			
			if(SaveCard.contains("Yes")){
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.saveIndicator"), "save Indicator Link");
				Report.updateTestLog("Save Card for Future is checkedin successfully", "WARN");
			}
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Confirm Pay Now Button");
			VerifyConfirmationPage(makeAPaymentCardDetails, userProfile);
			//String Cardtype = makeAPaymentCardDetails.getCardType();
			// Table Verification
			//auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.PaymentDBMessage").replace("CARDTYPE", Cardtype));
		}
		public void paymentViaNewCard_NotClickSaveButton(UserProfile userProfile ){
			browser.wait(3000);
			verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "2");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.NewCardRadio"),"New Card Radio");
			//browser.selectfromDropBox("id",pageProperties.getProperty("Payments.CardTypeDropdown"), userProfile.getCardtype());
			MakeAPaymentCardDetails makeAPaymentCardDetails =new TestDataHelper().getMakeAPaymentCardDetails("Card6");
			enterCardDetails(makeAPaymentCardDetails);
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			element.sendKeys(makeAPaymentCardDetails.getSecurityCode());
			
			//WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			//element.sendKeys(makeAPaymentCardDetails.getSecurityCode());	
			
		
			
			
			
		if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton"))){
		verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
		Report.updateTestLog("Payment Continue Button is Clicked succesfully", "WARN");
		}
		else {
			Report.updateTestLog("Payment Continue Button is not displayed", "FAIL");
			
		}
					
			
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton"))){
					verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Confirm Pay Now Button");
					Report.updateTestLog("Payment detail page is loaded successfully", "WARN");
				}
				else{
					Report.updateTestLog("Payment detail is not entered properly", "FAIL");
				}			
			VerifyConfirmationPage(makeAPaymentCardDetails, userProfile);
		}
		
		public void paymentDue () {
			
			
			verifyIsElementVisibleWithXpath((pageProperties.getProperty("Payments.PayementDue")),"Payment Due Link");
			
	
			
			
		}
		
		
		
		
		public void paymentViaNewCard_InValidAmount(){
			browser.wait(3000);
			verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "*6%,");
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.NewCardRadio"),"New Card Radio");
			MakeAPaymentCardDetails makeAPaymentCardDetails =new TestDataHelper().getMakeAPaymentCardDetails("Card6");
			enterCardDetails(makeAPaymentCardDetails);
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			element.sendKeys(makeAPaymentCardDetails.getSecurityCode());
			
			//WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			//element.sendKeys(makeAPaymentCardDetails.getSecurityCode());	
				if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton")))
			{
				verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
				{
					Report.updateTestLog("Forgot Details Link is Clicked succesfully", "WARN");
						}	
					
			}
						
			
		}

		public void VerifyConfirmationPage(MakeAPaymentCardDetails makeAPaymentCardDetails, UserProfile userProfile){
			
			browser.wait(getWaitTime());
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.PayConfirmationPage"))){
				Report.updateTestLog("Confirmation page loaded successfully", "WARN");
				String Cardtype = makeAPaymentCardDetails.getCardType();
				// Table Verification
				auditentryverification_for_payments(userProfile, pageProperties.getProperty("Payments.PaymentDBMessage").replace("CARDTYPE", Cardtype));
			}
			else{
				Report.updateTestLog("Confirmation page not loaded", "FAIL");
			}
			String referenceNum = browser.getTextByXpath(pageProperties.getProperty("Payments.PayReferenceNum"));
			Report.updateTestLog("Payment successfully and Reference Num : " + referenceNum, "WARN");
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.SetupDirectDebitBanner"))){
				Report.updateTestLog("Setup a Direct Debit Banner Section Displayed", "PASS");
				verifyAndClickWithXpath(pageProperties.getProperty("Payments.SetupDirectDebitBanner"), "Setup DD page");
			    verifyIsElementVisibleWithXpath(pageProperties.getProperty("Payments.SetupDDPage"), "Setup DD page");
			    Report.updateTestLog("Setup a Direct Debit Page is Displayed", "WARN");
			    
			}
				else {
					Report.updateTestLog("Setup a Direct Debit Section is not displayed", "FAIL");
				}
			browser.wait(1000);
			//verifyAndClickWithXpath(pageProperties.getProperty("Payments.logout"), "logged out sucessfully");
			
		}
		public void VerifypaymentViaExistingCard(UserProfile userProfile){
			
			browser.wait(getWaitTime());
			verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "6");
			//WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			MakeAPaymentCardDetails makeAPaymentCardDetails =new TestDataHelper().getMakeAPaymentCardDetails("Card8");
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			element.sendKeys(makeAPaymentCardDetails.getSecurityCode());
			//verifyAndInputByXpath(pageProperties.getProperty("Payments.CVV"), "CVV", "123");
			//verifyAndInputById(pageProperties.getProperty("Payments.CVV"), "CVV", "123");
			//verifyAndInputByXpath(pageProperties.getProperty("Payments.CVV"), "CVV", userProfile.getCardCVV());
			//browser.inputByXpath(pageProperties.getProperty("Payments.CVV"), "123");	
			browser.wait(getWaitTime());
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton"))){
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
			Report.updateTestLog("Payment detail page is loaded successfully", "WARN");
			}
			else{
				Report.updateTestLog("Payment detail is not entered properly", "FAIL");
			}
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton"))){
				verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Confirm Pay Now Button");
				Report.updateTestLog("Payment detail page is loaded successfully", "WARN");
			}
			else{
				Report.updateTestLog("Payment detail is not entered properly", "FAIL");
			}
			//verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
			//verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Confirm Pay Now Button");
			VerifyConfirmationPage(makeAPaymentCardDetails, userProfile);
		}
		
public void VerifypaymentViaExistingCardNew(UserProfile userProfile){
			
			browser.wait(getWaitTime());
			verifyAndInputByXpath(pageProperties.getProperty("Payments.AmountToPay"), "Amount To Pay", "2");
			WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			MakeAPaymentCardDetails makeAPaymentCardDetails =new TestDataHelper().getMakeAPaymentCardDetails("Card8");
			//WebElement element = browser.getElementByXpath(pageProperties.getProperty("Payments.CVV"));
			verifyAndInputByXpath(pageProperties.getProperty("Payments.CVV"), "CVV", "123");
			element.sendKeys(makeAPaymentCardDetails.getSecurityCode());
			//verifyAndInputByXpath(pageProperties.getProperty("Payments.CVV"), "CVV", "123");
			//verifyAndInputById(pageProperties.getProperty("Payments.CVV"), "CVV", "123");
			//verifyAndInputByXpath(pageProperties.getProperty("Payments.CVV"), "CVV", userProfile.getCardCVV());
			//browser.inputByXpath(pageProperties.getProperty("Payments.CVV"), "123");	
			browser.wait(getWaitTime());
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton"))){
			verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
			Report.updateTestLog("Payment detail page is loaded successfully", "WARN");
			}
			else{
				Report.updateTestLog("Payment detail is not entered properly", "FAIL");
			}
			if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.ContinueButton"))){
				verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Confirm Pay Now Button");
				Report.updateTestLog("Payment detail page is loaded successfully", "WARN");
			}
			else{
				Report.updateTestLog("Payment detail is not entered properly", "FAIL");
			}
			//verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Pay Continue Button");
			//verifyAndClickWithXpath(pageProperties.getProperty("Payments.ContinueButton"), "Confirm Pay Now Button");
			VerifyConfirmationPage(makeAPaymentCardDetails, userProfile);
		}
public void VerifyAccountSummaryPage(UserProfile userProfile){
			
			browser.wait(getWaitTime());
			BalanceAmount = "";
			Report.updateTestLog("Account Overview Page is loaded successfully", "WARN");
			String Debit = "in debit";
			String Credit = "in credit";
			System.out.println("****************************");
			if (browser.getTextByXpath(pageProperties.getProperty("Payments.Accountdetails")).contains(Debit)) {
		        Report.updateTestLog("Account type " + Debit + " is displayed", "PASS");
		        BalanceAmount = browser.getTextByXpath(pageProperties.getProperty("Payments.BalanceAmount"));
		        System.out.println("BalanceAmount is "+ BalanceAmount);
		        BalanceAmountnumbers =BalanceAmount.replaceAll("[^0-9.]", " ").trim();
		        System.out.println("BalanceAmountnumbers is"+BalanceAmountnumbers);
		       
		        Verifydotumber();
		        Verifypayment(userProfile);
		        
			}
		        else if (browser.getTextByXpath(pageProperties.getProperty("Payments.Accountdetails")).contains(Credit)) {
		            Report.updateTestLog("Payment type " + Credit + " is displayed", "PASS");
			}
			
			
		}
public void Verifypayment(UserProfile userProfile){
	int amount = Integer.valueOf(string_form);
	System.out.println("Now the Integer is"+amount);
	
    System.out.println(amount);
    if(amount>0)
    {
    	Report.updateTestLog("Amount is " + amount  + " is available. Eligible to do payments ", "PASS");
    	navigatetoPayments(userProfile);
    	navigatetoMakeAPayment();
    	//paymentViaNewCard_NotClickSaveButton(UserProfile userProfile);
    	
    	}
    else
    {
    	Report.updateTestLog("Amount is " + amount  + " is available. Not Eligible to do payments ", "PASS");
    }
}
    public void Verifydotumber(){
    	System.out.println("####################################");
    	String string_temp = new Double(BalanceAmountnumbers).toString();
    	string_form="";
    	string_form = string_temp.substring(0,string_temp.indexOf('.'));
    	
    	System.out.println("The numbers is "+string_form);
    	System.out.println("*********************");
    	int amount = Integer.valueOf(string_form);
    	System.out.println("Now the Integer is"+amount);
    	
    }
    public void VerifyPaymentforDebitDDAccount(){
		
		browser.wait(getWaitTime());
		BalanceAmount = "";
		Report.updateTestLog("Account Overview Page is loaded successfully", "WARN");
		String Debit = "in debit";
		String Credit = "in credit";
		System.out.println("****************************");
		if (browser.getTextByXpath(pageProperties.getProperty("Payments.Accountdetails")).contains(Debit)) {
	        Report.updateTestLog("Account type " + Debit + " is displayed", "PASS");
	        BalanceAmount = browser.getTextByXpath(pageProperties.getProperty("Payments.BalanceAmount"));
	        System.out.println("BalanceAmount is "+ BalanceAmount);
	        BalanceAmountnumbers =BalanceAmount.replaceAll("[^0-9.]", " ").trim();
	        System.out.println("BalanceAmountnumbers is"+BalanceAmountnumbers);
	        
	        if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.paymentslink")))
	        {
	        	Report.updateTestLog("Payments link is populating successfully", "Fail");
	    	}
	        Report.updateTestLog("Payments link is not populating", "Pass");
	        
		}
	 	
	} 	
	
public void VerifyPaymentforZeroBalanceAccount(){
		
		browser.wait(getWaitTime());
		BalanceAmount = "";
		Report.updateTestLog("Account Overview Page is loaded successfully", "WARN");
		System.out.println("****************************");
		 BalanceAmount = browser.getTextByXpath(pageProperties.getProperty("Payments.BalanceAmount"));
	        System.out.println("BalanceAmount is "+ BalanceAmount);
	        BalanceAmountnumbers =BalanceAmount.replaceAll("[^0-9.]", " ").trim();
	        System.out.println("BalanceAmountnumbers is"+BalanceAmountnumbers);
	        Report.updateTestLog("BalanceAmount is "+BalanceAmountnumbers, "Pass");
	        if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.paymentslink")))
	        {
	        	Report.updateTestLog("Payments link is populating successfully", "Fail");
	    	}
	        Report.updateTestLog("Payments link is not populating", "Pass");
	        
		}
public void VerifyAccountSummaryPage_forCreditAccount(){
	
	browser.wait(getWaitTime());
	BalanceAmount = "";
	Report.updateTestLog("Account Summary Page is loaded successfully", "WARN");
	String Credit = "in credit";
	System.out.println("****************************");
	if (browser.getTextByXpath(pageProperties.getProperty("Payments.Accountdetails")).contains(Credit)) {
        Report.updateTestLog("Account type " + Credit + " is displayed", "PASS");
        BalanceAmount = browser.getTextByXpath(pageProperties.getProperty("Payments.BalanceAmount"));
        System.out.println("BalanceAmount is "+ BalanceAmount);
        BalanceAmountnumbers =BalanceAmount.replaceAll("[^0-9.]", " ").trim();
        System.out.println("BalanceAmountnumbers is"+BalanceAmountnumbers);
        Report.updateTestLog("Balance Amount is"+BalanceAmountnumbers, "Pass");
        Verifydotumber();
        if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payments.paymentslink")))
        {
        	Report.updateTestLog("Payments link is populating successfully", "Fail");
    	}
        Report.updateTestLog("Payments link is not populating", "Pass");
        
	}
}

public void verifySetupDD(){
	browser.wait(3000);
	
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.VariableDD"),"Variable Direct Debit");
	browser.wait(3000);
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.YesButton"),"Yes radio Button Clicked");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.AccountHolderName"), "Account Holder Name", "Testing");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode1"), "Sort Code 1", "60");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode2"), "Sort Code 2", "80");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode3"), "Sort Code 3", "08");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.AccountNumber"), "Bank Account Number", "21441812");
	Report.updateTestLog("To Set up a Variable Direct Debit values entered successfully", "WARN");
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.continueDDSummary"),"Continue");
	
}

public void verifySummaryPage(){
	browser.wait(3000);
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.selectCheckbox"), "Select CheckBox");	
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.SetupDDButton"),"setup Direct Debit Button");
	browser.wait(3000);
	Report.updateTestLog("Confirmation Page is populated successfully", "WARN");
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.Backtoyouraccount"),"Back to your Account");
	Report.updateTestLog("Account Ovwerview Page", "WARN");
	
}
public void verifyamendDD(UserProfile userprofile){
	browser.wait(getWaitTime());
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.YesButton"),"Yes radio Button Clicked");
	browser.wait(getWaitTime());
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.AccountHolderName"), "Account Holder Name", "Testing");
	browser.wait(getWaitTime());
	browser.clearValueByXpath(pageProperties.getProperty("DDCustomer.sortcode1"));
	browser.clearValueByXpath(pageProperties.getProperty("DDCustomer.sortcode2"));
	browser.clearValueByXpath(pageProperties.getProperty("DDCustomer.sortcode3"));
	browser.wait(getWaitTime());
	
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode1"), "Amount To Pay", userprofile.getSortCode1());
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode2"), "Amount To Pay", userprofile.getSortCode2());
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode3"), "Amount To Pay", userprofile.getSortCode3());
	
	
	
	//verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode1"), "Sort Code 1", "60");
	/*verifyAndInputById(pageProperties.getProperty("DDCustomer.sortcode1"), "Sort Code 1", "60");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode2"), "Sort Code 2", "80");
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.sortcode3"), "Sort Code 3", "08");*/
	verifyAndInputByXpath(pageProperties.getProperty("DDCustomer.AccountNumber"), "Bank Account Number", "21441812");
	Report.updateTestLog("To Set up a Variable Direct Debit values entered successfully", "WARN");
	verifyAndClickWithXpath(pageProperties.getProperty("DDCustomer.continueDDSummary"),"Continue");
	
}

public void verifyAccountsummary(){
	browser.wait(3000);
	Report.updateTestLog("Account summary Page", "WARN");
	
}
public void auditentryverification_for_managecards(UserProfile userProfile, String DBmsg) {
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	System.out.println("OnlineDB In");
	browser.wait(2000);
	dbFunctions.verifycardDetails(userProfile, DBmsg);
	System.out.println("Inside jdc DB");
	}
public void auditentryverification_for_payments(UserProfile userProfile, String DBmsg) {
	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	System.out.println("OnlineDB In");
	browser.wait(2000);
	dbFunctions.verifycardDetails_payment(userProfile, DBmsg);
	System.out.println("Inside jdc DB");
	}

// November 7th Release
public void payment_search(){
	
	verifyAndInputByXpath(pageProperties.getProperty("Payment.SearchBar"), "Entering Data in Search bar", pageProperties.getProperty("Payment.WordtoSearch"));
	verifyAndClickWithXpath("//*[@id='newSearchFormSmall']/fieldset/button", "Search button");
	browser.wait(60000);
	String searchedtext = browser.getAttributeByXpath(pageProperties.getProperty("Payment.Searchbarnextpage"), "value");
	if(searchedtext.equalsIgnoreCase(pageProperties.getProperty("Payment.WordtoSearch")))// Word_To_Search - Pay my Bill or Make a Payment (Change it according to the scenario in Payment.WordtoSearch
	{
		Report.updateTestLog("Search Entry successful", "WARN");
	}else{
		Report.updateTestLog("Search Entry Mismatch", "Fail");
	}
	
	if(browser.isElementVisibleWithXpath(pageProperties.getProperty("Payment.firstlinkxpath"))){
		verifyAndClickWithXpath(pageProperties.getProperty("Payment.firstlinkxpath"), "First Link");
		getWaitTime();
		String header= browser.getTextByXpath(pageProperties.getProperty("Payment.headerid"));
		if(header.equalsIgnoreCase(pageProperties.getProperty("Payment.Wordtoverify"))){
			verifyPageTitle(pageProperties.getProperty("Payment.pTitle"));
			Report.updateTestLog("Navigated to Payments page: "+ browser.getURL(), "Pass");
		}else{
			Report.updateTestLog("Navigated to Payments page: Unsuccessfull "+ browser.getURL()+ "And Header :" +header, "Fail");
		}
	}else{
		Report.updateTestLog("No Search Item found", "Fail");
	}
}

public void open_bgburl(){
	browser.open(ApplicationConfig.APP_BG_URL+pageProperties.getProperty("Payment.bgburl"));
}

}
