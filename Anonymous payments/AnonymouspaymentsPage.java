package bg.framework.app.functional.page.Slingshot;

import java.util.Properties;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import junit.framework.Test;

public class AnonymouspaymentsPage extends BasePage  {
	private final static String FILE_NAME = "resources/Slingshot/Anonymouspaymentbgb.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();

	// Navigate to BGB URL and navigate to Make Payment option and verify the Landing page.
	public void navigatetobgburl_clickonMyPaymentoption(UserProfile userprofile){
		
		String url; 
		
		try {
		url = browser.getURL();
		StringBuilder sb = new StringBuilder();
		StringBuilder newurl = sb.append(url).insert(4, "s");
		
		browser.open(pageProperties.getProperty("BGB_Business_URL"));
		Report.updateTestLog("Launching British Gas Business URL", "DONE");
		
		//verifyAndClickWithLinkText("Payments & Direct Debit set up", "Payment Option");
		browser.wait(1000);
		
		/*String landingpageurl = browser.getURL();
		if(landingpageurl.equals(newurl+pageProperties.getProperty("BGB_Business_makepayment_URL"))){
		Report.updateTestLog("Landing Page URL is verified successfully", "PASS");
		verifyIsElementVisibleWithXpath("//*[@id='h1']", "Header Text");
		}else{
		Report.updateTestLog("Mismatch in the Landing Page URL = " + url+pageProperties.getProperty("BGB_Business_makepayment_URL"), "FAIL");
		}*/
		browser.open("https://10.224.70.111/business/pay/account-details");
		browser.wait(2000);
		
		//verifyAndClickWithLinkText((pageProperties.getProperty("Anonymouspaymentsbgb_Makeapayment_linktext")), "Make Payment Link");
		browser.wait(2000);
		
		} catch (Exception e) {
			Report.updateTestLog("Launching British Gas Business URL - Browser Exception", "FAIL");
		}
		
	}
	
	// Entering Valid Acc no and Post Code 
	public void entering_valid_accno_postcode(UserProfile userprofile){
		verifyAndInputById("contractAccountNumber", "Account Number", userprofile.getAccNumber());
		verifyAndInputById("postcode", "Postcode", userprofile.getPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Continue_button_yourdetailspage_Xpath"), "Continue Button (Your Details Page)");
		browser.wait(20000);
	}
	
	// Confirmation Page Validation
	public void confirm_payment_page_validation(UserProfile userprofile){
		browser.wait(10000);
		
		verifyAndInputByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_amounttopay_field_xpath"), "Amount Entered" , userprofile.getAmountToPay());
		card_payment(userprofile);
		verifyPageTitle(pageProperties.getProperty("Anonymouspaymentsbgb_confirmpayment_title_and_header"));
		verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_confirmpayment_title_and_header"),"Header Text 1");
		verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_confirmpayment_header2"), "Header Text 2");		

		String confirmamt = browser.getAttributeByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_amounttopay_confirm_xpath"), "value");
		String disablestatus = browser.getAttributeByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_amounttopay_confirm_xpath"), "disabled");
		
		if(confirmamt.equals(userprofile.getAmountToPay())){
		Report.updateTestLog("Confirmation Page - Payment Amount = " + confirmamt  + " - Verified", "PASS");
		if(disablestatus.equals("true")){
		Report.updateTestLog("Payment field Disabled status = " + disablestatus , "PASS");
		}else{
			Report.updateTestLog("Payment field Disabled status = " + disablestatus , "FAIL");
		}
		}else{
		Report.updateTestLog("Confirmation Page - Payment Amount & Field Disable status verification", "FAIL");
		}
		
		if(userprofile.getEmail()!=null || userprofile.getEmail()!="" ){
			verifyIsTextPresent(userprofile.getEmail(), "Confirmation Email");
		}
		
		verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Continue_button_confirmationpage_Xpath"), "Pay Now Button");
		
	}	
	
	public void confirm_page_validation(UserProfile userprofile){
		
		browser.wait(5000);
		verifyTextWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_confirm_page_header"), "Confirm Page Header - ", pageProperties.getProperty("Anonymouspaymentsbgb_confirm_page_header_verification_text"));
		
		String previousbalance = browser.getTextByXpath("//*[@id='balanceRowData']").substring(1);
		String amountpaid = browser.getTextByXpath("//*[@id='amtPaidRowData']").substring(1);
		String currentbalance = browser.getTextByXpath("//*[@id='currentBalRowData']").substring(1);
		
		float amountpaidfloat = Float.valueOf(amountpaid);
		float previousbalancefloat = Float.valueOf(previousbalance);
		float currentbalancefloat = Float.valueOf(currentbalance);
		float amountenteredbyuser = Float.valueOf(userprofile.getAmountToPay());
		
		float validatecurbal = previousbalancefloat - amountpaidfloat;
		
		if (amountpaidfloat==(amountenteredbyuser)) {
			Report.updateTestLog("Payment Amount verification (Confirm Page) = £" + amountpaid, "PASS");
			if (validatecurbal == currentbalancefloat) {
				Report.updateTestLog("Previous Balance - £" + previousbalance + " and Current Balance - £"
						+ currentbalance + " - Verification Successful", "PASS");
			} else {
			Report.updateTestLog("Previous Balance - £" + previousbalance + " and Current Balance - £"
						+ currentbalance + " - Mismatch in logic", "FAIL");
			}
		} else {
			Report.updateTestLog("Payment Amount verification (Confirm Page) = £" + amountpaid, "FAIL");
		}
	}
	
	// Invalid post code check. (invalid post code in xml)
	public void invalidpostcode_validation(UserProfile userprofile){
		verifyAndInputById("contractAccountNumber", "Account number", userprofile.getAccNumber());
		verifyAndInputById("postcode", "Invalid Postcode", userprofile.getinvalidPostCode());
		verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Continue_button_yourdetailspage_Xpath"), "Continue Button");
		browser.wait(2000);
		if(verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_invaidpostcode_text"), "Invalid Postcode Error message")==true){
			Report.updateTestLog("Error Message for Invalid Post code verfification", "PASS");
			Report.updateTestLog("Error Message for Invalid Post code verfification", "WARN");
		}else{
			Report.updateTestLog("Error Message for Invalid Post code verfification", "FAIL");
		};
		
	}
	
	//Payment Verification for user paying Lesser than 2 Pounds (Amount to pay in xml should be < 2 Pounds)
	public void paymentverification_for_lessthan_twopounds (UserProfile userprofile){
		browser.wait(2000);
		verifyAndInputByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_amounttopay_field_xpath"), "Amount Entered", pageProperties.getProperty("Anonymouspaymentsbgb_amounttopay"));
		card_payment(userprofile);
		try {
			verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_errormsgforlessthantowpounds1_xpath"), "Error message for less than 2 Pounds");
			verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_errormsgforlessthantowpounds2_xpath"), "Error message for less than 2 Pounds");
			Report.updateTestLog("Error Messages Validation for Less than 2 Pounds", "PASS");
		} catch (Exception e) {
			Report.updateTestLog("Error Messages for Validation for Less than 2 Pounds", "FAIL");
		}
		verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Errormsgdialog_close_xpath"), "Close button");
		//browser.clickbutton(pageProperties.getProperty("Anonymouspaymentsbgb_Errormsgdialog_close_id"));
	
	
	}
	
	// Payment Verification for user paying Greater than his Payment Amount 
	public void paymentverification_for_greaterthanpayment_amount (UserProfile userprofile){
		browser.wait(2000);
		String balance = browser.getTextByXpath("//*[@id='amountToPayValueWithComma']");
		String x = balance.substring(1);
		float amounttopay = Float.valueOf(x);
		int newamounttopay = Math.round(amounttopay) + 2;
		String finalamount = Integer.toString(newamounttopay);
		verifyAndInputByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_amounttopay_field_xpath"), "Amount Entered" , finalamount);
		browser.wait(2000);
		card_payment(userprofile);
		try {
			verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_errormsgformorethanpaymentamount1_xpath"), "Error message for more than Payment Amount");
			verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_errormsgformorethanpaymentamount2_xpath"), "Error message for more than Payment Amount");
			Report.updateTestLog("Error Messages Validation for more than Payment Amount", "PASS");
		} catch (Exception e) {
			Report.updateTestLog("Error Messages for Validation for more than Payment Amount", "FAIL");
		}
		verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Errormsgdialog_close_xpath"), "Close button");	
		//browser.clickbutton(pageProperties.getProperty("Anonymouspaymentsbgb_Errormsgdialog_close_id"));
	}
	
	// Enter Card Details method
	public void card_payment(UserProfile userProfile){
		try {
			verifyAndInputById("nameOnCard", "Card Name", userProfile.getCardHolderName());
			verifyAndInputById("cardNumber", "Card Number", userProfile.getCardNumber());
			verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Anonymouspaymentsbgb_expirymonth_xpath"), "Card Expiry Month ", userProfile.getCardExpiryMonth());
			verifyAndSelectDropDownBox_xpath(pageProperties.getProperty("Anonymouspaymentsbgb_expiryyear_xpath"), "Card Expiry Month ", userProfile.getCardExpiryYear());
			verifyAndInputById("cvv", "Card CVV", userProfile.getCardCVV());
			verifyAndInputById("email", "Confirmation Email Address", userProfile.getEmail());
			verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Continue_button_paymentspage_Xpath"), "Continue Button (Payments Page)");
			browser.wait(5000);
			Report.updateTestLog("Updating Card Details", "PASS");
			browser.wait(2000);
		
		} catch (Exception e) {
			Report.updateTestLog("Updating Card Details", "FAIL");
		}
		
	}
    
	// Child Account Verification
public void childaccount_validtion (UserProfile userprofile){
	verifyAndInputById("contractAccountNumber", "Child Account Number", userprofile.getchildAccNumber());
	verifyAndInputById("postcode", "Post Code", userprofile.getChildaccnopostcode());
	verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Continue_button_yourdetailspage_Xpath"), "Continue Button (Your Details Page)");
	browser.wait(2000);
	if(verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_child_acc_no_errortext"), "Child Account Error message")==true)// To enter error message
	{
		String errormsg = browser.getTextByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_child_acc_errortxt2"));
		if(errormsg.contains(pageProperties.getProperty("Anonymouspaymentsbgb_child_acc_errortxt3")))
		Report.updateTestLog("Error Message for Child Account verfification", "PASS");
		try {
			browser.clickWithLinkText("Close");
		} catch (Exception e) {
		}
	}else{
		Report.updateTestLog("Error Message for IChild Account verfification", "FAIL");
	};
}

// Direct Debit Account no validation - Amount to pay should be greater than 2 pounds
public void directDebitaccount_validtion (UserProfile userprofile){
	verifyAndInputById("contractAccountNumber", "DD Account Number", userprofile.getddaccNumber());
	verifyAndInputById("postcode", "Post Code", userprofile.getddPostcode());
	verifyAndClickWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_Continue_button_yourdetailspage_Xpath"), "Continue Button (Your Details Page)");
	browser.wait(2000);
	//confirm_payment_page_validation(userprofile);
	//verifyPageTitle(pageProperties.getProperty("Anonymouspaymentsbgb_unsuccessfullpagetitle"));
	//String url = browser.getURL();
	//verifyTextWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_ddunabletoprocesserrormessage"), "Error Message", pageProperties.getProperty("Anonymouspaymentsbgb_ddunabletoprocesserrormessage_texttoverify"));
	//verifyTextWithXpath(pageProperties.getProperty("Anonymouspaymentsbgb_activepage_xpath"), "Header Active Page - ", pageProperties.getProperty("Anonymouspaymentsbgb_activepage_text"));
	
	if(verifyIsTextPresent(pageProperties.getProperty("Anonymouspaymentsbgb_DD_acc_no_errortext"), "DD Account Error message")==true)// To enter error message
	{
		String errormsg = browser.getTextByXpath(pageProperties.getProperty("Anonymouspaymentsbgb_child_acc_errortxt2"));
		if(errormsg.contains(pageProperties.getProperty("Anonymouspaymentsbgb_DD_acc_no_errortext")))
		Report.updateTestLog("Error Message for DD Account verfification", "PASS");
		Report.updateTestLog("Error Message for DD Account verfification", "WARN");
		try {
			browser.clickWithLinkText("Close");
		} catch (Exception e) {
		}
	}else{
		Report.updateTestLog("Error Message for DD Account verfification", "FAIL");
	};	
}

public void quit(){
	
	try {
		browser.close();
		Report.updateTestLog("Browser Closed successfully", "DONE");
	} catch (Exception e) {
		Report.updateTestLog("Issue while closing the Browser", "WARN");
	}
}

}
